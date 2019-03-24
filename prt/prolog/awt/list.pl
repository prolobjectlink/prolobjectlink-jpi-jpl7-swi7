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

list_TOP_ALIGNMENT(OUT) :- 
	object_get('java.awt.List', top_alignment, OUT).

list_CENTER_ALIGNMENT(OUT) :- 
	object_get('java.awt.List', center_alignment, OUT).

list_BOTTOM_ALIGNMENT(OUT) :- 
	object_get('java.awt.List', bottom_alignment, OUT).

list_LEFT_ALIGNMENT(OUT) :- 
	object_get('java.awt.List', left_alignment, OUT).

list_RIGHT_ALIGNMENT(OUT) :- 
	object_get('java.awt.List', right_alignment, OUT).

list_WIDTH(OUT) :- 
	object_get('java.awt.List', width, OUT).

list_HEIGHT(OUT) :- 
	object_get('java.awt.List', height, OUT).

list_PROPERTIES(OUT) :- 
	object_get('java.awt.List', properties, OUT).

list_SOMEBITS(OUT) :- 
	object_get('java.awt.List', somebits, OUT).

list_FRAMEBITS(OUT) :- 
	object_get('java.awt.List', framebits, OUT).

list_ALLBITS(OUT) :- 
	object_get('java.awt.List', allbits, OUT).

list_ERROR(OUT) :- 
	object_get('java.awt.List', error, OUT).

list_ABORT(OUT) :- 
	object_get('java.awt.List', abort, OUT).

list(OUT) :- 
	object_new('java.awt.List', [], OUT).

list(ARG0, ARG1, OUT) :- 
	object_new('java.awt.List', '.'(ARG0, '.'(ARG1, [])), OUT).

list(ARG0, OUT) :- 
	object_new('java.awt.List', '.'(ARG0, []), OUT).

list_revalidate(REF) :- 
	object_call(REF, revalidate, [], _).

list_lost_focus(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, lostFocus, '.'(ARG0, '.'(ARG1, [])), OUT).

list_get_selected_objects(REF, OUT) :- 
	object_call(REF, getSelectedObjects, [], OUT).

list_get_key_listeners(REF, OUT) :- 
	object_call(REF, getKeyListeners, [], OUT).

list_print_all(REF, ARG0) :- 
	object_call(REF, printAll, '.'(ARG0, []), _).

list_set_size(REF, ARG0) :- 
	object_call(REF, setSize, '.'(ARG0, []), _).

list_get_toolkit(REF, OUT) :- 
	object_call(REF, getToolkit, [], OUT).

list_get_baseline(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getBaseline, '.'(ARG0, '.'(ARG1, [])), OUT).

list_set_size(REF, ARG0, ARG1) :- 
	object_call(REF, setSize, '.'(ARG0, '.'(ARG1, [])), _).

list_get_alignment_y(REF, OUT) :- 
	object_call(REF, getAlignmentY, [], OUT).

list_del_items(REF, ARG0, ARG1) :- 
	object_call(REF, delItems, '.'(ARG0, '.'(ARG1, [])), _).

list_remove(REF, ARG0) :- 
	object_call(REF, remove, '.'(ARG0, []), _).

list_remove(REF, ARG0) :- 
	object_call(REF, remove, '.'(ARG0, []), _).

list_remove(REF, ARG0) :- 
	object_call(REF, remove, '.'(ARG0, []), _).

list_get_location_on_screen(REF, OUT) :- 
	object_call(REF, getLocationOnScreen, [], OUT).

list_print(REF, ARG0) :- 
	object_call(REF, print, '.'(ARG0, []), _).

list_paint_all(REF, ARG0) :- 
	object_call(REF, paintAll, '.'(ARG0, []), _).

list_clear(REF) :- 
	object_call(REF, clear, [], _).

list_transfer_focus_up_cycle(REF) :- 
	object_call(REF, transferFocusUpCycle, [], _).

list_set_location(REF, ARG0) :- 
	object_call(REF, setLocation, '.'(ARG0, []), _).

list_set_minimum_size(REF, ARG0) :- 
	object_call(REF, setMinimumSize, '.'(ARG0, []), _).

list_set_visible(REF, ARG0) :- 
	object_call(REF, setVisible, '.'(ARG0, []), _).

list_set_location(REF, ARG0, ARG1) :- 
	object_call(REF, setLocation, '.'(ARG0, '.'(ARG1, [])), _).

list_is_minimum_size_set(REF, OUT) :- 
	object_call(REF, isMinimumSizeSet, [], OUT).

list_get_mouse_motion_listeners(REF, OUT) :- 
	object_call(REF, getMouseMotionListeners, [], OUT).

list_is_double_buffered(REF, OUT) :- 
	object_call(REF, isDoubleBuffered, [], OUT).

list_location(REF, OUT) :- 
	object_call(REF, location, [], OUT).

list_fire_property_change(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, firePropertyChange, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

list_fire_property_change(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, firePropertyChange, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

list_fire_property_change(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, firePropertyChange, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

list_fire_property_change(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, firePropertyChange, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

list_fire_property_change(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, firePropertyChange, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

list_fire_property_change(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, firePropertyChange, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), _).

list_minimum_size(REF, ARG0, OUT) :- 
	object_call(REF, minimumSize, '.'(ARG0, []), OUT).

list_minimum_size(REF, OUT) :- 
	object_call(REF, minimumSize, [], OUT).

list_add(REF, ARG0, ARG1) :- 
	object_call(REF, add, '.'(ARG0, '.'(ARG1, [])), _).

list_add(REF, ARG0) :- 
	object_call(REF, add, '.'(ARG0, []), _).

list_add(REF, ARG0) :- 
	object_call(REF, add, '.'(ARG0, []), _).

list_get_focus_listeners(REF, OUT) :- 
	object_call(REF, getFocusListeners, [], OUT).

list_get_parent(REF, OUT) :- 
	object_call(REF, getParent, [], OUT).

list_get_alignment_x(REF, OUT) :- 
	object_call(REF, getAlignmentX, [], OUT).

list_set_bounds(REF, ARG0, ARG1, ARG2, ARG3) :- 
	object_call(REF, setBounds, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), _).

list_got_focus(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, gotFocus, '.'(ARG0, '.'(ARG1, [])), OUT).

list_add_mouse_listener(REF, ARG0) :- 
	object_call(REF, addMouseListener, '.'(ARG0, []), _).

list_get_bounds(REF, OUT) :- 
	object_call(REF, getBounds, [], OUT).

list_set_bounds(REF, ARG0) :- 
	object_call(REF, setBounds, '.'(ARG0, []), _).

list_get_bounds(REF, ARG0, OUT) :- 
	object_call(REF, getBounds, '.'(ARG0, []), OUT).

list_notify(REF) :- 
	object_call(REF, notify, [], _).

list_count_items(REF, OUT) :- 
	object_call(REF, countItems, [], OUT).

list_get_maximum_size(REF, OUT) :- 
	object_call(REF, getMaximumSize, [], OUT).

list_get_color_model(REF, OUT) :- 
	object_call(REF, getColorModel, [], OUT).

list_set_preferred_size(REF, ARG0) :- 
	object_call(REF, setPreferredSize, '.'(ARG0, []), _).

list_inside(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, inside, '.'(ARG0, '.'(ARG1, [])), OUT).

list_set_background(REF, ARG0) :- 
	object_call(REF, setBackground, '.'(ARG0, []), _).

list_is_focus_owner(REF, OUT) :- 
	object_call(REF, isFocusOwner, [], OUT).

list_key_down(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, keyDown, '.'(ARG0, '.'(ARG1, [])), OUT).

list_set_drop_target(REF, ARG0) :- 
	object_call(REF, setDropTarget, '.'(ARG0, []), _).

list_get_cursor(REF, OUT) :- 
	object_call(REF, getCursor, [], OUT).

list_remove_all(REF) :- 
	object_call(REF, removeAll, [], _).

list_key_up(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, keyUp, '.'(ARG0, '.'(ARG1, [])), OUT).

list_add_key_listener(REF, ARG0) :- 
	object_call(REF, addKeyListener, '.'(ARG0, []), _).

list_is_displayable(REF, OUT) :- 
	object_call(REF, isDisplayable, [], OUT).

list_enable_input_methods(REF, ARG0) :- 
	object_call(REF, enableInputMethods, '.'(ARG0, []), _).

list_add_focus_listener(REF, ARG0) :- 
	object_call(REF, addFocusListener, '.'(ARG0, []), _).

list_prepare_image(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, prepareImage, '.'(ARG0, '.'(ARG1, [])), OUT).

list_get_component_listeners(REF, OUT) :- 
	object_call(REF, getComponentListeners, [], OUT).

list_prepare_image(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, prepareImage, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), OUT).

list_remove_mouse_motion_listener(REF, ARG0) :- 
	object_call(REF, removeMouseMotionListener, '.'(ARG0, []), _).

list_add_mouse_motion_listener(REF, ARG0) :- 
	object_call(REF, addMouseMotionListener, '.'(ARG0, []), _).

list_get_input_context(REF, OUT) :- 
	object_call(REF, getInputContext, [], OUT).

list_get_minimum_size(REF, OUT) :- 
	object_call(REF, getMinimumSize, [], OUT).

list_get_minimum_size(REF, ARG0, OUT) :- 
	object_call(REF, getMinimumSize, '.'(ARG0, []), OUT).

list_transfer_focus_backward(REF) :- 
	object_call(REF, transferFocusBackward, [], _).

list_do_layout(REF) :- 
	object_call(REF, doLayout, [], _).

list_is_cursor_set(REF, OUT) :- 
	object_call(REF, isCursorSet, [], OUT).

list_layout(REF) :- 
	object_call(REF, layout, [], _).

list_add_component_listener(REF, ARG0) :- 
	object_call(REF, addComponentListener, '.'(ARG0, []), _).

list_show(REF, ARG0) :- 
	object_call(REF, show, '.'(ARG0, []), _).

list_get_font(REF, OUT) :- 
	object_call(REF, getFont, [], OUT).

list_set_focusable(REF, ARG0) :- 
	object_call(REF, setFocusable, '.'(ARG0, []), _).

list_is_font_set(REF, OUT) :- 
	object_call(REF, isFontSet, [], OUT).

list_show(REF) :- 
	object_call(REF, show, [], _).

list_set_multiple_selections(REF, ARG0) :- 
	object_call(REF, setMultipleSelections, '.'(ARG0, []), _).

list_is_maximum_size_set(REF, OUT) :- 
	object_call(REF, isMaximumSizeSet, [], OUT).

list_mouse_move(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, mouseMove, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_get_ignore_repaint(REF, OUT) :- 
	object_call(REF, getIgnoreRepaint, [], OUT).

list_get_item_listeners(REF, OUT) :- 
	object_call(REF, getItemListeners, [], OUT).

list_add_property_change_listener(REF, ARG0, ARG1) :- 
	object_call(REF, addPropertyChangeListener, '.'(ARG0, '.'(ARG1, [])), _).

list_add_property_change_listener(REF, ARG0) :- 
	object_call(REF, addPropertyChangeListener, '.'(ARG0, []), _).

list_is_preferred_size_set(REF, OUT) :- 
	object_call(REF, isPreferredSizeSet, [], OUT).

list_get_selected_indexes(REF, OUT) :- 
	object_call(REF, getSelectedIndexes, [], OUT).

list_get_selected_item(REF, OUT) :- 
	object_call(REF, getSelectedItem, [], OUT).

list_set_enabled(REF, ARG0) :- 
	object_call(REF, setEnabled, '.'(ARG0, []), _).

list_deliver_event(REF, ARG0) :- 
	object_call(REF, deliverEvent, '.'(ARG0, []), _).

list_add_hierarchy_listener(REF, ARG0) :- 
	object_call(REF, addHierarchyListener, '.'(ARG0, []), _).

list_get_focus_traversal_keys_enabled(REF, OUT) :- 
	object_call(REF, getFocusTraversalKeysEnabled, [], OUT).

list_action(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, action, '.'(ARG0, '.'(ARG1, [])), OUT).

list_handle_event(REF, ARG0, OUT) :- 
	object_call(REF, handleEvent, '.'(ARG0, []), OUT).

list_deselect(REF, ARG0) :- 
	object_call(REF, deselect, '.'(ARG0, []), _).

list_set_locale(REF, ARG0) :- 
	object_call(REF, setLocale, '.'(ARG0, []), _).

list_list(REF, ARG0, ARG1) :- 
	object_call(REF, list, '.'(ARG0, '.'(ARG1, [])), _).

list_list(REF, ARG0, ARG1) :- 
	object_call(REF, list, '.'(ARG0, '.'(ARG1, [])), _).

list_set_component_orientation(REF, ARG0) :- 
	object_call(REF, setComponentOrientation, '.'(ARG0, []), _).

list_list(REF) :- 
	object_call(REF, list, [], _).

list_list(REF, ARG0) :- 
	object_call(REF, list, '.'(ARG0, []), _).

list_list(REF, ARG0) :- 
	object_call(REF, list, '.'(ARG0, []), _).

list_get_action_listeners(REF, OUT) :- 
	object_call(REF, getActionListeners, [], OUT).

list_get_graphics_configuration(REF, OUT) :- 
	object_call(REF, getGraphicsConfiguration, [], OUT).

list_is_visible(REF, OUT) :- 
	object_call(REF, isVisible, [], OUT).

list_get_foreground(REF, OUT) :- 
	object_call(REF, getForeground, [], OUT).

list_invalidate(REF) :- 
	object_call(REF, invalidate, [], _).

list_add_hierarchy_bounds_listener(REF, ARG0) :- 
	object_call(REF, addHierarchyBoundsListener, '.'(ARG0, []), _).

list_reshape(REF, ARG0, ARG1, ARG2, ARG3) :- 
	object_call(REF, reshape, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), _).

list_get_mouse_listeners(REF, OUT) :- 
	object_call(REF, getMouseListeners, [], OUT).

list_locate(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, locate, '.'(ARG0, '.'(ARG1, [])), OUT).

list_get_name(REF, OUT) :- 
	object_call(REF, getName, [], OUT).

list_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

list_is_valid(REF, OUT) :- 
	object_call(REF, isValid, [], OUT).

list_get_hierarchy_bounds_listeners(REF, OUT) :- 
	object_call(REF, getHierarchyBoundsListeners, [], OUT).

list_create_volatile_image(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, createVolatileImage, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_create_volatile_image(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, createVolatileImage, '.'(ARG0, '.'(ARG1, [])), OUT).

list_remove_mouse_wheel_listener(REF, ARG0) :- 
	object_call(REF, removeMouseWheelListener, '.'(ARG0, []), _).

list_move(REF, ARG0, ARG1) :- 
	object_call(REF, move, '.'(ARG0, '.'(ARG1, [])), _).

list_get_tree_lock(REF, OUT) :- 
	object_call(REF, getTreeLock, [], OUT).

list_add_input_method_listener(REF, ARG0) :- 
	object_call(REF, addInputMethodListener, '.'(ARG0, []), _).

list_remove_input_method_listener(REF, ARG0) :- 
	object_call(REF, removeInputMethodListener, '.'(ARG0, []), _).

list_get_location(REF, OUT) :- 
	object_call(REF, getLocation, [], OUT).

list_get_location(REF, ARG0, OUT) :- 
	object_call(REF, getLocation, '.'(ARG0, []), OUT).

list_set_name(REF, ARG0) :- 
	object_call(REF, setName, '.'(ARG0, []), _).

list_remove_key_listener(REF, ARG0) :- 
	object_call(REF, removeKeyListener, '.'(ARG0, []), _).

list_is_selected(REF, ARG0, OUT) :- 
	object_call(REF, isSelected, '.'(ARG0, []), OUT).

list_set_ignore_repaint(REF, ARG0) :- 
	object_call(REF, setIgnoreRepaint, '.'(ARG0, []), _).

list_request_focus_in_window(REF, OUT) :- 
	object_call(REF, requestFocusInWindow, [], OUT).

list_get_input_method_requests(REF, OUT) :- 
	object_call(REF, getInputMethodRequests, [], OUT).

list_add_action_listener(REF, ARG0) :- 
	object_call(REF, addActionListener, '.'(ARG0, []), _).

list_allows_multiple_selections(REF, OUT) :- 
	object_call(REF, allowsMultipleSelections, [], OUT).

list_get_visible_index(REF, OUT) :- 
	object_call(REF, getVisibleIndex, [], OUT).

list_mouse_enter(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, mouseEnter, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_set_foreground(REF, ARG0) :- 
	object_call(REF, setForeground, '.'(ARG0, []), _).

list_is_focus_traversable(REF, OUT) :- 
	object_call(REF, isFocusTraversable, [], OUT).

list_set_maximum_size(REF, ARG0) :- 
	object_call(REF, setMaximumSize, '.'(ARG0, []), _).

list_repaint(REF) :- 
	object_call(REF, repaint, [], _).

list_get_hierarchy_listeners(REF, OUT) :- 
	object_call(REF, getHierarchyListeners, [], OUT).

list_remove_property_change_listener(REF, ARG0, ARG1) :- 
	object_call(REF, removePropertyChangeListener, '.'(ARG0, '.'(ARG1, [])), _).

list_repaint(REF, ARG0) :- 
	object_call(REF, repaint, '.'(ARG0, []), _).

list_image_update(REF, ARG0, ARG1, ARG2, ARG3, ARG4, ARG5, OUT) :- 
	object_call(REF, imageUpdate, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, '.'(ARG4, '.'(ARG5, [])))))), OUT).

list_repaint(REF, ARG0, ARG1, ARG2, ARG3) :- 
	object_call(REF, repaint, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), _).

list_set_multiple_mode(REF, ARG0) :- 
	object_call(REF, setMultipleMode, '.'(ARG0, []), _).

list_remove_property_change_listener(REF, ARG0) :- 
	object_call(REF, removePropertyChangeListener, '.'(ARG0, []), _).

list_is_foreground_set(REF, OUT) :- 
	object_call(REF, isForegroundSet, [], OUT).

list_get_font_metrics(REF, ARG0, OUT) :- 
	object_call(REF, getFontMetrics, '.'(ARG0, []), OUT).

list_get_preferred_size(REF, OUT) :- 
	object_call(REF, getPreferredSize, [], OUT).

list_enable(REF, ARG0) :- 
	object_call(REF, enable, '.'(ARG0, []), _).

list_is_lightweight(REF, OUT) :- 
	object_call(REF, isLightweight, [], OUT).

list_get_preferred_size(REF, ARG0, OUT) :- 
	object_call(REF, getPreferredSize, '.'(ARG0, []), OUT).

list_enable(REF) :- 
	object_call(REF, enable, [], _).

list_are_focus_traversal_keys_set(REF, ARG0, OUT) :- 
	object_call(REF, areFocusTraversalKeysSet, '.'(ARG0, []), OUT).

list_get_component_at(REF, ARG0, OUT) :- 
	object_call(REF, getComponentAt, '.'(ARG0, []), OUT).

list_get_component_at(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getComponentAt, '.'(ARG0, '.'(ARG1, [])), OUT).

list_remove_item_listener(REF, ARG0) :- 
	object_call(REF, removeItemListener, '.'(ARG0, []), _).

list_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, '.'(ARG0, []), OUT).

list_is_index_selected(REF, ARG0, OUT) :- 
	object_call(REF, isIndexSelected, '.'(ARG0, []), OUT).

list_repaint(REF, ARG0, ARG1, ARG2, ARG3, ARG4) :- 
	object_call(REF, repaint, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, '.'(ARG4, []))))), _).

list_hide(REF) :- 
	object_call(REF, hide, [], _).

list_mouse_drag(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, mouseDrag, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_get_locale(REF, OUT) :- 
	object_call(REF, getLocale, [], OUT).

list_get_mouse_wheel_listeners(REF, OUT) :- 
	object_call(REF, getMouseWheelListeners, [], OUT).

list_get_property_change_listeners(REF, ARG0, OUT) :- 
	object_call(REF, getPropertyChangeListeners, '.'(ARG0, []), OUT).

list_get_rows(REF, OUT) :- 
	object_call(REF, getRows, [], OUT).

list_replace_item(REF, ARG0, ARG1) :- 
	object_call(REF, replaceItem, '.'(ARG0, '.'(ARG1, [])), _).

list_mouse_down(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, mouseDown, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_get_property_change_listeners(REF, OUT) :- 
	object_call(REF, getPropertyChangeListeners, [], OUT).

list_remove_hierarchy_bounds_listener(REF, ARG0) :- 
	object_call(REF, removeHierarchyBoundsListener, '.'(ARG0, []), _).

list_check_image(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, checkImage, '.'(ARG0, '.'(ARG1, '.'(ARG2, '.'(ARG3, [])))), OUT).

list_remove_focus_listener(REF, ARG0) :- 
	object_call(REF, removeFocusListener, '.'(ARG0, []), _).

list_check_image(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, checkImage, '.'(ARG0, '.'(ARG1, [])), OUT).

list_get_baseline_resize_behavior(REF, OUT) :- 
	object_call(REF, getBaselineResizeBehavior, [], OUT).

list_size(REF, OUT) :- 
	object_call(REF, size, [], OUT).

list_is_enabled(REF, OUT) :- 
	object_call(REF, isEnabled, [], OUT).

list_remove_mouse_listener(REF, ARG0) :- 
	object_call(REF, removeMouseListener, '.'(ARG0, []), _).

list_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

list_remove_component_listener(REF, ARG0) :- 
	object_call(REF, removeComponentListener, '.'(ARG0, []), _).

list_get_accessible_context(REF, OUT) :- 
	object_call(REF, getAccessibleContext, [], OUT).

list_get_drop_target(REF, OUT) :- 
	object_call(REF, getDropTarget, [], OUT).

list_is_showing(REF, OUT) :- 
	object_call(REF, isShowing, [], OUT).

list_update(REF, ARG0) :- 
	object_call(REF, update, '.'(ARG0, []), _).

list_mouse_up(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, mouseUp, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_validate(REF) :- 
	object_call(REF, validate, [], _).

list_is_focusable(REF, OUT) :- 
	object_call(REF, isFocusable, [], OUT).

list_dispatch_event(REF, ARG0) :- 
	object_call(REF, dispatchEvent, '.'(ARG0, []), _).

list_get_background(REF, OUT) :- 
	object_call(REF, getBackground, [], OUT).

list_paint(REF, ARG0) :- 
	object_call(REF, paint, '.'(ARG0, []), _).

list_get_x(REF, OUT) :- 
	object_call(REF, getX, [], OUT).

list_get_input_method_listeners(REF, OUT) :- 
	object_call(REF, getInputMethodListeners, [], OUT).

list_transfer_focus(REF) :- 
	object_call(REF, transferFocus, [], _).

list_get_focus_traversal_keys(REF, ARG0, OUT) :- 
	object_call(REF, getFocusTraversalKeys, '.'(ARG0, []), OUT).

list_add_item(REF, ARG0, ARG1) :- 
	object_call(REF, addItem, '.'(ARG0, '.'(ARG1, [])), _).

list_bounds(REF, OUT) :- 
	object_call(REF, bounds, [], OUT).

list_add_item(REF, ARG0) :- 
	object_call(REF, addItem, '.'(ARG0, []), _).

list_get_graphics(REF, OUT) :- 
	object_call(REF, getGraphics, [], OUT).

list_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

list_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

list_add_mouse_wheel_listener(REF, ARG0) :- 
	object_call(REF, addMouseWheelListener, '.'(ARG0, []), _).

list_get_peer(REF, OUT) :- 
	object_call(REF, getPeer, [], OUT).

list_add_notify(REF) :- 
	object_call(REF, addNotify, [], _).

list_get_items(REF, OUT) :- 
	object_call(REF, getItems, [], OUT).

list_get_mouse_position(REF, OUT) :- 
	object_call(REF, getMousePosition, [], OUT).

list_get_selected_index(REF, OUT) :- 
	object_call(REF, getSelectedIndex, [], OUT).

list_wait(REF, ARG0) :- 
	object_call(REF, wait, '.'(ARG0, []), _).

list_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, '.'(ARG0, '.'(ARG1, [])), _).

list_preferred_size(REF, ARG0, OUT) :- 
	object_call(REF, preferredSize, '.'(ARG0, []), OUT).

list_del_item(REF, ARG0) :- 
	object_call(REF, delItem, '.'(ARG0, []), _).

list_preferred_size(REF, OUT) :- 
	object_call(REF, preferredSize, [], OUT).

list_set_cursor(REF, ARG0) :- 
	object_call(REF, setCursor, '.'(ARG0, []), _).

list_set_focus_traversal_keys(REF, ARG0, ARG1) :- 
	object_call(REF, setFocusTraversalKeys, '.'(ARG0, '.'(ARG1, [])), _).

list_set_focus_traversal_keys_enabled(REF, ARG0) :- 
	object_call(REF, setFocusTraversalKeysEnabled, '.'(ARG0, []), _).

list_set_font(REF, ARG0) :- 
	object_call(REF, setFont, '.'(ARG0, []), _).

list_get_component_orientation(REF, OUT) :- 
	object_call(REF, getComponentOrientation, [], OUT).

list_remove_notify(REF) :- 
	object_call(REF, removeNotify, [], _).

list_has_focus(REF, OUT) :- 
	object_call(REF, hasFocus, [], OUT).

list_wait(REF) :- 
	object_call(REF, wait, [], _).

list_is_multiple_mode(REF, OUT) :- 
	object_call(REF, isMultipleMode, [], OUT).

list_remove_action_listener(REF, ARG0) :- 
	object_call(REF, removeActionListener, '.'(ARG0, []), _).

list_add_item_listener(REF, ARG0) :- 
	object_call(REF, addItemListener, '.'(ARG0, []), _).

list_get_focus_cycle_root_ancestor(REF, OUT) :- 
	object_call(REF, getFocusCycleRootAncestor, [], OUT).

list_is_focus_cycle_root(REF, ARG0, OUT) :- 
	object_call(REF, isFocusCycleRoot, '.'(ARG0, []), OUT).

list_get_y(REF, OUT) :- 
	object_call(REF, getY, [], OUT).

list_request_focus(REF) :- 
	object_call(REF, requestFocus, [], _).

list_create_image(REF, ARG0, OUT) :- 
	object_call(REF, createImage, '.'(ARG0, []), OUT).

list_select(REF, ARG0) :- 
	object_call(REF, select, '.'(ARG0, []), _).

list_post_event(REF, ARG0, OUT) :- 
	object_call(REF, postEvent, '.'(ARG0, []), OUT).

list_create_image(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, createImage, '.'(ARG0, '.'(ARG1, [])), OUT).

list_get_listeners(REF, ARG0, OUT) :- 
	object_call(REF, getListeners, '.'(ARG0, []), OUT).

list_is_opaque(REF, OUT) :- 
	object_call(REF, isOpaque, [], OUT).

list_next_focus(REF) :- 
	object_call(REF, nextFocus, [], _).

list_contains(REF, ARG0, OUT) :- 
	object_call(REF, contains, '.'(ARG0, []), OUT).

list_get_size(REF, OUT) :- 
	object_call(REF, getSize, [], OUT).

list_mouse_exit(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, mouseExit, '.'(ARG0, '.'(ARG1, '.'(ARG2, []))), OUT).

list_contains(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, contains, '.'(ARG0, '.'(ARG1, [])), OUT).

list_get_size(REF, ARG0, OUT) :- 
	object_call(REF, getSize, '.'(ARG0, []), OUT).

list_is_background_set(REF, OUT) :- 
	object_call(REF, isBackgroundSet, [], OUT).

list_get_selected_items(REF, OUT) :- 
	object_call(REF, getSelectedItems, [], OUT).

list_resize(REF, ARG0) :- 
	object_call(REF, resize, '.'(ARG0, []), _).

list_get_width(REF, OUT) :- 
	object_call(REF, getWidth, [], OUT).

list_get_height(REF, OUT) :- 
	object_call(REF, getHeight, [], OUT).

list_make_visible(REF, ARG0) :- 
	object_call(REF, makeVisible, '.'(ARG0, []), _).

list_disable(REF) :- 
	object_call(REF, disable, [], _).

list_apply_component_orientation(REF, ARG0) :- 
	object_call(REF, applyComponentOrientation, '.'(ARG0, []), _).

list_get_item_count(REF, OUT) :- 
	object_call(REF, getItemCount, [], OUT).

list_get_item(REF, ARG0, OUT) :- 
	object_call(REF, getItem, '.'(ARG0, []), OUT).

list_resize(REF, ARG0, ARG1) :- 
	object_call(REF, resize, '.'(ARG0, '.'(ARG1, [])), _).

list_remove_hierarchy_listener(REF, ARG0) :- 
	object_call(REF, removeHierarchyListener, '.'(ARG0, []), _).

