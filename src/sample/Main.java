/*Разработать приложение на основе UDP-соединения,
позволяющее осуществлять взаимодействие клиента и сервера
по совместному решению задач обработки информации.
Приложение должно располагать возможностью передачи и модифицирования получаемых (передаваемых) данных.
Возможности клиента: передать серверу исходные параметры
(число а=2, число b=4 и число с=7 вводятся с клавиатуры) для расчета значения функции,
а также получить расчетное значение функции. Возможности сервера: по полученным от клиента исходным
параметрам рассчитать сумму первого ряда от а до b и сумму второго ряда от b до с.
Расчет первой и второй сумм осуществляется в разных потоках.*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}