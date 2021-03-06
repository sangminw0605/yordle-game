import java.util.*;
import java.io.*;
import java.lang.Math;

public class driver {


    
    public static void main(String[] args) {
        final int CHAMP_SIZE = 159;
        champList list = new champList();

        int corrIndex = (int) (Math.random() * CHAMP_SIZE);
        Champion corrChamp = null;

       
        try {
            File in = new File("data.txt");
            Scanner scan = new Scanner(in);
            for(int i = 0; i < CHAMP_SIZE; i++) {
                String champName = scan.nextLine();
                String champRegion = scan.nextLine();
                String champRole = scan.next();
                String champLane = scan.next();
                int champCost = scan.nextInt();
            
                if (i != CHAMP_SIZE - 1) {
                    scan.nextLine();
                    scan.nextLine();
                }

                list.addChamp(champName, champRegion, champRole, champLane, champCost);

                if (i == corrIndex) {
                    corrChamp = list.getChamp(champName);
                }
            }
            scan.close();   
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }


        int counter = 1;
        Scanner readIn = new Scanner(System.in);

        while (counter != 10) {

            System.out.print("(" + counter + "/10) Guess a champion: ");
            String guess = readIn.nextLine().toLowerCase();

            Champion guessChamp = list.getChamp(guess);

            if (guessChamp == null) {
                System.out.println("Invalid Champion");
                continue;
            }

            if (guessChamp == corrChamp) {
                System.out.println("Correct! You solved the Yordle in " + counter + " guesses!");
                readIn.close();
                return;
            }

            System.out.print("\nRegion: " + guessChamp.getRegion());
            if (guessChamp.getRegion().equals(corrChamp.getRegion())) {
                System.out.println(" -> Correct, ");
            } else {
                System.out.println(" -> Incorrect, ");
            }

            System.out.print("Role: " + guessChamp.getRole());
            if (guessChamp.getRole().equals(corrChamp.getRole())) {
                System.out.println(" -> Correct, ");
            } else {
                System.out.println(" -> Incorrect, ");
            }

            System.out.print("Lane: " + guessChamp.getLane());
            if (guessChamp.getLane().equals(corrChamp.getLane())) {
                System.out.println(" -> Correct, ");
            } else {
                System.out.println(" -> Incorrect, ");
            }

            System.out.print("Cost: " + guessChamp.getCost());
            if (guessChamp.getCost() == corrChamp.getCost()) {
                System.out.println(" -> Correct\n");
            } else {
                System.out.println(" -> Incorrect\n");
            }

            counter++;
        }

        readIn.close();
        System.out.println("Oh no! " + corrChamp + " was the correct champion.");
    }
}
