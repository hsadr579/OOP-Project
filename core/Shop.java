package core;

public class Shop {
    static int[] allCards;
    static int[] usersCards;

    static boolean isIdIn(int id) {
        for (int i : usersCards) {
            if (i == id)
                return true;
        }
        return false;
    }

    static String shopToString() {
        String output = "You are in shop menu!\n\nCards that you don't have, and you can buy them:\n";
        int j = 0;
        for (int i : allCards) {
            if (!isIdIn(i)) {
                j++;
                output += j + DB.getCardByID(i).toString() + "\n";
            }

        }
        output += "\nCards that you already have, and you can upgrade them:\n";
        j = 0;
        for (int i : usersCards) {

            j++;
            output += j + DB.getCardByID(i).toString() + "\n";

        }
        return output;

    }

    public static void configShop() {

        allCards = DB.getAllCards();
        usersCards = DB.getUserCardsIDs(Session.getInstance().getLoggedUser());
        Session.getInstance().setOutput(shopToString());
    }

    public static void buyCard(int ID) {
        int id = Session.getInstance().getLoggedUser();
        int ourCoins = DB.getUserCoins(id);
        int cost = DB.getCardCost(ID);
        if (cost > ourCoins) {
            Session.getInstance().setOutput(Outputs.NOT_ENOUGH_COIN_BUY);
            return;
        }
        DB.setUsersCoins(id, ourCoins - cost);
        DB.buyCardForUser(id, ID);
        Session.getInstance().setOutput(Outputs.SUCCESS_BUY_CARD);
    }

    public static void upgradeCard(int ID) {
        int id = Session.getInstance().getLoggedUser();
        int ourCoins = DB.getUserCoins(id);
        int cost = DB.getCardCost(ID);
        if (cost > ourCoins) {
            Session.getInstance().setOutput(Outputs.NOT_ENOUGH_COIN_UPGRADE);
            return;
        }
        if (DB.getUserLevel(id) < DB.getCardUpgradeLevel(ID)) {
            Session.getInstance().setOutput(Outputs.LEVEL_NOT_ENOUGH);
        }
        DB.setUsersCoins(id, ourCoins - cost);
        DB.upgradeCardForUser(id, ID);
        Session.getInstance().setOutput(Outputs.SUCCESS_UPGRADE_CARD);
    }

    public static int[] getAllCards() {
        return allCards;
    }

    public static int[] getUsersCards() {
        return usersCards;
    }
}
