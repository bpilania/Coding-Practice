import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ExploringConcurrency {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new LinkedList<Integer>();
		ExploringConcurrency ec = new ExploringConcurrency();
/*		Producer produce = ec.new Producer(queue);
		Consumer consume = ec.new Consumer(queue);
		
		produce.produce();
		consume.consume();
		
		ReaderWriter rw = ec.new ReaderWriter();
		Thread t1 = new Thread(rw, "reader");
		Thread t8 = new Thread(rw, "reader");
		Thread t9 = new Thread(rw, "reader");
		Thread t10 = new Thread(rw, "reader");
		Thread t11 = new Thread(rw, "reader");
		Thread t12 = new Thread(rw, "reader");
		Thread t13 = new Thread(rw, "reader");
		Thread t2 = new Thread(rw, "writer");
		Thread t3 = new Thread(rw, "writer");
		Thread t4 = new Thread(rw, "reader");
		Thread t5 = new Thread(rw, "reader");
		Thread t6 = new Thread(rw, "writer");
		Thread t7 = new Thread(rw, "reader");
		t1.start();
		t8.start();
		t9.start();
		t10.start();
		t11.start();
		t12.start();
		t13.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();*/
		
		CigaretteSmokersProblem csp = ec.new CigaretteSmokersProblem();
		csp.start();

	}

	

class Node{
	int value;
	Node next;
	public Node(int val, Node node){
		value = val;
		next = node;
	}
}

class BlockingQueue{
	Node head=null;
	Node tail=null;
	int length = 0;
	final int size = 10;
	public synchronized void add(Node node){
		try{
		if(head == null){
			head = node;
			tail = node;
			length++;
			notifyAll();
		}
		else{
			if(length == size)
				wait();
			tail.next = node;
			tail = node;
			length++;
		}
		}catch(Exception e){
			System.out.println("Exception in add");
		}
	}
	
	synchronized void remove(Node node){
		try{
		if(head == null){
			wait();
		}
		Node temp = head;
		head = head.next;
		length--;
		if(length == 9)
			notifyAll();
		System.out.println("Dequeued "+temp.value);
	}
	catch(Exception e){
		
	}
}
}

class Producer implements Runnable{
	Queue<Integer> queue = null;
	public Producer(Queue<Integer> queue){
		this.queue = queue;
	}
	Producer(){}
	public void produce(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(true){
			try{
			synchronized(queue){
				if(queue.size() == 10){
					queue.wait();
				}
			}
			
			synchronized(queue){
				queue.add(queue.size()+1);
				int size = queue.size();
				System.out.println("Produced "+size);
				if(queue.size() == 1)
					queue.notifyAll();
			}
			Thread.sleep(500);			
			}catch(InterruptedException e){
				System.out.println("In InterruptedException of Produce");
			}
		}
	}
}


class Consumer implements Runnable{
	Queue<Integer> queue = null;
	public Consumer(Queue<Integer> queue){
		this.queue = queue;
	}
	
	public void consume(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(true){
			try{
				synchronized(queue){
					if(queue.size() == 0){
						queue.wait();
					}
				}
			
			synchronized(queue){
				int num = queue.remove();
				System.out.println("Consumed "+num);
				if(queue.size() == 9)
					queue.notifyAll();
			}
			Thread.sleep(700);	
			}catch(InterruptedException e){
				System.out.println("In InterruptedException of Produce");
			}
		}
	}
	
}

class ReaderWriter implements Runnable{
	private AtomicInteger readerCount = new AtomicInteger(0);
	int value = 20;
	Semaphore write = new Semaphore(1);
	

	public void run(){
		if(Thread.currentThread().getName().equals("reader"))
			Read();
		else
			Write(value+1);

	}
	
	void Read(){
		
		while(true){
			if(readerCount.get() > 0 || write.tryAcquire()){
				System.out.println("Reader No. "+readerCount.incrementAndGet());
				if(readerCount.get() == 1)
					System.out.println("Write Lock Acquired by readers");
				System.out.println("Reading value = "+value);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readerCount.decrementAndGet();
				if(readerCount.get() == 0){
					write.release();
					System.out.println("Write Lock Released by readers");
					
				}
			break;	
			}
			
		}
		
			
	}
	
	void Write(int value){
	while(true){	
		if(write.tryAcquire()){
			System.out.println("Write Lock Acquired by writers");

			this.value = value;
			System.out.println("Writing Value = "+this.value);
			write.release();
			System.out.println("Write Lock Released by writers");
			break;
		}
	}
		
		
		
	}
}

class CigaretteSmokersProblem implements Runnable{
	boolean match = false;
	boolean paper = false;
	boolean tobacco = false;
	
	Object a = new Object();
	Object b = new Object();
	Object c = new Object();
	Object d = new Object();
	
	void start(){
		Thread t1 = new Thread(this, "agent");
		Thread t2 = new Thread(this, "a");
		Thread t3 = new Thread(this, "b");
		Thread t4 = new Thread(this, "c");
		try{
			t2.start();
			t3.start();
			t4.start();
			Thread.sleep(200);
			t1.start();
		}
		catch(Exception e){
			System.out.println("Start method Exception");
		}
	}
	
	public void run(){
		if(Thread.currentThread().getName().equals("a"))
			smokerTobacco();
		else if(Thread.currentThread().getName().equals("b"))
			smokerMatch();
		else if(Thread.currentThread().getName().equals("c"))
			smokerPaper();
		else
			agent();
	}
	
	void agent(){
		System.out.println("Entered Agent");
		while(true){
			try{
			Random rand = new Random();
			int prev = rand.nextInt(3);
			int curr = prev;
			do{
				curr = rand.nextInt(3);
			}while(curr == prev);
			
			if((curr == 0 && prev == 1) || (curr == 1 && prev == 0)){
				paper = true;
				match = true;
				System.out.println("Waking up Smoker A");
				synchronized(b){
					b.notify();
				}
			}
			if((curr == 0 && prev == 2) || (curr == 2 && prev == 0)){
				tobacco = true;
				paper = true;
				System.out.println("Waking up Smoker B");
				synchronized(c){
					c.notify();
				}
			}
			if((curr == 1 && prev == 2) || (curr == 2 && prev == 1)){
				match = true;
				tobacco = true;
				System.out.println("Waking up Smoker C");
				synchronized(d){
					d.notify();
				}
			}
			synchronized(a){
				a.wait();
			}
			}catch(Exception e){
				System.out.println("Agent Exception");
			}
		}
		
			
	}
	
	
	void smokerTobacco(){ //0
		System.out.println("Entered Smoker A");
		while(true){
			try{
			while(!(match && paper)){
				synchronized(b){
					b.wait();
				}
			}
			match = false;
			paper = false;
			Thread.sleep(500);
			System.out.println("Smoker A smoked");
			synchronized(a){
				a.notify();
			}
			}catch(Exception e){
				System.out.println("Smoker A Exception");
			}
			
		}
	}
	
	void smokerMatch(){ // 1
		System.out.println("Entered Smoker B");

		while(true){
			try{
			while(!(tobacco && paper)){
				synchronized(c){
					c.wait();
				}
			}
			tobacco = false;
			paper = false;
			Thread.sleep(500);
			System.out.println("Smoker B smoked");
			synchronized(a){
				a.notify();
			}			
			}catch(Exception e){
				System.out.println("Smoker B Exception");

			}
			
		}
	}
	
	void smokerPaper(){ // 2
		System.out.println("Entered Smoker C");

		while(true){
			try{
			while(!(match && tobacco)){
				synchronized(d){
					d.wait();
				}
			}
			match = false;
			tobacco = false;
			Thread.sleep(500);
			System.out.println("Smoker C smoked");
			synchronized(a){
				a.notify();
			}
			}catch(Exception e){
				System.out.println("Smoker C Exception");
			}
			
		}
	}
}

}