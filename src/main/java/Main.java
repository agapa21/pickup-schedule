import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        /*Data userData = new Data();
        Scanner scan = new Scanner(System.in);
        System.out.println("Street: ");
        userData.setStreet(scan.nextLine());
        System.out.println("Number: ");
        userData.setNumber(scan.nextLine());
        HttpConnector httpConnectorUser = new HttpConnector(userData);*/

        Data data = new Data("40134", "694787");
        HttpConnector httpConnector = new HttpConnector(data);
    }
}