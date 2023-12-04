package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deletedStorageIndex(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void savedStorageIndex(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Object getKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return false;
    }

    @Override
    protected void updateResume(Resume resume, Object resumeKey) {

    }

    @Override
    protected void savedStorage(Resume r) {

    }

    @Override
    protected void deletedStorage(Object resumeKey) {

    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return null;
    }
}