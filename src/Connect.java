import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Connect {
    public static Student getStudentById(int id) {
        String dataBaseName="mybase";
        String userName="root";
        String password="root";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+dataBaseName, userName , password);
            stmt = conn.prepareStatement("select id, name, surname, age " +
                    "from student WHERE id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Student stud = null;
            if (rs.next()) {
                stud = new Student();
                stud.setId(rs.getInt("id"));
                stud.setName(rs.getString("name"));
                stud.setSurname(rs.getString("surname"));
                stud.setAge(rs.getInt("age"));
            }
            return stud;
        } catch (SQLException e) { System.out.println("SQLException");
            System.out.println("SQLException");
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } finally {
            if (rs != null) { //Sprzątamy bałagan
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("SQLException");
                    System.out.println(e.toString());
                    e.printStackTrace();}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }
    public static List<Student> getStudentsByNameAndSurname(String name, String surname) {
        String dataBaseName="mybase";
        String userName="root";
        String password="root";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+dataBaseName, userName , password);
            stmt = conn.prepareStatement("select id, name, surname, age " +
                    "from student WHERE name=? AND surname=?");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            rs = stmt.executeQuery();
            List<Student> students = new LinkedList<>();
            while (rs.next()) //
            {
                Student stud = new Student();
                stud.setId(rs.getInt("id"));
                stud.setName(rs.getString("name"));
                stud.setSurname(rs.getString("surname"));
                stud.setAge(rs.getInt("age"));
                students.add(stud);
            }
            return students;
        } catch (SQLException e) { System.out.println("SQLException");
            System.out.println("SQLException");
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } finally {
            if (rs != null) { //Sprzątamy bałagan
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("SQLException");
                    System.out.println(e.toString());
                    e.printStackTrace();}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }
    public static boolean insertStudentIntoDatabase(Student student) {
        String dataBaseName="mybase";
        String userName="root";
        String password="root";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+dataBaseName, userName , password);
            preparedStatement = conn.prepareStatement("insert into " +
                    "student values(?, ?, ?, ?)");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { System.out.println("SQLException");
            System.out.println("SQLException");
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } finally {
            if (rs != null) { //Sprzątamy bałagan
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("SQLException");
                    System.out.println(e.toString());
                    e.printStackTrace();}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return false;
    }


    public static boolean updateStunedtsNameById(int studentsId, String newStudentsName)
    {
        String dataBaseName="mybase";
        String userName="root";
        String password="root";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+dataBaseName, userName , password);
            //*UPDATE student SET name='Michal' WHERE id=24339
            preparedStatement = conn.prepareStatement("UPDATE student" +
                    " SET name=? WHERE id=?");
            preparedStatement.setString(1, newStudentsName);
            preparedStatement.setInt(2, studentsId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) { System.out.println("SQLException");
            System.out.println("SQLException");
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } finally {
            if (rs != null) { //Sprzątamy bałagan
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("SQLException");
                    System.out.println(e.toString());
                    e.printStackTrace();}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println("start");
        System.out.println(getStudentById(241148).toString());
        System.out.println(getStudentsByNameAndSurname("Pawel","Hetmanski").toString());
        System.out.println(insertStudentIntoDatabase(new Student(24339,"Jan", "Kowalski",12)));
        System.out.println(updateStunedtsNameById(24339,"Jan"));
        System.out.println("stop");
    }
}