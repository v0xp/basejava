package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(Object resumeKey);

    protected abstract void updateResume(Resume resume, Object resumeKey);

    protected abstract void savedStorage(Resume r);

    protected abstract void deletedStorage(Object resumeKey);

    protected abstract Resume getResume(Object resumeKey);


    public final void update(Resume resume) {
        Object resumeKey = getKey(resume.getUuid());
        if (!isExist(resumeKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, resumeKey);
            System.out.println("Резюме обновлено");
        }
    }

    public final void save(Resume r) {
        Object resumeKey = getKey(r.getUuid());
        if (isExist(resumeKey)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            savedStorage(r);
        }
    }

    public final void delete(String uuid) {
        Object resumeKey = getKey(uuid);
        if (!isExist(resumeKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deletedStorage(resumeKey);
        }
    }

    public final Resume get(String uuid) {
        Object resumeKey = getKey(uuid);
        if (!isExist(resumeKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(resumeKey);
    }
}