/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.mobiledevicerules.service.persistence;

/**
 * @author Edward C. Han
 */
public interface MDRRuleGroupFinder {
	public int countByG_N(long groupId, java.lang.String name,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_N(long groupId, java.lang.String[] names,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKeywords(long groupId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByG_N(
		long groupId, java.lang.String name, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByG_N(
		long groupId, java.lang.String name, boolean andOperator, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByG_N(
		long groupId, java.lang.String[] names, boolean andOperator, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByKeywords(
		long groupId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}