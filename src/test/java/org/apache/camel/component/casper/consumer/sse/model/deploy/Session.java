package org.apache.camel.component.casper.consumer.sse.model.deploy;

public class Session {
	 Transfer TransferObject;


	 public Session(Transfer transferObject) {
		super();
		TransferObject = transferObject;
	}

	// Getter Methods 

	 public Transfer getTransferObject() {
		return TransferObject;
	}

	public void setTransferObject(Transfer transferObject) {
		TransferObject = transferObject;
	}

	public Transfer getTransfer() {
	  return TransferObject;
	 }

	 // Setter Methods 

	 public void setTransfer(Transfer TransferObject) {
	  this.TransferObject = TransferObject;
	 }
	}