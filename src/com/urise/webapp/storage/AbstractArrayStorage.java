package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected final static int STORAGE_LIMIT = 10000;
    protected final static Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void deletedStorageIndex(int index);

    protected abstract void savedStorageIndex(Resume resume, int index);

//    protected abstract Integer getKey(String uuid);

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
    protected boolean isExist(Integer resumeKey) {
        return resumeKey >= 0;
    }

    @Override
    protected void doUpdate(Resume resume, Integer resumeKey) {
        storage[resumeKey] = resume;
    }

    @Override
    protected void doSave(Resume resume, Integer resumeKey) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен", resume.getUuid());
        } else {
            savedStorageIndex(resume, resumeKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Integer resumeKey) {
        deletedStorageIndex(resumeKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Integer resumeKey) {
        return storage[resumeKey];
    }

    @Override
    protected List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}