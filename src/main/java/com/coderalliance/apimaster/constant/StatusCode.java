package com.coderalliance.apimaster.constant;

import lombok.Getter;

@Getter
public enum StatusCode {
    SUCCESS(200),
    ERROR(500),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }
}
