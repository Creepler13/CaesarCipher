package modules;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSA {

	private BigInteger e, euler, n, d, p1, p2;

	private BigInteger zero = BigInteger.ZERO, one = BigInteger.ONE;

	public void generateKeys(BigInteger a, BigInteger b) {
		p1 = a;
		p2 = b;
		euler = (one.subtract(p1)).multiply(one.subtract(p2));
		n = p1.multiply(p2);
		setED(zero);
	}

	public void setPublicKey(int part1, int part2) {
		e = new BigInteger(part1 + "");
		n = new BigInteger(part2 + "");

	}

	public void setPrivateKey(int key, int publicPart2) {
		d = new BigInteger(key + "");
		n = new BigInteger(publicPart2 + "");

	}

	public void setPublicKey(BigInteger part1, BigInteger part2) {
		e = part1;
		n = part2;

	}

	public void setPrivateKey(BigInteger key, BigInteger publicPart2) {
		d = key;
		n = publicPart2;

	}

	public BigInteger[] getPublicKey() {
		BigInteger[] temp = { e, n };
		return temp;
	}

	public BigInteger getPrivateKey() {
		return d;
	}

	private void setED(BigInteger skipNum) {
		e = getE(skipNum);
		d = getD();
	}

	private BigInteger getE(BigInteger skipNum) {
		BigInteger e = zero;
		while (true) {
			if (e.compareTo(one) == 1 && e.compareTo(euler) == -1 && ggt(e, euler).compareTo(one) == 0
					&& e.compareTo(skipNum) == 1)
				return e;
			e = e.add(one);
		}
	}

	private BigInteger getD() {
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

	private String splitEncryptBigMath(String[] input) {
		String s = "";

		for (int i = 0; i < input.length; i++) {
			s = s + " " + bigMath(new BigInteger(input[i]), e, n);

		}

		return s.trim();

	}

	private String splitDecryptBigMath(String[] input) {
		String s = "";

		for (int i = 0; i < input.length; i++) {
			s = s + bigMath(new BigInteger(input[i]), d, n).toString().replaceFirst("1", "");
		}

		return s;

	}

	private String splitDecryptTextBigMath(String[] input) {
		String s = "";

		for (int i = 0; i < input.length; i = i + 2) {
			String one = bigMath(new BigInteger(input[i]), d, n).toString().replaceFirst("1", "");
			String two = bigMath(new BigInteger(input[i + 1]), d, n).toString();

			s = s + Character.toChars(Integer.parseInt(one + two))[0];
		}

		return s;

	}

	private String[] splitENum(String input) {

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

	private String[] splitDNum(String input) {
		return input.split(" ");
	}

	private String[] splitETextUTF8(String input) {
		String[] temp = new String[input.length() * 2];
		for (int i = 0; i < temp.length; i = i + 2) {
			String tempString = "";

			tempString = "" + input.codePointAt(i / 2);

			while (tempString.length() < 5) {
				tempString = 0 + tempString;
			}
			tempString = 1 + tempString;

			temp[i] = tempString.substring(0, 3);
			temp[i + 1] = tempString.substring(3, 6);
		}
		return temp;
	}

	private String[] splitEText(String input) {
		String[] temp = new String[input.length()];
		for (int i = 0; i < temp.length; i++) {
			String tempString = "";

			tempString = "" + input.codePointAt(i);

			tempString = 1 + tempString;

			temp[i] = tempString;

		}
		return temp;
	}

	private String[] splitDText(String input) {
		return input.split(" ");
	}

	// ---------------------- Access

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
		return splitEncryptBigMath(splitENum(number));
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
		return splitEncryptBigMath(splitENum("" + number));
	}

	/**
	 * Uses the RSA encryption to encrypt a Number
	 * <p>
	 * The encrypted String has to include the spaces. If you want to decrypt it
	 * don't remove them
	 * 
	 * @param number - the number to be encrypted as a BigInteger
	 * @return encrypted number as a String
	 */
	public String encryptNumber(BigInteger number) {
		return splitEncryptBigMath(splitENum(number.toString()));
	}

	/**
	 * Uses the RSA encryption to decrypt a Number
	 * 
	 * @param encryptedNumber - the number to be decrypted as a String
	 * @return decrypted number as a String
	 */
	public String decryptNumber(String encryptedNumber) {
		return splitDecryptBigMath(splitDNum(encryptedNumber));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String encryptText(String text) {
		return splitEncryptBigMath(splitEText(text));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String decryptText(String encryptedText) {
		return splitDecryptTextBigMath(splitDText(encryptedText));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String encryptTextFullUTF8(String text) {
		return splitEncryptBigMath(splitETextUTF8(text));
	}

	/**
	 * 
	 * WIP
	 * 
	 * @param text
	 * @return
	 */
	public String decryptTextFullUTF8(String encryptedText) {
		return splitDecryptTextBigMath(splitDText(encryptedText));
	}

}
