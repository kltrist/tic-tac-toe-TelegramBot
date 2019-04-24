package db;

import config.AppConfiguration;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseHelper  implements Serializable{

    private transient PreparedStatement update;
    private transient PreparedStatement insert;
    private transient PreparedStatement select;
    private transient Connection connect;

    public DataBaseHelper(AppConfiguration cfg) throws SQLException, ClassNotFoundException {
        connect = ConnectorDB.getConnection(cfg);

        select = connect.prepareStatement("SELECT * FROM player_statistic t WHERE t.name = ?");
        update = connect.prepareStatement("UPDATE player_statistic t set winCount = ? where t.name = ?");
        insert = connect.prepareStatement("INSERT INTO player_statistic (name,winCount) VALUES(?,?)");
    }

    public void updatePlayer(String name, int winCount) {
        try {
            update.setInt(1, winCount);
            update.setString(2, name);
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertPlayer(String name, int winCount) {
        try {
            if (isPlayerExists(name)) {
                updatePlayer(name, winCount);
            } else {

                insert.setString(1, name);
                insert.setInt(2, winCount);
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int selectPlayerWinCount(String name) throws SQLException {
        ResultSet rs = selectPlayer(name);
        rs.next();
        return rs.getInt("winCount");
    }

    private ResultSet selectPlayer(String name) throws SQLException {
        select.setString(1, name);
        return select.executeQuery();

    }

    public boolean isPlayerExists(String name) throws SQLException {
        return selectPlayer(name).next();
    }


}
