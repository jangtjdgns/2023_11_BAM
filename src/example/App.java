package example;

import java.util.Scanner;

import example.controller.ArticleController;
import example.controller.Controller;
import example.controller.MemberController;

public class App {

	void run() {
		System.out.println("== 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		memberController.makeTestData();
		articleController.makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			// member와 article 분리
			String[] cmdBits = cmd.split(" ");

			if(cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}
				
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			Controller controller = null;

			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			controller.doAction(methodName, cmd);
		}

		System.out.println(" == 끝 ==");

		sc.close(); // 닫아 줘야함
	}
}