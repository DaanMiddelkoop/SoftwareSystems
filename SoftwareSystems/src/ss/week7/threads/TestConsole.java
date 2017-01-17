package ss.week7.threads;

public class TestConsole extends Thread {
	public void run() {
		sum();
	}
	
	public void sum() {
		int a = Console.readInt("Thread" + this.getName() + " get number one?");
		int b = Console.readInt("Thread" + this.getName() + " get number two?");
		Console.println("Thread" + this.getName() + " " + a + " + " + b + " = " + (a + b));
	}
	
	public static void main(String[] args) {
		new Thread(new TestConsole(), "A").start();
		new Thread(new TestConsole(), "B").start();
	}
}
