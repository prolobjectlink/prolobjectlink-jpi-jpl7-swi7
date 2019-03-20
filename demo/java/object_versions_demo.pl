% Copyright (c) 2019 Prolobjectlink Project

% Permission is hereby granted, free of charge, to any person obtaining a copy
% of this software and associated documentation files (the "Software"), to deal
% in the Software without restriction, including without limitation the rights
% to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
% copies of the Software, and to permit persons to whom the Software is
% furnished to do so, subject to the following conditions:

% The above copyright notice and this permission notice shall be included in
% all copies or substantial portions of the Software.

% THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
% IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
% FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
% AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
% LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
% OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
% THE SOFTWARE.

% Author: Jose Zalacain

:-consult('../../obj/prolobject.pl').

jpl_versions_demo :-
    jpl_call('org.jpl7.JPL', version_string, [], Vj),
    jpl_c_lib_version(Vc),
    jpl_pl_lib_version(Vp),
    nl,
    write('prolog library version: '), write( Vp), nl,
    write('  java library version: '), write( Vj), nl,
    write('     c library version: '), write( Vc), nl,
    (       Vp == Vj,
            Vj == Vc
    ->      write('BINGO! you appear to have the same version of each library installed'), nl
    ;       write('WHOOPS! you appear not to have the same version of each library installed'), nl
    ),
    nl.


% this directive runs the above demo

:- jpl_versions_demo.

