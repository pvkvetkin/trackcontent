package io.github.pvkvetkin.scrapper.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;

@Component
public class ScrapperProcessorMetric {

    private static final Counter TRACKED_LINKS_COUNTER = Metrics.counter("tracked_links_count");

    public void incrementTrackedLinks() {
        TRACKED_LINKS_COUNTER.increment();
    }
}
