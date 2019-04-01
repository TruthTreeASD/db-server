package edu.neu.cs6510.controller;

import edu.neu.cs6510.config.ElasticsearchConfig;
import edu.neu.cs6510.enums.VoteType;
import edu.neu.cs6510.model.Story;
import edu.neu.cs6510.services.StoryService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.searchbox.client.JestClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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
    
    @ApiOperation(value = "find all approved stories")
    @GetMapping("/api/stories/story/approved")
    public ResponseMessage<List<Story>> findAllApprovedStories() {
        return Result.success(storyService.getAllApproved());
    }

    @ApiOperation(value = "find all stories pending approval")
    @GetMapping("/api/stories/story/pending")
    public ResponseMessage<List<Story>> findAllPendingStories() {
        return Result.success(storyService.getAllPending());
    }


    @ApiOperation(value = "find story by id")
    @GetMapping("/api/stories/story/{id}")
    public ResponseMessage<List<Story>> findStoryById(@PathVariable(value = "id") String id) {
        return Result.success(storyService.getById(id));
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

    @ApiOperation(value = "update story upvote or downvote")
    @PutMapping("/api/stories/story/{id}/{voteType}")
    public ResponseMessage<List<Story>> updateStoryVotes(@PathVariable(value = "id") String id, @PathVariable(value = "voteType") VoteType voteType){
        return Result.success(storyService.updateVote(id, voteType));
    }

    @ApiOperation(value = "search by key words")
    @GetMapping("/api/stories/story/search/{keyword}")
    public ResponseMessage<List<Story>> searchByKeyword(@PathVariable(value = "keyword") String keyword){
        return Result.success(storyService.searchByKeyword(keyword));
    }

}
