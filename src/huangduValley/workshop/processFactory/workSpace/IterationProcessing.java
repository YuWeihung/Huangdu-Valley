package huangduValley.workshop.processFactory.workSpace;

import huangduValley.workshop.concreteMachine.CleanMachine;
import huangduValley.workshop.concreteMachine.FineProMachine;
import huangduValley.workshop.concreteMachine.RoughProMachine;
import huangduValley.workshop.Machine;
import huangduValley.farm.storage.Items;

import java.util.Vector;

/**
 * Iteration Processing  (Template Method Design Pattern)
 * @author Leepaangsang
 * @version 2019/11/1
 */
public abstract class IterationProcessing implements WorkSpace {
    Vector<CleanMachine> cleanMachines;

    Vector<RoughProMachine> roughProMachines;

    Vector<FineProMachine> fineProMachines;

    protected Vector<Items> ingredients;

    private int iterationTimes = 1;

    /**
     * Start Processing.
     * @throws Exception
     */
    @Override
    public void doProcess() throws Exception{
        for (int i = 0; i < iterationTimes; i++) {
            preProcess();
            roughProcess();
        }
        for (int i = 0; i < iterationTimes; i++) {
            fineProcess();
        }
    }

    /**
     *
     * @param items
     * Materials to be processed
     * @param machineVector
     * Vector of machine to be used to doProcess()
     */
    @SafeVarargs
    @Override
    public final void doInit(Vector<Items> items, Vector<Machine>... machineVector)throws Exception{
        this.ingredients = items;

        cleanMachines = new Vector<>();
        roughProMachines = new Vector<>();
        fineProMachines = new Vector<>();
        int length = machineVector.length;
        if(length > 3){
            throw new Exception(String.format("MachineVectors contains: %d machineVector", length));
        }

        for (Vector<Machine> machines : machineVector) {
            String machineType = machines.get(0).getClass().getSimpleName();
            switch (machineType) {
                case "CleanMachine":
                    addCleanMachine(machines);
                    break;
                case "RoughProMachine":
                    addRoughProMachine(machines);
                    break;
                case "FineProMachine":
                    addFindProMachine(machines);
                    break;
            }
        }
    }

    /**
     * End processing
     */
    @Override
    public void doExit(){
    }

    /**
     * Get process items at the current.
     * @return
     * Vector of items.
     */
    @Override
    public Vector<Items> getIngredients() {
        return ingredients;
    }

    protected abstract void preProcess() throws Exception;
    protected abstract void roughProcess() throws Exception;
    protected abstract void fineProcess() throws Exception;

    protected abstract Vector<Items> process() throws Exception;
    protected abstract void exit();

    private void addCleanMachine(Vector<Machine> machines){
        for(Machine machine:machines){
            cleanMachines.add((CleanMachine) machine);
        }
    }
    private void addRoughProMachine(Vector<Machine> machines){
        for(Machine machine:machines){
            roughProMachines.add((RoughProMachine) machine);
        }

    }
    private void addFindProMachine(Vector<Machine> machines){
        for(Machine machine:machines){
            fineProMachines.add((FineProMachine) machine);
        }
    }

    public void setIterationTimes(int iterationTimes) {
        this.iterationTimes = iterationTimes;
    }
}
