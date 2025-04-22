package com.aksprojects.tracing;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.exporter.SpanExporter;
import io.micrometer.tracing.exporter.CompositeSpanExporter;
import io.micrometer.tracing.exporter.SpanExportingPredicate;
import io.micrometer.tracing.exporter.SpanFilter;
import io.micrometer.tracing.brave.bridge.BraveTracer;
import io.micrometer.tracing.otel.bridge.OtelTracer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.tracing.zipkin.ZipkinAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;
import zipkin2.reporter.urlconnection.URLConnectionSender;

import brave.Tracing;
import brave.handler.SpanHandler;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.sampler.Sampler;

@Configuration
public class ZipkinTracingConfig {

    @Value("${management.tracing.zipkin.endpoint}")
    private String zipkinEndpoint;

    @Bean
    public Sender zipkinSender() {
        return OkHttpSender.create(zipkinEndpoint);
    }

    @Bean
    public AsyncReporter<zipkin2.Span> spanReporter(Sender sender) {
        return AsyncReporter.create(sender);
    }

    @Bean
    public Tracing braveTracing(AsyncReporter<zipkin2.Span> reporter) {
        return Tracing.newBuilder()
                .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder().build())
                .sampler(Sampler.ALWAYS_SAMPLE)
                .spanReporter(reporter)
                .build();
    }

    @Bean
    public Tracer micrometerTracer(Tracing tracing) {
        return new BraveTracer(tracing.tracer(), tracing.currentTraceContext());
    }
}
