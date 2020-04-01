package com.mystore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyStoreBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final String CATEGORY_NOT_FOUND = "category_not_found";
    public static final String CATEGORY_NAME_ALREDY_REGISTERED = "category_name_alredy_registered";
    public static final String CATEGORY_NAME_SHOULD_HAVE_A_NAME = "category_name_should_have_a_name";

    public MyStoreBusinessException(final String msg) {
        super(msg);
    }
}
