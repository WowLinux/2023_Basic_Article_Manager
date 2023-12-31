package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.service.ArticleService;
import com.koreaIT.java.BAM.service.MemberService;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller{
//	List<Article> articles; 
	private Scanner sc;
	private String cmd;
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController(Scanner sc) {
       this.articleService = Container.articleService;
       this.memberService = Container.memberService;
		this.sc = sc;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch(methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default: 	
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}
	
	public void doWrite() {
		int id = articleService.setArticleId();
		String regDate = Util.getNowDateStr(); 			
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");			
		String body = sc.nextLine();
		
		Article article = new Article(id,regDate,loginedMember.id, title,body);
		
		articleService.add(article);
//		articles.add(article);
		
		System.out.printf("%d번 글이 생성되었습니다\n",id);
		
	}
	public void showList() {
				
		String searchKeyword = cmd.substring("article list".length()).trim();
			
		List<Article> forPrintArticles = articleService.getForPrintArticles(searchKeyword);
		
//		if(forPrintArticles.size() == 0) {
//			System.out.println("검색결과가 없습니다.");
//			return;
//		}		
		
		if (forPrintArticles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return; //return값이 없을때(void) 그냥 순수하게 함수를 종료한다.
		}
		
		System.out.println("번호	|	제목	|	날짜			|	작성자	|	조회수");
		for(int i = forPrintArticles.size()-1; i >= 0; i--) {
			Article article=forPrintArticles.get(i);
			
			String writerName = memberService.getWriterName(article.memberId);
//			List<Member> members = Container.memberDao.members;
			
			System.out.printf("%d	|	%s	|	%s	|	%s	|	%d\n", article.id, article.title, article.regDate, writerName, 
					article.viewCnt);					
		}
		
	}
	public void showDetail() {
		
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해 주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
	
		Article foundArticle = articleService.getArticleById(id);				

		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n",id);				
			return;					
		}  
		
		String writerName = memberService.getWriterName(foundArticle.memberId);

		foundArticle.addViewCnt();
		
		System.out.printf("번호 : %d\n",foundArticle.id);
		System.out.printf("날짜 : %s\n",foundArticle.regDate);
		System.out.printf("작성자 : %s\n",writerName);
		System.out.printf("제목 : %s\n",foundArticle.title);
		System.out.printf("내용 : %s\n",foundArticle.body);	 
		System.out.printf("조회수 : %d\n",foundArticle.viewCnt);
		
	}
	
	
	public void doModify() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해 주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
//		Article foundArticle = Container.articleService.getArticleById(id);
		Article foundArticle = articleService.getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n",id);				
			return;					
		}
		
		if(foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다.");
			return;			
		} 
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");			
		String body = sc.nextLine();
		
		foundArticle.title = title;
		foundArticle.body = body;
		
		System.out.printf("%d번 글이 수정되었습니다.\n",id);
		
	}
	
	public void doDelete() {		
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해 주세요.");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);
		
//		int foundIndex = getArticleIndexById(id);
		Article foundArticle = articleService.getArticleById(id);

		if(foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n",id);				
			return;					
		} 
		
		
		if(foundArticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다.");
			return;			
		} 
	
		articleService.remove(foundArticle);
//		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다\n",id);
		
	}
	
	public  void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성 합니다.");
		articleService.add(new Article(articleService.setArticleId(),Util.getNowDateStr(),1,"제목1","내용1",11));
		articleService.add(new Article(articleService.setArticleId(),Util.getNowDateStr(),2,"제목2","내용2",22));
		articleService.add(new Article(articleService.setArticleId(),Util.getNowDateStr(),2,"제목3","내용3",33));
	}
}
