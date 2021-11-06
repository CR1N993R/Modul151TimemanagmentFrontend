package ch.fenix.timemanagementfrontend.data;

import lombok.Getter;

import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.*;

@Getter
public enum Interval {
    YEARLY(YEARS),
    MONTHLY(MONTHS),
    WEEKLY(WEEKS),
    DAILY(DAYS);

    ChronoUnit chronoUnit;

    Interval(ChronoUnit interval) {
        chronoUnit = interval;
    }
}
