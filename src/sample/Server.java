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


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    double sum1, sum2;

    Server() throws InterruptedException {
        while (true) {
            try {
                DatagramSocket server = new DatagramSocket(5555); // определяем сокет дейтаграммы
                byte[] receiveData = new byte[10];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // определяем пакет дейтаграммы
                server.receive(receivePacket); //получаем дейтаграмму
                String a = new String(receivePacket.getData());
                a = a.substring(0, a.indexOf('\0'));
                int ia = Integer.parseInt(a);
                System.out.println("Получено от клиента " + ia);
                server.receive(receivePacket);//получаем дейтаграмму
                String b = new String(receivePacket.getData());
                b = b.substring(0, b.indexOf('\0'));
                int ib = Integer.parseInt(b);
                System.out.println("Получено от клиента " + ib);
                server.receive(receivePacket);//получаем дейтаграмму
                String c = new String(receivePacket.getData());
                c = c.substring(0, c.indexOf('\0'));
                int ic = Integer.parseInt(c);
                System.out.println("Получено от клиента " + ic);
                Thread t = new Thread(() -> {            //создание потока
                    sum1 = 0;
                    for (int i = ia; i < ib; i++) {
                        sum1 += (i * (i - 1));
                    }
                });
                Thread t2 = new Thread(() -> {              //создание потока
                    sum2 = 0;
                    for (int i = ib; i <= ic; i++) {
                        sum2 += 1 / (i + 1);
                    }
                });
                t.start();
                t2.start();
                t.join();
                t2.join();
                double res = sum1 - sum2;
                String result = String.valueOf(res);
                byte[] sendData = result.getBytes();
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                server.send(sendPacket);// посылаем пакет через сокет
                server.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
    }
}
