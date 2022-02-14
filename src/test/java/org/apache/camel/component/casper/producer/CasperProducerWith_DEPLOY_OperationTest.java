package org.apache.camel.component.casper.producer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.CasperConstants;
import org.apache.camel.component.casper.CasperTestSupport;
import org.apache.commons.cli.MissingArgumentException;
import org.junit.jupiter.api.Test;
import com.syntifi.casper.sdk.model.deploy.Deploy;
import com.syntifi.casper.sdk.service.CasperService;

public class CasperProducerWith_DEPLOY_OperationTest extends CasperTestSupport {
	@Produce("direct:start")
	protected ProducerTemplate template;

	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testCallWith_DEPLOY_HASH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.DEPLOY);

		exchange.getIn().setHeader(CasperConstants.DEPLOY_HASH,
				"5ff526617848b4416f818009dc90dd35485b4270a54d52f33652995472ef1fa9");
		template.send(exchange);
		Object body = exchange.getIn().getBody();
		// assert Object is a Deploy
		assertTrue(body instanceof Deploy);
		Deploy deploy = (Deploy) body;
		assertTrue(deploy != null);
		assertTrue(deploy.getHash().toLowerCase()
				.equals("5ff526617848b4416f818009dc90dd35485b4270a54d52f33652995472ef1fa9"));
	}

	@Test
	public void testCallWithout_DEPLOY_HASH_Parameter() throws Exception {

		Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.DEPLOY);
		template.send(exchange);
		Exception exception = exchange.getException();
		assertTrue(exception instanceof CamelExchangeException);
		String expectedMessage = "deployHash parameter is required with endpoint operation DEPLOY.";
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
