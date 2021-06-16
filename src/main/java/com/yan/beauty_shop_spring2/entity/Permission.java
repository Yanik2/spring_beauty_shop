package com.yan.beauty_shop_spring2.entity;

public enum Permission {
    MASTER("master"),
    CLIENT("client"),
    ADMIN("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
