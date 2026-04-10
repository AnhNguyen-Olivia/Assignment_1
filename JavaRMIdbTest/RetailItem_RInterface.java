import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface RetailItem_RInterface extends Remote {
    public List<RetailItem> getItems() throws RemoteException;
}
