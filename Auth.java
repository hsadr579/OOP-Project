public class Auth {

    // register
    public static void register(String username, String password, int security_question_id,
            String security_question_answer, String email) {

        if (Session.getInstance().getLoggedUser() != -1) {
            System.out.println("You are already logged in!");
            return;
        }

        if (!DB.usernameExists(username)) {
            DB.createUser(username, password, security_question_id, security_question_answer, email);
        } else {
            System.out.println("Username already exists");
        }
    }

    // login
    public static void login(String username, String password) {

        if (Session.getInstance().getLoggedUser() != -1) {
            System.out.println("You are already logged in!");
            return;
        }

        int[] failedLoginData = DB.getUserFailedLoginData(username);
        int loginFailNumber = failedLoginData[0];
        int lastFailedLogin = failedLoginData[1];
        int current_timestamp = (int) (System.currentTimeMillis() / 1000);
        if (current_timestamp < lastFailedLogin + 5 * loginFailNumber) {
            System.out.println(
                    "Try again in " + (lastFailedLogin + 5 * loginFailNumber - current_timestamp) + " seconds!");
            return;
        }

        if (!DB.usernameExists(username)) {
            System.out.println("Username doesn't exist!");
            return;
        }

        if (DB.login(username, password)) {
            Session.getInstance().setLoggedUser(DB.getUserId(username));
            Session.getInstance().setCurrentMenu("main menu");
            DB.resetFailedLoginNumber(username);
        } else {
            System.out.println("Password and Username don't match!");
            current_timestamp = (int) (System.currentTimeMillis() / 1000);
            DB.updateLastFailedLogin(username, current_timestamp);
        }
    }

    // logout

    public static void logout() {
        if (Session.getInstance().getLoggedUser() == -1) {
            System.out.println("You are not logged in!");
            return;
        }
        Session.getInstance().setLoggedUser(-1);
        Session.getInstance().setCurrentMenu("login menu");
    }

    // forgot password (asks security question and if the answer matches, resets the
    // password)
    public static void forgotPassword(String username) {
        if (Session.getInstance().getLoggedUser() != -1) {
            System.out.println("You are already logged in!");
            return;
        }

        if (!DB.usernameExists(username)) {
            System.out.println("Username doesn't exist!");
            return;
        }

        String[] securityQuestionData = DB.getUserSecurityQuestion(username);
        String securityQuestion = securityQuestionData[0];
        String securityQuestionAnswer = securityQuestionData[1];

        // System.out.println("Security Question: " + securityQuestion);
        // System.out.print("Answer: ");
        // String answer = new Scanner(System.in).nextLine();

        // if (answer.equals(securityQuestionAnswer)) {
        // System.out.print("Enter new password: ");
        // String newPassword = new Scanner(System.in).nextLine();
        // DB.updatePassword(username, newPassword);
        // } else {
        // System.out.println("Answer is incorrect!");
        // }
    }

}
