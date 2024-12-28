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
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import gnu.lists.LList;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import com.google.appinventor.components.runtime.Component;

@DesignerComponent(
        version = 2,
        versionName = "1.1",
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
        form = container.$form();
    }

    @SimpleFunction(description = "Copies every component on-screen by creating components dynamically")
    public void Copy(AndroidViewComponent layout, int id) {
        try {
            if (form instanceof ReplForm) {
                components = mapComponentsRepl();
            } else {
                components = mapComponents();
            }

            // Dynamically create components and add them to newComponents map
            for (Map.Entry<String, Component> entry : components.entrySet()) {
                String componentName = entry.getKey();
                Component originalComponent = entry.getValue();
                Class<?> componentClass = originalComponent.getClass();

                try {
                    Constructor<?> constructor = componentClass.getConstructor(ComponentContainer.class);
                    Component newComponent = (Component) constructor.newInstance(layout);

                    // Add "true" to the name to indicate it's created via reflection
                    newComponents.put(componentName + id + "true", newComponent);
                } catch (Exception e) {
                    Log.e("Rapu", "Error creating component: " + componentName, e);
                }
            }
        } catch (Exception e) {
            Log.e("Rapu", "Error in Copy method", e);
        }
    }

    @SimpleFunction(description = "Creates components dynamically")
    public void Create(AndroidViewComponent layout, String componentName, int id) {
        try {
            Class<?> clazz = Class.forName("com.google.appinventor.components.runtime." + componentName);
            Constructor<?> constructor = clazz.getConstructor(ComponentContainer.class);
            Component newComponent = (Component) constructor.newInstance(layout);

            // Add "true" to the name to indicate it's created via reflection
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

        // Ensure the layout is a ViewGroup (can be LinearLayout, FrameLayout, etc.)
        if (view instanceof ViewGroup) {
            ViewGroup layoutView = (ViewGroup) view;

            // Ensure to remove the component from its current parent layout if it has one
            View componentView = androidComponent.getView();
            if (componentView.getParent() != null) {
                ((ViewGroup) componentView.getParent()).removeView(componentView);
            }

            layoutView.removeView(componentView); // Remove the component from the layout
            newComponents.remove(componentName); // Remove from newComponents map
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

        // Ensure both views are instances of ViewGroup
        if (sourceView instanceof ViewGroup && targetView instanceof ViewGroup) {
            ViewGroup sourceLayout = (ViewGroup) sourceView;
            ViewGroup targetLayout = (ViewGroup) targetView;

            // Remove the component from its current parent layout
            View componentView = androidComponent.getView();
            if (componentView.getParent() != null) {
                ((ViewGroup) componentView.getParent()).removeView(componentView);
            }

            // Add the component to the new layout
            targetLayout.addView(componentView);
        }
    }
}


    @SimpleFunction(description = "Gets a component by its name (with 'true' suffix)")
    public Object GetComponentByName(String componentName) {
        if (componentName != null) {
            return (Component) newComponents.get(componentName + "true");
        }
        return null; 
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
