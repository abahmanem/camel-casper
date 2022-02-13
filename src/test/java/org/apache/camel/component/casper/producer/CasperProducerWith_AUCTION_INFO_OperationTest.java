package org.apache.camel.component.casper.producer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.CasperConstants;
import org.apache.camel.component.casper.CasperTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.syntifi.casper.sdk.model.auction.AuctionState;
import com.syntifi.casper.sdk.service.CasperService;

public class CasperProducerWith_AUCTION_INFO_OperationTest extends CasperTestSupport {
	@Produce("direct:start")
	protected ProducerTemplate template;

	private CasperService casperService;

	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testCallWith_BLOCK_HASH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION,
				CasperConstants.AUCTION_INFO);

		exchange.getIn().setHeader(CasperConstants.BLOCK_HASH,
				"7ef311f759a6a4128a3002ade44fb6ac4ad70b5f6748907e5add4afdfe33fd0a");

		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is an AuctionState
		assertTrue(body instanceof AuctionState);
		AuctionState auctionState = (AuctionState) body;
		assertTrue(auctionState != null);
		assertTrue(auctionState.getHeight() == 535075L);
		assertTrue(auctionState.getStateRootHash().toLowerCase()
				.equals("2aedb1538305c183d1a1b645516762dceec1e762eed0625980a4fe58c4397b97"));
	}

	@Test
	public void testCallWith_BLOCK_HEIGHT_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION,
				CasperConstants.AUCTION_INFO);
		exchange.getIn().setHeader(CasperConstants.BLOCK_HEIGHT, 534868L);
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is an AuctionState
		assertTrue(body instanceof AuctionState);
		AuctionState auctionState = (AuctionState) body;
		assertTrue(auctionState != null);
		assertTrue(auctionState.getHeight() == 534868L);
		assertTrue(auctionState.getStateRootHash().toLowerCase()
				.equals("49f4b72f71202eb0fbf17d89da038e0b342984b56feccaf6157e2291c69bcd9b"));
	}

	@Test
	public void testCallWithout_Parameters() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION,
				CasperConstants.AUCTION_INFO);
		template.send(exchange);

		Object body = exchange.getIn().getBody();
		// assert Object is an AuctionState
		assertTrue(body instanceof AuctionState);
		AuctionState auctionState = (AuctionState) body;
		assertTrue(auctionState != null);
		// this is the lastest one
		assertTrue(casperService.getStateRootHash().getStateRootHash().equals(auctionState.getStateRootHash()));
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
