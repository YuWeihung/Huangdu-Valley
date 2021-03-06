package huangduValley.workshop.processFactory.factory;

import huangduValley.Stdout;
import huangduValley.workshop.wareHouse.Store;
import huangduValley.workshop.wareHouse.WareHouse;
import huangduValley.workshop.processFactory.workSpace.WorkSpace;
import huangduValley.farm.storage.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//继承状态
public class NormalFactory implements FactoryState{
    //单例模式
    private NormalFactory(){
        addObservers(wareHouse);
        addObservers(store);
    }

    private static class NormalFactoryHolder{
        private static final NormalFactory INSTANCE = new NormalFactory();
    }

    public static NormalFactory getInstance(){
        return NormalFactoryHolder.INSTANCE;
    }

    private WareHouse wareHouse = WareHouse.getInstance();

    private Store store = Store.getInstance();


    private Vector<WorkSpace> processVector;

    private Vector<Vector<Items>> productsVector = new Vector<>();

    //The list of observers
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * Observer Pattern
     * Update the observers
     * @throws Exception
     */
    @Override
    public void runFactory() throws Exception {
        Stdout.print(this, "The Processing factory now is working!");
        for(WorkSpace workSpace:processVector){
            workSpace.doProcess();
            productsVector.add(workSpace.getIngredients());
            //Update the observer list
            for (Observer observer: observers) {
                observer.update(getProductsVector());
            }
            Stdout.print(this, "Update observers");
            workSpace.doExit();
        }
    }

    public Vector<Vector<Items>> getProductsVector() {
        return productsVector;
    }

    public WareHouse getWareHouse(){
        return wareHouse;
    }

    public void setProcessVector(Vector<WorkSpace> processVector){
        this.processVector = processVector;
    }

    public void addObservers(Observer observer) {
        observers.add(observer);
    }

    public void delObservers(Observer observer) {
        observers.remove(observer);
    }


}