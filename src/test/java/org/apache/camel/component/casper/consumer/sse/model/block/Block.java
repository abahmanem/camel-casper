package org.apache.camel.component.casper.consumer.sse.model.block;

import java.util.ArrayList;

public class Block {
	 private String hash;
	 BlockHeader HeaderObject;
	 Body BodyObject;
	 ArrayList < String > proofs = new ArrayList < String > ();


	 // Getter Methods 

	 public Block(String hash, BlockHeader headerObject, Body bodyObject, ArrayList<String> proofs) {
		super();
		this.hash = hash;
		HeaderObject = headerObject;
		BodyObject = bodyObject;
		this.proofs = proofs;
	}

	public ArrayList<String> getProofs() {
		return proofs;
	}

	public void setProofs(ArrayList<String> proofs) {
		this.proofs = proofs;
	}

	public String getHash() {
	  return hash;
	 }

	 public BlockHeader getHeader() {
	  return HeaderObject;
	 }

	 public Body getBody() {
	  return BodyObject;
	 }

	 // Setter Methods 

	 public void setHash(String hash) {
	  this.hash = hash;
	 }

	 public void setHeader(BlockHeader headerObject) {
	  this.HeaderObject = headerObject;
	 }

	 public void setBody(Body bodyObject) {
	  this.BodyObject = bodyObject;
	 }
	}