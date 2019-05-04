/*-
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.prolog.jpl7.swi7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

import org.junit.Test;

public class PrologScriptEngineFactoryTest extends PrologBaseTest {

	private ScriptEngineFactory sef = provider.getScriptFactory();

	@Test
	public void testGetEngineName() {
		assertEquals(provider.newEngine().getName(), sef.getEngineName());
	}

	@Test
	public void testGetEngineVersion() {
		assertEquals(provider.newEngine().getVersion(), sef.getEngineVersion());
	}

	@Test
	public void testGetExtensions() {
		assertEquals(Arrays.asList("pro", "pl"), sef.getExtensions());
	}

	@Test
	public void testGetMimeTypes() {
		assertEquals(Arrays.asList("text/plain"), sef.getMimeTypes());
	}

	@Test
	public void testGetNames() {
		assertEquals(Arrays.asList(provider.newEngine().getName(), "Prolog", "prolog"), sef.getNames());
	}

	@Test
	public void testGetLanguageName() {
		assertEquals("Prolog", sef.getLanguageName());
	}

	@Test
	public void testGetLanguageVersion() {
		assertEquals(provider.newEngine().getVersion(), sef.getLanguageVersion());
	}

	@Test
	public void testGetParameter() {
		assertEquals(provider.newEngine().getName(), sef.getParameter(ScriptEngine.NAME));
		assertEquals(provider.newEngine().getName(), sef.getParameter(ScriptEngine.ENGINE));
		assertEquals(provider.newEngine().getVersion(), sef.getParameter(ScriptEngine.ENGINE_VERSION));
		assertEquals("Prolog", sef.getParameter(ScriptEngine.LANGUAGE));
		assertEquals(provider.newEngine().getVersion(), sef.getParameter(ScriptEngine.LANGUAGE_VERSION));
	}

	@Test
	public void testGetOutputStatement() {
		assertEquals("write('Hello World')", sef.getOutputStatement("Hello World"));
	}

	@Test
	public void testGetProgram() {
		assertEquals("black(cat).\n"

				+ "gray(elephant).\n"

				+ "big(bear).\n"

				+ "big(elephant).\n"

				+ "brown(bear).\n"

				+ "dark(Z) :- black(Z).\n"

				+ "dark(Z) :- brown(Z).\n"

				+ "small(cat).",
				sef.getProgram("black(cat)", "gray(elephant)", "big(bear)", "big(elephant)", "brown(bear)",
						"dark(Z) :- black(Z)", "dark(Z) :- brown(Z)", "small(cat)"));
	}

	@Test
	public void testGetScriptEngine() {
		assertNotNull(sef.getScriptEngine());
	}

	@Test
	public void testGetMethodCallSyntax() {
		assertEquals("jpl_call(OBJ1, equals, [OBJ2], Result).", sef.getMethodCallSyntax("OBJ1", "equals", "OBJ2"));
	}

}
