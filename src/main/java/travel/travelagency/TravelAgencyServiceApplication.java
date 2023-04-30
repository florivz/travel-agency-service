package travel.travelagency;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.controllers.LandingPageController;
import travel.travelagency.controllers.StartingPageController;
import travel.travelagency.controllers.TravelAgencyController;

public class TravelAgencyServiceApplication extends Application {

    private static String languageFile = "en_US.properties";

    private static final String VIEW_DIRECTORY = "views/";

    private static final String MSG_FXML_LOADING_FAILED = "Unable to load fxml file with path %s";

    private Stage stage;
    private String mainView = "";
    private String layout = "";

    static final Logger logger = LogManager.getLogger(TravelAgencyServiceApplication.class);

    @Override
    public void start(Stage stage) {
        //read parameters from main(String[] args) method
        List<String> parameters = this.getParameters().getRaw();
        switch(parameters.size()) {
            case 3: layout = parameters.get(2);
            case 2: languageFile = parameters.get(1);
            case 1: mainView = parameters.get(0);
        }

        //set up stage
        this.stage = stage;
        setRoot(mainView);
        this.stage.setMaximized(true);
        this.stage.show();
    }

    public void setRoot(String rootView) {
        FXMLLoader loader = getFXMLLoader(rootView);
        Scene scene = loadScene(loader);
        TravelAgencyController controller = loader.getController();
        controller.setApplication(this);
        setScene(scene);
    }

    public Scene loadScene(FXMLLoader fxmlLoader) {
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            final String MSG = String.format(MSG_FXML_LOADING_FAILED, fxmlLoader.getLocation());
            logger.error(MSG);
            throw new RuntimeException(MSG);
        }
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public static FXMLLoader getFXMLLoader(String fxml) {
        final String VIEW_PATH = VIEW_DIRECTORY + fxml;
        return new FXMLLoader(TravelAgencyServiceApplication.class.getResource(VIEW_PATH));
    }

    public static String getLanguageFile() {
        return languageFile;
    }

    public static void main(String[] args) {
        launch(args);
    }

}