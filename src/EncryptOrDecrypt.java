import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptOrDecrypt{
    static Scanner console = new Scanner(System.in);
    static Path pathToFileToEncrypt;
    String textToEncrypt;
    static Path pathToFileToDecrypt;
    String textToDecrypt;

    public Path encryptFile(){
        EncryptOrDecrypt encrypt = new EncryptOrDecrypt();
        pathToFileToEncrypt = encrypt.getPath();
        textToEncrypt = encrypt.readFile(pathToFileToEncrypt);
        encrypt.setKey();
        encrypt.createCodeMap();
        String encryptedString = encrypt.textProcessing(textToEncrypt);
        return encrypt.createProcessedFile(encryptedString);
    }
    public Path decryptFile(){
        EncryptOrDecrypt encrypt = new EncryptOrDecrypt();
        pathToFileToDecrypt = encrypt.getPath();
        textToDecrypt = encrypt.readFile(pathToFileToDecrypt);
        encrypt.setKey();
        encrypt.createCodeMap();
        Alphabet.cryptMap = Alphabet.invertMap(Alphabet.cryptMap);
        String decryptedString = encrypt.textProcessing(textToDecrypt);
        return encrypt.createProcessedFile(decryptedString);
    }

    public Path createProcessedFile(String processedString){
        System.out.println("Введите путь для создания обработанного файла.");
        Path pathToProcessedFile = Path.of(console.nextLine());
        try {
            Files.createFile(pathToProcessedFile);
            Files.writeString(pathToProcessedFile, processedString);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Содержимое обработанного файла: " + processedString);
        return pathToProcessedFile;
    }
    public String textProcessing(@NotNull String textToProcessing){
        char[] arrayToProcessing = textToProcessing.toCharArray();
        char[] processedArray = new char[arrayToProcessing.length];
        int i = 0;
        for(char c : arrayToProcessing){
            processedArray[i] = Alphabet.cryptMap.get(c);
            i++;
        }
        return new String(processedArray);
    }
    public void createCodeMap(){
        Alphabet.cryptMap = Alphabet.fillMap(Alphabet.getKey());
    }
    public void setKey(){
        int i = 0;
        while(Alphabet.getKey() % 78 == 0){
            if(i == 0){
                System.out.println("Введите ключ шифрования. Ключ должен быть целым положительным числом,\nа также не равен 0, 78 и числам, кратным 78.");
            }else{
                System.out.println("Ошибка. Введите корректный ключ. Ключ должен быть целым положительным числом,\nа также не равен 0, 78 и числам, кратным 78.");
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
        Path path = Path.of("someFileThatNotExists");
        while (!Files.exists(path)) {
            if(path.compareTo(Path.of("someFileThatNotExists")) != 0){
                System.out.println("Ошибка: такого файла не существует.");
            }
            System.out.println("Введите путь к файлу.");
            try {
                path = Path.of(console.nextLine());
            } catch (java.nio.file.InvalidPathException e) {
                System.out.println("Ошибка: указан некорректный путь к файлу");
            }
        }
        return path;

    }

}
