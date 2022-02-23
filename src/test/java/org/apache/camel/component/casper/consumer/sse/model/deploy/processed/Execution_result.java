package org.apache.camel.component.casper.consumer.sse.model.deploy.processed;

public class Execution_result {
	 Failure FailureObject;


	 // Getter Methods 

	 public Failure getFailure() {
	  return FailureObject;
	 }

	 // Setter Methods 

	 public void setFailure(Failure FailureObject) {
	  this.FailureObject = FailureObject;
	 }

	public Execution_result(Failure failureObject) {
		super();
		FailureObject = failureObject;
	}
	 
	 
	 
	}

