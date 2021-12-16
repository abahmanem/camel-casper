package org.apache.camel.component.casper;

import org.apache.camel.CamelException;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URISyntaxException;
import com.casper.sdk.CasperSdk;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultEndpoint;

import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.spi.Metadata;

import org.apache.camel.component.casper.CasperProducer;

import org.apache.commons.validator.routines.UrlValidator;


@UriEndpoint(firstVersion = "1.0.0", scheme = "casper", title = "Casper", syntax = "casper:nodeAddress", consumerOnly = true)
public class CasperEndPoint extends DefaultEndpoint {
   @UriParam
   private String operation;

   private URI nodeAddress;

   public CasperEndPoint(String uri, String remaining, CasperComponent myComponent) throws Exception
   {
      super(uri, myComponent);
      validateAndSetURL(remaining);
   }

   @Override
   public Consumer createConsumer(Processor processor) throws Exception
   {
      Consumer consumer = new CasperConsumer(this, processor);

      configureConsumer(consumer);
      return consumer;
   }

   @Override
   public Producer createProducer() throws Exception
   {
      return new CasperProducer(this);
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
      return operation;
   }

   /**
    * @param operation the operation to set
    */
   public void setOperation(String operation)
   {
      this.operation = operation;
   }

   /**
    * @param nodeAddress the nodeAddress to set
    */
   public void setNodeAddress(URI nodeAddress)
   {
      this.nodeAddress = nodeAddress;
   }

   /**
    * @return the nodeAddress
    */
   public URI getNodeAddress()
   {
      return nodeAddress;
   }

   public void validateAndSetURL(String url) throws Exception
   {
      UrlValidator validator = new UrlValidator();

      if (!validator.isValid(url)) {
         throw new CamelException("Please provide a valid \"URL\" parameter. Get : " + url);
      }
      setNodeAddress(new URL(url).toURI());
   }
}
