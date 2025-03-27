import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        minesweeper game = new minesweeper(10,10,20);
        //game.checkTileUsable(3, 2);
    }

















    public void mathTask(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Task 1 or 2:");

        String taskID = myObj.nextLine();
        switch (taskID) {
            case "1": task1(); break;
            case "2": {
                System.out.println("Dividable number count:");
                try {
                    int count = Integer.parseInt(myObj.nextLine());
                    task2(count);
                } catch (NumberFormatException e) {System.out.println("Invalid number");}
            } break;
            default: System.out.println("Invalid task ID"); break;
        }



    }
    public static void task1(){

        for (int i = 1; i< 21; i++){
            System.out.print(15*i + ", ");
        }


    }
    public static void task2(int i){
        for(int j = 1; j<=i; j++){
            System.out.print(j*6 +", ");
        }
    }
}
