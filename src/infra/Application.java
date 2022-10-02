package infra;

import controller.Controller;

import java.util.Scanner;

public class Application {

    private Scanner sc = Container.sc;
    private boolean isActivate = true;

    public void run(){

        while (isActivate){
            System.out.print("명령어 : ");
            String inputUri = sc.nextLine().trim();

            if(inputUri.equals(".exit")){
                System.out.println("어플리케이션을 종료합니다.");
                break;
            }

            Request request = new Request(inputUri);

            if(!request.isValidRequest()){
                System.out.println("잘못된 요청입니다.");
                continue;
            }

            Filter filter = new Filter(request);

            if(!filter.isValidRequest()){
                System.out.println("잘못된 요청입니다.");
                continue;
            }

            Controller controller = getController(request.getControllerCode());

            if(controller != null){
                controller.execute(request);
            }else{
                System.out.println("올바른 uri를 입력해 주세요.");
            }

        }

    }

    public Controller getController(String code){

        switch (code){
            case "system":
                return Container.systemController;
            case "members":
                return Container.memberController;
            case "articles":
                return Container.articleController;
            default:
                return null;
        }

    }

}
