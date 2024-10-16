import database.ConnectDB;
import database.DB_Initializer;
import tests.*;
import menu.*;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {

        try{Class.forName("org.sqlite.JDBC");}catch(ClassNotFoundException e){System.out.print("");}

        //DatabaseTester.test();

        MainMenu.startMenu();
    }
}