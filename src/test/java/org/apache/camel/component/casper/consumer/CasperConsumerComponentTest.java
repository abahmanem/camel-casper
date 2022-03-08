package org.apache.camel.component.casper.consumer;

import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.InvalidPathException;

import org.apache.camel.CamelContext;
import org.apache.camel.FailedToStartRouteException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

public class CasperConsumerComponentTest {

	private final CamelContext context = new DefaultCamelContext();

	@Test
	public void testWithUnsuportedURL() throws Exception {

		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("casper:http://xx.yyy.zzz.ww:8080/events/test?event=test").log("${body}");

			}
		});

		Exception exception = assertThrows(FailedToStartRouteException.class, () -> context.start());
		assertEquals(exception.getCause().getClass(), InvalidPathException.class);
	}

	@Test
	public void testWithUnsuportedSSEEvent() throws Exception {

		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("casper:http://localhost:8080/events/main?event=test").log("${body}");

			}
		});

		Exception exception = assertThrows(FailedToStartRouteException.class, () -> context.start());
		assertEquals(UnsupportedOperationException.class, exception.getCause().getClass());
	}

	@Test
	public void testWithUnsuportedURLPath() throws Exception {

		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("casper:http://localhost:8080/events/test?event=test").log("${body}");

			}
		});

		Exception exception = assertThrows(FailedToStartRouteException.class, () -> context.start());
		assertEquals(exception.getCause().getClass(), InvalidPathException.class);
	}
}
