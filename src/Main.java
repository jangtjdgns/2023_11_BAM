import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		String cmd = sc.nextLine();
		
		System.out.println("입력된 명령어) " + cmd);
		
		System.out.println(" == 끝 ==");
		
		sc.close();			// 닫아 줘야함
	}
}
