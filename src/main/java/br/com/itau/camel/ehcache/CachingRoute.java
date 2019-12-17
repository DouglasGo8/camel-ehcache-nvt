package br.com.itau.camel.ehcache;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ehcache.EhcacheConstants;

/**
 * @author dbatista
 */
public class CachingRoute extends RouteBuilder {

    private static final String ehCacheCamelEndpoint = "{{ehCache.camel.endpoint}}";

    @Override
    public void configure() throws Exception {

        from("timer://myTimer?period=13s&fixedRate=true")
                .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_GET))
                .setHeader(EhcacheConstants.KEY, constant("token"))
                .to(ehCacheCamelEndpoint)
                .choice()
                    .when(header(EhcacheConstants.ACTION_HAS_RESULT).isNotEqualTo("true"))
                        .log("No cache hit yet.. Fetching data from internal Bean")
                        .bean(MyBean.class)
                        .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_PUT))
                        .setHeader(EhcacheConstants.KEY, constant("token"))
                        .to(ehCacheCamelEndpoint)
                .otherwise()
                    .bean(MyBean.class)
                    .setHeader(EhcacheConstants.ACTION, constant(EhcacheConstants.ACTION_REPLACE))
                    .setHeader(EhcacheConstants.KEY, constant("token"))
                    .to(ehCacheCamelEndpoint)
                .endChoice()
        .end();


    }
}
