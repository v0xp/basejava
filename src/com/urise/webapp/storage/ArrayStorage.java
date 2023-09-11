package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int STORAGE_LIMIT = 10000;
    private final static Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    // очищаем массив резюме (заполняем зачением null занятые резюме ячейки) и обнуляем сетчик резюме
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Массив переполнен");
        } else if (Arrays.toString(storage).contains(r.uuid)) {
            System.out.println("Резюме уже существует");
        } else {
            //сохраняем полученный объект резюме в первую свободную ячейку и увеличиваем счетчик
            storage[size] = r;
            size++;
        }
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    // получение резюме из массива
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме отсутствует");
            return null;
        }
        return storage[index];
    }

    // метод удаления резюме
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме отсутствует");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index < 0) {
            System.out.println("Резюме отсутствует");
        } else {
            storage[index] = resume;
            System.out.println("Резюме " + storage[index] + " обновлено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    // получение заполненного массива
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}