predecessor(_4422, _4423):-
	parent(_4422, _4423).
predecessor(_4424, _4425):-
	parent(_4424, _4426),
	predecessor(_4426, _4425).
male(tom).
male(bob).
male(jim).
mother(_4427, _4428):-
	parent(_4427, _4428),
	female(_4427).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
offspring(_4429, _4430):-
	parent(_4429, _4430).
sister(_4431, _4432):-
	parent(_4433, _4431),
	parent(_4433, _4432),
	female(_4431),
	different(_4431, _4432).
different(_4434, _4434):-
	!,
	fail.
different(_4435, _4436).
grandparent(_4437, _4438):-
	parent(_4437, _4439),
	parent(_4439, _4438).
female(pam).
female(liz).
female(ann).
female(pat).
