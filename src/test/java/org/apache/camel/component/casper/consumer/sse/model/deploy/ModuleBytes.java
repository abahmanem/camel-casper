package org.apache.camel.component.casper.consumer.sse.model.deploy;

import java.util.ArrayList;

public class ModuleBytes {
		 private String module_bytes;
		 ArrayList < String > args = new ArrayList < String > ();


		 public ModuleBytes(String module_bytes, ArrayList<String> args) {
			super();
			this.module_bytes = module_bytes;
			this.args = args;
		}

		// Getter Methods 

		 public ArrayList<String> getArgs() {
			return args;
		}

		public void setArgs(ArrayList<String> args) {
			this.args = args;
		}

		public String getModule_bytes() {
		  return module_bytes;
		 }

		 // Setter Methods 

		 public void setModule_bytes(String module_bytes) {
		  this.module_bytes = module_bytes;
		 }
		}
