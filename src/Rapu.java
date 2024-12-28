/* Extension written by Bextdev
 *
 * Thanks to @Kumaraswamy & @YusufCihan, Creator of Itoo & Dynamic Components respectively, For writing bits of code
 *
 * Extension is licensed under GNU General Public License V2
 *
 * Extension written in Niotron IDE
 *
 */

package ph.bxtdev.Rapu;

import android.util.Log;
import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
        version = 1,
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

    public Rapu(ComponentContainer container) {
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
        form = container.$form();
    }

    @SimpleFunction(description = "Creates components dynamically on the layout and copies methods from existing components")
    public void Create(AndroidViewComponent layout) {
        Map<String, Component> components;
        try {
            if (form instanceof ReplForm) {
                components = mapComponentsRepl();
            } else {
                components = mapComponents();
            }

            for (Map.Entry<String, Component> entry : components.entrySet()) {
                String componentName = entry.getKey();
                Component originalComponent = entry.getValue();
                Class<?> componentClass = originalComponent.getClass();

                try {
                    Constructor<?> constructor = componentClass.getConstructor(ComponentContainer.class);
                    Component newComponent = (Component) constructor.newInstance(layout);

                } catch (Exception e) {
                    Log.e("Rapu", "Error creating component: " + componentName, e);
                }
            }
        } catch (Exception e) {
            Log.e("Rapu", "Error in Create method", e);
        }
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
