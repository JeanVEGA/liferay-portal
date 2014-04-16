<%--
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
--%>

<%@ include file="/html/taglib/ui/input_asset_links/init.jsp" %>

<liferay-ui:icon-menu
	cssClass="select-existing-selector"
	icon='<%= themeDisplay.getPathThemeImages() + "/common/search.png" %>'
	id='<%= inputAssetLinksDisplayContext.getRandomNamespace() + "inputAssetLinks" %>'
	message="select"
	showWhenSingleIcon="<%= true %>"
>

	<%
	for (Map<String, Object> entry : inputAssetLinksDisplayContext.getSelectorEntries()) {
	%>

		<liferay-ui:icon
			cssClass="asset-selector"
			data='<%= (Map<String, Object>)entry.get("data") %>'
			id='<%= (String)entry.get("id") %>'
			message='<%= (String)entry.get("message") %>'
			src='<%= (String)entry.get("src") %>'
			url="javascript:;"
		/>

	<%
	}
	%>

</liferay-ui:icon-menu>

<br />

<div class="separator"><!-- --></div>

<liferay-util:buffer var="removeLinkIcon">
	<liferay-ui:icon
		image="unlink"
		label="<%= true %>"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	headerNames="type,title,scope,null"
>
	<liferay-ui:search-container-results
		results="<%= inputAssetLinksDisplayContext.getAssetLinks() %>"
		total="<%= inputAssetLinksDisplayContext.getAssetLinksCount() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.asset.model.AssetLink"
		keyProperty="entryId2"
		modelVar="assetLink"
	>

		<%
		AssetEntry assetLinkEntry = inputAssetLinksDisplayContext.getAssetLinkEntry(assetLink);
		%>

		<liferay-ui:search-container-column-text
			name="type"
			value="<%= inputAssetLinksDisplayContext.getAssetType(assetLinkEntry) %>"
		/>

		<liferay-ui:search-container-column-text
			name="title"
			value="<%= HtmlUtil.escape(assetLinkEntry.getTitle(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			name="scope"
			value="<%= HtmlUtil.escape(inputAssetLinksDisplayContext.getGroupDescriptiveName(assetLinkEntry)) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="modify-link" data-rowId="<%= assetLinkEntry.getEntryId() %>" href="javascript:;"><%= removeLinkIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<aui:input name="assetLinkEntryIds" type="hidden" />

<aui:script use="aui-base,escape,liferay-search-container">
	A.getBody().delegate(
		'click',
		function(event) {
			event.preventDefault();

			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						modal: true
					},
					eventName: '<%= inputAssetLinksDisplayContext.getEventName() %>',
					id: '<%= inputAssetLinksDisplayContext.getEventName() %>' + event.currentTarget.attr('id'),
					title: event.currentTarget.attr('data-title'),
					uri: event.currentTarget.attr('data-href')
				},
				function(event) {
					var searchContainerName = '<portlet:namespace/>assetLinksSearchContainer';

					searchContainer = Liferay.SearchContainer.get(searchContainerName);

					var entryLink = '<a class="modify-link" data-rowId="' + event.assetentryid + '" href="javascript:;"><%= UnicodeFormatter.toString(removeLinkIcon) %></a>';

					searchContainer.addRow([event.assettype, A.Escape.html(event.assettitle), A.Escape.html(event.groupdescriptivename), entryLink], event.assetentryid);

					searchContainer.updateDataStore();
				}
			);
		},
		'.asset-selector a'
	);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace/>assetLinksSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);
</aui:script>