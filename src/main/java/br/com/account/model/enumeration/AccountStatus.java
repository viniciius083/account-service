package br.com.account.model.enumeration;

public enum AccountStatus {
    PENDING,  // Conta aguardando aprovação
    APPROVED, // Conta aprovada, mas sem senha.
    ACTIVE, // Conta ativa, com senha.
    INACTIVE  // Conta rejeitada
}
