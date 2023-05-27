import com.example.sef_project.DBUtils;
import com.example.sef_project.LogInController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.Assert.assertNotNull;


public class LogInControllerTest {

    private LogInController controller;
    private TextField tfUsername;
    private TextField tfPassword;
    private Button buttonLogin;
    private Button buttonSignUp;

    @BeforeEach
    public void setUp() {
        controller= new LogInController();
        buttonLogin = new Button();
        buttonSignUp = new Button();
        tfUsername = new TextField();
        tfPassword = new TextField();

        controller.setButton_login(buttonLogin);
        controller.setButton_sign_up(buttonSignUp);
        controller.setTf_username(tfUsername);
        controller.setTf_password(tfPassword);
    }

    @Test
    public void testInitialize() {
        // Set up test variables
        ActionEvent loginEvent = new ActionEvent();
        ActionEvent signUpEvent = new ActionEvent();

        URL location = getClass().getResource("/log-in.fxml");
        ResourceBundle resources = ResourceBundle.getBundle("TestResources");


        controller.initialize(location, resources);

        // Verify that the button_login
        assertNotNull(buttonLogin.getOnAction());
        buttonLogin.getOnAction().handle(loginEvent);

        // Verify that the button_sign_up
        assertNotNull(buttonSignUp.getOnAction());
        buttonSignUp.getOnAction().handle(signUpEvent);
    }



    @Test
    public void testButtonLoginAction() {
       /** tfUsername.setText("username");
        tfPassword.setText("password");

        // Simulate button click
        buttonLogin.fireEvent(new ActionEvent());*/
        ActionEvent loginEvent = new ActionEvent();

        // Verify that the button_login
        assertNotNull(buttonLogin.getOnAction());
        buttonLogin.getOnAction().handle(loginEvent);

    }

    @Test
    public void testButtonSignUpAction() {
        ActionEvent signUpEvent = new ActionEvent();

        // Simulate button click
        buttonSignUp.fireEvent(new ActionEvent());

    }
}
