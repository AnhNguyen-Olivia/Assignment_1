import java.sql.*;
import java.rmi.RemoteException;
import java.util.*;

public class RetailItem_Imp implements RetailItem_RInterface {
    public List<RetailItem> getItems() throws RemoteException{
        List<RetailItem> list = new ArrayList<>();
        String DB_URL = "jdbc:postgresql://localhost:5432/retail_db";
        String USER = "postgres";
        String PASS = "1234";

        try {
            Class.forName("org.postgresql.Driver");

            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM retail_items")){
                    while(resultSet.next()){
                        String itemId = resultSet.getString("item_id");
                        String itemDescription = resultSet.getString("item_description");
                        String category = resultSet.getString("category");
                        String providerName = resultSet.getString("provider_name");

                        RetailItem item = new RetailItem();
                        item.setItemId(itemId);
                        item.setItemDescription(itemDescription);
                        item.setCategory(category);
                        item.setProviderName(providerName);
                        list.add(item);
                    }
                    
            }
        } catch (Exception e) {
            throw new RemoteException("Failed to fetch retail items", e);
            }
        return list;
    }
}
