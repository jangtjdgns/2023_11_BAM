package example;

import java.util.Scanner;

import example.controller.ArticleController;
import example.controller.MemberController;

public class App {

	void run() {
		System.out.println("== 시작 ==");

		Scanner sc = new Scanner(System.in);
		
		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);

		articleController.makeTestArticleData();

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
			
			// member
			if (cmd.equals("member join")) {
				memberController.doJoin();
			} 
			
			// article
			// 게시물 작성
			else if (cmd.equals("article write")) {
				articleController.doWrite();
			}

			// 게시물 목록
			else if (cmd.startsWith("article list")) {
				articleController.showList(cmd);
			}

			// 게시물 조회
			else if (cmd.startsWith("article detail ")) {
				articleController.showDetail(cmd);
			}

			// 게시물 삭제
			else if (cmd.startsWith("article delete ")) {
				articleController.doDelete(cmd);
			}

			// 게시물 수정
			else if (cmd.startsWith("article modify ")) {
				articleController.doModify(cmd);
			}

			// 명령어 입력 잘못했을 때
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		System.out.println(" == 끝 ==");

		sc.close(); // 닫아 줘야함
	}
}