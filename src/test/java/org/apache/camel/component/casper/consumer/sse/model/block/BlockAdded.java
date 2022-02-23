package org.apache.camel.component.casper.consumer.sse.model.block;

public class BlockAdded {
	 private String block_hash;
	 Block BlockObject;


	 // Getter Methods 

	 public BlockAdded(String block_hash, Block blockObject) {
		super();
		this.block_hash = block_hash;
		BlockObject = blockObject;
	}

	public String getBlock_hash() {
	  return block_hash;
	 }

	 public Block getBlock() {
	  return BlockObject;
	 }

	 // Setter Methods 

	 public void setBlock_hash(String block_hash) {
	  this.block_hash = block_hash;
	 }

	 public void setBlock(Block blockObject) {
	  this.BlockObject = blockObject;
	 }
	}