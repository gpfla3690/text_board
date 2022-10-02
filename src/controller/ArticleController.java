package controller;

import data.Article;
import infra.Container;
import infra.Request;
import service.ArticleService;
import utils.Util;

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
            case "detail":
                detail(request);
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

    public void detail(Request request){

        String paramKey = "id";

        if(!Util.hasParam(request, paramKey)){
            System.out.println(paramKey + " 파라미터가 필요합니다.");
            return;
        }

        int articleId = request.getParameterIntValue(paramKey);

        Article findArticle = articleService.getArticleById(articleId);

        if(findArticle == null){
            System.out.println("해당 게시글은 존재하지 않습니다.");
            return;
        }

        System.out.println(" == " + articleId + "번 게시글 == ");
        System.out.println("작성자 : " + findArticle.getAuthor());
        System.out.println("제목 : " + findArticle.getTitle());
        System.out.println("내용 : " + findArticle.getBody());
        System.out.println("작성일 : " + findArticle.getRegDate());

    }

}
