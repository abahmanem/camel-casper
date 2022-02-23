package org.apache.camel.component.casper.consumer.sse.model.deploy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeployAcceptedData {
	@JsonProperty("DeployAccepted")
	DeployAccepted DeployAcceptedObject;


	 // Getter Methods 
	@JsonProperty("DeployAccepted")
	 public DeployAccepted getDeployAccepted() {
	  return DeployAcceptedObject;
	 }

	 // Setter Methods 

	 public void setDeployAccepted(DeployAccepted DeployAcceptedObject) {
	  this.DeployAcceptedObject = DeployAcceptedObject;
	 }

	public DeployAcceptedData(DeployAccepted deployAcceptedObject) {
		super();
		DeployAcceptedObject = deployAcceptedObject;
	}
	
	
	
}
