package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    protected final ArrayList<Resume> list = new ArrayList<>();

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
    protected void doUpdate(Resume resume, Object resumeKey) {
        list.set((int) resumeKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object resumeKey) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Object resumeKey) {
        list.remove((int) resumeKey);
    }

    @Override
    protected Resume doGet(Object resumeKey) {
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
