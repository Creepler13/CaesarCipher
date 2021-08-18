package modules;

public class Vigenere {

	public Vigenere() {
	}

	public Vigenere(String key) {
		this.key = key;
	}

	private String key = "a";

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public String encodeVigenere(String text, String key) {
		return work(text, key, 1);
	}

	public String decodeVigenere(String text, String key) {
		return work(text, key, -1);
	}

	public String encodeVigenere(String text) {
		return work(text, this.key, 1);
	}

	public String decodeVigenere(String text) {
		return work(text, this.key, -1);
	}

	private String work(String text, String key, int decode) {
		int[] shifted = new int[text.length()];

		for (int i = 0; i < text.length(); i++) {
			shifted[i] = (text.codePointAt(i) - 97 + decode * (key.codePointAt(i % key.length()) - 97)) % 26;
		}

		String out = "";

		for (int i : shifted) {
			out = out + Character.toChars((i < 0 ? i + 26 : i) + 97)[0];
			;
		}

		return out;
	}

}
