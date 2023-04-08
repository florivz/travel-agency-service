package travel.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class TravelAgencyServiceApplication extends Application {

    private static final String
            VIEWS_DIRECTORY = "views/";

    private static final String
            MAIN_VIEW = VIEWS_DIRECTORY + "loginpage-view.fxml";

    static final Logger logger = LogManager.getLogger(TravelAgencyServiceApplication.class);
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TravelAgencyServiceApplication.class.getResource(MAIN_VIEW));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Travel Agency Service");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
