import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @version v1.0
 * @author: Zewei Shi
 * @date: Created in 06/03/2023 9:33 pm
 * @description: TODO
 */
public class SearchApp extends Application {
    private static String[] args;
//    private static MainController mainController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.init();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ACL Search Engine");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        SearchApp.args = args;
        launch(args);
    }
}