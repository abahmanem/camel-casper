package org.apache.camel.component.casper.consumer.sse.model.deploy;

public class Payment {
	 ModuleBytes ModuleBytesObject;


	 // Getter Methods 

	 public ModuleBytes getModuleBytes() {
	  return ModuleBytesObject;
	 }

	 // Setter Methods 

	 public void setModuleBytes(ModuleBytes ModuleBytesObject) {
	  this.ModuleBytesObject = ModuleBytesObject;
	 }

	public ModuleBytes getModuleBytesObject() {
		return ModuleBytesObject;
	}

	public void setModuleBytesObject(ModuleBytes moduleBytesObject) {
		ModuleBytesObject = moduleBytesObject;
	}

	public Payment(ModuleBytes moduleBytesObject) {
		super();
		ModuleBytesObject = moduleBytesObject;
	}
	}