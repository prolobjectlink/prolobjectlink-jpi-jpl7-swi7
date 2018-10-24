different(_5585, _5585):-
	!,
	fail.
different(_5586, _5587).
female(pam).
female(liz).
female(ann).
female(pat).
sister(_5588, _5589):-
	parent(_5590, _5588),
	parent(_5590, _5589),
	female(_5588),
	different(_5588, _5589).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
male(tom).
male(bob).
male(jim).
grandparent(_5591, _5592):-
	parent(_5591, _5593),
	parent(_5593, _5592).
offspring(_5594, _5595):-
	parent(_5594, _5595).
predecessor(_5596, _5597):-
	parent(_5596, _5597).
predecessor(_5598, _5599):-
	parent(_5598, _5600),
	predecessor(_5600, _5599).
mother(_5601, _5602):-
	parent(_5601, _5602),
	female(_5601).
