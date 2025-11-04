package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        Scanner scanner = new Scanner(System.in);
        List<WiseSaying> wiseSayings = new ArrayList<>();
        int id = 1;
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            // 1. "종료" 명령
            if (cmd.equals("종료")) {
                scanner.close();
                break;
            }

            // 2. "등록" 명령
            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                WiseSaying wiseSaying = new WiseSaying(id, content, author);
                wiseSayings.add(wiseSaying);
                System.out.println(id + "번 명언이 등록되었습니다.");
                id++;
            }

            // 3. "목록" 명령
            else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                // 목록은 역순으로 출력
                for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying ws = wiseSayings.get(i);
                    System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
                }
            }

            // 4. "삭제" 명령
            else if (cmd.startsWith("삭제?id=")) {
                String[] cmdBits = cmd.split("\\?id=");

                int deleteId = -1;
                try {
                    deleteId = Integer.parseInt(cmdBits[1]);
                }catch (NumberFormatException e){
                    System.out.println("ID는 숫자로 입력해야 합니다.");
                    continue;
                }

                WiseSaying foundSaying = null;
                for(WiseSaying ws : wiseSayings){
                    if (ws.getId() == deleteId){
                        foundSaying =ws;
                        break;
                    }
                }
                if (foundSaying != null){
                    wiseSayings.remove(foundSaying);
                    System.out.printf("%d번 명언이 삭제되었습니다.\n",deleteId);
                }else {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n",deleteId);
                }

            }


            //5. "수정" 명령
            else if (cmd.startsWith("수정?id=")) {
                String[] cmdBits = cmd.split("\\?id=");
                int updateId = -1;
                try {
                    updateId = Integer.parseInt(cmdBits[1]);
                } catch (NumberFormatException e) {
                    System.out.println("ID는 숫자로 입력해야 합니다.");
                    continue;
                }
                WiseSaying foundSaying = null;
                for(WiseSaying ws : wiseSayings){
                    if (ws.getId() == updateId){
                        foundSaying =ws;
                        break;
                    }
                }
                if (foundSaying == null){
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n",updateId);
                    continue;
                }

                System.out.println("명언(기존): "+ foundSaying.getContent());
                System.out.print("명언: ");
                String newContent = scanner.nextLine();

                System.out.println("작가(기존): "+ foundSaying.getAuthor());
                System.out.print("작가: ");
                String newAuthor = scanner.nextLine();

                foundSaying.setContent(newContent);
                foundSaying.setAuthor(newAuthor);

            }

        }
    }
}


class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}