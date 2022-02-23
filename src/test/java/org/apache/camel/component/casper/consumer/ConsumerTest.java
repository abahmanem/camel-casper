package org.apache.camel.component.casper.consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.CasperConstants;
import org.junit.jupiter.api.Test;

import com.syntifi.casper.sdk.model.balance.BalanceData;

public class ConsumerTest extends CasperConsumerTest{
	
	@Produce("direct:start")
	protected ProducerTemplate template;

	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testCall() throws Exception {

		
		Thread.sleep(10000);;

		//assert balance value
		assertTrue(0==0);
	}

	

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			public void configure() {
				from("casper:http://localhost:8080/events/main?operation=block_added").log("Block Hash - ${body}");
				}
		};
	}
	
}
