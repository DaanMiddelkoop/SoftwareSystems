package ss.week6.dictionaryattack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DictionaryAttack {
	private Map<String, String> passwordMap;
	private Map<String, String> hashDictionary;

	
	public DictionaryAttack() {
		passwordMap = new HashMap<String, String>();
		hashDictionary = new HashMap<String, String>();
	}
	/**
	 * Reads a password file. Each line of the password file has the form:
	 * username: encodedpassword
	 * 
	 * After calling this method, the passwordMap class variable should be
	 * filled with the content of the file. The key for the map should be
	 * the username, and the password hash should be the content.
	 * @param filename
	 */
	public void readPasswords(String filename) throws IOException {
		try {
			Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
			while (scan.hasNextLine()) {
				String[] part = scan.nextLine().split(": ");
				passwordMap.put(part[0], part[1]);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Given a password, return the MD5 hash of a password. The resulting
	 * hash (or sometimes called digest) should be hex-encoded in a String.
	 * @param password
	 * @return
	 */
	public String getPasswordHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//System.out.println(byteArrayToHex(md.digest(password.getBytes())));
			return byteArrayToHex(md.digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	  * Converts an array of bytes to a hexadecimal encoded string 
	  * @param a 
	  * @return 
	  */ 
	public static String byteArrayToHex(byte[] input) { 
		StringBuilder sb = new StringBuilder(input.length * 2); 
		for (byte b: input) { 
			sb.append(String.format("%02x", b & 0xff)); 
		} 
		return sb.toString(); 
	} 
	 
	 
	/**
	 * Checks the password for the user the password list. If the user
	 * does not exist, returns false.
	 * @param user
	 * @param password
	 * @return whether the password for that user was correct.
	 */
	public boolean checkPassword(String user, String password) {
        return ((passwordMap.get(user)).equals(getPasswordHash(password)) && getPasswordHash(password) != null);
	}

	/**
	 * Reads a dictionary from file (one line per word) and use it to add
	 * entries to a dictionary that maps password hashes (hex-encoded) to
     * the original password.
	 * @param filename filename of the dictionary.
	 */
    public void addToHashDictionary(String filename) {
        try {
        	Scanner scan = new Scanner(new BufferedReader(new FileReader(filename)));
		    while (scan.hasNextLine()) {
		    	String next = scan.nextLine();
		    	//String hashNext = getPasswordHash(next);
		    	System.out.println(next);
		    	System.out.println(getPasswordHash(next));
		    	hashDictionary.put(getPasswordHash(next), next);
		    }
		    scan.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
    }
	/**
	 * Do the dictionary attack.
	 */
	public void doDictionaryAttack() {
		for (String user : passwordMap.keySet()) {
			String password = hashDictionary.get(passwordMap.get(user));
			if (password != null) {
				System.out.println("Password of " + user + " is: " + password);
			}
		}
	}
	public static void main(String[] args) {
		DictionaryAttack da = new DictionaryAttack();
		try { 
		da.readPasswords("LeakedPasswords.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		da.addToHashDictionary("Dictionary.txt");
		da.doDictionaryAttack();
	}

}
