package edu.neu.cs6510.controller;

import edu.neu.cs6510.config.ElasticsearchConfig;
import edu.neu.cs6510.enums.EMessageType;
import edu.neu.cs6510.enums.EStoryStatus;
import edu.neu.cs6510.enums.VoteType;
import edu.neu.cs6510.model.Story;
import edu.neu.cs6510.services.StoryService;
import edu.neu.cs6510.util.GsonUtil;
import edu.neu.cs6510.util.Page;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import edu.neu.cs6510.util.sqs.MessageWapper;
import edu.neu.cs6510.util.sqs.QueueUtil;
import edu.neu.cs6510.util.sqs.SQSUtil;
import io.searchbox.client.JestClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.xml.ws.Response;
//import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StoryController {

    @Autowired
    StoryService storyService;


    @ApiOperation(value = "find all stories")
    @GetMapping("/api/stories/story/all")
    public ResponseMessage<List<Story>> findAllStories() {
        return Result.success(storyService.getAll());
    }

    @ApiOperation(value = "find all stories by page [And order by filed in given order]")
    @GetMapping("/api/stories/story/all/page")
    public ResponseMessage<Page<Story>> findAllStoriesPage(@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                           @RequestParam(value = "orderBy", defaultValue = "timestamp") String orderBy,
                                                           @RequestParam(value = "order", defaultValue =  "DESC") String order) {
        return Result.success(storyService.getAllPage(pageSize, currentPage, orderBy, order));
    }
    
    @ApiOperation(value = "find all approved stories")
    @GetMapping("/api/stories/story/approved")
    public ResponseMessage<List<Story>> findAllApprovedStories() {
        return Result.success(storyService.getAllApproved());
    }

    @ApiOperation(value = "find all approved stories")
    @GetMapping("/api/stories/story/{status}/page")
    public ResponseMessage<Page<Story>> findStoriesByStatusPage(@PathVariable("status") EStoryStatus storyStatus, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                                   @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                                   @RequestParam(value = "orderBy", defaultValue = "timestamp") String orderBy,
                                                                   @RequestParam(value = "order", defaultValue =  "DESC") String order) {
        return Result.success(storyService.getByStatusPage(storyStatus, pageSize, currentPage ,orderBy, order));
    }

    @ApiOperation(value = "change the stataus story")
    @PutMapping("/api/stories/story/{status}/{id}")
    public ResponseMessage<List<Story>> approveStory(@PathVariable("status") EStoryStatus storyStatus, @PathVariable(value = "id") String id) {
        MessageWapper messageWapper = new MessageWapper(EMessageType.valueOf(storyStatus.toString()), id);
        //SQSUtil.sendMessage(GsonUtil.t2Json(messageWapper));
        QueueUtil.sendMessage(GsonUtil.t2Json(messageWapper));
        return Result.success();
    }

    @ApiOperation(value = "find all stories pending approval")
    @GetMapping("/api/stories/story/pending")
    public ResponseMessage<List<Story>> findAllPendingStories() {
        return Result.success(storyService.getAllPending());
    }

//    @ApiOperation(value = "find all stories pending approval")
//    @GetMapping("/api/stories/story/pending/page")
//    public ResponseMessage<Page<Story>> findAllPendingStoriesPage(@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
//                                                              @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
//                                                                  @RequestParam(value = "orderBy", defaultValue = "timestamp") String orderBy,
//                                                                  @RequestParam(value = "order", defaultValue =  "DESC") String order) {
//        return Result.success(storyService.getAllPendingPage(pageSize, currentPage,orderBy, order));
//    }



    @ApiOperation(value = "find story by id")
    @GetMapping("/api/stories/story/{id}")
    public ResponseMessage<List<Story>> findStoryById(@PathVariable(value = "id") String id) {
        MessageWapper messageWapper = new MessageWapper(EMessageType.FREQ_INC, id);
        //SQSUtil.sendMessage(GsonUtil.t2Json(messageWapper));
        QueueUtil.sendMessage(GsonUtil.t2Json(messageWapper));
        return Result.success(storyService.getById(id));
    }

    @ApiOperation(value = "find story by id")
    @DeleteMapping("/api/stories/story/{id}")
    public ResponseMessage<List<Story>> deleteStory(@PathVariable(value = "id") String id) {
        MessageWapper messageWapper = new MessageWapper(EMessageType.FREQ_INC, id);
        //SQSUtil.sendMessage(GsonUtil.t2Json(messageWapper));
        storyService.delete(id);
        return Result.success();
    }


    @ApiOperation(value = "add story")
    @PostMapping("/api/stories/story")
    public ResponseMessage<List<Story>> addStory(@RequestBody Story story) {
        return Result.success(storyService.createStory(story));
    }

    @ApiOperation(value = "update story")
    @PutMapping("/api/stories/story")
    public ResponseMessage<Story> updateStory(@RequestBody Story story) {
        return Result.success(storyService.updateStory(story));
    }

    @ApiOperation(value = "find all and sort")
    @GetMapping("/api/stories/story/all/{fieldName}")
    public ResponseMessage<List<Story>> orderStoryByField(@PathVariable(value = "fieldName") String fieldName, @RequestParam(value = "orderType", required = false) String orderType) {
        return Result.success(storyService.searchByFieldandOrder(fieldName, orderType));
    }
    @ApiOperation(value = "find all and sort")
    @GetMapping("/api/stories/story/all/page/{fieldName}")
    public ResponseMessage<Page<Story>> orderStoryByFieldPage(@PathVariable(value = "fieldName") String fieldName, @RequestParam(value = "orderType", required = false) String orderType,
                                                              @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                              @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {
        return Result.success(storyService.searchByFieldandOrderPage(fieldName, orderType, pageSize, currentPage));
    }

    @ApiOperation(value = "update story upvote or downvote")
    @PutMapping("/api/stories/story/{id}/{voteType}")
    public ResponseMessage updateStoryVotes(@PathVariable(value = "id") String id, @PathVariable(value = "voteType") VoteType voteType){
        return storyService.updateVoteToQueue(id, voteType);
    }

//    @ApiOperation(value = "search by key words")
//    @GetMapping("/api/stories/story/search/{keyword}")
//    public ResponseMessage<List<Story>> searchByKeyword(@PathVariable(value = "keyword") String keyword){
//        return Result.success(storyService.searchByKeyword(keyword));
//    }

    @ApiOperation(value = "search by key words and selected fildes")
    @GetMapping("/api/stories/story/search/{keyword}")
    public ResponseMessage<List<Story>> searchByKeyword(@PathVariable(value = "keyword") String keyword,
                                                        @RequestParam(value = "fields", required = false) List<String> fields){
        return Result.success(storyService.searchByKeyword(keyword, fields));
    }


    @ApiOperation(value = "search by key words")
    @GetMapping("/api/stories/story/search/page/{keyword}")
    public ResponseMessage<Page<Story>> searchByKeyword(@PathVariable(value = "keyword") String keyword,
                                                        @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                        @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                                        @RequestParam(value = "orderBy", required = false) String orderBy,
                                                        @RequestParam(value = "order", defaultValue =  "DESC") String order){
        return Result.success(storyService.searchByKeywordPage(keyword, pageSize, currentPage, orderBy, order));
    }
}
