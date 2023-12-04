package com.urise.webapp.storage;

import org.junit.jupiter.api.Test;

class ListStorageTest extends AbstractStorageTest {


    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
    }
}