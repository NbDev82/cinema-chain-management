package com.example.cinemachainmanagement.enums;

import lombok.Getter;

@Getter
public enum EMethod {
    VISA(1),
    MASTER_CARD(2),
    VTC(3),
    CREDIT_CARD(4),
    BANKING(5),
    AtTheCounter(6);

    private final int value;

    EMethod(int value) {
        this.value = value;
    }

}
