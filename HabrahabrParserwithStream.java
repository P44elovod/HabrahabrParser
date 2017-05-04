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
                .map(HabrahabrParserwithStream::habrahabrGet)
                .filter(Optional::isPresent)
                .map(Optional::get)

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

    public static Optional<Document> habrahabrGet(String url) {
        Optional<Document> doc = Optional.empty();
        try {
            doc = Optional.of(Jsoup.connect(url)
                    .get());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;

    }


}