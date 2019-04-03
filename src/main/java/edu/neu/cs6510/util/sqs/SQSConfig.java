package edu.neu.cs6510.util.sqs;

import org.springframework.beans.factory.annotation.Value;

public class SQSConfig {

    public static String SQSUrl = "https://sqs.us-west-2.amazonaws.com/105226510240/gov_fin_mq.fifo";

    @Value(value = "${sqs.url}")
    public void setSQSUrl(String SQSUrl) {
        SQSConfig.SQSUrl = SQSUrl;
    }
}
