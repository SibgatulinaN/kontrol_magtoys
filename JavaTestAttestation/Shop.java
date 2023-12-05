import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.PriorityQueue;

//магазин игрушек
public class Shop {
    // 1. Коллекция разыгрываемых игрушек (каждая с уникальным id)
    private List<Toy> toys;
    public List<Toy> getCollectionToys() {
        return toys;
    }

    // 2. Это очередь игрушек, выпавших в розыгрыше из коллекции List<Toy> toys
    // может содержать игрушки с одним и тем же id
    private PriorityQueue<Toy> queueToys;

    public PriorityQueue<Toy> getQueueToys() {
        return queueToys;
    }

    public Shop() {
        toys = new ArrayList<>();
        queueToys = new PriorityQueue<>();
    }

    // Добавляем новую игрушку в коллекцию разыгрываемых
    public void addNewToy(int id, String name, int weight) throws RuntimeException {
        toys.add(new Toy(id, name, weight));
    }

    // Разыгрываем игрушку
    public Toy GetToy() throws RuntimeException {
        if (toys.size() == 0) {
            throw new RuntimeException("Коллекция игрушек для розыгрыша пуста. Добавьте элементы");
        }
        // Реализуем алгоритм розыгрыша игрушки с учетом веса(частоты выпадения)
        // 1. Суммируем вес всех игрушек
        int sumWeigths = 0;
        for (Toy t : toys) {
            sumWeigths += t.getWeight();
        }
        // 2. Генерируем случайное число от 0 до sumWeigths-1:
        Random rnd = new Random();
        int randomValue = rnd.nextInt(sumWeigths);

        // 3. Повторно обходим список и выбираем игрушку,
        // для которой текущая сумма превысила randomValue:
        sumWeigths = 0;
        for (Toy t : toys) {
            sumWeigths += t.getWeight();
            if (sumWeigths > randomValue) {
                // Добавляем в очередь выдачи:
                queueToys.add(t);

                return t;
            }
        }
        // Если добрались сюда, это ошибка
        throw new RuntimeException("Shop::GetToy(), ошибка алгоритма");
    }
}
