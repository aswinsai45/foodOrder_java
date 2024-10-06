import java.sql.*;


public class SQLtry{
    public static void main (String[] args){

        String url = "jdbc:mysql://localhost:3306/userdemo";
        String username = "root";
        String password = "mySQL1234$s-10763";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url,username,password);
            Statement stat = con.createStatement();
            stat.executeUpdate("insert into demotableuser values('<name>',<phone>);");
            ResultSet resultSet = stat.executeQuery("select * from demotableuser");

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+
                                    resultSet.getInt(2));
            }

            con.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}