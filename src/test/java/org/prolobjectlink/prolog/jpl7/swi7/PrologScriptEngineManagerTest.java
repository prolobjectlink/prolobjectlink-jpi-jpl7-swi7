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

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class PrologScriptEngineManagerTest extends PrologBaseTest {

	@Test
	public void test() throws ScriptException, FileNotFoundException {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName(provider.getName());
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));

		engine = manager.getEngineByName("Prolog");
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));

		engine = manager.getEngineByName("prolog");
		assertEquals(true, engine.eval("?- X is 5+3."));
		assertEquals(8, engine.get("X"));

		assertEquals(true, engine.eval(new FileReader("family.pl")));
		assertEquals(true, engine.eval(new FileReader("company.pl")));
		assertEquals(true, engine.eval(new FileReader("zoo.pl")));

		assertEquals(true, engine.eval("?- parent( Parent, Child)"));
		assertEquals("pam", engine.get("Parent"));
		assertEquals("bob", engine.get("Child"));

		assertEquals(true,
				engine.eval("?- employee(Name,Dpto,Scale),department(Dpto,DepartmentName),salary(Scale,Money)"));
		assertEquals("mcardon", engine.get("Name"));
		assertEquals(1, engine.get("Dpto"));
		assertEquals(5, engine.get("Scale"));
		assertEquals("board", engine.get("DepartmentName"));
		assertEquals(3000, engine.get("Money"));

		assertEquals(true, engine.eval("?- dark(Animal),big(Animal)"));
		assertEquals("bear", engine.get("Animal"));

	}

}
