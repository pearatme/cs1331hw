package hw5;

import java.lang.IllegalArgumentException;

public class MyList<E> implements List<E> {
    E[] elements;
    int size;

    public MyList() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public MyList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(E e) {
        if (e == null)
            throw new IllegalArgumentException("");

        if (size == elements.length) {
            E[] oldElements = elements;
            elements = (E[]) new Object[2 * size];
            for (int i = 0; i < size; i++)
                oldElements[i] = elements[i];
        }

        elements[size] = e;
        size++;
    }

    @Override
    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("");

        return elements[index];
    }

    @Override
    public void replace(E o, E replaceWith) {
        if (o == null || replaceWith == null)
            throw new IllegalArgumentException("");

        for (int i = 0; i < size; i++)
            if (elements[i].equals(o))
                elements[i] = replaceWith;
    }

    @Override
    public int remove(E o) {
        if (o == null)
            throw new IllegalArgumentException("");

        int count = 0;
        for (int i = 0; i < size; i++)
            if (elements[i].equals(o))
                count++;
            else if (count != 0)
                elements[i - count] = elements[i];
        return count;
    }

    @Override
    public int contains(E o) {
        if (o == null)
            throw new IllegalArgumentException("");

        int count = 0;
        for (E element : elements)
            if (element.equals(o))
                count++;
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        elements = (E[]) new Object[elements.length]; // i dont think this line even matters lol
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray(E[] e) {
        for (int i = 0; i < size && i < e.length; i++)
            if (elements[i] != null)
                e[i] = elements[i];

        return e;
    }
}
