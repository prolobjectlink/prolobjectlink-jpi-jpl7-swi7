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

import static io.github.prolobjectlink.prolog.PrologTermType.LIST_TYPE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
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

public class PrologListTest extends PrologBaseTest {

	private PrologList list;

	@Before
	public void setUp() throws Exception {
		list = provider.newList(new PrologTerm[] { zero, one, two, three, four, five, six, seven, eight, nine });
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetArguments() {
		PrologTerm[] terms = { zero, one, two, three, four, five, six, seven, eight, nine };
		assertArrayEquals(terms, list.getArguments());
	}

	@Test
	public final void testSize() {
		assertEquals(10, list.size());
	}

	@Test
	public final void testClear() {
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	public final void testIsEmpty() {
		assertFalse(list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	public final void testIterator() {
		int number = 0;
		for (Iterator<PrologTerm> iterator = list.iterator(); iterator.hasNext();) {
			PrologTerm prologTerm = (PrologTerm) iterator.next();
			assertEquals(provider.newInteger(number++), prologTerm);
		}
	}

	@Test
	public final void testGetHead() {
		assertEquals(provider.newInteger(0), list.getHead());
	}

	@Test
	public final void testGetTail() {
		assertEquals(provider.newList(new PrologTerm[] { one, two, three, four, five, six, seven, eight, nine }),
				list.getTail());
	}

	@Test
	public final void testGetType() {
		assertEquals(LIST_TYPE, list.getType());
	}

	@Test
	public final void testGetKey() {
		assertEquals("./2", list.getIndicator());
	}

	@Test
	public final void testIsAtom() {
		assertFalse(list.isAtom());
	}

	@Test
	public final void testIsNumber() {
		assertFalse(list.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(list.isFloat());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(list.isInteger());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(list.isVariable());
	}

	@Test
	public final void testIsList() {
		assertTrue(list.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(list.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(list.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(list.isEmptyList());
	}

	@Test
	public final void testIsExpression() {
		assertFalse(list.isEvaluable());
	}

	@Test
	public final void testGetArity() {
		assertEquals(2, list.getArity());
	}

	@Test
	public final void testGetFunctor() {
		assertEquals(".", list.getFunctor());
	}

	@Test
	public final void testUnify() {

		PrologTerm empty = provider.prologEmpty();
		PrologList flattened = provider.parseList("[a,b,c]");
		PrologList headTail = provider.parseList("[a|[b|[c|[]]]]");

		// with atom
		PrologAtom atom = provider.newAtom("John Doe");
		assertFalse(flattened.unify(atom));
		assertFalse(headTail.unify(atom));
		assertFalse(empty.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(28);
		assertFalse(flattened.unify(iValue));
		assertFalse(headTail.unify(iValue));
		assertFalse(empty.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(flattened.unify(lValue));
		assertFalse(headTail.unify(lValue));
		assertFalse(empty.unify(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertFalse(flattened.unify(fValue));
		assertFalse(headTail.unify(fValue));
		assertFalse(empty.unify(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertFalse(flattened.unify(dValue));
		assertFalse(headTail.unify(dValue));
		assertFalse(empty.unify(dValue));

		// with variable
		PrologVariable x = provider.newVariable("X", 0);
		PrologVariable y = provider.newVariable("Y", 0);
		PrologVariable z = provider.newVariable("Z", 0);
		assertTrue(flattened.unify(x));
		assertTrue(headTail.unify(y));
		assertTrue(empty.unify(z));

		// with predicate
		PrologStructure structure = provider.parseStructure("somepredicate(a,b,c)");
		assertFalse(flattened.unify(structure));
		assertFalse(headTail.unify(structure));
		assertFalse(empty.unify(structure));

		// with list
		x = provider.newVariable("X", 0);

		PrologList flattenList1 = provider.parseList("[X,Y,Z]");
		PrologList headTailList1 = provider.parseList("[X|[Y|[Z]]]");

		// true because are equals
		assertTrue(flattened.unify(flattened));
		assertTrue(headTail.unify(headTail));
		assertTrue(empty.unify(empty));

		// true because their terms unify
		assertTrue(flattened.unify(flattenList1));
		assertTrue(headTail.unify(headTailList1));

		// testing different list type that unify
		assertTrue(flattened.unify(headTail));
		assertTrue(flattenList1.unify(headTailList1));
		assertTrue(flattened.unify(headTailList1));
		assertTrue(flattenList1.unify(headTail));

	}

	@Test
	public final void testCompareTo() {

		PrologList flattened = provider.parseList("[a,b,c]");
		PrologList headTail = provider.parseList("[a|[b|[c|[]]]]");

		// with atom
		PrologAtom atom = provider.newAtom("John Doe");
		assertEquals(1, flattened.compareTo(atom));
		assertEquals(1, headTail.compareTo(atom));
		assertEquals(1, empty.compareTo(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(28);
		assertEquals(1, flattened.compareTo(iValue));
		assertEquals(1, headTail.compareTo(iValue));
		assertEquals(1, empty.compareTo(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(1, flattened.compareTo(lValue));
		assertEquals(1, headTail.compareTo(lValue));
		assertEquals(1, empty.compareTo(lValue));

		// with float
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(1, flattened.compareTo(fValue));
		assertEquals(1, headTail.compareTo(fValue));
		assertEquals(1, empty.compareTo(fValue));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(1, flattened.compareTo(dValue));
		assertEquals(1, headTail.compareTo(dValue));
		assertEquals(1, empty.compareTo(dValue));

		// with variable
		PrologVariable x = provider.newVariable("X", 0);
		PrologVariable y = provider.newVariable("Y", 0);
		PrologVariable z = provider.newVariable("Z", 0);
		assertEquals(1, flattened.compareTo(x));
		assertEquals(1, headTail.compareTo(y));
		assertEquals(1, empty.compareTo(z));

		// with predicate
		PrologStructure structure = provider.parseStructure("somepredicate(a,b,c)");
		assertEquals(flattened.compareTo(structure), -1);
		assertEquals(headTail.compareTo(structure), -1);
		assertEquals(empty.compareTo(structure), -1);

		// with list
		PrologList flattenList1 = provider.parseList("[X,Y,Z]");
		PrologList headTailList1 = provider.parseList("[X|[Y|[Z]]]");

		// true because are equals
		assertEquals(0, flattened.compareTo(flattened));
		assertEquals(0, headTail.compareTo(headTail));
		assertEquals(0, empty.compareTo(empty));

		// true because their terms are equals
		assertEquals(1, flattened.compareTo(flattenList1));
		assertEquals(1, headTail.compareTo(headTailList1));

		// testing different list type
		assertEquals(0, flattened.compareTo(headTail));
		// in version 2.7.2 this not work fine
		// assertEquals(flattenList1.compareTo(headTailList1), 0);
		assertEquals(1, flattened.compareTo(headTailList1));
		assertEquals(-1, flattenList1.compareTo(headTail));

	}

}
