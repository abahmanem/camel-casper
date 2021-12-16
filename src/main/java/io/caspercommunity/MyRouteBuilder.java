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
      from("casper:http://65.21.202.120:9999/events/main?operation=block_added")
      //.to("casper:http://65.21.202.120:7777?operation=get_peers")
      .log("The body was - ${body}");
      // .to("casper:http://65.21.202.120:7777?operation=get_latest_block_info")
      // .log("The body was - ${body}");
   }
}
