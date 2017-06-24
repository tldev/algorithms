/**
 * Created by tjohnell on 6/24/17.
 */
public class SelectionSort<T extends Comparable<T>> implements SortInterface<T> {

    @Override
    public void sort(T[] items) {
        for (int i = 0; i < items.length; i++) {
            int currentMin = i;
            for (int j = i + 1; j < items.length; j++) {
                if (items[j].compareTo(items[currentMin]) < 0) {
                    currentMin = j;
                }
            }

            if (currentMin != i) {
                T temp = items[i];
                items[i] = items[currentMin];
                items[currentMin] = temp;
            }
        }
    }
}
