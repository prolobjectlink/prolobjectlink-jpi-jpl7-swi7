offspring(_5676, _5677):-
	parent(_5676, _5677).
female(pam).
female(liz).
female(ann).
female(pat).
different(_5678, _5678):-
	!,
	fail.
different(_5679, _5680).
sister(_5681, _5682):-
	parent(_5683, _5681),
	parent(_5683, _5682),
	female(_5681),
	different(_5681, _5682).
mother(_5684, _5685):-
	parent(_5684, _5685),
	female(_5684).
grandparent(_5686, _5687):-
	parent(_5686, _5688),
	parent(_5688, _5687).
predecessor(_5689, _5690):-
	parent(_5689, _5690).
predecessor(_5691, _5692):-
	parent(_5691, _5693),
	predecessor(_5693, _5692).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
male(tom).
male(bob).
male(jim).
