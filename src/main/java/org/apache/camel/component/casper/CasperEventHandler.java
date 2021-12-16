package org.apache.camel.component.casper;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

import org.json.JSONObject;


import org.apache.camel.Exchange;

import org.apache.camel.component.casper.CasperEndPoint;

public class CasperEventHandler implements EventHandler {
   private final CasperConsumer consumer;
   private final CasperEndPoint endpoint;


   public CasperEventHandler(CasperConsumer consumer)
   {
      super();
      this.consumer = consumer;
      this.endpoint = (CasperEndPoint) this.consumer.getEndpoint();
   }

   @Override
   public void onOpen() throws Exception
   {
   }

   @Override
   public void onClosed() throws Exception
   {
   }

   @Override
   public void onMessage(String event, MessageEvent messageEvent) throws Exception
   {
      if (endpoint.getOperation().equals("block_added") && messageEvent.getData().contains("BlockAdded")) {
         processMessage(new JSONObject(messageEvent.getData()));
      }
   }

   @Override
   public void onComment(String comment) throws Exception
   {
   }

   @Override
   public void onError(Throwable t)
   {
   }

   private void processMessage(JSONObject data)
   {
      final Exchange exchange = consumer.createExchange(false);

      exchange.getMessage().setBody(data);
      try {
         consumer.getProcessor().process(exchange);
      } catch (Exception e) {
         exchange.setException(e);
      }
   }
}
