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

package com.liferay.portlet.blogs.trackback;

import com.liferay.portal.kernel.util.Function;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.util.LinkbackConsumerUtil;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.CallsRealMethods;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author André de Oliveira
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserLocalServiceUtil.class, LinkbackConsumerUtil.class})
public class TrackbackImplTest extends PowerMockito {

	public static void addNewTrackback(
		long messageId, String url, String entryUrl) {

		List<Object> list = Arrays.<Object>asList(messageId, url, entryUrl);

		_linkback = list.toString();
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpLinkbackConsumer();
		setUpPortal();
		setUpThemeDisplay();
		setUpUserLocalService();
	}

	@Test
	public void testAddTrackback() throws Exception {

		// Prepare

		long userId = 42;

		when(
			_userLocalService.getDefaultUserId(Matchers.anyLong())
		).thenReturn(
			userId
		);

		when(
			_themeDisplay.translate("read-more")
		).thenReturn(
			"__read-more__"
		);

		when(
			_portal.getLayoutFullURL(_themeDisplay)
		).thenReturn(
			"__LayoutFullURL__"
		);

		long groupId = 16;

		when(
			_blogsEntry.getGroupId()
		).thenReturn(
			groupId
		);

		long classPK = 142857;

		when(
			_blogsEntry.getEntryId()
		).thenReturn(
			classPK
		);

		when(
			_blogsEntry.getUrlTitle()
		).thenReturn(
			"__UrlTitle__"
		);

		when(
			_trackbackComments.addTrackbackComment(
				Matchers.anyLong(), Matchers.anyLong(), Matchers.anyString(),
				Matchers.anyLong(), Matchers.anyString(), Matchers.anyString(),
				Matchers.anyString(),
				(Function<String, ServiceContext>)Matchers.any()
			)
		).thenReturn(
			99999L
		);

		// Execute

		TrackbackImpl _trackbacks = new TrackbackImpl(_trackbackComments);

		_trackbacks.addTrackback(
			_blogsEntry, _themeDisplay, "__excerpt__", "__url__",
			"__blogName__", "__title__", _serviceContextFunction
		);

		// Verify

		String className = BlogsEntry.class.getName();

		Mockito.verify(
			_trackbackComments
		).addTrackbackComment(
			Matchers.eq(userId), Matchers.eq(groupId), Matchers.eq(className),
			Matchers.eq(classPK), Matchers.eq("__blogName__"),
			Matchers.eq("__title__"),
			Matchers.eq(
				"[...] __excerpt__ [...] [url=__url__]__read-more__[/url]"),
			Matchers.same(_serviceContextFunction)
		);

		Assert.assertEquals(
			"[99999, __url__, __LayoutFullURL__/-/blogs/__UrlTitle__]",
			_linkback);
	}

	protected void setUpLinkbackConsumer() throws Exception {
		mockStatic(LinkbackConsumerUtil.class, new CallsRealMethods());

		replace(
			method(LinkbackConsumerUtil.class, "addNewTrackback")
		).with(
			getClass().getMethod(
				"addNewTrackback", Long.TYPE,String.class, String.class)
		);
	}

	protected void setUpPortal() {
		new PortalUtil().setPortal(_portal);
	}

	protected void setUpThemeDisplay() throws Exception {
		_themeDisplay = PowerMockito.mock(ThemeDisplay.class);
	}

	protected void setUpUserLocalService() {
		mockStatic(UserLocalServiceUtil.class, new CallsRealMethods());

		stub(
			method(UserLocalServiceUtil.class, "getService")
		).toReturn(
			_userLocalService
		);
	}

	private static String _linkback;

	@Mock
	private BlogsEntry _blogsEntry;

	@Mock
	private Portal _portal;

	@Mock
	private Function<String, ServiceContext> _serviceContextFunction;

	private ThemeDisplay _themeDisplay;

	@Mock
	private TrackbackComments _trackbackComments;

	@Mock
	private UserLocalService _userLocalService;

}