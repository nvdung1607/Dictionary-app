package source;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    // Connection to mySQL.
    public Connection getConnection() {
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dic";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Couldn't connect jdbc");
            return null;
        }
    }

    // function returns array of words in database.
    public ArrayList <Word> getWord(){
        ArrayList <Word> listWords = new ArrayList<Word>();
        String sql = "SELECT * FROM dic";
        Connection connection;
        DataBase varData = new DataBase();
        connection = varData.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()){                
                    String explain = resultSet.getString("detail");
                    String explainReal = explain.replaceAll("<br />", "\n");
                    explainReal = explainReal.replaceAll("<C><F><I><N><Q>@", "");
                    explainReal = explainReal.replaceAll("</Q></N></I></F></C>", "");
                    Word word = new Word(resultSet.getString("word"), explainReal
                            , resultSet.getString("word").toLowerCase());
                    listWords.add(word);
                    
                }
                return listWords;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }    
        return null;
    }

    // insert word to database.
    public static void InsertWord(int id,String new_target, String new_explain) {
        String sql = "Insert into dic (id, word, detail) values('" + id + "','" 
                + new_target + "','" + new_explain 
                + "')";
        Connection connection;
        DataBase varData = new DataBase();
        connection = varData.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);
            //System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // remove word from database.
    public static void RemoveWord(int id) {
        String sql = "Delete from dic where(id = '" + id + "');";
        String sql1 = "Update dic set id = id - 1 where (id > '"
                + id + "')";
        Connection connection;
        DataBase varData = new DataBase();
        connection = varData.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);
            int rs1 = statement.executeUpdate(sql1);
            //System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // update word database.
    public static void UpdateWord(int id, String new_target, String new_explain) {
        String sql = "Update dic set word = '" + new_target
                + "', detail = '" + new_explain
                + "' where (id = '" + id + "')";
        Connection connection;
        DataBase varData = new DataBase();
        connection = varData.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);
            //System.out.println(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       
    }
}
