package com.urise.webapp.model;

public enum ContactType {
    ADDRESS("Moscow"),
    PHONE("+79780439723"),
    MAIL("vs-kisele@mail.ru"),
    TG("+79780439723");

    private final String contacts;

    ContactType(String contacts) {
        this.contacts = contacts;
    }

    public String getContacts() {
        return contacts;
    }
}
