import java.util.Random;
import java.util.Scanner;

public class Fight {
    private Scanner scan = new Scanner(System.in);
    private Random rand = new Random();
    private int abilityNr, dmg, weapon, mobDmg, accurat, hardhit = 0, fire = 0;
    private String input;
    private Abilities ability = new Abilities();
    private MageAbility mageAbility = new MageAbility();
    private WarriorAbility warriorAbility = new WarriorAbility();

    public Fight(Hero player, Enemy enemy) {
        while (enemy.mobLife > 0 && player.health > player.death) {
            battleMenu(player, enemy);
        }
    }

    private void battleMenu(Hero player, Enemy enemy) {
        if (!player.stun) {
            System.out.print("\nAngreifen(1)?\nFähigkeit(2)?\nInventar(3)?\n");
            abilityNr = scan.nextInt();
            if (abilityNr == 1) {
                attack(player, enemy);
            } else if (abilityNr == 2) {
                useAbilities(player, enemy);
            } else if (abilityNr == 3) {
                useInventory(player);
            }
            enemyAttack(player, enemy);
            levelUp(player, enemy);
        } else {
            System.out.print("Du bist betäubt\n");
            player.stun = false;
        }
    }

    private void attack(Hero player, Enemy enemy) {
        dmg = rand.nextInt(11) + 1 + player.str * 2 + weapon;
        int accurat = rand.nextInt(100) + 1 - player.accuracy;
        if (accurat < 0) {
            System.out.print("Du hast " + dmg + " Schaden gemacht!\n");
            enemy.mobLife -= dmg;
            System.out.print("Der Gegner hat noch " + enemy.mobLife + " Gesundheit!\n");
        } else System.out.print("Das ging leider daneben\n");
    }

    private void useAbilities(Hero player, Enemy enemy) {
        ability.abilityName(player);
        abilityNr = scan.nextInt();
        if (abilityNr - 2 > player.lvl) {
            System.out.print("Fähigkeit noch nicht freigeschaltet\n");
        } else {
            int accurat = rand.nextInt(100) + 1 - player.accuracy;
            if (accurat < 0) {
                if (player.isMage) {
                    mageAbility.abilityMage(abilityNr, player, enemy, weapon);
                } else if (player.isWarrior) {
                    warriorAbility.abilityWarrior(abilityNr, player, enemy, weapon);
                }
            } else System.out.print("Das ging leider daneben\n");
        }
    }

    private void useInventory(Hero player) {
        while (true) {
            player.inventory.listItems();
            System.out.print("Gib die Nummer des Items ein oder 'x' zum Zurückgehen: ");
            String inputChoice = scan.next();
            if (inputChoice.equalsIgnoreCase("x")) break;
            try {
                int itemIndex = Integer.parseInt(inputChoice);
                player.inventory.useItemByIndex(itemIndex, player);
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe! Bitte eine Zahl oder 'x' eingeben.");
            }
        }
    }

    private void enemyAttack(Hero player, Enemy enemy) {
        if (enemy.mobLife > 0 && enemy.mobStun == 0) {
            mobDmg = Math.max(0, (rand.nextInt(3) + 1 + enemy.mobStrength - player.def));
            if (enemy.name.equalsIgnoreCase("Hobgoblin")&& enemy.mobLife <= (enemy.mobLife/2)) {
                mobDmg *= 2;
                System.out.print("Der Hobgoblin haut zweimal hintereinander zu");
            }
            if (enemy.name.equalsIgnoreCase("Hochork")&& enemy.mobLife <= (enemy.mobLife/2)) {
                fire = 3;
                System.out.print("Der Hochork wirft einen Feuerball und du beginnst zu brennen");
            }
            if (enemy.name.equalsIgnoreCase("Schluchtentroll")&& enemy.mobLife <= (enemy.mobLife/2)) {
                player.stun = true;
                System.out.print("Der Schluchtentroll haut so fest zu, dass du für eine Runde betäubt bist");
            }
            if (enemy.mobBlock > 0) {
                System.out.print("Du konntest den Schaden vollständig blocken und hast keinen Schaden erlitten!\n");
                --enemy.mobBlock;
            } else {
                System.out.print("Du hast " + mobDmg + " Schaden erlitten!\n");
                player.death += mobDmg;
                if (fire > 0) {
                    System.out.print("Du erleidest Verbrennungen und verlierst 1 Gesundheit");
                    ++ player.death;
                    -- fire;
                }
            }
            System.out.print("Du hast noch " + (player.health - player.death) + " Gesundheit!\n");

        } else {
            --enemy.mobStun;
        }
    }

    private void levelUp(Hero player, Enemy enemy) {
        if (enemy.mobLife <= 0) {
            enemy.enemyDeath();
            player.exp += enemy.mobExp;
        }
        if (player.exp >= 10) {
            Hero.lvl(player);
            System.out.print("Stufe: " + player.lvl + "\nDrücke Enter!\n\n");
            scan.nextLine();
            scan.nextLine();
        }
    }

    public void Bossfight(Hero player) {
        Enemy enemy = new Enemy("Ogerboss", 150, 8, 300, 0, 0);
        System.out.print("Der Ogerboss erscheint!\n");
        while (enemy.mobLife > 0 && player.health > player.death) {
            System.out.print("\nAngreifen(1)?\nFähigkeit(2)?\nInventar(3)?\n");
            abilityNr = scan.nextInt();
            if (abilityNr == 1) attack(player, enemy);
            if (abilityNr == 2) useAbilities(player, enemy);
            if (abilityNr == 3) useInventory(player);
            ogreAttack(player, enemy);
        }
        player.lvl++;
    }

    private void ogreAttack(Hero player, Enemy enemy) {
        if (enemy.mobLife > 0 && enemy.mobStun == 0) {
            if (hardhit == 0) {
                int bossAbility = rand.nextInt(10);
                if (bossAbility < 3) {
                    mobDmg = rand.nextInt(10) + 1 + enemy.mobStrength - player.def;
                    if (enemy.mobBlock > 0) mobDmg /= 2;
                    System.out.print("Du hast " + mobDmg + " Schaden erlitten!\n");
                    player.death += mobDmg;
                } else {
                    System.out.print("Der Ogerboss schwingt wild seine fette Keule und wird in der nächsten Runde hart zuschlagen!\n");
                    hardhit = 1;
                }
            } else {
                if (enemy.mobBlock > 0) {
                    System.out.print("Du hast den harten Schlag blockiert!\n");
                    enemy.mobBlock = 0;
                } else {
                    mobDmg = rand.nextInt(60) + 1 + enemy.mobStrength - player.def;
                    System.out.print("Du hast den Angriff nicht blockiert und bekommst den vollen Schaden von " + mobDmg + "!\n");
                    player.death += mobDmg;
                }
                hardhit = 0;
            }
        } else {
            enemy.mobStun--;
        }
    }
}
