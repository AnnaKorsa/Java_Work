public class Toys {

    private String id;
    private String name;
    private int count;
    private int odds;

    /**
     *
     * @param id Идентификационный номер игрушки
     * @param name Название игрушки
     * @param count Количество игрушек
     * @param odds Вероятность выпадения в лотерее
     */
    public Toys(String id, String name, int count, int odds) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.odds = odds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getOdds() {
        return odds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setOdds(int odds) throws InputException{
        if (odds < 0) {
            throw new InputException("Ошибка! Вероятность выпадения должна быть больше 0: ");
        }
        if (odds > 100) {
            throw new InputException("Вероятность выпадения должна быть меньше 100: ");
        }
        this.odds = odds;
    }


}
