import java.util.HashMap;
import java.util.Map;

public class ToysStore implements IStoreFunctions{
    private Map<String,Toys> store = new HashMap<>();

    public ToysStore() {
        this.store = store;
    }
    @Override
    public Map<String, Toys> getStore() {
        return store;
    }

    @Override
    public void setStore(HashMap<String, Toys> store) {
        this.store = store;
    }

    /**
     * Метод добавления  игрушки
     * @param id Идентификационный номер
     * @param item Элемент
     */
    @Override
    public void addToy(String id, Toys item){
        this.store.put(id, item );
    }

    @Override
    public void copyToy(String id, Toys item){
        Toys tempToy = new Toys(item.getId(), item.getName(), item.getCount(), item.getOdds());
        this.store.put(id, tempToy);
    }

    /**
     * Метод удаления игрушки
     * @param id Идентификационный номер
     */
    @Override
    public void removeToy(String id){
        this.store.remove(id);
    }

    @Override
    public Toys getToy(String id){
        return this.store.get(id);
    }

    /**
     * Метод, который вывод в консоль список всех игрушек
     */
    @Override
    public void printToys(){
        for (Map.Entry<String, Toys> item: store.entrySet()){
            System.out.printf("ID: %s, Игрушка: %s, Количество: %d, Вероятность выпадения: %d \n", item.getKey(), item.getValue().getName(), item.getValue().getCount(), item.getValue().getOdds());
        }
    }

    /**
     * Метод изменения вероятности выпадения игрушки
     * @param toysStore Магазин игрушек
     * @param id Идентификационный номер
     * @param newOdds Новое значение вероятности выпадения игрушки при розыгрыше
     * @throws InputException ошибка ввода
     */
    public void correctionOdds(ToysStore toysStore, String id, int newOdds) throws InputException {
        for (Map.Entry<String, Toys> item : toysStore.getStore().entrySet()) {
            if (id.equals(item.getValue().getId())){
                item.getValue().setOdds(newOdds);
                toysStore.getToy(item.getValue().getId()).setCount(item.getValue().getCount() - 1);
                System.out.println("\u001B[32m" + "Изменена вероятность выпадения следующей игрушки: " + "\u001B[0m");
                System.out.printf("ID: %s, Игрушка: %s, Количество: %d, Вероятность выпадения: %d \n", item.getKey(), item.getValue().getName(), item.getValue().getCount(), item.getValue().getOdds());
            }
        }
    }
}
