/**
 * Created by tjohnell on 6/24/17.
 */
public class InsertionSort
        <T extends Comparable<T>> implements SortInterface<T> {

    @Override
    public void sort(T[] items) {
        for (int i = 0; i < items.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (items[j].compareTo(items[j + 1]) > 0) {
                    T temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }
}
