import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    // очищаем массив резюме (заполняем зачением null занятые резюме ячейки) и обнуляем сетчик резюме
    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Массив переполен");
        } else {
            //сохраняем полученный объект резюме в первую свободную ячейку и увеличиваем счетчик
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {

        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        return new Resume[0];
    }

    int size() {

        return size;
    }
}