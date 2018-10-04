package org.logicware.prolog.jpl7.swi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.logicware.prolog.PrologTermType.VARIABLE_TYPE;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.logicware.prolog.IndicatorError;
import org.logicware.prolog.PrologAtom;
import org.logicware.prolog.PrologDouble;
import org.logicware.prolog.PrologFloat;
import org.logicware.prolog.PrologInteger;
import org.logicware.prolog.PrologList;
import org.logicware.prolog.PrologLong;
import org.logicware.prolog.PrologStructure;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologVariable;
import org.logicware.prolog.jpl7.JplInteger;

public class PrologVariableTest extends PrologBaseTest {

	private PrologVariable variable;

	@Before
	public void setUp() throws Exception {
		variable = provider.newVariable("X");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetArguments() {
		assertArrayEquals(new JplInteger[0], variable.getArguments());
	}

	@Test
	public final void testIsAnonim() {
		assertFalse(variable.isAnonymous());
		assertTrue(provider.newVariable().isAnonymous());
	}

	@Test
	public final void testGetName() {
		assertEquals("X", variable.getName());
	}

	@Test
	public final void testSetName() {
		assertEquals("X", variable.getName());
		variable.setName("Y");
		assertEquals("Y", variable.getName());
	}

	@Test(expected = IndicatorError.class)
	public final void testGetKey() {
		variable.getIndicator();
	}

	@Test
	public final void testGetType() {
		assertEquals(VARIABLE_TYPE, variable.getType());
	}

	@Test
	public final void testIsAtom() {
		assertFalse(variable.isAtom());
	}

	@Test
	public final void testIsNumber() {
		assertFalse(variable.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertFalse(variable.isFloat());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(variable.isInteger());
	}

	@Test
	public final void testIsVariable() {
		assertTrue(variable.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(variable.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(variable.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(variable.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(variable.isEmptyList());
	}

	@Test
	public final void testIsExpression() {
		assertFalse(variable.isEvaluable());
	}

	public final void testGetArity() {
		variable.getArity();
	}

	public final void testGetFunctor() {
		variable.getFunctor();
	}

	@Test
	public final void testUnify() {

		// with atom
		PrologVariable variable = provider.newVariable("X");
		PrologAtom atom = provider.newAtom("John Smith");
		assertTrue(variable.unify(atom));

		// with integer
		variable = provider.newVariable("X");
		PrologInteger iValue = provider.newInteger(28);
		assertTrue(variable.unify(iValue));

		// with long
		variable = provider.newVariable("X");
		PrologLong lValue = provider.newLong(28);
		assertTrue(variable.unify(lValue));

		// with float
		variable = provider.newVariable("X");
		PrologFloat fValue = provider.newFloat(36.47);
		assertTrue(variable.unify(fValue));

		// with double
		variable = provider.newVariable("X");
		PrologDouble dValue = provider.newDouble(36.47);
		assertTrue(variable.unify(dValue));

		// with variable
		variable = provider.newVariable("X");
		PrologVariable y = provider.newVariable("Y");
		assertTrue(variable.unify(variable)); // are
		// equals
		assertTrue(variable.unify(y)); // alphabetic
		// substitution

		// with predicate with occurs check
		variable = provider.newVariable("X");
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertTrue(variable.unify(structure));
		structure = provider.parsePrologStructure("structure([X])");
		assertFalse(variable.unify(structure));

		variable = provider.newVariable("X");
		structure = provider.parsePrologStructure("structure(A,b,C)");
		assertTrue(variable.unify(structure));

		// with list
		variable = provider.newVariable("X");
		PrologVariable z = provider.newVariable("Z");
		PrologList flattenList = provider.parsePrologList("[X]");
		PrologList headTailList = provider.parsePrologList("[Y|[]]");
		PrologTerm empty = provider.prologEmpty();
		assertFalse(variable.unify(flattenList));
		assertFalse(y.unify(headTailList));
		assertTrue(z.unify(empty));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologVariable variable = provider.newVariable("X");
		PrologAtom atom = provider.newAtom("John Smith");
		assertEquals(variable.compareTo(atom), -1);

		// with integer
		variable = provider.newVariable("X");
		PrologInteger iValue = provider.newInteger(28);
		assertEquals(variable.compareTo(iValue), -1);

		// with long
		variable = provider.newVariable("X");
		PrologLong lValue = provider.newLong(28);
		assertEquals(variable.compareTo(lValue), -1);

		// with float
		variable = provider.newVariable("X");
		PrologFloat fValue = provider.newFloat(36.47);
		assertEquals(variable.compareTo(fValue), -1);

		// with double
		variable = provider.newVariable("X");
		PrologDouble dValue = provider.newDouble(36.47);
		assertEquals(variable.compareTo(dValue), -1);

		// with variable
		variable = provider.newVariable("X");
		PrologVariable y = provider.newVariable("Y");
		assertEquals(variable.compareTo(variable), 0); // are
		// equals
		assertEquals(variable.compareTo(y), -1); // alphabetic
		// substitution

		variable = provider.newVariable("X");
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertEquals(variable.compareTo(structure), -1);
		structure = provider.parsePrologStructure("structure([X])");
		assertEquals(variable.compareTo(structure), -1);

		variable = provider.newVariable("X");
		structure = provider.parsePrologStructure("structure(A,b,C)");
		assertEquals(variable.compareTo(structure), -1);

		// with list
		variable = provider.newVariable("X");
		PrologVariable z = provider.newVariable("Z");
		PrologList flattenList = provider.parsePrologList("[X]");
		PrologList headTailList = provider.parsePrologList("[Y|[]]");
		PrologTerm empty = provider.prologEmpty();
		assertEquals(variable.compareTo(flattenList), -1);
		assertEquals(y.compareTo(headTailList), -1);
		assertEquals(z.compareTo(empty), -1);
	}

}
