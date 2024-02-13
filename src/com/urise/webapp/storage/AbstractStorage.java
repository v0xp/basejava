package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    //    protected final Logger LOG = Logger.getLogger(getClass().getName());
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getKey(String uuid);

    protected abstract boolean isExist(SK resumeKey);

    protected abstract void doUpdate(Resume resume, SK resumeKey);

    protected abstract void doSave(Resume resume, SK resumeKey);

    protected abstract void doDelete(SK resumeKey);

    protected abstract Resume doGet(SK resumeKey);

    protected abstract List<Resume> doCopyAll();


    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        SK resumeKey = getKeyWithNotExistException(resume.getUuid());
        doUpdate(resume, resumeKey);
        System.out.println("Резюме обновлено");
    }

    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        SK resumeKey = getKeyWithExistException(resume.getUuid());
        doSave(resume, resumeKey);
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK resumeKey = getKeyWithNotExistException(uuid);
        doDelete(resumeKey);
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK resumeKey = getKeyWithNotExistException(uuid);
        return doGet(resumeKey);
    }

    public final SK getKeyWithExistException(String uuid) {
        SK resumeKey = getKey(uuid);
        if (isExist(resumeKey)) {
            LOG.warning("Резюме уже существует " + uuid);
            throw new ExistStorageException(uuid);
        }
        return resumeKey;
    }

    public final SK getKeyWithNotExistException(String uuid) {
        SK resumeKey = getKey(uuid);
        if (!isExist(resumeKey)) {
            LOG.warning("Резюме отсутствует " + uuid);
            throw new NotExistStorageException(uuid);
        }
        return resumeKey;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}