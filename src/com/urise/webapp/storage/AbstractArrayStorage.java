package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final static Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

//    public final void update(Resume resume) {
//        int index = getIndex(resume.getUuid());
//        if (index < 0) {
//            throw new NotExistStorageException(resume.getUuid());
//        } else {
//            storage[index] = resume;
//            System.out.println("Резюме " + storage[index] + " обновлено");
//        }
//    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

//    public final void save(Resume r) {
//        int index = getIndex(r.getUuid());
//        if (index > 0) {
//            throw new ExistStorageException(r.getUuid());
//        } else if (size >= STORAGE_LIMIT) {
//            throw new StorageException("Массив переполнен", r.getUuid());
//        } else {
//            savedStorageIndex(r, index);
//            size++;
//        }
//    }
//
//    public final void delete(String uuid) {
//        int index = getIndex(uuid);
//        if (index < 0) {
//            throw new NotExistStorageException(uuid);
//        } else {
//            deletedStorageIndex(index);
//            storage[size - 1] = null;
//            size--;
//        }
//    }
//
//    public final Resume get(String uuid) {
//        int index = getIndex(uuid);
//        if (index < 0) {
//            throw new NotExistStorageException(uuid);
//        }
//        return storage[index];
//    }

    protected abstract void deletedStorageIndex(int index);

    protected abstract void savedStorageIndex(Resume r, int index);

    protected abstract int getIndex(String uuid);
}