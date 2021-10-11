package com.example.visitsservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoker, Response response) {
        if (response.status() == 404 || response.status() == 400) {
            return new NotFoundException(
                    "Pet ID is not found"
            );
        }
        return defaultErrorDecoder.decode(invoker, response);
    }
}
