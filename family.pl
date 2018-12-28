male(tom).
male(bob).
male(jim).
offspring(_5602, _5603):-
	parent(_5602, _5603).
predecessor(_5604, _5605):-
	parent(_5604, _5605).
predecessor(_5606, _5607):-
	parent(_5606, _5608),
	predecessor(_5608, _5607).
mother(_5609, _5610):-
	parent(_5609, _5610),
	female(_5609).
grandparent(_5611, _5612):-
	parent(_5611, _5613),
	parent(_5613, _5612).
different(_5614, _5614):-
	!,
	fail.
different(_5615, _5616).
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
sister(_5617, _5618):-
	parent(_5619, _5617),
	parent(_5619, _5618),
	female(_5617),
	different(_5617, _5618).
