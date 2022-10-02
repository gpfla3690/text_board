package controller;

import infra.Container;
import infra.Request;
import service.ArticleService;

import java.util.Scanner;

public class ArticleController implements Controller{

    private Scanner sc;

    private ArticleService articleService;

    public ArticleController(){
        this.sc = Container.sc;
        this.articleService = Container.articleService;
    }


    @Override
    public void execute(Request request) {

        switch (request.getTarget()){
            case "write":
                write(request);
                break;
            default:
                System.out.println("존재하지 않는 페이지 입니다.");
                break;
        }

    }

    public void write(Request request){

        System.out.println(" == 게시글 작성 == ");
        System.out.print("제목 : ");
        String title = sc.nextLine().trim();

        if (title.length() == 0) {
            System.out.println("제목을 입력하여 주시기 바랍니다.");
            return;
        }

        System.out.print("내용 : ");
        String body = sc.nextLine().trim();

        String author = request.getLogonMemberId();

        int articleId = articleService.write(title, body, author);

        System.out.println(articleId + "번 게시글이 작성되었습니다.");

    }


}
