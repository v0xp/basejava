package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(Object resumeKey);

    protected abstract void doUpdate(Resume resume, Object resumeKey);

    protected abstract void doSave(Resume resume, Object resumeKey);

    protected abstract void doDelete(Object resumeKey);

    protected abstract Resume doGet(Object resumeKey);


    public final void update(Resume resume) {
        Object resumeKey = getKeyWithNotExistException(resume.getUuid());
        doUpdate(resume, resumeKey);
        System.out.println("Резюме обновлено");
    }

    public final void save(Resume resume) {
        Object resumeKey = getKeyWithExistException(resume.getUuid());
        doSave(resume, resumeKey);
    }

    public final void delete(String uuid) {
        Object resumeKey = getKeyWithNotExistException(uuid);
        doDelete(resumeKey);
    }

    public final Resume get(String uuid) {
        Object resumeKey = getKeyWithNotExistException(uuid);
        return doGet(resumeKey);
    }

    public final Object getKeyWithExistException(String uuid) {
        Object resumeKey = getKey(uuid);
        if (isExist(resumeKey)) {
            throw new ExistStorageException(uuid);
        }
        return resumeKey;
    }

    public final Object getKeyWithNotExistException(String uuid) {
        Object resumeKey = getKey(uuid);
        if (!isExist(resumeKey)) {
            throw new NotExistStorageException(uuid);
        }
        return resumeKey;
    }
}