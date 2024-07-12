package core;

public class Profile {
    public static void showInfo() {

        String info = getUser().toString();
        Session.getInstance().setOutput(info);
    }

    public static User getUser() {
        return DB.getUserById(Session.getInstance().getLoggedUser());
    }

    public static void changeUsername(String newUsername) {
        if (Utils.usernameIsValid(newUsername)) {
            if (DB.usernameExists(newUsername)) {
                Session.getInstance().setOutput(Outputs.ERROR_DUPLICATE_USERNAME);
            } else {
                DB.changeUsername(Session.getInstance().getLoggedUser(), newUsername);
                Session.getInstance().setOutput(Outputs.SUCCESS_CHANGE_USERNAME);

            }
        } else {
            Session.getInstance().setOutput(Outputs.ERROR_INVALID_USERNAME);
        }
    }

    public static void changePassword(String oldPass, String newPass) {
        if (oldPass.equals(DB.getUserById(Session.getInstance().getLoggedUser()).password)) {
            if (!newPass.equals(oldPass)) {
                if (Utils.passwordIsValid(newPass)) {

                    DB.changePassword(Session.getInstance().getLoggedUser(), newPass);
                    Session.getInstance().setOutput(Outputs.SUCCESS_CHANGE_PASSWORD);
                } else {
                    Session.getInstance().setOutput(Outputs.ERROR_INVALID_PASSWORD_1);
                }
            } else {
                Session.getInstance().setOutput(Outputs.ERROR_SAME_PASSWORD);
            }
        } else {
            Session.getInstance().setOutput(Outputs.ERROR_PASSWORDS_NOT_SAME);
        }
    }

    public static void changeEmail(String newEmail) {
        if (Utils.emailIsValid(newEmail)) {

            DB.changeEmail(Session.getInstance().getLoggedUser(), newEmail);
            Session.getInstance().setOutput(Outputs.SUCCESS_CHANGE_EMAIL);

        } else {
            Session.getInstance().setOutput(Outputs.ERROR_INVALID_EMAIL);
        }
    }

    public static void changeNickname(String newNickname) {

        DB.changeNickname(Session.getInstance().getLoggedUser(), newNickname);
        Session.getInstance().setOutput(Outputs.SUCCESS_CHANGE_NICKNAME);

    }
}
