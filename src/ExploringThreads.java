
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
			public int a = 10;
			public void run(){
				if(Thread.currentThread().getName().equals("Thread1"))
					sum();
				else if(Thread.currentThread().getName().equals("Thread2"))
					sub();
			}
			
			public synchronized void sum()
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"In Sum");
				sub();
			}
			
			public synchronized void sub()
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"In Sub");
				sum();
			}
		}
