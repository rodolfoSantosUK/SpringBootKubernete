package com.dailycodeBuffer.orderSrvc.decoder;

import com.dailycodeBuffer.orderSrvc.exception.CustomException;
import com.dailycodeBuffer.orderSrvc.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();

        log.info(" ::{}", response.request().url());
        log.info(" ::{}", response.request().headers());

        try {

            ErrorResponse errorResponse =
                    objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class );

            log.info(" :: dentro do try " );

            return new CustomException(errorResponse.getErrorMessage(),
                    errorResponse.getErrorCode(),
                    response.status());

        } catch (IOException e) {

            log.info(" :: dentro do catch " );
                throw new CustomException("Erro interno testando erro",
                        "INTERNAL_SERVER_ERROR",
                        500);

        }
    }
}
