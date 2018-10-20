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
package org.logicware.prolog.jpl7.swi;

import static org.logicware.platform.logging.LoggerConstants.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Util;
import org.logicware.platform.logging.LoggerUtils;
import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologTermType;
import org.logicware.prolog.jpl7.JplClause;
import org.logicware.prolog.jpl7.JplEngine;

public final class SwiPrologEngine extends JplEngine implements PrologEngine {

	private static final String META_INF = "META-INF";
	private static final String SWI_PROLOG = "swi-prolog.pl";
	private static final String SWI_TEMP_FILE = "prolobjectlink-jpi-jpl7-swi.pl";

	private static final String SWI_PROCEDURE = META_INF + "/" + SWI_PROLOG;

	private static final String ENSURE_LOADED_OPEN = "ensure_loaded('";
	private static final String ENSURE_LOADED_CLOSE_AND = "'),";

	private final String swiTemp;

	SwiPrologEngine(PrologProvider provider) {
		super(provider);
		InputStream in = null;
		OutputStream out = null;
		swiTemp = temp + "/" + SWI_TEMP_FILE;
		try {
			Thread thread = Thread.currentThread();
			ClassLoader cl = thread.getContextClassLoader();
			if (!(new File(SWI_PROCEDURE).exists())) {
				in = cl.getResource(SWI_PROCEDURE).openStream();
				out = new FileOutputStream(swiTemp);
				LoggerUtils.info(getClass(), swiTemp);
				copy(in, out);
			}
		} catch (IOException e) {
			LoggerUtils.error(JplEngine.class, IO + file, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LoggerUtils.error(JplEngine.class, IO + file, e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LoggerUtils.error(JplEngine.class, IO + file, e);
				}
			}
		}
	}

	SwiPrologEngine(PrologProvider provider, String file) {
		super(provider, file);
		InputStream in = null;
		OutputStream out = null;
		swiTemp = temp + "/" + SWI_TEMP_FILE;
		try {
			Thread thread = Thread.currentThread();
			ClassLoader cl = thread.getContextClassLoader();
			if (!(new File(SWI_PROCEDURE).exists())) {
				in = cl.getResource(SWI_PROCEDURE).openStream();
				out = new FileOutputStream(swiTemp);
				LoggerUtils.info(getClass(), swiTemp);
				copy(in, out);
			}
		} catch (IOException e) {
			LoggerUtils.error(JplEngine.class, IO + file, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					LoggerUtils.error(JplEngine.class, IO + file, e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LoggerUtils.error(JplEngine.class, IO + file, e);
				}
			}
		}
	}

	public Iterator<PrologClause> iterator() {
		query = new Query(

				ENSURE_LOADED_OPEN + swiTemp + ENSURE_LOADED_CLOSE_AND +

						"clause_list('" + location + "'," + KEY + ")"

		);
		Term term = query.oneSolution().get(KEY);
		Term[] array = Util.listToTermArray(term);
		List<PrologClause> cls = new ArrayList<PrologClause>(array.length);
		for (int i = 0; i < array.length; i++) {
			if (array[i].hasFunctor(":-", 2)) {
				PrologTerm head = toTerm(array[i].arg(1), PrologTerm.class);
				PrologTerm body = toTerm(array[i].arg(2), PrologTerm.class);
				if (body.getType() != PrologTermType.TRUE_TYPE) {
					cls.add(new JplClause(provider, head, body, false, false, false));
				} else {
					cls.add(new JplClause(provider, head, false, false, false));
				}

			}
		}
		return new PrologProgramIterator(cls);
	}

	public synchronized int getProgramSize() {
		query = new Query(

				ENSURE_LOADED_OPEN + swiTemp + ENSURE_LOADED_CLOSE_AND +

						"program_size('" + location + "'," + KEY + ")"

		);
		Term term = query.oneSolution().get(KEY);
		return term.intValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((swiTemp == null) ? 0 : swiTemp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwiPrologEngine other = (SwiPrologEngine) obj;
		if (swiTemp == null) {
			if (other.swiTemp != null)
				return false;
		} else if (!swiTemp.equals(other.swiTemp)) {
			return false;
		}
		return true;
	}

}
