package rmidatabase;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;
import java.util.*;

public interface RetailItem_RInterface extends Remote {
    String getTime() throws RemoteException;
    int countString(String message) throws RemoteException;
    ZonedDateTime getZonedDateTime(String zonedTime) throws RemoteException;
    List<RetailItem> printItembyCategory(String categoryName) throws RemoteException;
}
