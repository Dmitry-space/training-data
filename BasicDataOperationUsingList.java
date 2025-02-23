import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Клас BasicDataOperationUsingList надає методи для виконання основних операцiй з даними типу long.
 * 
 * <p>Цей клас зчитує данi з файлу "list/long.data", сортує їх та виконує пошук значення в масивi та списку.</p>
 * 
 * <p>Основнi методи:</p>
 * <ul>
 *   <li>{@link #main(String[])} - Точка входу в програму.</li>
 *   <li>{@link #doDataOperation()} - Виконує основнi операцiї з даними.</li>
 *   <li>{@link #sortArray()} - Сортує масив long.</li>
 *   <li>{@link #searchArray()} - Виконує пошук значення в масивi long.</li>
 *   <li>{@link #findMinAndMaxInArray()} - Знаходить мiнiмальне та максимальне значення в масивi long.</li>
 *   <li>{@link #sortList()} - Сортує список long.</li>
 *   <li>{@link #searchList()} - Виконує пошук значення в списку long.</li>
 *   <li>{@link #findMinAndMaxInList()} - Знаходить мiнiмальне та максимальне значення в списку long.</li>
 * </ul>
 * 
 * <p>Конструктор:</p>
 * <ul>
 *   <li>{@link #BasicDataOperationUsingList(String[])} - iнiцiалiзує об'єкт з значенням для пошуку.</li>
 * </ul>
 * 
 * <p>Константи:</p>
 * <ul>
 *   <li>{@link #PATH_TO_DATA_FILE} - Шлях до файлу з даними.</li>
 * </ul>
 * 
 * <p>Змiннi екземпляра:</p>
 * <ul>
 *   <li>{@link #longValueToSearch} - Значення long для пошуку.</li>
 *   <li>{@link #longArray} - Масив long.</li>
 *   <li>{@link #longList} - Список long.</li>
 * </ul>
 * 
 * <p>Приклад використання:</p>
 * <pre>
 * {@code
 * java BasicDataOperationUsingList "-5374543146828752099"
 * }
 * </pre>
 */
public class BasicDataOperationUsingList {
    static final String PATH_TO_DATA_FILE = "list/long.data";

    long longValueToSearch;
    long[] longArray;
    List<Long> longList;

    public static void main(String[] args) {  
        BasicDataOperationUsingList basicDataOperationUsingList = new BasicDataOperationUsingList(args);
        basicDataOperationUsingList.doDataOperation();
    }

    /**
     * Конструктор, який iнiцiалiзує об'єкт з значенням для пошуку.
     * 
     * @param args Аргументи командного рядка, де перший аргумент - значення для пошуку.
     */
    BasicDataOperationUsingList(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Вiдсутнє значення для пошуку");
        }

        String searchValue = args[0];
        longValueToSearch = Long.parseLong(searchValue);

        longArray = Utils.readArrayFromFile(PATH_TO_DATA_FILE);
        longList = new LinkedList<>();
        for (long value : longArray) {
            longList.add(value);
        }
    }

    /**
     * Виконує основнi операцiї з даними.
     * 
     * Метод зчитує масив та список об'єктiв long з файлу, сортує їх та виконує пошук значення.
     */
    void doDataOperation() {
        // операцiї з масивом
        searchArray();
        findMinAndMaxInArray();

        sortArray();
        
        searchArray();
        findMinAndMaxInArray();

        // операцiї з LinkedList
        searchList();
        findMinAndMaxInList();

        sortList();

        searchList();
        findMinAndMaxInList();

        // записати вiдсортований масив в окремий файл
        Utils.writeArrayToFile(longArray, PATH_TO_DATA_FILE + ".sorted");
    }

    /**
     * Сортує масив об'єктiв long та виводить початковий i вiдсортований масиви.
     * Вимiрює та виводить час, витрачений на сортування масиву в наносекундах.
     */
    void sortArray() {
        long startTime = System.nanoTime();

        Arrays.sort(longArray);

        Utils.printOperationDuration(startTime, "сортування масиву long");
    }

    /**
     * Метод для пошуку значення в масивi long.
     */
    void searchArray() {
        long startTime = System.nanoTime();

        int index = Arrays.binarySearch(this.longArray, longValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в масивi long");

        if (index >= 0) {
            System.out.println("Значення '" + longValueToSearch + "' знайдено в масивi за iндексом: " + index);
        } else {
            System.out.println("Значення '" + longValueToSearch + "' в масивi не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в масивi long.
     */
    void findMinAndMaxInArray() {
        if (longArray == null || longArray.length == 0) {
            System.out.println("Масив порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        long min = longArray[0];
        long max = longArray[0];

        for (long value : longArray) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в масивi");

        System.out.println("Мiнiмальне значення в масивi: " + min);
        System.out.println("Максимальне значення в масивi: " + max);
    }

    /**
     * Шукає задане значення long в LinkedList.
     */
    void searchList() {
        long startTime = System.nanoTime();

        int index = Collections.binarySearch(this.longList, longValueToSearch);

        Utils.printOperationDuration(startTime, "пошук в LinkedList long");        

        if (index >= 0) {
            System.out.println("Значення '" + longValueToSearch + "' знайдено в LinkedList за iндексом: " + index);
        } else {
            System.out.println("Значення '" + longValueToSearch + "' в LinkedList не знайдено.");
        }
    }

    /**
     * Знаходить мiнiмальне та максимальне значення в LinkedList.
     */
    void findMinAndMaxInList() {
        if (longList == null || longList.isEmpty()) {
            System.out.println("LinkedList порожнiй або не iнiцiалiзований.");
            return;
        }

        long startTime = System.nanoTime();

        long min = Collections.min(longList);
        long max = Collections.max(longList);

        Utils.printOperationDuration(startTime, "пошук мiнiмальної i максимальної дати i часу в LinkedList");

        System.out.println("Мiнiмальне значення в LinkedList: " + min);
        System.out.println("Максимальне значення в LinkedList: " + max);
    }

    /**
     * Сортує LinkedList об'єктiв long.
     * Вимiрює та виводить час, витрачений на сортування списку в наносекундах.
     */
    void sortList() {
        long startTime = System.nanoTime();

        Collections.sort(longList);

        Utils.printOperationDuration(startTime, "сортування LinkedList long");
    }
}

/**
 * Клас Utils мiститить допомiжнi методи для роботи з даними типу long.
 */
class Utils {
    /**
     * Виводить час виконання операцiї в наносекундах.
     * 
     * @param startTime Час початку операцiї в наносекундах.
     * @param operationName Назва операцiї.
     */
    static void printOperationDuration(long startTime, String operationName) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("\n>>>>>>>>> Час виконання операцiї '" + operationName + "': " + duration + " наносекунд");
    }

    /**
     * Зчитує масив об'єктiв long з файлу.
     * 
     * @param pathToFile Шлях до файлу з даними.
     * @return Масив об'єктiв long.
     */
    static long[] readArrayFromFile(String pathToFile) {
        long[] tempArray = new long[1000];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                long value = Long.parseLong(line);
                tempArray[index++] = value;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long[] finalArray = new long[index];
        System.arraycopy(tempArray, 0, finalArray, 0, index);

        return finalArray;
    }

    /**
     * Записує масив об'єктiв long у файл.
     * 
     * @param longArray Масив об'єктiв long.
     * @param pathToFile Шлях до файлу для запису.
     */
    static void writeArrayToFile(long[] longArray, String pathToFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (long value : longArray) {
                writer.write(Long.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}