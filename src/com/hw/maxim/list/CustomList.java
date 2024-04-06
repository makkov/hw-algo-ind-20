package com.hw.maxim.list;

import com.hw.maxim.exception.IncorrectIndexException;
import com.hw.maxim.exception.InvalidItemException;
import com.hw.maxim.exception.RemoveException;

import java.util.Arrays;

public class CustomList implements StringList {

    private static final int DEFAULT_ARRAY_SIZE = 10;

    private String[] array;

    private int size;

    public CustomList() {
        array = new String[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    @Override
    public String add(String item) {
        checkCapacity();
        checkItem(item);
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkCapacity();
        checkItem(item);
        checkIndex(index);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkItem(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkItem(item);

        int itemIndex = indexOf(item);

        if (itemIndex == -1) {
            throw new RemoveException("There isn't item!");
        }

        remove(itemIndex);

        return item;
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public boolean contains(String item) {
        checkItem(item);
        int itemIndex = indexOf(item);

        return itemIndex != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItem(item);
        for (int i = size - 1; i > 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (this.size != otherList.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (this.get(i) != otherList.get(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        array = new String[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    @Override
    public String[] toArray() {
        return array;
    }

    @Override
    public String toString() {
        return "CustomList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    private void checkCapacity() {
        int nextSize = size + 1;
        if (DEFAULT_ARRAY_SIZE < nextSize) {
            throw new IncorrectIndexException("New size greater than current size!");
        }
    }

    private void checkItem(String item) {
        if (item == null) {
            throw new InvalidItemException("Input item is null!");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IncorrectIndexException("Out of index!");
        }
    }
}
