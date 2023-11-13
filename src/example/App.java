package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Article;
import example.dto.Member;
import example.util.Util;

public class App {
	private int articleId;
	private int memberId;
	private List<Article> articles;
	private List<Member> members;

	App() {
		this.articleId = 0;
		this.memberId = 0;
		this.articles = new ArrayList<>();
		this.members = new ArrayList<>();
	}

	void run() {
		System.out.println("== 시작 ==");

		Scanner sc = new Scanner(System.in);

		addTestArticle();

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

			
			
			// 회원가입
			if (cmd.equals("member join")) {
				
				// 
				System.out.printf("아이디: ");
				String loginId = sc.nextLine().trim();
				System.out.printf("비밀번호: ");
				String loginPw = sc.nextLine().trim();
				System.out.printf("비밀번호 확인: ");
				String PwCheck = sc.nextLine().trim();

				System.out.printf("이름: ");
				String userName = sc.nextLine().trim();

				Member member = new Member(++memberId, Util.getDateStr(), loginId, loginPw, userName);
				members.add(member);

				for (Member us : members) {
					System.out.println(us.id);
					System.out.println(us.regDate);
					System.out.println(us.loginId);
					System.out.println(us.loginPw);
					System.out.println(us.name);
				}
			}

			// 게시물 작성
			else if (cmd.equals("article write")) {
				articleId++;

				System.out.printf("제목: ");
				String title = sc.nextLine().trim();
				System.out.printf("내용: ");
				String content = sc.nextLine();

				Article article = new Article(articleId, Util.getDateStr(), title, content);

				this.articles.add(article);

				System.out.println(articleId + "번 게시물이 생성되었습니다.");
			}

			// 게시물 목록
			else if (cmd.startsWith("article list")) {
				if (this.articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}

				String searchkeyword = cmd.substring("article list".length()).trim();
				List<Article> printArticles = this.articles;

				if (searchkeyword.length() > 0) {
					System.out.println("검색어: " + searchkeyword);

					// 검색어가 있으면 printArticles 빈 객체로 초기화
					printArticles = new ArrayList<>();

					for (Article article : articles) {
						if (article.title.contains(searchkeyword)) {
							printArticles.add(article); // 검색어가 존재한 경우 백업용 printArticles에 article 추가
						}
					}

					// 검색 결과가 없을 때
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue;
					}
				}

				System.out.println("번호	/	제목	/		작성일");

				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	/	%s	/	%s\n", article.id, article.title, article.regDate);
				}
			}

			// 게시물 조회
			else if (cmd.startsWith("article detail ")) {
				if (cmd.split(" ").length < 3) {
					System.out.println("게시물의 번호를 입력해주세요.");
					continue;
				}
				int id = Integer.parseInt(cmd.split(" ")[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.println("번  호: " + foundArticle.id);
				System.out.println("작성일: " + foundArticle.regDate);
				System.out.println("제  목: " + foundArticle.title);
				System.out.println("내  용: " + foundArticle.content);
			}

			// 게시물 삭제
			else if (cmd.startsWith("article delete ")) {
				int id = Integer.parseInt(cmd.split(" ")[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				this.articles.remove(foundArticle);
				System.out.printf("%d번 게시물을 삭제했습니다.\n", id);
			}

			// 게시물 수정
			else if (cmd.startsWith("article modify ")) {
				int id = Integer.parseInt(cmd.split(" ")[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("수정할 제목: ");
				String title = sc.nextLine().trim();
				System.out.printf("수정할 내용: ");
				String content = sc.nextLine();

				foundArticle.title = title;
				foundArticle.content = content;

				System.out.printf("%d번 게시물을 수정했습니다.\n", id);
			}

			// 명령어 입력 잘못했을 때
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		System.out.println(" == 끝 ==");

		sc.close(); // 닫아 줘야함
	}

	private Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	private void addTestArticle() {
		for (int i = 0; i < 20; i++) {
			this.articles.add(new Article(++articleId, Util.getDateStr(), "제목" + articleId * 3, "내용" + articleId * 3));
		}

		System.out.println("테스트용 게시물이 생성되었습니다.(" + this.articles.size() + "개)");
	}
}