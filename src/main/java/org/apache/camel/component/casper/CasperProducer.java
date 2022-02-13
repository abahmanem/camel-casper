package org.apache.camel.component.casper;

import java.io.IOException;
import java.util.Arrays;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Message;
import org.apache.camel.spi.InvokeOnHeader;
import org.apache.camel.support.HeaderSelectorProducer;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.syntifi.casper.sdk.identifier.block.HashBlockIdentifier;
import com.syntifi.casper.sdk.identifier.block.HeightBlockIdentifier;
import com.syntifi.casper.sdk.model.account.AccountData;
import com.syntifi.casper.sdk.model.auction.AuctionData;
import com.syntifi.casper.sdk.model.balance.BalanceData;
import com.syntifi.casper.sdk.model.block.JsonBlock;
import com.syntifi.casper.sdk.model.deploy.DeployData;
import com.syntifi.casper.sdk.model.era.EraInfoData;
import com.syntifi.casper.sdk.model.peer.PeerData;
import com.syntifi.casper.sdk.model.stateroothash.StateRootHashData;
import com.syntifi.casper.sdk.model.status.StatusData;
import com.syntifi.casper.sdk.model.storedvalue.StoredValueData;
import com.syntifi.casper.sdk.model.transfer.TransferData;
import com.syntifi.casper.sdk.service.CasperService;

/**
 * The direct consumer.
 */

public class CasperProducer extends HeaderSelectorProducer {

	private final CasperEndPoint endpoint;
	/**
	 * casperService
	 */
	private final CasperService casperService;

	public CasperService getCasperService() {
		return casperService;
	}

	private CasperConfiguration configuration;

	public static Logger logger = LoggerFactory.getLogger(CasperEndPoint.class);

	public CasperProducer(CasperEndPoint endpoint, final CasperConfiguration configuration) throws Exception {
		super(endpoint, CasperConstants.OPERATION, () -> configuration.getOperationOrDefault(), false);
		this.endpoint = endpoint;
		this.configuration = configuration;
		this.casperService = endpoint.getCasperService();

	}

	@Override
	public CasperEndPoint getEndpoint() {
		return (CasperEndPoint) super.getEndpoint();
	}

	public CasperConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(CasperConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * peers count
	 * 
	 * @param message
	 * @throws IOException
	 */
	@InvokeOnHeader(CasperConstants.NETWORK_PEERS)
	void listPeers(Message message) throws Exception {

		System.err.println("RPC method listpeers was Called through casper producer");

		PeerData peerData = null;

		try {
			peerData = casperService.getPeerData();

		} catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (peerData != null)
			message.setBody(peerData.getPeers());

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.NODE_STATUS)
	void nodeStatus(Message message) throws Exception {
		System.err.println("RPC method nodestatus was Called through casper producer");

		StatusData statusData = null;

		try {
			statusData = casperService.getStatus();

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (statusData != null)
			message.setBody(statusData);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.DEPLOY)
	void deploy(Message message) throws Exception {
		System.err.println("RPC method deploy was Called through casper producer");

		String deployHash = message.getHeader(CasperConstants.DEPLOY_HASH, configuration::getDeployHash, String.class);

		DeployData deploy = null;

		if (StringUtils.isEmpty(deployHash)) {

			handleError(
					new MissingArgumentException(
							"deployHash parameter is required with endpoint operation " + CasperConstants.DEPLOY),
					message);
			return;
		}

		try {
			deploy = casperService.getDeploy(deployHash);

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}
		if (deploy != null)
			message.setBody(deploy.getDeploy());
	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.LAST_BLOCK)
	void lastBlock(Message message) throws Exception {
		System.err.println("RPC method lastBlock was Called through casper producer");

		JsonBlock block = null;

		try {
			block = casperService.getBlock().getBlock();

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}
		if (block != null)
			message.setBody(block);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.BLOCK)
	void block(Message message) throws Exception {
		System.err.println("RPC method blockAtHeight was Called through casper producer");

		JsonBlock block = null;
		String blockHash = message.getHeader(CasperConstants.BLOCK_HASH, configuration::getBlockHash, String.class);
		Long blockHeight = message.getHeader(CasperConstants.BLOCK_HEIGHT, configuration::getBlockHeight, Long.class);

		try {

			if (!StringUtils.isEmpty(blockHash))
				block = casperService.getBlock(new HashBlockIdentifier(blockHash)).getBlock();

			else if (blockHeight != null)
				block = casperService.getBlock(new HeightBlockIdentifier(blockHeight)).getBlock();

			else
				// get last block
				block = casperService.getBlock().getBlock();

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}
		if (block != null)
			message.setBody(block);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.STATE_ROOT_HASH)
	void stateRootHash(Message message) throws Exception {
		System.err.println("RPC method stateRootHash was Called through casper producer");
		StateRootHashData stateRootHashData = null;
		String blockHash = message.getHeader(CasperConstants.BLOCK_HASH, configuration::getBlockHash, String.class);
		Long blockHeight = message.getHeader(CasperConstants.BLOCK_HEIGHT, configuration::getBlockHeight, Long.class);

		try {

			if (!StringUtils.isEmpty(blockHash))
				stateRootHashData = casperService.getStateRootHash(new HashBlockIdentifier(blockHash));

			else if (blockHeight != null)
				stateRootHashData = casperService.getStateRootHash(new HeightBlockIdentifier(blockHeight));

			else
				stateRootHashData = casperService.getStateRootHash();

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}
		if (stateRootHashData != null)
			message.setBody(stateRootHashData);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.ACCOUNT_INFO)
	void accountInfo(Message message) throws Exception {
		System.err.println("RPC method accountInfo was Called through casper producer");
		AccountData accountData = null;
		String publicKey = message.getHeader(CasperConstants.PUBLIC_KEY, configuration::getPublicKey, String.class);
		String blockHash = message.getHeader(CasperConstants.BLOCK_HASH, configuration::getBlockHash, String.class);
		Long blockHeight = message.getHeader(CasperConstants.BLOCK_HEIGHT, configuration::getBlockHeight, Long.class);

		if (StringUtils.isEmpty(publicKey)) {

			handleError(
					new MissingArgumentException(
							"publicKey parameter is required  with endpoint operation " + CasperConstants.ACCOUNT_INFO),
					message);
			return;
		}

		try {

			if (!StringUtils.isEmpty(blockHash))

				accountData = casperService.getStateAccountInfo(publicKey, new HashBlockIdentifier(blockHash));

			else if (blockHeight != null)
				accountData = casperService.getStateAccountInfo(publicKey, new HeightBlockIdentifier(blockHeight));

			else

				handleError(new MissingArgumentException(
						"Either blockHeight or BlockHash parameter is required  with endpoint operation "
								+ CasperConstants.ACCOUNT_INFO),
						message);
		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}
		if (accountData != null)
			message.setBody(accountData);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.LAST_BLOCK_TRANSFERS)
	void lastBlockTransfers(Message message) throws Exception {
		System.err.println("RPC method lastBlockTransfers was Called through casper producer");

		TransferData transferData = null;

		try {

			transferData = casperService.getBlockTransfers();

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}
		if (transferData != null)
			message.setBody(transferData);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.BLOCK_TRANSFERS)
	void blockTransfers(Message message) throws Exception {
		System.err.println("RPC method blockTransfers was Called through casper producer");

		TransferData transferData = null;
		String blockHash = message.getHeader(CasperConstants.BLOCK_HASH, configuration::getBlockHash, String.class);
		Long blockHeight = message.getHeader(CasperConstants.BLOCK_HEIGHT, configuration::getBlockHeight, Long.class);

		try {

			if (!StringUtils.isEmpty(blockHash))

				transferData = casperService.getBlockTransfers(new HashBlockIdentifier(blockHash));

			else if (blockHeight != null)

				transferData = casperService.getBlockTransfers(new HeightBlockIdentifier(blockHeight));

			else

				handleError(new MissingArgumentException(
						"Either blockHeight or BlockHash parameter is required  with endpoint operation "
								+ CasperConstants.ACCOUNT_INFO),
						message);

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (transferData != null)
			message.setBody(transferData);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.AUCTION_INFO)
	void auctionInfo(Message message) throws Exception {
		System.err.println("RPC method auctionInfo was Called through casper producer");

		AuctionData auction = casperService.getStateAuctionInfo(new HeightBlockIdentifier(1234));
		String blockHash = message.getHeader(CasperConstants.BLOCK_HASH, configuration::getBlockHash, String.class);
		Long blockHeight = message.getHeader(CasperConstants.BLOCK_HEIGHT, configuration::getBlockHeight, Long.class);

		try {

			if (!StringUtils.isEmpty(blockHash))

				auction = casperService.getStateAuctionInfo(new HashBlockIdentifier(blockHash));

			else if (blockHeight != null)

				auction = casperService.getStateAuctionInfo(new HeightBlockIdentifier(blockHeight));

			else
				handleError(new MissingArgumentException(
						"Either blockHeight or BlockHash parameter is required  with endpoint operation "
								+ CasperConstants.ACCOUNT_INFO),
						message);
		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (auction != null)
			message.setBody(auction);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.ERA_INFO)
	void eraInfo(Message message) throws Exception {
		System.err.println("RPC method eraInfo was Called through casper producer");
		EraInfoData eraInfoData = null;

		String blockHash = message.getHeader(CasperConstants.BLOCK_HASH, configuration::getBlockHash, String.class);
		Long blockHeight = message.getHeader(CasperConstants.BLOCK_HEIGHT, configuration::getBlockHeight, Long.class);

		try {

			if (!StringUtils.isEmpty(blockHash))

				eraInfoData = casperService.getEraInfoBySwitchBlock(new HashBlockIdentifier(blockHash));

			else if (blockHeight != null)

				eraInfoData = casperService.getEraInfoBySwitchBlock(new HeightBlockIdentifier(blockHeight));

			else

				handleError(new MissingArgumentException(
						"Either blockHeight or BlockHash parameter is required  with endpoint operation "
								+ CasperConstants.ACCOUNT_INFO),
						message);

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (eraInfoData != null)
			message.setBody(eraInfoData);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.STATE_ITEM)
	void storedValue(Message message) throws Exception {
		System.err.println("RPC method storedValue was Called through casper producer");
		StoredValueData value = null;
		String stateRootHash = message.getHeader(CasperConstants.STATE_ROOT_HASH, configuration::getStateRootHash,
				String.class);
		String itemKeys = message.getHeader(CasperConstants.ITEM_KEY, configuration::getItemKeys, String.class);
		String uref = message.getHeader(CasperConstants.UREF, configuration::getUref, String.class);

		if (StringUtils.isEmpty(stateRootHash)) {

			handleError(new MissingArgumentException(
					"stateRootHash parameter is required  with endpoint operation " + CasperConstants.STATE_ITEM),
					message);
			return;
		}

		if (StringUtils.isEmpty(itemKeys)) {

			handleError(
					new MissingArgumentException(
							"itemKey parameter is required with endpoint operation " + CasperConstants.STATE_ITEM),
					message);
			return;
		}

		if (StringUtils.isEmpty(uref)) {

			handleError(
					new MissingArgumentException(
							"uref parameter is required   with endpoint operation " + CasperConstants.STATE_ITEM),
					message);
			return;
		}

		try {
			value = casperService.getStateItem(stateRootHash, uref, Arrays.asList(itemKeys.split(",")));

		} catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (value != null)
			message.setBody(value);

	}

	/**
	 * 
	 * @param message
	 * @throws Exception
	 */
	@InvokeOnHeader(CasperConstants.ACCOUNT_BALANCE)
	void accountBalance(Message message) throws Exception {
		System.err.println("RPC method storedValue was Called through casper producer");
		BalanceData balance = null;
		String stateRootHash = message.getHeader(CasperConstants.STATE_ROOT_HASH, configuration::getStateRootHash,
				String.class);

		String purseUref = message.getHeader(CasperConstants.PURSE_UREF, configuration::getPurseUref, String.class);

		if (StringUtils.isEmpty(stateRootHash)) {

			handleError(new MissingArgumentException("stateRootHash parameter is required   with endpoint operation "
					+ CasperConstants.ACCOUNT_BALANCE.toLowerCase()), message);
			return;
		}

		if (StringUtils.isEmpty(purseUref)) {

			handleError(new MissingArgumentException("itemKey parameter is required   with endpoint operation "
					+ CasperConstants.ACCOUNT_BALANCE.toLowerCase()), message);
			return;
		}

		try {
			balance = casperService.getBalance(stateRootHash, purseUref);

		}

		catch (Exception e) {
			handleError(e.getCause(), message);
		}

		if (balance != null)
			message.setBody(balance);

	}

	/**
	 * handle errors
	 * 
	 * @param e
	 * @param message
	 */
	private void handleError(Throwable e, Message message) {
		message.setHeader(CasperConstants.ERROR_CAUSE, e);
		message.getExchange().setException(new CamelExchangeException(e.getMessage(), message.getExchange()));

	}

}
