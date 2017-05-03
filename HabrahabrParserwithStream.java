import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;


public class HabrahabrParserwithStream {

    public static void main(String[] args) {

        IntStream.range(1, 4)
                .mapToObj(s -> {

                    return "https://m.habrahabr.ru/page" + s + "/";
                })
                .map(s -> (habrahabrGet(s))

                )
                .map(s -> (s
                        .select("h3")
                        .toString()
                        .split("</h3>"))

                )
                .flatMap(s -> Arrays.stream(s)
                        .map(x -> (x.substring(x.lastIndexOf(">") + 1))))
                .filter(s -> (s.length() > 30))
                .sorted()
                .forEach(System.out::println);


    }

    public static Document habrahabrGet(String s) {
        Document doc = getNull().orElse(null);
        try {
            doc = Jsoup.connect(s)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;

    }

    public static Optional<Document> getNull() {
        return Optional.empty();
    }

}