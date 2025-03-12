import java.util.Random;
import java.util.Scanner;

public class Encounter {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    String input;

    //Erstellung von Item Objekten
    Item smallHeal = new Item("kleiner Heiltrank",1,5,true,false,true);
    Item midHeal = new Item("mittlerer Heiltrank",1,10,true,false,true);
    Item bigHeal = new Item("großer Heiltrank",1,20,true,false,true);

    Item smallmana = new Item("kleiner Manatrank",1,5,false,true,true);
    Item midmana = new Item("mittlerer Manatrank",1,10,false,true,true);
    Item bigmana = new Item("großer Manatrank",1,15,false,true,true);


    public Encounter(){

    }
    public void encounter(int weapon, Hero player) { //switch case Auflistung aller möglichen random encounter, unendlich erweiterbar
        int situation = rand.nextInt(30) + 1;

        switch (situation) {
            case 1:
                if (player.isMage) {
                    System.out.println("Du hast einen besseren Stab gefunden!\nIntelligenz +1");
                    player.intel++;
                }
                if (player.isWarrior) {
                    System.out.println("Du hast ein besseres Schwert gefunden!\nStärke +1");
                    player.str++;
                }
                break;

            case 2:
                if (player.isMage) {
                    System.out.println("Du hast eine bessere Robe gefunden!\nIntelligenz +1\nRüstung +1");
                    player.intel++;
                    player.def++;
                }
                if (player.isWarrior) {
                    System.out.println("Du hast eine bessere Rüstung gefunden!\nStärke +1\nRüstung +2");
                    player.str++;
                    player.def += 2;
                }
                break;

            case 3:
                player.death = Math.max(0, player.death - 10);
                System.out.println("Du findest einen heiligen Brunnen und trinkst daraus!\n" +
                        "Du wurdest um 10 geheilt!\nDeine Gesundheit beträgt jetzt: " + (player.health - player.death));
                break;

            case 4:
                player.death += rand.nextInt(5) + 1;
                System.out.println("Du bist in eine Falle geraten!\nDu hast Schaden erlitten und jetzt noch " +
                        (player.health - player.death) + " Gesundheit!");
                break;

            case 5:
                if (player.isMage) ++player.intel;
                if (player.isWarrior) ++player.str;
                System.out.println("Du begegnest dem Goblin Slayer, der gerade in Goblinärsche tritt.\n" +
                        "Der Anblick motiviert dich, darum machst du jetzt jedes Mal einen extra Schadenspunkt!");
                break;

            case 6:
                player.death = Math.max(0, player.death - 10);
                System.out.println("Du begegnest 13 Zwergen, die einen genervten Hobbit hinter sich her schleifen.\n" +
                        "Sie laden dich ein, mit ihnen zu rasten und zu speisen.\n" +
                        "Du wurdest um 10 geheilt!\nDeine Gesundheit beträgt jetzt: " + (player.health - player.death));
                break;

            case 7:
                player.death += rand.nextInt(5) + 1;
                System.out.println("Du trittst auf einen fürchterlich spitzen Stein!\n" +
                        "Du hast Schaden erlitten und hast jetzt noch " + (player.health - player.death) + " Gesundheit!");
                break;

            case 8:
                if (player.lvl <= 1) {
                    player.inventory.addItem(smallHeal);
                } else if (player.lvl == 2) {
                    player.inventory.addItem(midHeal);
                } else {
                    player.inventory.addItem(bigHeal);
                }
                break;

            case 9:
                if (player.lvl <= 1) {
                    player.inventory.addItem(smallmana);
                } else if (player.lvl == 2) {
                    player.inventory.addItem(midmana);
                } else {
                    player.inventory.addItem(bigmana);
                }
                break;

            case 10:
                System.out.println("Ein alter Einsiedler sitzt am Wegesrand und bietet dir eine mysteriöse Flasche an. Willst du sie annehemen?(Y/X)");
                if(scan.nextLine().equalsIgnoreCase("y")) {
                    if (rand.nextBoolean()) {
                        System.out.println("Du trinkst und fühlst dich gestärkt!\nAlle Werte +1");
                        player.str++;
                        player.intel++;
                        player.def++;
                    } else {
                        System.out.println("Du trinkst und fühlst dich plötzlich schwach...\nAlle Werte -1");
                        player.str = Math.max(1, player.str - 1);
                        player.intel = Math.max(1, player.intel - 1);
                        player.def = Math.max(1, player.def - 1);
                    }
                } else System.out.println("Du lehnst die Flasche zur Sicherheit ab");
                break;

            case 11:
                System.out.println("Du stößt auf eine verfallene Hütte und durchsuchst sie...");
                if (rand.nextInt(100) < 30) {
                    System.out.println("Du findest eine verstaubte Truhe mit Gold!");
                    player.gold += 50;
                } else {
                    int damage = rand.nextInt(5) + 5;
                    player.death += damage;
                    System.out.println("Ein Rudel hungriger Ratten greift dich an! Du hast " + damage + " Schaden erhalten");
                }
                break;

            case 12:
                System.out.println("Ein Wahrsager taucht aus dem Nichts auf und liest deine Zukunft.");
                if (rand.nextBoolean()) {
                    System.out.println("\"Großes Glück erwartet dich!\" Du fühlst dich sicherer.\nRüstung +2");
                    player.def += 2;
                } else {
                   System.out.println("\"Ein Schatten folgt dir...\" Du wirst nervös und unaufmerksam.\nTrefferchance -5%");
                    player.accuracy = Math.max(0, player.accuracy - 5);
                }
                break;

            case 13:
                System.out.println("Du findest einen geheimnisvollen Runenstein mit alter Magie.");
                if (player.isMage) {
                    System.out.println("Du verstehst die Runen und absorbierst die Macht!\nIntelligenz +2");
                    player.intel += 2;
                } else {
                    System.out.println("Die Magie ist überwältigend! Du erleidest 3 Schaden.");
                    player.death += 3;
                }
                break;

            case 14:
                System.out.println("Du hörst ein Rascheln in den Büschen...");
                if (rand.nextInt(100) < 40) {
                    System.out.println("Ein Dieb springt heraus und stiehlt 20 Gold!");
                    player.gold = Math.max(0, player.gold - 20);
                } else {
                    System.out.println("Nur ein paar harmlose Kaninchen.");
                }
                break;

            case 15:
                System.out.println("Ein freundlicher Händler bietet dir einen Trank an. Er kostet 30 Gold und du hast noch "
                        + player.gold + ", möchtest du ihn kaufen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 30) {
                        System.out.println("Du kaufst den Trank für 30 Gold.\nDu erhältst einen großen Heiltrank.");
                        player.gold -= 30;
                        player.inventory.addItem(bigHeal);
                    } else {
                        System.out.println("Kein Geld, kein Trank! Der Händler lacht und geht weiter.");
                    }
                } else System.out.println("Wer nicht will, hat schon");
                break;

            case 16:
                System.out.println("Ein dunkler Nebel zieht auf... Deine Sinne trügen dich.");
                System.out.println("Du verlierst die Orientierung und verlierst etwas Zeit.");
                // Mögliche zukünftige Mechanik, eines Zeit oder Runden verlust.
                break;

            case 17:
                System.out.println("Du stolperst über eine alte Schatzkarte!\n Vielleicht findest du bald einen Schatz...");
                // Dies könnte einen späteren Bonus-Encounter freischalten.
                break;

            case 18:
                System.out.println("Ein seltsamer Fremder spricht dich an: \"Wähle eine Zahl zwischen 1 und 3.\"");
                int choice = scan.nextInt(3);
                if (choice == 1) {
                    System.out.println("\"Glückwunsch! Hier, ein Geschenk!\"\nDu erhältst 100 Gold.");
                    player.gold += 100;
                } else if (choice == 2) {
                    System.out.println("\"Tja, Pech gehabt!\" Der Fremde lacht und verschwindet.");
                } else {
                    System.out.println("Der Fremde schlägt dich kichernd mit einem Stock! Du verlierst 5 Gesundheit.");
                    player.death += 5;
                }
                break;

            case 19:
                System.out.println("Du findest eine verlassene Schmiede.");
                if (player.isWarrior) {
                    System.out.println("Du verbesserst deine Waffe!\nSchaden um 2 erhöht");
                    player.str += 2;
                } else {
                    System.out.println("Nichts Nützliches für dich hier.");
                }
                break;

            case 20:
                System.out.println("Du triffst einen alten Scharfschützen, der dich herausfordert: \"Triff diese Flasche aus 20 Schritt Entfernung!\"");
                if (rand.nextInt(100) < 70) {
                    System.out.println("Du triffst die Flasche perfekt!\nTrefferchance um 5 erhöht");
                    player.accuracy += 5;
                } else {
                    System.out.println("Daneben! Der Scharfschütze schüttelt enttäuscht den Kopf.");
                }
                break;

            case 21:
                System.out.println("Ein reisender Barde bietet an, dir eine inspirierende Geschichte zu erzählen... für 40 Gold." +
                        "Du hast noch " + player.gold + ", willst du ihn dafür bezahlen? (Y/X)");
                if(scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 40) {
                        player.gold -= 40;
                        player.accuracy += 3;
                        player.death -= 5;
                        player.manadrain -= 5;
                        System.out.println("Du hörst zu und fühlst dich motiviert!\nTrefferchance um 3 erhöht!\n" +
                                "Deine Gesundheit und deine Resource wurde um 5 geheilt\n" +
                                "Du hast jetzt noch " + (player.health - player.death) + "Gesundheit und " +
                                (player.mana - player.manadrain) + "Mana");

                    } else {
                        System.out.println("\"Tja, Geschichten sind nicht umsonst!\" Der Barde zieht weiter.");
                    }
                } else System.out.println("\"Geiziges Schwein\" ruft dir der Barde hinterher");
                break;

            case 22:
                System.out.println("Du findest eine kleine Arena, wo Gladiatoren um Gold kämpfen. Du hast noch " +
                        player.gold + ", möchtest du Wetten abschließen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 50) {
                        System.out.println("Du setzt 50 Gold auf einen Kämpfer...");
                        if (rand.nextBoolean()) {
                            System.out.println("Dein Kämpfer gewinnt! Du erhältst 100 Gold.");
                            player.gold += 50;
                        } else {
                            System.out.println("Dein Kämpfer verliert... und dein Gold ist weg.");
                            player.gold -= 50;
                        }
                    } else {
                        System.out.println("Du hast nicht genug Gold, um zu wetten.");
                    }
                } else System.out.println("Du beschließt das sich Glückspiel nicht lohnt");
                break;

            case 23:
                System.out.println("Ein zwielichtiger Händler flüstert dir zu: \"Ich habe ein mächtiges Amulett... für 75 Gold.\" " +
                        "Du hast noch " + player.gold + ", möchtest du es kaufen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 75) {
                        System.out.println("Du kaufst das Amulett und fühlst eine unbekannte Macht!\nIntelligenz +2");
                        player.gold -= 75;
                        player.intel += 2;
                    } else {
                        System.out.println("\"Kein Gold, kein Amulett!\" Er verschwindet in der Menge.");
                    }
                } else System.out.println("Der Mann sieht viel zu zwielichtig aus, es ist besser nicht von ihm zu kaufen");
                break;

            case 24:
                System.out.println("Ein fahrender Medicus bietet an, dich zu heilen... für 30 Gold. Du hast noch " +
                        player.gold + ", möchtest du die Hilfe annehemen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 30) {
                        System.out.println("Der Medicus versorgt deine Wunden.\nDu wirst um 15 geheilt.");
                        player.gold -= 30;
                        player.death = Math.max(0, player.death - 15);
                    } else {
                        System.out.println("Der Medicus schüttelt den Kopf: \"Ich arbeite nicht umsonst.\"");
                    }
                } else System.out.println("Du bist dir sicher, dass du die Hilfe nicht benötigst");
                break;

            case 25:
                System.out.println("Du stößt auf eine verlassene Bogenschießanlage.");
                System.out.println("Du übst für eine Weile und verbesserst deine Präzision!\nTrefferchance +4%");
                player.accuracy += 4;
                break;

            case 26:
                System.out.println("Ein alter Mönch bietet dir eine mysteriöse Schriftrolle für 60 Gold an. Du hast noch " +
                        player.gold + ", möchtest du sie kaufen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 60) {
                        System.out.println("Du kaufst die Schriftrolle und spürst einen Energieschub!\nRüstung +2, Intelligenz +1");
                        player.gold -= 60;
                        player.def += 2;
                        player.intel += 1;
                    } else {
                        System.out.println("Der Mönch schüttelt den Kopf und geht.");
                    }
                } else System.out.println("Besser nich, am Ende wirst du noch verflucht");
                break;

            case 27:
                System.out.println("Ein geheimnisvoller Markt lockt mit seltenen Waren. Willst du dich umsehen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 100) {
                        System.out.println("Du kaufst eine seltsame Tinktur und fühlst dich gestärkt!\nStärke +2, Trefferchance +3%");
                        player.gold -= 100;
                        player.str += 2;
                        player.accuracy += 3;
                    } else {
                        System.out.println("Die Preise sind zu hoch für dich.");
                    }
                } else System.out.println("Der Markt sieht einfach viel zu shady aus");
                break;

            case 28:
                System.out.println("Du findest einen Waffenschmied, der deine Waffe verbessern kann... für 80 Gold. " +
                        "Du hast noch " + player.gold + ", möchtest du ihn dafür bezahlen? (Y/N)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 80) {
                        System.out.println("Der Schmied arbeitet hart und verbessert deine Waffe!\nAngriff +2, Trefferchance +2%");
                        player.gold -= 80;
                        player.str += 2;
                        player.accuracy += 2;
                    } else {
                        System.out.println("\"Komm wieder, wenn du mehr Gold hast.\"");
                    }
                }  else System.out.println("Deine Waffe ist jetzt schon perfekt");
                break;

            case 29:
                System.out.println("Ein mysteriöser Magier bietet dir ein Glücksspiel an: 50 Gold für eine magische Belohnung. " +
                        "Du hast noch " + player.gold + ", traust du dich am Glückspiel teilzunehmen? (Y/X)");
                if (scan.nextLine().equalsIgnoreCase("y")) {
                    if (player.gold >= 50) {
                        player.gold -= 50;
                        int magicRoll = rand.nextInt(3);
                        if (magicRoll == 0) {
                            System.out.println("Die Magie stärkt dich!\nStärke +2, Rüstung +2");
                            player.str += 2;
                            player.def += 2;
                        } else if (magicRoll == 1) {
                            System.out.println("Die Magie verwirrt dich... Du verlierst 10 Gesundheit.");
                            player.death += 10;
                        } else {
                            System.out.println("Nichts passiert. Der Magier lacht und verschwindet.");
                        }
                    } else {
                        System.out.println("\"Kein Gold, kein Zauber!\" Der Magier schüttelt den Kopf.");
                    }
                } else System.out.println("Glücksspiel ist nur was für Verlierer");
                break;

            case 30:
                System.out.println("Du findest einen seltsamen Spiegel. Dein Spiegelbild sieht anders aus...");
                System.out.println("Ein Gefühl der Klarheit durchströmt dich!\nTrefferchance +5%");
                player.accuracy += 5;
                break;

        }

        next();
    }

    public void next() {
        System.out.println("Drücke Enter!\n");
        input = scan.nextLine();
    }
}
