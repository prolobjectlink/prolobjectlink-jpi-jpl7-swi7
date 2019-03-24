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

:-consult('../../../obj/prolobject.pl').

boolean_TRUE(OUT) :- 
	object_get('java.lang.Boolean', true, OUT).

boolean_FALSE(OUT) :- 
	object_get('java.lang.Boolean', false, OUT).

boolean_TYPE(OUT) :- 
	object_get('java.lang.Boolean', type, OUT).

boolean(ARG0, OUT) :- 
	object_new('java.lang.Boolean', '.'(ARG0, []), OUT).

boolean(ARG0, OUT) :- 
	object_new('java.lang.Boolean', '.'(ARG0, []), OUT).

boolean_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

boolean_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, '.'(ARG0, '.'(ARG1, [])), _).

boolean_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

boolean_wait(REF, ARG0) :- 
	object_call(REF, wait, '.'(ARG0, []), _).

boolean_wait(REF) :- 
	object_call(REF, wait, [], _).

boolean_boolean_value(REF, OUT) :- 
	object_call(REF, booleanValue, [], OUT).

boolean_logical_and(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, logicalAnd, '.'(ARG0, '.'(ARG1, [])), OUT).

boolean_logical_or(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, logicalOr, '.'(ARG0, '.'(ARG1, [])), OUT).

boolean_parse_boolean(REF, ARG0, OUT) :- 
	object_call(REF, parseBoolean, '.'(ARG0, []), OUT).

boolean_get_boolean(REF, ARG0, OUT) :- 
	object_call(REF, getBoolean, '.'(ARG0, []), OUT).

boolean_value_of(REF, ARG0, OUT) :- 
	object_call(REF, valueOf, '.'(ARG0, []), OUT).

boolean_value_of(REF, ARG0, OUT) :- 
	object_call(REF, valueOf, '.'(ARG0, []), OUT).

boolean_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

boolean_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

boolean_hash_code(REF, ARG0, OUT) :- 
	object_call(REF, hashCode, '.'(ARG0, []), OUT).

boolean_to_string(REF, ARG0, OUT) :- 
	object_call(REF, toString, '.'(ARG0, []), OUT).

boolean_logical_xor(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, logicalXor, '.'(ARG0, '.'(ARG1, [])), OUT).

boolean_notify(REF) :- 
	object_call(REF, notify, [], _).

boolean_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, '.'(ARG0, []), OUT).

boolean_compare(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, compare, '.'(ARG0, '.'(ARG1, [])), OUT).

boolean_compare_to(REF, ARG0, OUT) :- 
	object_call(REF, compareTo, '.'(ARG0, []), OUT).

boolean_compare_to(REF, ARG0, OUT) :- 
	object_call(REF, compareTo, '.'(ARG0, []), OUT).

