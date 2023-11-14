package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Article;
import example.util.Util;

public class ArticleController {
	private int articleId;
	private List<Article> articles;
	Scanner sc;

	public ArticleController(Scanner sc) {
		this.articleId = 0;
		this.articles = new ArrayList<>();
		this.sc = sc;
	}

	// 작성
	public void doWrite() {
		articleId++;

		System.out.printf("제목: ");
		String title = sc.nextLine().trim();
		System.out.printf("내용: ");
		String content = sc.nextLine();

		Article article = new Article(articleId, Util.getDateStr(), title, content);

		this.articles.add(article);

		System.out.println(articleId + "번 게시물이 생성되었습니다.");
	}

	// 목록
	public void showList(String cmd) {
		if (this.articles.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다");
			return;
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
				return;
			}
		}

		System.out.println("번호	/	제목	/		작성일");

		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);
			System.out.printf("%d	/	%s	/	%s\n", article.id, article.title, article.regDate);
		}
	}

	// 조회
	public void showDetail(String cmd) {
		if (cmd.split(" ").length < 3) {
			System.out.println("게시물의 번호를 입력해주세요.");
			return;
		}
		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.println("번  호: " + foundArticle.id);
		System.out.println("작성일: " + foundArticle.regDate);
		System.out.println("제  목: " + foundArticle.title);
		System.out.println("내  용: " + foundArticle.content);
	}

	// 삭제
	public void doDelete(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		this.articles.remove(foundArticle);
		System.out.printf("%d번 게시물을 삭제했습니다.\n", id);
	}

	// 수정
	public void doModify(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		System.out.printf("수정할 제목: ");
		String title = sc.nextLine().trim();
		System.out.printf("수정할 내용: ");
		String content = sc.nextLine();

		foundArticle.title = title;
		foundArticle.content = content;

		System.out.printf("%d번 게시물을 수정했습니다.\n", id);
	}

	
	// id 찾기 메서드
	private Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}
	
	// 테스트용 article 생성 메서드
	public void makeTestArticleData() {
		for (int i = 0; i < 20; i++) {
			this.articles.add(new Article(++articleId, Util.getDateStr(), "제목" + articleId * 3, "내용" + articleId * 3));
		}

		System.out.println("테스트용 게시물이 생성되었습니다.(" + this.articles.size() + "개)");
	}
}