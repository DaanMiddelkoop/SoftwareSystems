package ss.week7.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FinegrainedIntCell implements IntCell {

	final Lock lock = new ReentrantLock();
	final Condition full = lock.newCondition();
	final Condition empty = lock.newCondition();
	private boolean isempty = true;
	private int value = 0;
	
	public void setValue(int arg) {
		lock.lock();
		try {
			while (!isempty) {
				try {
					empty.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			value = arg;
			isempty = false;
			full.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public int getValue() {
		lock.lock();
		try {
			while (isempty) {
				try {
					full.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			empty.signal();
			isempty = true;
			return value;
		} finally {
			lock.unlock();
		}
	}
}
