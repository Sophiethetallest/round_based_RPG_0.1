import java.util.Random;

public class Abilities {
    String[] ability = new String[6];
    public Abilities() {

    }
    public void abilityName(Hero player) {


        if (player.isMage) {
        	
        	ability[1] = "Heilung";
            ability[2] = "Feuerball";
            ability[3] = "eisige Berührung";
            ability[4] = "arkane Geschosse";
            ability[5] = "Eisschild";
            
        	if (player.lvl < 1) {
        		
        		System.out.println("Welche Fähigkeit willst du verwenden?");
        		System.out.println(ability[1]);
        		System.out.println(ability[2]);
            
        	} else if (player.lvl < 2) {
        		
                System.out.println("Welche Fähigkeit willst du verwenden?");
                System.out.println(ability[1]);
                System.out.println(ability[2]);
                System.out.println(ability[3]);

        	} else if (player.lvl < 3) {
        		
            
                System.out.println("Welche Fähigkeit willst du verwenden?");
                System.out.println(ability[1]);
                System.out.println(ability[2]);
                System.out.println(ability[3]);
                System.out.println(ability[4]);
                
        	} else {
            
                System.out.println("Welche Fähigkeit willst du verwenden?");
                System.out.println(ability[1]);
                System.out.println(ability[2]);
                System.out.println(ability[3]);
                System.out.println(ability[4]);
                System.out.println(ability[5]);
        	}
        }
        if (player.isWarrior) {
        	
            ability[1] = "Blocken";
            ability[2] = "harter Schlag";
            ability[3] = "Tritt in die Eier";
            ability[4] = "schnelle Hiebe";

            if (player.lvl < 1) {
        		
        		System.out.println("Welche Fähigkeit willst du verwenden?");
        		System.out.println(ability[1]);
        		System.out.println(ability[2]);
            
        	} else if (player.lvl < 2) {
        		
                System.out.println("Welche Fähigkeit willst du verwenden?");
                System.out.println(ability[1]);
                System.out.println(ability[2]);
                System.out.println(ability[3]);

        	} else if (player.lvl < 3) {
        		
            
                System.out.println("Welche Fähigkeit willst du verwenden?");
                System.out.println(ability[1]);
                System.out.println(ability[2]);
                System.out.println(ability[3]);
                System.out.println(ability[4]);
                
        	} else {
            
                System.out.println("Welche Fähigkeit willst du verwenden?");
                System.out.println(ability[1]);
                System.out.println(ability[2]);
                System.out.println(ability[3]);
                System.out.println(ability[4]);
                System.out.println(ability[5]);
        	}
        }
        
    }


}
