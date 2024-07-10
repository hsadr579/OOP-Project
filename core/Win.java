package core;

public class Win {
    static String winnersName,looserName;
    static int coinGift, coinDec;
    static int newXPW, newXPD;

    static void setUp(String winner,String loos, int gift, int dec, int XPW, int XPD) {
        winnersName = winner;
        looserName = loos;
        coinDec = dec;
        coinGift = gift;
        newXPW = XPW;
        newXPD = XPD;
    }

    static void setOutput()
    {
        String output=" The winner is" + winnersName + "!\n"
                + "The winner got +" + coinGift + "coins and +" + newXPW + "XP\n"
                +"The looser cost -"+coinDec+"coins and got +"+newXPD+"XP";
        if(Game.mode==Mode.BET)
        {
            output+="\n"+winnersName+" is the winner of the bet and got "+Game.betCost+"!";
        }
        Session.getInstance().setOutput(output);

}
