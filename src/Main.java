import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //========= DEBUG =========
        //print_bytes("abc");
        //printer(get_bytes("aba"));
        //printer(invertMap(get_occurrence("aba"),2));
        //System.out.println(splitBitString(encoder("aba")));
        //System.out.println(decoder(encoder("aba")));
        //========= DEBUG =========
        compareBytesLength(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        );
    }
    @SuppressWarnings("unused")
    public static void printer(String[] input){
        System.out.println(Arrays.toString(input));
    }
    @SuppressWarnings("unused")
    public static void printer(HashMap<String, Character> input) {
        for (String name : input.keySet()) {
            System.out.println(name + " " + input.get(name));
        }
    }
    @SuppressWarnings("unused")
    public static String splitBitString(String bitString) {
        List<String> chunks = new ArrayList<>();
        int length = bitString.length();
        for (int i = 0; i < length; i += 8) {
            int endIndex = Math.min(i + 8, length);
            chunks.add(bitString.substring(i, endIndex));
        }
        return Arrays.toString(chunks.toArray(new String[0]));
    }
    public static void compareBytesLength(String input){
        int compressedlength = encoder(input).length();
        int normallength = get_bytes(input).length * 8;
        System.out.println("Encoded : \n"+input);
        System.out.println("Decoded : \n"+decoder(encoder(input)));
        System.out.println("Compressed bytes : "+compressedlength);
        System.out.println("Normal bytes : "+normallength);
        System.out.println("Percentage lost : "+((1.0 -(double) compressedlength/normallength )*100) + "%");
    }
    public static String[] get_bytes(String input){
        byte[] res = input.getBytes();
        ArrayList<String> str = new ArrayList<>();
        for (byte b : res){
            str.add(Integer.toBinaryString(b));
        }
        return str.toArray(new String[0]);
    }
    public static HashMap<Character, Integer> get_occurrence(String input) {
        HashMap<Character, Integer> dic = new HashMap<>();
        String[] bytes = get_bytes(input);
        for (String b : bytes) {
            int character = Integer.parseInt(b, 2);
            char key = (char) character;
            if (dic.containsKey(key)) {
                dic.put(key, dic.get(key) + 1);
            } else {
                dic.put(key, 1);
            }
        }
        return dic;
    }
    public static HashMap<String, Character> invertMap(HashMap<Character, Integer> originalMap, int numBits) {
        HashMap<String, Character> invertedMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : originalMap.entrySet()) {
            invertedMap.put(formatter(Integer.toBinaryString(entry.getValue()),numBits), entry.getKey());
        }
        return invertedMap;
    }
    public static HashMap<Character,Integer> checker(HashMap<Character,Integer> input){
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(input.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        HashMap<Character,Integer> res = new HashMap<>();
        int count = 1;
        for (Map.Entry<Character, Integer> entry : entryList) {
            res.put(entry.getKey(),count);
            count++;
        }
        return res;
    }
    public static String formatter(String input, int length){
        if (input.length() >= length) {
            return input;
        } else {
            int zeroCount = length - input.length();
            return "0".repeat(zeroCount) +
                    input;
        }
    }
    public static String encoder(String input){
        HashMap<Character,Integer> dic = checker(get_occurrence(input));
        int numBits = (int) Math.ceil(Math.log(dic.size()) / Math.log(2)) + 1;
        if (numBits > 8){
            throw new RuntimeException("numbits > 8");
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%08d", Integer.parseInt(Integer.toBinaryString(numBits))));
        for (Character key : dic.keySet()){
            builder.append(String.format("%08d", Integer.parseInt(Integer.toBinaryString(key))));
            builder.append(String.format("%08d", Integer.parseInt(Integer.toBinaryString(dic.get(key).byteValue()))));
        }
        builder.append("00000000");
        for (char c : input.toCharArray()){
            Character character = c;
            String byt = formatter(Integer.toBinaryString(dic.get(character)),numBits);
            builder.append(byt);
        }
        return builder.toString();
    }
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
}