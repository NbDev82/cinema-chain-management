package com.example.cinemachainmanagement.enums;

import lombok.Getter;

@Getter
public enum ERating {
    LOW(1.0f),
    BELOW_AVERAGE(2.0f),
    AVERAGE(3.0f),
    ABOVE_AVERAGE(4.0f),
    HIGH(5.0f);

    private final float value;

    ERating(float value) {
        this.value = value;
    }

}
