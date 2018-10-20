parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, pat).
parent(pat, jim).
female(pam).
male(tom).
male(bob).
female(liz).
female(ann).
female(pat).
male(jim).
':-'(offspring(X, Y), parent(X, Y)).
':-'(mother(X, Y), ','(parent(X, Y), female(X))).
':-'(grandparent(X, Z), ','(parent(X, Y), parent(Y, Z))).
':-'(sister(X, Y), ','(parent(Z, X), ','(parent(Z, Y), ','(female(X), different(X, Y))))).
':-'(different(X, X), ','('!', fail)).
different(X, Y).
':-'(predecessor(X, Z), parent(X, Z)).
':-'(predecessor(X, Z), ','(parent(X, Y), predecessor(Y, Z))).
