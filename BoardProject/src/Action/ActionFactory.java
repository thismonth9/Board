package Action;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory(); // 2.static --> 
	private ActionFactory() { }
	public static ActionFactory getInstance() { //싱글턴 , 그리고 static -->인스턴스변수 접근x
		return instance;
	}
	public Action getAction(String command) {
		Action action = null;
		
		switch(command) {
		case "board_list" : action = new BoardListAction(); break;
		case "board_detail_list" : action = new BoardListDetailAction(); break;
		case "login" : action = new LoginAction(); break; 
		case "signup" : action = new SignUpAction(); break; 
		case "logout" : action = new LogoutAction(); break;
		case "mypage" : action = new MyPageAction(); break;
		case "write" : action = new WriteAction(); break;
		case "delete" : action = new DeleteAction(); break;
		case "likes" : action = new LikesAction(); break;
		case "dislikes" : action = new DisLikesAction(); break;
		case "views" : action = new ViewAction(); break;
		case "writeupdate" : action = new WriteUpdateAction(); break;
		case "comments" : action = new CommentsAction(); break;
		case "commentslist" : action = new CommentsListAction(); break;
		
		}
		return action;
	}
}
