grandparent(_9691, _9692):-
	parent(_9691, _9693),
	parent(_9693, _9692).
different(_9694, _9694):-
	!,
	fail.
different(_9695, _9696).
sister(_9697, _9698):-
	parent(_9699, _9697),
	parent(_9699, _9698),
	female(_9697),
	different(_9697, _9698).
offspring(_9700, _9701):-
	parent(_9700, _9701).
predecessor(_9702, _9703):-
	parent(_9702, _9703).
predecessor(_9704, _9705):-
	parent(_9704, _9706),
	predecessor(_9706, _9705).
male(tom).
male(bob).
male(jim).
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
mother(_9707, _9708):-
	parent(_9707, _9708),
	female(_9707).
