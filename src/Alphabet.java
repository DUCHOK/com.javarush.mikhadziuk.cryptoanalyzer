import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    static int key;

    static Map<Character, Character> cryptMap;

    public static Map<Character, Character> fillMap(int key){
        Map<Character, Character> map = new HashMap<>();
        int j = key;
        for (int i = 0; i < 42; i++) {
            while(j >= 42){
                j = j - 42;
            }
            map.put(ALPHABET[i], ALPHABET[j]);
            j++;
        }
        return map;
    }

    public static void setKey(int key){
        Alphabet.key = key;
    }
    public static int getKey(){
        return Alphabet.key;
    }
}
