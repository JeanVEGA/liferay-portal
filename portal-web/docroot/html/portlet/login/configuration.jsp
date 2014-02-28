<%--
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
--%>

<%@ include file="/html/portlet/login/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "general");
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String emailFromName = ParamUtil.getString(request, "preferences--emailFromName--", LoginUtil.getEmailFromName(portletPreferences, company.getCompanyId()));
String emailFromAddress = ParamUtil.getString(request, "preferences--emailFromAddress--", LoginUtil.getEmailFromAddress(portletPreferences, company.getCompanyId()));

String emailParam = "emailPasswordSent";
String defaultEmailSubject = StringPool.BLANK;
String defaultEmailBody = StringPool.BLANK;

if (tabs2.equals("password-reset-notification")) {
	emailParam = "emailPasswordReset";
	defaultEmailSubject = ContentUtil.get(PropsValues.ADMIN_EMAIL_PASSWORD_RESET_SUBJECT);
	defaultEmailBody = ContentUtil.get(PropsValues.ADMIN_EMAIL_PASSWORD_RESET_BODY);
}
else if (tabs2.equals("password-changed-notification")) {
	defaultEmailSubject = ContentUtil.get(PropsValues.ADMIN_EMAIL_PASSWORD_SENT_SUBJECT);
	defaultEmailBody = ContentUtil.get(PropsValues.ADMIN_EMAIL_PASSWORD_SENT_BODY);
}

String currentLanguageId = LanguageUtil.getLanguageId(request);

String emailSubjectParam = emailParam + "Subject_" + currentLanguageId;
String emailBodyParam = emailParam + "Body_" + currentLanguageId;

String emailSubject = PrefsParamUtil.getString(portletPreferences, request, emailSubjectParam, defaultEmailSubject);
String emailBody = PrefsParamUtil.getString(portletPreferences, request, emailBodyParam, defaultEmailBody);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<liferay-ui:tabs
		names="general,email-from,password-changed-notification,password-reset-notification"
		refresh="<%= false %>"
	>

		<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
		<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />

		<liferay-ui:section>
			<aui:fieldset>
				<aui:select label="authentication-type" name="preferences--authType--" value="<%= authType %>">
					<aui:option label="default" value="" />
					<aui:option label="by-email-address" value="<%= CompanyConstants.AUTH_TYPE_EA %>" />
					<aui:option label="by-screen-name" value="<%= CompanyConstants.AUTH_TYPE_SN %>" />
					<aui:option label="by-user-id" value="<%= CompanyConstants.AUTH_TYPE_ID %>" />
				</aui:select>
			</aui:fieldset>
		</liferay-ui:section>

		<liferay-ui:section>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" value="<%= emailFromAddress %>" />
			</aui:fieldset>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="alert alert-info">
				<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
			</div>

			<aui:fieldset>
				<aui:select label="language" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this);" %>'>

					<%
					Locale[] locales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());

					for (int i = 0; i < locales.length; i++) {
						String style = StringPool.BLANK;

						if (Validator.isNotNull(portletPreferences.getValue(emailParam + "Subject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
							Validator.isNotNull(portletPreferences.getValue(emailParam + "Body_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

							style = "font-weight: bold;";
						}
					%>

						<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= style %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "preferences--" + emailSubjectParam + "--" %>' value="<%= emailSubject %>" />

				<aui:field-wrapper label="body">
					<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" />

					<aui:input name='<%= "preferences--" + emailBodyParam + "--" %>' type="hidden" />
				</aui:field-wrapper>
			</aui:fieldset>

			<aui:fieldset cssClass="definition-of-terms">
				<legend>
					<liferay-ui:message key="definition-of-terms" />
				</legend>

				<dl>
					<dt>
						[$FROM_ADDRESS$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromAddress) %>
					</dd>
					<dt>
						[$FROM_NAME$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromName) %>
					</dd>

					<dt>
						[$PORTAL_URL$]
					</dt>
					<dd>
						<%= company.getVirtualHostname() %>
					</dd>
					<dt>
						[$REMOTE_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-browser's-remote-address" />
					</dd>
					<dt>
						[$REMOTE_HOST$]
					</dt>
					<dd>
						<liferay-ui:message key="the-browser's-remote-host" />
					</dd>

					<dt>
						[$TO_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-address-of-the-email-recipient" />
					</dd>
					<dt>
						[$TO_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-name-of-the-email-recipient" />
					</dd>

					<dt>
						[$USER_AGENT$]
					</dt>
					<dd>
						<liferay-ui:message key="the-browser's-user-agent" />
					</dd>

					<dt>
						[$USER_ID$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-id" />
					</dd>

					<dt>
						[$USER_SCREENNAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-screen-name" />
					</dd>
				</dl>
			</aui:fieldset>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="alert alert-info">
				<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
			</div>

			<aui:fieldset>
				<aui:select label="language" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this);" %>'>

					<%
					Locale[] locales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());

					for (int i = 0; i < locales.length; i++) {
						String style = StringPool.BLANK;

						if (Validator.isNotNull(portletPreferences.getValue(emailParam + "Subject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
							Validator.isNotNull(portletPreferences.getValue(emailParam + "Body_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

							style = "font-weight: bold;";
						}
					%>

						<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" style="<%= style %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "preferences--" + emailSubjectParam + "--" %>' value="<%= emailSubject %>" />

				<aui:field-wrapper label="body">
					<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" />

					<aui:input name='<%= "preferences--" + emailBodyParam + "--" %>' type="hidden" />
				</aui:field-wrapper>
			</aui:fieldset>

			<aui:fieldset cssClass="definition-of-terms">
				<legend>
					<liferay-ui:message key="definition-of-terms" />
				</legend>

				<dl>
					<dt>
						[$FROM_ADDRESS$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromAddress) %>
					</dd>
					<dt>
						[$FROM_NAME$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromName) %>
					</dd>

					<dt>
						[$PASSWORD_RESET_URL$]
					</dt>
					<dd>
						<liferay-ui:message key="the-password-reset-url" />
					</dd>

					<dt>
						[$PORTAL_URL$]
					</dt>
					<dd>
						<%= company.getVirtualHostname() %>
					</dd>
					<dt>
						[$REMOTE_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-browser's-remote-address" />
					</dd>
					<dt>
						[$REMOTE_HOST$]
					</dt>
					<dd>
						<liferay-ui:message key="the-browser's-remote-host" />
					</dd>

					<dt>
						[$TO_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-address-of-the-email-recipient" />
					</dd>
					<dt>
						[$TO_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-name-of-the-email-recipient" />
					</dd>

					<dt>
						[$USER_AGENT$]
					</dt>
					<dd>
						<liferay-ui:message key="the-browser's-user-agent" />
					</dd>

					<dt>
						[$USER_ID$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-id" />
					</dd>

					<dt>
						[$USER_PASSWORD$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-password" />
					</dd>

					<dt>
						[$USER_SCREENNAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-screen-name" />
					</dd>
				</dl>
			</aui:fieldset>
		</liferay-ui:section>
	</liferay-ui:tabs>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(emailBody) %>";
	}

	function <portlet:namespace />saveConfiguration() {
		<c:if test='<%= tabs2.endsWith("-notification") %>'>
			document.<portlet:namespace />fm.<portlet:namespace /><%= emailBodyParam %>.value = window.<portlet:namespace />editor.getHTML();
		</c:if>

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateLanguage() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.login.configuration.jsp";
%>