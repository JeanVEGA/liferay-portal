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

package com.liferay.portal.tools.seleniumbuilder;

import java.util.Map;

/**
 * @author Michael Hashimoto
 */
public class MacroConverter extends BaseConverter {

	public MacroConverter(
		SeleniumBuilderContext seleniumBuilderContext,
		SeleniumBuilderFileUtil seleniumBuilderFileUtil) {

		super(seleniumBuilderContext, seleniumBuilderFileUtil);
	}

	public void convert(String macroName) throws Exception {
		Map<String, Object> context = getContext();

		context.put("elementsStack", new FreeMarkerStack());
		context.put("forParameterStack", new FreeMarkerStack());
		context.put("ifTypeStack", new FreeMarkerStack());
		context.put("macroNameStack", new FreeMarkerStack());
		context.put("macroName", macroName);
		context.put("variableContextStack", new FreeMarkerStack());

		String content = processTemplate("macro.ftl", context);

		seleniumBuilderFileUtil.writeFile(
			seleniumBuilderContext.getMacroJavaFileName(macroName), content,
			true);
	}

}