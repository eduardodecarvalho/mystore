package com.mystore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyStoreBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final String CATEGORY_NOT_FOUND = "category_not_found";
    public static final String CATEGORY_NAME_ALREDY_REGISTERED = "category_name_alredy_registered";
    public static final String CATEGORY_SHOULD_HAVE_A_NAME = "category_should_have_a_name";
    public static final String CATEGORY_WITH_PRODUCTS = "category_with_products";

    public static final String PRODUCT_NOT_FOUND = "product_not_found";
    public static final String PRODUCT_SHOULD_HAVE_A_NAME = "product_should_have_a_name";
    public static final String PRODUCT_NAME_ALREDY_REGISTERED = "product_name_alredy_registered";
    public static final String PRODUCT_MODEL_ALREDY_REGISTERED = "product_model_alredy_registered";
    public static final String PRODUCT_SHOULD_HAVE_A_MODEL = "product_should_have_a_model";
    public static final String PRODUCT_HAVE_QUANTITY = "product_have_quantity";

    public static final String CLIENT_NOT_FOUND = "client_not_found";

    public static final String EMAIL_ALREDY_REGISTERED = "email_alredy_registered";
    public static final String NATIONAL_REGISTER_ALREDY_REGISTERED = "national_register_alredy_registered";

    public MyStoreBusinessException(final String msg) {
        super(msg);
    }
}
