/*
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2019 Prolobjectlink Project
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import org.prolobjectlink.prolog.PrologClause;
import org.prolobjectlink.prolog.PrologEngine;
import org.prolobjectlink.prolog.PrologIndicator;
import org.prolobjectlink.prolog.PrologTerm;

public class PrologClauseTest extends PrologBaseTest {

	PrologEngine engine = provider.newEngine();
	PrologEngine e = provider.newEngine();

	@Test
	public void testHashCode() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		e.assertz(provider.newStructure(parent, tom, bob));
		e.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.iterator();
		Iterator<PrologClause> ie = e.iterator();

		assertEquals(iengine.next().hashCode(), ie.next().hashCode());
		assertEquals(iengine.next().hashCode(), ie.next().hashCode());

	}

	@Test
	public void testGetTerm() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertEquals(
				provider.newStructure(":-", provider.newStructure(grandparent, x, z), provider.newStructure(",",
						provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))),
				engine.iterator().next().getTerm());

	}

	@Test
	public void testGetHead() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals(provider.newStructure(grandparent, x, z), engine.iterator().next().getHead());
	}

	@Test
	public void testGetBody() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals(
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z)),
				engine.iterator().next().getBody());
	}

	@Test
	public void testGetBodyArray() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertArrayEquals(new PrologTerm[] { provider.newStructure(parent, x, y), provider.newStructure(parent, y, z) },
				engine.iterator().next().getBodyArray());
	}

	@Test
	public void testGetBodyIterator() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		Iterator<PrologClause> iterator = engine.iterator();

		assertTrue(iterator.hasNext());
		assertNotNull(iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testGetFunctor() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals("grandparent", engine.iterator().next().getFunctor());
	}

	@Test
	public void testGetArity() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals(2, engine.iterator().next().getArity());
	}

	@Test
	public void testGetIndicator() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals("grandparent/2", engine.iterator().next().getIndicator());
	}

	@Test
	public void testIsDirective() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertFalse(engine.iterator().next().isDirective());
	}

	@Test
	public void testIsFact() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.iterator();

		// in global test invert the order
		PrologClause fact = iengine.next();
		PrologClause rule = iengine.next();

		assertTrue(fact.isFact());
		assertFalse(rule.isFact());
	}

	@Test
	public void testIsRule() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.iterator();

		// in global test invert the order
		PrologClause fact = iengine.next();
		PrologClause rule = iengine.next();

		assertFalse(fact.isRule());
		assertTrue(rule.isRule());

	}

	@Test
	public void testUnify() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		PrologEngine e = provider.newEngine();
		e.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertTrue(engine.iterator().next().unify(e.iterator().next()));

	}

	@Test
	public void testIsDynamic() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertFalse(engine.iterator().next().isDynamic());

	}

	@Test
	public void testIsMultifile() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertFalse(engine.iterator().next().isMultifile());

	}

	@Test
	public void testIsDiscontiguous() {

		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		assertFalse(engine.iterator().next().isDiscontiguous());

	}

	@Test
	public void testGetPrologIndicator() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		PrologIndicator i = engine.iterator().next().getPrologIndicator();
		assertEquals("grandparent", i.getFunctor());
		assertEquals(2, i.getArity());
	}

	@Test
	public void testEqualsObject() {

		engine.assertz(provider.newStructure(parent, tom, bob));
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		e.assertz(provider.newStructure(parent, tom, bob));
		e.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));

		Iterator<PrologClause> iengine = engine.iterator();
		Iterator<PrologClause> ie = e.iterator();

		assertEquals(iengine.next(), ie.next());
		assertEquals(iengine.next(), ie.next());

		assertTrue(engine.iterator().next().equals(e.iterator().next()));
		assertFalse(engine.iterator().next().equals(new Object()));
		assertFalse(engine.iterator().next().equals(null));
	}

	@Test
	public void testToString() {
		engine.assertz(provider.newStructure(grandparent, x, z), provider.newStructure(parent, x, y),
				provider.newStructure(parent, y, z));
		assertEquals("grandparent(X, Z):-\n\tparent(X, Y),\n\tparent(Y, Z).", engine.iterator().next().toString());
	}

}
