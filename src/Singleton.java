
public class Singleton implements Runnable{

	/**
	 * @param args
	 */
	static SingletonHelper sh = null;
	static SingletonHelper sh1 = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton st = new Singleton();
		Thread t1 = new Thread(st,"t1");
		Thread t2 = new Thread(st,"t2");
		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(sh);
		System.out.println(sh1);
	}
	
	public void run(){
		if(Thread.currentThread().getName().equals("t1"))
			sh = SingletonHelper.getInstance();
		else
			sh1 = SingletonHelper.getInstance();
			
	}

}

class SingletonHelper{
	static SingletonHelper sh  = null;
	
	private SingletonHelper(){}
	
	static SingletonHelper getInstance(){
		synchronized(SingletonHelper.class){
		if(sh == null)
			sh = new SingletonHelper();
		}
		return sh;
	}
	
	
}