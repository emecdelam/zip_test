import java.util.HashMap;
import java.util.Map;

public class Decoder {
    public static String decoder(String input){
        int numBits = Integer.parseInt(input.substring(0,8), 2);
        HashMap<Character,Integer> map = new HashMap<>();
        int pointer = 8;
        String current = input.substring(pointer, pointer + 8);
        while (!current.equals("00000000") && current != null) {
            String next = input.substring(pointer + 8, pointer + 16);
            char character = (char) Integer.parseInt(current, 2);
            int intValue = Integer.parseInt(next, 2);
            map.put(character, intValue);
            pointer += 16;
            current = input.substring(pointer, pointer + 8);
        }
        HashMap<String,Character> converter = invertMap(map,numBits);
        pointer+=8;
        StringBuilder res = new StringBuilder();
        while (pointer < input.length()){
            current = input.substring(pointer, pointer + numBits);
            pointer += numBits;
            res.append(converter.get(current));
        }
        return res.toString();
    }
    public static HashMap<String, Character> invertMap(HashMap<Character, Integer> originalMap, int numBits) {
        HashMap<String, Character> invertedMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : originalMap.entrySet()) {
            invertedMap.put(Utils.formatter(Integer.toBinaryString(entry.getValue()),numBits), entry.getKey());
        }
        return invertedMap;
    }
}
