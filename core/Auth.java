
package core;

public class Auth {

    // register
    public static void register(String username, String password, int security_question_id,
            String security_question_answer, String email) {

        if (Session.getInstance().getLoggedUser() != -1) {
            Session.getInstance().setOutput("You are already logged in!");
            return;
        }

        if (!Utils.usernameIsValid(username)) {
            Session.getInstance().setOutput("Username is not valid!");
            return;
        }

        if (password == "") {
            password = Utils.generateRandomString(8);
        } else if (!Utils.passwordIsValid(password)) {
            Session.getInstance().setOutput("Password is not valid!");
            return;
        }


        if (!DB.usernameExists(username)) {
            DB.createUser(username, password, security_question_id, security_question_answer, email);
        } else {
            Session.getInstance().setOutput("Username already exists");
        }
    }

    // login
    public static void login(String username, String password) {

        if (Session.getInstance().getLoggedUser() != -1) {
            Session.getInstance().setOutput("You are already logged in!");
            return;
        }

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

        // Session.getInstance().setOutput("Security Question: " + securityQuestion);
        // System.out.print("Answer: ");
        // String answer = new Scanner(System.in).nextLine();

        // if (answer.equals(securityQuestionAnswer)) {
        // System.out.print("Enter new password: ");
        // String newPassword = new Scanner(System.in).nextLine();
        // DB.updatePassword(username, newPassword);
        // } else {
        // Session.getInstance().setOutput("Answer is incorrect!");
        // }
    }

}
