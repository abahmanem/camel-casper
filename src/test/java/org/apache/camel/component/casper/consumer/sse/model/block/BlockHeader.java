package org.apache.camel.component.casper.consumer.sse.model.block;

public class BlockHeader {
	 private String parent_hash;
	 private String state_root_hash;
	 private String body_hash;
	 private boolean random_bit;
	 private String accumulated_seed;
	 private String era_end = null;
	 private String timestamp;
	 private float era_id;
	 private float height;
	 private String protocol_version;


	 // Getter Methods 

	 public String getParent_hash() {
	  return parent_hash;
	 }

	 public String getState_root_hash() {
	  return state_root_hash;
	 }

	 public String getBody_hash() {
	  return body_hash;
	 }

	 public boolean getRandom_bit() {
	  return random_bit;
	 }

	 public String getAccumulated_seed() {
	  return accumulated_seed;
	 }

	 public String getEra_end() {
	  return era_end;
	 }

	 public String getTimestamp() {
	  return timestamp;
	 }

	 public float getEra_id() {
	  return era_id;
	 }

	 public float getHeight() {
	  return height;
	 }

	 public String getProtocol_version() {
	  return protocol_version;
	 }

	 // Setter Methods 

	 public void setParent_hash(String parent_hash) {
	  this.parent_hash = parent_hash;
	 }

	 public void setState_root_hash(String state_root_hash) {
	  this.state_root_hash = state_root_hash;
	 }

	 public void setBody_hash(String body_hash) {
	  this.body_hash = body_hash;
	 }

	 public void setRandom_bit(boolean random_bit) {
	  this.random_bit = random_bit;
	 }

	 public void setAccumulated_seed(String accumulated_seed) {
	  this.accumulated_seed = accumulated_seed;
	 }

	 public void setEra_end(String era_end) {
	  this.era_end = era_end;
	 }

	 public void setTimestamp(String timestamp) {
	  this.timestamp = timestamp;
	 }

	 public void setEra_id(float era_id) {
	  this.era_id = era_id;
	 }

	 public void setHeight(float height) {
	  this.height = height;
	 }

	 public void setProtocol_version(String protocol_version) {
	  this.protocol_version = protocol_version;
	 }

	public BlockHeader(String parent_hash, String state_root_hash, String body_hash, boolean random_bit,
			String accumulated_seed, String era_end, String timestamp, float era_id, float height,
			String protocol_version) {
		super();
		this.parent_hash = parent_hash;
		this.state_root_hash = state_root_hash;
		this.body_hash = body_hash;
		this.random_bit = random_bit;
		this.accumulated_seed = accumulated_seed;
		this.era_end = era_end;
		this.timestamp = timestamp;
		this.era_id = era_id;
		this.height = height;
		this.protocol_version = protocol_version;
	}
	 }