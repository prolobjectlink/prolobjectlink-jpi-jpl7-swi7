/*
 * #%L
 * prolobjectlink-jpi-zprolog
 * %%
 * Copyright (C) 2012 - 2017 Logicware Project
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.logicware.prolog.jpl7.swi7;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologClauses;
import org.logicware.prolog.jpl7.JplClauses;

public class PrologClausesTest extends PrologBaseTest {

	private PrologClauses fact;
	private PrologClauses rule;

	private final PrologClause[] ruleArray = new PrologClause[1];
	private final PrologClause[] factsArray = new PrologClause[6];

	@Before
	public void setUp() throws Exception {

		fact = new JplClauses("parent/2");
		fact.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		fact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		fact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		fact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		fact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		fact.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));

		rule = new JplClauses("grandparent/2",
				new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )")));

		ruleArray[0] = new SwiProlog7Clause(
				provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )"));

		factsArray[0] = new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )"));
		factsArray[1] = new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )"));
		factsArray[2] = new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )"));
		factsArray[3] = new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )"));
		factsArray[4] = new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )"));
		factsArray[5] = new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHashCode() {

		PrologClauses newFact = new JplClauses("parent/2");
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));

		PrologClauses newRule = new JplClauses("grandparent/2",
				new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )")));

		assertEquals(fact.hashCode(), newFact.hashCode());
		assertEquals(rule.hashCode(), newRule.hashCode());

	}

	@Test
	public final void testSize() {
		assertEquals(6, fact.size());
		assertEquals(1, rule.size());
	}

	@Test
	public final void testClear() {

		fact.clear();
		rule.clear();

		assertTrue(fact.isEmpty());
		assertEquals(0, fact.size());
		assertTrue(rule.isEmpty());
		assertEquals(0, rule.size());

	}

	@Test
	public final void testAddIPrologClause() {

		fact = new JplClauses("parent/2");
		assertEquals(0, fact.size());

		assertTrue(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )"))));
		assertEquals(1, fact.size());

		assertTrue(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )"))));
		assertEquals(2, fact.size());

		assertTrue(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )"))));
		assertEquals(3, fact.size());

		assertTrue(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )"))));
		assertEquals(4, fact.size());

		assertTrue(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )"))));
		assertEquals(5, fact.size());

		assertTrue(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"))));
		assertEquals(6, fact.size());

		// already exist
		assertFalse(fact.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"))));
		assertEquals(6, fact.size());

	}

	@Test
	public final void testContains() {

		assertTrue(fact.contains(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )"))));
		assertTrue(fact.contains(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )"))));
		assertTrue(fact.contains(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )"))));
		assertTrue(fact.contains(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )"))));
		assertTrue(fact.contains(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )"))));
		assertTrue(fact.contains(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"))));
		assertFalse(fact.contains(
				new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )"))));

		assertFalse(rule.contains(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )"))));
		assertFalse(rule.contains(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )"))));
		assertFalse(rule.contains(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )"))));
		assertFalse(rule.contains(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )"))));
		assertFalse(rule.contains(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )"))));
		assertFalse(rule.contains(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"))));
		assertTrue(rule.contains(
				new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )"))));

	}

	@Test
	public final void testRemoveObject() {

		assertTrue(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )"))));
		assertEquals(5, fact.size());

		assertTrue(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )"))));
		assertEquals(4, fact.size());

		assertTrue(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )"))));
		assertEquals(3, fact.size());

		assertTrue(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )"))));
		assertEquals(2, fact.size());

		assertTrue(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )"))));
		assertEquals(1, fact.size());

		assertTrue(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"))));
		assertEquals(0, fact.size());

		assertFalse(fact.remove(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )"))));
		assertEquals(0, fact.size());
		assertTrue(fact.isEmpty());

		assertTrue(rule.remove(
				new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )"))));
		assertEquals(0, rule.size());
		assertTrue(rule.isEmpty());

	}

	// @Test
	// public final void testMarkDynamic() {
	//
	// assertFalse(fact.isDynamic());
	// assertFalse(rule.isDynamic());
	//
	// fact.markDynamic();
	// rule.markDynamic();
	//
	// assertTrue(fact.isDynamic());
	// assertTrue(rule.isDynamic());
	//
	// }

	// @Test
	// public final void testUnmarkDynamic() {
	//
	// assertFalse(fact.isDynamic());
	// assertFalse(rule.isDynamic());
	//
	// fact.markDynamic();
	// rule.markDynamic();
	//
	// assertTrue(fact.isDynamic());
	// assertTrue(rule.isDynamic());
	//
	// fact.unmarkDynamic();
	// rule.unmarkDynamic();
	//
	// assertFalse(fact.isDynamic());
	// assertFalse(rule.isDynamic());
	//
	// }

	@Test
	public final void testIsDynamic() {
		assertFalse(fact.isDynamic());
		assertFalse(rule.isDynamic());
	}

	// @Test
	// public final void testMarkMultifile() {
	//
	// assertFalse(fact.isMultifile());
	// assertFalse(rule.isMultifile());
	//
	// fact.markMultifile();
	// rule.markMultifile();
	//
	// assertTrue(fact.isMultifile());
	// assertTrue(rule.isMultifile());
	//
	// }

	// @Test
	// public final void testUnmarkMultifile() {
	//
	// assertFalse(fact.isMultifile());
	// assertFalse(rule.isMultifile());
	//
	// fact.markMultifile();
	// rule.markMultifile();
	//
	// assertTrue(fact.isMultifile());
	// assertTrue(rule.isMultifile());
	//
	// fact.unmarkMultifile();
	// rule.unmarkMultifile();
	//
	// assertFalse(fact.isMultifile());
	// assertFalse(rule.isMultifile());
	//
	// }

	@Test
	public final void testIsMultifile() {
		assertFalse(fact.isMultifile());
		assertFalse(rule.isMultifile());
	}

	// @Test
	// public final void testMarkDiscontiguous() {
	//
	// assertFalse(fact.isDiscontiguous());
	// assertFalse(rule.isDiscontiguous());
	//
	// fact.markDiscontiguous();
	// rule.markDiscontiguous();
	//
	// assertTrue(fact.isDiscontiguous());
	// assertTrue(rule.isDiscontiguous());
	//
	// }

	// @Test
	// public final void testUnmarkDiscontiguous() {
	//
	// assertFalse(fact.isDiscontiguous());
	// assertFalse(rule.isDiscontiguous());
	//
	// fact.markDiscontiguous();
	// rule.markDiscontiguous();
	//
	// assertTrue(fact.isDiscontiguous());
	// assertTrue(rule.isDiscontiguous());
	//
	// fact.unmarkDiscontiguous();
	// rule.unmarkDiscontiguous();
	//
	// assertFalse(fact.isDiscontiguous());
	// assertFalse(rule.isDiscontiguous());
	//
	// }

	@Test
	public final void testIsDiscontiguous() {
		assertFalse(fact.isDiscontiguous());
		assertFalse(rule.isDiscontiguous());
	}

	@Test
	public final void testEqualsObject() {

		PrologClauses newFact = new JplClauses("parent/2");
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		newFact.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));

		PrologClauses newRule = new JplClauses("grandparent/2",
				new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )")));

		assertEquals(fact, newFact);
		assertEquals(rule, newRule);

	}

	@Test
	public final void testIterator() {

		Iterator<PrologClause> i = fact.iterator();
		assertNotNull(i);

		for (int index = 0; i.hasNext() && index < factsArray.length; index++) {
			PrologClause clause = i.next();
			assertEquals(factsArray[index], clause);
		}

	}

	@Test
	public final void testIsEmpty() {

		assertFalse(fact.isEmpty());
		assertFalse(rule.isEmpty());
		assertTrue(new JplClauses("empty/0").isEmpty());

	}

	@Test
	public final void testToArray() {

		assertArrayEquals(factsArray, fact.toArray());

		Object[] rules = new Object[1];
		rules[0] = new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )"));

		assertArrayEquals(rules, rule.toArray());

	}

	@Test
	public final void testToArrayTArray() {

		assertArrayEquals(factsArray, fact.toArray(new PrologClause[0]));

		PrologClause[] rules = new PrologClause[1];
		rules[0] = new SwiProlog7Clause(provider.parseTerm("grandparent( X, Z ) :- parent( X, Y ), parent( Y, Z )"));

		assertArrayEquals(rules, rule.toArray(new PrologClause[0]));

	}

	@Test
	public final void testContainsAll() {

		assertTrue(fact.containsAll(fact));

		List<PrologClause> clauses = Arrays.asList(factsArray);
		assertTrue(fact.containsAll(clauses));

	}

	@Test
	public final void testAddAll() {

		List<PrologClause> clauseList = Arrays.asList(factsArray);

		PrologClauses clauses = new JplClauses("parent/2");
		assertEquals(0, clauses.size());

		assertTrue(clauses.addAll(clauseList));
		assertEquals(6, clauses.size());
		assertEquals(fact, clauses);

	}

	@Test
	public final void testRemoveAll() {

		assertTrue(!fact.isEmpty());
		fact.removeAll(Arrays.asList(factsArray));
		assertTrue(fact.isEmpty());

		assertTrue(!rule.isEmpty());
		rule.removeAll(Arrays.asList(ruleArray));
		assertTrue(rule.isEmpty());

	}

	@Test
	public final void testRetainAll() {

		PrologClause[] array = {

				new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")),

				new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )"))

		};

		List<PrologClause> clauses = Arrays.asList(array);
		PrologClauses expected = new JplClauses("parent/2", clauses);
		assertTrue(fact.retainAll(clauses));
		assertArrayEquals(array, fact.toArray(array));
		assertEquals(expected, fact);

	}

}
