import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try{
            Scanner scan = new Scanner(new File("./asset/saved.txt"));
            while(scan.hasNextLine()){
                System.out.println(scan.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}