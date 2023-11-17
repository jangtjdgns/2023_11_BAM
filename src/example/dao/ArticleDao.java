package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;
	
	public ArticleDao(){
		this.articles = new ArrayList<>();
	}
	
	public List<Article> getArticles() {
		return this.articles;
	}
	
	// 작성
	public void doWrite(Article article) {
		this.articles.add(article);
		this.lastId++;
	}

	// 삭제
	public void removeArticle(Article foundArticle) {
		this.articles.remove(foundArticle);
	}
	
	// 수정
	public void doModify(Article foundArticle, String title, String content) {
		foundArticle.title = title;
		foundArticle.content = content;
	}

	// 검색어 확인
	public List<Article> getArticlesBySearchkeyword(List<Article> printArticles, String searchkeyword) {
		printArticles = new ArrayList<>();
		for (Article article : articles) {
			if (article.title.contains(searchkeyword)) {
				printArticles.add(article);
			}
		}
		return printArticles;
	}

	// id 찾기 메서드
	public Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	// articles 사이즈 확인용
	public int getArticlesSize() {
		return this.articles.size();
	}
	
}
