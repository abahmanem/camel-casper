package org.apache.camel.component.casper.consumer.sse.model.deploy.processed;

public class DeployProcessedData {

	DeployProcessed DeployProcessedObject;


	 // Getter Methods 

	 public DeployProcessed getDeployProcessed() {
	  return DeployProcessedObject;
	 }

	 // Setter Methods 

	 public void setDeployProcessed(DeployProcessed DeployProcessedObject) {
	  this.DeployProcessedObject = DeployProcessedObject;
	 }

	public DeployProcessedData(DeployProcessed deployProcessedObject) {
		super();
		DeployProcessedObject = deployProcessedObject;
	}


	 

}
	
	
	

