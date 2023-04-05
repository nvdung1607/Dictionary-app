package source;

import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class DictionaryManagement {
    public ArrayList<Word> dic = new ArrayList<Word>();
    public ArrayList<Word> dicSort = new ArrayList<Word>();
    public Function function = new Function();
    // initialization data, set words for list.
    public void insertFromJDBC() {
        DataBase list = new DataBase();
        dic = list.getWord();
        dicSort = list.getWord();
        dicSort = function.sortDic(dicSort);
    }

    // Search for words that start with "search".
    public ArrayList<Word> dictionarySearcher(String search) {
        ArrayList <Word> listWordSeachered = new ArrayList<Word>();
        String searchLS = search.toLowerCase();
        if (search != "") {
            int pos = function.binarySearcher(dicSort, searchLS);
            while (pos != -1 && pos <= dicSort.size() - 1) {
                if (function.compare(dicSort.get(pos).getWord_target_lowcase(), searchLS) == 0) {
                    listWordSeachered.add(dicSort.get(pos));
                    pos++;
                } else {
                    break;
                }
            }
        }
        return listWordSeachered;
    }

    // Word search.
    public String dictionaryLookup(String search) {
        String searchLS = search.toLowerCase();
        int pos = function.binaryLookup(dicSort, searchLS);
        if (pos != -1) return dicSort.get(pos).getWord_explain();
        return "NOT FOUND!";
    }

    // add word to list and database.
    public void addWord(String new_target, String new_explain) {
        DataBase data = new DataBase();
        Word word = new Word(new_target, new_explain, new_target.toLowerCase());
        dic.add(word);
        dicSort.add(word);
        dicSort = function.sortDic(dicSort);
        data.InsertWord(dic.size() + 1, new_target, new_explain);
    }
    // delete word from list and database.
    public boolean deleteWord(String selected_target) {
        DataBase data = new DataBase();
        int check = 0;
        String StVal = selected_target;
        int index = 0;
        for (int i = 0; i < dic.size(); i++) {
            if (dic.get(i).getWord_target_lowcase().equals(StVal.toLowerCase())) {
                index = i;
                dic.remove(i);
                check++;
                break;
            }    
        }
        for (int i = 0; i < dicSort.size(); i++) {
            if (dicSort.get(i).getWord_target_lowcase().equals(StVal.toLowerCase())) {
                dicSort.remove(i);
                break;
            }
        }
        if (check != 0) {
            data.RemoveWord(index + 2);
            return true;
        }
        return false;
    }

    // edit selected word in list and database.
    public void editWord(String selected_target, String new_target, String new_explain) {
        DataBase data = new DataBase();
        int val = 0;
        for (int i = 0; i < dic.size(); i++) {
            if (dic.get(i).getWord_target().equals(selected_target)) {
                dic.get(i).setWord_explain(new_explain);
                dic.get(i).setWord_target(new_target);
                dic.get(i).setWord_target_lowcase(new_target.toLowerCase());
                val = i + 2;
                break;
            }  
        }
        int index = function.binaryLookup(dicSort, selected_target.toLowerCase());
        dicSort.get(index).setWord_target(new_target);
        dicSort.get(index).setWord_explain(new_explain);
        dicSort.get(index).setWord_target_lowcase(new_target.toLowerCase());
        if (val != 0) {
            dicSort = function.sortDic(dicSort);
            data.UpdateWord(val, new_target, new_explain);
        }
    }

    // add google API.
    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbwHsg9Ywpg25EwswiLFGGSCVKaN3eNr8QxsGrdDe9ofcfZIZds/exec" +
                "?q=" + URLEncoder.encode(text, String.valueOf(StandardCharsets.UTF_8)) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputDataString;
        while ((inputDataString = in.readLine()) != null) {
            response.append(inputDataString);
        }
        in.close();
        String realSt = response.toString();
        realSt = realSt.replaceAll("&#39;","'");
        realSt = realSt.replaceAll("&quot;","''");
        realSt = realSt.replaceAll("&amp;","&");
        realSt = realSt.replaceAll("&gt;",">");
        realSt = realSt.replaceAll("&lt;", "<");
        return realSt;
    }
}
