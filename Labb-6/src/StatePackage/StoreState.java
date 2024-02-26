package StatePackage;
import ViewPackage.*;

public class StoreState extends State {

    private int openReg;
    private int maxCustomers;
    private int customersInStore;
    private int checkedOutCustomers;
    private boolean isOpen;
    private double closeTime;
    public StoreState(View view){
        super(view);
    }
}
