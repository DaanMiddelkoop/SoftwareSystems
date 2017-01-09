package ss.week6;

import java.util.Scanner;

public class Words {

	public static void main(String[] args) {
		System.out.println("Give a sentence");
		Scanner in = new Scanner(System.in);
		int i = 1;
		String input = in.next();
		if (input.equals("")) {
			in.close();
			return;
		}
		
		while (!input.equals("end")) {
			i++;
			
			System.out.println("Word " + i + ": " + input);
			input = in.next();
		}
		in.close();
	}

}
