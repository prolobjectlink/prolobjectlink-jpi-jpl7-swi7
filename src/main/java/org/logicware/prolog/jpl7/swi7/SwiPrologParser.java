package org.logicware.prolog.jpl7.swi7;

import org.jpl7.Term;
import org.logicware.prolog.PrologParser;
import org.logicware.prolog.PrologTerm;
import org.logicware.prolog.PrologTermType;
import org.logicware.prolog.jpl7.JplClause;
import org.logicware.prolog.jpl7.JplParser;

public final class SwiPrologParser extends JplParser implements PrologParser {

	@Override
	protected JplClause getClause(Term clauseTerm) {
		PrologTerm head = toTerm(clauseTerm, PrologTerm.class);
		if (clauseTerm.hasFunctor(":-", 2)) {
			head = toTerm(clauseTerm.arg(1), PrologTerm.class);
			PrologTerm body = toTerm(clauseTerm.arg(2), PrologTerm.class);
			if (body.getType() != PrologTermType.TRUE_TYPE) {
				return new SwiProlog7Clause(provider, head, body, false, false, false);
			}
		}
		return new SwiProlog7Clause(provider, head, false, false, false);
	}

}
