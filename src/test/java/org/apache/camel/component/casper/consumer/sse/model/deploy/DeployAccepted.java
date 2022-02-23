package org.apache.camel.component.casper.consumer.sse.model.deploy;


import java.util.ArrayList;

public class DeployAccepted {
	 private String hash;
	 DeployHeader HeaderObject;
	 Payment PaymentObject;
	 Session SessionObject;
	 ArrayList < String > approvals = new ArrayList < String > ();


	 public DeployAccepted(String hash, DeployHeader headerObject, Payment paymentObject, Session sessionObject,
			ArrayList<String> approvals) {
		super();
		this.hash = hash;
		HeaderObject = headerObject;
		PaymentObject = paymentObject;
		SessionObject = sessionObject;
		this.approvals = approvals;
	}

	// Getter Methods 

	 public DeployHeader getHeaderObject() {
		return HeaderObject;
	}

	public void setHeaderObject(DeployHeader headerObject) {
		HeaderObject = headerObject;
	}

	public Payment getPaymentObject() {
		return PaymentObject;
	}

	public void setPaymentObject(Payment paymentObject) {
		PaymentObject = paymentObject;
	}

	public Session getSessionObject() {
		return SessionObject;
	}

	public void setSessionObject(Session sessionObject) {
		SessionObject = sessionObject;
	}

	public ArrayList<String> getApprovals() {
		return approvals;
	}

	public void setApprovals(ArrayList<String> approvals) {
		this.approvals = approvals;
	}

	public String getHash() {
	  return hash;
	 }

	 public DeployHeader getHeader() {
	  return HeaderObject;
	 }

	 public Payment getPayment() {
	  return PaymentObject;
	 }

	 public Session getSession() {
	  return SessionObject;
	 }

	 // Setter Methods 

	 public void setHash(String hash) {
	  this.hash = hash;
	 }

	 public void setHeader(DeployHeader headerObject) {
	  this.HeaderObject = headerObject;
	 }

	 public void setPayment(Payment paymentObject) {
	  this.PaymentObject = paymentObject;
	 }

	 public void setSession(Session sessionObject) {
	  this.SessionObject = sessionObject;
	 }
	}
	

	