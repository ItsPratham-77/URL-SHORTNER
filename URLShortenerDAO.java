import java.sql.*;

public class URLShortenerDAO {
    public void saveURL(String shortCode, String longURL) {
        String sql = "INSERT INTO urls (short_code, long_url) VALUES (?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, shortCode);
            ps.setString(2, longURL);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getLongURL(String shortCode) {
        String sql = "SELECT long_url FROM urls WHERE short_code = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, shortCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("long_url");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
