/*
 * Copyright 2015 Len Payne <len.payne@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd4414.buildit3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class CPD4414BuildIt3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //doStatement();
        displayRow("2");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception! " + e.getMessage());
        }

        String url = "jdbc:mysql://IPRO:3306/Winter2015";
        try {
            connection = DriverManager.getConnection(url,
                    "Winter2015", "P@ssw0rd");            
        } catch (SQLException e) {
            System.out.println("Failed to Connect! " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void doStatement() {
        try{
            Connection con = getConnection();
            String query = "SELECT * FROM product";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Above 3 lines same as line below:
//            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM product");
            
            System.out.printf("%s\t%s\t%s\n","Name","Description","Quantity"); 
            System.out.println("(------------------------------)");
            while(rs.next()){
                String name = rs.getString("Name");
                String des = rs.getString("Description");
                String quan = rs.getString("Quantity");
                
                System.out.printf("%s\t%s\t\t%s\n",name, des, quan );
            }
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Failure to SELECT: " + e.getMessage());
        }
    }
    
    public static void displayRow(String id) {
        try {
            Connection con = getConnection();
            String query = "SELECT * FROM  product WHERE ProductID = " + id;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.printf("%s\t%s\t%s\t%s\t\n", "ID" ,"Name","Description","Quantity"); 
            System.out.println("(-------------------------------------------)");
            
            while(rs.next()){
                String prod = rs.getString("ProductID");
                String name = rs.getString("Name");
                String des = rs.getString("Description");
                String quan = rs.getString("Quantity");
                 
                System.out.printf("%s\t%s\t%s\t\t%s\n",prod,name, des, quan );
            }
        } catch (SQLException e) {
            System.out.println("Failure to SELECT: " + e.getMessage());
        }
    }
}