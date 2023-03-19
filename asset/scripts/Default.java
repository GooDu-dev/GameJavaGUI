package asset.scripts;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Default {
    public static final String DEFAULT_IMAGE_PATH = "./asset/picture/default_image.png";
    public static final String DEFAULT_SAVED_PATH = "./asset/saved.txt";
    public static final String DEFAULT_WORD_PATH = "./asset/words.txt";
    public static final int MAX_CHAPTER=2;
    public static final int MAX_EPISODE=6;
    public static final String EPISODE="episode", CHAPTER="chapter", HIGHEST_SCORE="highest_score";
    public static Map<String, Integer> data = loadData();
    public static Map<String, Integer> loadData(){
        Map<String, Integer> data = new HashMap<String, Integer>();
        try{
            Scanner file = new Scanner(new File(DEFAULT_SAVED_PATH));
            while(file.hasNextLine()){
                String[] d = file.nextLine().split(":");
                data.put(d[0], Integer.valueOf(d[1]));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Fail to load");
        }
        return data;
    }
    public static void savedData(Map<String, Integer> data){
        try{
            FileWriter fw = new FileWriter(DEFAULT_SAVED_PATH);
            fw.write("chapter:"+data.get(CHAPTER)+"\n");
            fw.write("episode:"+data.get(EPISODE)+"\n");
            fw.write("highest_score:"+data.get(HIGHEST_SCORE));
            fw.close();
        }
        catch(IOException e){
            System.out.println("Saved Fail");
            System.out.println(e);
        }
    }
    public static ArrayList<String> fetchWords(){
        ArrayList<String> wordsArray = new ArrayList<>();
        try{
            Scanner words = new Scanner(new File(DEFAULT_WORD_PATH));
            while(words.hasNextLine()){
                wordsArray.add(words.nextLine());
            }
        } catch(FileNotFoundException e){
            System.out.println("Fail to fetch");
        }
        return wordsArray;
    }
}