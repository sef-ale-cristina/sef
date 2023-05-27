
import com.example.sef_project.DBUtils;
import com.example.sef_project.SignUpPacientController;
import javafx.embed.swing.JFXPanel;
        import javafx.event.ActionEvent;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import org.junit.Before;
        import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

        import java.net.URL;
        import java.time.LocalDate;
        import java.util.ResourceBundle;

public class SignUpPacientTest {

    private SignUpPacientController controller;
    private Button buttonSignup;
    private Button buttonLogIn;
    private TextField tfName;
    private TextField tfLastname;
    private DatePicker dpBirthdate;
    private TextField tfPhone;
    private TextField tfOther;
    private Label lRole;
    private TextField tfPassword;
    private TextField tfUsername;
    private DBUtils dbUtils;

    @BeforeEach
    public void setUp() {
        // Initialize JavaFX toolkit
        JFXPanel jfxPanel = new JFXPanel();

        controller = new SignUpPacientController();
        buttonSignup = new Button();
        buttonLogIn = new Button();
        tfName = new TextField();
        tfLastname = new TextField();
        dpBirthdate = new DatePicker();
        tfPhone = new TextField();
        tfOther = new TextField();
        lRole = new Label();
        tfPassword = new TextField();
        tfUsername = new TextField();

        controller.setButton_log_in(buttonLogIn);
        controller.setButton_signup(buttonSignup);
        controller.setTf_name(tfName);
        controller.setTf_lastname(tfLastname);
        controller.setDp_birthdate(dpBirthdate);
        controller.setTf_phone(tfPhone);
        controller.setTf_other(tfOther);
        controller.setL_role(lRole);
        controller.setTf_password(tfPassword);
        controller.setTf_username(tfUsername);
    }

    @Test
    public void testInitialize() {
        // Set up test variables
        ActionEvent signUpEvent = new ActionEvent();
        ActionEvent logInEvent = new ActionEvent();
        // controller.getTf_name();
        String validName = "Cristina";
        String validLastname = "Rodean";
        LocalDate validBirthdate = LocalDate.of(2023, 5, 4);
        String validPhone = "0723456789";
        String validOther = "Other info";
        String validUsername = "Cristina";
        String validPassword = "cris";
        String validRole = "pacient";


    }
}