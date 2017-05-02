import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;


public class HabrahabrParser_with_Stream {

    public static void main(String[] args) throws IOException {

        IntStream.range(1, 4)
                .mapToObj(s -> {
                    String url = "https://m.habrahabr.ru/page" + s + "/";
                    return url;
                })
                .map(s -> {

                            Document doc = new Document("");
                            try {
                                {
                                    doc = Jsoup.connect(s)
                                            .get();

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return doc;
                        }
                )
                .map(s -> (s
                        .select("h3")
                        .toString()
                        .split("</h3>"))

                )
                .flatMap(s -> Arrays.stream(s)
                        .map(x -> (x.substring(x.lastIndexOf(">") + 1))))


                .forEach(System.out::println);


    }
}