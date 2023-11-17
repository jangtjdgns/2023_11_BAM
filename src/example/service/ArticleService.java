package example.service;

import java.util.List;

import example.container.Container;
import example.dao.ArticleDao;
import example.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public int getLastId() {
		return articleDao.getLastId();
	}

	public void doWrite(Article article) {
		articleDao.doWrite(article);
	}

	public List<Article> getArticlesBySearchkeyword(List<Article> printArticles, String searchkeyword) {
		return articleDao.getArticlesBySearchkeyword(printArticles, searchkeyword);
	}

	public int getArticlesSize() {
		return articleDao.getArticlesSize();
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void removeArticle(Article foundArticle) {
		articleDao.removeArticle(foundArticle);
	}

	public void doModify(Article foundArticle, String title, String content) {
		articleDao.doModify(foundArticle, title, content);
	}

}
