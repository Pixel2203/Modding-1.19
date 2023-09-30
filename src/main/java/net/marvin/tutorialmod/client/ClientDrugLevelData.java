package net.marvin.tutorialmod.client;

public class ClientDrugLevelData {
    private static int playerDrugLevel;
    public static void set(int drugLevel){playerDrugLevel = drugLevel;}

    public static int getPlayerDrugLevel() {
        return playerDrugLevel;
    }
}
