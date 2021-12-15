package org.apache.camel.component.casper;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultEndpoint;

import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

@UriEndpoint(firstVersion = "1.0.0", scheme = "casper", title = "Casper", syntax = "casper:casperUri", consumerOnly = true)
public class CasperEndPoint extends DefaultEndpoint {
   @UriParam
   private String operation;

   public CasperEndPoint(String uri, CasperComponent myComponent)
   {
      super(uri, myComponent);
   }

   @Override
   public Consumer createConsumer(Processor processor) throws Exception
   {
      Consumer consumer = new CasperConsumer(this, processor);

      configureConsumer(consumer);
      return(consumer);
   }

   @Override
   public Producer createProducer() throws Exception
   {
      throw new UnsupportedOperationException();
   }

   @Override
   protected void doStop() throws Exception
   {
      super.doStop();
   }

   @Override
   protected void doStart() throws Exception
   {
      super.doStart();
   }

   @Override
   protected void doShutdown() throws Exception
   {
      super.doShutdown();
   }

   /**
    * @return the operation
    */
   public String getOperation()
   {
      return(operation);
   }

   /**
    * @param operation the operation to set
    */
   public void setOperation(String operation)
   {
      this.operation = operation;
   }
}
