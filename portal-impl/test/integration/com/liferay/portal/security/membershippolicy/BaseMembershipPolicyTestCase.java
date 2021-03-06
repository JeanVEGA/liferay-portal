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

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.UserTestUtil;

import org.junit.After;
import org.junit.Before;

/**
 * @author Roberto Díaz
 */
public abstract class BaseMembershipPolicyTestCase {

	public static long[] getUserIds() {
		return _userIds;
	}

	public static boolean isPropagateMembership() {
		return _propagateMembership;
	}

	public static boolean isPropagateRoles() {
		return _propagateRoles;
	}

	public static boolean isVerify() {
		return _verify;
	}

	public static void setPropagateMembership(boolean propagateMembership) {
		_propagateMembership = propagateMembership;
	}

	public static void setPropagateRoles(boolean propagateRoles) {
		_propagateRoles = propagateRoles;
	}

	public static void setVerify(boolean verify) {
		_verify = verify;
	}

	@Before
	public void setUp() throws Exception {
		FinderCacheUtil.clearCache();

		group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		group = null;

		_propagateMembership = false;
		_propagateRoles = false;
		_userIds = new long[2];
		_verify = false;
	}

	protected long[] addUsers() throws Exception {
		User user1 = UserTestUtil.addUser(
			RandomTestUtil.randomString(), group.getGroupId());

		_userIds[0] = user1.getUserId();

		User user2 = UserTestUtil.addUser(
			RandomTestUtil.randomString(), group.getGroupId());

		_userIds[1] = user2.getUserId();

		return _userIds;
	}

	protected Group group;

	private static boolean _propagateMembership;
	private static boolean _propagateRoles;
	private static long[] _userIds = new long[2];
	private static boolean _verify;

}