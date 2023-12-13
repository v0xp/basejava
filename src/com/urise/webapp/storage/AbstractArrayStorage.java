package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected final static int STORAGE_LIMIT = 10000;
    protected final static Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void deletedStorageIndex(int index);

    protected abstract void savedStorageIndex(Resume resume, int index);

    protected abstract Integer getKey(String uuid);


    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return (Integer) resumeKey >= 0;
    }

    @Override
    protected void doUpdate(Resume resume, Object resumeKey) {
        storage[(int) resumeKey] = resume;
    }

    @Override
    protected void doSave(Resume resume, Object resumeKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен", resume.getUuid());
        } else {
            savedStorageIndex(resume, (int) resumeKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Object resumeKey) {
        deletedStorageIndex((int) resumeKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object resumeKey) {
        return storage[(int) resumeKey];
    }
}