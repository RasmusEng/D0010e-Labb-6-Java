package StatePackage;

/**
 * @author Rasmus Engström, Walter Wimmercranz, Axel Grönberg, Albin Täljsten
 */
public class ShoppingTimeGenerator {
    private double kMax;
    private double kMin;
    private double pMax;
    private double pMin;
    public double createCheckoutTime(){
        return (kMax + kMin)/2;
    }

    public double createPickupTime(){
        return (pMax + pMin)/2;
    }
}
