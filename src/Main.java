
public class Main {

	public static void main(String[] args) {

		Krypto k = new Krypto();

		k.caesar.setKey(1233);

		String klarText = "test";
		String encoded = k.caesar.encodeCaesar(klarText);
		String decoded = k.caesar.decodeCaesar(encoded);

		System.out.println(klarText);
		System.out.println(encoded);
		System.out.println(decoded);

	}

}
