import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encrypt {
    static Path pathToFileToEncrypt;
    public Path encryptFile(){
        Encrypt encrypt = new Encrypt();
        pathToFileToEncrypt = encrypt.getPath();
        String textToEncrypt = encrypt.readFile(pathToFileToEncrypt);
        encrypt.setKey();
        encrypt.createCodeMap();
        String encryptedString = encrypt.encryption(textToEncrypt);
        return encrypt.createEncryptedFile(encryptedString);
    }

    public Path createEncryptedFile(String encryptedString){
        Path pathToEncryptedFile = Path.of(pathToFileToEncrypt.getParent() + "\\Зашифрованный файл.txt");
        try {
            Files.createFile(pathToEncryptedFile);
            Files.writeString(pathToEncryptedFile, encryptedString);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(encryptedString);
        return pathToEncryptedFile;
    }
    public String encryption(@NotNull String textToEncrypt){
        char[] arrayToEncrypt = textToEncrypt.toCharArray();
        char[] encryptedArray = new char[arrayToEncrypt.length];
        int i = 0;
        for(char c : arrayToEncrypt){
            encryptedArray[i] = Alphabet.cryptMap.get(c);
            i++;
        }
        String encryptedString = new String(encryptedArray);
        return encryptedString;
    }
    public void createCodeMap(){
        Alphabet.cryptMap = Alphabet.fillMap(Alphabet.getKey());
    }
    public void setKey(){
        Scanner console = new Scanner(System.in);
        int i = 0;
        while(Alphabet.getKey() == 40 || Alphabet.getKey() == 0){
            if(i == 0){
                System.out.println("Введите ключ шифрования.");
            }else{
                System.out.println("Ошибка. Введите корректный ключ.");
            }i++;
            try{
                Alphabet.setKey(Integer.parseInt(console.nextLine()));
            }catch (NumberFormatException e){
                System.out.println("Вы ввели буквы или символы.");
            }
        }
    }
    public String readFile(Path path){
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Path getPath() {
        Scanner console = new Scanner(System.in);
        Path path = Path.of("someFileThatNotExists");
        while (!Files.exists(path)) {
            if(path.compareTo(Path.of("someFileThatNotExists")) != 0){
                System.out.println("Ошибка: такого файла не существует.");
            }
            System.out.println("Введите путь к файлу, который вы хотите зашифровать.");
            try {
                path = Path.of(console.nextLine());
            } catch (java.nio.file.InvalidPathException e) {
                System.out.println("Ошибка: указан некорректный путь к файлу");
            }
        }
        return path;

    }

}
