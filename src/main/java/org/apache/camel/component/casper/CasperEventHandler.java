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
      JSONObject json = new JSONObject(messageEvent.getData());
      String firstJsonPropertyKey = "";

      if (json.keys().hasNext()) {
         firstJsonPropertyKey = json.keys().next();
      }

      if (endpoint.getOperation().equals("block_added") && !firstJsonPropertyKey.isEmpty() && firstJsonPropertyKey.equals("BlockAdded")) {
         processMessage(json.getJSONObject("BlockAdded"));
      }
      if (endpoint.getOperation().equals("api_version") && !firstJsonPropertyKey.isEmpty() && firstJsonPropertyKey.equals("ApiVersion")) {
         processMessage(json);
      }
      if (endpoint.getOperation().equals("deploy_processed") && !firstJsonPropertyKey.isEmpty() && firstJsonPropertyKey.equals("DeployProcessed")) {
         processMessage(json.getJSONObject("DeployProcessed"));
      }
      if (endpoint.getOperation().equals("deploy_accepted") && !firstJsonPropertyKey.isEmpty() && firstJsonPropertyKey.equals("DeployAccepted")) {
         processMessage(json.getJSONObject("DeployAccepted"));
      }
      if (endpoint.getOperation().equals("finality_signature") && !firstJsonPropertyKey.isEmpty() && firstJsonPropertyKey.equals("FinalitySignature")) {
         processMessage(json.getJSONObject("FinalitySignature"));
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
