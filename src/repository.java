import java.sql.*;

public class Repository {

    Connection connection;

    public void Repository(Connection connection) {
        this.connection = connection;
    }

    public void login(String username, String password) {

    }

    public void getBooksFromUser(int userId) {

    }

    public void getChaptersFromBook(int bookId) {

    }

    public void getCommentsFromChapter(int chapterId) {

    }

    public void setComment(int chapterId, String comment) {

    }

    public void setChapter(int bookId, String chapter) {

    }

}
