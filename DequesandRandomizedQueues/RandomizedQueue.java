package DequesandRandomizedQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

// import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        this.arr = (Item[]) new Object[2];
    };

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    };

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    };

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.size == this.arr.length) resize(this.size * 2);
        this.arr[size] = item;
        size++;
    };

    private void resize(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.arr[i];
        }
        this.arr = newArr;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.size == 0) throw new NoSuchElementException();
        int randomIndex = randomIndex();
        Item item = this.arr[randomIndex];

        this.arr[randomIndex] = this.arr[this.size-1];
        this.arr[this.size - 1] = null;

        this.size--;

        if (this.size > 0 && this.size == this.arr.length / 4) resize(this.arr.length/2);
        return item;
    };

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.size == 0) throw new NoSuchElementException();
        int randomIndex = randomIndex();
        return this.arr[randomIndex];
    }

    private int randomIndex() {
        return StdRandom.uniformInt(this.size);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new myIterator(); 
    }

    private class myIterator implements Iterator<Item> {
        private Item[] queue = RandomizedQueue.this.arr;
        private Item[] temp;
        private int returnCount = 0;
        private int capturedSize = RandomizedQueue.this.size;
        private int currentIndex = 0;

        private myIterator() {
            this.temp = (Item[]) new Object[capturedSize];
            for (int i = 0; i < this.capturedSize; i++) {
                temp[i] = queue[i];
            }

            for (int i = capturedSize - 1; i > 0; i--) {
                int j = StdRandom.uniformInt(i+1);
                Item tempValue = this.temp[i];
                this.temp[i] = this.temp[j];
                this.temp[j] = tempValue;
            }
        }

        @Override
        public boolean hasNext() {
            return returnCount < capturedSize;
        }

        @Override
        public Item next() {
            if (returnCount == capturedSize) throw new NoSuchElementException();
            Item item = this.temp[currentIndex];
            currentIndex++;
            returnCount++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
        String value = rq.dequeue();
        System.out.println(value + " ");

        Iterator<String> itr = rq.iterator();

        while (itr.hasNext()) {
             System.out.println(itr.next());
        }
    }
}