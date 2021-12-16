package org.apache.camel.component.casper;

import org.apache.camel.Endpoint;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.spi.Metadata;
import org.apache.camel.support.DefaultComponent;

import java.util.Map;

@Component("casper")
public class CasperComponent extends DefaultComponent {
   @Metadata(label = "advanced")
   private String casperService;



   public CasperComponent()
   {
   }

   @Override
   protected Endpoint createEndpoint(String uri, String remaining, Map <String, Object> parameters) throws Exception
   {
      CasperEndPoint answer = new CasperEndPoint(uri, remaining, this);

      setProperties(answer, parameters);

      return answer;
   }

   @Override
   protected void doInit() throws Exception
   {
      super.doInit();
   }

   /**
    * @return the casperService
    */
   public String getCasperService()
   {
      return casperService;
   }

   /**
    * @param casperService the casperService to set
    */
   public void setCasperService(String casperService)
   {
      this.casperService = casperService;
   }
}
