class TV {
	private int size;
	public TV(int size) { this.size = size; }
	protected int getSize() { return size; }
}

class ColorTV extends TV {
	private int nColors;
	public ColorTV(int size, int nColors) {
		super(size);
		this.nColors = nColors;
	}
	
	protected int getnColors() { return nColors; }
	
	public void printProperty() {
		System.out.println(getSize() + "��ġ " + nColors + "�÷�");
	}
}

public class IPTV extends ColorTV {
	private String ipAddr;
	public IPTV(String ipAddr, int size, int nColors) {
		super(size, nColors);
		this.ipAddr = ipAddr;
	}
	public void printProperty() {
		System.out.print("���� IPTV�� " + ipAddr + " �ּ��� ");
		super.printProperty();
	} 
	public static void main(String[] args) {
		IPTV iptv = new IPTV("192.1.1.2", 32, 2048); //"192.1.1.2" �ּҿ� 32��ġ, 2048 �÷� 
		iptv.printProperty();
	}

}
