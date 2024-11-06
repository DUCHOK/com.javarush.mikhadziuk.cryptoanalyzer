import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ProcessingFile {
    static Scanner console = new Scanner(System.in);
    static ProcessingFile processing = new ProcessingFile();
    static Path pathToProcessingFile;
    static String processingText;
    static String processedText;

    public Path encryptFile(){
        pathToProcessingFile = processing.getPath();
        processingText = processing.readFile(pathToProcessingFile);
        processing.setKey();
        processing.createCodeMap();
        processedText = processing.textProcessing(processingText);
        return processing.createProcessedFile(processedText);
    }
    public Path decryptFile(){
        pathToProcessingFile = processing.getPath();
        processingText = processing.readFile(pathToProcessingFile);
        processing.setKey();
        processing.createCodeMap();
        Alphabet.invertMap(Alphabet.cryptMap);
        processedText = processing.textProcessing(processingText);
        return processing.createProcessedFile(processedText);
    }
    public Path bruteForce(){
        pathToProcessingFile = processing.getPath();
        processingText = processing.createTextToBruteForced(processing.readFile(pathToProcessingFile));
        processedText = processing.createBruteForcedText(processingText);
        return processing.createProcessedFile(processedText);
    }

    public String createBruteForcedText(String substring){
        String bruteForcedText ="";
        for(int i = 1; i < 78; i++){
            Alphabet.setKey(i);
            processing.createCodeMap();
            Alphabet.invertMap(Alphabet.cryptMap);
            bruteForcedText = bruteForcedText + "При ключе - " + Alphabet.getKey()+ " Расшифровка:" + processing.textProcessing(substring) + "\n";
        }
        return bruteForcedText;
    }
    public String createTextToBruteForced(@NotNull String textToBruteForce){
        String substring = textToBruteForce.trim();
        if(substring.length() > 40){
            substring = substring.substring(0, 40);
        }return substring;
    }
    public Path createProcessedFile(String processedString){
        System.out.println("Введите путь для создания файла.");
        Path pathToProcessedFile = Path.of(console.nextLine());
        try {
            Files.createFile(pathToProcessedFile);
            Files.writeString(pathToProcessedFile, processedString);
        }catch(Exception e){
            e.printStackTrace();
        }
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
