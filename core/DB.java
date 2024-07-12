package core;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static Connection connection;

    private DB() {
    }

    static {
        try {
            String url = "jdbc:sqlite:citywars.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            createTables();
            System.out.println("Tables Ok.");
            createCards();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public static void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "username TEXT NOT NULL,\n" + "password TEXT NOT NULL,\n" + "security_question_id INTEGER NOT NULL,\n"
                + "security_question_answer TEXT NOT NULL,\n" + "email TEXT NOT NULL,\n" + "nickname TEXT NOT NULL,\n"
                + "level INTEGER NOT NULL,\n"
                + "xp INTEGER NOT NULL,\n" + "coins INTEGER NOT NULL,\n" + "hp INTEGER NOT NULL DEFAULT '0',\n" + "clan_id INTEGER,\n"
                + "last_failed_login INTEGER NOT NULL,\n" + "login_fail_number INTEGER NOT NULL\n" + ");";
        command(sql);

        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS users_cards (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "user_id INTEGER NOT NULL,\n" + "card_id TEXT NOT NULL,\n" + "level INTEGER NOT NULL\n" + ");";
        command(sql);

        sql = "CREATE TABLE IF NOT EXISTS games (\n" + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "user_id INTEGER NOT NULL,\n" + "opponent_id INTEGER NOT NULL,\n" + "opponent_level INTEGER NOT NULL,\n" + "is_winner INTEGER NOT NULL,\n"
                + "prize TEXT NOT NULL,\n" + "punish TEXT NOT NULL\n" + ");";
        command(sql);


        sql = "CREATE TABLE IF NOT EXISTS cards (\n" 
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + "name TEXT NOT NULL,\n"
                    + "cost INTEGER NOT NULL,\n"
                    + "levelup_cost INTEGER NOT NULL,\n"
                    + "duration INTEGER NOT NULL,\n"
                    + "defence INTEGER NOT NULL,\n"
                    + "damage INTEGER NOT NULL,\n"
                    + "explanation TEXT NOT NULL,\n"
                    + "gr TEXT NOT NULL,\n"
                    + "type TEXT NOT NULL,\n"
                    + "level INTEGER NOT NULL,\n"
                    + "upgrade_level INTEGER NOT NULL\n"
                    + ");";
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

    public static void createCards() {
        String sql = "SELECT * FROM cards";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        

        sql = "INSERT INTO `cards` (`id`, `name`, `duration`, `defence`, `damage`, `explanation`, `levelup_cost`, `cost`, `gr`, `type`, `level`, `upgrade_level`) VALUES";
        sql += "(NULL, 'Scout Trooper', '2', '20', '10', '-', '10', '30', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Storm Trooper', '2', '15', '10', '-', '12', '30', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Snow Trooper', '4', '20', '20', '-', '15', '30', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Executioner Trooper', '2', '20', '30', '-', '20', '30', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Range Trooper', '3', '40', '30', '-', '20', '30', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Shock Trooper', '4', '24', '16', '-', '20', '30', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Rocket Trooper', '5', '20', '50', '-', '30', '35', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Flame Trooper', '4', '24', '16', '-', '10', '35', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Death Trooper', '2', '30', '28', '-', '20', '40', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Armored Commando', '2', '40', '24', '-', '40', '60', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Crimson Guard', '4', '30', '64', '-', '50', '70', 'attacker', 'imperial', '1', '2'),";
        sql += "(NULL, 'Darth Vader', '1', '50', '60', '-', '80', '100', 'attacker', 'imperial', '1', '3'),";
        sql += "(NULL, 'Palpatine', '1', '20', '30', '-', '70', '80', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'Inquisitor', '1', '35', '35', '-', '40', '60', 'attacker', 'imperial', '1', '1'),";
        sql += "(NULL, 'TIE fighter', '5', '1000000', '50', '-', '50', '50', 'attacker', 'imperial', '1', '2'),";
        sql += "(NULL, 'Star Destroyer', '1', '0', '0', 'If takes damage, deal 2x damage to opponent', '-', '100', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Shield', '1', '0', '0', 'Prevents any damage', '-', '20', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Heal', '1', '0', '0', 'Heal player +40', '-', '20', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Clover', '1', '0', '0', 'Halves the damage of opponent', '-', '20', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Poison', '1', '0', '0', 'Deal +30 Damage and cancel attack', '-', '40', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Fixer', '0', '0', '0', 'Change gap cell', '-', '20', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Thief', '0', '0', '0', 'Remove card from opponent and add to your hand', '-', '30', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Swamp', '0', '0', '0', 'Redused damage and defence of two oppennet card', '-', '30', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Clone', '0', '0', '0', 'Copy a card from your hand and add it to your hand', '-', '30', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Hidden', '0', '0', '0', 'Hide opponent cards in next turn', '-', '50', 'spell', '-', '1', '1'),";
        sql += "(NULL, 'Luke Skywalker', '4', '30', '40', '-', '50', '84', 'attacker', 'jedi', '1', '2'),";
        sql += "(NULL, 'Mace Windu', '2', '25', '30', '-', '40', '50', 'attacker', 'jedi', '1', '1'),";
        sql += "(NULL, 'Obi-Wan Kenobi', '2', '35', '40', '-', '45', '78', 'attacker', 'jedi', '1', '1'),";
        sql += "(NULL, 'Plo Koon', 2, 20, 24, '-', 40, 56, 'attacker', 'jedi', 1, 1),";
        sql += "(NULL, 'Qui-Gon Jinn', 1, 45, 30, '-', 55, 48, 'attacker', 'jedi', 1, 2),";
        sql += "(NULL, 'Yoda', 1, 50, 50, '-', 60, 100, 'attacker', 'jedi', 1, 3),";
        sql += "(NULL, 'Baby Yoda', 3, 20, 45, '-', 45, 90, 'attacker', 'jedi', 1, 1),";
        sql += "(NULL, 'Cal Kestis', 3, 40, 30, '-', 50, 82, 'attacker', 'jedi', 1, 1),";
        sql += "(NULL, 'Kit Fisto', 2, 25, 26, '-', 30, 40, 'attacker', 'jedi', 1, 1),";
        sql += "(NULL, 'Anakin Skywalker', 1, 50, 40, '-', 60, 98, 'attacker', 'jedi', 1, 3),";
        sql += "(NULL, 'Boba Fett', 2, 50, 30, '-', 60, 90, 'attacker', 'mandalorian', 1, 2),";
        sql += "(NULL, 'Jango Fett', 2, 50, 30, '-', 40, 70, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'Paz Vizsla', 2, 30, 30, '-', 30, 60, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'Din Djarin', 1, 45, 30, '-', 45, 90, 'attacker', 'mandalorian', 1, 2),";
        sql += "(NULL, 'Death Watch', 3, 35, 45, '-', 40, 80, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'The Armorer', 1, 50, 20, '-', 35, 78, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'Tarre Vizsla', 1, 30, 25, '-', 30, 60, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'Pre Vizsla', 2, 30, 20, '-', 30, 60, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'Gar Saxon', 1, 34, 26, '-', 30, 64, 'attacker', 'mandalorian', 1, 1),";
        sql += "(NULL, 'Mythosaurs', 5, 60, 64, '-', 70, 140, 'attacker', 'mandalorian', 1, 3),";
        sql += "(NULL, 'Count Dooku', 1, 30, 45, '-', 55, 95, 'attacker', 'separatist', 1, 2),";
        sql += "(NULL, 'Wat Tambor', 3, 45, 24, '-', 35, 75, 'attacker', 'separatist', 1, 1),";
        sql += "(NULL, 'Tikkes', 2, 25, 20, '-', 25, 50, 'attacker', 'separatist', 1, 1),";
        sql += "(NULL, 'Darth Sidious', 1, 30, 30, '-', 45, 90, 'attacker', 'separatist', 1, 2),";
        sql += "(NULL, 'General Grievous', 4, 50, 40, '-', 60, 100, 'attacker', 'separatist', 1, 2),";
        sql += "(NULL, 'San Hill', 2, 30, 20, '-', 25, 50, 'attacker', 'separatist', 1, 1),";
        sql += "(NULL, 'Nute Gunray', 2, 27, 30, '-', 20, 40, 'attacker', 'separatist', 1, 1),";
        sql += "(NULL, 'Poggle the Lesser', 2, 32, 20, '-', 22, 45, 'attacker', 'separatist', 1, 1),";
        sql += "(NULL, 'Death Star', 4, '1000000', 64, '-', 75, 150, 'attacker', 'separatist', 1, 3),";
        sql += "(NULL, 'Kylo Ren', 1, 36, 30, '-', 42, 86, 'attacker', 'separatist', 1, 1);";

        command(sql);

    }

    public static void setUserLevel(int id, int newLevel) {
        String sql = "UPDATE users SET level = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, newLevel);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setUsersXP(int id, int newXP) {
        if (newXP >= (getUserLevel(id) + 1) * 10) {
            setUserLevel(id, getUserLevel(id) + 1);
        }
        String sql = "UPDATE users SET xp = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, newXP);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = '" + id +"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return new User(rs.getString("username"), 
            rs.getString("password"), rs.getString("nickname"),
            rs.getString("email"), 
            rs.getString("security_question_id"), 
            rs.getString("security_question_answer"), 
            getUserCardsIDs(id),
            rs.getInt("level"),
            rs.getInt("hp"), rs.getInt("xp"), rs.getInt("coins"));
        } catch (SQLException e) {
            System.out.println("SAAAAAAALLAAAAMMMMMMMM");
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

    public static int getCardUpgradeLevel(String cardId) {
        String sql = "SELECT upgrade_level FROM cards WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cardId);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt("upgrade_level");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static String[] getUserCardsIDs(int id) {
        String sql = "SELECT card_id FROM users_cards WHERE user_id = ?";
        List<String> cardList = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cardList.add(rs.getString("card_id"));
            }
            return cardList.toArray(new String[0]);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Card getCardByID(String ID) {
        String sql = "SELECT * FROM cards WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            return new Card(rs.getString("id"), rs.getString("name"), rs.getInt("duration"), rs.getInt("defence"),
                    rs.getInt("damage"), rs.getString("explanation"), rs.getString("type"), rs.getInt("level"),
                    rs.getString("gr"));
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
        List<String> cardList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cardList.add(rs.getString("id"));
            }
            return cardList.toArray(new String[0]);
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

    public static void createUser(String username, String password, int security_question_id, String security_question_answer, String email) {
        String sql = "INSERT INTO `users` (`id`, `username`, `password`, `security_question_id`, `security_question_answer`, `email`, `nickname`, `xp`, `coins`, `clan_id`, `last_failed_login`, `login_fail_number`, `level`) VALUES";
        sql += "(NULL, '" + username + "', '" + password + "', '" + security_question_id + "', '" + security_question_answer + "', '" + email + "', '" + username + "', '0', '0', '0', '0', '0', '0')";
        command(sql);
    }

    public static boolean usernameExists(String username) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
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
        String sql = "SELECT id FROM users WHERE username = '" + username + "'";
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
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
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

    public static String[] getUsers() {
        String sql = "SELECT * FROM users";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String[] users = new String[rs.getFetchSize()];
            int i = 0;
            while (rs.next()) {
                users[i] = rs.getString("username");
                i++;
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
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

    // create card
    public static void createCard(String name, int cost, int levelup_cost, int duration, int defence, int damage, String explanation, String type, int level, String group, String image) {
        String sql = "INSERT INTO `cards` (`id`, `name`, `cost`, `levelup_cost`, `duration`, `defence`, `damage`, `explanation`, `type`, `level`, `group`, `image`) VALUES";
        sql += "(NULL, '" + name + "', '" + cost + "', '" + levelup_cost + "', '" + duration + "', '" + defence + "', '" + damage + "', '" + explanation + "', '" + type + "', '" + level + "', '" + group + "', '" + image + "')";
        command(sql);
    }
    // ===========================================================
    // CLANS
    // ===========================================================
    public static void createClan(String name, String tag, int owner_id) {
        String sql = "INSERT INTO `clans` (`id`, `name`, `tag`, `owner_id`, `xp`, `members`, `description`) VALUES";
        sql += "(NULL, '" + name + "', '" + tag + "', '" + owner_id + "', '0', '1', '')";
        command(sql);
    }

    public static String[] getGamesHistorySortedByWin(int userId, boolean isWinner) {
        String sql = "SELECT * FROM games WHERE user_id = ? AND is_winner = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, isWinner ? 1 : 0);
            ResultSet rs = stmt.executeQuery();
            String[] games = new String[rs.getFetchSize()];
            int i = 0;
            while (rs.next()) {
                games[i] = rs.getString("opponent_id");
                i++;
            }
            return games;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String[] getGamesHistorySortedByOpponent(int userId) {
        String sql = "SELECT * FROM games WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            String[] games = new String[rs.getFetchSize()];
            int i = 0;
            while (rs.next()) {
                games[i] = rs.getString("opponent_id");
                i++;
            }
            return games;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String[] getGamesHistorySortedByOpponentLevel(int userId) {
        String sql = "SELECT * FROM games WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            String[] games = new String[rs.getFetchSize()];
            int i = 0;
            while (rs.next()) {
                games[i] = rs.getString("opponent_level");
                i++;
            }
            return games;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
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
