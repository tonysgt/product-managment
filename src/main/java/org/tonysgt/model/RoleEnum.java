package org.tonysgt.model;

public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private String value;

    RoleEnum(String value) {}
    public String getValue() {
        return value;
    }
}
