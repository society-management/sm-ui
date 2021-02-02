package com.sm.enums;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private String userPermission;

    ApplicationUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserPermission() {
        return userPermission;
    }
}
