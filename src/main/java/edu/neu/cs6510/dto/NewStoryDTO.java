package edu.neu.cs6510.dto;

import edu.neu.cs6510.enums.EStoryStatus;
import io.searchbox.annotations.JestId;

import java.util.List;

public class NewStoryDTO {
    @JestId
    private String id;
    private String author;
    private String title;
    private String content;
    private long upvote;
    private long downvote;
    private long freq;
    private List<String> tags;
    private Long timestamp;
    private EStoryStatus isApproved;
    private String raw_content;

    public String getId() {
        return id;
    }

    public long getFreq() {
        return freq;
    }

    public void setFreq(long freq) {
        this.freq = freq;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUpvote() {
        return upvote;
    }

    public void setUpvote(long upvote) {
        this.upvote = upvote;
    }

    public long getDownvote() {
        return downvote;
    }

    public void setDownvote(long downvote) {
        this.downvote = downvote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public EStoryStatus getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(EStoryStatus isApproved) {
        this.isApproved = isApproved;
    }

    public String getRaw_content() {
        return raw_content;
    }

    public void setRaw_content(String raw_content) {
        this.raw_content = raw_content;
    }
}
