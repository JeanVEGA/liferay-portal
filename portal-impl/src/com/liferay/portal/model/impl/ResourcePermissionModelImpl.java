/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.ResourcePermissionModel;
import com.liferay.portal.model.ResourcePermissionSoap;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the ResourcePermission service. Represents a row in the &quot;ResourcePermission&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.ResourcePermissionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ResourcePermissionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermissionImpl
 * @see com.liferay.portal.model.ResourcePermission
 * @see com.liferay.portal.model.ResourcePermissionModel
 * @generated
 */
@JSON(strict = true)
public class ResourcePermissionModelImpl extends BaseModelImpl<ResourcePermission>
	implements ResourcePermissionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a resource permission model instance should use the {@link com.liferay.portal.model.ResourcePermission} interface instead.
	 */
	public static final String TABLE_NAME = "ResourcePermission";
	public static final Object[][] TABLE_COLUMNS = {
			{ "resourcePermissionId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "scope", Types.INTEGER },
			{ "primKey", Types.VARCHAR },
			{ "roleId", Types.BIGINT },
			{ "ownerId", Types.BIGINT },
			{ "actionIds", Types.BIGINT },
			{ "mvccVersion", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table ResourcePermission (resourcePermissionId LONG not null primary key,companyId LONG,name VARCHAR(255) null,scope INTEGER,primKey VARCHAR(255) null,roleId LONG,ownerId LONG,actionIds LONG,mvccVersion LONG default 0)";
	public static final String TABLE_SQL_DROP = "drop table ResourcePermission";
	public static final String ORDER_BY_JPQL = " ORDER BY resourcePermission.resourcePermissionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ResourcePermission.resourcePermissionId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.ResourcePermission"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.ResourcePermission"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.model.ResourcePermission"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static long NAME_COLUMN_BITMASK = 2L;
	public static long PRIMKEY_COLUMN_BITMASK = 4L;
	public static long ROLEID_COLUMN_BITMASK = 8L;
	public static long SCOPE_COLUMN_BITMASK = 16L;
	public static long RESOURCEPERMISSIONID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ResourcePermission toModel(ResourcePermissionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ResourcePermission model = new ResourcePermissionImpl();

		model.setResourcePermissionId(soapModel.getResourcePermissionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setName(soapModel.getName());
		model.setScope(soapModel.getScope());
		model.setPrimKey(soapModel.getPrimKey());
		model.setRoleId(soapModel.getRoleId());
		model.setOwnerId(soapModel.getOwnerId());
		model.setActionIds(soapModel.getActionIds());
		model.setMvccVersion(soapModel.getMvccVersion());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ResourcePermission> toModels(
		ResourcePermissionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ResourcePermission> models = new ArrayList<ResourcePermission>(soapModels.length);

		for (ResourcePermissionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.ResourcePermission"));

	public ResourcePermissionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _resourcePermissionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setResourcePermissionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourcePermissionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ResourcePermission.class;
	}

	@Override
	public String getModelClassName() {
		return ResourcePermission.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("resourcePermissionId", getResourcePermissionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("name", getName());
		attributes.put("scope", getScope());
		attributes.put("primKey", getPrimKey());
		attributes.put("roleId", getRoleId());
		attributes.put("ownerId", getOwnerId());
		attributes.put("actionIds", getActionIds());
		attributes.put("mvccVersion", getMvccVersion());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long resourcePermissionId = (Long)attributes.get("resourcePermissionId");

		if (resourcePermissionId != null) {
			setResourcePermissionId(resourcePermissionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer scope = (Integer)attributes.get("scope");

		if (scope != null) {
			setScope(scope);
		}

		String primKey = (String)attributes.get("primKey");

		if (primKey != null) {
			setPrimKey(primKey);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}

		Long actionIds = (Long)attributes.get("actionIds");

		if (actionIds != null) {
			setActionIds(actionIds);
		}

		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}
	}

	@JSON
	@Override
	public long getResourcePermissionId() {
		return _resourcePermissionId;
	}

	@Override
	public void setResourcePermissionId(long resourcePermissionId) {
		_resourcePermissionId = resourcePermissionId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public int getScope() {
		return _scope;
	}

	@Override
	public void setScope(int scope) {
		_columnBitmask |= SCOPE_COLUMN_BITMASK;

		if (!_setOriginalScope) {
			_setOriginalScope = true;

			_originalScope = _scope;
		}

		_scope = scope;
	}

	public int getOriginalScope() {
		return _originalScope;
	}

	@JSON
	@Override
	public String getPrimKey() {
		if (_primKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _primKey;
		}
	}

	@Override
	public void setPrimKey(String primKey) {
		_columnBitmask |= PRIMKEY_COLUMN_BITMASK;

		if (_originalPrimKey == null) {
			_originalPrimKey = _primKey;
		}

		_primKey = primKey;
	}

	public String getOriginalPrimKey() {
		return GetterUtil.getString(_originalPrimKey);
	}

	@JSON
	@Override
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		_columnBitmask |= ROLEID_COLUMN_BITMASK;

		if (!_setOriginalRoleId) {
			_setOriginalRoleId = true;

			_originalRoleId = _roleId;
		}

		_roleId = roleId;
	}

	public long getOriginalRoleId() {
		return _originalRoleId;
	}

	@JSON
	@Override
	public long getOwnerId() {
		return _ownerId;
	}

	@Override
	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
	}

	@JSON
	@Override
	public long getActionIds() {
		return _actionIds;
	}

	@Override
	public void setActionIds(long actionIds) {
		_actionIds = actionIds;
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ResourcePermission.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ResourcePermission toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ResourcePermission)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ResourcePermissionImpl resourcePermissionImpl = new ResourcePermissionImpl();

		resourcePermissionImpl.setResourcePermissionId(getResourcePermissionId());
		resourcePermissionImpl.setCompanyId(getCompanyId());
		resourcePermissionImpl.setName(getName());
		resourcePermissionImpl.setScope(getScope());
		resourcePermissionImpl.setPrimKey(getPrimKey());
		resourcePermissionImpl.setRoleId(getRoleId());
		resourcePermissionImpl.setOwnerId(getOwnerId());
		resourcePermissionImpl.setActionIds(getActionIds());
		resourcePermissionImpl.setMvccVersion(getMvccVersion());

		resourcePermissionImpl.resetOriginalValues();

		return resourcePermissionImpl;
	}

	@Override
	public int compareTo(ResourcePermission resourcePermission) {
		long primaryKey = resourcePermission.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResourcePermission)) {
			return false;
		}

		ResourcePermission resourcePermission = (ResourcePermission)obj;

		long primaryKey = resourcePermission.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ResourcePermissionModelImpl resourcePermissionModelImpl = this;

		resourcePermissionModelImpl._originalCompanyId = resourcePermissionModelImpl._companyId;

		resourcePermissionModelImpl._setOriginalCompanyId = false;

		resourcePermissionModelImpl._originalName = resourcePermissionModelImpl._name;

		resourcePermissionModelImpl._originalScope = resourcePermissionModelImpl._scope;

		resourcePermissionModelImpl._setOriginalScope = false;

		resourcePermissionModelImpl._originalPrimKey = resourcePermissionModelImpl._primKey;

		resourcePermissionModelImpl._originalRoleId = resourcePermissionModelImpl._roleId;

		resourcePermissionModelImpl._setOriginalRoleId = false;

		resourcePermissionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ResourcePermission> toCacheModel() {
		ResourcePermissionCacheModel resourcePermissionCacheModel = new ResourcePermissionCacheModel();

		resourcePermissionCacheModel.resourcePermissionId = getResourcePermissionId();

		resourcePermissionCacheModel.companyId = getCompanyId();

		resourcePermissionCacheModel.name = getName();

		String name = resourcePermissionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			resourcePermissionCacheModel.name = null;
		}

		resourcePermissionCacheModel.scope = getScope();

		resourcePermissionCacheModel.primKey = getPrimKey();

		String primKey = resourcePermissionCacheModel.primKey;

		if ((primKey != null) && (primKey.length() == 0)) {
			resourcePermissionCacheModel.primKey = null;
		}

		resourcePermissionCacheModel.roleId = getRoleId();

		resourcePermissionCacheModel.ownerId = getOwnerId();

		resourcePermissionCacheModel.actionIds = getActionIds();

		resourcePermissionCacheModel.mvccVersion = getMvccVersion();

		return resourcePermissionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{resourcePermissionId=");
		sb.append(getResourcePermissionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", scope=");
		sb.append(getScope());
		sb.append(", primKey=");
		sb.append(getPrimKey());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append(", ownerId=");
		sb.append(getOwnerId());
		sb.append(", actionIds=");
		sb.append(getActionIds());
		sb.append(", mvccVersion=");
		sb.append(getMvccVersion());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.ResourcePermission");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>resourcePermissionId</column-name><column-value><![CDATA[");
		sb.append(getResourcePermissionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scope</column-name><column-value><![CDATA[");
		sb.append(getScope());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>primKey</column-name><column-value><![CDATA[");
		sb.append(getPrimKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ownerId</column-name><column-value><![CDATA[");
		sb.append(getOwnerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actionIds</column-name><column-value><![CDATA[");
		sb.append(getActionIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mvccVersion</column-name><column-value><![CDATA[");
		sb.append(getMvccVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = ResourcePermission.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			ResourcePermission.class
		};
	private long _resourcePermissionId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private String _name;
	private String _originalName;
	private int _scope;
	private int _originalScope;
	private boolean _setOriginalScope;
	private String _primKey;
	private String _originalPrimKey;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private long _ownerId;
	private long _actionIds;
	private long _mvccVersion;
	private long _columnBitmask;
	private ResourcePermission _escapedModel;
}