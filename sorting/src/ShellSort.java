/**
 * Created by tjohnell on 6/24/17.
 */
public class ShellSort
        <T extends Comparable<T>> implements SortInterface<T> {

    @Override
    public void sort(T[] items) {
        int N = items.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {
            for (int i = h; i < items.length; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (items[j].compareTo(items[j - h]) < 0) {
                        T temp = items[j];
                        items[j] = items[j - h];
                        items[j - h] = temp;
                    }
                }
            }

            h = (h - 1) / 3;
        }
    }
}
