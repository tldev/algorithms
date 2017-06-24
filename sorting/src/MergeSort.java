/**
 * Created by tjohnell on 6/24/17.
 */
public class MergeSort {
    public static void mergeSort(Comparable[] list, Comparable[] aux, int lo, int hi, int mid) {
        for (int i = lo; i < hi; i++) {
            aux[i] = list[i];
        }

        int left = lo;
        int right = mid;

        for (int k = lo; k < hi; k++) {
            if (left >= mid) {
                list[k] = aux[right++];
            } else if (right >= hi) {
                list[k] = aux[left++];
            } else if (aux[right].compareTo(aux[left]) > 0) {
                list[k] = aux[left++];
            } else {
                list[k] = aux[right++];
            }
        }
    }
}
