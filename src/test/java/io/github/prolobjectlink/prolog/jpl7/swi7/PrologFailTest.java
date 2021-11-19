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

public class PrologFailTest extends PrologBaseTest {

	private PrologTerm fail = provider.prologFail();

	@Test
	public void testGetArguments() {
		assertArrayEquals(new PrologTerm[0], fail.getArguments());
	}

	@Test
	public void testGetArity() {
		assertEquals(0, fail.getArity());
	}

	@Test
	public void testGetFunctor() {
		assertEquals("fail", fail.getFunctor());
	}

	@Test
	public void testGetIndicator() {
		assertEquals("fail/0", fail.getIndicator());
	}

	@Test
	public void testHasIndicator() {
		assertTrue(fail.hasIndicator("fail", 0));
	}

	@Test
	public void testHashCode() {
		assertFalse(provider.prologCut().hashCode() == fail.hashCode());
		assertEquals(provider.prologFail().hashCode(), fail.hashCode());
	}

	@Test
	public void testIsAtom() {
		assertTrue(fail.isAtom());
	}

	@Test
	public void testIsNumber() {
		assertFalse(fail.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(fail.isFloat());
	}

	@Test
	public final void testIsDouble() {
		assertFalse(fail.isDouble());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(fail.isInteger());
	}

	@Test
	public final void testIsLong() {
		assertFalse(fail.isLong());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(fail.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(fail.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(fail.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(fail.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(fail.isEmptyList());
	}

	@Test
	public final void testIsEvaluable() {
		assertFalse(fail.isEvaluable());
	}

	@Test
	public void testIsAtomic() {
		assertTrue(fail.isAtomic());
	}

	@Test
	public void testIsCompound() {
		assertFalse(fail.isCompound());
	}

	@Test
	public final void testUnify() {

		// with atom
		PrologTerm fail = provider.prologFail();
		PrologAtom atom = provider.newAtom("John Doe");
		assertFalse(fail.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertFalse(fail.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(fail.unify(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertFalse(fail.unify(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertFalse(fail.unify(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertTrue(fail.unify(variable));

		// with predicate
		PrologStructure structure = provider.parseStructure("some_predicate(a,b,c)");
		assertFalse(fail.unify(structure));

		// with list
		PrologList list = provider.parseList("[a,b,c]");
		assertFalse(fail.unify(list));
		assertTrue(fail.unify(fail));

		// with expression
		PrologTerm expression = provider.parseTerm("58+93*10");
		assertFalse(fail.unify(expression));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologTerm fail = provider.prologFail();
		PrologAtom atom = provider.newAtom("John Doe");
		assertEquals(1, fail.compareTo(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertEquals(1, fail.compareTo(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(1, fail.compareTo(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(1, fail.compareTo(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(1, fail.compareTo(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		assertEquals(1, fail.compareTo(variable));

		// with predicate
		PrologStructure structure = provider.parseStructure("some_predicate(a,b,c)");
		assertEquals(fail.compareTo(structure), -1);

		// with list
		PrologList list = provider.parseList("[a,b,c]");
		assertEquals(-1, fail.compareTo(list));
		assertEquals(0, fail.compareTo(fail));

		// with expression
		PrologTerm expression = provider.parseTerm("58+93*10");
		assertEquals(-1, fail.compareTo(expression));

	}

	@Test
	public void testEqualsObject() {
		assertFalse(fail.equals(provider.prologCut()));
		assertTrue(fail.equals(provider.prologFail()));
	}

	@Test
	public void testToString() {
		assertEquals("fail", fail.toString());
	}

}
