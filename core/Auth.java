
package core;

public class Auth {
    static String currentCapcha, registeringUsername, registeringPassword, registeringNickname, registeringEmail,
            secAnswer;
    static int secID;

    public static void preRegister(String username, String password, String passConf, String nickName, String email) {

        if(DB.usernameExists(username))
        {
            Session.getInstance().setOutput(Outputs.ERROR_DUPLICATE_USERNAME);
            return;
        }
        if (!Utils.usernameIsValid(username)) {
            Session.getInstance().setOutput(Outputs.ERROR_INVALID_USERNAME);
            return;
        }
        if (passConf != null)
            if (!passConf.equals(password)) {
                Session.getInstance().setOutput(Outputs.ERROR_PASSWORDS_NOT_SAME);
                return;
            }
        if (password.equals("random")) {
            password = Utils.generateRandomString(8);
        } else if (!Utils.passwordIsValid(password)) {
            Session.getInstance().setOutput(Outputs.ERROR_INVALID_PASSWORD_1);
            return;
        } else if (!Utils.emailIsValid(email)) {
            Session.getInstance().setOutput(Outputs.ERROR_INVALID_EMAIL);
            return;
        }
        registeringUsername = username;
        registeringPassword = password;
        registeringEmail = email;
        registeringNickname = nickName;
        Session.getInstance().setCurrentMenu(Menus.SECURITY_QUESTION);
        Session.getInstance().setOutput(Outputs.SUCCESS_CREATE_USER);

    }

    private static String makeNewCapcha() {
        currentCapcha=Utils.generateRandomString(4);

        return Utils.convertStringToAsciiArt(currentCapcha);
    }

    public static void setSecurityQ(int id, String answer, String confirm) {
        if (!answer.equals(confirm)) {
            Session.getInstance().setOutput(Outputs.ERROR_ANSWERS_NOT_SAME);
            return;
        }
        secID = id;
        secAnswer = answer;
        Session.getInstance().setCurrentMenu(Menus.CAPCHA);
        Session.getInstance().setOutput(makeNewCapcha());

    }

    public static void checkCapcha(String c) {
        if (currentCapcha.equals(c)) {
            register(registeringUsername, registeringPassword, secID, secAnswer, registeringEmail);
            DB.changeNickname(DB.getUserId(registeringUsername), registeringNickname);
            String[] cards = DB.getAllCards();
            String[] userRandom = Utils.getRandomElements(cards);
            int id = DB.getUserId(registeringUsername);
            for (String i : userRandom) {
                DB.buyCardForUser(id, i);
            }
            Session.getInstance().setCurrentMenu(Menus.SIGN_UP);

        } else {
            Session.getInstance().setOutput(makeNewCapcha());
        }
    }
    // register

    public static void register(String username, String password, int security_question_id,
            String security_question_answer, String email) {

        if (!DB.usernameExists(username)) {
            DB.createUser(username, password, security_question_id, security_question_answer, email);

        } else {
            Session.getInstance().setOutput("Username already exists");
        }
    }

    // login
    public static void login(String username, String password) {

        int[] failedLoginData = DB.getUserFailedLoginData(username);
        int loginFailNumber = failedLoginData[0];
        int lastFailedLogin = failedLoginData[1];
        int current_timestamp = (int) (System.currentTimeMillis() / 1000);
        if (current_timestamp < lastFailedLogin + 5 * loginFailNumber) {
            Session.getInstance().setOutput(
                    "Try again in " + (lastFailedLogin + 5 * loginFailNumber - current_timestamp) + " seconds!");
            return;
        }

        if (!DB.usernameExists(username)) {
            Session.getInstance().setOutput("Username doesn't exist!");
            return;
        }

        if (DB.login(username, password)) {
            Session.getInstance().setLoggedUser(DB.getUserId(username));
            Session.getInstance().setCurrentMenu(Menus.MAIN);
            Session.getInstance().setOutput(Outputs.SUCCESS_LOGIN);
            DB.resetFailedLoginNumber(username);
        } else {
            Session.getInstance().setOutput("Password and Username don't match!");
            current_timestamp = (int) (System.currentTimeMillis() / 1000);
            DB.updateLastFailedLogin(username, current_timestamp);
        }
    }

    // logout

    public static void logout() {
        if (Session.getInstance().getLoggedUser() == -1) {
            Session.getInstance().setOutput("You are not logged in!");
            return;
        }
        Session.getInstance().setLoggedUser(-1);

    }

    // forgot password (asks security question and if the answer matches, resets the
    // password)
    public static void forgotPassword(String username) {
        if (Session.getInstance().getLoggedUser() != -1) {
            Session.getInstance().setOutput("You are already logged in!");
            return;
        }

        if (!DB.usernameExists(username)) {
            Session.getInstance().setOutput("Username doesn't exist!");
            return;
        }

        String[] securityQuestionData = DB.getUserSecurityQuestion(username);
        String securityQuestion = securityQuestionData[0];
        String securityQuestionAnswer = securityQuestionData[1];

    }

}
