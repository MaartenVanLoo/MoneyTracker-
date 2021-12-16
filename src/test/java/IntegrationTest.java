import database.*;
import tickets.*;
import travel.Travel;




public class IntegrationTest {

    public static void IntegrationTest(String[] args){

        IntegrationTest test = new IntegrationTest();
        test.run();
    }

    public IntegrationTest(){

    }

    public void run(){// Travel.compute testen
        Travel travel = new Travel();


        travel.compute();


    }
}
