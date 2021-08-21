package modules;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RSA {

	private BigInteger e, euler, n, d, p1, p2;

	private BigInteger zero = BigInteger.ZERO, one = BigInteger.ONE;

	public void setPrime(int a, int b) {
		p1 = new BigDecimal(a).toBigInteger();
		p2 = new BigDecimal(b).toBigInteger();
		euler = (one.subtract(p1)).multiply(one.subtract(p2));
		n = p1.multiply(p2);
		setED(zero);
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

	public String encrypt(String number) {
		return splitEncryptBigMath(number);
	}

	public String decrypt(String number) {
		return splitDecryptBigMath(number);
	}

	private BigInteger bigMath(BigInteger a, BigInteger b, BigInteger c) {
		BigInteger Bi = a;
		Bi = Bi.pow(b.intValue());
		Bi = Bi.mod(c);
		return Bi;
	}

	private String splitEncryptBigMath(String input) {

		String[] tempSplit = new String[(int) Math.ceil((double) input.length() / 3)];

		for (int i = 0; i < tempSplit.length; i++) {
			tempSplit[i] = "" + input.charAt(i * 3);
			if (i * 3 + 1 < input.length())
				tempSplit[i] = tempSplit[i] + input.charAt(i * 3 + 1);
			if (i * 3 + 2 < input.length())
				tempSplit[i] = tempSplit[i] + input.charAt(i * 3 + 2);
		
		}

		BigInteger[] splitInput = new BigInteger[tempSplit.length];
		for (int i = 0; i < tempSplit.length; i++) {

			splitInput[i] = bigMath(new BigInteger(tempSplit[i]), e, n);
		}

		String s = "";

		for (BigInteger bigInteger : splitInput) {
			s = s + " " + bigInteger;
		}

		return s.trim();

	}

	private String splitDecryptBigMath(String input) {
		String[] splitInputS = input.split(" ");
		BigInteger[] splitInput = new BigInteger[splitInputS.length];
		for (int i = 0; i < splitInputS.length; i++) {
			splitInput[i] = bigMath(new BigInteger(splitInputS[i]), d, n);
		}

		String s = "";

		for (BigInteger bigInteger : splitInput) {
			s = s + bigInteger;
		}

		return s;

	}

}
