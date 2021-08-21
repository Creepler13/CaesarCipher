public class Main {

	public static void main(String[] args) {

		Krypto k = new Krypto();

		k.rsa.setPrime(43, 67);

		String number = "15125255600000001111";
		String encodedrsa = k.rsa.encryptNumber(number);
		String decodedrsa = k.rsa.decryptNumber(encodedrsa);

		System.out.println("\nRSA Number");
		System.out.println(number);
		System.out.println(encodedrsa);
		System.out.println(decodedrsa);

		String text = "Tesst";
		String encodedrsaText = k.rsa.encryptText(text);
		String decodedrsaText = k.rsa.decryptText(encodedrsaText);

		System.out.println("\nRSA Text");
		System.out.println(text);
		System.out.println(encodedrsaText);
		System.out.println(decodedrsaText);

		k.vigenere.setKey("senf");

		String klarText = "musik ist der atem der seele";
		String encoded = k.vigenere.encodeVigenere(klarText);
		String decoded = k.vigenere.decodeVigenere(encoded);

		System.out.println("\nVigenere");
		System.out.println(klarText);
		System.out.println(encoded);
		System.out.println(decoded);

		k.caesar.setKey(1233);

		klarText = "test";
		encoded = k.caesar.encodeCaesar(klarText);
		decoded = k.caesar.decodeCaesar(encoded);

		System.out.println("\nCaesar");
		System.out.println(klarText);
		System.out.println(encoded);
		System.out.println(decoded);

	}

}
