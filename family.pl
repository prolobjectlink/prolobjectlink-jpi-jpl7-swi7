predecessor(_6190, _6191):-
	parent(_6190, _6191).
predecessor(_6192, _6193):-
	parent(_6192, _6194),
	predecessor(_6194, _6193).
sister(_6195, _6196):-
	parent(_6197, _6195),
	parent(_6197, _6196),
	female(_6195),
	different(_6195, _6196).
different(_6198, _6198):-
	!,
	fail.
different(_6199, _6200).
grandparent(_6201, _6202):-
	parent(_6201, _6203),
	parent(_6203, _6202).
mother(_6204, _6205):-
	parent(_6204, _6205),
	female(_6204).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
offspring(_6206, _6207):-
	parent(_6206, _6207).
female(pam).
female(liz).
female(ann).
female(pat).
male(tom).
male(bob).
male(jim).
