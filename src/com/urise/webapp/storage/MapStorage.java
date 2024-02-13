package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.*;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public class MapStorage extends AbstractStorage<Resume> {

    protected final Map<String, Resume> map = new HashMap<>(STORAGE_LIMIT);

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    public void clear() {
        map.clear();
    }

    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
