import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

public class DateTimeAPI {
    public static void main(String[] args) throws InterruptedException {
        DateTimeAPI dateTimeAPI = new DateTimeAPI();
        dateTimeAPI.getLocalDate();
    }

    public DateTimeAPI(){}

    public void getLocalDate() {
        LocalDate today = LocalDate.now();
        System.out.println("Current Date="+today);

        LocalDate firstDay_2018 = LocalDate.of(2018, Month.JANUARY, 1);
        System.out.println("Specific Date="+firstDay_2018);

        LocalDate todayTokyo = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Current Date in Tokyo="+todayTokyo);

        LocalDate dateFromBase = LocalDate.ofEpochDay(50);
        System.out.printf("50th day from base date= %s", dateFromBase);
    }
}
