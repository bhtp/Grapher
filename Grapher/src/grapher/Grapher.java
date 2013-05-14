/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher;

/**
 *
 * @author Parker
 */
public class Grapher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(NetworkFunctions.fetchData("https://www.bitstamp.net/api/ticker/", "high"));
    }
}
