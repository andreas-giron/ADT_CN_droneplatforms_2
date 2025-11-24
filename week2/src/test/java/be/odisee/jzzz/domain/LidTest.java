package be.odisee.jzzz.domain;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LidTest {

    @Test
    void onCreate() throws ParseException {

        // Arrange
        Lid lid = new Lid();

        // Act
        lid.onCreate();

        // Assert that after onCreate(), the creation date matches today
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date creationDateWithZeroTime = formatter.parse(formatter.format(lid.getCreated())); // we only want the date part
        Date today = new Date(); // will have todays' date and time
        Date todayWithZeroTime = formatter.parse(formatter.format(today)); // we only want the date part
        assertEquals(creationDateWithZeroTime, todayWithZeroTime);
    }
}