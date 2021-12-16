package org.apache.camel.component.casper;

import org.apache.camel.CamelException;

import org.apache.camel.Processor;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.Suspendable;
import org.apache.camel.spi.ShutdownAware;
import org.apache.camel.support.DefaultConsumer;


import org.apache.camel.component.casper.CasperEndPoint;

import java.util.List;
import java.util.Arrays;

import java.time.Duration;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

/**
 * The direct consumer.
 */
public class CasperConsumer extends DefaultConsumer implements ShutdownAware, Suspendable {
   private final CasperEndPoint endpoint;

   private List <String> supportedPaths = Arrays.asList("/events/main", "/events/deploys", "/events/sigs");
   private List <String> supportedMainOperations = Arrays.asList("block_added", "api_version", "deploy_processed");
   private List <String> supportedDeploysOperations = Arrays.asList("deploy_accepted");
   private List <String> supportedSigsOperations = Arrays.asList("finality_signature");

   public CasperConsumer(CasperEndPoint endpoint, Processor processor) throws Exception
   {
      super(endpoint, processor);

      if (endpoint.getNodeAddress().getPath().isEmpty() || (!endpoint.getNodeAddress().getPath().isEmpty() && !supportedPaths.contains(endpoint.getNodeAddress().getPath()))) {
         throw new CamelException("Please provide a valid \"path\" parameter. Get : " + endpoint.getNodeAddress().getPath() + ". Allowed :" + supportedPaths.toString());
      }
      if (endpoint.getOperation() == null || (endpoint.getOperation() != null&& endpoint.getNodeAddress().getPath().equals("/events/mains") && !supportedMainOperations.contains(endpoint.getOperation()))) {
         throw new CamelException("Please provide a valid \"operation\" parameterfor \"" + endpoint.getNodeAddress().getPath() + "\". Get : " + endpoint.getOperation() + ". Allowed :" + supportedMainOperations.toString());
      }
      if (endpoint.getOperation() == null || (endpoint.getOperation() != null&& endpoint.getNodeAddress().getPath().equals("/events/deploys") && !supportedDeploysOperations.contains(endpoint.getOperation()))) {
         throw new CamelException("Please provide a valid \"operation\" parameter for \"" + endpoint.getNodeAddress().getPath() + "\". Get : " + endpoint.getOperation() + ". Allowed :" + supportedDeploysOperations.toString());
      }
      if (endpoint.getOperation() == null || (endpoint.getOperation() != null&& endpoint.getNodeAddress().getPath().equals("/events/sigs") && !supportedSigsOperations.contains(endpoint.getOperation()))) {
         throw new CamelException("Please provide a valid \"operation\" parameterfor \"" + endpoint.getNodeAddress().getPath() + "\". Get : " + endpoint.getOperation() + ". Allowed :" + supportedSigsOperations.toString());
      }

      this.endpoint = endpoint;
   }

   @Override
   protected void doStart() throws Exception
   {
      super.doStart();
      EventHandler eventHandler = new CasperEventHandler(this);
      EventSource builder = new EventSource.Builder(eventHandler, endpoint.getNodeAddress()).reconnectTime(Duration.ofMillis(3000)).build();
      builder.start();
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
}
