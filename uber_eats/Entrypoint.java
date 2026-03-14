/*
 * Producer of orders for Restaurant (Consumer) to fulfill
 */

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Entrypoint {
      private static final Logger logger = Logger.getLogger(Entrypoint.class.getCanonicalName());
      public static void main(String[] args) {
            logger.log(Level.ALL, "🚀 Starting entrypoint");
            Restaurant restaurant = new Restaurant();
            DriverQueue driverQueue = new DriverQueue(restaurant);
            restaurant.start();
            driverQueue.start();
            Scanner scnr = new Scanner(System.in);
            int id = 1;
            Random rand = new Random(); 
            while (true) {
                  logger.log(Level.INFO ,"#️⃣ Please enter a number of orders: Enter any number low than 1 to quit");
                  int numOrders = scnr.nextInt();
                  
                  // Break
                  if (numOrders < 1) {
                        break;
                  }
                  
                  for (int i = 1; i <= numOrders; i++) {
                        OrderQueue oq = restaurant.getOrderQueue();
                        synchronized(oq) {
                              try {
                                    while (oq.isAtCapacity()) {
                                         oq.wait();       
                                    }
                                    int randomNum = rand.nextInt(5) + 1; // Random integer between 1, 5
                                    Order order = new Order(id, randomNum, randomNum);
                                    oq.addOrder(order);
                                    id++;
                                    oq.notifyAll(); // wakes other threads up
                              } catch (Exception e) {}
                        }
                  }
            }
            logger.log(Level.OFF ,"Exiting...");
            scnr.close();
      }
}