package experia.coffee.experiacoffee;

import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class App extends Application {

    private static final String DATABASE_URL = "jdbc:mysql://localhost/information_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        if (!dataBaseExists()) {
            createAndPopulateDatabase();
        }

        stage.getIcons().add(new Image(App.class.getResourceAsStream("assets/ExperiaFavicon.png")));

        Scene scene = new Scene(loadFXML("loginPage"));

        stage.setTitle("Experia Coffee");

        stage.setScene(scene);
        stage.show();
    }

    private boolean dataBaseExists() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT 1 FROM information_schema.schemata WHERE schema_name = 'experia_coffee'");
            boolean dbExists = resultSet.next();
            return dbExists;
        } catch (Exception e) {
            PopupWindowError.handleException(e);
            return false;
        }
    }

    private void createAndPopulateDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
                executeScript("src/main/java/experia/coffee/experiacoffee/data/scripts/experia-coffee-creazioneDB.sql", connection);
                executeScript("src/main/java/experia/coffee/experiacoffee/data/scripts/experia-coffee-useDatabase.sql", connection);
                executeScript("src/main/java/experia/coffee/experiacoffee/data/scripts/experia-coffee.sql", connection);
                executeScript("src/main/java/experia/coffee/experiacoffee/data/scripts/experia-coffee-popolamento.sql", connection);
            } catch (Exception e) {
                PopupWindowError.handleException(e);
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
    }

    private void executeScript(String filePath, Connection connection) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder script = new StringBuilder();

            try (Statement preparedStatement = connection.createStatement()) {
                while ((line = reader.readLine()) != null) {
                    script.append(line).append("\n");


                    if (line.trim().endsWith(";")) {
                        preparedStatement.execute(script.toString());
                        script.setLength(0);
                    }
                }

                if (script.length() > 0) {
                    preparedStatement.execute(script.toString());
                }
            } catch (Exception e) {
                PopupWindowError.handleException(e);
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }
}