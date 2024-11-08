
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Приветствую, пользователь!");
        choose(menu());
    }


    public static int menu() {
        Scanner console = new Scanner(System.in);
        System.out.println("""
                Введите номер действия, которое вы хотите выполнить.
                1 - Зашифровать текст по ключу.
                2 - Расшифровать текст по ключу.
                3 - Расшифровать текст перебором(Brute force).
                4 - Завершение работы программы.""");
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
            ProcessingFile encrypt = new ProcessingFile();
            System.out.println("Путь к зашифрованному файлу: "
                    + encrypt.encryptFile() + "\nДо новых встреч!");
        }else if(number == 2){
            //Decrypt
            ProcessingFile decrypt = new ProcessingFile();
            System.out.println("Путь к расшифрованному файлу: "
                    + decrypt.decryptFile() + "\nДо новых встреч!");
        }else if(number == 3){
            //Brute force
            ProcessingFile bruteForce = new ProcessingFile();
            System.out.println("Путь к файлу, содержащему все варианты расшифровок соответственно ключам:\n"
                               + bruteForce.bruteForce() + "\nДо новых встреч!");
        }else if(number == 4){
            System.out.println("До новых встреч!");
        }
    }
}