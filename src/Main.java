import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        Hero player = GameSave.promptLoadCharacter(); //laden eines evtl bestehenden Charackters
        if (player == null) { // Wenn es keine Savedatei eines bestehenden Charakters gibt, wird ein neuer erstellt
            player = createCharacter();
        }
        int weapon = 0, situation;
        //player.lvl = 2;


        while (player.health > player.death && player.lvl < 4) {// entscheidung ob Kampf oder ein anderer random Encounter
            if (player.floor.floorNr < 19) {
                player.floor.tilesNr = player.floor.FloorSize(); //Bestimmung der Größe der Ebene
                int bossLevel = 5;
                while (player.floor.tilesNr > 0) {
                    if (player.floor.tilesNr == 1 && player.floor.floorNr == bossLevel) {
                        Enemy enemy = generateMiniboss(bossLevel);
                        enemy.enemyPresentation();
                        new Fight(player, enemy);
                        bossLevel += 5;
                    } else {
                        situation = chooseEncounter();
                        if (situation >= 10 && situation <= 20) { //wenn die Zufallszahl größer oder gleich 10 ist, bzw kleiner oder gleich 20 ist, wird ein Kampf getriggert
                            Enemy enemy = generateEnemy(rand);
                            enemy.enemyPresentation();
                            new Fight(player, enemy);
                        } else {
                            new Encounter().encounter(weapon, player);
                        }
                    }
                    regenerateMana(player);
                    GameSave.promptSaveCharacter(player); //Abfrage, ob User ihren Charakter speichern wollen
                    player.floor.tilesDecrease(); //verkleinern der Ebene
                }
                player.floor.floorIncrease(); //Stockwerkszahl erhöhung
                System.out.print("Ende des Stockwerkes erreicht. Nächster Stock: " + player.floor.floorNr + "\n");
                System.out.println("Drücke Enter!\n");
                String input = scan.nextLine();
            } else{
                new Fight(player, new Enemy("Ogerboss", 150, 8, 300, 0, 0)).Bossfight(player);
            }
        }
        printGameResult(player);
    }


    private static Hero createCharacter() { // Methode mit der ein neuer Charakter, bzw ein Hero Object erstellt wird
        Scanner scan = new Scanner(System.in);
        System.out.print("Gib den Namen deines Charakters ein: ");
        String name = scan.nextLine();
        int job = chooseJob();
        Hero player = (job == 1)
                ? new Hero(name, 30, 1, 10, 1, 30, 0, 0,
                            0, 30,90,false, false, true)
                : new Hero(name, 60, 10, 1, 2, 10, 0, 0,
                            0, 10,90, false,true, false);
        player.printCharacter();
        return player;
    }

    public static int chooseJob() { // Methode die darüber entscheidet was für eine Charakterklasse der Charakter hat
        Scanner scan = new Scanner(System.in);
        int job = 0;
        while (job < 1 || job > 2) {
            System.out.print("Wähle deine Klasse (Magier = 1, Krieger = 2):" +
                    "\nOder Infos zur Klasse(Magier = 3, Krieger = 4): ");
            job = scan.nextInt();
            if (job == 3) System.out.print("\nDer Magier ist ein Fernkämpfer, ohne viel Gesundheit und Verteidigung\n\n");
            if (job == 4) System.out.print("\nDer Krieger ist ein Nahkämpfer, mit viel Gesundheit und Verteidigung\n\n");
        }
        return job;
    }

    private static int chooseEncounter() { //rng zwischen 1-30, ob es ein Kampf ist oder ein anderer random Encounter ist
        return new Random().nextInt(10+10+10) + 1;
    }

    private static Enemy generateEnemy(Random rand) { //Methode zum Erstellen von Gegnern, bzw Objekten der Enemy Klasse
        int mob = rand.nextInt(10) + 1;
        if (mob <= 4) return new Enemy("Goblin", 20, 2, 1, 0, 0);
        if (mob >= 7) return new Enemy("Ork", 30, 4, 2, 0, 0);
        return new Enemy("Troll", 40, 6, 3, 0, 0);
    }

    private static Enemy generateMiniboss(int floor) { //Methode zum Erstellen von Gegnern, bzw Objekten der Enemy Klasse
        if (floor == 5) return new Enemy("Hobgoblin", 60, 4, 4, 0, 0);
        if (floor == 10) return new Enemy("Hochork", 80, 6, 6, 0, 0);
        if (floor == 15) return new Enemy("Schluchtentroll", 100, 8, 8, 0, 0);
        return new Enemy("Maus", 1, 1, 100, 0, 0);
    }

    private static void regenerateMana(Hero player) { //Methode zum automatischen Regenerieren der Fähigkeiten Ressource des Charakters
        if (player.manadrain > 0) {
            --player.manadrain;
            String resource = player.isMage ? "Mana" : "Energie";
            System.out.print("Du hast 1 " + resource + " erhalten!\nDu hast jetzt noch " + (player.mana - player.manadrain) + " " + resource + "\n");
            System.out.println("Drücke Enter!\n");
            new Scanner(System.in).nextLine();
        }
    }

    private static void printGameResult(Hero player) { //Endnachricht des Spiels, je nachdem ob der Charakter gestorben ist oder den Endboss besiegt hat
        if (player.health < player.death) {
            System.out.print("Du bist tot!");
        } else {
            System.out.print("Der Ogerboss ist tot!\nDein Genozid abgeschlossen!\nAlle Bergvölker sind tot, obwohl sie niemandem etwas getan haben, du Monster!");
        }
    }
}
