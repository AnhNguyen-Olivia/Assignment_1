import java.io.Serializable;

public class RetailItem implements Serializable{
    private String itemId;
    private String itemDescription;
    private String category;
    private String providerName;
    
    public String getItemId(){
        return itemId;
    }

    public String getItemDescription(){
        return itemDescription;
    }

    public String getCategory(){
        return category;
    }

    public String getProviderName(){
        return providerName;
    }

    public void setItemId(String itemId){
        this.itemId = itemId;
    }
    
    public void setItemDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setProviderName(String providerName){
        this.providerName = providerName;
    }
}
