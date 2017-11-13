import java.util.Scanner;

public class WritableSummRun {

    public static void main(String[] args) throws Exception {

        WritableSumm w = new WritableSumm();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Введите число: ");
            long userInput  = sc.nextLong();

            System.out.println(userInput  + "\t" + w.numberToString(userInput ));
        }
    }
}




