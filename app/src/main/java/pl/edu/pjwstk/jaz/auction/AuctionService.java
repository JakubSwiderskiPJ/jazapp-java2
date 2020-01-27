package pl.edu.pjwstk.jaz.auction;

import pl.edu.pjwstk.jaz.Models.Auction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AuctionService
 *
 * Service provides CRUD for Auction
 */
public class AuctionService {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public AuctionService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertAuction(Auction auction) throws SQLException {
        String sql = "INSERT INTO auctions (title, description, price, category_id, creator_id) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, auction.getTitle());
        statement.setString(2, auction.getDescription());
        statement.setFloat(3, auction.getPrice());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Auction> listAllAuctions() throws SQLException {
        List<Auction> listAuction = new ArrayList<>();

        String sql = "SELECT * FROM auctions";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");

            Auction auction = new Auction(id, title, description, price);
            listAuction.add(auction);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listAuction;
    }

    public boolean deleteAuction(Auction auction) throws SQLException {
        String sql = "DELETE FROM auction where id= ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, auction.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateAuction(Auction auction) throws SQLException {
        String sql = "UPDATE auction SET title = ?, description = ?, price = ?, author = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, auction.getTitle());
        statement.setString(2, auction.getDescription());
        statement.setFloat(3, auction.getPrice());
        statement.setInt(4, auction.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Auction getAuction(int id) throws SQLException {
        Auction auction = null;
        String sql = "SELECT * FROM auction WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");
            String author = resultSet.getString("author");

            auction = new Auction(id, title, description, price);
        }

        resultSet.close();
        statement.close();

        return auction;
    }
}