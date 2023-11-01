import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encoder {

    public static String encoder(String input){
        HashMap<Character,Integer> dic = checker(get_occurrence(input));
        int numBits = (int) Math.ceil(Math.log(dic.size()+1) / Math.log(2));
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
            String byt = Utils.formatter(Integer.toBinaryString(dic.get(character)),numBits);
            builder.append(byt);
        }
        return builder.toString();
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
    public static HashMap<Character, Integer> get_occurrence(String input) {
        HashMap<Character, Integer> dic = new HashMap<>();
        String[] bytes = Utils.get_bytes(input);
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
}
