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

initial_ldap_context_CONTROL_FACTORIES(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', control_factories, OUT).

initial_ldap_context_ADD_ATTRIBUTE(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', add_attribute, OUT).

initial_ldap_context_REPLACE_ATTRIBUTE(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', replace_attribute, OUT).

initial_ldap_context_REMOVE_ATTRIBUTE(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', remove_attribute, OUT).

initial_ldap_context_INITIAL_CONTEXT_FACTORY(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', initial_context_factory, OUT).

initial_ldap_context_OBJECT_FACTORIES(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', object_factories, OUT).

initial_ldap_context_STATE_FACTORIES(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', state_factories, OUT).

initial_ldap_context_URL_PKG_PREFIXES(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', url_pkg_prefixes, OUT).

initial_ldap_context_PROVIDER_URL(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', provider_url, OUT).

initial_ldap_context_DNS_URL(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', dns_url, OUT).

initial_ldap_context_AUTHORITATIVE(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', authoritative, OUT).

initial_ldap_context_BATCHSIZE(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', batchsize, OUT).

initial_ldap_context_REFERRAL(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', referral, OUT).

initial_ldap_context_SECURITY_PROTOCOL(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', security_protocol, OUT).

initial_ldap_context_SECURITY_AUTHENTICATION(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', security_authentication, OUT).

initial_ldap_context_SECURITY_PRINCIPAL(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', security_principal, OUT).

initial_ldap_context_SECURITY_CREDENTIALS(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', security_credentials, OUT).

initial_ldap_context_LANGUAGE(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', language, OUT).

initial_ldap_context_APPLET(OUT) :- 
	object_get('javax.naming.ldap.InitialLdapContext', applet, OUT).

initial_ldap_context(OUT) :- 
	object_new('javax.naming.ldap.InitialLdapContext', [], OUT).

initial_ldap_context(ARG0, ARG1, OUT) :- 
	object_new('javax.naming.ldap.InitialLdapContext', '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_get_attributes(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getAttributes, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_get_attributes(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getAttributes, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_extended_operation(REF, ARG0, OUT) :- 
	object_call(REF, extendedOperation, '.'(ARG0, []), OUT).

initial_ldap_context_set_request_controls(REF, ARG0) :- 
	object_call(REF, setRequestControls, '.'(ARG0, []), _).

initial_ldap_context_get_schema(REF, ARG0, OUT) :- 
	object_call(REF, getSchema, '.'(ARG0, []), OUT).

initial_ldap_context_get_schema(REF, ARG0, OUT) :- 
	object_call(REF, getSchema, '.'(ARG0, []), OUT).

initial_ldap_context_create_subcontext(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, createSubcontext, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_create_subcontext(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, createSubcontext, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_compose_name(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, composeName, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_compose_name(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, composeName, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_reconnect(REF, ARG0) :- 
	object_call(REF, reconnect, '.'(ARG0, []), _).

initial_ldap_context_do_lookup(REF, ARG0, OUT) :- 
	object_call(REF, doLookup, '.'(ARG0, []), OUT).

initial_ldap_context_do_lookup(REF, ARG0, OUT) :- 
	object_call(REF, doLookup, '.'(ARG0, []), OUT).

initial_ldap_context_lookup_link(REF, ARG0, OUT) :- 
	object_call(REF, lookupLink, '.'(ARG0, []), OUT).

initial_ldap_context_lookup_link(REF, ARG0, OUT) :- 
	object_call(REF, lookupLink, '.'(ARG0, []), OUT).

initial_ldap_context_unbind(REF, ARG0) :- 
	object_call(REF, unbind, '.'(ARG0, []), _).

initial_ldap_context_unbind(REF, ARG0) :- 
	object_call(REF, unbind, '.'(ARG0, []), _).

initial_ldap_context_create_subcontext(REF, ARG0, OUT) :- 
	object_call(REF, createSubcontext, '.'(ARG0, []), OUT).

initial_ldap_context_create_subcontext(REF, ARG0, OUT) :- 
	object_call(REF, createSubcontext, '.'(ARG0, []), OUT).

initial_ldap_context_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, '.'(ARG0, []), OUT).

initial_ldap_context_notify(REF) :- 
	object_call(REF, notify, [], _).

initial_ldap_context_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

initial_ldap_context_add_to_environment(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, addToEnvironment, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

initial_ldap_context_list_bindings(REF, ARG0, OUT) :- 
	object_call(REF, listBindings, '.'(ARG0, []), OUT).

initial_ldap_context_list_bindings(REF, ARG0, OUT) :- 
	object_call(REF, listBindings, '.'(ARG0, []), OUT).

initial_ldap_context_get_schema_class_definition(REF, ARG0, OUT) :- 
	object_call(REF, getSchemaClassDefinition, '.'(ARG0, []), OUT).

initial_ldap_context_get_schema_class_definition(REF, ARG0, OUT) :- 
	object_call(REF, getSchemaClassDefinition, '.'(ARG0, []), OUT).

initial_ldap_context_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

initial_ldap_context_get_request_controls(REF, OUT) :- 
	object_call(REF, getRequestControls, [], OUT).

initial_ldap_context_new_instance(REF, ARG0, OUT) :- 
	object_call(REF, newInstance, '.'(ARG0, []), OUT).

initial_ldap_context_get_connect_controls(REF, OUT) :- 
	object_call(REF, getConnectControls, [], OUT).

initial_ldap_context_modify_attributes(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, modifyAttributes, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

initial_ldap_context_modify_attributes(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, modifyAttributes, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

initial_ldap_context_get_attributes(REF, ARG0, OUT) :- 
	object_call(REF, getAttributes, '.'(ARG0, []), OUT).

initial_ldap_context_get_attributes(REF, ARG0, OUT) :- 
	object_call(REF, getAttributes, '.'(ARG0, []), OUT).

initial_ldap_context_modify_attributes(REF, ARG0, ARG1) :- 
	object_call(REF, modifyAttributes, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_modify_attributes(REF, ARG0, ARG1) :- 
	object_call(REF, modifyAttributes, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_remove_from_environment(REF, ARG0, OUT) :- 
	object_call(REF, removeFromEnvironment, '.'(ARG0, []), OUT).

initial_ldap_context_get_response_controls(REF, OUT) :- 
	object_call(REF, getResponseControls, [], OUT).

initial_ldap_context_wait(REF) :- 
	object_call(REF, wait, [], _).

initial_ldap_context_get_name_parser(REF, ARG0, OUT) :- 
	object_call(REF, getNameParser, '.'(ARG0, []), OUT).

initial_ldap_context_get_name_parser(REF, ARG0, OUT) :- 
	object_call(REF, getNameParser, '.'(ARG0, []), OUT).

initial_ldap_context_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_wait(REF, ARG0) :- 
	object_call(REF, wait, '.'(ARG0, []), _).

initial_ldap_context_search(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_search(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, search, '.'(ARG0, '.'(ARG1, [])), OUT).

initial_ldap_context_rebind(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, rebind, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

initial_ldap_context_rebind(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, rebind, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

initial_ldap_context_rebind(REF, ARG0, ARG1) :- 
	object_call(REF, rebind, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_rebind(REF, ARG0, ARG1) :- 
	object_call(REF, rebind, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_get_name_in_namespace(REF, OUT) :- 
	object_call(REF, getNameInNamespace, [], OUT).

initial_ldap_context_get_environment(REF, OUT) :- 
	object_call(REF, getEnvironment, [], OUT).

initial_ldap_context_bind(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, bind, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

initial_ldap_context_bind(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, bind, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

initial_ldap_context_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

initial_ldap_context_bind(REF, ARG0, ARG1) :- 
	object_call(REF, bind, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_bind(REF, ARG0, ARG1) :- 
	object_call(REF, bind, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_destroy_subcontext(REF, ARG0) :- 
	object_call(REF, destroySubcontext, '.'(ARG0, []), _).

initial_ldap_context_destroy_subcontext(REF, ARG0) :- 
	object_call(REF, destroySubcontext, '.'(ARG0, []), _).

initial_ldap_context_rename(REF, ARG0, ARG1) :- 
	object_call(REF, rename, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_rename(REF, ARG0, ARG1) :- 
	object_call(REF, rename, '.'(ARG0, '.'(ARG1, [])), _).

initial_ldap_context_lookup(REF, ARG0, OUT) :- 
	object_call(REF, lookup, '.'(ARG0, []), OUT).

initial_ldap_context_lookup(REF, ARG0, OUT) :- 
	object_call(REF, lookup, '.'(ARG0, []), OUT).

initial_ldap_context_list(REF, ARG0, OUT) :- 
	object_call(REF, list, '.'(ARG0, []), OUT).

initial_ldap_context_list(REF, ARG0, OUT) :- 
	object_call(REF, list, '.'(ARG0, []), OUT).

initial_ldap_context_close(REF) :- 
	object_call(REF, close, [], _).

