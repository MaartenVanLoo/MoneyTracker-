import controller.TravelController;
import travel.Travel;
import JavaGUI.view.Home;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.run();
    }
    public Main(){}

    public void run() throws InterruptedException {
        Travel travel = new Travel();
        TravelController controller = new TravelController(travel);
        Home home = new Home(controller);
    }
}
