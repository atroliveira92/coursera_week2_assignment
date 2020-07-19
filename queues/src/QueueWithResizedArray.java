import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueWithResizedArray<Item> implements Iterable<Item> {
    private Item[] items;
    private int N = 0;

    private int head = 0;
    private int tail = 0;

    // construct an empty randomized queue
    public QueueWithResizedArray() {
        items = (Item[]) new Object[1];
    }

    private class ListIterator implements Iterator<Item> {
        private int i = head;

        public boolean hasNext() {
            return i < tail;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = items[i++];
            return item;
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return tail - head == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return tail - head;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot add null value");
        }
        if (tail == items.length) {
            resize(2 * items.length);
        }

        items[tail++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }
        Item item = items[head];
        items[head] = null;
        head++;
        if (size() == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (!isEmpty()) {
            return items[getRandomIndex()];
        }
        return null;
    }

    private int getRandomIndex() {
        return StdRandom.uniform(head, tail);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = head, j = 0; i < tail; i++, j++) {
            copy[j] = items[i];
        }
        items = copy;
        tail = size();
        head = 0;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        QueueWithResizedArray<Integer> randomizedQueue = new QueueWithResizedArray<>();

        randomizedQueue.enqueue(0);
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        randomizedQueue.enqueue(6);
        randomizedQueue.enqueue(7);
        randomizedQueue.enqueue(8);
        randomizedQueue.enqueue(9);

        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());
        System.out.println("Item removed: " + randomizedQueue.dequeue() + " size is now: " + randomizedQueue.size());

        System.out.println("Size " + randomizedQueue.size());

        printValues(randomizedQueue);

    }

    private static void printValues(QueueWithResizedArray<Integer> randomizedQueue) {
        for (Integer i : randomizedQueue) {
            System.out.println("Item: " + i);
        }
    }

}
