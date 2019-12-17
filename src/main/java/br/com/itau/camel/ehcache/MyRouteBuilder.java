package br.com.itau.camel.ehcache;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ehcache.EhcacheConstants;

/**
 * A Camel Java8 DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    private static final String ehCacheCamelEndpoint = "{{ehCache.camel.endpoint}}";

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("timer://myTimer1?period=3s&fixedRate=true&delay=5s")
                .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_GET))
                .setHeader(EhcacheConstants.KEY, constant("token"))
                .to(ehCacheCamelEndpoint)
                .log("${body}")
        .end();

        from("timer://myTimer2?period=1s&fixedRate=true&delay=5s")
                .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_GET))
                .setHeader(EhcacheConstants.KEY, constant("token"))
                .to(ehCacheCamelEndpoint)
                .log("${body}")
        .end();

        from("timer://myTimer3?period=200&fixedRate=true&delay=5s")
                .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_GET))
                .setHeader(EhcacheConstants.KEY, constant("token"))
                .to(ehCacheCamelEndpoint)
                .log("${body}")
        .end();


    }


}
