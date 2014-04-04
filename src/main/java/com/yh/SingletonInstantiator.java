package com.yh;

public class SingletonInstantiator {
	 /* case 1 */

    private static SingletonInstantiator singleton = new SingletonInstantiator();

    public static int counter1;

    public static int counter2 = 0;



    /**

     * case 2

* public static int counter1 = 0;

* public static int counter2 = 0;

     * private static SingletonInstantiator singleton = new SingletonInstantiator();

     */

    private SingletonInstantiator() {

              counter1++;

              counter2++;

    }



    public static SingletonInstantiator getInstance() {

              return singleton;

    }


}
