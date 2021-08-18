package modules;

public class Caesar {

	public Caesar() {
	}

	public Caesar(int key) {
		this.key = key;
	}

	private int key = 0;

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}

	public String encodeCaesar(String text, int key) {
		return work(text, key);
	}

	public String decodeCaesar(String text, int key) {
		return work(text, -key);
	}

	public String encodeCaesar(String text) {
		return work(text, this.key);
	}

	public String decodeCaesar(String text) {
		return work(text, -this.key);
	}

	private String work(String inp, int key) {
		int[] shifted = new int[inp.length()];

		for (int i = 0; i < shifted.length; i++) {
			shifted[i] = (inp.codePointAt(i) + key - 97) % 26;
		}

		String out = "";

		for (int i : shifted) {
			out = out + Character.toChars((i < 0 ? i + 26 : i) + 97)[0];
			;
		}

		return out;
	}

}
