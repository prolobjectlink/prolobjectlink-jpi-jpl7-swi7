different(_5566, _5566):-
	!,
	fail.
different(_5567, _5568).
mother(_5569, _5570):-
	parent(_5569, _5570),
	female(_5569).
predecessor(_5571, _5572):-
	parent(_5571, _5572).
predecessor(_5573, _5574):-
	parent(_5573, _5575),
	predecessor(_5575, _5574).
grandparent(_5576, _5577):-
	parent(_5576, _5578),
	parent(_5578, _5577).
offspring(_5579, _5580):-
	parent(_5579, _5580).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
male(tom).
male(bob).
male(jim).
sister(_5581, _5582):-
	parent(_5583, _5581),
	parent(_5583, _5582),
	female(_5581),
	different(_5581, _5582).
female(pam).
female(liz).
female(ann).
female(pat).
