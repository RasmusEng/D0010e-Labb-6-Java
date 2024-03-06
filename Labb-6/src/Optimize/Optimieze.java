
package Optimize;

/**
 *
 * @author Rasmus, Albin, Walter, Axel
 * Optimize of the Sim
 *
 */

public class Optimieze {
    public int Optimize1(int kassor, int maxCustomers, double lambda, double pmin, double pmax, double kmin,
                         double kmax, int seed) {
        int missedCustomers = 5;
        return missedCustomers;
    }

    public int Optimize2(int maxCustomers, double lambda, double pmin, double pmax, double kmin, double kmax,
                         int seed) {
        int x = 0;
        int low = Optimize1(1, maxCustomers, lambda, pmin, pmax, kmin, kmax, seed);
        int high = Optimize1(maxCustomers, maxCustomers, lambda, pmin, pmax, kmin, kmax, seed);
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (Optimize1(mid, maxCustomers, lambda, pmin, pmax, kmin, kmax, seed) <= x) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }

        }
        ans = ans + 1;
        return ans;
    }

    public void Optimize3() {

        int counter = 0;
        while (counter < 100){

            counter ++;
            continue;
        }





    }

}
