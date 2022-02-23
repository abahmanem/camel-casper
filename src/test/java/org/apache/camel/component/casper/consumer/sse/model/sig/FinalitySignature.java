package org.apache.camel.component.casper.consumer.sse.model.sig;

public class FinalitySignature {
	 private String block_hash;
	 private float era_id;
	 private String signature;
	 private String public_key;


	 // Getter Methods 

	 public String getBlock_hash() {
	  return block_hash;
	 }

	 public float getEra_id() {
	  return era_id;
	 }

	 public String getSignature() {
	  return signature;
	 }

	 public String getPublic_key() {
	  return public_key;
	 }

	 // Setter Methods 

	 public void setBlock_hash(String block_hash) {
	  this.block_hash = block_hash;
	 }

	 public void setEra_id(float era_id) {
	  this.era_id = era_id;
	 }

	 public void setSignature(String signature) {
	  this.signature = signature;
	 }

	 public void setPublic_key(String public_key) {
	  this.public_key = public_key;
	 }

	public FinalitySignature(String block_hash, float era_id, String signature, String public_key) {
		super();
		this.block_hash = block_hash;
		this.era_id = era_id;
		this.signature = signature;
		this.public_key = public_key;
	}
}