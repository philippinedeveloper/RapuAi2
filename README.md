<div align="center">
<h1><kbd>🧩 Rapu</kbd></h1>
An extension for MIT App Inventor 2.<br>
</div>

## 📝 Specifications
* **
💾 **Size:** 22 KB
⚙️ **Version:** 3.3
📱 **Minimum API Level:** 7
📅 **Updated On:** January 14, 2025 (Asia/Taipei)

## <kbd>Events:</kbd>
**Rapu** has total 9 events.

### 💛 TouchDown
Triggered when the user touches down on the component

### 💛 TouchUp
Triggered when the user releases the touch on the component

### 💛 Click
Triggered when the user clicks the component

### 💛 LongClick
Triggered when the user performs a long click on the component

### 💛 DragStart
Triggered when a drag operation starts

| Parameter | Type
| - | - |
| component | component

### 💛 Dragged
Triggered when the component is being dragged

| Parameter | Type
| - | - |
| component | component
| x | number
| y | number

### 💛 Drop
Triggered when the component is dropped

| Parameter | Type
| - | - |
| component | component
| x | number
| y | number

### 💛 GotFocus
This event is fired when a registered component is focused.

| Parameter | Type
| - | - |
| component | component

### 💛 LostFocus
This event is fired when a registered component has focus removed.

| Parameter | Type
| - | - |
| component | component

## <kbd>Methods:</kbd>
**Rapu** has total 55 methods.

### 💜 BackgroundColor
Method for BackgroundColor

| Parameter | Type
| - | - |
| component | component
| backgroundColor | number

### 💜 TextColor
Method for TextColor

| Parameter | Type
| - | - |
| component | component
| textColor | number

### 💜 Text
Method for Text

| Parameter | Type
| - | - |
| component | component
| text | text

### 💜 Copy
Copies every component on-screen by creating components dynamically

| Parameter | Type
| - | - |
| layout | component
| id | number

### 💜 Create
Creates components dynamically

| Parameter | Type
| - | - |
| layout | component
| componentName | text
| id | number

### 💜 Remove
Removes a dynamically created component

| Parameter | Type
| - | - |
| layout | component
| componentName | text

### 💜 Move
Moves a dynamically created component to another component

| Parameter | Type
| - | - |
| layout | component
| newLayout | component
| componentName | text

### 💜 EnableTouchListener
Enables touch listener on a component

| Parameter | Type
| - | - |
| component | component

### 💜 EnableLongClickListener
Enables long click listener on a component

| Parameter | Type
| - | - |
| component | component

### 💜 EnableClickListener
Enables click listener on a component

| Parameter | Type
| - | - |
| component | component

### 💜 GetPaddingTop
Gets the padding top of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetPaddingBottom
Gets the padding bottom of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetOpacity
Gets the opacity of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetElevation
Gets the elevation of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetLeft
Gets the left position of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetRight
Gets the right position of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetPivotX
Gets the pivot X value of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetPivotY
Gets the pivot Y value of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetRotationX
Gets the rotation X value of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 GetRotationY
Gets the rotation Y value of a given view

| Parameter | Type
| - | - |
| component | component

### 💜 SetPaddingTop
Sets the padding top of a given view

| Parameter | Type
| - | - |
| component | component
| paddingTop | number

### 💜 SetPaddingBottom
Sets the padding bottom of a given view

| Parameter | Type
| - | - |
| component | component
| paddingBottom | number

### 💜 SetOpacity
Sets the opacity of a given view

| Parameter | Type
| - | - |
| component | component
| opacity | number

### 💜 SetElevation
Sets the elevation of a given view

| Parameter | Type
| - | - |
| component | component
| elevation | number

### 💜 SetLeft
Sets the left position of a given view

| Parameter | Type
| - | - |
| component | component
| left | number

### 💜 SetRight
Sets the right position of a given view

| Parameter | Type
| - | - |
| component | component
| right | number

### 💜 SetPivotX
Sets the pivot X value of a given view

| Parameter | Type
| - | - |
| component | component
| pivotX | number

### 💜 SetPivotY
Sets the pivot Y value of a given view

| Parameter | Type
| - | - |
| component | component
| pivotY | number

### 💜 SetRotationX
Sets the rotation X value of a given view

| Parameter | Type
| - | - |
| component | component
| rotationX | number

### 💜 SetRotationY
Sets the rotation Y value of a given view

| Parameter | Type
| - | - |
| component | component
| rotationY | number

### 💜 ListComponents
Lists all existing components

### 💜 GetParent
Gets the parent component of a given component

| Parameter | Type
| - | - |
| component | component

### 💜 GetChildren
Gets the children of a given component

| Parameter | Type
| - | - |
| component | component

### 💜 EnableDragAndDrop
Enables drag and drop functionality on a component

| Parameter | Type
| - | - |
| component | component

### 💜 GetTranslationX
Returns the horizontal location of this component relative to its left position. This position is post-layout, in addition to wherever the object's layout placed it.

| Parameter | Type
| - | - |
| component | component

### 💜 GetTranslationY
Returns the horizontal location of this component relative to its top position. This position is post-layout, in addition to wherever the object's layout placed it.

| Parameter | Type
| - | - |
| component | component

### 💜 GetTranslationZ
Returns the depth location of this component relative to its elevation.

| Parameter | Type
| - | - |
| component | component

### 💜 GetScaleX
Returns the amount that the component is scaled in x around the pivot point, as a proportion of the view's unscaled width.

| Parameter | Type
| - | - |
| component | component

### 💜 GetScaleY
Returns the amount that the component is scaled in y around the pivot point, as a proportion of the view's unscaled height.

| Parameter | Type
| - | - |
| component | component

### 💜 GetScrollBarSize
Returns the scrollbar size for this component.

| Parameter | Type
| - | - |
| component | component

### 💜 GetScrollX
Return the scrolled left position of this component. This is the left edge of the displayed part of your component. You do not need to draw any pixels farther left, since those are outside of the frame of your component on screen.

| Parameter | Type
| - | - |
| component | component

### 💜 GetScrollY
Return the scrolled top position of this component. This is the top edge of the displayed part of your component. You do not need to draw any pixels farther top, since those are outside of the frame of your component on screen.

| Parameter | Type
| - | - |
| component | component

### 💜 SetScrollBarSize
Sets the size of the scrollbar.

| Parameter | Type
| - | - |
| component | component
| size | number

### 💜 SetTranslationX
Sets the horizontal location of this component relative to its left position.

| Parameter | Type
| - | - |
| component | component
| translationX | number

### 💜 SetTranslationY
Sets the vertical location of this component relative to its top position.

| Parameter | Type
| - | - |
| component | component
| translationY | number

### 💜 SetScaleX
Sets the amount that the component is scaled in x around the pivot point, as a proportion of the component's unscaled width. A value of 1 means that no scaling is applied.

| Parameter | Type
| - | - |
| component | component
| scaleX | number

### 💜 SetScaleY
Sets the amount that the component is scaled in y around the pivot point, as a proportion of the component's unscaled height. A value of 1 means that no scaling is applied.

| Parameter | Type
| - | - |
| component | component
| scaleY | number

### 💜 GetBottom
Returns the bottom of this component, in pixels.

| Parameter | Type
| - | - |
| component | component

### 💜 GetTop
Returns the top edge of this component, in pixels.

| Parameter | Type
| - | - |
| component | component

### 💜 SetTop
Sets the top position of this component relative to its parent, in pixels.

| Parameter | Type
| - | - |
| component | component
| top | number

### 💜 SetBottom
Sets the bottom position of this component relative to its parent, in pixels.

| Parameter | Type
| - | - |
| component | component
| bottom | number

### 💜 GetComponent
Gets component using name & id

| Parameter | Type
| - | - |
| componentName | text

### 💜 IsFocusable
Checks whether the component is currently able to take focus.

| Parameter | Type
| - | - |
| component | component

### 💜 SetFocusable
Set whether this component can receive the focus.

| Parameter | Type
| - | - |
| component | component
| focusable | boolean

### 💜 RegisterFocus
Registers the component so that when the user focuses or removes focus for the component, it will fire the respective event.

| Parameter | Type
| - | - |
| component | component

