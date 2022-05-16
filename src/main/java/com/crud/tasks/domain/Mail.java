package com.crud.tasks.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class Mail {
    private String mailReceiver;
    private String toCc;
    private String subject;
    private String message;
}
