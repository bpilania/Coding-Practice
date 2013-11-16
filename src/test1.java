import java.util.LinkedList;

public class test1<T> {
    LinkedList<T> queue;
    final int limit;

    public static void main(String args[]) {

        final int numItems = 100;
        test1<Integer> queue = new test1<Integer>(numItems);

    }

    public test1() {
        // TODO Auto-generated constructor stub
        queue = new LinkedList<T>();
        limit = 10;
    }

    public T get() {
        T t;
        synchronized (queue) {
            while (isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean empty = isFull();
            t = queue.poll();
            if (empty) {
                queue.notifyAll();
            }
        }
        return t;
    }

    public synchronized void put(T t) {
    /*    
         * while (isFull()) {
         * 
         * try { queue.wait(); } catch (InterruptedException e) { // TODO Auto-generated 
         * catch block e.printStackTrace(); } }
         */
        boolean full = isEmpty();
        queue.add(t);
        /*
         * if (full) { queue.notifyAll(); }
         */
    }

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    private boolean isFull() {
        return queue.size() == limit;

    }

    private boolean isEmpty() {
        return queue.isEmpty();
    }

}