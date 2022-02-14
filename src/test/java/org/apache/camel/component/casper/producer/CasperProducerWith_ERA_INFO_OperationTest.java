package org.apache.camel.component.casper.producer;

import static org.junit.Assert.assertNull;
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

import com.syntifi.casper.sdk.model.era.EraSummary;

public class CasperProducerWith_ERA_INFO_OperationTest extends CasperTestSupport {
	@Produce("direct:start")
	protected ProducerTemplate template;


	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testCallWith_BLOCK_HASH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ERA_INFO);

		exchange.getIn().setHeader(CasperConstants.BLOCK_HASH,
				"1e46B4c173dB70fDE0E867FF679ACa24e1c5Bea3C4333af94e53B4E3BC548B6B");
		// "7ef311f759a6a4128a3002ade44fb6ac4ad70b5f6748907e5add4afdfe33fd0a");
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is an EraSummary
		assertTrue(body instanceof EraSummary);
		EraSummary eraSummary = (EraSummary) body;
		assertTrue(eraSummary != null);
		assertTrue(eraSummary.getEraId() == 2974);
		assertTrue(eraSummary.getStateRootHash().toLowerCase()
				.equals("c1A62d5DeB74d3fEAfeCd1EEa526941edd0264895EB8E516474108D4EA4D7D21".toLowerCase()));
	}

	@Test
	public void testCallWith_BLOCK_HEIGHT_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ERA_INFO);
		exchange.getIn().setHeader(CasperConstants.BLOCK_HEIGHT, 371969L);
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is an EraSummary
		assertTrue(body instanceof EraSummary);
		EraSummary eraSummary = (EraSummary) body;
		assertTrue(eraSummary != null);
		assertTrue(eraSummary.getEraId() == 2974);
		assertTrue(eraSummary.getStateRootHash().toLowerCase()
				.equals("c1A62d5DeB74d3fEAfeCd1EEa526941edd0264895EB8E516474108D4EA4D7D21".toLowerCase()));

	}

	@Test
	public void testCallWith_BLOCK_HEIGHT_Parameter_Returning_Null() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ERA_INFO);
		exchange.getIn().setHeader(CasperConstants.BLOCK_HEIGHT, 535155L);
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		assertNull(body);
	}

	@Test
	public void testCallWithout_Parameters() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.ERA_INFO);
		template.send(exchange);
		Exception exception = exchange.getException();
		assertTrue(exception instanceof CamelExchangeException);
		String expectedMessage = "Either blockHeight or BlockHash parameter is required  with endpoint operation "
				+ CasperConstants.ACCOUNT_INFO;
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
