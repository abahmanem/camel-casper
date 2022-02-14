package org.apache.camel.component.casper;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.component.casper.CasperConstants;

import com.syntifi.casper.sdk.service.CasperService;

@UriParams
public class CasperConfiguration implements Cloneable {

	public static Logger logger = LoggerFactory.getLogger(CasperConfiguration.class);

	/**
	 * casperService : Casper RPC SDK
	 */

	@UriParam(label = "common", description = "Casper RPC API used to perform RPC queries on Casper Network Blockchain")
	private CasperService casperService;

	/**
	 * operation parameter
	 */
	@UriParam(label = "common", defaultValue = CasperConstants.NODE_STATUS, description = "The endpoint operation.", enums = CasperConstants.ENDPOINT_SERVICE)
	private String operation;

	/**
	 * deployHash parameter
	 */

	@UriParam(label = "producer", description = "Deploy hash : used to query a Deploy in the network")
	private String deployHash;

	/**
	 * blockHeight parameter
	 */

	@UriParam(label = "producer", description = "Block height : used to query a Block in the network ")
	private Long blockHeight=null;

	/**
	 * blockHash parameter
	 */

	@UriParam(label = "producer", description = "Deploy Hash : used to query a Block in the network")
	private String blockHash;

	/**
	 * publicKey parameter
	 */

	@UriParam(label = "producer", description = "Account publick key  : used to query a account infos")
	private String publicKey;

	/**
	 * uref parameter
	 */

	@UriParam(label = "producer", description = "casper_types::Key as formatted string")
	private String key;

	/**
	 * uref parameter
	 */

	@UriParam(label = "producer", description = "The path components starting from the key as base")
	private String path;

	/**
	 * stateRootHash parameter
	 */

	@UriParam(label = "producer", description = "state_Root_Hash : an identifier of the current network state")
	private String stateRootHash;

	/**
	 * purseUref parameter
	 */

	@UriParam(label = "producer", description = "purseUref : URef of an  account main purse")
	private String purseUref;

	public String getPurseUref() {
		return purseUref;
	}

	public void setPurseUref(String purseUref) {
		this.purseUref = purseUref;
	}

	public String getStateRootHash() {
		return stateRootHash;
	}

	public void setStateRootHash(String stateRootHash) {
		this.stateRootHash = stateRootHash;
	}


	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Long getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(Long blockHeight) {
		this.blockHeight = blockHeight;
	}

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getDeployHash() {
		return deployHash;
	}

	public void setDeployHash(String deployHash) {
		this.deployHash = deployHash;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public CasperService getCasperService() {
		return casperService;
	}

	public void setCasperService(CasperService casperService) {
		this.casperService = casperService;
	}

	public String getOperationOrDefault() {
		return this.operation != null ? operation : CasperConstants.NODE_STATUS;
	}

	public CasperConfiguration clone() {
		try {
			return (CasperConfiguration) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeCamelException(e);
		}
	}

}
