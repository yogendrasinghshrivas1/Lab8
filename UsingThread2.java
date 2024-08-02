package Lab8;

public class UsingThread2 {
	
	    private static volatile int counter = 1;
	    private final int limit;

	    public UsingThread2(int limit) {
	        this.limit = limit;
	    }

	    public synchronized void printOddNumber() {
	        while (counter <= limit) {
	            if (counter % 2 == 1) {
	                System.out.println(Thread.currentThread().getName() + ": " + counter);
	                counter++;
	                notifyAll();
	            } else {
	                try {
	                    wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    public synchronized void printEvenNumber() {
	        while (counter <= limit) {
	            if (counter % 2 == 0) {
	                System.out.println(Thread.currentThread().getName() + ": " + counter);
	                counter++;
	                notifyAll();
	            } else {
	                try {
	                    wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    public static void main(String[] args) {
	        UsingThread2 printer = new UsingThread2(10);

	        Thread t1 = new Thread(() -> printer.printOddNumber());
	        t1.setName("Odd");

	        Thread t2 = new Thread(() -> printer.printEvenNumber());
	        t2.setName("Even");

	        t1.start();
	        t2.start();
	    }
}



