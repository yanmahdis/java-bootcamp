import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileApi {
    public static void main(String[] args) throws InterruptedException {
        FileApi fileAPI = new FileApi();
        fileAPI.withoutMultiCatch();
        fileAPI.withMultiCatch();
        fileAPI.getPath();
        fileAPI.fileReading();
        fileAPI.fileWrite();
        fileAPI.fileBufferedWrited();
    }

    public void withoutMultiCatch() {
        System.out.println("withoutMultiCatch");
        String input = "ABC";
        int num = 0;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            num = 1;
        } catch (NullPointerException npe) {
            num = 1;
        } finally {
            System.out.println(num);
            System.out.println();
        }
    }

    public void withMultiCatch() {
        System.out.println("withMultiCatch");
        String input = null;
        int num = 0;
        try {
            num = Integer.parseInt(input);
        } catch (NullPointerException | NumberFormatException nfe) {
            num = 1;
        } finally {
            System.out.println(num);
            System.out.println();
        }
    }

    public void getPath() {
        System.out.println("getPath");
        Path p1 = Paths.get("main.java");
        Path p2 = Paths.get("C:\\Users\\yan_s\\IdeaProjects\\heritage\\src\\main.java");

        System.out.println(p1.toAbsolutePath());
        System.out.println(p2);
        System.out.printf("getFileName: %s%n", p2.getFileName());
        System.out.printf("getName(0): %s%n", p2.getName(0));
        System.out.printf("getNameCount: %d%n", p2.getNameCount());
        System.out.printf("subpath(0,2): %s%n", p2.subpath(0, 2));
        System.out.printf("getParent: %s%n", p2.getParent());
        System.out.printf("getRoot: %s%n", p2.getRoot());
        System.out.println();
    }

    public void fileReading() {
        System.out.println("File Reading");
        Path p1 = Paths.get("C:\\Users\\yan_s\\IdeaProjects\\heritage\\src\\main.java");
        try (Stream<String> stream = Files.lines(p1)) {
            stream.map(String::toUpperCase)
                    .filter(e -> e.contains("CDC"))
                    .forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void fileWrite() {
        System.out.println("File Write");
        Path output = Paths.get("output-test.txt");
        try {
            List<String> result = new ArrayList<>();
            result.add("Start" + LocalDateTime.now().toString());
            result.addAll(this.getFileContent());
            result.add("end" + LocalDateTime.now().toString());
            Files.write(output, result, Charset.defaultCharset());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void fileBufferedWrited() {
        System.out.println("File Buffered Writer");
        Path output = Paths.get("output-test-buffered.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(output, Charset.defaultCharset())) {
            List<String> result = new ArrayList<>();
            result.add("Start" + LocalDateTime.now().toString());
            result.addAll(this.getFileContent());
            result.add("end" + LocalDateTime.now().toString());
            for (String line : result) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }



    private List<String> getFileContent() throws IOException {
        Path p1 = Paths.get("C:\\Users\\yan_s\\IdeaProjects\\heritage\\src\\main.java");
        Stream<String> stream = Files.lines(p1);
        Path output = Paths.get("output-test.txt");
        List<String> result = stream.map(String::toUpperCase)
                .filter(e -> e.contains("CDC"))
                .collect(Collectors.toList());
        return result;
    }
}
