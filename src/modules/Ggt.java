package modules;

public class Ggt {

	public int ggt(int a, int b) {
		while (b != 0) {
			int h = a % b;
			a = b;
			b = h;
		}
		return a;
	}

	public int ggtRecursive(int a, int b) {
		return b == 0 ? a : ggtRecursive(b, a % b);
	}
}
