package travel.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travel.travelagency.controllers.LandingPageController;
import travel.travelagency.controllers.TravelAgencyController;
import travel.travelagency.database.TravelAgencyEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TravelAgencyServiceApplication extends Application {

    private String languageFile = "en_US.properties";

    /**
     * Directory of fxml views
     */
    public static final String VIEW_DIRECTORY = "views/";

    /**
     * Directory of language files
     */
    public static final String LANGUAGE_DIRECTORY = "src/main/resources/languages/";

    private static final String MSG_FXML_LOADING_FAILED = "Unable to load fxml file with path %s";

    private Stage stage;
    private String mainView = "";

    private TravelAgencyEntityManagerFactory entityManagerFactory;

    static final Logger logger = LogManager.getLogger(TravelAgencyServiceApplication.class);

    @Override
    public void start(Stage stage) {
        //read parameters from main(String[] args) method
        List<String> parameters = this.getParameters().getRaw();
        switch(parameters.size()) {
            case 2: languageFile = parameters.get(1);
            case 1: mainView = parameters.get(0);
        }

        //set up stage
        this.stage = stage;
        setRoot(mainView, new LandingPageController(this));
        this.stage.setMaximized(true);
        this.stage.show();
    }

    public void setRoot(String rootView, TravelAgencyController controller) {
        FXMLLoader loader = getFXMLLoader(rootView);
        try {
            loader.setControllerFactory(c -> controller);
            Scene scene = loadScene(loader);
            setScene(scene);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public Scene loadScene(FXMLLoader fxmlLoader) throws LoadException {
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            final String MSG = String.format(MSG_FXML_LOADING_FAILED, fxmlLoader.getLocation());
            logger.error(MSG);
            throw new LoadException(MSG);
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

    public String getLanguageFile() {
        return languageFile;
    }

    public void setEntityManagerFactory(TravelAgencyEntityManagerFactory factory) {
        this.entityManagerFactory = factory;
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {
        launch(args);
    }

}