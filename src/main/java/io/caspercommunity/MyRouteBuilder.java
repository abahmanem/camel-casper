package io.caspercommunity;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {
/**
 * Let's configure the Camel routing rules using Java code...
 */
   public void configure()
   {
      // here is a sample which processes the input files
      // (leaving them in place - see the 'noop' flag)
      // then performs content based routing on the message using XPath
      from("casper:test?operation=block_added")
      .to("casper:http://65.21.202.120:8888/status")
      .log("The body was - ${body}");
   }
}
