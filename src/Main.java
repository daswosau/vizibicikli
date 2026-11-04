import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

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
            String[] prLine = line.split(";");


            Kolcsonzes elso = new Kolcsonzes(prLine[0], prLine[1], Integer.parseInt(prLine[2]), Integer.parseInt(prLine[3]), Integer.parseInt(prLine[4]), Integer.parseInt(prLine[5]));
            kolcsonzesek.add(new Kolcsonzes(prLine[0], prLine[1], Integer.parseInt(prLine[2]), Integer.parseInt(prLine[3]), Integer.parseInt(prLine[4]), Integer.parseInt(prLine[5])));
            System.out.print(elso.getName());


        }

        for(Kolcsonzes k : kolcsonzesek) {
            System.out.println(k.getName());
        }


        System.out.println("5. feladat: Napi kölcsönzések száma: " + kolcsonzesek.size());

        System.out.print("6. feladat: Kérek egy nevet: ");
        Scanner userInput = new Scanner(System.in);
        String userInputName =  userInput.nextLine();

        ArrayList<Kolcsonzes> inputKolcs = new ArrayList();
        for(Kolcsonzes k : kolcsonzesek) {
            if(k.getName().equals(userInputName)) {
                inputKolcs.add(k);
            }
        }
        System.out.println("    "+userInputName+" kölcsönzései:");
        if(inputKolcs.isEmpty()) {
            System.out.println("    Nem volt ilyen nevű kölcsönző!");
        } else {

            for(Kolcsonzes k : inputKolcs) {
                System.out.println("    "+k.Eora+":"+k.Eperc+"-"+k.Vora+":"+k.Vperc);
            }
        }


        int checkHour, checkMinute;
        while(true) {

            System.out.print("7. Feladat: Adjon meg egy időpontot óra:perc alakban: ");
            String userInputTime =  userInput.nextLine();
            try {
                String[] userInputTimeUnprocessed =  userInputTime.split(":");
                checkHour = Integer.parseInt(userInputTimeUnprocessed[0]);
                checkMinute = Integer.parseInt(userInputTimeUnprocessed[1]);
                break;

            } catch (Exception e) {
                System.out.println("Az időpont formátuma helytelen. Kérem, próbálja meg újra.");
                continue;
            }
        }
        int kezdIdo, vegIdo, checkIdo = checkMinute+(checkHour*60);
        ArrayList<Kolcsonzes> vizenKolcs = new ArrayList();
        for(Kolcsonzes k : kolcsonzesek) {
            kezdIdo = k.Eperc+(k.Eora*60);
            vegIdo = k.Vperc+(k.Vora*60);
            if((kezdIdo<=checkIdo)&&(vegIdo>=checkIdo)) {
                vizenKolcs.add(k);
            }
        }
        System.out.println("A vizen lévő járművek:");
        for(Kolcsonzes k : vizenKolcs) {
            System.out.println("    " + k.Eora+":"+k.Eperc+"-"+k.Vora+":"+k.Vperc+" : "+k.getName());
        }


        int bejovetel = 0;
        for(Kolcsonzes k : kolcsonzesek) {
            kezdIdo = k.Eperc+(k.Eora*60);
            vegIdo = k.Vperc+(k.Vora*60);

            double szorzo = Math.floor((vegIdo-kezdIdo)/30)+1;
            bejovetel = (int) (bejovetel + szorzo * 2400);
            System.out.println(k.getName() + ", " + kezdIdo + "-" + vegIdo + ": *"  + szorzo);
            for(int x = 0; x != szorzo; x++) {
                System.out.println(k.getName() + " " + (int) (x+1) + ". turnusa: " + (kezdIdo+(30*x)) + "-kor kezdődött.");
            }
            System.out.println(k.getName() + "-ból/ből " + (int) (szorzo*2400) + " forintot zsebeltünk be. Napi egyenleg: " + bejovetel + "\n");
        }
        System.out.println("8. feladat: A napi bevétel: " + bejovetel);

        System.out.print("9. feladat: ");
        try {
            FileWriter myWriter = new FileWriter("F.txt");
            for(Kolcsonzes k : kolcsonzesek) {
                if(Objects.equals(k.Jazon, "F")) {
                    myWriter.write(k.Eora+":"+k.Eperc+"-"+k.Vora+":"+k.Vperc+" : "+k.getName() + "\n");
                }
            }
            //myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();  // must close manually
            System.out.println("Fájlba írva!");
        } catch (IOException e) {
            System.out.println("Fájl írása sikertelen.");

        }

        HashMap<String, Integer> statisztika = new HashMap<String, Integer>();
        System.out.println("10. feladat: Statisztika");

        for(Kolcsonzes k : kolcsonzesek) {
            if(statisztika.containsKey(k.Jazon)) {
                statisztika.put(k.Jazon, statisztika.get(k.Jazon) + 1);
            } else {
                statisztika.put(k.Jazon, 1);
            }
        }

        for (String i : statisztika.keySet()) {
            System.out.println(i + " - " + statisztika.get(i));
        }


    }
}