package dto;


public class BoardDto {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String writedate;
	private int views;
	private String image;
	private int likes;
	private int dislikes;
	private String visibility;
	
	public BoardDto(int bno, String title, String content, String writer, String writedate, int views, String image,
			int likes, int dislikes, String visibility) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writedate = writedate;
		this.views = views;
		this.image = image;
		this.likes = likes;
		this.dislikes = dislikes;
		this.visibility = visibility;
	}
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}