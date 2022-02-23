package org.apache.camel.component.casper.consumer.sse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.camel.component.casper.consumer.sse.model.block.*;
import org.apache.camel.component.casper.consumer.sse.model.deploy.*;
import org.springframework.stereotype.Service;



@Service
public class DataSetService {
	private final List<BlockData> blokcs = new ArrayList<>();

	private final List<DeployAcceptedData> deploys = new ArrayList<>();
	
	
	
	
	
	@PostConstruct
	public void setup() {
		createBlockDataSet();
		createDeployDataSet();
	}

	public List<BlockData> getBlocks() {
		return Collections.unmodifiableList(blokcs);
	}

	public List<DeployAcceptedData> getDeploys() {
		return Collections.unmodifiableList(deploys);
	}
	
	private Iterable<BlockData> createBlockDataSet() 
	{
	////Block
				Body body = new Body("01717c1899762ffdbd12def897ac905f1debff38e8bafb081620cb6da5a6bb1f25", new ArrayList<>(), new ArrayList<>());
				BlockHeader header = new BlockHeader("b9b7465aa84343597def17fe64afe6b5851d20736bbbbd1d998f4fb76156de7a","39d5cc72a0129781ac261dcf306c26a600b51ace307b98cd5e8ce3feb91af51c", "7a8d15d2fb0679b5549064431eb1116043b186ea562af6d76ec207f029075278", true, "8acc39d236c63fb415823884212d051bf674f3b4ce809d408314e07ddc4339e5",null, "2022-02-22T15:12:15.872Z",3827,558756, "1.4.4");
				Block block = new Block("a636c524063588756cff0f306a13ce2804fa50fe2430e7910196fa93a55ed5f9", header, body,  new ArrayList<>());
				BlockAdded added = new BlockAdded("a636c524063588756cff0f306a13ce2804fa50fe2430e7910196fa93a55ed5f9", block);
				BlockData blockData = new BlockData(added);
				
				
				
		
		for (int i = 0; i < 25; i++) {
			this.blokcs.add(blockData);
		}
		return blokcs;
	}
	
	private Iterable<DeployAcceptedData> createDeployDataSet() 
	{
		DeployHeader deployHeader = new DeployHeader("01b92e36567350dd7b339d709bfe341df6fda853e85315418f1bb3ddd414d9f5bee95d5bf8f3a397a3d9", "2022-02-22T13:35:55.863Z","1day", 1, "acf8fd7edd5389f43b9250f743691d932ffd2d43bba8477ac4c877c8036dab55", new ArrayList<>() , "casper");
		ModuleBytes mdb = new ModuleBytes("", new ArrayList<>());
		Payment p = new Payment(mdb);
		Transfer tr = new Transfer(new ArrayList<>());
		Session ss = new Session(tr);
		DeployAccepted da = new DeployAccepted("6baaf4b76adbcbba47ce2ad013dc9f29c2ebdc1a9c25abe95d5bf8f3a397a3d9", deployHeader, p, ss, new ArrayList<>());
		DeployAcceptedData deploydata = new DeployAcceptedData(da);
		
		for (int i = 0; i < 25; i++) {
			
		
			this.deploys.add(deploydata);
		}
		return deploys;
	}
}
