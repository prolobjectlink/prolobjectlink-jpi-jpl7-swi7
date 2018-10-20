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