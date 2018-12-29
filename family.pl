offspring(X, Y):-
	parent(X, Y).
mother(X, Y):-
	parent(X, Y),
	female(X).
grandparent(X, Z):-
	parent(X, Y),
	parent(Y, Z).
sister(X, Y):-
	parent(Z, X),
	parent(Z, Y),
	female(X),
	different(X, Y).
different(X, X):-
	!,
	fail.
predecessor(X, Z):-
	parent(X, Z).
predecessor(X, Z):-
	parent(X, Y),
	predecessor(Y, Z).
