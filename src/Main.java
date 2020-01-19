
import javafx.application.Application;


public class Main {
    public static void main(String[] args) {
        RWDatabase database = new RWDatabase();
//        database.setAccount("ramon@gmail.com","ABC","ramon","goese_dieplaan_41","AAAAAA");
//        database.getAccount("ramon@gmail.com","123");
        Application.launch(PanelSelect.class, args);
    }
}
