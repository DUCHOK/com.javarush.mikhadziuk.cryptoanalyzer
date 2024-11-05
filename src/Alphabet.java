import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', 'Ё', 'Й', 'Ц',
            'У', 'К', 'Е', 'Н', 'Г', 'Ш', 'Щ', 'З', 'Х', 'Ъ', 'Ф', 'Ы', 'В', 'А', 'П', 'Р', 'О', 'Л',
            'Д', 'Ж', 'Э', 'Я', 'Ч', 'С', 'М', 'И', 'Т', 'Ь', 'Б', 'Ю', '\n', '\r', '\f'};
    static int key;

    static Map<Character, Character> cryptMap;

    public static Map<Character, Character> fillMap(int key){
        Map<Character, Character> map = new HashMap<>();
        int j = key;
        for (int i = 0; i < 78; i++) {
            while(j >= 78){
                j = j - 78;
            }
            map.put(ALPHABET[i], ALPHABET[j]);
            j++;
        }
        return map;
    }
    public static Map<Character, Character> invertMap(Map<Character, Character> mapToInvert){
        Map<Character, Character> invertedMap = new HashMap<>();
        for(char mapKey : mapToInvert.keySet()){
            invertedMap.put(mapToInvert.get(mapKey), mapKey);
        }
        return invertedMap;
    }

    public static void setKey(int key){
        Alphabet.key = key;
    }
    public static int getKey(){
        return Alphabet.key;
    }
}
