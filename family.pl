male(tom).
male(bob).
male(jim).
grandparent(_6219, _6220):-
	parent(_6219, _6221),
	parent(_6221, _6220).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
female(pam).
female(liz).
female(ann).
female(pat).
sister(_6222, _6223):-
	parent(_6224, _6222),
	parent(_6224, _6223),
	female(_6222),
	different(_6222, _6223).
different(_6225, _6225):-
	!,
	fail.
different(_6226, _6227).
offspring(_6228, _6229):-
	parent(_6228, _6229).
predecessor(_6230, _6231):-
	parent(_6230, _6231).
predecessor(_6232, _6233):-
	parent(_6232, _6234),
	predecessor(_6234, _6233).
mother(_6235, _6236):-
	parent(_6235, _6236),
	female(_6235).
