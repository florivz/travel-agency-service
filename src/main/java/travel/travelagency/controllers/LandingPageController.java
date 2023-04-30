package travel.travelagency.controllers;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.TravelAgencyServiceApplication;
import travel.travelagency.authentication.TravelAgencyDatabaseAuthenticator;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;

public class LandingPageController extends TravelAgencyController {

    static final Logger logger = LogManager.getLogger(LandingPageController.class);

    public static final String VIEW_NAME = "landing_page.fxml";

    @FXML public Text headerMessage;
    @FXML public Group usernameGroup;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML public Text infoMessage;
    @FXML public Button loginButton;

    private final String LANGUAGES_DIRECTORY = "src/main/resources/languages/";

    private final String VIEW_DIRECTORY = "landing_page/";

    /**
     * Text to be displayed when login credentials are falsely
     */
    private String msg_invalid_credentials;

    /**
     * Text to be displayed while attempting to log in
     */
    private String msg_login_attempt;

    /**
     * Text to be displayed if this controller is unable to load the next page
     */
    private String msg_view_not_loaded;

    private class LoginThread extends Thread {

        @Override
        public void run() {
            try {
                TravelAgencyEntityManagerFactory factory = TravelAgencyDatabaseAuthenticator.loginToDataBase(
                    usernameTextField.getText(), passwordTextField.getText()
                );
                FXMLLoader loader = TravelAgencyServiceApplication.getFXMLLoader(StartingPageController.VIEW_NAME);
                Scene scene = application.loadScene(loader);
                StartingPageController controller = loader.getController();
                controller.setApplication(application);
                controller.setEntityManagerFactory(factory);
                Platform.runLater(() -> application.setScene(scene));
            } catch (RuntimeException e) {
                logger.info(e.getMessage());
                final String MSG;
                if(e.getClass().equals(IllegalStateException.class))
                    MSG = msg_view_not_loaded;
                else {
                    MSG = msg_invalid_credentials;
                }
                Platform.runLater(() -> {
                    usernameTextField.clear();
                    passwordTextField.clear();
                    infoMessage.setFill(Color.RED);
                    infoMessage.setText(MSG);
                    usernameTextField.requestFocus();
                });
            }
        }

    }

    public void initialize() {
        setTexts(TravelAgencyServiceApplication.getLanguageFile());
        usernameTextField.requestFocus();
    }

    private void setTexts(String languageFile) {
        //load texts for java fx elements
        Properties languageProperties = LanguagePropertiesLoader.loadProperties(
                LANGUAGES_DIRECTORY + VIEW_DIRECTORY, languageFile
        );
        headerMessage.setText(languageProperties.getProperty("login.title", "Welcome back!"));
        usernameTextField.setPromptText(languageProperties.getProperty("login.username", "Username"));
        passwordTextField.setPromptText(languageProperties.getProperty("login.password", "Password"));
        loginButton.setText(languageProperties.getProperty("login.login", "LOG IN"));

        //load texts for error messages
        Properties errorMessageProperties = LanguagePropertiesLoader.loadProperties(
            LANGUAGES_DIRECTORY + "error_messages/", languageFile
        );
        msg_invalid_credentials = errorMessageProperties.getProperty(
            "landingPage.invalidDatabaseCredentials", "Invalid credentials"
        );
        msg_view_not_loaded = errorMessageProperties.getProperty(
            "landingPage.unableToLoadView", "Unable to load service"
        );

        //load texts for info texts
        Properties infoTextProperties = LanguagePropertiesLoader.loadProperties(
            LANGUAGES_DIRECTORY + "info_texts/", languageFile
        );
        msg_login_attempt = infoTextProperties.getProperty(
            "landingPage.attemptLogin", "Attempting login..."
        );
    }

    @FXML
    private void _login_onClick(ActionEvent actionEvent) throws IOException {
        infoMessage.setFill(Color.BLACK);
        infoMessage.setText(msg_login_attempt);
        new LoginThread().start();
    }


}

