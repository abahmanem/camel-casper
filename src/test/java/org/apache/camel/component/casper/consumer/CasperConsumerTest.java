package org.apache.camel.component.casper.consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.apache.camel.Exchange;
import org.apache.camel.component.casper.CasperConstants;
import org.apache.camel.component.casper.CasperTestSupport;
import org.apache.camel.component.casper.consumer.sse.SpringAsyncTestApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.syntifi.casper.sdk.model.balance.BalanceData;

public class CasperConsumerTest extends CasperTestSupport {
	
	@BeforeAll
	public static void startServer() throws Exception {
		SpringAsyncTestApplication.main(new String[0]);
	}

	

	
	@AfterAll
	public static void stopServer() throws Exception {
	}
	

}
