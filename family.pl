parent( pam, bob ).
parent( tom, bob ).
parent( tom, liz ).
parent( bob, ann ).
parent( bob, pat ).
parent( pat, jim ).

female( pam ).
female( liz ).
female( ann ).
female( pat ).

male( tom ).
male( bob ).
male( jim ).

offspring( pam, bob ) :- 
	parent( pam, bob ).


mother( pam, bob ) :- 
	parent( pam, bob ),
	female( pam ).


grandparent( pam, ann ) :- 
	parent( pam, bob ),
	parent( bob, ann ).


sister( pam, bob ) :- 
	parent( ann, pam ),
	parent( ann, bob ),
	female( pam ),
	different( pam, bob ).


different( pam, pam ) :- 
	!,
	fail.

different( pam, bob ).

predecessor( pam, ann ) :- 
	parent( pam, ann ).

predecessor( pam, ann ) :- 
	parent( pam, bob ),
	predecessor( bob, ann ).


