package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return map.containsKey((String)resumeKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object resumeKey) {
        map.put((String) resumeKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object resumeKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object resumeKey) {
        map.remove((String) resumeKey);
    }

    @Override
    protected Resume doGet(Object resumeKey) {
        return map.get((String) resumeKey);
    }

    @Override
    public void clear() {
        map.clear();
    }


    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
