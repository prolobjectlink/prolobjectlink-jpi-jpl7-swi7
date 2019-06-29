/*
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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.prolobjectlink.prolog.PrologEngine;
import org.prolobjectlink.prolog.PrologQuery;
import org.prolobjectlink.prolog.PrologQueryBuilder;
import org.prolobjectlink.prolog.PrologStructure;
import org.prolobjectlink.prolog.PrologTerm;
import org.prolobjectlink.prolog.PrologVariable;

public class PrologQueryBuilderTest extends PrologBaseTest {

	private PrologVariable x;
	private PrologStructure dark;
	private PrologStructure big;
	private PrologStructure small;

	private PrologEngine engine;
	private PrologQuery query;

	private PrologQueryBuilder builder;

	private PrologTerm[] bearExpected = { provider.newAtom("bear") };
	private PrologTerm[] catExpected = { provider.newAtom("cat") };

	@Before
	public void setUp() throws Exception {

		engine = provider.newEngine();

		engine.assertz("big(bear)");
		engine.assertz("big(elephant)");
		engine.assertz("small(cat)");
		engine.assertz("brown(bear)");
		engine.assertz("black(cat)");
		engine.assertz("gray(elephant)");
		engine.assertz("dark(Z) :- black(Z)");
		engine.assertz("dark(Z) :- brown(Z)");

	}

	@After
	public void tearDown() throws Exception {
		engine.dispose();
	}

	@Test
	public void testBeginPrologTerm() {

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		query = engine.newQueryBuilder().begin(dark).query();
		assertTrue(query.hasSolution());
		assertArrayEquals(catExpected, query.oneSolution());

	}

	@Test
	public void testCommaPrologTerm() {

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		builder = engine.newQueryBuilder();
		builder.begin(dark);
		builder.comma(big);
		builder.comma(big);
		builder.comma(big);
		builder.comma(big);
		builder.comma(big);
		builder.comma(big);
		builder.comma(big);
		builder.comma(big);

		query = builder.query();
		assertTrue(query.hasSolution());
		assertArrayEquals(bearExpected, query.oneSolution());

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		builder = engine.newQueryBuilder();
		builder.begin(dark);
		builder.comma(provider.newStructure("not", big));
		query = builder.query();
		assertTrue(query.hasSolution());
		assertArrayEquals(catExpected, query.oneSolution());

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		builder = engine.newQueryBuilder();
		builder.begin(dark);
		builder.comma(provider.newStructure("not", big));
		builder.comma(small);
		query = builder.query();
		assertTrue(query.hasSolution());
		assertArrayEquals(catExpected, query.oneSolution());

	}

	@Test
	public void testSemicolonPrologTerm() {

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		builder = engine.newQueryBuilder();
		builder.begin(dark);
		builder.semicolon(big);
		builder.semicolon(big);
		builder.semicolon(big);
		builder.semicolon(big);
		builder.semicolon(big);
		builder.semicolon(big);
		builder.semicolon(big);
		builder.semicolon(big);

		query = builder.query();
		assertTrue(query.hasSolution());
		assertArrayEquals(catExpected, query.oneSolution());

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		builder = engine.newQueryBuilder();

		// fail intentionally at the first predicate
		builder.begin(provider.prologFail());

		// solve second as alternative
		builder.semicolon(big);
		query = builder.query();
		assertTrue(query.hasSolution());
		assertArrayEquals(bearExpected, query.oneSolution());

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		builder = engine.newQueryBuilder();

		// fail intentionally at the first predicate
		builder.begin("dark", provider.newAtom("octupus"));

		// solve second as alternative
		builder.semicolon(big);
		query = builder.query();
		assertTrue(query.hasSolution());
		assertArrayEquals(bearExpected, query.oneSolution());

	}

	@Test
	public void testBuildQuery() {

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		query = engine.query(dark);
		builder = engine.newQueryBuilder().begin(dark);
		assertEquals(query, builder.query());
		query.dispose();

		x = provider.newVariable("X", 0);
		big = provider.newStructure("big", x);
		dark = provider.newStructure("dark", x);
		small = provider.newStructure("small", x);

		query = engine.query("dark(X),big(X)");
		builder = engine.newQueryBuilder().begin(dark).comma(big);
		assertEquals(query, builder.query());

	}

}
