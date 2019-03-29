package edu.neu.cs6510.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchConfig {

    private String serverUrl;

    @Bean
    public JestClient Elasticsearchclient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(serverUrl)
                .readTimeout(10000)
                .multiThreaded(true).build());
        JestClient client = factory.getObject();
        return client;

    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
