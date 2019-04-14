package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs6510.enums.EStoryStatus;
import edu.neu.cs6510.model.Story;

import static org.junit.Assert.*;

public class NewStoryDTOTest {
  NewStoryDTO story;

  @Before
  public void setUp() throws Exception {
    story = new NewStoryDTO();

    List<String> tags = new ArrayList<>();
    tags.add("tag1");

    story.setId("1");
    story.setFreq(1);
    story.setUpvote(1);
    story.setDownvote(1);
    story.setAuthor("Author");
    story.setTitle("Title");
    story.setContent("Content");
    story.setTags(tags);
    story.setTimestamp(new Long(1));
    story.setIsApproved(EStoryStatus.APPROVED);
    story.setRawContent("Raw Content");
  }

  @Test
  public void getId() {
    Assert.assertEquals("1", story.getId());
  }

  @Test
  public void setId() {
    story.setId("2");

    Assert.assertEquals("2", story.getId());
  }

  @Test
  public void getFreq() {
    Assert.assertEquals(1, story.getFreq());
  }

  @Test
  public void setFreq() {
    story.setFreq(2);
    Assert.assertEquals(2, story.getFreq());
  }

  @Test
  public void getUpvote() {
    Assert.assertEquals(1, story.getUpvote());
  }

  @Test
  public void setUpvote() {
    story.setUpvote(2);

    Assert.assertEquals(2, story.getUpvote());
  }

  @Test
  public void getDownvote() {
    Assert.assertEquals(1, story.getDownvote());
  }

  @Test
  public void setDownvote() {
    story.setDownvote(2);

    Assert.assertEquals(2, story.getDownvote());
  }

  @Test
  public void getAuthor() {
    Assert.assertEquals("Author", story.getAuthor());
  }

  @Test
  public void setAuthor() {
    story.setAuthor("New Author");

    Assert.assertEquals("New Author", story.getAuthor());
  }

  @Test
  public void getTitle() {
    Assert.assertEquals("Title", story.getTitle());
  }

  @Test
  public void setTitle() {
    story.setTitle("New Title");

    Assert.assertEquals("New Title", story.getTitle());
  }

  @Test
  public void getContent() {
    Assert.assertEquals("Content", story.getContent());
  }

  @Test
  public void setContent() {
    story.setContent("New Content");

    Assert.assertEquals("New Content", story.getContent());
  }

  @Test
  public void getTags() {
    List<String> testTags = new ArrayList<>();
    testTags.add("tag1");

    Assert.assertEquals(testTags, story.getTags());
  }

  @Test
  public void setTags() {
    List<String> updatedTestTags = new ArrayList<>();
    updatedTestTags.add("tag1");
    updatedTestTags.add("tag2");

    story.setTags(updatedTestTags);

    Assert.assertEquals(updatedTestTags, story.getTags());
  }

  @Test
  public void getTimestamp() {
    Assert.assertEquals(new Long(1), story.getTimestamp());
  }

  @Test
  public void setTimestamp() {
    story.setTimestamp(new Long(2));

    Assert.assertEquals(new Long(2), story.getTimestamp());
  }

  @Test
  public void getIsApproved() {
    Assert.assertEquals(EStoryStatus.APPROVED, story.getIsApproved());
  }

  @Test
  public void setIsApproved() {
    story.setIsApproved(EStoryStatus.DISAPPROVED);

    Assert.assertEquals(EStoryStatus.DISAPPROVED, story.getIsApproved());
  }

  @Test
  public void getRawContent() {
    Assert.assertEquals("Raw Content", story.getRawContent());
  }

  @Test
  public void setRawContent() {
    story.setRawContent("New Raw Content");

    Assert.assertEquals("New Raw Content", story.getRawContent());
  }
}