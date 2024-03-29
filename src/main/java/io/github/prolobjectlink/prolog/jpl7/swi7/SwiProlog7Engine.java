/*
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the Prolobjectlink Project nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package io.github.prolobjectlink.prolog.jpl7.swi7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;

import io.github.prolobjectlink.prolog.Licenses;
import io.github.prolobjectlink.prolog.PrologEngine;
import io.github.prolobjectlink.prolog.PrologProvider;
import io.github.prolobjectlink.prolog.jpl7.JplEngine;

/**
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public class SwiProlog7Engine extends JplEngine implements PrologEngine {

	protected SwiProlog7Engine(PrologProvider provider) {
		super(provider);
	}
	
	protected SwiProlog7Engine(PrologProvider provider, String file) {
		super(provider, file);
	}

	public final String getLicense() {
		return Licenses.LGPL_V3;
	}

	public final String getVersion() {
		Term swi = Query.oneSolution("current_prolog_flag(version_data,Swi)").get("Swi");
		return "" + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3) + " (JPL v" + JPL.version_string() + ")";
	}

	public final String getVendor() {
		return "SWI-Prolog";
	}

	public final String getName() {
		return "SWI-Prolog";
	}

	public final List<String> verify() {
		String slash = File.separator;
		List<String> list = new ArrayList<String>();
		String javaHome = System.getProperty("java.home");
		String javaVersion = System.getProperty("java.version");
		String pathSeparator = System.getProperty("path.separator");
		if (runOnWindows()) {
			list.add(javaHome.replace(slash + "jre", slash) + "/jdk" + javaVersion + "/bin" + pathSeparator);
			list.add(javaHome.replace(slash + "jre", slash) + "/jdk" + javaVersion + "/lib/tools.jar" + pathSeparator);
			list.add(
					javaHome.replace(slash + "jre", slash) + "/jdk" + javaVersion + "/jre/lib/rt.jar;" + pathSeparator);
			list.add("C:/Program Files/swipl/lib/jpl.jar" + pathSeparator);
			list.add("C:/Program Files/swipl/bin");
		} else if (runOnOSX()) {
			// TODO environment routes for MacOSX
		} else if (runOnLinux()) {
			list.add("/usr/lib/jvm/java-" + javaVersion + "-openjdk-" + getOSArch() + "/bin" + pathSeparator);
			list.add("/usr/lib/jvm/java-" + javaVersion + "-openjdk-" + getOSArch() + "/lib/tools.jar" + pathSeparator);
			list.add(
					"/usr/lib/jvm/java-" + javaVersion + "-openjdk-" + getOSArch() + "/jre/lib/rt.jar" + pathSeparator);
			list.add("/usr/local/bin/swipl/lib/jpl.jar" + pathSeparator);
			list.add("/usr/local/bin");
		}
		return list;
	}

}
