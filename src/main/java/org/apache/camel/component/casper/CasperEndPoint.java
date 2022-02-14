package org.apache.camel.component.casper;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;

import org.apache.camel.CamelException;
import org.apache.camel.Category;
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

import com.jayway.jsonpath.InvalidPathException;
import com.syntifi.casper.sdk.service.CasperService;

/**
 * Interact with Casper nodes using Capser SDK.
 */

@UriEndpoint(firstVersion = "3.14.1", scheme = "casper", title = "Casper Camel Connector", syntax = "casper:nodeUrl", label = "casper", category = {
        Category.BITCOIN, Category.BLOCKCHAIN, Category.API})
public class CasperEndPoint extends DefaultEndpoint {

    public static Logger logger = LoggerFactory.getLogger(CasperEndPoint.class);

    /**
     * CasperService bean : Casper java SDK
     */
    private CasperService casperService;

    /**
     * nodeUrl : node address
     */
    @UriPath(description = "Node URL,  e.g. http://localhost:7777/ for producer, http://localhost:9999/events/main for consumer")
    @Metadata(required = true)
    private String nodeUrl;
    /**
     * Casper component configuration
     */
    @UriParam(description = "Casper component configuration")
    private CasperConfiguration configuration;

    /**
     * CasperEndPoint constructor
     *
     * @param uri             : Uri
     * @param remaining       :  remaining
     * @param casperComponent : casperComponent
     * @param configuration   : casperConfiguration
     * @throws Exception : Exception
     */
    public CasperEndPoint(String uri, String remaining, CasperComponent casperComponent, CasperConfiguration configuration) throws Exception {
        super(uri, casperComponent);
        this.configuration = configuration;
        this.nodeUrl = remaining;
        validateAndSetURL(remaining);
    }
    /**
     * Create a consumer component
     *
     * @param processor : Camel Processor
     * @return CasperConsumer
     * @throws Exception
     */
    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        URI uri = new URI(nodeUrl);
        String operation = configuration.getOperation();
        if (!Arrays.asList(CasperConstants.CONSUMER_PATHS.split(",")).stream().anyMatch(s -> s.equals(uri.getPath())))
            throw new InvalidPathException(String.format("Invalid path '%s' for Casper Stream event server: expected '/events/main', '/events/deploys' or '/events/sigs ",
                    uri.getPath()));
        if (ConsumerOperation.findByName(operation) != null) {
            CasperConsumer consumer = new CasperConsumer(this, processor, configuration);
            configureConsumer(consumer);
            return consumer;
        }
        throw new UnsupportedOperationException(String.format("Operation '%s' not supported by casper cosumner", operation));
    }

    /**
     * Create Producer
     *
     * @return CasperProducer
     * @throws Exception
     */
    @Override
    public Producer createProducer() throws Exception {
        String operation = configuration.getOperationOrDefault();
        if (ProducerOperation.findByName(operation) != null)
            return new CasperProducer(this, configuration);
        //Insupported operation
        throw new UnsupportedOperationException(String.format("Operation '%s' not supported by casper producer", operation));
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
            this.casperService = CasperService.usingPeer(uri.getHost(), uri.getPort());
        }
        super.doStart();
    }

    @Override
    protected void doShutdown() throws Exception {
        super.doShutdown();
    }

    /**
     * validate node adress
     *
     * @param url : url
     * @throws Exception : excpetion thrown
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
