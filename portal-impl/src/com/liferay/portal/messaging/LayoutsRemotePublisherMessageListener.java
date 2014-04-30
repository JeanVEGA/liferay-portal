/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.messaging;

import com.liferay.portal.kernel.lar.ExportImportDateUtil;
import com.liferay.portal.kernel.messaging.BaseMessageStatusMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageStatus;
import com.liferay.portal.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateRange;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.ExportImportConfiguration;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bruno Farache
 * @author Daniel Kocsis
 */
public class LayoutsRemotePublisherMessageListener
	extends BaseMessageStatusMessageListener {

	public LayoutsRemotePublisherMessageListener() {
	}

	@Override
	protected void doReceive(Message message, MessageStatus messageStatus)
		throws Exception {

		long exportImportConfigurationId = GetterUtil.getLong(
			message.getPayload());

		ExportImportConfiguration exportImportConfiguration =
			ExportImportConfigurationLocalServiceUtil.
				getExportImportConfiguration(exportImportConfigurationId);

		messageStatus.setPayload(exportImportConfiguration);

		Map<String, Serializable> settingsMap =
			exportImportConfiguration.getSettingsMap();

		long userId = MapUtil.getLong(settingsMap, "userId");
		long sourceGroupId = MapUtil.getLong(settingsMap, "sourceGroupId");
		boolean privateLayout = MapUtil.getBoolean(
			settingsMap, "privateLayout");
		Map<Long, Boolean> layoutIdMap = (Map<Long, Boolean>)settingsMap.get(
			"layoutIdMap");
		Map<String, String[]> parameterMap =
			(Map<String, String[]>)settingsMap.get("parameterMap");
		String remoteAddress = MapUtil.getString(settingsMap, "remoteAddress");
		int remotePort = MapUtil.getInteger(settingsMap, "remotePort");
		String remotePathContext = MapUtil.getString(
			settingsMap, "remotePathContext");
		boolean secureConnection = MapUtil.getBoolean(
			settingsMap, "secureConnection");
		long remoteGroupId = MapUtil.getLong(settingsMap, "remoteGroupId");
		boolean remotePrivateLayout = MapUtil.getBoolean(
			settingsMap, "remotePrivateLayout");
		DateRange dateRange = ExportImportDateUtil.getDateRange(
			exportImportConfiguration);

		initThreadLocals(userId, parameterMap);

		try {
			StagingUtil.copyRemoteLayouts(
				sourceGroupId, privateLayout, layoutIdMap, parameterMap,
				remoteAddress, remotePort, remotePathContext, secureConnection,
				remoteGroupId, remotePrivateLayout, dateRange.getStartDate(),
				dateRange.getEndDate());
		}
		finally {
			resetThreadLocals();
		}
	}

	protected void initThreadLocals(
			long userId, Map<String, String[]> parameterMap)
		throws Exception {

		User user = UserLocalServiceUtil.getUserById(userId);

		CompanyThreadLocal.setCompanyId(user.getCompanyId());

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		PrincipalThreadLocal.setName(user.getUserId());

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(user.getCompanyId());
		serviceContext.setPathMain(PortalUtil.getPathMain());
		serviceContext.setSignedIn(!user.isDefaultUser());
		serviceContext.setUserId(user.getUserId());

		Map<String, Serializable> attributes =
			new HashMap<String, Serializable>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String param = entry.getKey();
			String[] values = entry.getValue();

			if (ArrayUtil.isNotEmpty(values)) {
				if (values.length == 1) {
					attributes.put(param, values[0]);
				}
				else {
					attributes.put(param, values);
				}
			}
		}

		serviceContext.setAttributes(attributes);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);
	}

	protected void resetThreadLocals() {
		CompanyThreadLocal.setCompanyId(CompanyConstants.SYSTEM);
		PermissionThreadLocal.setPermissionChecker(null);
		PrincipalThreadLocal.setName(null);
		ServiceContextThreadLocal.popServiceContext();
	}

}