import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {

		Krypto k = new Krypto();

		k.rsa.setPrime(43, 67);

		String number = "1512525561111";
		String encodedrsa = k.rsa.encrypt(number);
		String decodedrsa = k.rsa.decrypt(encodedrsa);

		System.out.println(number);
		System.out.println(encodedrsa);
		System.out.println(decodedrsa);

		k.vigenere.setKey("senf");

		String klarText = "musik ist der atem der seele";
		String encoded = k.vigenere.encodeVigenere(klarText);
		String decoded = k.vigenere.decodeVigenere(encoded);

		System.out.println(klarText);
		System.out.println(encoded);
		System.out.println(decoded);

		k.caesar.setKey(1233);

		klarText = "test";
		encoded = k.caesar.encodeCaesar(klarText);
		decoded = k.caesar.decodeCaesar(encoded);

		System.out.println(klarText);
		System.out.println(encoded);
		System.out.println(decoded);

	}

}
