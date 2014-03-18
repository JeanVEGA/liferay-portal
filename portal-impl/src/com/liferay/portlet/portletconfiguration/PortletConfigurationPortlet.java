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

package com.liferay.portlet.portletconfiguration;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.language.CompositeResourceBundle;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletConfigFactoryUtil;
import com.liferay.portlet.PortletConfigImpl;
import com.liferay.portlet.StrutsPortlet;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos Sierra Andrés
 */
public class PortletConfigurationPortlet extends StrutsPortlet {

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		if (portletConfig instanceof PortletConfigImpl) {
			ConfigurationPortletPortletConfig confPortletConfig =
				new ConfigurationPortletPortletConfig(
					(PortletConfigImpl)portletConfig);

			super.init(confPortletConfig);
		}
		else {
			super.init(portletConfig);
		}
	}

	private class ConfigurationPortletPortletConfig extends PortletConfigImpl {
		ConfigurationPortletPortletConfig(
			PortletConfigImpl wrappedPortletConfig) {

			super(
				wrappedPortletConfig.getPortlet(),
				wrappedPortletConfig.getPortletContext());
		}

		@Override
		public ResourceBundle getResourceBundle(Locale locale) {
			PortletRequest portletRequest = _portletRequestThreadLocal.get();

			String portletResource = ParamUtil.getString(
				portletRequest, "portletResource");

			long companyId = PortalUtil.getCompanyId(portletRequest);

			try {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					companyId, portletResource);

				HttpServletRequest httpServletRequest =
					PortalUtil.getHttpServletRequest(portletRequest);

				PortletConfig portletConfig = PortletConfigFactoryUtil.create(
					portlet, httpServletRequest.getServletContext());

				return new CompositeResourceBundle(
					super.getResourceBundle(locale),
					portletConfig.getResourceBundle(locale));
			}
			catch (SystemException e) {
				e.printStackTrace();
			}

			return super.getResourceBundle(locale);
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(actionRequest);

		actionRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void processEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(eventRequest);

		eventRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.processEvent(eventRequest, eventResponse);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(renderRequest);

		renderRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(resourceRequest);

		resourceRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.serveResource(resourceRequest, resourceResponse);
	}

	private ThreadLocal<PortletRequest> _portletRequestThreadLocal =
		new AutoResetThreadLocal<PortletRequest>("portletRequest");

}