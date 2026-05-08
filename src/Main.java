import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args){
        Map<String, User> users = new TreeMap<>();
        Map<String, Set<String>> usersIp = new TreeMap<>();

        //Создаем объект файл
        File file = new File("users.db");

        //Создаем scanner для чтения из файла
        Scanner scanner = new Scanner(file);

        //Построчно читаем файл в цикле, пока файл не закончится
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");

            // Сохранение ползователя
            users.put(id, new User(id, fio, address));

            // Сохранение IP-адресса пользователя
            usersIp.putIfAbsent(id, new HashSet<>());
            usersIp.get(id).add(ip);
        }
        scanner.close(); //Закрываем scanner после чтения, для освобождения файла и ресурсов
        System.out.println("Файл users.db прочитан.");

        Map<String, Integer> ipCount = new HashMap<>();

        File logFile = new File("server.log");
        Scanner logScanner = new Scanner(logFile);

        while (logScanner.hasNextLine()) {

            String line = logScanner.nextLine();

            // если есть порт
            String ip = line.split(":")[0];

            ipCount.put(ip, ipCount.getOrDefault(ip, 0) + 1);
        }
        logScanner.close(); //Закрываем scanner после чтения, для освобождения файла и ресурсов
        System.out.println("Файл server.log прочитан.");
    }
}
