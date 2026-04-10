import java.rmi.registry.*;
import java.rmi.server.*;

public class Server {
    private static final int REGISTRY_PORT = 2002;

    public static void main(String[] main){
        try{
            RetailItem_Imp retailItem_Imp = new RetailItem_Imp();
            RetailItem_RInterface retailItem_RInterface = (RetailItem_RInterface) UnicastRemoteObject.exportObject(retailItem_Imp, 0);

            Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);

            registry.rebind("Retail Interface", retailItem_RInterface);

            System.err.println("Server is ready! :D");
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }    
}
