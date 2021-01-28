package com.guigehling.healthcare.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_INSTITUTION_CREATE("error.institution.create"),
    ERROR_INSTITUTION_FIND_BY_ACCESS_KEY("error.institution.find.by.access.key"),

    ERROR_WALLET_GET_BY_INSTITUTION("error.wallet.get.by.institution"),

    ERROR_EXAM_CREATE_INSUFFICIENT_FUNDS("error.exam.create.insufficient.funds"),
    ERROR_EXAM_FIND_INSUFFICIENT_FUNDS("error.exam.find.insufficient.funds"),
    ERROR_EXAM_FIND_BY_ID("error.exam.find.by.id");

    private final String messageKey;
}
