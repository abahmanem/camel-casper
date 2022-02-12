package org.apache.camel.component.casper;

import java.net.URI;
import java.net.URL;

import org.apache.camel.CamelException;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Category;
import com.syntifi.casper.sdk.service.CasperService;

/**
 * Interact with Casper nodes using  Capser SDK.
 */

@UriEndpoint(firstVersion = "3.14.0", scheme = "casper", title = "Casper Camel Connector", syntax = "casper:nodeUrl", label = "casper",
category = { Category.BITCOIN, Category.BLOCKCHAIN, Category.API })
public class CasperEndPoint extends DefaultEndpoint {

	public static Logger logger = LoggerFactory.getLogger(CasperEndPoint.class);

	
	/**
	 * CasperService bean : Casper java SDK
	 */
	
	private CasperService casperService;


	/**
	 * nodeUrl : node address
	 */
	@UriPath
	@Metadata(required = true)
	private String nodeUrl;

	@UriParam
	private CasperConfiguration configuration;

	/**
	 * 
	 * @param uri
	 * @param remaining
	 * @param casperComponent
	 * @param casperConfiguration
	 * @throws Exception
	 */

	public CasperEndPoint(String uri, String remaining, CasperComponent casperComponent,
			CasperConfiguration configuration) throws Exception {
		super(uri, casperComponent);
		this.configuration = configuration;
		this.nodeUrl=remaining;
		//validateAndSetURL(remaining);
	}

	/**
	* 
	*/
	@Override
	public Consumer createConsumer(Processor processor) throws Exception {

		CasperConsumer consumer = new CasperConsumer(this, processor,configuration);
		configureConsumer(consumer);
		return consumer;
	}

	@Override
	public Producer createProducer() throws Exception {
		return new CasperProducer(this, configuration);
	}

	@Override
	protected void doStop() throws Exception {
		super.doStop();
	}

	@Override
	protected void doStart() throws Exception {
		if (configuration.getCasperService() != null) {
			this.casperService = configuration.getCasperService();
		} else {
			URI uri = new URI(nodeUrl);
			this.casperService = CasperService.usingPeer(uri.getHost(),uri.getPort());
		}
		super.doStart();
	}

	@Override
	protected void doShutdown() throws Exception {
		super.doShutdown();
	}


	/**
	 * validate node adress
	 * @param url
	 * @throws Exception
	 */
	public void validateAndSetURL(String url) throws Exception {
		UrlValidator validator = new UrlValidator();

		if (!validator.isValid(url)) {
			throw new CamelException("Please provide a valid \"URL\" parameter. Get : " + url);
		}
		setNodeUrl(new URL(url).toString());
	}

	
	
	public CasperService getCasperService() {
		return casperService;
	}

	public void setCasperService(CasperService casperService) {
		this.casperService = casperService;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public CasperConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(CasperConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	
	
	
}
