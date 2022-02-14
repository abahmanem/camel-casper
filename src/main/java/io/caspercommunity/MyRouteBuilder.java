package io.caspercommunity;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.CasperConstants;


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
     /*
	   from("casper:http://65.21.202.120:9999/events/main?operation=block_added")
      .process(new Processor()
               {
                  public void process(Exchange exchange) throws Exception {
                     JSONObject body = exchange.getMessage().getBody(JSONObject.class );
                     exchange.getMessage().setBody(body.getString("block_hash"));
                  }
               })
      .log("Block Hash - ${body}");
      
     
	   
	   
	   from("timer://simpleTimer?period=1000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.LAST_BLOCK).process(new TestProcessor())
	      .log("This call gives - ${body}");

	   
	   
	   
	   from("timer://simpleTimer?period=1000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.BLOCK+"&blockHeight=530157").process(new TestProcessor())
	      .log("This call gives - ${body}");

	   */
	   
	   from("casper:http://65.21.202.120:9999/events/main?operation=block_added").log("Block Hash - ${body}");
	   
	   
	   
	   from("timer://simpleTimer?period=3000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.ACCOUNT_INFO).process(new TestProcessor())
	      .log("This call gives - ${body}");
	   
	   from("timer://simpleTimer?period=3000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.ACCOUNT_INFO+"&blockHeight=530214&publicKey=017d9aa0b86413d7ff9a9169182c53f0bacaa80d34c211adab007ed4876af17077").process(new TestProcessor())
	      .log("This call gives - ${body}");
	   
	   
	   from("timer://simpleTimer?period=3000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.STATE_ROOT_HASH+"&blockHeight=530214").process(new TestProcessor())
	      .log("This 2nd call gives - ${body}");
	   
	   
	   
	 /*
		   from("timer://simpleTimer?period=1000")
      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.NETWORK_PEERS).process(new TestProcessor())
      .log("This call gives - ${body}");

	   
	   from("timer://simpleTimer?period=1000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.NODE_STATUS).process(new TestProcessor())
	      .log("This call gives - ${body}");
	   
	   
	   
	   
	   from("timer://simpleTimer?period=1000")
	      .to("casper:http://65.21.227.180:7777/?operation="+CasperConstants.DEPLOY+"&deployHash=182bc93b6b90ac0a8b5d6d9d677e15c915c865435d46e6f667e186c4056e683d").process(new TestProcessor())
	      .log("This call gives - ${body}");
	   
	   
	   
	   from("timer://simpleTimer?period=1000")
	      .to("casper:http://65.21.227.180:7777/?operation=nodeVersion").process(new TestProcessor())
	      .log("This call gives - ${body}");
	   
	   */

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
