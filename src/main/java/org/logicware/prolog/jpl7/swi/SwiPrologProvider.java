package org.logicware.prolog.jpl7.swi;

import org.jpl7.Term;
import org.logicware.prolog.PrologConverter;
import org.logicware.prolog.PrologEngine;
import org.logicware.prolog.PrologProvider;
import org.logicware.prolog.jpl7.JplProvider;

public class SwiPrologProvider extends JplProvider implements PrologProvider {

	public SwiPrologProvider() {
		super(new SwiPrologConverter());
	}

	public SwiPrologProvider(PrologConverter<Term> converter) {
		super(converter);
	}

	public PrologEngine newEngine() {
		return new SwiPrologEngine(this);
	}

	@Override
	public String toString() {
		return "SwiPrologProvider [converter=" + converter + "]";
	}

}
