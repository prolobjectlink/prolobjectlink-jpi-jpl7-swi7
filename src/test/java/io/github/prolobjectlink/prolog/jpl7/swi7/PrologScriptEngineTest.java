/*-
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.StringReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import org.junit.Test;

public class PrologScriptEngineTest extends PrologBaseTest {

	private final Bindings bindings = new SimpleBindings();
	private final ScriptEngine engine = manager.getEngineByName(provider.getName());

	@Test
	public void testCreateBindings() throws ScriptException {
		Bindings b = engine.createBindings();
		b.put("X", 42.0);
		assertEquals(true, engine.eval("?- X == 42.0.", b));
	}

	@Test
	public void testGetFactory() {
		assertEquals(manager.getEngineByName(provider.getName()).getFactory(), engine.getFactory());
	}

	@Test
	public void testSetContextScriptContext() {
		ScriptContext old = engine.getContext();
		engine.setContext(new SimpleScriptContext());
		assertNotSame(old, engine.getContext());
	}

	@Test
	public void testGetContext() throws ScriptException {
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));
		assertNotNull(engine.getContext());
	}

	@Test
	public void testGetBindingsInt() throws ScriptException {
		assertEquals(0, engine.getBindings(ScriptContext.ENGINE_SCOPE).size());
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));
		assertEquals(1, engine.getBindings(ScriptContext.ENGINE_SCOPE).size());
	}

	@Test
	public void testSetBindingsBindingsInt() throws ScriptException {
		bindings.put("X", 42.0);
		engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
		assertEquals(true, engine.eval("?- X == 42.0.", bindings));
	}

	@Test
	public void testPutStringObject() throws ScriptException {
		engine.put("X", 42.0);
		assertEquals(true, engine.eval("?- X == 42.0."));
	}

	@Test
	public void testGetString() throws ScriptException {
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));
	}

	@Test
	public void testEvalStringScriptContext() throws ScriptException {
		assertEquals(true, engine.eval("?- X is 5+3.", engine.getContext()));
		assertEquals(8, engine.get("X"));
	}

	@Test
	public void testEvalReaderScriptContext() throws ScriptException {
		assertEquals(true, engine.eval(new StringReader("?- X is 5+3."), engine.getContext()));
		assertEquals(8, engine.get("X"));
	}

	@Test
	public void testEvalReaderBindings() throws ScriptException {
		assertEquals(true, engine.eval(new StringReader("?- X is 5+3."), bindings));
		assertEquals(8, engine.get("X"));
	}

	@Test
	public void testEvalStringBindings() throws ScriptException {
		assertEquals(0, engine.getBindings(ScriptContext.ENGINE_SCOPE).size());
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));
		assertEquals(1, engine.getBindings(ScriptContext.ENGINE_SCOPE).size());
	}

	@Test
	public void testEvalReader() throws ScriptException {
		assertEquals(true, engine.eval(new StringReader("?- X is 5+3.")));
		assertEquals(8, engine.get("X"));
	}

	@Test
	public void testEvalString() throws ScriptException {
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));
	}

	@Test
	public void testGetScriptContextBindings() throws ScriptException {
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));
		assertNotNull(engine.getContext());
	}

}
