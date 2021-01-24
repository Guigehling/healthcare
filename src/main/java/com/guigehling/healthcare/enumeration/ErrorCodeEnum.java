package com.guigehling.healthcare.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    ERROR_INSTITUTION_CREATE("error.institution.create");

    private final String messageKey;
}
