predecessor(_5559, _5560):-
	parent(_5559, _5560).
predecessor(_5561, _5562):-
	parent(_5561, _5563),
	predecessor(_5563, _5562).
mother(_5564, _5565):-
	parent(_5564, _5565),
	female(_5564).
male(tom).
male(bob).
male(jim).
grandparent(_5566, _5567):-
	parent(_5566, _5568),
	parent(_5568, _5567).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
offspring(_5569, _5570):-
	parent(_5569, _5570).
sister(_5571, _5572):-
	parent(_5573, _5571),
	parent(_5573, _5572),
	female(_5571),
	different(_5571, _5572).
female(pam).
female(liz).
female(ann).
female(pat).
different(_5574, _5574):-
	!,
	fail.
different(_5575, _5576).
