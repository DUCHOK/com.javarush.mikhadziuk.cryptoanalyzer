import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Приветствую, пользователь!");
        int number = menu();
        while(number < 1 || number > 4){
            System.out.println("Номер функции введён некорректно, попробуйте ввести его заново.");
            number = menu();
        }
        choice(number);

    }
    public static int menu() {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите номер действия, которое вы хотите выполнить.");
        System.out.println("1 - Зашифровать текст по ключу.\n" +
                "2 - Расшифровать текст по ключу.\n" +
                "3 - Расшифровать текст перебором(Brute force).\n" +
                "4 - Завершение работы программы.");
        String n = console.nextLine();
        int number = Integer.parseInt(n);
        return number;
    }
    public static void choice(int number){
        if (number == 1) {
            //Encrypt
        }else if(number == 2){
            //Decrypt
        }else if(number == 3){
            //Brute force
        }else if(number == 4){
            System.out.println("До новых встреч!");
        }
    }
}