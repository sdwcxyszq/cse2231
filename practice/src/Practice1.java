import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Practice1 {

    public static void main(String[] args) {
        BufferedReader input;
        PrintWriter output;
        try {
            input = new BufferedReader(new FileReader(args[0]));
            output = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        }catch(IOException e){
            System.err.println("File does not exist");
        }
        try {
            String line = input.readLine();
            while(line!=null) {
                output.println(line);
                line=input.readLine()
            }

        }catch(IOException e) {
            System.err.println("Fail to read the file");
        }
        try {
            input.close();
            output.close();
        }catch(IOException e) {
            System.err.println("Cannot close the file");
        }

    }

}
