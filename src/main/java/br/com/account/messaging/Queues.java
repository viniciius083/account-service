package br.com.account.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Queues {

    ACCOUNT_UPSERT("account-upsert");

    private final String value;
}
