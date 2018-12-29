package org.logicware.prolog.jpl7.swi7;

import org.logicware.prolog.PrologClause;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.jpl7.JplClause;

public final class SwiProlog7Clause extends JplClause implements PrologClause {

	public SwiProlog7Clause(PrologTerm head) {
		super(new SwiProlog7(), head, false, false, false);
	}

	public SwiProlog7Clause(PrologTerm head, PrologTerm body) {
		super(new SwiProlog7(), head, body, false, false, false);
	}

	public SwiProlog7Clause(PrologTerm head, boolean dynamic, boolean multifile, boolean discontiguous) {
		super(new SwiProlog7(), head, dynamic, multifile, discontiguous);
	}

	public SwiProlog7Clause(PrologTerm head, PrologTerm body, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		super(new SwiProlog7(), head, body, dynamic, multifile, discontiguous);
	}

	public SwiProlog7Clause(PrologProvider provider, PrologTerm head) {
		super(provider, head, false, false, false);
	}

	public SwiProlog7Clause(PrologProvider provider, PrologTerm head, PrologTerm body) {
		super(provider, head, body, false, false, false);
	}

	public SwiProlog7Clause(PrologProvider provider, PrologTerm head, boolean dynamic, boolean multifile,
			boolean discontiguous) {
		super(provider, head, dynamic, multifile, discontiguous);
	}

	public SwiProlog7Clause(PrologProvider provider, PrologTerm head, PrologTerm body, boolean dynamic,
			boolean multifile, boolean discontiguous) {
		super(provider, head, body, dynamic, multifile, discontiguous);
	}

}
