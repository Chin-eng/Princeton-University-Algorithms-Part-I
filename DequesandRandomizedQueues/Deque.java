package DequesandRandomizedQueues;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private int head;
    private int tail; 
    private Item[] arr; 
    // construct an empty deque
    public Deque() {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.arr = (Item[]) new Object[2];
    };

    // is the deque empty?
    public boolean isEmpty() {        
        return this.size == 0;
    };

    // return the number of items on the deque
    public int size(){
        return this.size;
    };

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) { 
            throw new IllegalArgumentException();
        }

        if (size() == arr.length) {
            Item[] newArr = (Item[]) new Object[this.size * 2];

            for (int i = 0; i < this.arr.length; i++) {
                newArr[i] = this.arr[(head+i) % this.arr.length];
            }

            this.arr = newArr;
            this.head = 0;
            this.tail = this.size;
        }
        if (this.head == 0) {
            this.head = (this.head - 1 + this.arr.length) % this.arr.length;
        } else {
            this.head--;
        }
        arr[head] = item;
        this.size++;
    };

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { 
            throw new IllegalArgumentException();
        }
        if (size() == arr.length) {
            Item[] newArr = (Item[]) new Object[this.size * 2];
            for (int i = 0; i < this.arr.length; i++) {
                newArr[i] = this.arr[(head+i) % this.arr.length];
            }
            this.arr = newArr;
            this.head = 0;
            this.tail = this.size;
        }
        arr[tail] = item; 
        this.tail = ((this.tail + 1) % this.arr.length);
        this.size++;
    };

    // remove and return the item from the front
    public Item removeFirst(){
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        Item item = this.arr[this.head];
        this.arr[head] = null;
        this.head = (this.head + 1) % this.arr.length;
        this.size--; 
        return item;
    };

    // remove and return the item from the back
    public Item removeLast(){
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        Item item = this.arr[this.tail];
        this.arr[this.tail] = null;
        this.tail = (this.tail - 1 + this.arr.length) % this.arr.length;
        this.size--; 
        return item;
    };

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new myIterator();
    };

    private class myIterator implements Iterator<Item> {
        private int currentIndex = Deque.this.head;
        private int capturedSize = Deque.this.size;
        private Item[] innerArray = Deque.this.arr;
        private int returnCount = 0;

        @Override
        public boolean hasNext() {
            return returnCount < capturedSize;
        }

        @Override
        public Item next() {
            if (returnCount == capturedSize) {
                throw new NoSuchElementException();
            }
            Item item = innerArray[currentIndex];
            currentIndex = (currentIndex + 1) % innerArray.length;
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
        Deque<String> d = new Deque<>();

        d.addFirst("a");
        d.addLast("b");
        d.addFirst("c");
        d.addLast("d");
        
        Iterator<String> itr = d.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next() + " ");
        }
    }
}
