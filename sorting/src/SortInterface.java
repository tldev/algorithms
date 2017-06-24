/**
 * Created by tjohnell on 6/24/17.
 */
public interface SortInterface<T extends Comparable<? super T>> {
    public void sort(T[] items);
}
