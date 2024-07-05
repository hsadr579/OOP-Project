import java.sql.*;

public class DB {
    private static Connection connection;

    private DB() {}

    static {
        try {
            String url = "jdbc:sqlite:citywars.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // ===========================================================
    //                          USERS
    // ===========================================================

    public static void createUser(String username, String password, int security_question_id, String security_question_answer, String email) {
        String sql = "INSERT INTO `users` (`id`, `username`, `password`, `security_question_id`, `security_question_answer`, `email`, `nickname`, `xp`, `coins`, `clan_id`) VALUES";
        sql += "(NULL, '" + username + "', '" + password + "' , '" + security_question_id + "', '" + security_question_answer + "', '" + email + "', '0', '0', '0', NULL)";
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

    // ===========================================================
    //                          AUTH
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
    //                          CLANS
    // ===========================================================
    public static void createClan(String name, String tag, int owner_id) {
        String sql = "INSERT INTO `clans` (`id`, `name`, `tag`, `owner_id`, `xp`, `members`, `description`) VALUES";
        sql += "(NULL, '" + name + "', '" + tag + "', '" + owner_id + "', '0', '1', '')";
        command(sql);
    }


    // ===========================================================
    //                       DB METHODS
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