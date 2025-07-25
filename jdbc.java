import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class SampleJDBC {
    public static void main(String[] args) throws Exception {

    Class.forName("org.sqlite.JDBC");
    Connection con=DriverManager.getConnection("jdbc:sqlite://home//Sathvik//mydb1.db");
        insertDepartment(con, 101, "Finance");
        readDepartments(con);
        updateDepartment(con, 101, "Accounting");
        deleteDepartment(con, 101);
        con.close();

    }
    public static void insertDepartment(Connection con, int deptno, String deptname) throws SQLException {
        String query = "INSERT INTO dept (deptno, deptname) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, deptno);
            pstmt.setString(2, deptname);
            pstmt.executeUpdate();
            System.out.println("Inserted: " + deptno + " | " + deptname);
        }
    }


    public static void readDepartments(Connection con) throws SQLException {
        String query = "SELECT * FROM dept";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Deptno | Deptname");
            while (rs.next()) {
                int dno = rs.getInt("deptno");
                String dname = rs.getString("deptname");
                System.out.println(dno + "     | " + dname);
            }
        }
    }


    public static void updateDepartment(Connection con, int deptno, String newName) throws SQLException {
        String query = "UPDATE dept SET deptname = ? WHERE deptno = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, deptno);
            int updated = pstmt.executeUpdate();
            System.out.println("Updated " + updated + " row(s) for deptno " + deptno);
        }
    }
    public static void deleteDepartment(Connection con, int deptno) throws SQLException {
        String query = "DELETE FROM dept WHERE deptno = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, deptno);
            int deleted = pstmt.executeUpdate();
            System.out.println("Deleted " + deleted + " row(s) for deptno " + deptno);
        }
    }

}
