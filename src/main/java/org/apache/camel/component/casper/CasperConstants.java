package org.apache.camel.component.casper;

/**
 * @author mabahma
 */
public interface CasperConstants {
    String ENDPOINT_SERVICE = "NETWORK_PEERS, NODE_STATUS,DEPLOY,LAST_BLOCK,BLOCK,LAST_BLOCK_TRANSFERS,BLOCK_TRANSFERS,STATE_ROOT_HASH,ACCOUNT_INFO"
            + ",AUCTION_INFO,ERA_INFO,STATE_ITEM,ACCOUNT_BALANC,ERPC_SCHEMA";
    //RPC Calls
    String CHAIN_GET_BLOCK = "CHAIN_GET_BLOCK";
    String INFO_GET_PEERS = "INFO_GET_PEERS";
    String INFO_GET_STATUS = "INFO_GET_STATUS";
    String CHAIN_GET_BLOCK_TRANSFERTS = "CHAIN_GET_BLOCK_TRANSFERTS";
    String INFO_GET_DEPLOY = "INFO_GET_DEPLOY";
    String STATE_GET_AUCTION_INFO = "STATE_GET_AUCTION_INFO";
    String CHAIN_GET_ERA_INFO_BY_SWITCH_BLOCK = "CHAIN_GET_ERA_INFO_BY_SWITCH_BLOCK";
    String STATE_GET_ITEM = "STATE_GET_ITEM";
    String STATE_GET_BALANCE = "STATE_GET_BALANCE";
    String STATE_GET_DICTIONARY_ITEM = "STATE_GET_DICTIONARY_ITEM";
    String ACCOUNT_PUT_DEPLOY = "ACCOUNT_PUT_DEPLOY";
    //OPERATION
    String OPERATION = "OPERATION";
    //Query peers
    String NETWORK_PEERS = "NETWORK_PEERS";
    // Get node status
    String NODE_STATUS = "NODE_STATUS";
    //Query deploy
    String DEPLOY = "DEPLOY";
    String DEPLOY_HASH = "DEPLOY_HASH";
    //Query a block
    String LAST_BLOCK = "LAST_BLOCK";
    String BLOCK = "BLOCK";
    String BLOCK_HEIGHT = "BLOCK_HEIGHT";
    String BLOCK_HASH = "BLOCK_HASH";
    //Query transfers : params===>  BLOCK_HASH or  BLOCK_HEIGHT
    String LAST_BLOCK_TRANSFERS = "LAST_BLOCK_TRANSFERS";
    String BLOCK_TRANSFERS = "BLOCK_TRANSFERS";
    //Query state root hash :  params===>  BLOCK_HASH or  BLOCK_HEIGHT
    String STATE_ROOT_HASH = "STATE_ROOT_HASH";
    //Get account info  :  params===>  BLOCK_HASH or  BLOCK_HEIGHT
    String ACCOUNT_INFO = "ACCOUNT_INFO";
    String PUBLIC_KEY = "PUBLIC_KEY";
    //Get auction info : params===>  BLOCK_HASH or  BLOCK_HEIGHT
    String AUCTION_INFO = "AUCTION_INFO";
    //Get era info :  params===>  BLOCK_HASH or  BLOCK_HEIGHT
    String ERA_INFO = "ERA_INFO";
    //Query stored value :  params===>STATE_ROOT_HASH
    String STATE_ITEM = "STATE_ITEM";
    String PATH = "PATH";
    String ITEM_KEY = "ITEM_KEY";
    //Account Balance
    String ACCOUNT_BALANCE = "ACCOUNT_BALANCE";
    String PURSE_UREF = "PURSE_UREF";
    //RPC Schema
    String RPC_SCHEMA = "RPC_SCHEMA";
    //TODO
    String NETWORK_VERSION = "NETWORK_VERSION";
    String NETWORK_FEES = "NETWORK_FEES";
    //Consumer valid url paths
    String CONSUMER_PATHS = "/events/main,/events/deploys,/events/sigs";
    //Testnet nodeUrl
    String TESTNET_NODE_URL = "http://65.108.1.10:7777";
    String TESTNET_ENDPOINT_TEST = "casper:" + TESTNET_NODE_URL;
    String ERROR_CAUSE = "ERROR_CAUSE";
}
