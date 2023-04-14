package travel.travelagency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.beans.value.ChangeListener;

public class MainApp extends Application {

    private static String mainView;

    private static final String VIEW_DIRECTORY = "views";

    private static Scene scene;
    protected static String layout;

    @Override
    public void start(Stage stage) throws IOException {
        layout = "";

        scene = new Scene(loadFXML(VIEW_DIRECTORY + layout + "/" + mainView));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
/*
        //responsive listener
        ChangeListener<Number> responsiveListener = (observable, oldValue, newValue) -> {
            layout = "";

            try{
                setRoot(mainView);
            } catch (IOException e) {

            }
        };

        stage.widthProperty().addListener(responsiveListener);
        stage.heightProperty().addListener(responsiveListener);*/
    }

    public static void setRoot(String fxml) throws IOException {
        MainApp.mainView = fxml;
        scene.setRoot(loadFXML("views"+layout+"/"+fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        mainView = args[0];
        launch();
    }

}