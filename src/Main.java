import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
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
            String ip = data[0];
            String id = data[1];
            String fio = data[2];
            String address = data[3];

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

        String villainIp = "";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : ipCount.entrySet()){
            if (entry.getValue() > maxCount){
                maxCount = entry.getValue();
                villainIp = entry.getKey();
            }
        }
        String villainId = "";

        for (Map.Entry<String, Set<String>> entry : usersIp.entrySet()){
            if (entry.getValue().contains(villainIp)){
                villainId = entry.getKey();
                break;
            }
        }

        User villain = users.get(villainId);

        System.out.println();
        System.out.println("Предполагаемый злоумышленник:");
        System.out.println("ФИО: " + villain.getFio());
        System.out.println("Адрес: " + villain.getAddress());
        System.out.println("Количество входов: " + maxCount);
    }
}
