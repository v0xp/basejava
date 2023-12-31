package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deletedStorageIndex(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected void savedStorageIndex(Resume r, int index) {
        int indexStorage = -index - 1;
        System.arraycopy(storage, indexStorage, storage, indexStorage + 1, size - indexStorage);
        storage[indexStorage] = r;
    }

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}