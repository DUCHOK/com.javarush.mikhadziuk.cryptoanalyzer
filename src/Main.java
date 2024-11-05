
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Приветствую, пользователь!");
        choose(menu());
    }


    public static int menu() {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите номер действия, которое вы хотите выполнить.\n" +
                "1 - Зашифровать текст по ключу.\n" +
                "2 - Расшифровать текст по ключу.\n" +
                "3 - Расшифровать текст перебором(Brute force).\n" +
                "4 - Завершение работы программы.");
        int number = Integer.parseInt(console.nextLine());
        while(number < 1 || number > 4){
            System.out.println("Номер функции введён некорректно, попробуйте ввести его заново.");
            number = Integer.parseInt(console.nextLine());
        }
        return number;
    }
    public static void choose(int number){
        if (number == 1) {
            //Encrypt
            EncryptOrDecrypt encrypt = new EncryptOrDecrypt();
            System.out.println("Путь к зашифрованному файлу: " + encrypt.encryptFile());

        }else if(number == 2){
            //Decrypt
            EncryptOrDecrypt decrypt = new EncryptOrDecrypt();
            System.out.println("Путь к расшифрованному файлу: " + decrypt.decryptFile());
        }else if(number == 3){
            //Brute force
        }else if(number == 4){
            System.out.println("До новых встреч!");
        }
    }
}