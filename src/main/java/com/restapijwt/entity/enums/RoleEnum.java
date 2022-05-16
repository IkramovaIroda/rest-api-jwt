package com.restapijwt.entity.enums;

import lombok.Data;

public enum RoleEnum {
    //static
    ROLE_USER("user"), //tengkuchli == RolEnum enum = new RoleEnum("Role USER")
    ROLE_ADMIN("admin"),
    ROLE_MODERATOR("moderator");
    private String description;

    RoleEnum(String description) {
        this.description = description;
    }
}
