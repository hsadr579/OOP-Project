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

    // create tables
    public static void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "username TEXT NOT NULL,\n" + "password TEXT NOT NULL,\n" + "security_question_id INTEGER NOT NULL,\n"
                + "security_question_answer TEXT NOT NULL,\n" + "email TEXT NOT NULL,\n" + "nickname TEXT NOT NULL,\n"
                + "xp INTEGER NOT NULL,\n" + "coins INTEGER NOT NULL,\n" + "clan_id INTEGER,\n"
                + "last_failed_login INTEGER NOT NULL,\n" + "login_fail_number INTEGER NOT NULL\n" + ");";
        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS users_cards (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "user_id INTEGER NOT NULL,\n" + "card_id TEXT NOT NULL,\n" + "level INTEGER NOT NULL\n" + ");";
        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS cards (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "name TEXT NOT NULL,\n"
                + "cost INTEGER NOT NULL,\n" + "levelup_cost INTEGER NOT NULL,\n"
                + "duration INTEGER NOT NULL,\n" + "defence INTEGER NOT NULL,\n" + "damage INTEGER NOT NULL,\n"
                + "explanation TEXT NOT NULL,\n" + "type TEXT NOT NULL,\n" + "level INTEGER NOT NULL,\n"
                + "image TEXT NOT NULL\n" + ");";
        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS clans (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "name TEXT NOT NULL,\n" + "owner_id INTEGER NOT NULL);";
        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS security_questions (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "question TEXT NOT NULL\n" + ");";
        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS user_security_questions (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "user_id INTEGER NOT NULL,\n" + "question_id INTEGER NOT NULL,\n" + "answer TEXT NOT NULL\n" + ");";
        command(sql);
    }

    public static void setUserLevel(int id, int newLevel) {

    }

    public static void setUsersXP(int id, int newXP) {
        if (newXP >= (getUserLevel(id) + 1) * 10) {
            setUserLevel(id, getUserLevel(id) + 1);
        }
        // ...
        
    }

    public static User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("security_question_id"), rs.getString("security_question_answer"), new String[] {},
                    rs.getInt("level"),
                    rs.getInt("xp"), rs.getInt("hp"), rs.getInt("coins"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static int getUserLevel(int userId) {
        String sql = "SELECT level FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt("level");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    // public static int getCardUpgradeLevel(String cardId) {
    // // it gets the required level to upgrade a particular card
    // }

    // public static String[] getUserCardsIDs(int id) {
    // // getting the ID of all user's cards
    // }

    public static Card getCardByID(String ID) {
        String sql = "SELECT * FROM cards WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            return new Card(rs.getString("id"), rs.getString("name"), rs.getInt("duration"), rs.getInt("defence"),
                    rs.getInt("damage"), rs.getString("explanation"), rs.getString("type"), rs.getInt("level"),
                    rs.getString("group"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Card getUserCardByID(int userId, String cardId) {
        String sql = "SELECT * FROM users_cards WHERE user_id = ? AND card_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, cardId);
            ResultSet rs = stmt.executeQuery();
            return getCardByID(rs.getString("card_id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String[] getAllCards() {
        String sql = "SELECT id FROM cards";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String[] cards = new String[rs.getFetchSize()];
            int i = 0;
            while (rs.next()) {
                cards[i] = rs.getString("id");
                i++;
            }
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int getUserCoins(int id) {
        String sql = "SELECT coins FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt("coins");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static String getUsernameById(int id) {
        String sql = "SELECT username FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.getString("username");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int getCardCost(String ID) {
        String sql = "SELECT cost FROM cards WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt("cost");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static int getLevelUpCardCost(String ID) {
        String sql = "SELECT levelup_cost FROM cards WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt("levelup_cost");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static void setUsersCoins(int id, int newCoin) {
        String sql = "UPDATE users SET coins = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, newCoin);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void buyCardForUser(int userId, String cardId) {
        String sql = "INSERT INTO `users_cards` (`id`, `user_id`, `card_id`, `level`) VALUES";
        sql += "(NULL, '" + userId + "', '" + cardId + "', '1')";
        command(sql);
    }

    public static void upgradeCardForUser(int userId, String cardId) {
        String sql = "UPDATE users_cards SET level = level + 1 WHERE user_id = '" + userId + "' AND card_id = '"
                + cardId + "'";
        command(sql);
    }

    // ===========================================================
    // USERS
    // ===========================================================

    public static void changeUsername(int userId, String newUsername) {
        String sql = "UPDATE users SET username = '" + newUsername + "' WHERE id = '" + userId + "'";
        command(sql);
    }

    public static void changePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password = '" + newPassword + "' WHERE id = '" + userId + "'";
        command(sql);
    }

    public static void changeEmail(int userId, String newEmail) {
        String sql = "UPDATE users SET email = '" + newEmail + "' WHERE id = '" + userId + "'";
        command(sql);
    }

    public static void changeNickname(int userId, String newNickname) {
        String sql = "UPDATE users SET nickname = '" + newNickname + "' WHERE id = '" + userId + "'";
        command(sql);
    }

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

    public static String[] getUserCardsIDs(int loggedUser) {
        
    }

    public static int getCardUpgradeLevel(String iD) {
       
    }
}
