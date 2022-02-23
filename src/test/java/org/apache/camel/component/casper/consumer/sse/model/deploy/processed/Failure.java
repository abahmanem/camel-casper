package org.apache.camel.component.casper.consumer.sse.model.deploy.processed;

import java.util.ArrayList;

public class Failure {
	 Effect EffectObject;
	 
		public Failure(Effect effectObject, String cost, String error_message, ArrayList<String> transfers) {
		super();
		EffectObject = effectObject;
		this.cost = cost;
		this.error_message = error_message;
		this.transfers = transfers;
	}

		private String cost;
		 private String error_message;


	 ArrayList < String > transfers = new ArrayList < String > ();
	 public Effect getEffectObject() {
		return EffectObject;
	}

	public void setEffectObject(Effect effectObject) {
		EffectObject = effectObject;
	}

	public ArrayList<String> getTransfers() {
		return transfers;
	}

	public void setTransfers(ArrayList<String> transfers) {
		this.transfers = transfers;
	}


	 // Getter Methods 

	 public Effect getEffect() {
	  return EffectObject;
	 }

	 public String getCost() {
	  return cost;
	 }

	 public String getError_message() {
	  return error_message;
	 }

	 // Setter Methods 

	 public void setEffect(Effect effectObject) {
	  this.EffectObject = effectObject;
	 }

	 public void setCost(String cost) {
	  this.cost = cost;
	 }

	 public void setError_message(String error_message) {
	  this.error_message = error_message;
	 }
	}