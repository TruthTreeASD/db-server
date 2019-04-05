package edu.neu.cs6510.util.sqs;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueUtil {

    private static LinkedBlockingQueue<String> messageQueue;

    private static int BATCH_NUM = 50;

    static {
        messageQueue = new LinkedBlockingQueue<>();
    }

    private QueueUtil() {
    }

    public static void sendMessage(String message) {
        //String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        messageQueue.add(message);
    }


    public static List<String> receiveMessages() {
        int count = 0;
        List<String> res = new ArrayList<>(BATCH_NUM);
        while (count ++ < BATCH_NUM && messageQueue.size() != 0) {
            res.add(messageQueue.remove());
        }
        return res;
    }
}
