/*
 * #%L
 * prolobjectlink-jpi-jpl7-swi7
 * %%
 * Copyright (C) 2012 - 2018 Logicware Project
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jpl7.JPL;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Util;
import org.logicware.Licenses;
import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologTermType;
import org.logicware.prolog.jpl7.JplClause;
import org.logicware.prolog.jpl7.JplEngine;

public final class SwiProlog7Engine extends JplEngine implements PrologEngine {

	SwiProlog7Engine(PrologProvider provider) {
		super(provider);
	}

	SwiProlog7Engine(PrologProvider provider, String file) {
		super(provider, file);
	}

	public final String getLicense() {
		return Licenses.LGPL_V3;
	}

	public final String getVersion() {
		Term swi = Query.oneSolution("current_prolog_flag(version_data,Swi)").get("Swi");
		return "" + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3) + " (JPL v" + JPL.version_string() + ")";
	}

	public final String getName() {
		return "SWI-Prolog";
	}

	@Override
	protected final synchronized Iterator<PrologClause> iterator(String path) {
		Query query = new Query(

				"consult('" + path + "')," +

						"findall(" +

						/* tab */"Head:-Body," +

						/* tab */"(" +

						/* tab *//* tab */"predicate_property(Head, file('" + path + "')),clause(Head,Body)" +

						/* tab */")," +

						/* tab */"X" +

						")."

		);
		Term term = query.oneSolution().get(KEY);
		Term[] array = Util.listToTermArray(term);
		List<PrologClause> cls = new ArrayList<PrologClause>(array.length);
		for (int i = 0; i < array.length; i++) {
			if (array[i].hasFunctor(":-", 2)) {
				PrologTerm head = toTerm(array[i].arg(1), PrologTerm.class);
				PrologTerm body = toTerm(array[i].arg(2), PrologTerm.class);
				if (body.getType() != PrologTermType.TRUE_TYPE) {
					cls.add(new SwiProlog7Clause(provider, head, body, false, false, false));
				} else {
					cls.add(new SwiProlog7Clause(provider, head, false, false, false));
				}
			}
		}
		query.close();
		return new PrologProgramIterator(cls);
	}

}