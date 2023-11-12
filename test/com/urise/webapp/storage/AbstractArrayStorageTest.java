package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractArrayStorageTest {
    public Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        storage.getAll();
        assertEquals(3, storage.size());
    }

    @Test
    public void save() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        assertEquals(1, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());

    }

    @Test
    public void get() {
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
            storage.delete("dummy");
            storage.update(storage.get("dummy"));
        });
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_3));
        });
    }

    @Test
    public void saveOverflow() {
        Assertions.assertThrows(StorageException.class, () -> {
            for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
            storage.save(new Resume());
        });
    }
}