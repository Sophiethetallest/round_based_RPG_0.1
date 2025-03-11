import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.Scanner;

public class GameSave {
    private static final String SAVE_DIRECTORY = "saves/"; // Verzeichnis, in dem die gespeicherten Spielstände abgelegt werden
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();     // Gson-Instanz zur Umwandlung von Objekten in JSON und umgekehrt


    public static void saveCharacter(Hero player) { // speichern des Charakters
        try {
            File dir = new File(SAVE_DIRECTORY); // Erstelle das Speicherverzeichnis, falls es noch nicht existiert
            if (!dir.exists()) dir.mkdirs();

            FileWriter writer = new FileWriter(SAVE_DIRECTORY + player.name + ".json");
            gson.toJson(player, writer);
            writer.close();
            System.out.println("Spiel gespeichert!");
        } catch (IOException e) { //Fehler beim Schreiben der Datei
            System.out.println("Fehler beim Speichern des Spiels: " + e.getMessage());
        }
    }

    public static Hero loadCharacter(String name) { //Laden des Charakters
        try {
            File file = new File(SAVE_DIRECTORY + name + ".json"); // Datei mit dem gespeicherten Charakter suchen
            if (!file.exists()) {
                System.out.println("Kein gespeicherter Charakter mit diesem Namen gefunden.");
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file)); // Lade die JSON-Daten und wandle sie in ein Hero-Objekt um
            Hero player = gson.fromJson(reader, Hero.class);
            reader.close();
            System.out.println("Charakter " + name + " geladen!");
            return player;
        } catch (IOException e) { //Fehler beim Laden der Datei
            System.out.println("Fehler beim Laden des Spiels: " + e.getMessage());
            return null;
        }
    }

    public static String[] listSavedCharacters() { //Listet alle gespeicherten Charaktere auf
        File dir = new File(SAVE_DIRECTORY);
        if (!dir.exists() || dir.list().length == 0) {
            System.out.println("Keine gespeicherten Charaktere gefunden.");
            return new String[0];
        }
        return dir.list((d, name) -> name.endsWith(".json"));
    }

    public static Hero promptLoadCharacter() { //Charakterauswahl zum Laden
        Scanner scan = new Scanner(System.in);
        String[] savedFiles = listSavedCharacters();

        if (savedFiles.length > 0) {
            System.out.println("Möchtest du einen gespeicherten Charakter laden? (Y/X)");
            if (scan.nextLine().equalsIgnoreCase("y")) {
                System.out.println("Verfügbare Charaktere:");
                for (int i = 0; i < savedFiles.length; i++) {
                    System.out.println((i + 1) + ". " + savedFiles[i].replace(".json", ""));
                }
                System.out.print("Gib die Nummer des Charakters ein: ");
                int choice = scan.nextInt();
                scan.nextLine();
                if (choice > 0 && choice <= savedFiles.length) {
                    return loadCharacter(savedFiles[choice - 1].replace(".json", ""));
                }
            }
        }
        return null;
    }

    public static void promptSaveCharacter(Hero player) { //Abfrage ob der Charakter gespeichert werden soll
        Scanner scan = new Scanner(System.in);
        System.out.println("Möchtest du dein Spiel speichern? (Y/X)");
        if (scan.nextLine().equalsIgnoreCase("Y")) {
            saveCharacter(player);
        }
    }
}
