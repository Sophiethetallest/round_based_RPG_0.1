import java.util.*;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item newItem) { //Klassenmethode zum Hinzufügen neuer Objekte der Klasse Item
        for (Item item : items) {
            if (item.getName().equals(newItem.getName())) { //Überprüfung, ob das Objekt schon vorhanden ist, dann wird nur die Anzahl erhöht
                if (item.isStackable()) {
                    item.increaseAmount(newItem.getAmount());
                    System.out.println(newItem.getName() + " Menge erhöht auf " + item.getAmount());
                    return;
                } else {
                    System.out.println(newItem.getName() + " kann nicht hinzugefügt werden, da es nicht stapelbar ist.");
                    return;
                }
            }
        }
        items.add(newItem); // ansonsten wird ein neues Objekt hinzugefügt
        System.out.println(newItem.getName() + " wurde dem Inventar hinzugefügt.");
    }

    /*public void removeItem(String itemName, int amount) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                item.decreaseAmount(amount);
                if (item.getAmount() == 0) {
                    items.remove(item);
                    System.out.println(itemName + " wurde aus dem Inventar entfernt.");
                } else {
                    System.out.println("Menge von " + itemName + " reduziert auf " + item.getAmount());
                }
                return;
            }
        }
        System.out.println("Item " + itemName + " nicht gefunden.");
    }*/

    public void listItems() { //Klassenmethode zum Auflisten der vorhandenen Itemobjekte
        if (items.isEmpty()) {
            System.out.println("Inventar ist leer.");
        } else {
            System.out.println("Dein Inventar:");
            int index = 1; //erstellen eines Indexes, damit User das Item mit einer Nummer auswählen können
            for (Item item : items) {
                System.out.println(index + ". " + item.getName() + " | Menge: " + item.getAmount());
                index++;
            }
        }
    }
    public void useItemByIndex(int index, Hero player) { //Klassenmethode zum Verwenden von Itemobjekten
        if (index > 0 && index <= items.size()) {
            Item item = items.get(index - 1);

            if (item.healItem) { // Methode zur Gesundheitsregeneration
                item.HealUsage(player, item.heal);
                System.out.println(item.getName() + " benutzt!");

                item.decreaseAmount(1); //Mengenreduzierung oder vollständige Entfernung des Items
                if (item.getAmount() == 0) {
                    items.remove(item);
                    System.out.println("Das war dein letzter " + item.getName());
                }
            } else if (item.resourceHeal) { //Methode zur Resourcenregeneration
                item.HealUsage(player, item.heal);
                System.out.println(item.getName() + " benutzt!");

                item.decreaseAmount(1);
                if (item.getAmount() == 0) {
                    items.remove(item);
                    System.out.println("Das war dein letzter " + item.getName());
                }
            } else {
                System.out.println("Dieses Item kann nicht verwendet werden.");
            }
        } else {
            System.out.println("Ungültige Auswahl!");
        }
    }
}

