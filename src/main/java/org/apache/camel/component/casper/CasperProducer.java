package org.apache.camel.component.casper;


import org.apache.camel.CamelException;
import com.casper.sdk.CasperSdk;
import org.apache.camel.support.DefaultProducer;
import org.apache.camel.Message;
import org.apache.camel.Exchange;

import org.apache.camel.component.casper.CasperEndPoint;

import java.util.List;
import java.util.Arrays;

/**
 * The direct consumer.
 */
public class CasperProducer extends DefaultProducer {
   private final CasperEndPoint endpoint;
   private final CasperSdk sdk;

   private List <String> supportedOperations = Arrays.asList("get_peers", "rpc_schema", "get_node_status", "get_node_metrics", "get_auction_info", "get_state_root_hash", "get_block_transfers", "get_latest_block_info");

   public CasperProducer(CasperEndPoint endpoint) throws Exception
   {
      super(endpoint);

      if (endpoint.getOperation() == null || (endpoint.getOperation() != null&& !supportedOperations.contains(endpoint.getOperation()))) {
         throw new CamelException("Please provide a valid \"operation\" parameter. Get : " + endpoint.getOperation() + ". Allowed :" + supportedOperations.toString());
      }

      this.endpoint = endpoint;
      this.sdk = new CasperSdk(endpoint.getNodeAddress().getScheme() + "://" + endpoint.getNodeAddress().getHost(), endpoint.getNodeAddress().getPort());
   }

   @Override
   public void process(Exchange exchange) throws Exception
   {
      Message in = exchange.getIn();

      switch (endpoint.getOperation())
      {
      case "get_peers":
         in.setBody(sdk.getNodePeers());
         break;

      case "get_latest_block_info":
         in.setBody(sdk.getLatestBlockInfo());
         break;

      case "get_block_transfers":
         in.setBody(sdk.getBlockTransfers());
         break;

      case "get_state_root_hash":
         in.setBody(sdk.getStateRootHash());
         break;

      case "get_auction_info":
         in.setBody(sdk.getAuctionInfo());
         break;

      case "get_node_metrics":
         in.setBody(sdk.getNodeMetrics());
         break;

      case "get_node_status":
         in.setBody(sdk.getNodeStatus());
         break;

      case "rpc_schema":
         in.setBody(sdk.getRpcSchema());
         break;
      }
   }
}
