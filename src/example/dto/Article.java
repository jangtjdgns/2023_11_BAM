package example.dto;

public class Article {
	public int id;
	public String regDate;
	public String title;
	public String content;

	// 생성자
	public Article(int id, String regDate, String title, String content) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.content = content;
	}
}
