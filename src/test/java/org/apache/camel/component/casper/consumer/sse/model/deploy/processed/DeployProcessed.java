package org.apache.camel.component.casper.consumer.sse.model.deploy.processed;

import java.util.ArrayList;

public class DeployProcessed {
	 private String deploy_hash;
	 private String account;
	 private String timestamp;
	 private String ttl;
	 ArrayList < String > dependencies = new ArrayList < String > ();
	 private String block_hash;
	 Execution_result Execution_resultObject;


	 
	 
	 
	 public DeployProcessed(String deploy_hash, String account, String timestamp, String ttl,
			ArrayList<String> dependencies, String block_hash, Execution_result execution_resultObject) {
		super();
		this.deploy_hash = deploy_hash;
		this.account = account;
		this.timestamp = timestamp;
		this.ttl = ttl;
		this.dependencies = dependencies;
		this.block_hash = block_hash;
		Execution_resultObject = execution_resultObject;
	}

	// Getter Methods 

	 public String getDeploy_hash() {
	  return deploy_hash;
	 }

	 public String getAccount() {
	  return account;
	 }

	 public String getTimestamp() {
	  return timestamp;
	 }

	 public String getTtl() {
	  return ttl;
	 }

	 public String getBlock_hash() {
	  return block_hash;
	 }

	 public Execution_result getExecution_result() {
	  return Execution_resultObject;
	 }

	 // Setter Methods 

	 public void setDeploy_hash(String deploy_hash) {
	  this.deploy_hash = deploy_hash;
	 }

	 public void setAccount(String account) {
	  this.account = account;
	 }

	 public void setTimestamp(String timestamp) {
	  this.timestamp = timestamp;
	 }

	 public void setTtl(String ttl) {
	  this.ttl = ttl;
	 }

	 public void setBlock_hash(String block_hash) {
	  this.block_hash = block_hash;
	 }

	 public void setExecution_result(Execution_result execution_resultObject) {
	  this.Execution_resultObject = execution_resultObject;
	 }
	}