package org.logicware.prolog.jpl7.swi;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.logicware.pdb.prolog.PrologTermType.FLOAT_TYPE;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.logicware.pdb.prolog.ArityError;
import org.logicware.pdb.prolog.FunctorError;
import org.logicware.pdb.prolog.IndicatorError;
import org.logicware.pdb.prolog.PrologAtom;
import org.logicware.pdb.prolog.PrologDouble;
import org.logicware.pdb.prolog.PrologFloat;
import org.logicware.pdb.prolog.PrologInteger;
import org.logicware.pdb.prolog.PrologList;
import org.logicware.pdb.prolog.PrologLong;
import org.logicware.pdb.prolog.PrologStructure;
import org.logicware.pdb.prolog.PrologTerm;
import org.logicware.pdb.prolog.PrologVariable;
import org.logicware.prolog.jpl7.JplInteger;

public class PrologFloatTest extends PrologBaseTest {

	private PrologFloat f;

	@Before
	public void setUp() throws Exception {
		f = provider.newFloat(3.14);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetArguments() {
		assertArrayEquals(new JplInteger[0], f.getArguments());
	}

	@Test
	public final void testGetPrologInteger() {
		assertEquals(provider.newInteger(3), f.getPrologInteger());
	}

	@Test
	public final void testGetPrologFloat() {
		assertEquals(provider.newFloat(3.14), f.getPrologFloat());
	}

	@Test
	public final void testGetPrologLong() {
		assertEquals(provider.newLong(3), f.getPrologLong());
	}

	@Test
	public final void testGetPrologDouble() {
		assertEquals(provider.newDouble(3.14F), f.getPrologDouble());
	}

	@Test
	public final void testGetLongValue() {
		assertEquals(3, f.getLongValue());
	}

	@Test
	public final void testGetDoubleValue() {
		assertEquals(3.14F, f.getDoubleValue(), 0);
	}

	@Test
	public final void testGetIntValue() {
		assertEquals(3, f.getIntValue());
	}

	@Test
	public final void testGetFloatValue() {
		assertEquals(3.14F, f.getFloatValue(), 0);
	}

	@Test(expected = IndicatorError.class)
	public final void testGetKey() {
		f.getIndicator();
	}

	@Test
	public final void testGetType() {
		assertEquals(FLOAT_TYPE, f.getType());
	}

	@Test
	public final void testIsAtom() {
		assertFalse(f.isAtom());
	}

	@Test
	public final void testIsNumber() {
		assertTrue(f.isNumber());
	}

	@Test
	public final void testIsFloat() {
		assertTrue(f.isFloat());
	}

	@Test
	public final void testIsInteger() {
		assertFalse(f.isInteger());
	}

	@Test
	public final void testIsVariable() {
		assertFalse(f.isVariable());
	}

	@Test
	public final void testIsList() {
		assertFalse(f.isList());
	}

	@Test
	public final void testIsStructure() {
		assertFalse(f.isStructure());
	}

	@Test
	public final void testIsNil() {
		assertFalse(f.isNil());
	}

	@Test
	public final void testIsEmptyList() {
		assertFalse(f.isEmptyList());
	}

	@Test
	public final void testIsExpression() {
		assertFalse(f.isEvaluable());
	}

	@Test(expected = ArityError.class)
	public final void testGetArity() {
		f.getArity();
	}

	@Test(expected = FunctorError.class)
	public final void testGetFunctor() {
		f.getFunctor();
	}

	@Test
	public final void testUntify() {

		// with atom
		PrologFloat fValue = provider.newFloat(36.47);
		PrologAtom atom = provider.newAtom("doe");
		assertFalse(fValue.unify(atom));

		// with integer
		PrologInteger iValue = provider.newInteger(28);
		assertFalse(fValue.unify(iValue));

		// with long
		PrologLong lValue = provider.newLong(28);
		assertFalse(fValue.unify(lValue));

		// with float
		PrologFloat fValue1 = provider.newFloat(100.98);
		// true because are equals
		assertTrue(fValue.unify(fValue));
		// false because are different
		assertFalse(fValue.unify(fValue1));

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		PrologDouble dValue1 = provider.newDouble(100.98);
		// true because are equals
		assertFalse(fValue.unify(dValue));
		// false because are different
		assertFalse(fValue.unify(dValue1));

		// with variable
		PrologVariable variable = provider.newVariable("X");
		// true. case float and variable
		assertTrue(fValue.unify(variable));

		// with predicate
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertFalse(fValue.unify(structure));

		// with list
		PrologList flattenedList = provider.parsePrologList("[a,b,c]");
		assertFalse(fValue.unify(flattenedList));

		// with expression
		PrologTerm expression = provider.parsePrologTerm("58+93*10");
		assertFalse(fValue.unify(expression));

	}

	@Test
	public final void testCompareTo() {

		// with atom
		PrologFloat fValue = provider.newFloat(36.47);
		PrologAtom atom = provider.newAtom("doe");
		assertEquals(fValue.compareTo(atom), -1);

		// with integer
		PrologInteger iValue = provider.newInteger(28);
		assertEquals(fValue.compareTo(iValue), 1);

		// with long
		PrologLong lValue = provider.newLong(28);
		assertEquals(fValue.compareTo(lValue), 1);

		// with float
		PrologFloat fValue1 = provider.newFloat(100.98);
		// true because are equals
		assertEquals(fValue.compareTo(fValue), 0);
		// false because are different
		assertEquals(fValue.compareTo(fValue1), -1);

		// with double
		PrologDouble dValue = provider.newDouble(36.47);
		PrologDouble dValue1 = provider.newDouble(100.98);
		// true because are equals
		assertEquals(fValue.compareTo(dValue), 1);
		// false because are different
		assertEquals(fValue.compareTo(dValue1), -1);

		// with variable
		PrologVariable variable = provider.newVariable("X");
		// true. case float and variable
		assertEquals(fValue.compareTo(variable), 1);

		// with predicate
		PrologStructure structure = provider.parsePrologStructure("some_predicate(a,b,c)");
		assertEquals(fValue.compareTo(structure), -1);

		// with list
		PrologList flattenedList = provider.parsePrologList("[a,b,c]");
		assertEquals(fValue.compareTo(flattenedList), -1);

		// with expression
		PrologTerm expression = provider.parsePrologTerm("58+93*10");
		assertEquals(fValue.compareTo(expression), -1);

	}

}
