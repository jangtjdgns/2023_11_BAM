import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 시작 ==");

		Scanner sc = new Scanner(System.in);
		int articleId = 0;
		List<Article> articles = new ArrayList<>();

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

			// 게시물 작성
			if (cmd.equals("article write")) {
				articleId++;

				System.out.printf("제목: ");
				String title = sc.nextLine().trim();
				System.out.printf("내용: ");
				String content = sc.nextLine();

				Article article = new Article(articleId, Util.getDateStr(), title, content);

				articles.add(article);

				System.out.println(articleId + "번 게시물이 생성되었습니다.");
			}
			
			// 게시물 목록
			else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호	/	제목	/	작성일");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i); // article 변수 자체만 프린트해보면 주소값 나옴
					System.out.printf("%d	/	%s	/	%s\n", article.id, article.title, article.regDate);
				}
			}

			// 게시물 조회
			else if (cmd.startsWith("article detail ")) {
				if (cmd.split(" ").length < 3) {
					System.out.println("게시물의 번호를 입력해주세요.");
					continue;
				}
				int articleNo = Integer.parseInt(cmd.split(" ")[2]);

				// 참조형 변수 선언시에는 기본값 null, 반복문 돌릴때 해당 게시물이 있을경우 백업하기 위해 선언
				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == articleNo) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(articleNo + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				System.out.println("번  호: " + foundArticle.id);
				System.out.println("작성일: " + foundArticle.regDate);
				System.out.println("제  목: " + foundArticle.title);
				System.out.println("내  용: " + foundArticle.content);
			}
			
			// 게시물 삭제
			else if (cmd.startsWith("article delete ")) {
				int articleNo = Integer.parseInt(cmd.split(" ")[2]);

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == articleNo) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", articleNo);
					continue;
				}

				articles.remove(foundArticle);
				System.out.printf("%d번 게시물을 삭제했습니다.\n", articleNo);
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

class Article {
	int id;
	String regDate;
	String title;
	String content;

	// 생성자
	Article(int id, String regDate, String title, String content) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.content = content;
	}
}
