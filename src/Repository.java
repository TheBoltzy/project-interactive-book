import java.sql.*;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Repository {

    public Connection connection;

    private String s1 = "test";
    private String s2 = "Project@1";

    public Repository() {
    }

    public void login(String username, String password) {

    }

    private Connection getConnection() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://68.205.83.101:3306/interactivebook", s1, s2);
            Class.forName("com.mysql.cj.jdbc.Driver");
            return connect;

        } catch (SQLException except) {
            JOptionPane.showMessageDialog(null, except.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException classnotfound) {
            System.out.println("Error! Driver not found");
        }
        return null;
    }

    public DefaultListModel<String> getBooksFromUser(int userId) {
        try (ResultSet rs = getConnection().createStatement().executeQuery("SELECT * FROM books")) {
            DefaultListModel<String> model = new DefaultListModel<String>();
            while (rs.next()) {
                System.out.println(rs.getString("title"));
                model.addElement(rs.getString("title"));
            }
            return model;
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int getBooksId(String title) {
        try (ResultSet rs = getConnection().createStatement()
                .executeQuery("SELECT * FROM books WHERE title = '" + title.trim() + "'")) {
            DefaultListModel<String> model = new DefaultListModel<String>();
            while (rs.next()) {
                System.out.println(rs.getString("book_id"));
                return rs.getInt("book_id");

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public DefaultListModel<String> getChaptersFromBook(int bookId) {
        try (ResultSet rs = getConnection().createStatement()
                .executeQuery("SELECT * FROM chapters WHERE book_id = " + bookId)) {
            DefaultListModel<String> model = new DefaultListModel<String>();
            while (rs.next()) {
                System.out.println(rs.getString("chapter_number"));
                model.addElement(rs.getString("chapter_number"));
            }
            return model;
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String geChapterBody(int bookId, int chapterNumber) {
        try (ResultSet rs = getConnection().createStatement().executeQuery(String.format(
                "SELECT * FROM chapters WHERE book_id = '%s' AND chapter_number = '%s'", bookId, chapterNumber))) {
            while (rs.next()) {
                System.out.println(rs.getString("chapter_body"));
                return rs.getString("chapter_body");

            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getCommentsFromChapter(int bookId, int chapterId) {
        try (ResultSet rs = getConnection().createStatement().executeQuery(
                "SELECT * FROM comments C INNER join chapters U ON C.chapter_id = U.chapter_id  WHERE U.book_id = "
                        + chapterId + " AND U.chapter_number = " + chapterId)) {
            while (rs.next()) {
                System.out.println(rs.getString("comment_body"));
                return rs.getString("comment_body");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addComment(int chapterId, String comment) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO comments (chapter, text) VALUES (?, ?)");
            ps.setInt(1, chapterId);
            ps.setString(2, comment);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveChapter(int bookId, int chapter, String body) {
        try {
            PreparedStatement ps = getConnection()
                    .prepareStatement("UPDATE chapters SET chapter_body = ? WHERE book_id = ? AND chapter_number = ?");
            ps.setString(1, body);
            ps.setInt(2, bookId);
            ps.setInt(3, chapter);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveComment(int bookId, int chapter, String body) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "UPDATE comments C INNER join chapters U ON C.chapter_id = U.chapter_id SET C.comment_body  = ? WHERE U.book_id = ? AND U.chapter_number = ?");
            ps.setString(1, body);
            ps.setInt(2, bookId);
            ps.setInt(3, chapter);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
