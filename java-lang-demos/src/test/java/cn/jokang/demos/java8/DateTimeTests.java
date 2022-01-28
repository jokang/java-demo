package cn.jokang.demos.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.zone.ZoneRulesException;
import java.util.*;

/**
 * 演示了如何使用Java8的时间日期类。这些类都是不可变的。
 *
 * @author zhoukang
 * @date 2019-03-29
 */
@Slf4j
public class DateTimeTests {
    @Test
    public void testLocalDate() {
        //Current Date
        LocalDate today = LocalDate.now();
        System.out.println("Current Date=" + today);

        //Creating LocalDate by providing input arguments
        // 月份还是从0开始的
        LocalDate firstDayOf2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("Specific Date=" + firstDayOf2014);

        //Try creating date by providing invalid inputs
        try {
            LocalDate feb29Of2014 = LocalDate.of(2014, Month.FEBRUARY, 29);
        } catch (DateTimeException e) {
            log.error("Fail:", e);
        }

        //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
        LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Date in IST=" + todayKolkata);

        //java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
        try {
            LocalDate todayIST = LocalDate.now(ZoneId.of("IST"));
        } catch (ZoneRulesException e) {

        }

        //Getting date from the base date i.e 01/01/1970
        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365th day from base date= " + dateFromBase);

        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
        System.out.println("100th day of 2014=" + hundredDay2014);

    }

    @Test
    public void testChronoField() {
        LocalDate today = LocalDate.now();
        System.out.println(today.get(ChronoField.DAY_OF_MONTH));
    }

    @Test
    public void testLocalTime() {
        //Current Time
        LocalTime time = LocalTime.now();
        System.out.println("Current Time=" + time);

        //Creating LocalTime by providing input arguments
        LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
        System.out.println("Specific Time of Day=" + specificTime);

        //Try creating time by providing invalid inputs
        try {
            LocalTime invalidTime = LocalTime.of(25, 20);
        } catch (DateTimeException e) {
            log.error("Fail", e);
        }

        //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
        LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Time in IST=" + timeKolkata);

        //java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
        try {
            LocalTime todayIST = LocalTime.now(ZoneId.of("IST"));
        } catch (ZoneRulesException e) {
            log.error("Fail", e);
        }

        //Getting date from the base date i.e 01/01/1970
        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
        System.out.println("10000th second time= " + specificSecondTime);
    }

    @Test
    public void testInstant() {
        // instant 作为名词，有瞬间的意思
        //Current timestamp
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = " + timestamp);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = " + specificTime);


    }

    @Test
    public void testDuration() {
        //Duration example
        Duration thirtyDay = Duration.ofDays(30);
        System.out.println(thirtyDay);
        System.out.println(thirtyDay.get(ChronoUnit.SECONDS));
    }

    @Test
    public void testPeriod() {
        Period period = Period.ofDays(4);
        System.out.println("Period Format= " + period);
        System.out.println("Months remaining in the year= " + period.getMonths());
    }

    /**
     * 测试时间计算
     */
    @Test
    public void testCalculation() {
        LocalDate today = LocalDate.now();

        //Get the Year, check if it's leap year
        System.out.println("Year " + today.getYear() + " is Leap Year? " + today.isLeapYear());

        //Compare two LocalDate for before and after
        System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDate.of(2015, 1, 1)));

        //Create LocalDateTime from LocalDate
        System.out.println("Current Time=" + today.atTime(LocalTime.now()));

        //plus and minus operations
        System.out.println("10 days after today will be " + today.plusDays(10));
        System.out.println("3 weeks after today will be " + today.plusWeeks(3));
        System.out.println("20 months after today will be " + today.plusMonths(20));

        System.out.println("10 days before today will be " + today.minusDays(10));
        System.out.println("3 weeks before today will be " + today.minusWeeks(3));
        System.out.println("20 months before today will be " + today.minusMonths(20));

        //Temporal adjusters for adjusting the dates
        System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= " + lastDayOfYear);
        System.out.println("today= " + today);


        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= " + period);
        System.out.println("Months remaining in the year= " + period.getMonths());
    }

    @Test
    public void testDateTimeFormat() {
        //Format examples
        LocalDate date = LocalDate.now();
        //default format
        System.out.println("Default format of LocalDate=" + date);
        //specific format
        System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

        LocalDateTime dateTime = LocalDateTime.now();
        //default format
        System.out.println("Default format of LocalDateTime=" + dateTime);
        //specific format
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

        Instant timestamp = Instant.now();
        //default format
        System.out.println("Default format of Instant=" + timestamp);


    }

    @Test
    public void testLocalDateTimeParse() {
        //ofPattern是带Locale的格式。
        // 跟环境相关，中文环境下应该用"27::四月::2014 21::39::48"
        try {
            LocalDateTime dt = LocalDateTime.parse("27::Apr::2014 21::39::48",
                DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
            System.out.println("Default format after parsing = " + dt);
        } catch (DateTimeParseException e) {
            log.error("Fail", e);
        }

        LocalDate d = LocalDate.parse("20190702", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(d);
    }

    @Test
    public void testZoneId() {
        ZoneId z = ZoneId.of("America/Chicago");
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zonedDateTime = date.atStartOfDay(z);
        System.out.println(zonedDateTime);
    }


    /**
     * 测试与旧API的兼容性
     */
    @Test
    public void testCompatibility() {
        //Date转Instant
        Instant timestamp = new Date().toInstant();
        //Now we can convert Instant to LocalDateTime or other similar classes
        LocalDateTime date = LocalDateTime.ofInstant(timestamp,
            ZoneId.of(ZoneId.SHORT_IDS.get("PST")));
        System.out.println("Date = " + date);

        //Calendar to Instant
        Instant time = Calendar.getInstance().toInstant();
        System.out.println(time);

        //TimeZone to ZoneId
        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        System.out.println(defaultZone);

        //ZonedDateTime from specific Calendar
        ZonedDateTime gregorianCalendarDateTime = new GregorianCalendar().toZonedDateTime();
        System.out.println(gregorianCalendarDateTime);

        //Date API to Legacy classes
        Date dt = Date.from(Instant.now());
        System.out.println(dt);

        TimeZone tz = TimeZone.getTimeZone(defaultZone);
        System.out.println(tz);

        GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
        System.out.println(gc);
    }

    @Test
    public void testMinusInstant() {
        Instant endInstant = Instant.now();
        Instant startInstant = endInstant.minus(7, ChronoUnit.DAYS);
        log.info("startInstant is " + startInstant + ". endInstant is " + endInstant);
        log.info("startInstant.getEpochSecond is " + startInstant.getEpochSecond());
    }

    @Test
    public void testMonthsBetween() {
        LocalDate d = LocalDate.now();
        LocalDate d2 = d.plusMonths(1);
        Period p = Period.between(d, d2);
        System.out.println(p);
    }

    @Test
    public void daysBetween() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse("20200101", dtf);
        LocalDate endDate = LocalDate.parse("20200101", dtf);
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        Assert.assertEquals(0, days);
    }


    @Test
    public void testDateRange() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fromDate = LocalDate.parse("20200601", dtf);
        LocalDate endDate = LocalDate.parse("20200601", dtf);

        if (endDate.isBefore(fromDate)) {
            return;
        }

        LocalDate processingDate = fromDate;
        while (processingDate.isBefore(endDate) || processingDate.isEqual(endDate)) {
            // System.out.println(processingDate);
            Date d = Date.from(processingDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            // 转成Date, 传给其它参数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(sdf.format(d));
            processingDate = processingDate.plusDays(1);
        }
    }

    @Test
    public void testLocalDateTime2Date() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        System.out.println(zdt);
        System.out.println(Date.from(zdt.toInstant()));
    }
}
