import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputException {
        Toys toy1 = new Toys("100", "Конструктор", 50, 15);
        Toys toy2 = new Toys("101", "Кукла", 50, 50);
        Toys toy3 = new Toys("102", "Мяч", 50, 30);
        Toys toy4 = new Toys("103", "Машинка", 50, 60);
        Toys toy5 = new Toys("104", "Плюшевый мишка", 50, 50);
        Toys toy6 = new Toys("105", "Юла", 50, 30);
        Toys toy7 = new Toys("106", "Пистолет", 50, 70);
        Toys toy8 = new Toys("107", "Кубики", 50, 5);
        Toys toy9 = new Toys("108", "Неваляшка", 50, 10);

        ToysStore toysStore = new ToysStore();
        ToysStore prizeStore = new ToysStore();

        toysStore.addToy(toy1.getId(), toy1);
        toysStore.addToy(toy2.getId(), toy2);
        toysStore.addToy(toy3.getId(), toy3);
        toysStore.addToy(toy4.getId(), toy4);
        toysStore.addToy(toy5.getId(), toy5);
        toysStore.addToy(toy6.getId(), toy6);
        toysStore.addToy(toy7.getId(), toy7);
        toysStore.addToy(toy8.getId(), toy8);
        toysStore.addToy(toy9.getId(), toy9);

        LotteryToys lotteryToys = new LotteryToys();
        lotteryToys.buttonLottery(toysStore, prizeStore);



    }
}

