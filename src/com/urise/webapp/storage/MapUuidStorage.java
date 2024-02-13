package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;


public class MapUuidStorage extends AbstractStorage<String> {

    protected final Map<String, Resume> map = new HashMap<>(STORAGE_LIMIT);

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void doSave(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}