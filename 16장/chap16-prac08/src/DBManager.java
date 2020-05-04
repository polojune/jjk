import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class DBManager {
	static Connection conn;
	static Statement stmt = null;
	static Scanner sin = new Scanner(System.in);
	public static void main (String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL ����̹� �ε�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root","1234"); // JDBC ����
			System.out.println("DB ���� �Ϸ�");
			stmt = conn.createStatement(); // SQL�� ó���� Statement ��ü ����
			printTable(stmt); // ��� ������ ���
			int choice = 1;
			while (choice != 4) {
				System.out.print("�߰�(1),����(2), ����(3), ������(4)>>");
				choice = Integer.parseInt(sin.nextLine());
				switch (choice) {
					case 1:
						add();
						break;
					case 2:
						delete();
						break;
					case 3:
						update();
						break;
					case 4:
						break;
					default:
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("SQL ���� ����");
		}
	}
	private static void update() {
		try {
			System.out.print("������ �Ӽ� �̸�>>");
			String colName =  sin.nextLine();
			System.out.print("������ �Ӽ��� ���簪 >>");
			String oldValue =  sin.nextLine();
			System.out.print("������ �Ӽ��� ���ο� �� >>");
			String newValue =  sin.nextLine();
			if (colName.equals("id")) 
				stmt.executeUpdate("update book set " + colName + "="+ newValue + " where " + colName + "="+ oldValue + ""); //������ ����
			else
				stmt.executeUpdate("update book set " + colName + "='"+ newValue + "' where " + colName + "='"+ oldValue +"'"); //������ ����
			printTable(stmt);
		} catch (SQLException e) {
			System.out.println("���ڵ� ���� ����");
		}	
	}
	private static void delete() {
		try {
			System.out.print("id>>");
			int id =  Integer.parseInt(sin.nextLine());
			stmt.executeUpdate("delete from book where id="+id); // ���ڵ� ����
			printTable(stmt);
		} catch (SQLException e) {
			System.out.println("���ڵ� ���� ����");
		}
	}
	private static void add() {
		try {
			System.out.print("id>>");
			int id =  Integer.parseInt(sin.nextLine());
			System.out.print("å �̸�>>");
			String title = sin.nextLine();
			System.out.print("���ǻ�>>");
			String publisher = sin.nextLine();
			System.out.print("�۰�>>");
			String author = sin.nextLine();
			stmt.executeUpdate("insert into book (id, title, publisher, author) values("+ id++ +",'" + title + "','" + publisher + "','" + author + "');"); // ���ڵ� �߰�
			printTable(stmt);
		} catch (SQLException e) {
			System.out.println("���ڵ� �߰� ����");
		}
	}

	// ���ڵ��� �� ���� �� ȭ�鿡 ���
	private static void printTable(Statement stmt) {
		ResultSet srs;
		System.out.printf("%4s|%-30s|%-30s|%-10s\n", "id","title", "publisher", "author");
		try {
			srs = stmt.executeQuery("select * from book");
			while (srs.next()) {
				System.out.printf("%4s|%-30s|%-30s|%-10s\n", new String(srs.getString("id")), srs.getString("title"), srs.getString("publisher"), srs.getString("author"));
			}
		} catch (SQLException e) {
			System.out.println("SQL ���� ����");
		}
	}
}
