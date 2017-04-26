import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;


public class HabrahabrParser {

    public static void main(String[] args) {
        try {
            ArrayList<String> list = new ArrayList<>();
            for (int count = 1; count < 4; count++) {
                String url = "https://m.habrahabr.ru/page" + count + "/";
                String[] title = Jsoup.connect(url)
                        .get()
                        .select("h3")
                        .toString()
                        .split("</h3>");




                for (int index = 0; index < title.length; index++) {
                    int cutPoint = title[index].lastIndexOf(">") + 1;
                    title[index] = title[index].substring(cutPoint);
                    if (title[index].length() >= 30) {
                        list.add(title[index]);
                    }
                }


            }

            list
                    .stream()
                    .sorted()
                    .forEach(System.out::println);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}