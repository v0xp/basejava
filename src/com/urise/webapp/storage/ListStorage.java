package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected final ArrayList<Resume> list = new ArrayList<>();

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer resumeKey) {
        return resumeKey != null;
    }

    @Override
    protected void doUpdate(Resume resume, Integer resumeKey) {
        list.set(resumeKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Integer resumeKey) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Integer resumeKey) {
        list.remove((int) resumeKey);
    }

    @Override
    protected Resume doGet(Integer resumeKey) {
        return list.get(resumeKey);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
