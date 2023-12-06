package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Class {@code FridayThirteen} provides utility methods to find Fridays the 13th in a given year and
 * the next Friday the 13th from a given date.
 */
public final class FridayThirteen {
    private static final int DAYS_IN_WEEK = 7;
    private static final int THIRTEENTH_DAY = 13;

    private FridayThirteen() {
    }

    /**
     * Finds all Fridays the 13th in a given year.
     *
     * @param year the year to search for Fridays the 13th. Must be greater than 0.
     * @return a list of {@link LocalDate} objects representing all Fridays the 13th in the given year.
     * @throws IllegalArgumentException if the year is less than 0.
     */
    public static @NotNull List<LocalDate> findFridaysThirteen(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("The input integer must be greater than 0.");
        }

        List<LocalDate> fridaysThirteen = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, THIRTEENTH_DAY);

        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysThirteen.add(date);
            }

            date = date.plusMonths(1);
        }

        return fridaysThirteen;
    }

    /**
     * Finds the next Friday the 13th from a given start date.
     *
     * @param startDate the date to start the search from. Must not be null.
     * @return a {@link LocalDate} object representing the next Friday the 13th.
     * @throws NullPointerException if startDate is null.
     */
    public static LocalDate findNextFriday13(LocalDate startDate) {
        Objects.requireNonNull(startDate);
        LocalDate date = startDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (date.getDayOfMonth() != THIRTEENTH_DAY) {
            date = date.plusDays(DAYS_IN_WEEK);
        }

        return date;
    }
}
