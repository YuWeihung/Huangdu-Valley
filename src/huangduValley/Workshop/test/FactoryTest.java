package huangduValley.Workshop.test;

import huangduValley.Workshop.ConcreteMachine.CleanMachine;
import huangduValley.Workshop.ConcreteMachine.FineProMachine;
import huangduValley.Workshop.ConcreteMachine.RoughProMachine;
import huangduValley.Workshop.Machine;
import huangduValley.Workshop.WareHouse.WareHouse;
import huangduValley.Workshop.processFactory.factory.ClosedFactory;
import huangduValley.Workshop.processFactory.factory.Context;
import huangduValley.Workshop.processFactory.factory.FactoryState;
import huangduValley.Workshop.processFactory.factory.NormalFactory;
import huangduValley.Workshop.processFactory.workSpace.CompleteProcessing;
import huangduValley.Workshop.processFactory.workSpace.WorkSpace;
import huangduValley.farm.storage.Ingredients;
import huangduValley.farm.storage.Items;

import java.util.Vector;

public class FactoryTest {

    public static void main(String[] args) throws Exception {
	// write your code here
        Context context=new Context();
        FactoryState factory=ClosedFactory.getInstance();
        context.setState(factory);    //设置状态为工厂关闭
        context.runState();         //状态为关闭工厂下的运行函数
        factory= NormalFactory.getInstance();
        context.setState(factory);    //设置状态为工厂开启

        /**
         * Create machine
         */
        CleanMachine cm = new CleanMachine("1st cm");
        RoughProMachine rm = new RoughProMachine("1st rm");
        FineProMachine fm = new FineProMachine("1st fm");
        /**
         * Set wareHouse size
         */
        WareHouse wareHouse = WareHouse.getInstance();
        wareHouse.setMaxSize(10);
        /**
         * Create ingredients
         */
        Vector<Items> ingredientsVector = new Vector<>();
        ingredientsVector.add(new Ingredients("rice", 10));
        ingredientsVector.add(new Ingredients("potato", 20));
        /**
         * Create processing
         */
        Vector<WorkSpace> processVector = new Vector<>();
        CompleteProcessing processing1 = new CompleteProcessing();
        Vector<Machine> machineVector1 = new Vector<>();
        machineVector1.add(cm);
        Vector<Machine> machineVector2 = new Vector<>();
        machineVector2.add(rm);
        Vector<Machine> machineVector3 = new Vector<>();
        machineVector3.add(fm);
        processing1.doInit(ingredientsVector, machineVector1, machineVector2, machineVector3);
        processVector.add(processing1);
        ((NormalFactory) factory).setProcessVector(processVector);
        /**
         * Run
         */
        context.runState();         //状态为开启工厂下的运行函数
    }
}
