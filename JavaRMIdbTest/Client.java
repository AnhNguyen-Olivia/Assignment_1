import java.rmi.registry.*;
import java.util.*;

public class Client {
    private static final int REGISTRY_PORT = 2002;

    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry("localhost", REGISTRY_PORT);
            RetailItem_RInterface retailItem_RInterface = (RetailItem_RInterface) registry.lookup("Retail Interface");

            List<RetailItem> list = retailItem_RInterface.getItems();

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
