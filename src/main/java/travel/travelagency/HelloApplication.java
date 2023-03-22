package travel.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class HelloApplication extends Application {

    private static final String
        VIEWS_DIRECTORY = "views/";

    private static final String
        MAIN_VIEW = VIEWS_DIRECTORY + "hello-view.fxml";

    static final Logger logger = LogManager.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(MAIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        logger.debug("Logger");
        logger.info("info");
        logger.debug("Logger");
        logger.info("info");
        logger.debug("Logger");
        logger.info("info");
        logger.debug("Logger");
        logger.info("info");
        logger.debug("Logger");
        logger.info("info");
        logger.debug("Logger");
        logger.info("info");
        logger.debug("Logger");
        logger.info("info");
        launch();
    }
}