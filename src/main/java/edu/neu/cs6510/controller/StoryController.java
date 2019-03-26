package edu.neu.cs6510.controller;

import edu.neu.cs6510.config.ElasticsearchConfig;
import edu.neu.cs6510.model.Story;
import edu.neu.cs6510.services.StoryService;
import io.searchbox.client.JestClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StoryController {

    @Autowired
    StoryService storyService;


    @ApiOperation(value = "find all stories")
    @GetMapping("/stories/story/all")
    public List<Story> findAllStories() {
        return storyService.getAll();
    }


    @ApiOperation(value = "find story by id")
    @GetMapping("/stories/story/{id}")
    public List<Story> findStoryById(@PathVariable(value = "id") String id) {

        return storyService.getById(id);
    }


    @ApiOperation(value = "add story")
    @PostMapping("/stories/story/add")
    public Story addStory(@RequestBody Story story) {
        return storyService.createStory(story);
    }

    @ApiOperation(value = "update story")
    @PutMapping("/stories/story/update")
    public Story updateStory(@RequestBody Story story) {
        ElasticsearchConfig client = new ElasticsearchConfig();
        return storyService.updateStory(story);
    }

    @ApiOperation(value = "find story by id")
    @GetMapping("/stories/story/all/sortBy={fieldName}")
    public List<Story> orderStoryByField(@PathVariable(value = "fieldName") String fieldName, @RequestParam(value = "orderType", required = false) String orderType) {
        return storyService.searchByFieldandOrder(fieldName, orderType);
    }
}
