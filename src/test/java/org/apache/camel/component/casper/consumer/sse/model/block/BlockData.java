package org.apache.camel.component.casper.consumer.sse.model.block;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockData {

	@JsonProperty("BlockAdded")
	BlockAdded BlockAddedObject;


	 // Getter Methods 

	 public BlockData(BlockAdded blockAddedObject) {
		super();
		BlockAddedObject = blockAddedObject;
	}
	 @JsonProperty("BlockAdded")
	public BlockAdded getBlockAdded() {
	  return BlockAddedObject;
	 }

	 // Setter Methods 

	 public void setBlockAdded(BlockAdded BlockAddedObject) {
	  this.BlockAddedObject = BlockAddedObject;
	 }
	}

	
	
	
	