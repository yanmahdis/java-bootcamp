import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamAPI {
    public static void main(String[] args) throws InterruptedException {
        StreamAPI streamAPI = new StreamAPI();
        streamAPI.steamPeek();
        streamAPI.findFirst();
        streamAPI.limit();
        streamAPI.skip();
        streamAPI.limitSkip();
        streamAPI.sorted();
        streamAPI.min();
        streamAPI.max();
        streamAPI.distinct();
        streamAPI.nonMatch();
        streamAPI.anyMatch();
        streamAPI.allMatch();
        streamAPI.count();
        streamAPI.optional();
    }

    public void steamPeek() {
        System.out.println();
        System.out.println("Stream PEEK");
        Stream.of("bus", "car", "bicycle", "flight", "train")
                .filter( e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: "+ e))
                .map(StreamAPI::getTest)
                .peek(e -> System.out.println("Mapped value: "+ e))
                .collect(toList());
    }

    private static String getTest(String A){
        return A + "#";
    }

    public void findFirst() {
        System.out.println();
        System.out.println("Stream FIND FIRST");
        String result = Stream.of("bus", "car", "bicycle", "flight", "train")
                .filter( e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: "+ e))
                .findFirst()
                .orElse("Not Found");
        System.out.println(result);
    }

    public void limit() {
        System.out.println();
        System.out.println("Stream LIMIT");
        String[] result = Stream.of("bus", "car", "bicycle", "flight", "train").limit(2).toArray(String[]::new);
        System.out.println(Arrays.toString(result));
    }

    public void skip() {
        System.out.println();
        System.out.println("Stream SKIP");
        String[] result = Stream.of("bus", "car", "bicycle", "flight", "train").skip(2).toArray(String[]::new);
        System.out.println(Arrays.toString(result));
    }

    public void limitSkip() {
        System.out.println();
        System.out.println("Stream LimitSKip");
        List<String> result = Stream.of("bus", "car", "bicycle", "flight", "train", "boat", "auto", "mobile", "ferry", "truck", "ship")
                .map(StreamAPI::called)
                .limit(8)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(result.toString());
    }

    public void sorted() {
        System.out.println();
        System.out.println("SORTED");
        String[] result = Stream.of("bus", "car", "bicycle", "flight", "train", "boat", "auto", "mobile")
                .limit(6)
                .skip(2)
                .sorted((e1, e2) -> e1.compareTo(e2))
                .toArray(String[]::new);
        System.out.println(Arrays.toString(result));
    }

    public void min() {
        System.out.println();
        System.out.println("MIN");
        Integer result = Stream.of(9,8,5,7,8,2)
                .min((e1, e2) -> e1.compareTo(e2))
                .get();
        System.out.println(result);
    }

    public void max() {
        System.out.println();
        System.out.println("MAX");
        Integer result = Stream.of(9,8,5,7,8,2)
                .max((e1, e2) -> e1.compareTo(e2))
                .get();
        System.out.println(result);
    }

    public void distinct() {
        System.out.println();
        System.out.println("DISTINCT");
        List<Integer> result = Stream.of(7,8,5,7,8,2)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result.toString());
    }

    public void nonMatch() {
        System.out.println();
        System.out.println("Non Match");
        boolean result = Stream.of(7,8,5,7,8,2)
                .noneMatch(e -> e == 10);
        System.out.println(result);
    }

    public void anyMatch() {
        System.out.println();
        System.out.println("Any Match");
        boolean result = Stream.of(7,8,5,7,8,2)
                .anyMatch(e -> e == 5);
        System.out.println(result);
    }

    public void allMatch() {
        System.out.println();
        System.out.println("All Match");
        boolean result = Stream.of(7,8,5,7,8,2)
                .allMatch(e -> e > 3);
        System.out.println(result);
    }

    public void count() {
        System.out.println();
        System.out.println("Count");
        Long result = Stream.of(7,8,5,7,8,2)
                .filter(e -> e > 4)
                .count();
        System.out.println(result);
    }

    public void optional() {
        System.out.println();
        System.out.println("Optional");
        Optional<Integer> result = Stream.of(7,8,5,7,8,2)
                .filter(e -> e > 7)
                .findFirst();

        if(result.isPresent()) {
            System.out.println("exist");
        } else {
            System.out.println("false");
        }
    }

    public static String called(String test) {
        System.out.println("Called");
        return test;
    }
}
