package com.example.testrest.repository;


import com.example.testrest.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;


public class BookRepository extends JdbcTemplate {

    private static final String dbURL = "jdbc:h2:mem:bookDB";
    private static final String dbUserName = "root";
    private static final String dbPassword = "admin";

    private BookRepository() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "create table books (\n" +
                "                   id int not null primary key auto_increment,\n" +
                "                   title varchar(100) not null,\n" +
                "                   author varchar(100) not null,\n" +
                "                   publicationYear int not null);";
        statement.execute(sql);
        statement.close();
        connection.close();
    }

    public static BookRepository getRepository() {
        try {
            return new BookRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
    }


    public void createBook(Book newBook) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into BOOKS (TITLE, AUTHOR, publicationYear)\n" +
                "values(?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newBook.getTitle());
        preparedStatement.setString(2, newBook.getAuthor());
        preparedStatement.setInt(3, newBook.getPublicationYear());
        preparedStatement.execute();

        statement.close();
        connection.close();
    }

    public Book getBook(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from books where id=" + id;
        ResultSet res = statement.executeQuery(sql);

        res.next();
        String title = res.getString("title");
        String author = res.getString("author");
        int publicationYear = res.getInt("publicationYear");
        Book book = new Book(id, title, author, publicationYear);

        statement.close();
        connection.close();

        return book;
    }

    public void update(Book book) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "update BOOKS\n" +
                "set title = ?, AUTHOR = ?, PUBLICATIONYEAR = ?\n" +
                "where id = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setInt(3, book.getPublicationYear());
        preparedStatement.setInt(4, book.getId());
        preparedStatement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void delete(int id) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "delete from books where id =?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        preparedStatement.execute();
        statement.close();
        connection.close();
    }

}
