package org.logicware.prolog.jpl7.swi;

import org.jpl7.Term;
import org.logicware.prolog.PrologConverter;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.jpl7.JplConverter;

public class SwiPrologConverter extends JplConverter implements PrologConverter<Term> {

	public PrologProvider createProvider() {
		return new SwiPrologProvider(this);
	}

}
