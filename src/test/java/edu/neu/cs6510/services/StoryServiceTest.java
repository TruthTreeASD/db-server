package edu.neu.cs6510.services;

import edu.neu.cs6510.dto.NewStoryDTO;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.model.Story;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StoryServiceTest {

    @InjectMocks
    StoryService storyService;

    List<Story> storyDTO = new ArrayList<>();
    NewStoryDTO story = new NewStoryDTO();

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        story.setFreq(1);
        story.setUpvote(1);
        story.setDownvote(1);
        story.setAuthor("Author");
        story.setTitle("Title");
        story.setContent("Content");
        story.setTags(tags);
        story.setRawContent("Raw Content");
        storyDTO.add(story);
    }

    @Test
    public void endToEndTest(){
        Mockito.when(storyService.createStory(story)).thenReturn(storyDTO);
        Story currStory = storyDTO.get(0);
        assertEquals(currStory.getAuthor(), story.getAuthor());
        assertEquals(currStory.getContent(), story.getContent());
        assertEquals(currStory.getDownvote(), story.getDownvote());
        assertEquals(currStory.getFreq(), story.getFreq());
        assertEquals(currStory.getTitle(), story.getTitle());
        assertEquals(currStory.getAuthor(), story.getAuthor());

        storyService.delete(currStory.getId());
        Story unavailable = storyService.getById(currStory.getId()).get(0);
        System.out.println(unavailable);

    }
}
