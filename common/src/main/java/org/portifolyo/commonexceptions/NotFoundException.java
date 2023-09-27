package org.portifolyo.commonexceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id) {
        super(String.format("Not Found %s",id));
    }
}
