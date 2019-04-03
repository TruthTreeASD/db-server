package edu.neu.cs6510.services;

import com.amazonaws.services.sqs.model.Message;
import edu.neu.cs6510.enums.EMessageType;
import edu.neu.cs6510.enums.VoteType;
import edu.neu.cs6510.util.GsonUtil;
import edu.neu.cs6510.util.sqs.MessageWapper;
import edu.neu.cs6510.util.sqs.SQSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageHandleTask {

    @Autowired
    private StoryService storyService;

    /**
     * every 1 min
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void finishInspectionJob(){
        List<Message> messages = SQSUtil.receiveMessages();
        for (Message message : messages) {
            messageHandler(GsonUtil.fromJson(message.getBody(), MessageWapper.class), storyService);
        }
    }

    private static void messageHandler(MessageWapper wapper, StoryService storyService) {
        if (wapper.getMessageType() == EMessageType.DOWNVOTE || wapper.getMessageType() == EMessageType.UPVOTE ) {
            System.out.println(storyService.updateVote(wapper.getId(), VoteType.valueOf(wapper.getMessageType().toString())));
        } else if (wapper.getMessageType() == EMessageType.APPROVED) {
            storyService.setApproved(wapper.getId());
        }
    }
}


