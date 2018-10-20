sister(_4639, _4640):-
	parent(_4641, _4639),
	parent(_4641, _4640),
	female(_4639),
	different(_4639, _4640).
female(pam).
female(liz).
female(ann).
female(pat).
grandparent(_4642, _4643):-
	parent(_4642, _4644),
	parent(_4644, _4643).
different(_4645, _4645):-
	'!',
	fail.
different(_4646, _4647).
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
mother(_4648, _4649):-
	parent(_4648, _4649),
	female(_4648).
offspring(_4650, _4651):-
	parent(_4650, _4651).
male(tom).
male(bob).
male(jim).
predecessor(_4652, _4653):-
	parent(_4652, _4653).
predecessor(_4654, _4655):-
	parent(_4654, _4656),
	predecessor(_4656, _4655).
