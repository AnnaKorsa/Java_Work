import java.util.HashMap;
import java.util.Map;

public interface IStoreFunctions {

    public Map<String, Toys> getStore();
    public void setStore(HashMap<String, Toys> store);
    public void addToy(String id, Toys item);
    public void copyToy(String id, Toys item);
    public void removeToy(String id);
    public Toys getToy(String id);
    public void printToys();
}
