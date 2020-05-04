import java.util.Scanner;
abstract class Converter {
	abstract protected double convert(double src); // �߻� �޼ҵ�
	abstract protected String srcString(); // �߻� �޼ҵ�
	abstract protected String destString(); // �߻� �޼ҵ�
	protected double ratio; // ����
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(srcString() + "�� " + destString() + "�� �ٲߴϴ�.");
		System.out.print(srcString() + "�� �Է��ϼ���>> ");
		double val = scanner.nextDouble();
		double res = convert(val);
		System.out.println("��ȯ ���: " + res + destString() + "�Դϴ�");
		scanner.close();
	}
}

class Km2Mile extends Converter {
	public Km2Mile(double ratio) {
		this.ratio = ratio;
	}
	
	@Override
	protected double convert(double src) {
		return src/ratio;
	}

	@Override
	protected String srcString() { return "Km"; }

	@Override
	protected String destString() { return "mile"; }
	
	public static void main(String args[]) {
		Km2Mile toMile = new Km2Mile(1.6); // 1������ 1.6Km
		toMile.run();
	}
}