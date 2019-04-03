package edu.neu.cs6510.util.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.services.sqs.model.Message;
import com.google.gson.Gson;
import edu.neu.cs6510.enums.EMessageType;
import edu.neu.cs6510.enums.VoteType;
import edu.neu.cs6510.services.StoryService;
import edu.neu.cs6510.util.GsonUtil;

import javax.jms.*;
import java.util.*;

public class SQSUtil {
    private static AmazonSQS sqs;

    static {
        AWSCredentials credentials = new AnonymousAWSCredentials();
        sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_2).build();
    }

    private SQSUtil() {
    }


    public static void sendMessage(String message) {
        //String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        SendMessageRequest request = new SendMessageRequest();
        request.withQueueUrl(SQSConfig.SQSUrl);
        request.withMessageBody(message);
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("vote-opt", new MessageAttributeValue().withDataType("String").withStringValue("COCO"));
        request.withMessageAttributes(messageAttributes);
        request.setMessageDeduplicationId(UUID.randomUUID().toString());
        request.setMessageGroupId("vote-opt");
        sqs.sendMessage(request);
    }


    public static List<Message> receiveMessages() {
        //System.out.println("Receiving messages from " + queueUrl);
        //String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQSConfig.SQSUrl);
        receiveMessageRequest.setMaxNumberOfMessages(10);
        receiveMessageRequest.withWaitTimeSeconds(10);

        receiveMessageRequest.setMessageAttributeNames(Arrays.asList("vote-opt"));

        return sqs.receiveMessage(receiveMessageRequest).getMessages();
    }





    public static void main(String[] args) {
        MessageWapper messageWapper = new MessageWapper(EMessageType.DOWNVOTE, "abc");
        SQSUtil.sendMessage(GsonUtil.t2Json(messageWapper));
        SQSUtil.receiveMessages();
    }

}