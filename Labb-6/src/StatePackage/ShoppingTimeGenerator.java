package StatePackage;

/**
 * @author Rasmus Engström, Walter Wimmercranz, Axel Grönberg, Albin Täljsten
 */
public class ShoppingTimeGenerator {
    private double kMax;
    private double kMin;
    private double pMax;
    private double pMin;


    public void createCheckoutTime(double kMax, double kMin) {
        this.kMax = kMax;
        this.kMin = kMin;
    }

    public void createPickingTime(double pMax, double pMin){
        this.pMax = pMax;
        this.kMin = pMin;
    }

    public double getPickupTime() {
        return (pMax + pMin) / 2;
    }

    public double getCheckoutTime(){
        return (kMax + kMin) / 2;
    }

    public double getkMin() {
        return kMin;
    }

    public double getkMax() {
        return kMax;
    }

    public double getpMax() {
        return pMax;
    }

    public double getpMin() {
        return pMin;
    }
}
