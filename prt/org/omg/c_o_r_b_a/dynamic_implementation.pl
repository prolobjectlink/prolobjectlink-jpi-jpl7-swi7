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

:-consult('../../../../obj/prolobject.pl').

dynamic_implementation(OUT) :- 
	object_new('org.omg.CORBA.DynamicImplementation', [], OUT).

dynamic_implementation__create_request(REF, ARG0, ARG1, ARG2, ARG3, ARG4, ARG5, OUT) :- 
	object_call(REF, '_create_request', '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, '.'(ARG4, '.'(ARG5, [])))))), OUT).

dynamic_implementation__invoke(REF, ARG0, OUT) :- 
	object_call(REF, '_invoke', '.'(ARG0, []), OUT).

dynamic_implementation__create_request(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, '_create_request', '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), OUT).

dynamic_implementation__set_delegate(REF, ARG0) :- 
	object_call(REF, '_set_delegate', '.'(ARG0, []), _).

dynamic_implementation_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

dynamic_implementation_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

dynamic_implementation__orb(REF, OUT) :- 
	object_call(REF, '_orb', [], OUT).

dynamic_implementation__get_interface_def(REF, OUT) :- 
	object_call(REF, '_get_interface_def', [], OUT).

dynamic_implementation__ids(REF, OUT) :- 
	object_call(REF, '_ids', [], OUT).

dynamic_implementation__get_policy(REF, ARG0, OUT) :- 
	object_call(REF, '_get_policy', '.'(ARG0, []), OUT).

dynamic_implementation__duplicate(REF, OUT) :- 
	object_call(REF, '_duplicate', [], OUT).

dynamic_implementation_wait(REF) :- 
	object_call(REF, wait, [], _).

dynamic_implementation_wait(REF, ARG0) :- 
	object_call(REF, wait, '.'(ARG0, []), _).

dynamic_implementation__release(REF) :- 
	object_call(REF, '_release', [], _).

dynamic_implementation_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, '.'(ARG0, '.'(ARG1, [])), _).

dynamic_implementation__is_a(REF, ARG0, OUT) :- 
	object_call(REF, '_is_a', '.'(ARG0, []), OUT).

dynamic_implementation__request(REF, ARG0, OUT) :- 
	object_call(REF, '_request', '.'(ARG0, []), OUT).

dynamic_implementation__get_delegate(REF, OUT) :- 
	object_call(REF, '_get_delegate', [], OUT).

dynamic_implementation__release_reply(REF, ARG0) :- 
	object_call(REF, '_releaseReply', '.'(ARG0, []), _).

dynamic_implementation__servant_preinvoke(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, '_servant_preinvoke', '.'(ARG0, '.'(ARG1, [])), OUT).

dynamic_implementation_notify(REF) :- 
	object_call(REF, notify, [], _).

dynamic_implementation_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

dynamic_implementation__is_local(REF, OUT) :- 
	object_call(REF, '_is_local', [], OUT).

dynamic_implementation__request(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, '_request', '.'(ARG0, '.'(ARG1, [])), OUT).

dynamic_implementation__get_domain_managers(REF, OUT) :- 
	object_call(REF, '_get_domain_managers', [], OUT).

dynamic_implementation__non_existent(REF, OUT) :- 
	object_call(REF, '_non_existent', [], OUT).

dynamic_implementation__hash(REF, ARG0, OUT) :- 
	object_call(REF, '_hash', '.'(ARG0, []), OUT).

dynamic_implementation__servant_postinvoke(REF, ARG0) :- 
	object_call(REF, '_servant_postinvoke', '.'(ARG0, []), _).

dynamic_implementation__set_policy_override(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, '_set_policy_override', '.'(ARG0, '.'(ARG1, [])), OUT).

dynamic_implementation_invoke(REF, ARG0) :- 
	object_call(REF, invoke, '.'(ARG0, []), _).

dynamic_implementation_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, '.'(ARG0, []), OUT).

dynamic_implementation__is_equivalent(REF, ARG0, OUT) :- 
	object_call(REF, '_is_equivalent', '.'(ARG0, []), OUT).

dynamic_implementation_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).
