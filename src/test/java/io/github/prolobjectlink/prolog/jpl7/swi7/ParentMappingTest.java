/*-
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2020 - 2021 Prolobjectlink Project
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
package io.github.prolobjectlink.prolog.jpl7.swi7;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.prolobjectlink.prolog.PrologEngine;
import io.github.prolobjectlink.prolog.PrologTerm;

public class ParentMappingTest extends PrologBaseTest {

	private ParentMapping parentMapping = new ParentMapping();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFromTerm() {
		PrologTerm term = provider.newStructure("parent", provider.newAtom("tom"), provider.newAtom("bob"));
		assertEquals(new Parent("tom", "bob"), parentMapping.fromTerm(provider, term));
	}

	@Test
	public void testToTermPrologProviderParent() {
		assertEquals(provider.newStructure("parent", "tom", "bob"), parentMapping.toTerm(provider, new Parent("tom", "bob")));
	}

	@Test
	public void testToTermPrologProvider() {
		PrologTerm name = provider.newVariable("Name", 0);
		PrologTerm child = provider.newVariable("Child", 1);
		assertEquals(provider.newStructure("parent", name, child), parentMapping.toTerm(provider));
	}

	@Test
	public void testGetType() {
		assertEquals(Parent.class, parentMapping.getType());
	}

	@Test
	public void testQueryOneClass() {

		PrologEngine engine = provider.newEngine();
		engine.register(parentMapping);
		engine.consult("family.pl");

		famillySolutionMap.put("Name", pam);
		famillySolutionMap.put("Child", bob);

		solutionMap = engine.queryOne(Parent.class);
		assertEquals(famillySolutionMap, solutionMap);
		engine.dispose();

	}

	@Test
	public void testQueryAllClass() {

		PrologEngine engine = provider.newEngine();
		engine.register(parentMapping);
		engine.consult("family.pl");

		List<Map<String, PrologTerm>> famillyAll = new ArrayList<Map<String, PrologTerm>>(6);
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("Name", pam);
		solutionMap.put("Child", bob);
		famillyAll.add(0, solutionMap);
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("Name", tom);
		solutionMap.put("Child", bob);
		famillyAll.add(1, solutionMap);
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("Name", tom);
		solutionMap.put("Child", liz);
		famillyAll.add(2, solutionMap);
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("Name", bob);
		solutionMap.put("Child", ann);
		famillyAll.add(3, solutionMap);
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("Name", bob);
		solutionMap.put("Child", pat);
		famillyAll.add(4, solutionMap);
		solutionMap = new HashMap<String, PrologTerm>();
		solutionMap.put("Name", pat);
		solutionMap.put("Child", jim);
		famillyAll.add(5, solutionMap);

		List<Map<String, PrologTerm>> allSolutionMap = engine.queryAll(Parent.class);
		assertEquals(famillyAll, allSolutionMap);
		engine.dispose();

	}

}
