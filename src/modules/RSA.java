package modules;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSA {

	private BigInteger e, euler, n, d, p1, p2;

	private BigInteger zero = BigInteger.ZERO, one = BigInteger.ONE;

	private void generateKeysP(BigInteger a, BigInteger b) {
		p1 = a;
		p2 = b;
		euler = (one.subtract(p1)).multiply(one.subtract(p2));
		n = p1.multiply(p2);
		setED(zero);
	}

	private void setED(BigInteger skipNum) {
		e = makeE(skipNum);
		d = makeD();
	}

	private BigInteger makeE(BigInteger skipNum) {
		BigInteger e = zero;
		while (true) {
			if (e.compareTo(one) == 1 && e.compareTo(euler) == -1 && ggt(e, euler).compareTo(one) == 0
					&& e.compareTo(skipNum) == 1)
				return e;
			e = e.add(one);
		}
	}

	private BigInteger makeD() {
		BigInteger d = zero;
		BigInteger s = zero;

		while (!(d.compareTo(euler) <= 0 && e.multiply(d).mod(euler).compareTo(one) == 0)) {
			d = (s.multiply(euler).add(one)).divide(e);
			s = s.add(one);

		}

		return d;
	}

	public BigInteger ggt(BigInteger a, BigInteger b) {

		while (!(b.compareTo(zero) == 0)) {
			BigInteger h = a.mod(b);
			a = b;
			b = h;
		}

		return a;

	}

	private BigInteger bigMath(BigInteger a, BigInteger b, BigInteger c) {
		BigInteger Bi = a;
		Bi = Bi.pow(b.intValue());
		Bi = Bi.mod(c);
		return Bi;
	}

	private String encrypt(String[] input) {
		String s = "";

		for (int i = 0; i < input.length; i++) {
			s = s + " " + bigMath(new BigInteger(input[i]), e, n);

		}

		return s.trim();

	}

	private String decrypt(String[] input) {
		String s = "";

		for (int i = 0; i < input.length; i++) {
			s = s + bigMath(new BigInteger(input[i]), d, n).toString().replaceFirst("1", "");
		}

		return s;

	}

	private String decryptText(String[] input, boolean UTF8) {
		int cutLength = UTF8 ? 6 : 4;
		String temp = decrypt(input), s = "";

		for (int i = 0; i < temp.length(); i = i + cutLength) {
			s = s + Character.toChars(Integer.parseInt(temp.substring(i + 1, i + cutLength)))[0];

		}

		return s;

	}

	private String[] splitENum(String input) {
		// split number to be encrypted and not be bigger than n
		ArrayList<String> list = new ArrayList<>();
		String temp = "1";
		for (int i = 0; i < input.length(); i++) {
			if (new BigInteger(temp + input.charAt(i)).compareTo(n) > 0) {
				list.add(temp);
				temp = "1";
			}
			temp = temp + input.charAt(i);
		}
		list.add(temp);
		return list.toArray(new String[list.size()]);

	}

	private String[] splitText(String input, boolean UTF8) {
		// split text to be encrypted and not be bigger than n
		int codeLength = UTF8 ? 5 : 3;
		String temp = "";
		for (int i = 0; i < input.length(); i++) {
			String tempString = "";

			tempString = "" + input.codePointAt(i);

			while (tempString.length() < codeLength) {
				tempString = "0" + tempString;
			}

			tempString = "1" + tempString;

			temp = temp + tempString;

		}

		// example: temp = 100510321078
		return splitENum(temp);
	}

	private String[] splitEncryted(String input) {
		return input.split(" ");
	}

	// ---------------------- Access

	/**
	 * 
	 * Compacts the encrypted String of numbers to characters
	 * <p>
	 * It has to to decompacted again to decrypt it
	 * 
	 * @param encrytedString - encrypted string of numbers to be Compacted
	 * @return - returns the encrypted String but compacted to characters
	 */
	public String compact(String encrytedString) {
		String temp = "";

		for (String s : encrytedString.split(" ")) {

			for (int i = 0; i < s.length(); i = i + 4) {
				String subString = "";
				if ((i + 4) <= s.length()) {
					subString = s.substring(i, i + 4);
				} else {
					subString = s.substring(i, s.length());
				}

				temp = temp + Character.toChars(Integer.parseInt("1" + subString))[0];
			}
			temp = temp + " ";
		}
		return temp;
	}

	/**
	 * 
	 * decompacts the compacted encrypted String
	 * 
	 * @param encrytedString - Compacted encrypted String
	 * @return - returns the decompacted encrypted String that can be decrypted
	 */
	public String decompact(String compactedString) {
		String temp = "";

		for (String s : compactedString.split(" ")) {

			for (int i = 0; i < s.length(); i++) {
				temp = temp + ("" + s.codePointAt(i)).replaceFirst("1", "");
			}
			temp = temp + " ";
		}
		return temp.trim();
	}

	/**
	 * Uses the RSA encryption to encrypt a Number
	 * <p>
	 * The encrypted String has to include the spaces. If you want to decrypt it
	 * don't remove them
	 * 
	 * @param number - the number to be encrypted as a String
	 * @return encrypted number as a String
	 */

	public String encryptNumber(String number) {
		return encrypt(splitENum(number));
	}

	/**
	 * Uses the RSA encryption to encrypt a Number
	 * <p>
	 * The encrypted String has to include the spaces. If you want to decrypt it
	 * don't remove them
	 * 
	 * @param number - the number to be encrypted as a Integer
	 * @return encrypted number as a String
	 */
	public String encryptNumber(int number) {
		return encrypt(splitENum("" + number));
	}

	/**
	 * Uses the RSA encryption to encrypt a Number
	 * <p>
	 * The encrypted String has to include the spaces. If you want to decrypt it
	 * don't remove them
	 * 
	 * @param number - the number to be encrypted as a {@link BigInteger}
	 * @return encrypted number as a String
	 */
	public String encryptNumber(BigInteger number) {
		return encrypt(splitENum(number.toString()));
	}

	/**
	 * Uses the RSA encryption to decrypt a Number
	 * 
	 * @param encryptedNumber - the number to be decrypted as a String
	 * @return decrypted number as a String
	 */
	public String decryptNumber(String encryptedNumber) {
		return decrypt(splitEncryted(encryptedNumber));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String encryptText(String text) {
		return encrypt(splitText(text, false));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String decryptText(String encryptedText) {
		return decryptText(splitEncryted(encryptedText), false);
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String encryptTextFullUTF8(String text) {
		return encrypt(splitText(text, true));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String decryptTextFullUTF8(String encryptedText) {
		return decryptText(splitEncryted(encryptedText), true);
	}

	/**
	 * Generates the private and public key with the given prime numbers
	 * 
	 * @param a - prime number as Integer
	 * @param b - prime number as Integer
	 */
	public void generateKeys(int a, int b) {
		generateKeysP(new BigInteger(a + ""), new BigInteger(b + ""));
	}

	/**
	 * Generates the private and public key with the given prime numbers
	 * 
	 * @param a - prime number as {@link BigInteger}
	 * @param b - prime number as {@link BigInteger}
	 */
	public void generateKeys(BigInteger a, BigInteger b) {
		generateKeysP(a, b);
	}

	/**
	 * Sets the Publickey
	 * 
	 * 
	 * @param part1 - first part of the Public key (e) as Integer
	 * @param part2 - second part of the Public key (n) as Integer
	 */
	public void setPublicKey(int part1, int part2) {
		e = new BigInteger(part1 + "");
		n = new BigInteger(part2 + "");

	}

	/**
	 * Sets the Publickey
	 * 
	 * 
	 * @param part1 - first part of the Public key (e) as {@link BigInteger}
	 * @param part2 - second part of the Public key (n) as {@link BigInteger}
	 */
	public void setPublicKey(BigInteger part1, BigInteger part2) {
		e = part1;
		n = part2;

	}

	/**
	 * Sets the Privatekey
	 * 
	 * 
	 * @param key         - the private key (d) as Integer
	 * @param publicPart2 - second part of the Public key (n) as Integer
	 */
	public void setPrivateKey(int key, int publicPart2) {
		d = new BigInteger(key + "");
		n = new BigInteger(publicPart2 + "");

	}

	/**
	 * Sets the Privatekey
	 * 
	 * 
	 * @param key         - the private key (d) as {@link BigInteger}
	 * @param publicPart2 - second part of the Public key (n) as {@link BigInteger}
	 */
	public void setPrivateKey(BigInteger key, BigInteger publicPart2) {
		d = key;
		n = publicPart2;

	}

	/**
	 * returns the Publickey tupel
	 * 
	 * @return - the Publickey tupel as BigInteger
	 */
	public BigInteger[] getPublicKey() {
		BigInteger[] temp = { e, n };
		return temp;
	}

	/**
	 * returns the Privatekey
	 * 
	 * @return - the Privatekey as {@link BigInteger}
	 */
	public BigInteger getPrivateKey() {
		return d;
	}

}
