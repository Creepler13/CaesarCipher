
public class Main {

	public static void main(String[] args) {

		Caesar c = new Caesar(1233);
		
		String klarText = "test";
		String encoded = c.encodeCaesar(klarText);
		String decoded = c.decodeCaesar(encoded);

		System.out.println(klarText);
		System.out.println(encoded);
		System.out.println(decoded);

	}

}
