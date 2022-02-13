package org.apache.camel.component.casper.producer;

import static org.junit.jupiter.api.Assertions.assertTrue;



import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.CasperConstants;
import org.apache.camel.component.casper.CasperTestSupport;
import org.apache.commons.cli.MissingArgumentException;

import org.junit.jupiter.api.Test;

import com.syntifi.casper.sdk.model.account.AccountData;

public class CasperProducerWith_ACCOUNT_INFO_Operation extends CasperTestSupport {
	@Produce("direct:start")
	protected ProducerTemplate template;
	
	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testCallWith_BLOCK_HASH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ACCOUNT_INFO);

		exchange.getIn().setHeader(CasperConstants.PUBLIC_KEY,
				"017d9aa0b86413d7ff9a9169182c53f0bacaa80d34c211adab007ed4876af17077");

		exchange.getIn().setHeader(CasperConstants.BLOCK_HASH,
				"d162d54f93364bb9cafd923cfde35a195e6a76d2f67515ddb2dce12443dc8aa5");
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is a AccountData
		assertTrue(body instanceof AccountData);
		AccountData account = (AccountData) body;
		assertTrue(account != null);
		assertTrue(account.getAccount().getMainPurse().toLowerCase().equals("uref-e18e33382032c835e9ccf367baa20e043229c6d45d135b60aa7301ff1eeb317b-007"));
		

	}


	@Test
	public void testCallWith_BLOCK_HEIGHT_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ACCOUNT_INFO);

		exchange.getIn().setHeader(CasperConstants.PUBLIC_KEY,
				"017d9aa0b86413d7ff9a9169182c53f0bacaa80d34c211adab007ed4876af17077");

		exchange.getIn().setHeader(CasperConstants.BLOCK_HEIGHT,534838);
			
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is a AccountData
		assertTrue(body instanceof AccountData);
		AccountData account = (AccountData) body;
		assertTrue(account != null);
		assertTrue(account.getAccount().getHash().toLowerCase().equals("account-hash-a8261377ef9cf8e741dd6858801c71e38c9322e66355586549b75ab24bdd73f2"));
		
		
	}
	
	@Test
	public void testCallWithout_PUBLIC_KEY_Parameters() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ACCOUNT_INFO);
		exchange.getIn().setHeader(CasperConstants.BLOCK_HEIGHT,534838);
		template.send(exchange);
				
		Exception exception = exchange.getException();
		assertTrue(exception instanceof CamelExchangeException);
		String expectedMessage = "publicKey parameter is required  with endpoint operation "+ CasperConstants.ACCOUNT_INFO;
		String actualMessage = exception.getMessage();

		// assert Exception message
		assertTrue(actualMessage.contains(expectedMessage));

		// Cause

		Object cause = exchange.getMessage().getHeader(CasperConstants.ERROR_CAUSE);
		assertTrue(cause instanceof MissingArgumentException);
	
	}
	
	
	@Test
	public void testCallWithOnly_PUBLIC_KEY_Parameters() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ACCOUNT_INFO);
		exchange.getIn().setHeader(CasperConstants.PUBLIC_KEY,
				"017d9aa0b86413d7ff9a9169182c53f0bacaa80d34c211adab007ed4876af17077");
		template.send(exchange);
				
		Exception exception = exchange.getException();
		assertTrue(exception instanceof CamelExchangeException);
		String expectedMessage = "Either blockHeight or BlockHash parameter is required  with endpoint operation "+ CasperConstants.ACCOUNT_INFO;
		String actualMessage = exception.getMessage();

		// assert Exception message
		assertTrue(actualMessage.contains(expectedMessage));

		// Cause

		Object cause = exchange.getMessage().getHeader(CasperConstants.ERROR_CAUSE);
		assertTrue(cause instanceof MissingArgumentException);
	
	}
	
	
	
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			public void configure() {
				from("direct:start").to(getUrl());

			}
		};
	}
	}