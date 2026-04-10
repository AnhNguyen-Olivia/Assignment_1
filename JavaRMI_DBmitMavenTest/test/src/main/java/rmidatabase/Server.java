package rmidatabase;

import java.rmi.registry.*;
import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] main){
        try{
            System.setProperty("java.rmi.server.hostname", "localhost");

            RetailItem_Imp retailItem_Imp = new RetailItem_Imp();

            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (Exception ignored) {
                registry = LocateRegistry.getRegistry("localhost", 1099);
            }

            registry.rebind("Retail Interface", retailItem_Imp);

            System.err.println("Server is ready! :D");

            new CountDownLatch(1).await();
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }    
}
