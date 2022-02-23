package org.apache.camel.component.casper.consumer.sse.model.sig;

public class FinalitySignatureData {
	FinalitySignature FinalitySignatureObject;


	 // Getter Methods 

	 public FinalitySignature getFinalitySignature() {
	  return FinalitySignatureObject;
	 }

	 // Setter Methods 

	 public void setFinalitySignature(FinalitySignature FinalitySignatureObject) {
	  this.FinalitySignatureObject = FinalitySignatureObject;
	 }

	public FinalitySignatureData(FinalitySignature finalitySignatureObject) {
		super();
		FinalitySignatureObject = finalitySignatureObject;
	}

	 
	 
	 
}
