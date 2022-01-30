package com.qnocks.universemanagesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    ACTIVE("active"),

    BANNED("banned");

    private final String name;
}
