package org.apache.camel.component.casper;

public enum ConsumerOperation {
	DEPLOY_PROCESSED,
	DEPLOY_ACCEPTED,
	BLOCK_ADDED,
	FINALITY_SIGNATURE,
	STEP;
	/**
	 *  findByName
	 * @param name : name to search
	 * @return: ProducerOperation
	 */
	public static ConsumerOperation findByName(String name) {
		ConsumerOperation result = null;
	    for (ConsumerOperation operation : values()) {
	        if (operation.name().equalsIgnoreCase(name)) {
	            result = operation;
	            break;
	        }
	    }
	    return result;
	}
}
