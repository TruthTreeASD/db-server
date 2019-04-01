package edu.neu.cs6510.enums;

public enum VoteType {
    UPVOTE("upvote"), DOWNVOTE("downvote");

    private String field;
    VoteType(String type) {
        this.field = type;
    }

    public String getField() {
        return field;
    }

}
