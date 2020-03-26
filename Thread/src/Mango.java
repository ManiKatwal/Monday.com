import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mango {
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private Random pear = new Random ();
	private List<Integer> list1 = new ArrayList<Integer>();
	
	
	public  void stageOne() {
		synchronized(lock1){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		list1.add(pear.nextInt(100));
		}	
	}
	
	public  void stageTwo() {
		synchronized(lock2) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		list2.add(pear.nextInt(100));
		}
	}
	
	public  void process() {
		synchronized(lock2) {
			
		}
		for(int i=0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
		
	}
	
	
	
	
	private List<Integer> list2 = new ArrayList<Integer>();
	
	
	
	
	
	
	
	public void main() {
		System.out.println("Starting.........");
		
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable(){

			
			public void run() {
				process();
				
			}
			
		});
		

Thread t2 = new Thread(new Runnable(){

			
			public void run() {
				process();
			
			}
			
		});
t1.start();
t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
			
		
		
		
		
		
		long end = System.currentTimeMillis();
		System.out.println("Time Taken: " +  (end - start));
        System.out.println("List1: " +  list1.size() + "; List2:" + list2.size());
	}
}
