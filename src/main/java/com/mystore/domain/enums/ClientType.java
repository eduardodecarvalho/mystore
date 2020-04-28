package com.mystore.domain.enums;

public enum ClientType {

    PERSON(0),
    ENTERPRISE(1),
    UNKNOWN(99);

    private Integer value;

    private ClientType(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ClientType toEum(final Integer value) {
        for (final ClientType en : ClientType.values()) {
            if (en.getValue().equals(value)) {
                return en;
            }
        }
        return UNKNOWN;
    }
}
