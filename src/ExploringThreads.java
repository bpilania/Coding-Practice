import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
<<<<<<< HEAD


		public class ExploringThreads {
			
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				threadexample a1 = new threadexample();
				Thread t1 = new Thread(a1, "Thread1");
				Thread t2 = new Thread(a1,"Thread2");
				t1.start();
				t2.start();
			}
		}
		
		class threadexample implements Runnable{
			public int a = 0;
			
			
			public void run(){
				System.out.println(Thread.currentThread().getName()+" entered in run");
				if(Thread.currentThread().getName().equals("Thread1"))
					sum();
				else if(Thread.currentThread().getName().equals("Thread2"))
					sub();
			}
			
			public void sum()
			{
				Lock lock1 = new ReentrantLock();
				lock1.lock();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"In Sum");
				sub();
				lock1.unlock();
			}
			
			public void sub()
			{
				Lock lock2 = new ReentrantLock();
				lock2.lock();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"In Sub");
				sum();
				
				lock2.unlock();
			}


public class ExploringThreads {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		threadexample a1 = new threadexample();
		Thread t1 = new Thread(a1, "Thread1");
		Thread t2 = new Thread(a1, "Thread2");
		t1.start();
		t2.start();
	}
}

class threadexample implements Runnable {
	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();

	public void run() {
		System.out
				.println(Thread.currentThread().getName() + " entered in Run");
		if (Thread.currentThread().getName().equals("Thread1"))
			sum();
		else if (Thread.currentThread().getName().equals("Thread2"))
			sub();
	}

	public void sum() {
		lock1.lock();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "In Sum");
		sub();
		lock1.unlock();
	}

	public void sub() {
		lock2.lock();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		System.out.println(Thread.currentThread().getName() + "In Sub");
		sum();
		lock2.unlock();
	}
}
