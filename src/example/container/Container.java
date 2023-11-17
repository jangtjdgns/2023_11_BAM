package example.container;

import example.dao.ArticleDao;
import example.dao.MemberDao;

public class Container {
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
	}
}
