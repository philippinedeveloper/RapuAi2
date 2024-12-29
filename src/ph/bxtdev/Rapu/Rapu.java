/* Extension written by Bextdev, written on the 28th of December 2024
 * 
 * Bits of code written by yusufcihan & Kumaraswamy B G
 *
 * Licensed under GNU General Public License V2
 *
 */

package ph.bxtdev.Rapu;

import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.MotionEvent;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.*;
import com.google.appinventor.components.runtime.errors.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import gnu.lists.LList;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import com.google.appinventor.components.runtime.Component;

@DesignerComponent(
        version = 6,
        versionName = "1.5",
        description = "Gets List of Components & Using the List, It will create components",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://raw.githubusercontent.com/bextdev/Rapu/refs/heads/main/assets/direction%20(1).png"
)
@SimpleObject(external = true)
@UsesLibraries(libraries = "")
@UsesPermissions(permissionNames = "")
public class Rapu extends AndroidNonvisibleComponent {

    private Context context;
    private Activity activity;
    private Form form;
    private Map<String, Component> components = new HashMap<>();
    private Map<String, Component> newComponents = new HashMap<>();

    public Rapu(ComponentContainer container) {
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
        this.form = container.$form();
    }

    @SimpleFunction(description = "Enables touch listener on a component")
    public void EnableTouchListener(AndroidViewComponent component) {
        View view = component.getView();
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        TouchDown();
                        break;
                    case MotionEvent.ACTION_UP:
                        TouchUp();
                        break;
                }
                return true; // Return true to indicate the touch event has been handled
            }
        });
    }

    @SimpleEvent(description = "Triggered when the user touches down on the component")
    public void TouchDown() {
        EventDispatcher.dispatchEvent(this, "TouchDown");
    }

    @SimpleEvent(description = "Triggered when the user releases the touch on the component")
    public void TouchUp() {
        EventDispatcher.dispatchEvent(this, "TouchUp");
    }

    @SimpleFunction(description = "Enables long click listener on a component")
    public void EnableLongClickListener(AndroidViewComponent component) {
        View view = component.getView();
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LongClick();
                return true; // Return true to indicate the event is consumed
            }
        });
    }

    @SimpleEvent(description = "Triggered when the user performs a long click on the component")
    public void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick");
    }

    @SimpleFunction(description = "Gets the padding top of a given view")
    public int GetPaddingTop(AndroidViewComponent layout) {
        return layout.getView().getPaddingTop();
    }

    @SimpleFunction(description = "Gets the padding bottom of a given view")
    public int GetPaddingBottom(AndroidViewComponent layout) {
        return layout.getView().getPaddingBottom();
    }

    @SimpleFunction(description = "Gets the opacity of a given view")
    public int GetOpacity(AndroidViewComponent layout) {
        return (int) layout.getView().getAlpha(); // getAlpha() returns a float, so cast it to int if you want an integer representation
    }

    @SimpleFunction(description = "Gets the elevation of a given view")
    public float GetElevation(AndroidViewComponent layout) {
        return layout.getView().getElevation();
    }

    @SimpleFunction(description = "Gets the left position of a given view")
    public int GetLeft(AndroidViewComponent layout) {
        return layout.getView().getLeft();
    }

    @SimpleFunction(description = "Gets the right position of a given view")
    public int GetRight(AndroidViewComponent layout) {
        return layout.getView().getRight();
    }

    @SimpleFunction(description = "Gets the pivot X value of a given view")
    public float GetPivotX(AndroidViewComponent layout) {
        return layout.getView().getPivotX();
    }

    @SimpleFunction(description = "Gets the pivot Y value of a given view")
    public float GetPivotY(AndroidViewComponent layout) {
        return layout.getView().getPivotY();
    }

    @SimpleFunction(description = "Gets the rotation X value of a given view")
    public float GetRotationX(AndroidViewComponent layout) {
        return layout.getView().getRotationX();
    }

    @SimpleFunction(description = "Gets the rotation Y value of a given view")
    public float GetRotationY(AndroidViewComponent layout) {
        return layout.getView().getRotationY();
    }

    @SimpleFunction(description = "Sets the padding top of a given view")
    public void SetPaddingTop(AndroidViewComponent layout, int paddingTop) {
        layout.getView().setPadding(layout.getView().getPaddingLeft(), paddingTop, layout.getView().getPaddingRight(), layout.getView().getPaddingBottom());
    }

    @SimpleFunction(description = "Sets the padding bottom of a given view")
    public void SetPaddingBottom(AndroidViewComponent layout, int paddingBottom) {
        layout.getView().setPadding(layout.getView().getPaddingLeft(), layout.getView().getPaddingTop(), layout.getView().getPaddingRight(), paddingBottom);
    }

    @SimpleFunction(description = "Sets the opacity of a given view")
    public void SetOpacity(AndroidViewComponent layout, float opacity) {
        layout.getView().setAlpha(opacity); // Opacity is controlled by alpha value (0.0 - 1.0)
    }

    @SimpleFunction(description = "Sets the elevation of a given view")
    public void SetElevation(AndroidViewComponent layout, float elevation) {
        layout.getView().setElevation(elevation);
    }

    @SimpleFunction(description = "Sets the left position of a given view")
    public void SetLeft(AndroidViewComponent layout, int left) {
        layout.getView().setLeft(left); // This method doesn't really work as expected, use layoutParams for true positioning
    }

    @SimpleFunction(description = "Sets the right position of a given view")
    public void SetRight(AndroidViewComponent layout, int right) {
        layout.getView().setRight(right); // Same as left, use layoutParams for true positioning
    }

    @SimpleFunction(description = "Sets the pivot X value of a given view")
    public void SetPivotX(AndroidViewComponent layout, float pivotX) {
        layout.getView().setPivotX(pivotX);
    }

    @SimpleFunction(description = "Sets the pivot Y value of a given view")
    public void SetPivotY(AndroidViewComponent layout, float pivotY) {
        layout.getView().setPivotY(pivotY);
    }

    @SimpleFunction(description = "Sets the rotation X value of a given view")
    public void SetRotationX(AndroidViewComponent layout, float rotationX) {
        layout.getView().setRotationX(rotationX);
    }

    @SimpleFunction(description = "Sets the rotation Y value of a given view")
    public void SetRotationY(AndroidViewComponent layout, float rotationY) {
        layout.getView().setRotationY(rotationY);
    }

    @SimpleFunction(description = "Lists all existing components")
    public String ListComponents() {
        try {
            components = form instanceof ReplForm ? mapComponentsRepl() : mapComponents();
            return components.keySet().toString();
        } catch (NoSuchFieldException e) {
            Log.e("Rapu", "No such field exception: " + e.getMessage());
            return "Error: No such field exception.";
        } catch (IllegalAccessException e) {
            Log.e("Rapu", "Illegal access exception: " + e.getMessage());
            return "Error: Illegal access exception.";
        }
    }

    @SimpleFunction(description = "Sets a property on a given component dynamically")
    public void SetProperty(String componentName, String propertyName, Object value) {
        try {
            Component component = components.get(componentName);
            if (component == null) {
                throw new YailRuntimeError("Component not found: " + componentName, "SetProperty");
            }

            Method propertyMethod = null;
            for (Method method : component.getClass().getMethods()) {
                if (method.getName().equals(propertyName) && method.getParameterCount() == 1) {
                    propertyMethod = method;
                    break;
                }
            }

            if (propertyMethod == null) {
                throw new YailRuntimeError("Property method not found: " + propertyName, "SetProperty");
            }

            Class<?> parameterType = propertyMethod.getParameterTypes()[0];
            Object convertedValue = convertValue(value, parameterType);
            propertyMethod.invoke(component, convertedValue);
        } catch (Exception e) {
            Log.e("Rapu", "Error in SetProperty", e);
            throw new YailRuntimeError("Error setting property: " + e.getMessage(), "SetProperty");
        }
    }

    @SimpleFunction(description = "Allows a component to be draggable within its parent layout")
    public void EnableDrag(AndroidViewComponent component) {
        View view = component.getView();
        view.setOnTouchListener(new View.OnTouchListener() {
            private float dX, dY;
            private int lastAction;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() + dX);
                        v.setY(event.getRawY() + dY);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            TouchReleased();
                        }
                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private Object convertValue(Object value, Class<?> parameterType) {
        if (value == null) return null;

        try {
            if (parameterType == int.class || parameterType == Integer.class) {
                return Integer.parseInt(value.toString());
            } else if (parameterType == float.class || parameterType == Float.class) {
                return Float.parseFloat(value.toString());
            } else if (parameterType == double.class || parameterType == Double.class) {
                return Double.parseDouble(value.toString());
            } else if (parameterType == boolean.class || parameterType == Boolean.class) {
                return Boolean.parseBoolean(value.toString());
            } else if (parameterType == String.class) {
                return value.toString();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert value to " + parameterType.getName());
        }
        return null;
    }

    @SimpleFunction(description = "Sets a click listener on a given component")
    public void SetClickListener(String componentName) {
        Component component = components.get(componentName);
        if (component instanceof AndroidViewComponent) {
            View view = ((AndroidViewComponent) component).getView();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Click();
                }
            });
        } else {
            throw new YailRuntimeError("Component is not clickable: " + componentName, "SetClickListener");
        }
    }

    @SimpleEvent(description = "When clicked this event will be fired and do whatever user wants")
    public void Click() {
        EventDispatcher.dispatchEvent(this, "Click");
    }

    @SimpleFunction(description = "Copies every component on-screen by creating components dynamically")
    public void Copy(AndroidViewComponent layout, int id) {
        try {
            components = form instanceof ReplForm ? mapComponentsRepl() : mapComponents();

            for (Map.Entry<String, Component> entry : components.entrySet()) {
                String componentName = entry.getKey();
                Component originalComponent = entry.getValue();

                String originalComponentName = originalComponent.getClass().getSimpleName();

                try {
                    Create(layout, originalComponentName, id);
                } catch (Exception e) {
                    Log.e("Rapu", "Error copying component: " + componentName, e);
                }
            }
        } catch (Exception e) {
            Log.e("Rapu", "Error in Copy: ", e);
        }
    }
 
   
    @SimpleFunction(description = "Creates components dynamically")
    public void Create(AndroidViewComponent layout, String componentName, int id) {
        try {
            Class<?> clazz = Class.forName("com.google.appinventor.components.runtime." + componentName);
            Constructor<?> constructor = clazz.getConstructor(ComponentContainer.class);
            Component newComponent = (Component) constructor.newInstance(layout);
            newComponents.put(componentName + id + "true", newComponent);
        } catch (Exception e) {
            Log.e("Rapu", "Error creating component: " + componentName, e);
        }
    }

    @SimpleFunction(description = "Removes a dynamically created component")
    public void Remove(AndroidViewComponent layout, String componentName) {
        Component component = newComponents.get(componentName + "true");
        if (component instanceof AndroidViewComponent) {
            AndroidViewComponent androidComponent = (AndroidViewComponent) component;
            View view = layout.getView();

            if (view instanceof ViewGroup) {
                ViewGroup layoutView = (ViewGroup) view;
                View componentView = androidComponent.getView();
                if (componentView.getParent() != null) {
                    ((ViewGroup) componentView.getParent()).removeView(componentView);
                }
                layoutView.removeView(componentView);
                newComponents.remove(componentName);
            }
        }
    }

    @SimpleFunction(description = "Moves a dynamically created component to another layout")
    public void Move(AndroidViewComponent layout, AndroidViewComponent newLayout, String componentName) {
        Component component = newComponents.get(componentName + "true");
        if (component instanceof AndroidViewComponent) {
            AndroidViewComponent androidComponent = (AndroidViewComponent) component;
            View sourceView = layout.getView();
            View targetView = newLayout.getView();

            if (sourceView instanceof ViewGroup && targetView instanceof ViewGroup) {
                ViewGroup sourceLayout = (ViewGroup) sourceView;
                ViewGroup targetLayout = (ViewGroup) targetView;

                View componentView = androidComponent.getView();
                if (componentView.getParent() != null) {
                    ((ViewGroup) componentView.getParent()).removeView(componentView);
                }
                targetLayout.addView(componentView);
            }
        }
    }

    @SimpleFunction(description = "Gets a component by its name")
    public Object GetComponent(String componentName) {
        return componentName != null ? newComponents.get(componentName + "true") : null;
    }

    @SimpleEvent(description = "When user touch released, event fired")
    public void TouchReleased(){
        EventDispatcher.dispatchEvent(this, "TouchReleased");
    }

    private Map<String, Component> mapComponents() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Component> components = new HashMap<>();
        try {
            Field componentsField = form.getClass().getDeclaredField("components$Mnto$Mncreate");
            componentsField.setAccessible(true);
            LList listComponents = (LList) componentsField.get(form);

            for (Object component : listComponents) {
                LList lList = (LList) component;
                SimpleSymbol symbol = (SimpleSymbol) lList.get(2);
                String componentName = symbol.getName();
                Object value = form.getClass().getDeclaredField(componentName).get(form);
                if (value instanceof Component) {
                    components.put(componentName, (Component) value);
                }
            }
        } catch (Exception e) {
            Log.e("Rapu", "Error mapping components", e);
        }
        return components;
    }

    private Map<String, Component> mapComponentsRepl() throws NoSuchFieldException, IllegalAccessException {
        Map<String, Component> components = new HashMap<>();
        try {
            Field field = form.getClass().getDeclaredField("form$Mnenvironment");
            field.setAccessible(true);
            Environment environment = (Environment) field.get(form);
            LocationEnumeration locationEnumeration = environment.enumerateAllLocations();

            while (locationEnumeration.hasMoreElements()) {
                Location location = locationEnumeration.next();
                String componentName = location.getKeySymbol().getName();
                Object value = location.getValue();
                if (value instanceof Component) {
                    components.put(componentName, (Component) value);
                }
            }
        } catch (Exception e) {
            Log.e("Rapu", "Error mapping REPL components", e);
        }
        return components;
    }
}
