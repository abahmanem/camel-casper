package org.apache.camel.component.casper;

import java.net.URI;
import java.time.Duration;

import org.apache.camel.Processor;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.Suspendable;
import org.apache.camel.spi.ShutdownAware;
import org.apache.camel.support.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

/**
 * Camel  CasperComsumer Component
 * @author p35862
 *
 */

public class CasperConsumer extends DefaultConsumer implements ShutdownAware, Suspendable {

    public static Logger logger = LoggerFactory.getLogger(CasperEndPoint.class);
    public CasperConfiguration getConfiguration() {
		return configuration;
	}

	private final CasperEndPoint endpoint;
    private final CasperConfiguration configuration;
    /**
     * Create consumer
     *
     * @param endpoint      : endpoint
     * @param processor     : consumer Processor
     * @param configuration : CasperConfiguration
     * @throws Exception
     */
    public CasperConsumer(CasperEndPoint endpoint, Processor processor, CasperConfiguration configuration)
            throws Exception {
        super(endpoint, processor);
        this.configuration = configuration;
        URI uri = new URI(endpoint.getNodeUrl());
        this.endpoint = endpoint;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        EventHandler eventHandler = new CasperEventHandler(this);
        EventSource builder = new EventSource.Builder(eventHandler, new URI(endpoint.getNodeUrl()))
                .reconnectTime(Duration.ofMillis(3000)).build();
        builder.start();
    }

    @Override
    public boolean deferShutdown(ShutdownRunningTask shutdownRunningTask) {
        // deny stopping on shutdown as we want direct consumers to run in case some
        // other queues
        // depend on this consumer to run, so it can complete its exchanges
        return true;
    }

    @Override
    public int getPendingExchangesSize() {
        // return 0 as we do not have an internal memory queue with a variable size
        // of inflight messages.
        return 0;
    }

    @Override
    public void prepareShutdown(boolean suspendOnly, boolean forced) {
        // noop
    }
}
