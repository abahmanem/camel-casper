package org.apache.camel.component.casper;


import java.net.http.HttpRequest.BodyPublishers;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import org.apache.camel.support.DefaultProducer;
import org.apache.camel.Message;
import org.apache.camel.Exchange;

import org.apache.camel.component.casper.CasperEndPoint;

import java.net.http.HttpResponse;

/**
 * The direct consumer.
 */
public class CasperProducer extends DefaultProducer {
   public CasperProducer(CasperEndPoint endpoint)
   {
      super(endpoint);
   }

   @Override
   public void process(Exchange exchange) throws Exception
   {
      System.out.println("Producer Casper");
      Message in = exchange.getIn();
      in.setBody(this.sendRPC());
   }

   private String sendRPC() throws Exception
   {
      String data = "{\"id\": 1,  \"jsonrpc\": \"2.0\",  \"method\": \"info_get_peers\",  \"params\": []}";
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://65.21.202.120:7777/rpc"))
                            .header("Content-Type", "application/json")
                            .POST(BodyPublishers.ofString(data))
                            .build();

      HttpResponse <String> response =
         client.send(request, BodyHandlers.ofString());
//
      System.out.println(response.body());
      return response.body();
   }
}
