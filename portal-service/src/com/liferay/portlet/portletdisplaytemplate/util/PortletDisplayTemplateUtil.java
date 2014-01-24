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

package com.liferay.portlet.portletdisplaytemplate.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Garcia
 */
public class PortletDisplayTemplateUtil {

	/**
	 * Returns the d d m template containing the portlet display template
	 * matching the group and the display style stored in the portlet
	 * configuration.
	 *
	 * @param  groupId the primary key of the group
	 * @param  displayStyle the display style stored in the portlet
	 *         configuration
	 * @return the d d m template containing the portlet display template
	 *         matching the group and the display style stored in the portlet
	 *         configuration
	 */
	public static DDMTemplate fetchDDMTemplate(
		long groupId, String displayStyle) {

		return getPortletDisplayTemplate().fetchDDMTemplate(
			groupId, displayStyle);
	}

	/**
	 * Returns the primary key of the group which the d d m templates containing
	 * the portlet display templates actually belong to.
	 *
	 * @param  groupId the primary key of the group where the portlet display
	 *         templates are originally searched for
	 * @return the primary key of the group which the d d m templates containing
	 *         the portlet display templates actually belong to
	 */
	public static long getDDMTemplateGroupId(long groupId) {
		return getPortletDisplayTemplate().getDDMTemplateGroupId(groupId);
	}

	/**
	 * Returns the UUID of the d d m template containing the portlet display
	 * template from the given display style stored in the portlet configuration
	 *
	 * @param  displayStyle the display style stored in the portlet
	 *         configuration
	 * @return the UUID of the d d m template containing the portlet display
	 *         template from the given display style stored in the portlet
	 *         configuration
	 */
	public static String getDDMTemplateUuid(String displayStyle) {
		return getPortletDisplayTemplate().getDDMTemplateUuid(displayStyle);
	}

	public static PortletDisplayTemplate getPortletDisplayTemplate() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletDisplayTemplate.class);

		return _portletDisplayTemplate;
	}

	/**
	 * Returns the primary key of the d d m template containing the portlet
	 * display template matching the group and the display style stored in the
	 * portlet configuration.
	 *
	 * @param  groupId the primary key of the group
	 * @param  displayStyle the display style stored in the portlet
	 *         configuration
	 * @return Returns the primary key of the d d m template containing the
	 *         portlet display template matching the group and the display style
	 *         stored in the portlet configuration
	 */
	public static long getPortletDisplayTemplateDDMTemplateId(
		long groupId, String displayStyle) {

		return
			getPortletDisplayTemplate().getPortletDisplayTemplateDDMTemplateId(
				groupId, displayStyle);
	}

	/**
	 * Returns a list with the template handlers that are portlet display
	 * template handlers.
	 *
	 * @return Returns a list with the template handlers that are portlet
	 *         display template handlers
	 */
	public static List<TemplateHandler> getPortletDisplayTemplateHandlers() {
		return getPortletDisplayTemplate().getPortletDisplayTemplateHandlers();
	}

	/**
	 * Returns the map of variable groups that portlet display templates expose
	 * commonly as hints to the palette of the template editor.
	 *
	 * @param  language the language of the template
	 * @return the map of variable groups that portlet display templates expose
	 *         commonly as hints to the palette of the template editor
	 * @see    TemplateHandler#getTemplateVariableGroups(long, String, Locale)
	 */
	public static Map<String, TemplateVariableGroup>
		getTemplateVariableGroups(String language) {

		return getPortletDisplayTemplate().getTemplateVariableGroups(language);
	}

	/**
	 * Returns the result of rendering the d d m template in the given page
	 * context and list of entries.
	 *
	 * @param  pageContext the page context in which the template is rendered.
	 *         For example, the context of the JSP view
	 * @param  ddmTemplateId the primary key of the d d m template
	 * @param  entries the list of entries that are rendered in the template
	 * @return the result of rendering the d d m template in the given page
	 *         context and list of entries
	 * @throws Exception if an exception occurred rendering the template
	 */
	public static String renderDDMTemplate(
			PageContext pageContext, long ddmTemplateId, List<?> entries)
		throws Exception {

		return getPortletDisplayTemplate().renderDDMTemplate(
			pageContext, ddmTemplateId, entries);
	}

	/**
	 * Returns the result of rendering the d d m template in the given page
	 * context, list of entries and template context.
	 *
	 * @param  pageContext the page context in which the template is rendered.
	 *         For example, the context of the JSP view
	 * @param  ddmTemplateId the primary key of the d d m template
	 * @param  entries the list of entries that are rendered in the template
	 * @param  contextObjects the map of objects defining the context in which
	 *         the template is rendered
	 * @return Returns the result of rendering the d d m template in the given
	 *         page context, list of entries and template context
	 * @throws Exception if an exception occurred rendering the template
	 */
	public static String renderDDMTemplate(
			PageContext pageContext, long ddmTemplateId, List<?> entries,
			Map<String, Object> contextObjects)
		throws Exception {

		return getPortletDisplayTemplate().renderDDMTemplate(
			pageContext, ddmTemplateId, entries, contextObjects);
	}

	public void setPortletDisplayTemplate(
		PortletDisplayTemplate portletDisplayTemplate) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletDisplayTemplate = portletDisplayTemplate;
	}

	private static PortletDisplayTemplate _portletDisplayTemplate;

}