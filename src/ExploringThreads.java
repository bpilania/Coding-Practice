import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



		public class ExploringThreads {
			static int data = 0;
			static int readers = 0;
			static Object obj1 = new Object();
			static Lock read = new ReentrantLock();
			static Lock write = new ReentrantLock();
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				/*threadexample a1 = new threadexample();
				Thread t1 = new Thread(a1, "Thread1");
				Thread t2 = new Thread(a1,"Thread2");
				t1.start();
				t2.start();
				*/
				/*Producer prod = new Producer();
				Consumer cons = new Consumer();
				Thread t1 = new Thread(prod, "Thread1");
				Thread t2 = new Thread(cons,"Thread2");
				t1.start();
				t2.start();*/
				
				Reader reader = new Reader();
				Writer writer = new Writer();
				Thread t1 = new Thread(reader, "Thread1");
				Thread t2 = new Thread(reader,"Thread2");
				Thread t3 = new Thread(reader, "Thread3");
				Thread t4 = new Thread(reader,"Thread4");
				Thread t5 = new Thread(writer, "Thread5");
				Thread t6 = new Thread(writer,"Thread6");
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				t5.start();
				t6.start();
				}
		
		static class Producer implements Runnable {
	
			public void run() {
				produce();
			}
	
			void produce() {
				while (true) {
					
						if (data < 5){
							synchronized(obj1){
							System.out.println("Producing Data. Now Data is "+data++);
							obj1.notifyAll();
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							try {
								System.out.println("Producer inactive");
								synchronized(obj1){
								obj1.wait();
								}
								System.out.println("Producer active");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
				}
			}
		}
			
			static class Consumer implements Runnable{
				
				public void run(){
					consume();
				}
				void consume() {
					while (true) {
						
							if (data > 0){
								synchronized(obj1){
								System.out.println("Consuming Data. Now Data is "+data--);
								obj1.notifyAll();
								}
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else{
								try {
									System.out.println("Consumer Inactive");
									synchronized(obj1){
									obj1.wait();
									}
									System.out.println("Consumer Active");
	
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						
					}
				}
			}
		
		
		static class Reader implements Runnable{
			public void run(){
				readData();
			}
			
			void readData(){
				
				
				if(readers > 0){
					readers++;
					System.out.println(Thread.currentThread().getName()+" in Read. Data is: "+data);
					readers--;
				}
				else if(write.tryLock()){
					read.tryLock();
					readers++;
					System.out.println(Thread.currentThread().getName()+" in Read. Data is: "+data);
					readers--;
					while(true){
					if(readers == 0){
						read.unlock();
						break;
					}
					}
				}
				else{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
					
			}
		}
		
		static class Writer implements Runnable{
			public void run(){
				writeData();
			}
			
			void writeData(){
				
				
				boolean readLock = read.tryLock();
				boolean writeLock = write.tryLock();
				if(readLock && writeLock){
					System.out.println(Thread.currentThread().getName()+" in Write. New data is "+data++);
					write.unlock();
					read.unlock();
					notifyAll();
				}
				else{
					if(readLock)
						read.unlock();
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
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
			Lock lock1 = new ReentrantLock();
			Lock lock2 = new ReentrantLock();
			public synchronized static void sum()
			{
				
				//lock1.lock();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"In Sum");
				
				sub();
				//lock1.unlock();
			}
			
			public synchronized static void sub()
			{
				
				//lock2.lock();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"In Sub");
				sum();
				
				//lock2.unlock();
			}

}

