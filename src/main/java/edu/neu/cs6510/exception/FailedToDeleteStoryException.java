package edu.neu.cs6510.exception;

import edu.neu.cs6510.util.BaseException;

public class FailedToDeleteStoryException extends BaseException {
    public FailedToDeleteStoryException(String message) {
        super(message);
    }
}
