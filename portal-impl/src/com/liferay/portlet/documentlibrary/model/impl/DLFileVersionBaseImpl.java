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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;

/**
 * The extended model base implementation for the DLFileVersion service. Represents a row in the &quot;DLFileVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLFileVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFileVersionImpl
 * @see com.liferay.portlet.documentlibrary.model.DLFileVersion
 * @generated
 */
public abstract class DLFileVersionBaseImpl extends DLFileVersionModelImpl
	implements DLFileVersion {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document library file version model instance should use the {@link DLFileVersion} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			DLFileVersionLocalServiceUtil.addDLFileVersion(this);
		}
		else {
			DLFileVersionLocalServiceUtil.updateDLFileVersion(this);
		}
	}

	@Override
	public void updateTreePath(String treePath) throws SystemException {
		DLFileVersion dlFileVersion = this;

		dlFileVersion.setTreePath(treePath);

		DLFileVersionLocalServiceUtil.updateDLFileVersion(dlFileVersion);
	}
}