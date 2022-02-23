package org.apache.camel.component.casper.consumer.sse.model.block;

import java.util.ArrayList;

public class Body {
	 private String proposer;
	 ArrayList < String > deploy_hashes = new ArrayList < String > ();
	 ArrayList < String > transfer_hashes = new ArrayList < String > ();


	 // Getter Methods 

	 public Body(String proposer, ArrayList<String> deploy_hashes, ArrayList<String> transfer_hashes) {
		super();
		this.proposer = proposer;
		this.deploy_hashes = deploy_hashes;
		this.transfer_hashes = transfer_hashes;
	}

	public ArrayList<String> getDeploy_hashes() {
		return deploy_hashes;
	}

	public void setDeploy_hashes(ArrayList<String> deploy_hashes) {
		this.deploy_hashes = deploy_hashes;
	}

	public ArrayList<String> getTransfer_hashes() {
		return transfer_hashes;
	}

	public void setTransfer_hashes(ArrayList<String> transfer_hashes) {
		this.transfer_hashes = transfer_hashes;
	}

	public String getProposer() {
	  return proposer;
	 }

	 // Setter Methods 

	 public void setProposer(String proposer) {
	  this.proposer = proposer;
	 }
	}