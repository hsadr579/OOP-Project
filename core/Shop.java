package core;

public class Shop {
    static String[] allCards;
    static String[] usersCards;

    static boolean isIdIn(String id) {
        for (String i : usersCards) {
            if (i.equals(id))
                return true;
        }
        return false;
    }

    static String shopToString() {
        String output = "You are in shop menu!\n\nCards that you don't have, and you can buy them:\n";
        int j = 0;
        for (String i : allCards) {
            if (!isIdIn(i)) {
                j++;
                output += j + DB.getCardByID(i).toStringBuy() + "\n";
            }

        }
        output += "\nCards that you already have, and you can upgrade them:\n";
        j = 0;

        for (String i : usersCards) {
            if (Spells.isSpell(i))
                continue;
            j++;
            output += j + DB.getUserCardByID(Session.getInstance().getLoggedUser(), i).toStringUpgrade() + "\n";

        }
        return output;

    }

    public static void configShop() {

        allCards = DB.getAllCards();
        usersCards = DB.getUserCardsIDs(Session.getInstance().getLoggedUser());
        Session.getInstance().setOutput(shopToString());
        Session.getInstance().setCurrentMenu(Menus.SHOP);
    }

    public static void buyCard(String ID) {
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

    public static void upgradeCard(String ID) {
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

    public static void showMyCards() {
        int j = 0;
        String output = "";
        for (String i : usersCards) {

            j++;
            output += j + DB.getUserCardByID(Session.getInstance().getLoggedUser(), i).toStringUpgrade() + "\n";

        }
        Session.getInstance().setOutput(output);
    }

    public static String[] getAllCards() {
        return allCards;
    }

    public static String[] getUsersCards() {
        return usersCards;
    }
}
