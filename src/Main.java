
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try (Scanner input = new Scanner(new File("src/input"))) {
            List<Map.Entry<String, Integer>> list  = new ArrayList<>();
            Map<String, Integer> cityApCounter  = new HashMap<>();
            while (input.hasNext())
            {
                String city = input.next();
                int rooms = input.nextInt();
                int squares = input.nextInt();
                int price = input.nextInt();
                String phone = input.next();
                Apartment apartment= new Apartment(city, rooms, squares, price, phone);
                if (cityApCounter .containsKey(city))
                {
                    cityApCounter .put(city, cityApCounter .get(city) + 1);
                }
                else
                {
                    cityApCounter .put(city, 1);
                }
                if ((city.equals("София") || city.equals("Бургас") || city.equals("Варна")) && squares > 100 && rooms == 3)
                {
                    list .add(Map.entry(phone, price));
                }
            }
            if (list .isEmpty()) throw new UnsuitableApartmentsException("Няма апартаменти от търсения вид!");
            list .sort(Map.Entry.comparingByValue());
            Set<String> set = new LinkedHashSet<>();
            for (int i = 0; i < 5; i++)
            {
                set.add(list .get(i).getKey());
            }
            File file = new File("src/output.txt");
            PrintWriter output = new PrintWriter(file);
            output.write("Телефонните номера на търсените брокери са: \n");
            for (String str : set)
            {
                output.write(str + "\n");
            }
            int maximum=0;
            String cities="";
            for (String key : cityApCounter.keySet())
            {
                Integer current = cityApCounter.get(key);
                if(current>maximum)
                {
                    cities=key;
                }
                if(current==maximum)
                {
                    cities=cities+"\n"+maximum;
                }
            }
            output.write("Градовете с най - много продавани апартаменти са:\n" +cities);
            output.close();

        } catch (IOException | UnsuitableApartmentsException e) {
            System.out.println("Не е подаден файл");
        }
    }
}
