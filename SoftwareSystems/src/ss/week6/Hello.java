package ss.week6;

import java.util.Scanner;

public class Hello {
	public static void main(String[] args) {
		System.out.println("What is your name?");
		Scanner in = new Scanner(System.in);
		String input = in.next();
		System.out.println("Hello " + input);
		
		while (in.hasNext() && !input.equals("")) {
			input = in.next();
			System.out.println("Hello " + input);
		}
		in.close();
	}
}
