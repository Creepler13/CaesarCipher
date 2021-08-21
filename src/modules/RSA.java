package modules;

import java.math.BigDecimal;

public class RSA {

	private BigDecimal e, euler, n, d, p1, p2;

	private BigDecimal zero = new BigDecimal(0), one = new BigDecimal(1);

	public void setPrime(int a, int b) {
		p1 = new BigDecimal(a);
		p2 = new BigDecimal(b);
		euler = (one.subtract(p1)).multiply(one.subtract(p2));
		n = p1.multiply(p2);
		setED(zero);
		System.out.println(e + " " + d);
	}

	private void setED(BigDecimal skipNum) {
		e = getE(skipNum);
		d = getD();
	}

	private BigDecimal getE(BigDecimal skipNum) {
		BigDecimal e = zero;
		while (true) {
			if (e.compareTo(one) == 1 && e.compareTo(euler) == -1 && ggt(e, euler).compareTo(one) == 0
					&& e.compareTo(skipNum) == 1)
				return e;
			e = e.add(one);
		}
	}

	private BigDecimal getD() {
		BigDecimal d = zero;
		BigDecimal s = one;
		while (true) {
			d = s.multiply(euler).add(one).divide(e);
			s = s.add(one);
			if (d.compareTo(euler) <= 0 && e.multiply(d).remainder(euler).compareTo(one) == 0)
				return d;
			if (d.compareTo(euler) == 1)
				setED(e);

		}
	}

	public BigDecimal ggt(BigDecimal a, BigDecimal b) {

		while (!(b.compareTo(zero) == 0)) {
			BigDecimal h = a.remainder(b);
			a = b;
			b = h;
		}

		return a;

	}

	public BigDecimal encrypt(BigDecimal number) {
		if (n.compareTo(number) < 0)
			System.out.println("warning input " + number + " is to great");
		return bigMath(number, e, n);
	}

	public BigDecimal decrypt(BigDecimal number) {
		return bigMath(number, d, n);
	}

	public BigDecimal encrypt(int number) {
		BigDecimal num = new BigDecimal(number);
		if (n.compareTo(num) < 0)
			System.out.println("warning input " + num + " is to great");
		return bigMath(num, e, n);
	}

	public BigDecimal decrypt(int number) {
		return bigMath(new BigDecimal(number), d, n);
	}

	private BigDecimal bigMath(BigDecimal a, BigDecimal b, BigDecimal c) {

		BigDecimal Bi = a;
		Bi = Bi.pow(b.intValue());
		Bi = Bi.remainder(c);
		return Bi;
	}

	private String bigMathText(String text, BigDecimal b, BigDecimal c) {
		int[] temp = new int[text.length()];
		for (int i = 0; i < text.length(); i++) {
			int ut = text.codePointAt(i);
			temp[i] = ut < 100 ? ut < 10 ? ut * 100 : ut * 10 : ut;
		}

	}

}
