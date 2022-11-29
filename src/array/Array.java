package array;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {

    private T[] internal_arr; // internal static array
    private int len = 0; // length of array
    private int capacity = 0; // actual array length


    public Array(int cons_capacity) {
        if (cons_capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = cons_capacity;
        // creating new array object
        internal_arr = (T[]) new Object[capacity];
    }

    public Array() {
        this(16);
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return internal_arr[index];
    }

    public void set(int index, T new_element) {
        internal_arr[index] = new_element;
    }

    public void clear() {
        for (int index = 0; index < capacity; index++) {
            internal_arr[index] = null;
        }
        len = 0;
    }

    public void add(T new_element) {
        if (len + 1 >= capacity) {
            /*
             * If array capacity is full, time to resize array size,
             * creating new array, double the size and coping old element in the
             * array.
             *
             * */
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size

            T[] new_arr = (T[]) new Object[capacity];

            for (int index = 0; index < len; index++) {
                new_arr[index] = internal_arr[index];
            }

            internal_arr = new_arr;
        }
        internal_arr[len++] = new_element;
    }

    public T removeAt(int target_index) {
        if (target_index >= len && target_index < 0)
            throw new IndexOutOfBoundsException();

        T data = internal_arr[target_index];
        T[] new_arr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++)
            if (i == target_index) j--;
            else new_arr[j] = internal_arr[i];
        internal_arr = new_arr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (internal_arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (internal_arr[i].equals(obj))
                return i;
        }
        return -1;
    }

    public boolean contain(Object obj) {
        return indexOf(obj) != -1;
    }

    public String toStringFormat() {
        if (len == 0) return "[]";
        else {
            StringBuilder stringBuilder = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++)
                stringBuilder.append(internal_arr[i] + " , ");
            return stringBuilder.append(internal_arr[len - 1] + "]").toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return internal_arr[index++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
