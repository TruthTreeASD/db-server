package edu.neu.cs6510.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public JestClient Elasticsearchclient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("https://search-gov-fin-es-3y3ydkxijtitmqim7xxyhtczeq.us-west-1.es.amazonaws.com")
                .multiThreaded(true).build());
        JestClient client = factory.getObject();
        return client;

    }

}
