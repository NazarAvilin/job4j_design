package ru.job4j.map;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {

    private static class Node<K, V> {
        final int hash;
        K key;
        V value;
        Node<K, V> next;

        protected Node(int hash, final K key, final V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash
                   && Objects.equals(key, node.key)
                   && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value);
        }

        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            }
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public K setKey(K key) {
            if (key == null) {
                throw new NullPointerException();
            }
            K oldKey = this.key;
            this.key = key;
            return oldKey;
        }
    }

    private Node[] array;
    private int index = 0;
    private int modCount = 0;
    private int latestCapacity;

    private int rehash(K key, Node<?, ?>[] newArray) {
        int hash = Objects.hash(key);
        return (hash & 0x7FFFFFFF) % newArray.length;
    }

    private int hash(K key) {
        if (key == null) {
            throw new UnsupportedOperationException();
        } else {
            int hash = Objects.hash(key);
            return (hash & 0x7FFFFFFF) % array.length;
        }
    }

    private void enlargeArray() {
        Node[] newArray = new Node[latestCapacity << 1];
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == null) {
                continue;
            }
            for (Node pair = array[i]; pair != null;) {
                int position = rehash((K) pair.getKey(), newArray);
                if (newArray[position] != null) {
                    newArray[position].next = pair;
                } else {
                    newArray[position] = pair;
                }
                pair = pair.next;
            }
        }
        array = newArray;
        latestCapacity = array.length;
    }

    public SimpleHashMap() {
        array = new Node[10];
        latestCapacity = array.length;
    }

    public void insert(K key, V value) {
        final float factor = 0.75f;
        if ((float) array.length / index >= factor) {
            enlargeArray();
        }
        int position = hash(key);
        if (array[position] != null) {
            Node pair = array[position];
            Node prev = pair;
            for (pair = array[position]; pair != null; pair = pair.next) {
                if (Objects.equals(pair.key, key)) {
                    pair.setValue(value);
                    return;
                }
                prev = pair;
            }
            prev.next = new Node<>(hash(key), key, value, null);
        } else {
            array[position] = new Node<>(hash(key), key, value, null);

        }
        index++;
        modCount++;
    }

    public V get(K key) {
        int index = hash(key);
        V value = null;
        for (Node pair = array[index]; pair != null;) {
            if (pair.getKey().equals(key)) {
                value = (V) pair.getValue();
                break;
            }
            pair = pair.next;
        }
        return value;
    }

    public boolean delete(K key) {
        boolean result = false;
        int position = hash(key);
        if (array[position] == null) {
            throw new NoSuchElementException();
        } else {
            Node previous;
            Node head = array[position];
            if (head.getKey().equals(key)) {
                array[position] = array[position].next;
                modCount++;
                result = true;
            } else {
                previous = head;
                head = head.next;
                while (head != null && !head.key.equals(key)) {
                    previous = head;
                    head = head.next;
                }
                if (head != null) {
                    previous.next = head.next;
                    modCount++;
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {

        return new Iterator<>() {
            private int innerCountMod = modCount;
            private  int nextCounter = 0;

            @Override
            public boolean hasNext() {
                while (array[nextCounter] == null && nextCounter < array.length - 1) {
                    nextCounter++;
                }
                return array[nextCounter] != null;
            }

            @Override
            public V next() {
                if (innerCountMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                V element = (V) array[nextCounter].value;
                nextCounter++;
                return element;
            }
        };
    }
}