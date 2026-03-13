// Goal: Design low level Uber eats

/* Restaurant receives order
Orders come in and restaurant processes it
Restaurant can not start preparing food until orders come in
Waiters can not pick up food until there is an order.
Order arrives
      ↓
Restaurant cooks
      ↓
Order completed
      ↓
Driver picks up
*/

import java.util.Random;
import java.util.Scanner;

public class Entrypoint {

      public static void main(String[] args) {
            DriverQueue driverQueue = new DriverQueue();
            Restaurant restaurant = new Restaurant();
            Scanner scnr = new Scanner(System.in);
            int id = 1;
            Random rand = new Random();
            while (true) {
                  System.out.println("Please enter a number of orders: ");
                  int numOrders = scnr.nextInt();

                  // exit loop
                  if (numOrders == -1) {
                        break;
                  }

                  for (int i = 1; i <= numOrders; i++) {
                        int randomNum = rand.nextInt(5) + 1; // Random integer between 1, 5
                        Order order = new Order(id, randomNum, randomNum);
                        restaurant.addOrder(order);
                        id++;
                  }
            }
            scnr.close();
      }
}