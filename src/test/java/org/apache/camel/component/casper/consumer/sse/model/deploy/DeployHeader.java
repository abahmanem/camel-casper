package org.apache.camel.component.casper.consumer.sse.model.deploy;

import java.util.ArrayList;

public class DeployHeader {
 private String account;
 private String timestamp;
 private String ttl;
 private float gas_price;
 private String body_hash;
 ArrayList < String > dependencies = new ArrayList < String > ();
 private String chain_name;


 // Getter Methods 

 public String getAccount() {
  return account;
 }

 public String getTimestamp() {
  return timestamp;
 }

 public String getTtl() {
  return ttl;
 }

 public float getGas_price() {
  return gas_price;
 }

 public String getBody_hash() {
  return body_hash;
 }

 public String getChain_name() {
  return chain_name;
 }

 // Setter Methods 

 public void setAccount(String account) {
  this.account = account;
 }

 public void setTimestamp(String timestamp) {
  this.timestamp = timestamp;
 }

 public void setTtl(String ttl) {
  this.ttl = ttl;
 }

 public void setGas_price(float gas_price) {
  this.gas_price = gas_price;
 }

 public void setBody_hash(String body_hash) {
  this.body_hash = body_hash;
 }

 public void setChain_name(String chain_name) {
  this.chain_name = chain_name;
 }

public ArrayList<String> getDependencies() {
	return dependencies;
}

public void setDependencies(ArrayList<String> dependencies) {
	this.dependencies = dependencies;
}

public DeployHeader(String account, String timestamp, String ttl, float gas_price, String body_hash,
		ArrayList<String> dependencies, String chain_name) {
	super();
	this.account = account;
	this.timestamp = timestamp;
	this.ttl = ttl;
	this.gas_price = gas_price;
	this.body_hash = body_hash;
	this.dependencies = dependencies;
	this.chain_name = chain_name;
}
}