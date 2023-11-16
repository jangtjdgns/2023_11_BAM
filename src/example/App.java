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

			String[] cmdBits = cmd.split(" ");

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요.");
				continue;
			}

			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			// 현재 제작한 프로그램은 methodName만으로 충분히 검증 가능하지만,
			// 좀더 명확하게 구분하기 위해 controllerName도 구분
			String actionName = controllerName + "/" + methodName;

			switch (actionName) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
				if (!Controller.isLogined()) {
					System.out.println("로그인 후 이용해주세요.");
					continue;
				}
				break;				// break 넣어야함 안넣으면 로그아웃 불가능함
			case "member/join":
			case "member/login":
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				break;
			}
			
			Controller controller = null;

			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			System.out.println(controllerName + " " + methodName);

			controller.doAction(methodName, cmd);
		}

		System.out.println(" == 끝 ==");

		sc.close(); // 닫아 줘야함
	}
}