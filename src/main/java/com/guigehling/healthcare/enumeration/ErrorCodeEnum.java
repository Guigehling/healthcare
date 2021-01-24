package com.guigehling.healthcare.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_INSTITUTION_CREATE("error.institution.create"),

    ERROR_WALLET_GET_BY_INSTITUTION("error.wallet.get.by.institution"),

    ERROR_EXAM_CREATE_INSUFFICIENT_FUNDS("error.exam.create.insufficient.funds");

    private final String messageKey;
}
