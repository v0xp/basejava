package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AbstractStorageTest {
    public final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_NOT_EXIST = "dummy";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] array = new Resume[0];
        assertArrayEquals(array, storage.getAll());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSame(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        Arrays.sort(array);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_3, array[2]);
    }

    @Test
    public void save() {
        storage.clear();
        storage.save(RESUME_1);
        assertSize(1);
        assertGet(RESUME_1);
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_3);
        });
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_NOT_EXIST);
        });
    }

    @Test
    public void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.delete(UUID_NOT_EXIST);
        });
    }

    @Test
    public void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.update(storage.get(UUID_NOT_EXIST));
        });
    }

    @Test
    public void saveExist() {
        assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_3);
        });
    }

    @Test
    public void saveOverflow() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });
    }

    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    protected void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}