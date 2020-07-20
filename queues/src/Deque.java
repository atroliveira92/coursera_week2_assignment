import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        public Item item;
        public Node next;
        public Node previous;
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        size++;
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.previous = null;

        if (oldFirst != null) {
            oldFirst.previous = first;
            first.next = oldFirst;

            if (last == null) {
                last = oldFirst;
            }
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (isEmpty()) {
            addFirst(item);
            return;
        }
        size++;
        if (last == null) {
            last = first;
        }
        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;

        oldLast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        size--;
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        size--;
        Item item = last.item;
        last = last.previous;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();

        deque.addFirst(0);
        deque.addLast(1);
        deque.addFirst(-1);
        deque.addLast(2);
        deque.addFirst(-2);
        deque.addLast(3);
        deque.addFirst(-3);
        deque.addLast(4);
        deque.addFirst(-4);

        System.out.println("remove first  " + deque.removeFirst());

        System.out.println("remove last  " + deque.removeLast());

        System.out.println("remove first  " + deque.removeFirst());

        System.out.println("remove last  " + deque.removeLast());

        System.out.println("remove first  " + deque.removeFirst());

        for (Integer i : deque) {
            System.out.println(i);
        }

        System.out.println("Size " + deque.size());
    }
}
