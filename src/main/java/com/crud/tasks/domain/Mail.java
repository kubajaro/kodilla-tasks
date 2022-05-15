package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Mail {
    private String mailReceiver;
    private String toCc;
    private String subject;
    private String message;
}
