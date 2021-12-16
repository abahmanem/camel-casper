package org.apache.camel.component.casper;


import org.apache.camel.CamelException;
import com.casper.sdk.CasperSdk;
import org.apache.camel.support.DefaultProducer;
import org.apache.camel.Message;
import org.apache.camel.Exchange;

import org.apache.camel.component.casper.CasperEndPoint;

import java.util.List;
import java.util.Arrays;
import com.casper.sdk.types.Digest;

/**
 * The direct consumer.
 */
public class CasperProducer extends DefaultProducer {
   private final CasperEndPoint endpoint;
   private final CasperSdk sdk;

   private List <String> supportedOperations = Arrays.asList("get_peers", "get_contract_hash", "get_info_block_by_height", "get_account_main_purse", "get_account_hash", "get_account_info", "get_account_balance", "get_block_info", "rpc_schema", "get_node_status", "get_node_metrics", "get_auction_info", "get_state_root_hash", "get_block_transfers", "get_latest_block_info");

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

      case "get_block_info":
         if (in.getHeader("BLOCK_HASH") == null) {
            throw new CamelException("Pleade provide a header BLOCK_HASH for the operation \"get_block_info\"");
         }
         in.setBody(sdk.getBlockInfo(new Digest(in.getHeader("BLOCK_HASH").toString())));
         break;

      case "get_account_balance":
         if (in.getHeader("PUBLIC_KEY") == null) {
            throw new CamelException("Pleade provide a header PUBLIC_KEY for the operation \"get_account_balance\"");
         }

         in.setBody(sdk.getAccountBalance(sdk.createPublicKey(in.getHeader("PUBLIC_KEY").toString())));
         break;

      case "get_account_info":
         if (in.getHeader("PUBLIC_KEY") == null) {
            throw new CamelException("Pleade provide a header PUBLIC_KEY for the operation \"get_account_info\"");
         }

         in.setBody(sdk.getAccountInfo(sdk.createPublicKey(in.getHeader("PUBLIC_KEY").toString())));
         break;

      case "get_account_hash":
         if (in.getHeader("PUBLIC_KEY") == null) {
            throw new CamelException("Pleade provide a header PUBLIC_KEY for the operation \"get_account_hash\"");
         }

         in.setBody(sdk.getAccountHash(sdk.createPublicKey(in.getHeader("PUBLIC_KEY").toString())));
         break;

      case "get_account_main_purse":
         if (in.getHeader("PUBLIC_KEY") == null) {
            throw new CamelException("Pleade provide a header PUBLIC_KEY for the operation \"get_account_main_purse\"");
         }

         in.setBody(sdk.getAccountMainPurseURef(sdk.createPublicKey(in.getHeader("PUBLIC_KEY").toString())));
         break;

      case "get_contract_hash":
         if (in.getHeader("PUBLIC_KEY") == null) {
            throw new CamelException("Pleade provide a header PUBLIC_KEY for the operation \"get_contract_hash\"");
         }

         in.setBody(sdk.getContractHash(sdk.createPublicKey(in.getHeader("PUBLIC_KEY").toString())));
         break;

      case "get_info_block_by_height":
         if (in.getHeader("PUBLIC_KEY") == null) {
            throw new CamelException("Pleade provide a header BLOCK_HEIGHT for the operation \"get_info_block_by_height\"");
         }

         in.setBody(sdk.getBlockInfoByHeight(Integer.parseInt(in.getHeader("BLOCK_HEIGHT").toString())));
         break;

      case "rpc_schema":
         in.setBody(sdk.getRpcSchema());
         break;
      }
   }
}
