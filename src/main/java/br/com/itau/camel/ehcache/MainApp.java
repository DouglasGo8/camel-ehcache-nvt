package br.com.itau.camel.ehcache;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {

        Main main = new Main();
        main.setPropertyPlaceholderLocations("application.properties");
        main.addRouteBuilder(new MyRouteBuilder());
        main.addRouteBuilder(new CachingRoute());
        main.run(args);
    }
}

