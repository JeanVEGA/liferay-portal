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

package com.liferay.portal.tools.sourceformatter;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author André de Oliveira
 */
public abstract class ImportsFormatter {

	public String format(String imports) throws IOException {

		if (imports.contains("/*") || imports.contains("*/") ||
			imports.contains("//")) {

			return imports + "\n";
		}

		List<ImportPackage> importPackages = new ArrayList<ImportPackage>();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(imports));

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			ImportPackage importPackage = createImportPackage(line);

			if ((importPackage != null) &&
				!importPackages.contains(importPackage)) {

				importPackages.add(importPackage);
			}
		}

		importPackages = ListUtil.sort(importPackages);

		StringBundler sb = new StringBundler(3 * importPackages.size());

		ImportPackage temp = null;

		for (ImportPackage importPackage : importPackages) {
			if (!importPackage.isGroupedWith(temp)) {
				sb.append("\n");
			}

			sb.append(importPackage.getLine());
			sb.append("\n");

			temp = importPackage;
		}

		return sb.toString();
	}

	protected abstract ImportPackage createImportPackage(String line);

}