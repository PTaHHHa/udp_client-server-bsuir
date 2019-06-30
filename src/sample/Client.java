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

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;

public class Client {
    public TextField fieldA;
    public TextField fieldC;
    public TextArea Result;
    public TextField fieldB;
    public Button SendButton;

    public void counting() throws IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        DatagramPacket sendPacket;
        DatagramPacket receivePacket;
        byte[] sendData;  // массив принятых из поля данных
        sendData = fieldA.getText().getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5555);
        client.send(sendPacket);
        String B = fieldB.getText();
        sendData = B.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5555);
        client.send(sendPacket);
        String C = fieldC.getText();
        sendData = C.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5555);
        client.send(sendPacket);
        byte[] receiveData = new byte[8];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        String res = new String(receivePacket.getData());
        Result.setText(res);
    }
}

