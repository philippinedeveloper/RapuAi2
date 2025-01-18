/* Extension written by Bextdev, written on the 28th of December 2024
 *
 * Bits of code written by yusufcihan, Kumaraswamy B G, Gordon Lu, Evan (MIT) & Hridoy.
 *
 * Licensed under GNU General Public License V2
 *
 */

package ph.bxtdev.Rapu;

import kawa.standard.Scheme;
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
import java.util.HashSet;
import gnu.lists.LList;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import com.google.appinventor.components.runtime.Component;

public class Rapu extends AndroidNonvisibleComponent {

    private Context context;
    private Activity activity;
    private Form form;
    private Map<String, Component> components = new HashMap<>();
    private Map<String, Component> newComponents = new HashMap<>();
    private String TAG = this.getClass().getSimpleName();

    public Rapu(ComponentContainer container) {
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
        this.form = container.$form();
    }

    @SimpleFunction
public void BackgroundColor(AndroidViewComponent component, int backgroundColor) {
    component.getView().setBackgroundColor(backgroundColor);
}

@SimpleFunction
public void TextColor(AndroidViewComponent component, int textColor) {
    android.view.View view = component.getView();
    if (view instanceof android.widget.TextView) {
        android.widget.TextView textView = (android.widget.TextView) view;
        textView.setTextColor(textColor);
    }
}

@SimpleFunction
public void Text(AndroidViewComponent component, String text) {
    android.view.View view = component.getView();
    if (view instanceof android.widget.TextView) {
        android.widget.TextView textView = (android.widget.TextView) view;
        textView.setText(text);
    }
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
                    if (!newComponents.containsKey(componentName + id + "true")) {
                        Create(layout, originalComponentName, id);
                    } else {
                        Log.e("Rapu", "Component already exists: " + componentName);
                    }
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

    @SimpleFunction(description = "Moves a dynamically created component to another component")
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
                return true;
            }
        });
    }

    @SimpleFunction(description = "Enables click listener on a component")
    public void EnableClickListener(AndroidViewComponent component) {
       View view = component.getView();
       view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Click();
          }
       });
   }

   @SimpleEvent(description = "Triggered when the user clicks the component")
   public void Click() {
      EventDispatcher.dispatchEvent(this, "Click");
   }


    @SimpleEvent(description = "Triggered when the user performs a long click on the component")
    public void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick");
    }

    @SimpleFunction(description = "Gets the padding top of a given view")
    public int GetPaddingTop(AndroidViewComponent component) {
        return component.getView().getPaddingTop();
    }

    @SimpleFunction(description = "Gets the padding bottom of a given view")
    public int GetPaddingBottom(AndroidViewComponent component) {
        return component.getView().getPaddingBottom();
    }

    @SimpleFunction(description = "Gets the opacity of a given view")
    public int GetOpacity(AndroidViewComponent component) {
        return (int) component.getView().getAlpha(); 
    }

    @SimpleFunction(description = "Gets the elevation of a given view")
    public float GetElevation(AndroidViewComponent component) {
        return component.getView().getElevation();
    }

    @SimpleFunction(description = "Gets the left position of a given view")
    public int GetLeft(AndroidViewComponent component) {
        return component.getView().getLeft();
    }

    @SimpleFunction(description = "Gets the right position of a given view")
    public int GetRight(AndroidViewComponent component) {
        return component.getView().getRight();
    }

    @SimpleFunction(description = "Gets the pivot X value of a given view")
    public float GetPivotX(AndroidViewComponent component) {
        return component.getView().getPivotX();
    }

    @SimpleFunction(description = "Gets the pivot Y value of a given view")
    public float GetPivotY(AndroidViewComponent component) {
        return component.getView().getPivotY();
    }

    @SimpleFunction(description = "Gets the rotation X value of a given view")
    public float GetRotationX(AndroidViewComponent component) {
        return component.getView().getRotationX();
    }

    @SimpleFunction(description = "Gets the rotation Y value of a given view")
    public float GetRotationY(AndroidViewComponent component) {
        return component.getView().getRotationY();
    }

    @SimpleFunction(description = "Sets the padding top of a given view")
    public void SetPaddingTop(AndroidViewComponent component, int paddingTop) {
        component.getView().setPadding(component.getView().getPaddingLeft(), paddingTop, component.getView().getPaddingRight(), component.getView().getPaddingBottom());
    }

    @SimpleFunction(description = "Sets the padding bottom of a given view")
    public void SetPaddingBottom(AndroidViewComponent component, int paddingBottom) {
        component.getView().setPadding(component.getView().getPaddingLeft(), component.getView().getPaddingTop(), component.getView().getPaddingRight(), paddingBottom);
    }

    @SimpleFunction(description = "Sets the opacity of a given view")
    public void SetOpacity(AndroidViewComponent component, float opacity) {
        component.getView().setAlpha(opacity); 
    }

    @SimpleFunction(description = "Sets the elevation of a given view")
    public void SetElevation(AndroidViewComponent component, float elevation) {
        component.getView().setElevation(elevation);
    }

    @SimpleFunction(description = "Sets the left position of a given view")
    public void SetLeft(AndroidViewComponent component, int left) {
        component.getView().setLeft(left); 
    }

    @SimpleFunction(description = "Sets the right position of a given view")
    public void SetRight(AndroidViewComponent component, int right) {
        component.getView().setRight(right); 
    }

    @SimpleFunction(description = "Sets the pivot X value of a given view")
    public void SetPivotX(AndroidViewComponent component, float pivotX) {
        component.getView().setPivotX(pivotX);
    }

    @SimpleFunction(description = "Sets the pivot Y value of a given view")
    public void SetPivotY(AndroidViewComponent component, float pivotY) {
        component.getView().setPivotY(pivotY);
    }

    @SimpleFunction(description = "Sets the rotation X value of a given view")
    public void SetRotationX(AndroidViewComponent component, float rotationX) {
        component.getView().setRotationX(rotationX);
    }

    @SimpleFunction(description = "Sets the rotation Y value of a given view")
    public void SetRotationY(AndroidViewComponent component, float rotationY) {
        component.getView().setRotationY(rotationY);
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

    @SimpleFunction(description = "Gets the parent component of a given component")
    public Object GetParent(AndroidViewComponent component) {
        View view = component.getView();
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup parentView = (ViewGroup) view.getParent();
            for (Component comp : newComponents.values()) {
                if (comp instanceof AndroidViewComponent && ((AndroidViewComponent) comp).getView() == parentView) {
                    return comp;
                }
            }
        }
        return null; 
    }


    @SimpleFunction(description = "Gets the children of a given component")
    public Object GetChildren(AndroidViewComponent component) {
       ViewGroup parentView = (ViewGroup) component.getView();
       HashSet<Object> childrenSet = new HashSet<>();

       if (parentView != null) {
          int childCount = parentView.getChildCount();
          for (int i = 0; i < childCount; i++) {
            View childView = parentView.getChildAt(i);
            for (Component comp : newComponents.values()) {
                if (comp instanceof AndroidViewComponent && ((AndroidViewComponent) comp).getView() == childView) {
                    childrenSet.add(comp);
                }
             }
          }
       }
          return YailList.makeList(childrenSet).toArray();
    }

    private Map<String, Component> mapComponents() throws NoSuchFieldException, IllegalAccessException {
     Map<String, Component> components = new HashMap<>();
     Field componentsField = form.getClass().getField("components$Mnto$Mncreate");
     LList listComponents = (LList) componentsField.get(form);
     for (Object component : listComponents) {
      LList lList = (LList) component;
      SimpleSymbol symbol = (SimpleSymbol) lList.get(2);
      String componentName = symbol.getName();
      Object value = form.getClass().getField(componentName).get(form);
      if (value instanceof Component) {
        components.put(componentName, (Component) value);
      }
    }
    return components;
  }

   private Map<String, Component> mapComponentsRepl() throws NoSuchFieldException, IllegalAccessException {
    Map<String, Component> components = new HashMap<>();
    Field field = form.getClass().getField("form$Mnenvironment");
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
    return components;
  }

  private Object lookupComponentInRepl(String componentName) {
    Scheme lang = Scheme.getInstance();
    try {
      // Since we're in the REPL, we can cheat and invoke the Scheme interpreter to get the method.
      Object result = lang.eval("(begin (require <com.google.youngandroid.runtime>)(get-component " +
              componentName + "))");
      if (result instanceof Component) {
        return (Component) result;
      } else {
        Log.e(TAG, "Wanted a Component, but got a " +
                (result == null ? "null" : result.getClass().toString()));
      }
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return "";
  }


  private Object lookupComponentInForm(String componentName) {
    try {
      // Get the field by name
      Field field = form.getClass().getField(componentName);
      // Get the field's value, since field itself isn't a Component
      Object component = field.get(form);
      if (component instanceof Component) {
        return (Component) component;
      } else {
        Log.e(TAG, "Wanted a Component, but got a " +
                (component == null ? "null" : component.getClass().toString()));
      }
    } catch (NoSuchFieldException | IllegalAccessException e) {
      Log.e(TAG, "Error accessing component: " + componentName, e);
    }
    return "";
  }

    @SimpleFunction(description = "Enables drag and drop functionality on a component")
public void EnableDragAndDrop(AndroidViewComponent component) {
    View view = component.getView();

    view.setOnTouchListener(new View.OnTouchListener() {
        private float dX, dY;
        private boolean isDragging = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    dX = v.getX() - event.getRawX();
                    dY = v.getY() - event.getRawY();
                    isDragging = true;
                    DragStart(component);
                    return true;

                case MotionEvent.ACTION_MOVE:
                    if (isDragging) {
                        float newX = event.getRawX() + dX;
                        float newY = event.getRawY() + dY;
                        v.setX(newX);
                        v.setY(newY);
                        Dragged(component, (int) newX, (int) newY);
                    }
                    return true;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (isDragging) {
                        isDragging = false;
                        Drop(component, (int) v.getX(), (int) v.getY());
                    }
                    return true;

                default:
                    return false;
            }
        }
    });
}

@SimpleEvent(description = "Triggered when a drag operation starts")
public void DragStart(AndroidViewComponent component) {
    EventDispatcher.dispatchEvent(this, "DragStart", component);
}

@SimpleEvent(description = "Triggered when the component is being dragged")
public void Dragged(AndroidViewComponent component, int x, int y) {
    EventDispatcher.dispatchEvent(this, "Dragged", component, x, y);
}

@SimpleEvent(description = "Triggered when the component is dropped")
public void Drop(AndroidViewComponent component, int x, int y) {
    EventDispatcher.dispatchEvent(this, "Drop", component, x, y);
}

   @SimpleFunction(description = "Returns the horizontal location of this component relative to its left position." + 
    " This position is post-layout, in addition to wherever the object's layout placed it.")
    public float GetTranslationX(AndroidViewComponent component) {
        View view = component.getView();
        return view.getTranslationX();
    }

    @SimpleFunction(description = "Returns the horizontal location of this component relative to its top position." + 
    " This position is post-layout, in addition to wherever the object's layout placed it.")
    public float GetTranslationY(AndroidViewComponent component) {
        View view = component.getView();
        return view.getTranslationY();
    }

    @SimpleFunction(description = "Returns the depth location of this component relative to its elevation.")
    public float GetTranslationZ(AndroidViewComponent component) {
        View view = component.getView();
        return view.getTranslationZ();
    }

      @SimpleFunction(description = "Returns the amount that the component is scaled in x around the pivot point, as a proportion of the view's unscaled width.")
    public float GetScaleX(AndroidViewComponent component) {
        View view = component.getView();
        return view.getScaleX();
    }

    @SimpleFunction(description = "Returns the amount that the component is scaled in y around the pivot point, as a proportion of the view's unscaled height.")
    public float GetScaleY(AndroidViewComponent component) {
        View view = component.getView();
        return view.getScaleY();
    }

    @SimpleFunction(description = "Returns the scrollbar size for this component.")
    public int GetScrollBarSize(AndroidViewComponent component) {
        View view = component.getView();
        return view.getScrollBarSize();
    }

    @SimpleFunction(description = "Return the scrolled left position of this component. This is the left edge of the displayed part of your component." + 
    " You do not need to draw any pixels farther left, since those are outside of the frame of your component on screen.")
    public int GetScrollX(AndroidViewComponent component) {
        View view = component.getView();
        return view.getScrollX();
    }

    @SimpleFunction(description = "Return the scrolled top position of this component. This is the top edge of the displayed part of your component." + 
    " You do not need to draw any pixels farther top, since those are outside of the frame of your component on screen.")
    public int GetScrollY(AndroidViewComponent component) {
        View view = component.getView();
        return view.getScrollY();
    }
    
     @SimpleFunction(description = "Sets the size of the scrollbar.")
    public void SetScrollBarSize(AndroidViewComponent component, int size) {
        View view = component.getView();
        view.setScrollBarSize(size);
    }

    @SimpleFunction(description = "Sets the horizontal location of this component relative to its left position.")
    public void SetTranslationX(AndroidViewComponent component, float translationX) {
        View view = component.getView();
        view.setTranslationX(translationX);
    }

    @SimpleFunction(description = "Sets the vertical location of this component relative to its top position.")
    public void SetTranslationY(AndroidViewComponent component, float translationY) {
        View view = component.getView();
        view.setTranslationY(translationY);
    }

      @SimpleFunction(description = "Sets the amount that the component is scaled in x around the pivot point, as a proportion of the component's unscaled width." + 
    " A value of 1 means that no scaling is applied.")
    public void SetScaleX(AndroidViewComponent component, float scaleX){
        View view = component.getView();
        view.setScaleX(scaleX);
    }

    @SimpleFunction(description = "Sets the amount that the component is scaled in y around the pivot point, as a proportion of the component's unscaled height." + 
    " A value of 1 means that no scaling is applied.")
    public void SetScaleY(AndroidViewComponent component, float scaleY){
        View view = component.getView();
        view.setScaleY(scaleY);
    }

        @SimpleFunction(description = "Returns the bottom of this component, in pixels.")
    public int GetBottom(AndroidViewComponent component) {
        View view = component.getView();
        return view.getBottom();
    }

     @SimpleFunction(description = "Returns the top edge of this component, in pixels.")
    public int GetTop(AndroidViewComponent component) {
        View view = component.getView();
        return view.getTop();
    }

     @SimpleFunction(description = "Sets the top position of this component relative to its parent, in pixels.")
    public void SetTop(AndroidViewComponent component, int top) {
        View view = component.getView();
        view.setTop(top);
    }

       @SimpleFunction(description = "Sets the bottom position of this component relative to its parent, in pixels.")
    public void SetBottom(AndroidViewComponent component, int bottom) {
        View view = component.getView();
        view.setBottom(bottom);
    }

    @SimpleFunction(description = "Gets component using name & id (For dynamically created components ONLY)")
    public Object GetComponent(String componentName){
      return (Component) newComponents.get(componentName + "true");
    }

    @SimpleFunction(description = "Gets component by name (This doesn't work on dynamically created components)")
    public Object GetComponentByName(String componentName){
      if(form instanceof ReplForm){
         return lookupComponentInRepl(componentName);
      } else {
         return lookupComponentInForm(componentName);
      }
    }

    @SimpleFunction(description = "Checks if component is an extension")
    public boolean IsExtension(String componentName){
      if (form instanceof ReplForm) {
         return !lookupComponentInRepl(componentName).getClass().getName().startsWith("com.google.appinventor.components.runtime");
      } else {
         return !lookupComponentInForm(componentName).getClass().getName().startsWith("com.google.appinventor.components.runtime");
      }
    }

      @SimpleFunction(description = "Checks whether the component is currently able to take focus.")
    public boolean IsFocusable(AndroidViewComponent component) {
        View view = component.getView();
        return view.isFocusable();
    }

       @SimpleFunction(description = "Set whether this component can receive the focus.")
    public void SetFocusable(AndroidViewComponent component, boolean focusable) {
        View view = component.getView();
        view.setFocusable(focusable);
    }

        @SimpleFunction(description = "Registers the component so that when the user focuses or removes focus for the component, it will fire the respective event.")
    public void RegisterFocus(final AndroidViewComponent component) {
        View view = component.getView();
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            GotFocus(component);
        } else {
            LostFocus(component);
        }
        }
    });
    }

     @SimpleEvent(description = "This event is fired when a registered component is focused.")
    public void GotFocus(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this, "GotFocus", component);
    }
    @SimpleEvent(description = "This event is fired when a registered component has focus removed.")
    public void LostFocus(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this, "LostFocus", component);
    }
}
