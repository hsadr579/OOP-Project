package core;

import java.security.PublicKey;
import java.sql.*;

public class DB {
    private static Connection connection;

    private DB() {
    }

    static {
        try {
            String url = "jdbc:sqlite:citywars.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getUserLevel(int userId)
    {

    }

    public static int getCardUpgradeLevel(int cardId)
    {
        //it gets the required level to upgrade a particular card
    }
    public static int[] getUserCardsIDs(int id) {
        // getting the ID of all user's cards
    }

    public static Card getCardByID(int ID) {

    }

    public static int[] getAllCards() {

    }

    public static int getUserCoins(int id) {

    }

    public static String getUsernameById(int id) {

    }

    public static int getCardCost(int ID) {

    }

    public static int getLevelUpCardCost(int ID) {

    }

    public static void setUsersCoins(int id, int newCoin) {

    }

    public static void buyCardForUser(int userId, int cardId) {

    }

    public static void upgradeCardForUser(int userId, int cardId) {

    }
    // ===========================================================
    // USERS
    // ===========================================================

    public static void createUser(String username, String password, int security_question_id,
            String security_question_answer, String email) {
        String sql = "INSERT INTO `users` (`id`, `username`, `password`, `security_question_id`, `security_question_answer`, `email`, `nickname`, `xp`, `coins`, `clan_id`) VALUES";
        sql += "(NULL, '" + username + "', '" + password + "' , '" + security_question_id + "', '"
                + security_question_answer + "', '" + email + "', '0', '0', '0', NULL)";
        command(sql);
    }

    public static boolean usernameExists(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static int getUserId(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static int[] getUserFailedLoginData(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return new int[] { rs.getInt("login_fail_number"), rs.getInt("last_failed_login") };
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String[] getUserSecurityQuestion(String username) {
        String sql = "SELECT security_question_id, security_question_answer FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String question = rs.getString("security_question_id");
                String answer = rs.getString("security_question_answer");
                return new String[] { question, answer };
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void updatePassword(String username, String password) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String[] getUserData(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return new String[] { rs.getString("username"), rs.getString("password"),
                    rs.getString("security_question_id"), rs.getString("security_question_answer"),
                    rs.getString("email"), rs.getString("nickname"), rs.getString("xp"), rs.getString("coins"),
                    rs.getString("clan_id"), rs.getString("last_failed_login"), rs.getString("login_fail_number") };
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void updateLastFailedLogin(String username, int timestamp) {
        String sql = "UPDATE users SET last_failed_login = '" + timestamp + "' WHERE username = '" + username + "';";
        command(sql);
        sql = "UPDATE users SET login_fail_number = login_fail_number + 1 WHERE username = '" + username + "';";
        command(sql);
    }

    public static void resetFailedLoginNumber(String username) {
        String sql = "UPDATE users SET login_fail_number = 0 WHERE username = '" + username + "';";
        command(sql);
    }

    // ===========================================================
    // AUTH
    // ===========================================================

    public static boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // ===========================================================
    // CLANS
    // ===========================================================
    public static void createClan(String name, String tag, int owner_id) {
        String sql = "INSERT INTO `clans` (`id`, `name`, `tag`, `owner_id`, `xp`, `members`, `description`) VALUES";
        sql += "(NULL, '" + name + "', '" + tag + "', '" + owner_id + "', '0', '1', '')";
        command(sql);
    }

    // ===========================================================
    // DB METHODS
    // ===========================================================

    private static void command(String sql) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}