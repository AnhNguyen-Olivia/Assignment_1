package rmidatabase;

import java.rmi.registry.*;
import java.time.ZonedDateTime;
import java.util.*;

public class Client {
    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            RetailItem_RInterface retailItem_RInterface = (RetailItem_RInterface) registry.lookup("Retail Interface");

            System.out.println("Server uptime: " + retailItem_RInterface.getTime());

            String message = "Hello from RMI client";
            int messageLength = retailItem_RInterface.countString(message);
            System.out.println("Message length returned by server: " + messageLength);

            ZonedDateTime zonedDateTime = retailItem_RInterface.getZonedDateTime("Asia/Ho_Chi_Minh");
            System.out.println("Current time in Asia/Ho_Chi_Minh: " + zonedDateTime);

            List<RetailItem> list = retailItem_RInterface.printItembyCategory("BEER");

            for(RetailItem item : list){
                System.out.println("Item ID: " + item.getItemId());
                System.out.println("Item Description: " + item.getItemDescription());
                System.out.println("Category: " + item.getCategory());
                System.out.println("Provider: " + item.getProviderName());
            }
        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
