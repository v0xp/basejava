package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    // очищаем массив резюме (заполняем зачением null занятые резюме ячейки) и обнуляем сетчик резюме
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Массив переполнен");
        } else {
            //сохраняем полученный объект резюме в первую свободную ячейку и увеличиваем счетчик
            storage[size] = r;
            size++;
        }
    }

    // получение резюме из массива
    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме отсутствует");
        return null;
    }

    // метод удаления резюме
    public void delete(String uuid) {
        for (int i = 0; i < size - 1; i++) {
            // присваиваем найденному резюме последнее значение
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            } else {
                System.out.println("Резюме отсутствует");
            }
        }
    }

    public void update(Resume resume){
        for (int i = 0; i <= size - 1; i++) {
            if (storage[i].uuid.equals(resume.uuid)) {
                storage[i] = resume;
                System.out.println("Резюме обновлено");
                break;
            } else if (i == size - 1 && !Arrays.asList(storage).contains(resume)){
                System.out.println("Резюме отсутствует");
            }
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