male(tom).
male(bob).
male(jim).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
offspring(_6128, _6129):-
	parent(_6128, _6129).
predecessor(_6130, _6131):-
	parent(_6130, _6131).
predecessor(_6132, _6133):-
	parent(_6132, _6134),
	predecessor(_6134, _6133).
female(pam).
female(liz).
female(ann).
female(pat).
sister(_6135, _6136):-
	parent(_6137, _6135),
	parent(_6137, _6136),
	female(_6135),
	different(_6135, _6136).
mother(_6138, _6139):-
	parent(_6138, _6139),
	female(_6138).
grandparent(_6140, _6141):-
	parent(_6140, _6142),
	parent(_6142, _6141).
different(_6143, _6143):-
	!,
	fail.
different(_6144, _6145).
