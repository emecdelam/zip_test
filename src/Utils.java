import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Utils {
    public static String formatter(String input, int length){
        if (input.length() >= length) {
            return input;
        } else {
            int zeroCount = length - input.length();
            return "0".repeat(zeroCount) +
                    input;
        }
    }
    public static String[] get_bytes(String input){
        byte[] res = input.getBytes();
        ArrayList<String> str = new ArrayList<>();
        for (byte b : res){
            str.add(Integer.toBinaryString(b));
        }
        return str.toArray(new String[0]);
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
}
