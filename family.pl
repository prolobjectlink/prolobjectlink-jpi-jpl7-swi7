predecessor(_6176, _6177):-
	parent(_6176, _6177).
predecessor(_6178, _6179):-
	parent(_6178, _6180),
	predecessor(_6180, _6179).
different(_6181, _6181):-
	!,
	fail.
different(_6182, _6183).
grandparent(_6184, _6185):-
	parent(_6184, _6186),
	parent(_6186, _6185).
offspring(_6187, _6188):-
	parent(_6187, _6188).
mother(_6189, _6190):-
	parent(_6189, _6190),
	female(_6189).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
male(tom).
male(bob).
male(jim).
female(pam).
female(liz).
female(ann).
female(pat).
sister(_6191, _6192):-
	parent(_6193, _6191),
	parent(_6193, _6192),
	female(_6191),
	different(_6191, _6192).
