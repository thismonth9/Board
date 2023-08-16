package dto;


public class CommentsDto {
	private int boardbno;
	private int bno;
	private String comments;
	private String writer;
	private String writedate;
	
	
	
	public CommentsDto(int boardbno, int bno, String comments, String writer, String writedate) {
		this.boardbno = boardbno;
		this.bno = bno;
		this.comments = comments;
		this.writer = writer;
		this.writedate = writedate;
	}

	public int getBoardbno() {
		return boardbno;
	}

	public void setBoardbno(int boardbno) {
		this.boardbno = boardbno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
	
}