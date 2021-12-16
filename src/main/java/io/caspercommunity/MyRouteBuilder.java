package io.caspercommunity;

import org.json.JSONObject;
import org.apache.camel.util.json.JsonObject;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
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
      // from("casper:http://65.21.202.120:9999/events/main?operation=block_added")
      // .log("Block Added - ${body}");
      from("casper:http://65.21.202.120:9999/events/main?operation=block_added")
      .process(new Processor()
               {
                  public void process(Exchange exchange) throws Exception {
                     JSONObject body = exchange.getMessage().getBody(JSONObject.class );
                     exchange.getMessage().setBody(body.getString("block_hash"));
                  }
               })
      .log("Block Hash - ${body}")
      .setHeader("BLOCK_HASH", simple("${body}"))
      .to("casper:http://65.21.202.120:7777?operation=get_block_info")
      .log("Block Details - ${body}");


      // from("casper:http://65.21.202.120:9999/events/main?operation=deploy_processed")
      // .log("Block Processed - ${body}");
      // from("casper:http://65.21.202.120:9999/events/deploys?operation=deploy_accepted")
      // .log("Deploy Accepted - ${body}");
      //
      //.to("casper:http://65.21.202.120:7777?operation=get_peers")

      // .log("The body was - ${body}")
      // .to("casper:http://65.21.202.120:7777?operation=get_block_info")
      // .setHeader("PUBLIC_KEY", simple("01ec78afd125dce4cfd4437b04132eb186d56d680b96757196a1c97b43868b639e"))
      // .to("casper:http://65.21.202.120:7777?operation=get_account_balance")
   }
}
