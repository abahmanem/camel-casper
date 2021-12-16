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
      .log("Block Added - ${body}");
      from("casper:http://65.21.202.120:9999/events/main?operation=api_version")
      .log("Api Version - ${body}");
      from("casper:http://65.21.202.120:9999/events/main?operation=deploy_processed")
      .log("Block Processed - ${body}");
      from("casper:http://65.21.202.120:9999/events/deploys?operation=deploy_accepted")
      .log("Deploy Accepted - ${body}");
      //
      //.to("casper:http://65.21.202.120:7777?operation=get_peers")

      // .log("The body was - ${body}")
      // .setHeader("BLOCK_HASH", simple("6882b007dd4ad1ca7862f124c577723edc0aa0787e639774c56daf3ffccc41cb"))
      // .to("casper:http://65.21.202.120:7777?operation=get_block_info")
      // .setHeader("PUBLIC_KEY", simple("01ec78afd125dce4cfd4437b04132eb186d56d680b96757196a1c97b43868b639e"))
      // .to("casper:http://65.21.202.120:7777?operation=get_account_balance")
   }
}
