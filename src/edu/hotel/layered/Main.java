
import edu.hotel.layered.util.SessionFactoryConfiguration;
import edu.hotel.layered.view.CustomerView;
import edu.hotel.layered.view.HomeView;
import edu.hotel.layered.view.LoginView;
import edu.hotel.layered.view.ReservationView;
import org.hibernate.Session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Yasas Randeeka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new LoginView().setVisible(true);
        
        //Session session = SessionFactoryConfiguration.getInstance().getSession();
    }
    
}
