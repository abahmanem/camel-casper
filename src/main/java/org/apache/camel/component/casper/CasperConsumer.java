package org.apache.camel.component.casper;

import org.apache.camel.CamelException;
import org.apache.camel.component.casper.sse.EventStreamAdapter;
import org.apache.camel.component.casper.sse.HttpEventStreamClient;
import org.apache.camel.component.casper.sse.HttpEventStreamClient.Event;
import org.json.JSONObject;

import org.apache.camel.Processor;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.Suspendable;
import org.apache.camel.spi.ShutdownAware;
import org.apache.camel.support.DefaultConsumer;

import org.apache.camel.Exchange;

import org.apache.camel.component.casper.CasperEndPoint;

import java.util.List;
import java.util.Arrays;

/**
 * The direct consumer.
 */
public class CasperConsumer extends DefaultConsumer implements ShutdownAware, Suspendable {
   private final CasperEndPoint endpoint;

   private List <String> supportedPaths = Arrays.asList("/events/main", "/events/deploys", "/events/sigs");
   private List <String> supportedOperations = Arrays.asList("block_added");

   public CasperConsumer(CasperEndPoint endpoint, Processor processor) throws Exception
   {
      super(endpoint, processor);

      if (endpoint.getNodeAddress().getPath().isEmpty() || (!endpoint.getNodeAddress().getPath().isEmpty() && !supportedPaths.contains(endpoint.getNodeAddress().getPath()))) {
         throw new CamelException("Please provide a valid \"path\" parameter. Get : " + endpoint.getNodeAddress().getPath() + ". Allowed :" + supportedPaths.toString());
      }
      if (endpoint.getOperation() == null || (endpoint.getOperation() != null&& !supportedOperations.contains(endpoint.getOperation()))) {
         throw new CamelException("Please provide a valid \"operation\" parameter. Get : " + endpoint.getOperation() + ". Allowed :" + supportedOperations.toString());
      }

      this.endpoint = endpoint;
   }

   @Override
   protected void doStart() throws Exception
   {
      super.doStart();


      HttpEventStreamClient client = new HttpEventStreamClient(endpoint.getNodeAddress().toString(), new EventStreamAdapter()
                                                               {
                                                                  @Override
                                                                  public void onEvent(HttpEventStreamClient client, Event event)
                                                                  {
                                                                     if (endpoint.getOperation().equals("block_added") && event.getData().contains("BlockAdded")) {
                                                                        processMessage(new JSONObject(event.getData()));
                                                                     }
                                                                  }
                                                               });
      client.start().join();
   }

   @Override
   public boolean deferShutdown(ShutdownRunningTask shutdownRunningTask)
   {
      // deny stopping on shutdown as we want direct consumers to run in case some other queues
      // depend on this consumer to run, so it can complete its exchanges
      return true;
   }

   @Override
   public int getPendingExchangesSize()
   {
      // return 0 as we do not have an internal memory queue with a variable size
      // of inflight messages.
      return 0;
   }

   @Override
   public void prepareShutdown(boolean suspendOnly, boolean forced)
   {
      // noop
   }

   public void processMessage(JSONObject data)
   {
      final Exchange exchange = createExchange(false);

      exchange.getMessage().setBody(data);
      try {
         getProcessor().process(exchange);
      } catch (Exception e) {
         exchange.setException(e);
      }
   }
}
