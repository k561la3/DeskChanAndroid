package example.k561la3.com.helloworld.deskchan.gui_javafx;

import java.util.*;

public class altTest {
    static Integer priority = Integer.MAX_VALUE;
    public static void altSay(Character character, Map<String,Object> data)  {
        String toSay = (String)data.get((Object)"text");
        System.out.println(toSay);  //DEBUG

        String[] phrases = toSay.split("\\s*[\\.!\\?]+\\s+");
        ArrayList<String> signs = new ArrayList<String>(Arrays.asList(toSay.split("[^!\\.\\?]")));
        Iterator<String> iterator = signs.iterator();
        while (iterator.hasNext()) {
            String s1 = iterator.next();
            if (s1.equals("")||s1.equals(null)) {
                iterator.remove();
            }
        }
        if (!data.containsKey("mode")&&toSay.length()<=100){
            data.put("mode","single-phrase");
        }
        //int iter = (phrases.length == signs.size()) ? signs.size() : 0;
        //int iter = Math.min(signs.size(),phrases.length);
        int iter = phrases.length;
        HashMap<String,Object> sayMap = new HashMap<>();
        for (int i = 0; i < iter; i++) {
            if(!data.containsKey("mode"))data.put("mode","");
            if(data.get("mode").equals("single-phrase") && !data.get("mode").equals(null)){
                sayMap.put("text",toSay);
                break;
            }
            if(signs.isEmpty())signs.add("");
            if(i==iter-1){
                sayMap.put("text",phrases[i]);
            }else {
                sayMap.put("text", phrases[i] + signs.get(i));
            }
            sayMap.put("priority",priority);
            if(i != iter-1)sayMap.put("timeout", 4000);
            if(--priority <= 1500){priority = Integer.MAX_VALUE;}else{priority--;}
            character.say(sayMap);
        }


        character.say(sayMap);
    }
}
