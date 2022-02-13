package org.apache.camel.component.casper.producer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.CasperConstants;
import org.apache.camel.component.casper.CasperTestSupport;
import org.apache.commons.cli.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.syntifi.casper.sdk.model.block.JsonBlock;
import com.syntifi.casper.sdk.service.CasperService;

public class CasperProducerWith_BLOCK_Operation extends CasperTestSupport {
	@Produce("direct:start")
	protected ProducerTemplate template;

	
	
	private CasperService casperService;

	
	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testCallWith_BLOCK_HASH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.BLOCK);

		exchange.getIn().setHeader(CasperConstants.BLOCK_HASH,
				"30c1263cbcc95066f5c20e96cb8ba11356295515f414961b646e831c17992d26");

		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is a JsonBlock
		assertTrue(body instanceof JsonBlock);
		JsonBlock block = (JsonBlock) body;
		assertTrue(block != null);
		assertTrue(block.getHash().toLowerCase().equals("30c1263cbcc95066f5c20e96cb8ba11356295515f414961b646e831c17992d26"));
		

	}


	@Test
	public void testCallWith_BLOCKD_LENGTH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.BLOCK);
		exchange.getIn().setHeader(CasperConstants.BLOCK_HEIGHT,534910);
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is a JsonBlock
		assertTrue(body instanceof JsonBlock);
		JsonBlock block = (JsonBlock) body;
		assertTrue(block != null);
		assertTrue(block.getHash().toLowerCase().equals("f990a7079e3ebb1972d1388c0efd97cd7d7e2be9e442bd80f0ddb8134625a8f2"));
		
	}
	
	@Test
	public void testCallWithout_Parameters() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.BLOCK);
		
		template.send(exchange);
				
		Object body = exchange.getIn().getBody();
		// assert Object is a JsonBlock
		assertTrue(body instanceof JsonBlock);
	   // Assert the call returns the last block
	   assertTrue(casperService.getBlock().getBlock().getHash().toLowerCase().equals(((JsonBlock) body).getHash()));
	
	}
	
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			public void configure() {
				from("direct:start").to(getUrl());

			}
		};
	}

	
	@Override
	@BeforeEach
	public void setUp() throws Exception {
		URI uri = new URI(CasperConstants.TESTNET_NODE_URL);
		casperService = CasperService.usingPeer(uri.getHost(), uri.getPort());
		super.setUp();
	}
}
