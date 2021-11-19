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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.prolobjectlink.prolog.PrologAtom;
import io.github.prolobjectlink.prolog.PrologDouble;
import io.github.prolobjectlink.prolog.PrologFloat;
import io.github.prolobjectlink.prolog.PrologInteger;
import io.github.prolobjectlink.prolog.PrologList;
import io.github.prolobjectlink.prolog.PrologLong;
import io.github.prolobjectlink.prolog.PrologStructure;
import io.github.prolobjectlink.prolog.PrologTerm;
import io.github.prolobjectlink.prolog.PrologVariable;

public class PrologTrueTest extends PrologBaseTest {

	private PrologTerm t = provider.prologTrue();

	@Test
	public void testGetArguments() {
		assertArrayEquals(new PrologTerm[0], t.getArguments());
	}

	@Test
	public void testGetArity() {
		assertEquals(0, t.getArity());
	}

	@Test
	public void testGetFunctor() {
		assertEquals("true", t.getFunctor());
	}

	@Test
	public void testGetIndicator() {
		assertEquals("true/0", t.getIndicator());
	}

	@Test
	public void testHasIndicator() {
		assertTrue(t.hasIndicator("true", 0));
	}

	@Test
	public void testHashCode() {
		assertFalse(provider.prologCut().hashCode() == t.hashCode());
		assertFalse(provider.prologFail().hashCode() == t.hashCode());
		assertEquals(provider.prologTrue().hashCode(), t.hashCode());
	}

	@Test
	public void testIsAtom() {
		assertTrue(t.isAtom());
	}

	@Test
	public void testIsNumber() {
		assertFalse(t.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(t.isFloat());
	}

	@Test
	public final void testIsDouble() {
		assertFalse(t.isDouble());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(t.isInteger());
	}

	@Test
	public final void testIsLong() {
		assertFalse(t.isLong());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(t.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(t.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(t.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(t.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(t.isEmptyList());
	}

	@Test
	public final void testIsEvaluable() {
		assertFalse(t.isEvaluable());
	}

	@Test
	public void testIsAtomic() {
		assertTrue(t.isAtomic());
	}

	@Test
	public void testIsCompound() {
		assertFalse(t.isCompound());
	}

	@Test
	public final void testUnify() {

		// with atom
		PrologTerm t = provider.prologTrue();
		PrologAtom atom = provider.newAtom("John Doe");
		assertFalse(t.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertFalse(t.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(t.unify(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertFalse(t.unify(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertFalse(t.unify(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertTrue(t.unify(variable));

		// with predicate
		PrologStructure structure = provider.parseStructure("some_predicate(a,b,c)");
		assertFalse(t.unify(structure));

		// with list
		PrologList list = provider.parseList("[a,b,c]");
		assertFalse(t.unify(list));
		assertTrue(t.unify(t));

		// with expression
		PrologTerm expression = provider.parseTerm("58+93*10");
		assertFalse(t.unify(expression));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologTerm t = provider.prologTrue();
		PrologAtom atom = provider.newAtom("John Doe");
		assertEquals(1, t.compareTo(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertEquals(1, t.compareTo(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(1, t.compareTo(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(1, t.compareTo(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(1, t.compareTo(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		assertEquals(1, t.compareTo(variable));

		// with predicate
		PrologStructure structure = provider.parseStructure("some_predicate(a,b,c)");
		assertEquals(-1, t.compareTo(structure));

		// with list
		PrologList list = provider.parseList("[a,b,c]");
		assertEquals(-1, t.compareTo(list));
		assertEquals(0, t.compareTo(t));

		// with expression
		PrologTerm expression = provider.parseTerm("58+93*10");
		assertEquals(-1, t.compareTo(expression));

	}

	@Test
	public void testEqualsObject() {
		assertFalse(t.equals(provider.prologCut()));
		assertFalse(t.equals(provider.prologFail()));
		assertTrue(t.equals(provider.prologTrue()));
	}

	@Test
	public void testToString() {
		assertEquals("true", t.toString());
	}

}
