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
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologClauses;
import org.logicware.prolog.PrologGoal;
import org.logicware.prolog.PrologProgram;
import org.logicware.prolog.jpl7.JplClauses;
import org.logicware.prolog.jpl7.JplProgram;

public class PrologProgramTest extends PrologBaseTest {

	private PrologProgram program = new JplProgram();
	private final PrologClause[] clauseArray = new PrologClause[50];
	private final PrologClauses[] clausesArray = new PrologClauses[18];

	@Before
	public void setUp() throws Exception {

		// parent relationship
		program.add(new SwiProlog7Clause(provider.newStructure(parent, pam, bob)));
		program.add(new SwiProlog7Clause(provider.newStructure(parent, tom, bob)));
		program.add(new SwiProlog7Clause(provider.newStructure(parent, tom, liz)));
		program.add(new SwiProlog7Clause(provider.newStructure(parent, bob, ann)));
		program.add(new SwiProlog7Clause(provider.newStructure(parent, bob, pat)));
		program.add(new SwiProlog7Clause(provider.newStructure(parent, pat, jim)));

		// female relationship
		program.add(new SwiProlog7Clause(provider.newStructure(female, pam)));
		program.add(new SwiProlog7Clause(provider.newStructure(female, liz)));
		program.add(new SwiProlog7Clause(provider.newStructure(female, ann)));
		program.add(new SwiProlog7Clause(provider.newStructure(female, pat)));

		// male relationship
		program.add(new SwiProlog7Clause(provider.newStructure(male, tom)));
		program.add(new SwiProlog7Clause(provider.newStructure(male, bob)));
		program.add(new SwiProlog7Clause(provider.newStructure(male, jim)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.add(new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		program.add(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.add(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.add(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));

		x = provider.newVariable("X", 0);
		program.add(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		program.add(new SwiProlog7Clause(provider.newStructure(different, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.add(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.add(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));

		// employee relationship
		program.add(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));
		program.add(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		program.add(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		program.add(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		program.add(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		program.add(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		program.add(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));

		// department relationship
		program.add(new SwiProlog7Clause(provider.newStructure(department, one, board)));
		program.add(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		program.add(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		program.add(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		program.add(new SwiProlog7Clause(provider.newStructure(department, five, administration)));

		// salary relationship
		program.add(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		program.add(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));

		// zoo relationships
		program.add(new SwiProlog7Clause(provider.newStructure("big", bear)));
		program.add(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		program.add(new SwiProlog7Clause(provider.newStructure("small", cat)));
		program.add(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		program.add(new SwiProlog7Clause(provider.newStructure("black", cat)));
		program.add(new SwiProlog7Clause(provider.newStructure("gray", elephant)));

		// dark rules
		z = provider.newVariable("Z", 0);
		program.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		z = provider.newVariable("Z", 0);
		program.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));

		//
		// Clause Array Creation
		//

		// parent relationship
		clauseArray[0] = new SwiProlog7Clause(provider.newStructure(parent, pam, bob));
		clauseArray[1] = new SwiProlog7Clause(provider.newStructure(parent, tom, bob));
		clauseArray[2] = new SwiProlog7Clause(provider.newStructure(parent, tom, liz));
		clauseArray[3] = new SwiProlog7Clause(provider.newStructure(parent, bob, ann));
		clauseArray[4] = new SwiProlog7Clause(provider.newStructure(parent, bob, pat));
		clauseArray[5] = new SwiProlog7Clause(provider.newStructure(parent, pat, jim));

		// female relationship
		clauseArray[6] = new SwiProlog7Clause(provider.newStructure(female, pam));
		clauseArray[7] = new SwiProlog7Clause(provider.newStructure(female, liz));
		clauseArray[8] = new SwiProlog7Clause(provider.newStructure(female, ann));
		clauseArray[9] = new SwiProlog7Clause(provider.newStructure(female, pat));

		// male relationship
		clauseArray[10] = new SwiProlog7Clause(provider.newStructure(male, tom));
		clauseArray[11] = new SwiProlog7Clause(provider.newStructure(male, bob));
		clauseArray[12] = new SwiProlog7Clause(provider.newStructure(male, jim));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		clauseArray[13] = new SwiProlog7Clause(provider.newStructure(offspring, x, y),
				provider.newStructure(parent, x, y));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		clauseArray[14] = new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		clauseArray[15] = new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		clauseArray[16] = new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y)))));

		x = provider.newVariable("X", 0);
		clauseArray[17] = new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail()));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		clauseArray[18] = new SwiProlog7Clause(provider.newStructure(different, x, y));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		clauseArray[19] = new SwiProlog7Clause(provider.newStructure(predecessor, x, z),
				provider.newStructure(parent, x, z));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		clauseArray[20] = new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z)));

		// employee relationship
		clauseArray[21] = new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five));
		clauseArray[22] = new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three));
		clauseArray[23] = new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two));
		clauseArray[24] = new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight));
		clauseArray[26] = new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven));
		clauseArray[27] = new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine));

		// department relationship
		clauseArray[28] = new SwiProlog7Clause(provider.newStructure(department, one, board));
		clauseArray[29] = new SwiProlog7Clause(provider.newStructure(department, two, human_resources));
		clauseArray[30] = new SwiProlog7Clause(provider.newStructure(department, three, production));
		clauseArray[31] = new SwiProlog7Clause(provider.newStructure(department, four, technical_services));
		clauseArray[32] = new SwiProlog7Clause(provider.newStructure(department, five, administration));

		// salary relationship
		clauseArray[33] = new SwiProlog7Clause(provider.newStructure(salary, one, thousand));
		clauseArray[34] = new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred));
		clauseArray[35] = new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand));
		clauseArray[36] = new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred));
		clauseArray[37] = new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand));
		clauseArray[38] = new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred));
		clauseArray[39] = new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand));
		clauseArray[40] = new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred));
		clauseArray[41] = new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand));

		// zoo relationships
		clauseArray[42] = new SwiProlog7Clause(provider.newStructure("big", bear));
		clauseArray[43] = new SwiProlog7Clause(provider.newStructure("big", elephant));
		clauseArray[44] = new SwiProlog7Clause(provider.newStructure("small", cat));
		clauseArray[45] = new SwiProlog7Clause(provider.newStructure("brown", bear));
		clauseArray[46] = new SwiProlog7Clause(provider.newStructure("black", cat));
		clauseArray[47] = new SwiProlog7Clause(provider.newStructure("gray", elephant));

		// dark rules
		z = provider.newVariable("Z", 0);
		clauseArray[48] = new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z));

		z = provider.newVariable("Z", 0);
		clauseArray[49] = new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z));

		//
		//
		//
		// parent relationship
		PrologClauses parents = new JplClauses("parent/2");
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));
		assertEquals(parents, program.get("parent/2"));

		// female relationship
		PrologClauses females = new JplClauses("female/1");
		females.add(new SwiProlog7Clause(provider.newStructure(female, pam)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, liz)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, ann)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, pat)));
		assertEquals(females, program.get("female/1"));

		// male relationship
		PrologClauses males = new JplClauses("male/1");
		males.add(new SwiProlog7Clause(provider.newStructure(male, tom)));
		males.add(new SwiProlog7Clause(provider.newStructure(male, bob)));
		males.add(new SwiProlog7Clause(provider.newStructure(male, jim)));
		assertEquals(males, program.get("male/1"));

		PrologClauses offsprings = new JplClauses("offspring/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		offsprings
				.add(new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));
		assertEquals(offsprings, program.get("offspring/2"));

		PrologClauses mothers = new JplClauses("mother/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		mothers.add(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));
		assertEquals(mothers, program.get("mother/2"));

		PrologClauses grandparents = new JplClauses("grandparent/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		grandparents.add(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));
		assertEquals(grandparents, program.get("grandparent/2"));

		PrologClauses sisters = new JplClauses("sister/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		sisters.add(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));
		assertEquals(sisters, program.get("sister/2"));

		PrologClauses differents = new JplClauses("different/2");
		x = provider.newVariable("X", 0);
		differents.add(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		differents.add(new SwiProlog7Clause(provider.newStructure(different, x, y)));
		assertEquals(differents, program.get("different/2"));

		PrologClauses predecessors = new JplClauses("predecessor/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		predecessors.add(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		predecessors.add(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));
		assertEquals(predecessors, program.get("predecessor/2"));

		// employee relationship
		PrologClauses employees = new JplClauses("employee/3");
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));
		assertEquals(employees, program.get("employee/3"));

		// department relationship
		PrologClauses departments = new JplClauses("department/2");
		departments.add(new SwiProlog7Clause(provider.newStructure(department, one, board)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, five, administration)));
		assertEquals(departments, program.get("department/2"));

		// salary relationship
		PrologClauses salaries = new JplClauses("salary/2");
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));
		assertEquals(salaries, program.get("salary/2"));

		// big relationships
		PrologClauses bigs = new JplClauses("big/1");
		bigs.add(new SwiProlog7Clause(provider.newStructure("big", bear)));
		bigs.add(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		assertEquals(bigs, program.get("big/1"));

		PrologClauses smalls = new JplClauses("small/1");
		smalls.add(new SwiProlog7Clause(provider.newStructure("small", cat)));
		assertEquals(smalls, program.get("small/1"));

		PrologClauses browns = new JplClauses("brown/1");
		browns.add(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		assertEquals(browns, program.get("brown/1"));

		PrologClauses blacks = new JplClauses("black/1");
		blacks.add(new SwiProlog7Clause(provider.newStructure("black", cat)));
		assertEquals(blacks, program.get("black/1"));

		PrologClauses grays = new JplClauses("gray/1");
		grays.add(new SwiProlog7Clause(provider.newStructure("gray", elephant)));
		assertEquals(grays, program.get("gray/1"));

		// dark rules
		PrologClauses darks = new JplClauses("dark/1");
		z = provider.newVariable("Z", 0);
		darks.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		z = provider.newVariable("Z", 0);
		darks.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));
		assertEquals(darks, program.get("dark/1"));

		clausesArray[0] = parents;
		clausesArray[1] = females;
		clausesArray[2] = males;
		clausesArray[3] = offsprings;
		clausesArray[4] = mothers;
		clausesArray[5] = grandparents;
		clausesArray[6] = sisters;
		clausesArray[7] = differents;
		clausesArray[8] = predecessors;
		clausesArray[9] = employees;
		clausesArray[10] = departments;
		clausesArray[11] = salaries;
		clausesArray[12] = bigs;
		clausesArray[13] = smalls;
		clausesArray[14] = browns;
		clausesArray[15] = blacks;
		clausesArray[16] = grays;
		clausesArray[17] = darks;

	}

	@After
	public void tearDown() throws Exception {
		program.clear();
	}

	@Test
	public final void testSize() {
		assertEquals(50, program.size());
	}

	@Test
	public final void testClear() {
		program.clear();
		assertEquals(0, program.size());
		assertTrue(program.isEmpty());
	}

	@Test
	public final void testGet() {

		// parent relationship
		PrologClauses parents = new JplClauses("parent/2");
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));
		assertEquals(parents, program.get("parent/2"));

		// female relationship
		PrologClauses females = new JplClauses("female/1");
		females.add(new SwiProlog7Clause(provider.newStructure(female, pam)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, liz)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, ann)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, pat)));
		assertEquals(females, program.get("female/1"));

		// male relationship
		PrologClauses males = new JplClauses("male/1");
		males.add(new SwiProlog7Clause(provider.newStructure(male, tom)));
		males.add(new SwiProlog7Clause(provider.newStructure(male, bob)));
		males.add(new SwiProlog7Clause(provider.newStructure(male, jim)));
		assertEquals(males, program.get("male/1"));

		PrologClauses offsprings = new JplClauses("offspring/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		offsprings
				.add(new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));
		assertEquals(offsprings, program.get("offspring/2"));

		PrologClauses mothers = new JplClauses("mother/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		mothers.add(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));
		assertEquals(mothers, program.get("mother/2"));

		PrologClauses grandparents = new JplClauses("grandparent/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		grandparents.add(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));
		assertEquals(grandparents, program.get("grandparent/2"));

		PrologClauses sisters = new JplClauses("sister/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		sisters.add(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));
		assertEquals(sisters, program.get("sister/2"));

		PrologClauses differents = new JplClauses("different/2");
		x = provider.newVariable("X", 0);
		differents.add(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		differents.add(new SwiProlog7Clause(provider.newStructure(different, x, y)));
		assertEquals(differents, program.get("different/2"));

		PrologClauses predecessors = new JplClauses("predecessor/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		predecessors.add(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		predecessors.add(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));
		assertEquals(predecessors, program.get("predecessor/2"));

		// employee relationship
		PrologClauses employees = new JplClauses("employee/3");
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));
		assertEquals(employees, program.get("employee/3"));

		// department relationship
		PrologClauses departments = new JplClauses("department/2");
		departments.add(new SwiProlog7Clause(provider.newStructure(department, one, board)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, five, administration)));
		assertEquals(departments, program.get("department/2"));

		// salary relationship
		PrologClauses salaries = new JplClauses("salary/2");
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));
		assertEquals(salaries, program.get("salary/2"));

		// big relationships
		PrologClauses bigs = new JplClauses("big/1");
		bigs.add(new SwiProlog7Clause(provider.newStructure("big", bear)));
		bigs.add(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		assertEquals(bigs, program.get("big/1"));

		PrologClauses smalls = new JplClauses("small/1");
		smalls.add(new SwiProlog7Clause(provider.newStructure("small", cat)));
		assertEquals(smalls, program.get("small/1"));

		PrologClauses browns = new JplClauses("brown/1");
		browns.add(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		assertEquals(browns, program.get("brown/1"));

		PrologClauses blacks = new JplClauses("black/1");
		blacks.add(new SwiProlog7Clause(provider.newStructure("black", cat)));
		assertEquals(blacks, program.get("black/1"));

		PrologClauses grays = new JplClauses("gray/1");
		grays.add(new SwiProlog7Clause(provider.newStructure("gray", elephant)));
		assertEquals(grays, program.get("gray/1"));

		// dark rules
		PrologClauses darks = new JplClauses("dark/1");
		z = provider.newVariable("Z", 0);
		darks.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		z = provider.newVariable("Z", 0);
		darks.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));
		assertEquals(darks, program.get("dark/1"));

	}

	@Test
	public final void testAddIPrologClause() {

		PrologProgram otherProgram = new JplProgram();
		assertEquals(0, otherProgram.size());
		assertTrue(otherProgram.isEmpty());

		// parent relationship
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(parent, pam, bob)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(parent, tom, bob)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(parent, tom, liz)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(parent, bob, ann)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(parent, bob, pat)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(parent, pat, jim)));

		// female relationship
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(female, pam)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(female, liz)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(female, ann)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(female, pat)));

		// male relationship
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(male, tom)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(male, bob)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(male, jim)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram
				.add(new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));

		x = provider.newVariable("X", 0);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(different, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.add(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));

		// employee relationship
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));

		// department relationship
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(department, one, board)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(department, five, administration)));

		// salary relationship
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));

		// zoo relationships
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("big", bear)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("small", cat)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("black", cat)));
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("gray", elephant)));

		// dark rules
		z = provider.newVariable("Z", 0);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		z = provider.newVariable("Z", 0);
		otherProgram.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));

		assertEquals(program, otherProgram);
		assertEquals(50, otherProgram.size());
		assertFalse(otherProgram.isEmpty());

	}

	@Test
	public final void testAddIPrologClauses() {

		// parent relationship
		PrologClauses parents = new JplClauses("parent/2");
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));
		assertEquals(parents, program.get("parent/2"));

		// female relationship
		PrologClauses females = new JplClauses("female/1");
		females.add(new SwiProlog7Clause(provider.newStructure(female, pam)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, liz)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, ann)));
		females.add(new SwiProlog7Clause(provider.newStructure(female, pat)));
		assertEquals(females, program.get("female/1"));

		// male relationship
		PrologClauses males = new JplClauses("male/1");
		males.add(new SwiProlog7Clause(provider.newStructure(male, tom)));
		males.add(new SwiProlog7Clause(provider.newStructure(male, bob)));
		males.add(new SwiProlog7Clause(provider.newStructure(male, jim)));
		assertEquals(males, program.get("male/1"));

		PrologClauses offsprings = new JplClauses("offspring/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		offsprings
				.add(new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));
		assertEquals(offsprings, program.get("offspring/2"));

		PrologClauses mothers = new JplClauses("mother/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		mothers.add(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));
		assertEquals(mothers, program.get("mother/2"));

		PrologClauses grandparents = new JplClauses("grandparent/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		grandparents.add(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));
		assertEquals(grandparents, program.get("grandparent/2"));

		PrologClauses sisters = new JplClauses("sister/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		sisters.add(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));
		assertEquals(sisters, program.get("sister/2"));

		PrologClauses differents = new JplClauses("different/2");
		x = provider.newVariable("X", 0);
		differents.add(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		differents.add(new SwiProlog7Clause(provider.newStructure(different, x, y)));
		assertEquals(differents, program.get("different/2"));

		PrologClauses predecessors = new JplClauses("predecessor/2");
		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		predecessors.add(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		predecessors.add(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));
		assertEquals(predecessors, program.get("predecessor/2"));

		// employee relationship
		PrologClauses employees = new JplClauses("employee/3");
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		employees.add(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));
		assertEquals(employees, program.get("employee/3"));

		// department relationship
		PrologClauses departments = new JplClauses("department/2");
		departments.add(new SwiProlog7Clause(provider.newStructure(department, one, board)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		departments.add(new SwiProlog7Clause(provider.newStructure(department, five, administration)));
		assertEquals(departments, program.get("department/2"));

		// salary relationship
		PrologClauses salaries = new JplClauses("salary/2");
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		salaries.add(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));
		assertEquals(salaries, program.get("salary/2"));

		// big relationships
		PrologClauses bigs = new JplClauses("big/1");
		bigs.add(new SwiProlog7Clause(provider.newStructure("big", bear)));
		bigs.add(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		assertEquals(bigs, program.get("big/1"));

		PrologClauses smalls = new JplClauses("small/1");
		smalls.add(new SwiProlog7Clause(provider.newStructure("small", cat)));
		assertEquals(smalls, program.get("small/1"));

		PrologClauses browns = new JplClauses("brown/1");
		browns.add(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		assertEquals(browns, program.get("brown/1"));

		PrologClauses blacks = new JplClauses("black/1");
		blacks.add(new SwiProlog7Clause(provider.newStructure("black", cat)));
		assertEquals(blacks, program.get("black/1"));

		PrologClauses grays = new JplClauses("gray/1");
		grays.add(new SwiProlog7Clause(provider.newStructure("gray", elephant)));
		assertEquals(grays, program.get("gray/1"));

		// dark rules
		PrologClauses darks = new JplClauses("dark/1");
		z = provider.newVariable("Z", 0);
		darks.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		z = provider.newVariable("Z", 0);
		darks.add(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));
		assertEquals(darks, program.get("dark/1"));

		PrologProgram otherProgram = new JplProgram();
		otherProgram.add(parents);
		otherProgram.add(females);
		otherProgram.add(males);
		otherProgram.add(offsprings);
		otherProgram.add(mothers);
		otherProgram.add(grandparents);
		otherProgram.add(sisters);
		otherProgram.add(differents);
		otherProgram.add(predecessors);
		otherProgram.add(employees);
		otherProgram.add(departments);
		otherProgram.add(salaries);
		otherProgram.add(bigs);
		otherProgram.add(smalls);
		otherProgram.add(browns);
		otherProgram.add(blacks);
		otherProgram.add(grays);
		otherProgram.add(darks);

		assertEquals(50, otherProgram.size());
		assertFalse(otherProgram.isEmpty());

	}

	@Test
	public final void testAddIPrologProgram() {

		PrologProgram otherProgram = new JplProgram();
		assertEquals(0, otherProgram.size());
		assertTrue(otherProgram.isEmpty());

		otherProgram.add(program);

		assertEquals(program, otherProgram);
		assertEquals(50, otherProgram.size());
		assertFalse(otherProgram.isEmpty());

	}

	@Test
	public final void testPush() {

		PrologProgram otherProgram = new JplProgram();

		otherProgram.push(new SwiProlog7Clause(provider.newStructure(parent, pat, jim)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(parent, bob, pat)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(parent, bob, ann)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(parent, tom, liz)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(parent, tom, bob)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(parent, pam, bob)));

		otherProgram.push(new SwiProlog7Clause(provider.newStructure(female, pat)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(female, ann)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(female, liz)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(female, pam)));

		otherProgram.push(new SwiProlog7Clause(provider.newStructure(male, jim)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(male, bob)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(male, tom)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.push(
				new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(different, x, y)));

		x = provider.newVariable("X", 0);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		otherProgram.push(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));

		otherProgram.push(new SwiProlog7Clause(provider.newStructure(department, five, administration)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(department, one, board)));

		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));

		otherProgram.push(new SwiProlog7Clause(provider.newStructure("gray", elephant)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("black", cat)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("small", cat)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("big", bear)));

		z = provider.newVariable("Z", 0);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));

		z = provider.newVariable("Z", 0);
		otherProgram.push(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		assertEquals(program, otherProgram);
		assertEquals(50, program.size());
		assertFalse(program.isEmpty());

	}

	@Test
	public final void testRemoveAllString() {

		// parent relationship
		program.removeAll("parent/2");
		assertEquals(44, program.size());

		// female relationship
		program.removeAll("female/1");
		assertEquals(40, program.size());

		// male relationship
		program.removeAll("male/1");
		assertEquals(37, program.size());

		program.removeAll("offspring/2");
		assertEquals(36, program.size());

		program.removeAll("mother/2");
		assertEquals(35, program.size());

		program.removeAll("grandparent/2");
		assertEquals(34, program.size());

		program.removeAll("sister/2");
		assertEquals(33, program.size());

		program.removeAll("different/2");
		assertEquals(31, program.size());

		program.removeAll("predecessor/2");
		assertEquals(29, program.size());

		// employee relationship
		program.removeAll("employee/3");
		assertEquals(22, program.size());

		// department relationship
		program.removeAll("department/2");
		assertEquals(17, program.size());

		// salary relationship
		program.removeAll("salary/2");
		assertEquals(8, program.size());

		// big relationships
		program.removeAll("big/1");
		assertEquals(6, program.size());

		program.removeAll("small/1");
		assertEquals(5, program.size());

		program.removeAll("brown/1");
		assertEquals(4, program.size());

		program.removeAll("black/1");
		assertEquals(3, program.size());

		program.removeAll("gray/1");
		assertEquals(2, program.size());

		// dark rules
		program.removeAll("dark/1");
		assertEquals(0, program.size());
		assertTrue(program.isEmpty());

	}

	@Test
	public final void testRemoveAllStringInt() {

		// parent relationship
		program.removeAll("parent", 2);
		assertEquals(44, program.size());

		// female relationship
		program.removeAll("female", 1);
		assertEquals(40, program.size());

		// male relationship
		program.removeAll("male", 1);
		assertEquals(37, program.size());

		program.removeAll("offspring", 2);
		assertEquals(36, program.size());

		program.removeAll("mother", 2);
		assertEquals(35, program.size());

		program.removeAll("grandparent", 2);
		assertEquals(34, program.size());

		program.removeAll("sister", 2);
		assertEquals(33, program.size());

		program.removeAll("different", 2);
		assertEquals(31, program.size());

		program.removeAll("predecessor", 2);
		assertEquals(29, program.size());

		// employee relationship
		program.removeAll("employee", 3);
		assertEquals(22, program.size());

		// department relationship
		program.removeAll("department", 2);
		assertEquals(17, program.size());

		// salary relationship
		program.removeAll("salary", 2);
		assertEquals(8, program.size());

		// big relationships
		program.removeAll("big", 1);
		assertEquals(6, program.size());

		program.removeAll("small", 1);
		assertEquals(5, program.size());

		program.removeAll("brown", 1);
		assertEquals(4, program.size());

		program.removeAll("black", 1);
		assertEquals(3, program.size());

		program.removeAll("gray", 1);
		assertEquals(2, program.size());

		// dark rules
		program.removeAll("dark", 1);
		assertEquals(0, program.size());
		assertTrue(program.isEmpty());

	}

	@Test
	public final void testGetDirectives() {

		assertEquals(Arrays.asList(), program.getDirectives());
		assertEquals(0, program.getDirectives().size());
		assertTrue(program.getDirectives().isEmpty());

	}

//	@Test
//	public final void testAddDirective() {
//
//		assertEquals(Arrays.asList(), program.getDirectives());
//		assertEquals(0, program.getDirectives().size());
//		assertTrue(program.getDirectives().isEmpty());
//
//		PrologGoal include = new ZPrologGoal(provider.parseTerm("a('./suite')"));
//		PrologGoal ensure_loaded = new ZPrologGoal(provider.parseTerm("b('./suite')"));
//		PrologGoal initialization = new ZPrologGoal(provider.parseTerm("c(rsuite)"));
//
//		List<PrologGoal> directives = Arrays.asList(include, ensure_loaded, initialization);
//
//		program.addDirective(include);
//		program.addDirective(ensure_loaded);
//		program.addDirective(initialization);
//
//		assertEquals(directives, program.getDirectives());
//		assertEquals(3, program.getDirectives().size());
//		assertFalse(program.getDirectives().isEmpty());
//
//		System.out.println(program);
//
//	}

//	@Test
//	public final void testRemoveDirective() {
//
//		assertEquals(Arrays.asList(), program.getDirectives());
//		assertEquals(0, program.getDirectives().size());
//		assertTrue(program.getDirectives().isEmpty());
//
//		PrologGoal include = new ZPrologGoal(provider.parseTerm("include('./suite')"));
//		PrologGoal ensure_loaded = new ZPrologGoal(provider.parseTerm("ensure_loaded('./suite')"));
//		PrologGoal initialization = new ZPrologGoal(provider.parseTerm("initialization('rsuite')"));
//
//		List<PrologGoal> directives = Arrays.asList(include, ensure_loaded, initialization);
//
//		program.addDirective(include);
//		program.addDirective(ensure_loaded);
//		program.addDirective(initialization);
//
//		assertEquals(directives, program.getDirectives());
//		assertEquals(3, program.getDirectives().size());
//		assertFalse(program.getDirectives().isEmpty());
//
//		program.removeDirective(include);
//		program.removeDirective(ensure_loaded);
//		program.removeDirective(initialization);
//
//		assertEquals(Arrays.asList(), program.getDirectives());
//		assertEquals(0, program.getDirectives().size());
//		assertTrue(program.getDirectives().isEmpty());
//
//	}

	@Test
	public final void testGetGoals() {

		assertEquals(Arrays.asList(), program.getGoals());
		assertEquals(0, program.getGoals().size());
		assertTrue(program.getGoals().isEmpty());

	}

//	@Test
//	public final void testAddGoal() {
//
//		assertEquals(Arrays.asList(), program.getGoals());
//		assertEquals(0, program.getGoals().size());
//		assertTrue(program.getGoals().isEmpty());
//
//		PrologGoal dark = new ZPrologGoal(provider.parseTerm("dark(X)"));
//		PrologGoal grandparent = new ZPrologGoal(provider.parseTerm("grandparent(X,Y)"));
//		PrologGoal employee = new ZPrologGoal(provider.parseTerm("employee(X,Y,_)"));
//
//		List<PrologGoal> goals = Arrays.asList(dark, grandparent, employee);
//
//		program.addGoal(dark);
//		program.addGoal(grandparent);
//		program.addGoal(employee);
//
//		assertEquals(goals, program.getGoals());
//		assertEquals(3, program.getGoals().size());
//		assertFalse(program.getGoals().isEmpty());
//
//	}

//	@Test
//	public final void testRemoveGoal() {
//
//		assertEquals(Arrays.asList(), program.getGoals());
//		assertEquals(0, program.getGoals().size());
//		assertTrue(program.getGoals().isEmpty());
//
//		PrologGoal dark = new ZPrologGoal(provider.parseTerm("dark(X)"));
//		PrologGoal grandparent = new ZPrologGoal(provider.parseTerm("grandparent(X,Y)"));
//		PrologGoal employee = new ZPrologGoal(provider.parseTerm("employee(X,Y,_)"));
//
//		List<PrologGoal> goals = Arrays.asList(dark, grandparent, employee);
//
//		program.addGoal(dark);
//		program.addGoal(grandparent);
//		program.addGoal(employee);
//
//		assertEquals(goals, program.getGoals());
//		assertEquals(3, program.getGoals().size());
//		assertFalse(program.getGoals().isEmpty());
//
//		program.removeGoal(dark);
//		program.removeGoal(grandparent);
//		program.removeGoal(employee);
//
//		assertEquals(Arrays.asList(), program.getGoals());
//		assertEquals(0, program.getGoals().size());
//		assertTrue(program.getGoals().isEmpty());
//
//	}

	@Test
	public final void testIsDynamic() {

		// family
		assertFalse(program.isDynamic(male, 1));
		assertFalse(program.isDynamic(parent, 2));
		assertFalse(program.isDynamic(female, 1));
		assertFalse(program.isDynamic(offspring, 2));
		assertFalse(program.isDynamic(mother, 2));
		assertFalse(program.isDynamic(grandparent, 2));
		assertFalse(program.isDynamic(different, 2));
		assertFalse(program.isDynamic(predecessor, 2));

		// company
		assertFalse(program.isDynamic(employee, 3));
		assertFalse(program.isDynamic(department, 2));
		assertFalse(program.isDynamic(salary, 2));

		// zoo
		assertFalse(program.isDynamic("big", 1));
		assertFalse(program.isDynamic("small", 1));
		assertFalse(program.isDynamic("brown", 1));
		assertFalse(program.isDynamic("black", 1));
		assertFalse(program.isDynamic("gray", 1));
		assertFalse(program.isDynamic("dark", 1));

	}

	@Test
	public final void testMarkDynamic() {

		// family
		assertFalse(program.isDynamic(male, 1));
		assertFalse(program.isDynamic(parent, 2));
		assertFalse(program.isDynamic(female, 1));
		assertFalse(program.isDynamic(offspring, 2));
		assertFalse(program.isDynamic(mother, 2));
		assertFalse(program.isDynamic(grandparent, 2));
		assertFalse(program.isDynamic(different, 2));
		assertFalse(program.isDynamic(predecessor, 2));

		// company
		assertFalse(program.isDynamic(employee, 3));
		assertFalse(program.isDynamic(department, 2));
		assertFalse(program.isDynamic(salary, 2));

		// zoo
		assertFalse(program.isDynamic("big", 1));
		assertFalse(program.isDynamic("small", 1));
		assertFalse(program.isDynamic("brown", 1));
		assertFalse(program.isDynamic("black", 1));
		assertFalse(program.isDynamic("gray", 1));
		assertFalse(program.isDynamic("dark", 1));

		// mark dynamic
		program.markDynamic(male, 1);
		program.markDynamic(parent, 2);
		program.markDynamic(female, 1);
		program.markDynamic(offspring, 2);
		program.markDynamic(mother, 2);
		program.markDynamic(grandparent, 2);
		program.markDynamic(different, 2);
		program.markDynamic(predecessor, 2);

		// company
		program.markDynamic(employee, 3);
		program.markDynamic(department, 2);
		program.markDynamic(salary, 2);

		// zoo
		program.markDynamic("big", 1);
		program.markDynamic("small", 1);
		program.markDynamic("brown", 1);
		program.markDynamic("black", 1);
		program.markDynamic("gray", 1);
		program.markDynamic("dark", 1);

		// family
		assertTrue(program.isDynamic(male, 1));
		assertTrue(program.isDynamic(parent, 2));
		assertTrue(program.isDynamic(female, 1));
		assertTrue(program.isDynamic(offspring, 2));
		assertTrue(program.isDynamic(mother, 2));
		assertTrue(program.isDynamic(grandparent, 2));
		assertTrue(program.isDynamic(different, 2));
		assertTrue(program.isDynamic(predecessor, 2));

		// company
		assertTrue(program.isDynamic(employee, 3));
		assertTrue(program.isDynamic(department, 2));
		assertTrue(program.isDynamic(salary, 2));

		// zoo
		assertTrue(program.isDynamic("big", 1));
		assertTrue(program.isDynamic("small", 1));
		assertTrue(program.isDynamic("brown", 1));
		assertTrue(program.isDynamic("black", 1));
		assertTrue(program.isDynamic("gray", 1));
		assertTrue(program.isDynamic("dark", 1));

	}

	@Test
	public final void testUnmarkDynamic() {

		// family
		assertFalse(program.isDynamic(male, 1));
		assertFalse(program.isDynamic(parent, 2));
		assertFalse(program.isDynamic(female, 1));
		assertFalse(program.isDynamic(offspring, 2));
		assertFalse(program.isDynamic(mother, 2));
		assertFalse(program.isDynamic(grandparent, 2));
		assertFalse(program.isDynamic(different, 2));
		assertFalse(program.isDynamic(predecessor, 2));

		// company
		assertFalse(program.isDynamic(employee, 3));
		assertFalse(program.isDynamic(department, 2));
		assertFalse(program.isDynamic(salary, 2));

		// zoo
		assertFalse(program.isDynamic("big", 1));
		assertFalse(program.isDynamic("small", 1));
		assertFalse(program.isDynamic("brown", 1));
		assertFalse(program.isDynamic("black", 1));
		assertFalse(program.isDynamic("gray", 1));
		assertFalse(program.isDynamic("dark", 1));

		// mark dynamic
		program.markDynamic(male, 1);
		program.markDynamic(parent, 2);
		program.markDynamic(female, 1);
		program.markDynamic(offspring, 2);
		program.markDynamic(mother, 2);
		program.markDynamic(grandparent, 2);
		program.markDynamic(different, 2);
		program.markDynamic(predecessor, 2);

		// company
		program.markDynamic(employee, 3);
		program.markDynamic(department, 2);
		program.markDynamic(salary, 2);

		// zoo
		program.markDynamic("big", 1);
		program.markDynamic("small", 1);
		program.markDynamic("brown", 1);
		program.markDynamic("black", 1);
		program.markDynamic("gray", 1);
		program.markDynamic("dark", 1);

		// family
		assertTrue(program.isDynamic(male, 1));
		assertTrue(program.isDynamic(parent, 2));
		assertTrue(program.isDynamic(female, 1));
		assertTrue(program.isDynamic(offspring, 2));
		assertTrue(program.isDynamic(mother, 2));
		assertTrue(program.isDynamic(grandparent, 2));
		assertTrue(program.isDynamic(different, 2));
		assertTrue(program.isDynamic(predecessor, 2));

		// company
		assertTrue(program.isDynamic(employee, 3));
		assertTrue(program.isDynamic(department, 2));
		assertTrue(program.isDynamic(salary, 2));

		// zoo
		assertTrue(program.isDynamic("big", 1));
		assertTrue(program.isDynamic("small", 1));
		assertTrue(program.isDynamic("brown", 1));
		assertTrue(program.isDynamic("black", 1));
		assertTrue(program.isDynamic("gray", 1));
		assertTrue(program.isDynamic("dark", 1));

		// un-mark dynamic
		program.unmarkDynamic(male, 1);
		program.unmarkDynamic(parent, 2);
		program.unmarkDynamic(female, 1);
		program.unmarkDynamic(offspring, 2);
		program.unmarkDynamic(mother, 2);
		program.unmarkDynamic(grandparent, 2);
		program.unmarkDynamic(different, 2);
		program.unmarkDynamic(predecessor, 2);

		// company
		program.unmarkDynamic(employee, 3);
		program.unmarkDynamic(department, 2);
		program.unmarkDynamic(salary, 2);

		// zoo
		program.unmarkDynamic("big", 1);
		program.unmarkDynamic("small", 1);
		program.unmarkDynamic("brown", 1);
		program.unmarkDynamic("black", 1);
		program.unmarkDynamic("gray", 1);
		program.unmarkDynamic("dark", 1);

		// family
		assertFalse(program.isDynamic(male, 1));
		assertFalse(program.isDynamic(parent, 2));
		assertFalse(program.isDynamic(female, 1));
		assertFalse(program.isDynamic(offspring, 2));
		assertFalse(program.isDynamic(mother, 2));
		assertFalse(program.isDynamic(grandparent, 2));
		assertFalse(program.isDynamic(different, 2));
		assertFalse(program.isDynamic(predecessor, 2));

		// company
		assertFalse(program.isDynamic(employee, 3));
		assertFalse(program.isDynamic(department, 2));
		assertFalse(program.isDynamic(salary, 2));

		// zoo
		assertFalse(program.isDynamic("big", 1));
		assertFalse(program.isDynamic("small", 1));
		assertFalse(program.isDynamic("brown", 1));
		assertFalse(program.isDynamic("black", 1));
		assertFalse(program.isDynamic("gray", 1));
		assertFalse(program.isDynamic("dark", 1));

	}

	@Test
	public final void testIsMultifile() {

		// family
		assertFalse(program.isMultifile(male, 1));
		assertFalse(program.isMultifile(parent, 2));
		assertFalse(program.isMultifile(female, 1));
		assertFalse(program.isMultifile(offspring, 2));
		assertFalse(program.isMultifile(mother, 2));
		assertFalse(program.isMultifile(grandparent, 2));
		assertFalse(program.isMultifile(different, 2));
		assertFalse(program.isMultifile(predecessor, 2));

		// company
		assertFalse(program.isMultifile(employee, 3));
		assertFalse(program.isMultifile(department, 2));
		assertFalse(program.isMultifile(salary, 2));

		// zoo
		assertFalse(program.isMultifile("big", 1));
		assertFalse(program.isMultifile("small", 1));
		assertFalse(program.isMultifile("brown", 1));
		assertFalse(program.isMultifile("black", 1));
		assertFalse(program.isMultifile("gray", 1));
		assertFalse(program.isMultifile("dark", 1));
	}

	@Test
	public final void testMarkMultifile() {

		// family
		assertFalse(program.isMultifile(male, 1));
		assertFalse(program.isMultifile(parent, 2));
		assertFalse(program.isMultifile(female, 1));
		assertFalse(program.isMultifile(offspring, 2));
		assertFalse(program.isMultifile(mother, 2));
		assertFalse(program.isMultifile(grandparent, 2));
		assertFalse(program.isMultifile(different, 2));
		assertFalse(program.isMultifile(predecessor, 2));

		// company
		assertFalse(program.isMultifile(employee, 3));
		assertFalse(program.isMultifile(department, 2));
		assertFalse(program.isMultifile(salary, 2));

		// zoo
		assertFalse(program.isMultifile("big", 1));
		assertFalse(program.isMultifile("small", 1));
		assertFalse(program.isMultifile("brown", 1));
		assertFalse(program.isMultifile("black", 1));
		assertFalse(program.isMultifile("gray", 1));
		assertFalse(program.isMultifile("dark", 1));

		// mark dynamic
		program.markMultifile(male, 1);
		program.markMultifile(parent, 2);
		program.markMultifile(female, 1);
		program.markMultifile(offspring, 2);
		program.markMultifile(mother, 2);
		program.markMultifile(grandparent, 2);
		program.markMultifile(different, 2);
		program.markMultifile(predecessor, 2);

		// company
		program.markMultifile(employee, 3);
		program.markMultifile(department, 2);
		program.markMultifile(salary, 2);

		// zoo
		program.markMultifile("big", 1);
		program.markMultifile("small", 1);
		program.markMultifile("brown", 1);
		program.markMultifile("black", 1);
		program.markMultifile("gray", 1);
		program.markMultifile("dark", 1);

		// family
		assertTrue(program.isMultifile(male, 1));
		assertTrue(program.isMultifile(parent, 2));
		assertTrue(program.isMultifile(female, 1));
		assertTrue(program.isMultifile(offspring, 2));
		assertTrue(program.isMultifile(mother, 2));
		assertTrue(program.isMultifile(grandparent, 2));
		assertTrue(program.isMultifile(different, 2));
		assertTrue(program.isMultifile(predecessor, 2));

		// company
		assertTrue(program.isMultifile(employee, 3));
		assertTrue(program.isMultifile(department, 2));
		assertTrue(program.isMultifile(salary, 2));

		// zoo
		assertTrue(program.isMultifile("big", 1));
		assertTrue(program.isMultifile("small", 1));
		assertTrue(program.isMultifile("brown", 1));
		assertTrue(program.isMultifile("black", 1));
		assertTrue(program.isMultifile("gray", 1));
		assertTrue(program.isMultifile("dark", 1));

	}

	@Test
	public final void testUnmarkMultifile() {

		// family
		assertFalse(program.isMultifile(male, 1));
		assertFalse(program.isMultifile(parent, 2));
		assertFalse(program.isMultifile(female, 1));
		assertFalse(program.isMultifile(offspring, 2));
		assertFalse(program.isMultifile(mother, 2));
		assertFalse(program.isMultifile(grandparent, 2));
		assertFalse(program.isMultifile(different, 2));
		assertFalse(program.isMultifile(predecessor, 2));

		// company
		assertFalse(program.isMultifile(employee, 3));
		assertFalse(program.isMultifile(department, 2));
		assertFalse(program.isMultifile(salary, 2));

		// zoo
		assertFalse(program.isMultifile("big", 1));
		assertFalse(program.isMultifile("small", 1));
		assertFalse(program.isMultifile("brown", 1));
		assertFalse(program.isMultifile("black", 1));
		assertFalse(program.isMultifile("gray", 1));
		assertFalse(program.isMultifile("dark", 1));

		// mark Multifile
		program.markMultifile(male, 1);
		program.markMultifile(parent, 2);
		program.markMultifile(female, 1);
		program.markMultifile(offspring, 2);
		program.markMultifile(mother, 2);
		program.markMultifile(grandparent, 2);
		program.markMultifile(different, 2);
		program.markMultifile(predecessor, 2);

		// company
		program.markMultifile(employee, 3);
		program.markMultifile(department, 2);
		program.markMultifile(salary, 2);

		// zoo
		program.markMultifile("big", 1);
		program.markMultifile("small", 1);
		program.markMultifile("brown", 1);
		program.markMultifile("black", 1);
		program.markMultifile("gray", 1);
		program.markMultifile("dark", 1);

		// family
		assertTrue(program.isMultifile(male, 1));
		assertTrue(program.isMultifile(parent, 2));
		assertTrue(program.isMultifile(female, 1));
		assertTrue(program.isMultifile(offspring, 2));
		assertTrue(program.isMultifile(mother, 2));
		assertTrue(program.isMultifile(grandparent, 2));
		assertTrue(program.isMultifile(different, 2));
		assertTrue(program.isMultifile(predecessor, 2));

		// company
		assertTrue(program.isMultifile(employee, 3));
		assertTrue(program.isMultifile(department, 2));
		assertTrue(program.isMultifile(salary, 2));

		// zoo
		assertTrue(program.isMultifile("big", 1));
		assertTrue(program.isMultifile("small", 1));
		assertTrue(program.isMultifile("brown", 1));
		assertTrue(program.isMultifile("black", 1));
		assertTrue(program.isMultifile("gray", 1));
		assertTrue(program.isMultifile("dark", 1));

		// un-mark Multifile
		program.unmarkMultifile(male, 1);
		program.unmarkMultifile(parent, 2);
		program.unmarkMultifile(female, 1);
		program.unmarkMultifile(offspring, 2);
		program.unmarkMultifile(mother, 2);
		program.unmarkMultifile(grandparent, 2);
		program.unmarkMultifile(different, 2);
		program.unmarkMultifile(predecessor, 2);

		// company
		program.unmarkMultifile(employee, 3);
		program.unmarkMultifile(department, 2);
		program.unmarkMultifile(salary, 2);

		// zoo
		program.unmarkMultifile("big", 1);
		program.unmarkMultifile("small", 1);
		program.unmarkMultifile("brown", 1);
		program.unmarkMultifile("black", 1);
		program.unmarkMultifile("gray", 1);
		program.unmarkMultifile("dark", 1);

		// family
		assertFalse(program.isMultifile(male, 1));
		assertFalse(program.isMultifile(parent, 2));
		assertFalse(program.isMultifile(female, 1));
		assertFalse(program.isMultifile(offspring, 2));
		assertFalse(program.isMultifile(mother, 2));
		assertFalse(program.isMultifile(grandparent, 2));
		assertFalse(program.isMultifile(different, 2));
		assertFalse(program.isMultifile(predecessor, 2));

		// company
		assertFalse(program.isMultifile(employee, 3));
		assertFalse(program.isMultifile(department, 2));
		assertFalse(program.isMultifile(salary, 2));

		// zoo
		assertFalse(program.isMultifile("big", 1));
		assertFalse(program.isMultifile("small", 1));
		assertFalse(program.isMultifile("brown", 1));
		assertFalse(program.isMultifile("black", 1));
		assertFalse(program.isMultifile("gray", 1));
		assertFalse(program.isMultifile("dark", 1));

	}

	@Test
	public final void testIsDiscontiguous() {

		// family
		assertFalse(program.isDiscontiguous(male, 1));
		assertFalse(program.isDiscontiguous(parent, 2));
		assertFalse(program.isDiscontiguous(female, 1));
		assertFalse(program.isDiscontiguous(offspring, 2));
		assertFalse(program.isDiscontiguous(mother, 2));
		assertFalse(program.isDiscontiguous(grandparent, 2));
		assertFalse(program.isDiscontiguous(different, 2));
		assertFalse(program.isDiscontiguous(predecessor, 2));

		// company
		assertFalse(program.isDiscontiguous(employee, 3));
		assertFalse(program.isDiscontiguous(department, 2));
		assertFalse(program.isDiscontiguous(salary, 2));

		// zoo
		assertFalse(program.isDiscontiguous("big", 1));
		assertFalse(program.isDiscontiguous("small", 1));
		assertFalse(program.isDiscontiguous("brown", 1));
		assertFalse(program.isDiscontiguous("black", 1));
		assertFalse(program.isDiscontiguous("gray", 1));
		assertFalse(program.isDiscontiguous("dark", 1));

	}

	@Test
	public final void testMarkDiscontiguous() {

		// family
		assertFalse(program.isDiscontiguous(male, 1));
		assertFalse(program.isDiscontiguous(parent, 2));
		assertFalse(program.isDiscontiguous(female, 1));
		assertFalse(program.isDiscontiguous(offspring, 2));
		assertFalse(program.isDiscontiguous(mother, 2));
		assertFalse(program.isDiscontiguous(grandparent, 2));
		assertFalse(program.isDiscontiguous(different, 2));
		assertFalse(program.isDiscontiguous(predecessor, 2));

		// company
		assertFalse(program.isDiscontiguous(employee, 3));
		assertFalse(program.isDiscontiguous(department, 2));
		assertFalse(program.isDiscontiguous(salary, 2));

		// zoo
		assertFalse(program.isDiscontiguous("big", 1));
		assertFalse(program.isDiscontiguous("small", 1));
		assertFalse(program.isDiscontiguous("brown", 1));
		assertFalse(program.isDiscontiguous("black", 1));
		assertFalse(program.isDiscontiguous("gray", 1));
		assertFalse(program.isDiscontiguous("dark", 1));

		// mark Discontiguous
		program.markDiscontiguous(male, 1);
		program.markDiscontiguous(parent, 2);
		program.markDiscontiguous(female, 1);
		program.markDiscontiguous(offspring, 2);
		program.markDiscontiguous(mother, 2);
		program.markDiscontiguous(grandparent, 2);
		program.markDiscontiguous(different, 2);
		program.markDiscontiguous(predecessor, 2);

		// company
		program.markDiscontiguous(employee, 3);
		program.markDiscontiguous(department, 2);
		program.markDiscontiguous(salary, 2);

		// zoo
		program.markDiscontiguous("big", 1);
		program.markDiscontiguous("small", 1);
		program.markDiscontiguous("brown", 1);
		program.markDiscontiguous("black", 1);
		program.markDiscontiguous("gray", 1);
		program.markDiscontiguous("dark", 1);

		// family
		assertTrue(program.isDiscontiguous(male, 1));
		assertTrue(program.isDiscontiguous(parent, 2));
		assertTrue(program.isDiscontiguous(female, 1));
		assertTrue(program.isDiscontiguous(offspring, 2));
		assertTrue(program.isDiscontiguous(mother, 2));
		assertTrue(program.isDiscontiguous(grandparent, 2));
		assertTrue(program.isDiscontiguous(different, 2));
		assertTrue(program.isDiscontiguous(predecessor, 2));

		// company
		assertTrue(program.isDiscontiguous(employee, 3));
		assertTrue(program.isDiscontiguous(department, 2));
		assertTrue(program.isDiscontiguous(salary, 2));

		// zoo
		assertTrue(program.isDiscontiguous("big", 1));
		assertTrue(program.isDiscontiguous("small", 1));
		assertTrue(program.isDiscontiguous("brown", 1));
		assertTrue(program.isDiscontiguous("black", 1));
		assertTrue(program.isDiscontiguous("gray", 1));
		assertTrue(program.isDiscontiguous("dark", 1));

	}

	@Test
	public final void testUnmarkDiscontiguous() {

		// family
		assertFalse(program.isDiscontiguous(male, 1));
		assertFalse(program.isDiscontiguous(parent, 2));
		assertFalse(program.isDiscontiguous(female, 1));
		assertFalse(program.isDiscontiguous(offspring, 2));
		assertFalse(program.isDiscontiguous(mother, 2));
		assertFalse(program.isDiscontiguous(grandparent, 2));
		assertFalse(program.isDiscontiguous(different, 2));
		assertFalse(program.isDiscontiguous(predecessor, 2));

		// company
		assertFalse(program.isDiscontiguous(employee, 3));
		assertFalse(program.isDiscontiguous(department, 2));
		assertFalse(program.isDiscontiguous(salary, 2));

		// zoo
		assertFalse(program.isDiscontiguous("big", 1));
		assertFalse(program.isDiscontiguous("small", 1));
		assertFalse(program.isDiscontiguous("brown", 1));
		assertFalse(program.isDiscontiguous("black", 1));
		assertFalse(program.isDiscontiguous("gray", 1));
		assertFalse(program.isDiscontiguous("dark", 1));

		// mark Discontiguous
		program.markDiscontiguous(male, 1);
		program.markDiscontiguous(parent, 2);
		program.markDiscontiguous(female, 1);
		program.markDiscontiguous(offspring, 2);
		program.markDiscontiguous(mother, 2);
		program.markDiscontiguous(grandparent, 2);
		program.markDiscontiguous(different, 2);
		program.markDiscontiguous(predecessor, 2);

		// company
		program.markDiscontiguous(employee, 3);
		program.markDiscontiguous(department, 2);
		program.markDiscontiguous(salary, 2);

		// zoo
		program.markDiscontiguous("big", 1);
		program.markDiscontiguous("small", 1);
		program.markDiscontiguous("brown", 1);
		program.markDiscontiguous("black", 1);
		program.markDiscontiguous("gray", 1);
		program.markDiscontiguous("dark", 1);

		// family
		assertTrue(program.isDiscontiguous(male, 1));
		assertTrue(program.isDiscontiguous(parent, 2));
		assertTrue(program.isDiscontiguous(female, 1));
		assertTrue(program.isDiscontiguous(offspring, 2));
		assertTrue(program.isDiscontiguous(mother, 2));
		assertTrue(program.isDiscontiguous(grandparent, 2));
		assertTrue(program.isDiscontiguous(different, 2));
		assertTrue(program.isDiscontiguous(predecessor, 2));

		// company
		assertTrue(program.isDiscontiguous(employee, 3));
		assertTrue(program.isDiscontiguous(department, 2));
		assertTrue(program.isDiscontiguous(salary, 2));

		// zoo
		assertTrue(program.isDiscontiguous("big", 1));
		assertTrue(program.isDiscontiguous("small", 1));
		assertTrue(program.isDiscontiguous("brown", 1));
		assertTrue(program.isDiscontiguous("black", 1));
		assertTrue(program.isDiscontiguous("gray", 1));
		assertTrue(program.isDiscontiguous("dark", 1));

		// un-mark Discontiguous
		program.unmarkDiscontiguous(male, 1);
		program.unmarkDiscontiguous(parent, 2);
		program.unmarkDiscontiguous(female, 1);
		program.unmarkDiscontiguous(offspring, 2);
		program.unmarkDiscontiguous(mother, 2);
		program.unmarkDiscontiguous(grandparent, 2);
		program.unmarkDiscontiguous(different, 2);
		program.unmarkDiscontiguous(predecessor, 2);

		// company
		program.unmarkDiscontiguous(employee, 3);
		program.unmarkDiscontiguous(department, 2);
		program.unmarkDiscontiguous(salary, 2);

		// zoo
		program.unmarkDiscontiguous("big", 1);
		program.unmarkDiscontiguous("small", 1);
		program.unmarkDiscontiguous("brown", 1);
		program.unmarkDiscontiguous("black", 1);
		program.unmarkDiscontiguous("gray", 1);
		program.unmarkDiscontiguous("dark", 1);

		// family
		assertFalse(program.isDiscontiguous(male, 1));
		assertFalse(program.isDiscontiguous(parent, 2));
		assertFalse(program.isDiscontiguous(female, 1));
		assertFalse(program.isDiscontiguous(offspring, 2));
		assertFalse(program.isDiscontiguous(mother, 2));
		assertFalse(program.isDiscontiguous(grandparent, 2));
		assertFalse(program.isDiscontiguous(different, 2));
		assertFalse(program.isDiscontiguous(predecessor, 2));

		// company
		assertFalse(program.isDiscontiguous(employee, 3));
		assertFalse(program.isDiscontiguous(department, 2));
		assertFalse(program.isDiscontiguous(salary, 2));

		// zoo
		assertFalse(program.isDiscontiguous("big", 1));
		assertFalse(program.isDiscontiguous("small", 1));
		assertFalse(program.isDiscontiguous("brown", 1));
		assertFalse(program.isDiscontiguous("black", 1));
		assertFalse(program.isDiscontiguous("gray", 1));
		assertFalse(program.isDiscontiguous("dark", 1));

	}

	@Test
	public final void testGetIndicators() {

		Set<String> indicators = new LinkedHashSet<String>(

				Arrays.asList("parent/2", "female/1", "male/1", "offspring/2", "mother/2", "grandparent/2", "sister/2",
						"different/2", "predecessor/2", "employee/3", "department/2", "salary/2", "big/1", "small/1",
						"brown/1", "black/1", "gray/1", "dark/1")

		);

		assertEquals(indicators, program.getIndicators());

	}

	@Test
	@Ignore
	public final void testGetClauses() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIterator() {

		Iterator<PrologClauses> i = program.iterator();
		assertNotNull(i);

		for (int index = 0; i.hasNext() && index < clausesArray.length; index++) {
			PrologClauses clause = i.next();
			assertEquals(clausesArray[index], clause);
		}

	}

	@Test
	public final void testHashCode() {

		PrologProgram otherProgram = new JplProgram();
		otherProgram.add(program);
		assertEquals(program, otherProgram);
		assertFalse(program == otherProgram);

	}

	@Test
	public final void testEquals() {

		PrologProgram otherProgram = new JplProgram();
		otherProgram.add(program);
		assertEquals(program, otherProgram);
		assertFalse(program == otherProgram);

	}

	@Test
	public final void testRemoveAllCollectionOfQ() {

		// parent relationship
		PrologClauses parents = new JplClauses("parent/2");
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));

		assertTrue(program.removeAll(parents));
		assertEquals(44, program.size());
		assertFalse(program.isEmpty());

		assertTrue(program.removeAll(program));
		assertEquals(0, program.size());
		assertTrue(program.isEmpty());

	}

	@Test
	public final void testIsEmpty() {

		assertFalse(program.isEmpty());
		program.clear();
		assertTrue(program.isEmpty());

	}

	@Test
	@Ignore
	public final void testContains() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToArray() {
		assertArrayEquals(clausesArray, program.toArray());
	}

	@Test
	public final void testToArrayTArray() {
		assertArrayEquals(clausesArray, program.toArray(new PrologClauses[0]));
	}

	@Test
	public final void testRemove() {

		assertEquals(50, program.size());
		assertFalse(program.isEmpty());

		// parent relationship
		program.remove(new SwiProlog7Clause(provider.newStructure(parent, pam, bob)));
		program.remove(new SwiProlog7Clause(provider.newStructure(parent, tom, bob)));
		program.remove(new SwiProlog7Clause(provider.newStructure(parent, tom, liz)));
		program.remove(new SwiProlog7Clause(provider.newStructure(parent, bob, ann)));
		program.remove(new SwiProlog7Clause(provider.newStructure(parent, bob, pat)));
		program.remove(new SwiProlog7Clause(provider.newStructure(parent, pat, jim)));

		// female relationship
		program.remove(new SwiProlog7Clause(provider.newStructure(female, pam)));
		program.remove(new SwiProlog7Clause(provider.newStructure(female, liz)));
		program.remove(new SwiProlog7Clause(provider.newStructure(female, ann)));
		program.remove(new SwiProlog7Clause(provider.newStructure(female, pat)));

		// male relationship
		program.remove(new SwiProlog7Clause(provider.newStructure(male, tom)));
		program.remove(new SwiProlog7Clause(provider.newStructure(male, bob)));
		program.remove(new SwiProlog7Clause(provider.newStructure(male, jim)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.remove(
				new SwiProlog7Clause(provider.newStructure(offspring, x, y), provider.newStructure(parent, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		program.remove(new SwiProlog7Clause(provider.newStructure(mother, x, y),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(female, x))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.remove(new SwiProlog7Clause(provider.newStructure(grandparent, x, z),
				provider.newStructure(",", provider.newStructure(parent, x, y), provider.newStructure(parent, y, z))));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.remove(new SwiProlog7Clause(provider.newStructure(sister, x, y),
				provider.newStructure(",", provider.newStructure(parent, z, x),
						provider.newStructure(",", provider.newStructure(parent, z, y), provider.newStructure(",",
								provider.newStructure(female, x), provider.newStructure(different, x, y))))));

		x = provider.newVariable("X", 0);
		program.remove(new SwiProlog7Clause(provider.newStructure(different, x, x),
				provider.newStructure(",", provider.prologCut(), provider.prologFail())));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		program.remove(new SwiProlog7Clause(provider.newStructure(different, x, y)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.remove(
				new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(parent, x, z)));

		x = provider.newVariable("X", 0);
		y = provider.newVariable("Y", 1);
		z = provider.newVariable("Z", 2);
		program.remove(new SwiProlog7Clause(provider.newStructure(predecessor, x, z), provider.newStructure(",",
				provider.newStructure(parent, x, y), provider.newStructure(predecessor, y, z))));

		// employee relationship
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, mcardon, one, five)));
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, treeman, two, three)));
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, chapman, one, two)));
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, claessen, four, one)));
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, petersen, five, eight)));
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, cohn, one, seven)));
		program.remove(new SwiProlog7Clause(provider.newStructure(employee, duffy, one, nine)));

		// department relationship
		program.remove(new SwiProlog7Clause(provider.newStructure(department, one, board)));
		program.remove(new SwiProlog7Clause(provider.newStructure(department, two, human_resources)));
		program.remove(new SwiProlog7Clause(provider.newStructure(department, three, production)));
		program.remove(new SwiProlog7Clause(provider.newStructure(department, four, technical_services)));
		program.remove(new SwiProlog7Clause(provider.newStructure(department, five, administration)));

		// salary relationship
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, one, thousand)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, two, thousandFiveHundred)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, three, twoThousand)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, four, twoThousandFiveHundred)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, five, threeThousand)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, six, threeThousandFiveHundred)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, seven, fourThousand)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, eight, fourThousandFiveHundred)));
		program.remove(new SwiProlog7Clause(provider.newStructure(salary, nine, fiveThousand)));

		// zoo relationships
		program.remove(new SwiProlog7Clause(provider.newStructure("big", bear)));
		program.remove(new SwiProlog7Clause(provider.newStructure("big", elephant)));
		program.remove(new SwiProlog7Clause(provider.newStructure("small", cat)));
		program.remove(new SwiProlog7Clause(provider.newStructure("brown", bear)));
		program.remove(new SwiProlog7Clause(provider.newStructure("black", cat)));
		program.remove(new SwiProlog7Clause(provider.newStructure("gray", elephant)));

		// dark rules
		z = provider.newVariable("Z", 0);
		program.remove(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("black", z)));

		z = provider.newVariable("Z", 0);
		program.remove(new SwiProlog7Clause(provider.newStructure("dark", z), provider.newStructure("brown", z)));

		assertEquals(0, program.size());
		assertTrue(program.isEmpty());

	}

	@Test
	@Ignore
	public final void testContainsAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddAll() {

		PrologProgram otherProgram = new JplProgram();
		assertEquals(0, otherProgram.size());
		assertTrue(otherProgram.isEmpty());

		otherProgram.addAll(program);

		assertEquals(program, otherProgram);
		assertEquals(50, otherProgram.size());
		assertFalse(otherProgram.isEmpty());

	}

	@Test
	@Ignore
	public final void testRetainAll() {

		// parent relationship
		PrologClauses parents = new JplClauses("parent/2");
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pam, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, bob )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( tom, liz )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, ann )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( bob, pat )")));
		parents.add(new SwiProlog7Clause(provider.parseTerm("parent( pat, jim )")));

		assertTrue(program.retainAll(parents));
		assertEquals(6, program.size());
		assertFalse(program.isEmpty());

	}

}
