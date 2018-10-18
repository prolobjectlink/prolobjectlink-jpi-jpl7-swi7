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
import org.logicware.prolog.PrologQuery;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologTermType;
import org.logicware.prolog.jpl7.JplClause;
import org.logicware.prolog.jpl7.JplEngine;
import org.logicware.prolog.jpl7.JplQuery;

public final class SwiPrologEngine extends JplEngine implements PrologEngine {

	private static final String META_INF = "META-INF";
	private static final String SWI_PROLOG = "swi-prolog.pl";
	private static final String SWI_TEMP_FILE = "prolobjectlink-jpi-jpl7-swi.pl";

	private static final String SWI_TEMP = temp + "/" + SWI_TEMP_FILE;
	private static final String SWI_PROCEDURE = META_INF + "/" + SWI_PROLOG;

	private static final String ENSURE_LOADED_OPEN = "ensure_loaded('";
	private static final String ENSURE_LOADED_CLOSE_AND = "'),";

	SwiPrologEngine(PrologProvider provider) {
		super(provider);
		InputStream in = null;
		OutputStream out = null;
		try {
			Thread thread = Thread.currentThread();
			ClassLoader cl = thread.getContextClassLoader();
			if (!(new File(SWI_PROCEDURE).exists())) {
				in = cl.getResource(SWI_PROCEDURE).openStream();
				out = new FileOutputStream(SWI_TEMP);
				LoggerUtils.info(getClass(), SWI_TEMP);
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
		try {
			Thread thread = Thread.currentThread();
			ClassLoader cl = thread.getContextClassLoader();
			if (!(new File(SWI_PROCEDURE).exists())) {
				in = cl.getResource(SWI_PROCEDURE).openStream();
				out = new FileOutputStream(SWI_TEMP);
				LoggerUtils.info(getClass(), SWI_TEMP);
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

	public void include(String path) {
		// TODO Auto-generated method stub
	}

	public synchronized void abolish(String functor, int arity) {
		query = new Query("" +

				ENSURE_LOADED_OPEN + SWI_TEMP + ENSURE_LOADED_CLOSE_AND +

				"remove_all('" + location + "'," + functor + "," + arity + ")"

		);
		query.hasSolution();
	}

	@Override
	public synchronized void asserta(Term term) {
		// TODO Auto-generated method stub
	}

	@Override
	public synchronized void assertz(Term t) {
		Term h = t;
		Term b = BODY;
		if (t.hasFunctor(":-", 2)) {
			h = t.arg(1);
			b = t.arg(2);
		}
		query = new Query("" +

				ENSURE_LOADED_OPEN + SWI_TEMP + ENSURE_LOADED_CLOSE_AND +

				"add_clause('" + location + "'," + t + "," + h + "," + b + ")"

		);
		query.hasSolution();
	}

	@Override
	public synchronized boolean clause(Term t) {
		Term h = t;
		Term b = BODY;
		if (t.hasFunctor(":-", 2)) {
			h = t.arg(1);
			b = t.arg(2);
		}
		query = new Query("" +

				"clause(" + h + "," + b + ")"

		);
		return query.hasSolution();
	}

	@Override
	public synchronized void retract(Term t) {
		query = new Query("" +

				ENSURE_LOADED_OPEN + SWI_TEMP + ENSURE_LOADED_CLOSE_AND +

				"remove_clause('" + location + "'," + t + ")"

		);
		query.hasSolution();
	}

	public PrologQuery query(String stringQuery) {
		return new JplQuery(this, file, stringQuery);
	}

	public PrologQuery query(PrologTerm... terms) {
		StringBuilder buffer = new StringBuilder();
		int length = terms.length;
		for (int i = 0; i < length; i++) {
			buffer.append(i < length - 1 ? terms[i] + ", " : terms[i]);
		}
		return query("" + buffer + "");
	}

	public Iterator<PrologClause> iterator() {
		query = new Query(

				ENSURE_LOADED_OPEN + SWI_TEMP + ENSURE_LOADED_CLOSE_AND +

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

				ENSURE_LOADED_OPEN + SWI_TEMP + ENSURE_LOADED_CLOSE_AND +

						"program_size('" + location + "'," + KEY + ")"

		);
		Term term = query.oneSolution().get(KEY);
		return term.intValue();
	}

}
