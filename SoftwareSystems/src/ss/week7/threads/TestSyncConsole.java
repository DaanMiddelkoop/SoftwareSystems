package ss.week7.threads;

import java.util.concurrent.locks.*;

public class TestSyncConsole extends Thread {
	private final ReentrantLock lock = new ReentrantLock();

	public void run() {
		sum();
	}
	
	public void sum() {
		
		lock.lock();
		try {
			int a = SyncConsole.readInt("Thread" + this.getName() + " get number one?");
			int b = SyncConsole.readInt("Thread" + this.getName() + " get number two?");
			SyncConsole.println("Thread" + this.getName() + " " + a + " + " + b + " = " + (a + b));
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		new Thread(new TestSyncConsole(), "A").start();
		new Thread(new TestSyncConsole(), "B").start();
	}
}
