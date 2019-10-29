import javafx.scene.transform.Rotate;

// part of "Facade" design pattern
// a singleton class representing a role that
// can do a lot as your request
// scene "The Farm", by Song Guanqun
public class Manager {

    // Singleton instance
    private static Manager instance;
    // the manager's bag
    private Bag managerBag;
    // root bag instance
    private RootBag rootBag;
    // farm instance
    private Farm farm;
    // guard instance
    private Guard guard;

    // private constructor
    private Manager() {
        managerBag = new Bag();
        rootBag = RootBag.getInstance();
        farm = Farm.getInstance();
        guard = Guard.getInstance();
    }

    public static Manager getInstance() {
        if(instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    // plant, water, fertilize, harvest the plants,
    // and wake up the guard for you
    public void doAll() throws Exception {
        farm.plantAll();

        if(fetch("Water")) {
            farm.waterAll();
            putback("Water");
        }

        if(fetch("Fertilizer")) {
            farm.fertilizeAll();
            putback("Fertilizer");
        }

        farm.harvestAll();

        guard.wakeup();

        farm.printList();
        farm.upgradeLand(1);
        farm.printList();
        farm.addLand();
    }

    // take something, like Water or Fertilizer
    // from rootBag of the Storage into the manager's bag
    // Items in child bags will not be fetched
    private boolean fetch(String name) throws Exception {
        Items items = rootBag.getItems(name);
        // none left
        if(items == null) {
            System.out.println("No " + name + " left in the root bag.");
            return false;
        }
        // take away all
        else {
            rootBag.deleteItems(name);
            managerBag.add(items);
            return true;
        }
    }

    private void putback(String name) throws Exception {
        Items items = managerBag.getItems(name);
        managerBag.deleteItems(name);
        if(items.getCount() > 1) {
            items.setCount(items.getCount() - 1);
            RootBag rootBag = RootBag.getInstance();
            rootBag.add(items);
        }
    }

}