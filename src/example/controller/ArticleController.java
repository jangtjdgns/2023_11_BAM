package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.container.Container;
import example.dto.Article;
import example.service.ArticleService;
import example.service.MemberService;
import example.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private String cmd;
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.cmd = null;
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;
	}

	@Override
	public void doAction(String methodName, String cmd) {
		this.cmd = cmd;

		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	// 작성
	private void doWrite() {
		int lsatArticleId = articleService.getLastId();

		System.out.printf("제목: ");
		String title = sc.nextLine().trim();
		System.out.printf("내용: ");
		String content = sc.nextLine();

		Article article = new Article(lsatArticleId, Util.getDateStr(), loginedMember.id, title, content);

		articleService.doWrite(article);

		System.out.println(lsatArticleId + "번 게시물이 생성되었습니다.");
	}

	// 목록
	private void showList() {
		if (articleService.getArticlesSize() == 0) {
			System.out.println("게시물이 존재하지 않습니다");
			return;
		}

		String searchkeyword = cmd.substring("article list".length()).trim();
		List<Article> printArticles = articleService.getArticles();

		if (searchkeyword.length() > 0) {
			System.out.println("검색어: " + searchkeyword);

			// 검색어가 있으면 printArticles 빈 객체로 초기화
			printArticles = new ArrayList<>();

			printArticles = articleService.getArticlesBySearchkeyword(printArticles, searchkeyword);

			// 검색 결과가 없을 때
			if (printArticles.size() == 0) {
				System.out.println("검색결과가 없습니다.");
				return;
			}
		}

		System.out.println("번호	/		작성일		/	제목	/	작성자");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			
			String writerName = memberService.getWriterName(article.memberId);
			
			System.out.printf("%d	/	%s	/	%s	/	%s\n", article.id, article.regDate, article.title, writerName);
		}
	}

	// 조회
	private void showDetail() {
		if (cmd.split(" ").length < 3) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.println("번  호: " + foundArticle.id);
		System.out.println("작성일: " + foundArticle.regDate);
		System.out.println("작성일: " + memberService.getWriterName(foundArticle.memberId));
		System.out.println("제  목: " + foundArticle.title);
		System.out.println("내  용: " + foundArticle.content);
	}

	// 삭제
	private void doDelete() {
		if (cmd.split(" ").length < 3) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("%d번 게시물에 대한 권한이 없습니다.\n", foundArticle.id);
			return;
		}

		articleService.removeArticle(foundArticle);
		System.out.printf("%d번 게시물을 삭제했습니다.\n", id);
	}

	// 수정
	private void doModify() {
		if (cmd.split(" ").length < 3) {
			System.out.println("명령어를 확인해주세요.");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("%d번 게시물에 대한 권한이 없습니다.\n", foundArticle.id);
			return;
		}

		System.out.printf("수정할 제목: ");
		String title = sc.nextLine().trim();
		System.out.printf("수정할 내용: ");
		String content = sc.nextLine();

		articleService.doModify(foundArticle, title, content);

		System.out.printf("%d번 게시물을 수정했습니다.\n", id);
	}

	// 테스트용 article 생성 메서드
	@Override
	public void makeTestData() {
		for (int i = 0; i < 5; i++) {
			articleService.doWrite(new Article(i + 1, Util.getDateStr(), 1, "제목" + i + 1, "내용" + i + 1));
		}

		System.out.println("테스트용 게시물이 생성되었습니다.(" + articleService.getArticlesSize() + "개)");
	}
}
