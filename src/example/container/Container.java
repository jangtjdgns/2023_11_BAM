package example.container;

import example.dao.ArticleDao;
import example.dao.MemberDao;
import example.service.ArticleService;
import example.service.MemberService;

public class Container {
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static ArticleService articleService;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		memberService = new MemberService();
		articleService = new ArticleService();
	}
}
