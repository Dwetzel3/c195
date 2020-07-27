package Model;

import java.sql.Timestamp;

public class User {

    private static int userId;
    private static String username;
    private static String password;
    private int active;
    private String createBy;
    private Timestamp createDate;
    private Timestamp lastUpdate;

    private String lastUpdatedBy;

    public User(
            int userId,
            String username,
            String password,
            int active,
            String createBy,
            Timestamp createDate,
            Timestamp lastUpdate,
            String lastUpdatedBy) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.active = active;
        this.createBy = createBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public static int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

}