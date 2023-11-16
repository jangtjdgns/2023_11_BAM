package example.dto;

public class Article {
	public int id;
	public String regDate;
	public int memberId;
	public String title;
	public String content;

	// 생성자
	public Article(int id, String regDate, int memberId, String title, String content) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
	}
}
