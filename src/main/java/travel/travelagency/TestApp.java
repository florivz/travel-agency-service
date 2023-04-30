package travel.travelagency;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TestApp extends Application {

    private static String mainView;

    private static String language_file;

    private static final String VIEW_DIRECTORY = "views";

    private static Scene scene;
    protected static String layout;

    static final Logger logger = LogManager.getLogger(TestApp.class);

    @Override
    public void start(Stage stage) {
        layout = "";
        try {
            scene = new Scene(loadFXML(VIEW_DIRECTORY + layout + "/" + mainView));
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        //responsive listener
        ChangeListener<Number> responsiveListener = (observable, oldValue, newValue) -> {
            layout = "";

            try{
                setRoot(mainView);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        };

        stage.widthProperty().addListener(responsiveListener);
        stage.heightProperty().addListener(responsiveListener);
    }

    public static void setRoot(String newRootView) throws IOException {
        TestApp.mainView = newRootView;
        scene.setRoot(loadFXML(VIEW_DIRECTORY + layout + "/" + newRootView));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TestApp.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static String getLanguageFile() {
        return language_file;
    }

    public static void main(String[] args) {
        mainView = "bookings.fxml";
        language_file = "en_US.properties";
        launch();
    }

}