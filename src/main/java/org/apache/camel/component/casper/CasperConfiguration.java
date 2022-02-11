package org.apache.camel.component.casper;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.syntifi.casper.sdk.service.CasperService;

@UriParams
public class CasperConfiguration implements Cloneable {

	public static Logger logger = LoggerFactory.getLogger(CasperConfiguration.class);

	/**
	 * casperService : Casper java SDK
	 */

	@UriParam(label = "common")
	private CasperService casperService;

	/**
	 * operation parameter
	 */
	@UriParam(label = "producer", defaultValue = "info_get_status")
	private String operation;

	/**
	 * deployHash parameter
	 */

	@UriParam(label = "producer")
	private String deployHash;

	/**
	 * blockHeight parameter
	 */

	@UriParam(label = "producer")
	private long blockHeight;

	/**
	 * blockHash parameter
	 */

	@UriParam(label = "producer")
	private String blockHash;

	/**
	 * publicKey parameter
	 */

	@UriParam(label = "producer")
	private String publicKey;

	
	/**
	 * uref parameter
	 */

	@UriParam(label = "producer")
	private String uref;

	
	
	/**
	 * uref parameter
	 */

	@UriParam(label = "producer")
	private String itemKey;

	
	
	/**
	 * stateRootHash parameter
	 */

	@UriParam(label = "producer")
	private String stateRootHash;

	
	
	/**
	 * purseUref parameter
	 */

	@UriParam(label = "producer")
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

	public String getUref() {
		return uref;
	}

	public void setUref(String uref) {
		this.uref = uref;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public long getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(long blockHeight) {
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
		return this.operation != null ? operation : CasperConstants.INFO_GET_STATUS;
	}

	public CasperConfiguration clone() {
		try {
			return (CasperConfiguration) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeCamelException(e);
		}
	}

}
