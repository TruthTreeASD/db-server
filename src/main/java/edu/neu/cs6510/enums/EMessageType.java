package edu.neu.cs6510.enums;

public enum EMessageType {
    UPVOTE("upvote"), DOWNVOTE("downvote"), APPROVED("isApproved"), DISAPPROVED("isApproved"), PENDING("isApproved"), FREQ_INC("freq");


    private String field;
    EMessageType(String type) {
        this.field = type;
    }

    public String getField() {
        return field;
    }

    public static void main(String[] args) {
        System.out.println(EMessageType.APPROVED);
    }
}
