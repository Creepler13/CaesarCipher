
public class Main {

	public static void main(String[] args) {


		
		Krypto k = new Krypto();

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
