package org.apache.camel.component.casper;

import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.apache.camel.component.casper.CasperConstants;

public class CasperProducerTest extends CasperTestSupport {
	@Produce("direct:start")
    protected ProducerTemplate template;

  

    @Override
    public boolean isUseAdviceWith() {
        return false;
    }


    @Test
    public void listPeers() throws Exception {
  
    	//Mockito.when(casperSdk.getPeerData().getPeers()).thenCallRealMethod();
        Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, CasperConstants.NETWORK_PEERS);
        template.send(exchange);
        String body = exchange.getIn().getBody(String.class);
        assertEquals("bar", body);
    }

    
    @Test
    public void nodeStatus() throws Exception {
  
    	
    	
        Exchange exchange = createExchangeWithBodyAndHeader(null, CasperConstants.OPERATION, "nodeStatus");
        template.send(exchange);
        String body = exchange.getIn().getBody(String.class);
        assertEquals("boo", body);
    }
    
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start")
                        .to(getUrl() );
                        		
            }
        };
    }
    
    
}
