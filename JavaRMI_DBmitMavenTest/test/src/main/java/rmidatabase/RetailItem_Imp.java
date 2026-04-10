package rmidatabase;
import java.sql.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.time.*;
import java.rmi.RemoteException;

public class RetailItem_Imp extends UnicastRemoteObject implements RetailItem_RInterface {
    private final Instant startTime = Instant.now();

    public RetailItem_Imp() throws RemoteException{
        super();
    }

    public String getTime() throws RemoteException{
        Duration duration = Duration.between(startTime, Instant.now());
        long totalSeconds = duration.getSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public int countString(String message) throws RemoteException{
        System.out.println("Server received: " + message);
        return message.length();
    }

    public ZonedDateTime getZonedDateTime(String zonedTime) throws RemoteException{
        ZoneId zoneId = ZoneId.of(zonedTime);
        return ZonedDateTime.now(zoneId);
    }

    public List<RetailItem> printItembyCategory(String categoryName) throws RemoteException{
        List<RetailItem> list = new ArrayList<>();
        String DB_URL = "jdbc:postgresql://localhost:5432/retail_db";
        String USER = "postgres";
        String PASS = "1234";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT item_id, item_description, category, provider_name FROM retail_items WHERE category = ?")){
                preparedStatement.setString(1, categoryName);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    RetailItem item = new RetailItem();
                    item.setItemId(resultSet.getString("item_id"));
                    item.setItemDescription(resultSet.getString("item_description"));
                    item.setCategory(resultSet.getString("category"));
                    item.setProviderName(resultSet.getString("provider_name"));
                    list.add(item);
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        return list;
    }
}
