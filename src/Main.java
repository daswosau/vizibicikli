import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!\n");
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Kolcsonzes> kolcsonzesek = new ArrayList<>();

        File myObj = new File("kolcsonzesek.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                lines.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        lines.remove(0);
        for(String line : lines) {
            System.out.println(line);
            String[] prLine = line.split(";");

            System.out.print(prLine[0]+"\n");

            kolcsonzesek.add(new Kolcsonzes(prLine[0], prLine[1], Integer.parseInt(prLine[2]), Integer.parseInt(prLine[3]), Integer.parseInt(prLine[4]), Integer.parseInt(prLine[5])));
        }

        for(Kolcsonzes k : kolcsonzesek) {
            System.out.println(k.getName());
        }
    }
}