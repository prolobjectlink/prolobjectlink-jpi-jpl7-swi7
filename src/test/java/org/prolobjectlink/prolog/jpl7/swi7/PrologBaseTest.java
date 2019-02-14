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

import java.util.HashMap;
import java.util.Map;

import org.prolobjectlink.prolog.Prolog;
import org.prolobjectlink.prolog.PrologAtom;
import org.prolobjectlink.prolog.PrologDouble;
import org.prolobjectlink.prolog.PrologFloat;
import org.prolobjectlink.prolog.PrologInteger;
import org.prolobjectlink.prolog.PrologProvider;
import org.prolobjectlink.prolog.PrologTerm;
import org.prolobjectlink.prolog.PrologVariable;
import org.prolobjectlink.prolog.jpl7.swi7.SwiProlog7;

/** @author Jose Zalacain @since 1.0 */
public abstract class PrologBaseTest {

	protected String male = "male";
	protected String parent = "parent";
	protected String female = "female";
	protected String mother = "mother";
	protected String sister = "sister";
	protected String different = "different";
	protected String offspring = "offspring";
	protected String grandparent = "grandparent";
	protected String predecessor = "predecessor";

	protected String salary = "salary";
	protected String employee = "employee";
	protected String department = "department";

	protected static final PrologProvider provider = Prolog.getProvider(SwiProlog7.class);

	protected PrologTerm nil = provider.prologNil();
	protected PrologTerm empty = provider.prologEmpty();

	protected PrologVariable x = provider.newVariable("X", 0);
	protected PrologVariable y = provider.newVariable("Y", 1);
	protected PrologVariable z = provider.newVariable("Z", 2);

	protected PrologVariable name = provider.newVariable("Name", 0);
	protected PrologVariable dpto = provider.newVariable("Dpto", 1);
	protected PrologVariable scale = provider.newVariable("Scale", 2);
	protected PrologVariable dptoName = provider.newVariable("DepartmentName", 3);
	protected PrologVariable money = provider.newVariable("Money", 4);

	protected PrologAtom pam = provider.newAtom("pam");
	protected PrologAtom bob = provider.newAtom("bob");
	protected PrologAtom tom = provider.newAtom("tom");
	protected PrologAtom liz = provider.newAtom("liz");
	protected PrologAtom ann = provider.newAtom("ann");
	protected PrologAtom pat = provider.newAtom("pat");
	protected PrologAtom jim = provider.newAtom("jim");

	protected PrologInteger zero = provider.newInteger(0);
	protected PrologInteger one = provider.newInteger(1);
	protected PrologInteger two = provider.newInteger(2);
	protected PrologInteger three = provider.newInteger(3);
	protected PrologInteger four = provider.newInteger(4);
	protected PrologInteger five = provider.newInteger(5);
	protected PrologInteger six = provider.newInteger(6);
	protected PrologInteger seven = provider.newInteger(7);
	protected PrologInteger eight = provider.newInteger(8);
	protected PrologInteger nine = provider.newInteger(9);
	protected PrologInteger ten = provider.newInteger(10);

	protected PrologDouble gauss = provider.newDouble(.8346268);
	protected PrologDouble aureo = provider.newDouble(1.6180339887);
	protected PrologDouble pi = provider.newDouble(3.141592);
	protected PrologFloat euler = provider.newFloat(2.71828);

	protected PrologAtom cat = provider.newAtom("cat");
	protected PrologAtom bear = provider.newAtom("bear");
	protected PrologAtom elephant = provider.newAtom("elephant");

	protected PrologAtom mcardon = provider.newAtom("mcardon");
	protected PrologAtom treeman = provider.newAtom("treeman");
	protected PrologAtom chapman = provider.newAtom("chapman");
	protected PrologAtom claessen = provider.newAtom("claessen");
	protected PrologAtom petersen = provider.newAtom("petersen");
	protected PrologAtom cohn = provider.newAtom("cohn");
	protected PrologAtom duffy = provider.newAtom("duffy");

	protected PrologAtom board = provider.newAtom("board");
	protected PrologAtom human_resources = provider.newAtom("human_resources");
	protected PrologAtom production = provider.newAtom("production");
	protected PrologAtom technical_services = provider.newAtom("technical_services");
	protected PrologAtom administration = provider.newAtom("administration");

	protected PrologInteger thousand = provider.newInteger(1000);
	protected PrologInteger thousandFiveHundred = provider.newInteger(1500);
	protected PrologInteger twoThousand = provider.newInteger(2000);
	protected PrologInteger twoThousandFiveHundred = provider.newInteger(2500);
	protected PrologInteger threeThousand = provider.newInteger(3000);
	protected PrologInteger threeThousandFiveHundred = provider.newInteger(3500);
	protected PrologTerm fourThousand = provider.newInteger(4000);
	protected PrologTerm fourThousandFiveHundred = provider.newInteger(4500);
	protected PrologTerm fiveThousand = provider.newInteger(5000);

	protected PrologTerm[] expecteds0 = new PrologTerm[] { mcardon, one, five, board, threeThousand };
	protected PrologTerm[] expecteds1 = new PrologTerm[] { treeman, two, three, human_resources, twoThousand };
	protected PrologTerm[] expecteds2 = new PrologTerm[] { chapman, one, two, board, thousandFiveHundred };
	protected PrologTerm[] expecteds3 = new PrologTerm[] { claessen, four, one, technical_services, thousand };
	protected PrologTerm[] expecteds4 = new PrologTerm[] { petersen, five, eight, administration,
			fourThousandFiveHundred };
	protected PrologTerm[] expecteds5 = new PrologTerm[] { cohn, one, seven, board, fourThousand };
	protected PrologTerm[] expecteds6 = new PrologTerm[] { duffy, one, nine, board, fiveThousand };

	protected PrologTerm[][] solution = new PrologTerm[7][5];

	protected Map<String, PrologTerm> solutionMap;
	protected Map<String, PrologTerm>[] allSolutionMap;

	protected Map<String, PrologTerm>[] famillyAllSolutionMap;
	protected Map<String, PrologTerm> famillySolutionMap = new HashMap<String, PrologTerm>();

}
