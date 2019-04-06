package edu.neu.cs6510.services;

import com.amazonaws.services.sqs.model.Message;
import edu.neu.cs6510.config.SchedulerConfig;
import edu.neu.cs6510.enums.EMessageType;
import edu.neu.cs6510.enums.VoteType;
import edu.neu.cs6510.util.GsonUtil;
import edu.neu.cs6510.util.sqs.MessageWapper;
import edu.neu.cs6510.util.sqs.QueueUtil;
import edu.neu.cs6510.util.sqs.SQSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandleTask {

    Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    private StoryService storyService;

    /**
     * every 30 s
     */
    @Scheduled(cron = "*/15 * * * * ?")
    public void finishInspectionJob(){
        logger.info("<------------- Fetching messages from SQS ------------->");
//        List<Message> messages = SQSUtil.receiveMessages();
//        for (Message message : messages) {
//            messageHandler(GsonUtil.fromJson(message.getBody(), MessageWapper.class), storyService);
//            SQSUtil.deleteMessage(message);
//        }
        List<String> messages = QueueUtil.receiveMessages();
        HashMap<MessageWapper, Integer> cnt = new HashMap<>();
        for (String message : messages) {
            MessageWapper messageWapper = GsonUtil.fromJson(message, MessageWapper.class);
            cnt.put(messageWapper, cnt.getOrDefault(messageWapper, 0) + 1);
        }
        for (Map.Entry<MessageWapper, Integer> entry : cnt.entrySet()) {
            entry.getKey().setValue(entry.getValue());
            messageHandler(entry.getKey(), storyService);
        }
        logger.info("<------------- Processed "+ messages.size() +" messages, updated "+ cnt.size() +" stories------------->");
    }

    private static void messageHandler(MessageWapper wapper, StoryService storyService) {
       if (wapper.getMessageType() == EMessageType.APPROVED || wapper.getMessageType() == EMessageType.DISAPPROVED || wapper.getMessageType() == EMessageType.PENDING ) {
            storyService.changeStatus(wapper);
        } else {
           storyService.updateField(wapper.getId(), wapper);
       }
    }
}


