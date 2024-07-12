package core;

public class User {
    public String username, password, nickName, email, securityQuestion, securityAnswer;
    public String[] cardsId;
    int level, XP, HP, coin;
    String character=null;

    public User(String username, String password, String nickName, String email, String securityQuestion,
            String securityAnswer, String[] cardsId, int level, int XP, int HP, int coin) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.cardsId = cardsId;
        this.level = level;
        this.HP = HP;
        this.XP = XP;
        this.coin = coin;
    }

    public String toString() {
        String output = "User Information:\n";
        output += "username: " + username + "\n";
        output += "password: " + password + "\n";
        output += "nick name: " + nickName + "\n";
        output += "email: " + email + "\n";
        output += "security question: " + securityQuestion + "\n";
        output += "security answer: " + securityAnswer + "\n";
        output += "level: " + level + "\n";
        output += "XP: " + XP + "\n";
        output += "HP: " + HP + "\n";
        output += "coins: " + coin + "\n";

        output += "your cards: \n";
        int j = 0;
        for (String i : cardsId) {

            j++;
            output += j +". "+ DB.getUserCardByID(Session.getInstance().getLoggedUser(), i).toStringUpgrade() + "\n";

        }
        return output;
    }
}
