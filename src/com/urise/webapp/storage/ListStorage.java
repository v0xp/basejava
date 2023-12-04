package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> list = new ArrayList<>();

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return resumeKey != null;
    }

    @Override
    protected void updateResume(Resume resume, Object resumeKey) {
        list.set((int) resumeKey, resume);
    }

    @Override
    protected void savedStorage(Resume r) {
        list.add(r);
    }

    @Override
    protected void deletedStorage(Object resumeKey) {
        list.remove((int) resumeKey);
    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return list.get((int) resumeKey);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
