import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LotteryToys implements LoteryRealization{
    Random random = new Random();

    /**
     *
     * @param toysStore
     * @param prizeStore
     */
    @Override
    public  void start_lottery(ToysStore toysStore, ToysStore prizeStore) {

        boolean result_lottery = true;
        while (result_lottery) {
            int random = (int) (1 + (Math.random() * 100));
            int i = 1;
            int toy_number = (int) (Math.random() * toysStore.getStore().size() + 1);
            for (Map.Entry<String, Toys> item : toysStore.getStore().entrySet()) {
                if (i == toy_number) {
                    if (random <= (item.getValue().getOdds())) {
                        System.out.printf("Выпала игрушка: %s. Наличие в магазине: %d \n", item.getValue().getName(), item.getValue().getCount());
                        if (prizeStore.getStore().containsKey(item.getKey())) {
                            System.out.println("Уже есть такой приз, увеличиваем количество +1");
                            prizeStore.getStore().get(item.getKey()).setCount(prizeStore.getStore().get(item.getKey()).getCount() + 1);
                        } else {
                            System.out.println("Приза нет в коробке, добавляем +1");
                            prizeStore.copyToy(item.getKey(), item.getValue());
                            prizeStore.getToy(item.getValue().getId()).setCount(1);
                        }
                        toysStore.getToy(item.getValue().getId()).setCount(item.getValue().getCount() - 1);
                        System.out.printf("Остаток игрушек '%s' в магазине: %d \n", item.getValue().getName(), item.getValue().getCount());
                        if ((item.getValue().getCount()) == 0) {
                            System.out.printf("ID %s закончился, в магазине не осталось игрушек '%s' \n", item.getValue().getId(), item.getValue().getName());
                            toysStore.removeToy(item.getKey());
                        }
                        result_lottery = false;
                        break;
                    }
                }
                i += 1;
            }
        }
    }

    @Override
    public void getPrize(ToysStore prizeStore, String id) {
        if (prizeStore.getStore().containsKey(id)) {

            System.out.println("Ваш приз - игрушка " + prizeStore.getStore().get(id).getName());
            String info = "Выдан приз - " + "ID: " + prizeStore.getStore().get(id).getId() +
                    ", Наименование: " + prizeStore.getStore().get(id).getName() +
                    ", Количество: 1" + "\n";
            try(FileWriter writer = new FileWriter("Lottery_results.txt", true))
            {
                writer.write(info);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            prizeStore.getStore().get(id).setCount(prizeStore.getStore().get(id).getCount() - 1);

            if (prizeStore.getStore().get(id).getCount() == 0) {
                System.out.printf("Призовые игрушки %s закончились", prizeStore.getStore().get(id).getName());
                prizeStore.removeToy(id);
                System.out.println();
            }
        }
    }
    public void buttonLottery(ToysStore toysStore, ToysStore prizeStore) throws InputException {
        System.out.println("\u001B[35m"+"В розыгрыше учавствуют следующие игрушки: " + "\u001B[0m" );
        toysStore.printToys();
        System.out.println();

        toysStore.correctionOdds(toysStore, "107",15);
        System.out.println();

        for (int i = 1; i < random.nextInt(2, 6);i++){
            System.out.println("\u001B[31m" + "Старт лотереи " + i + "\u001B[0m");
            start_lottery(toysStore, prizeStore);
            System.out.println();
        }

        System.out.println("\u001B[32m"+"Ура! Розыгрыш завершен" + "\u001B[0m" );
        System.out.println("\u001B[35m"+"Наличие ирушек в магазине, после проведения розыгрыша:" + "\u001B[0m");
        toysStore.printToys();
        System.out.println("\u001B[32m"+" Призовые игрушки: " + "\u001B[0m" );
        prizeStore.printToys();

        while (!prizeStore.getStore().isEmpty()) {
            Scanner in = new Scanner(System.in);
            System.out.println("Чтобы забрать свой приз, введите ID игрушки: ");
            String prize = in.nextLine();
            getPrize(prizeStore, prize);

            System.out.println("\u001B[32m"+" Призовые игрушки: " + "\u001B[0m" );
            prizeStore.printToys();
            if (prizeStore.getStore().isEmpty()){
                System.out.println("Все призы розданы. Ознакомиться с итогами розыгрыша можно в :Lottery_results.txt \n Cпасибо!");
            }
        }
    }
}


