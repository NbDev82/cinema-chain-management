package com.example.cinemachainmanagement.enums;

import lombok.Getter;

@Getter
public enum EMessage {
    CHANGE_PASS_WORD_SUCCESS("Thay đổi mật khẩu thành công!!"),
    OLD_PASS_NOT_MATCH("Mật khẩu không đúng!!"),
    CHANGE_PASS_WORD_NOT_SUCCESS("Thay đổi mật khẩu thất bại!!"),
    CUSTOMER_NOT_EXIST("Khách hàng không tồn tại!!");
    private final String value;

    EMessage(String value) {
        this.value = value;
    }
}
