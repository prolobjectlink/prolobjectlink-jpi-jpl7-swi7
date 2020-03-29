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
package io.github.prolobjectlink.prolog.jpl7.swi7;

import static io.github.prolobjectlink.prolog.PrologTermType.NIL_TYPE;
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

public class PrologNilTest extends PrologBaseTest {

	private PrologTerm nil = provider.prologNil();

	@Test
	public final void testGetIndicator() {
		assertEquals("nil/0", nil.getIndicator());
	}

	@Test
	public void testHasIndicator() {
		assertFalse(nil.hasIndicator("an", 100));
		assertFalse(nil.hasIndicator("an", 0));
		assertFalse(nil.hasIndicator("nil", 100));
		assertTrue(nil.hasIndicator("nil", 0));
	}

	@Test
	public final void testGetArity() {
		assertEquals(0, nil.getArity());
	}

	@Test
	public final void testGetFunctor() {
		assertEquals("nil", nil.getFunctor());
	}

	@Test
	public final void testGetArguments() {
		assertArrayEquals(new PrologTerm[0], nil.getArguments());
	}

	@Test
	public final void testToString() {
		assertEquals("nil", nil.toString());
	}

	@Test
	public final void testHashCode() {
		assertEquals(provider.prologNil().hashCode(), nil.hashCode());
	}

	@Test
	public final void testGetType() {
		assertEquals(NIL_TYPE, nil.getType());
	}

	@Test
	public final void testGetTerm() {
		assertEquals(nil, nil.getTerm());
	}

	@Test
	public final void testIsAtom() {
		assertTrue(nil.isAtom());
	}

	@Test
	public final void testIsNumber() {
		assertFalse(nil.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(nil.isFloat());
	}

	@Test
	public final void testIsDouble() {
		assertFalse(nil.isDouble());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(nil.isInteger());
	}

	@Test
	public final void testIsLong() {
		assertFalse(nil.isLong());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(nil.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(nil.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(nil.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertTrue(nil.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(nil.isEmptyList());
	}

	@Test
	public final void testIsExpression() {
		assertFalse(nil.isEvaluable());
	}

	@Test
	public final void testUnify() {

		// with atom
		PrologTerm empty = provider.prologNil();
		PrologAtom atom = provider.newAtom("John Doe");
		assertFalse(empty.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertFalse(empty.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(empty.unify(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertFalse(empty.unify(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertFalse(empty.unify(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		// true. case [] and variable
		assertTrue(empty.unify(variable));

		// with predicate
		PrologStructure structure = provider.parseStructure("some_predicate(a,b,c)");
		assertFalse(empty.unify(structure));

		// with list
		PrologList list = provider.parseList("[a,b,c]");
		assertFalse(empty.unify(list));
		assertTrue(empty.unify(empty));

		// with expression
		PrologTerm expression = provider.parseTerm("58+93*10");
		assertFalse(empty.unify(expression));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologTerm empty = provider.prologNil();
		PrologAtom atom = provider.newAtom("John Doe");
		assertEquals(1, empty.compareTo(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(36);
		assertEquals(1, empty.compareTo(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(1, empty.compareTo(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(1, empty.compareTo(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(1, empty.compareTo(dValue));

		// with variable
		PrologVariable variable = provider.newVariable("X", 0);
		assertEquals(1, empty.compareTo(variable));

		// with predicate
		PrologStructure structure = provider.parseStructure("some_predicate(a,b,c)");
		assertEquals(-1, empty.compareTo(structure));

		// with list
		PrologList list = provider.parseList("[a,b,c]");
		assertEquals(-1, empty.compareTo(list));
		assertEquals(0, empty.compareTo(empty));

		// with expression
		PrologTerm expression = provider.parseTerm("58+93*10");
		assertEquals(-1, empty.compareTo(expression));

	}

	@Test
	public final void testEqualsObject() {
		assertEquals(provider.prologNil(), nil);
	}

}
