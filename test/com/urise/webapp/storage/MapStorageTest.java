package com.urise.webapp.storage;

import org.junit.jupiter.api.Test;

class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void saveOverflow() {
    }
}