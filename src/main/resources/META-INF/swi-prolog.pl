###
# #%L
# prolobjectlink-jpi-jpl7-swi7
# %%
# Copyright (C) 2012 - 2018 Logicware Project
# %%
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#      http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# #L%
###
/******************************************************
*
* Copyright (C) 2012 - 2019 Logicware Project
* This file is part of prolobjectlink-jpi-jpl7-swi.
* Contains helpers prolog procedures for interact
* with the program-database using jpl7 over swi-prolog.
* 
*         DO NOT ERASE OR MODIFY THIS FILE
*
******************************************************/

/*****************************************************
* 
* common procedures
*
******************************************************/
delete( X, [X|Tail], Tail).
delete( X, [Y|Tail], [Y|Taill] ) :- 
	delete( X, Tail, Taill).

delete( F, A, [X|Tail], Tail) :- 
	functor(X, F, A).
delete( F, A, [Y|Tail], [Y|Taill] ) :- 
	delete( F, A, Tail, Taill).
	
print_list([]).
print_list([X|R]) :- 
	write_term(X,[quoted(true)]),
	write('.'),
	nl,
	print_list(R).

/****************************************************
* 
* asserta procedures
*
*****************************************************/

% TODO

/***********************************
* 
* assertz procedures
*
************************************/
add_clause(File,Term,Head,Body) :- 
	not(clause(Head,Body)),
	append(File),
	write_term(Term,[quoted(true)]),
	write('.'),
	nl,					 
	told.

/***********************************
* 
* retract procedures
*
************************************/
remove_clause(File,Term) :- 
	read_file_to_terms(File,Terms,[]),
	delete(Term,Terms,Res),
	tell(File),
	print_list(Res),
	told.

/***********************************
* 
* abolish procedures
*
************************************/
remove_all(File,Functor,Arity) :-
	consult(File),
	findall(
			Head:-Body,
			(
				predicate_property(Head, file(File)),
				clause(Head,Body)
			),
			Terms
			),
	delete(Functor, Arity,Terms,Res),
	tell(File),
	print_list(Res),
	told.

/***********************************
* 
* length procedures
*
************************************/
program_size(File,Size) :-
	consult(File),
	findall(
			Head:-Body,
			(
				predicate_property(Head, file(File)),
				clause(Head,Body)
			),
			L
			),
	length(L, Size).
	
	
	
/***********************************
* 
* list procedures
*
************************************/
clause_list(File,L) :-
	consult(File),
	findall(
			Head:-Body,
			(
				predicate_property(Head, file(File)),
				clause(Head,Body)
			),
			L
			).
	
