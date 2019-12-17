package br.com.itau.camel.ehcache;

import java.util.UUID;

/**
 * @author dbatista
 */
public class MyBean {

    public String myUuid() {
        return UUID.randomUUID().toString();
    }

}
