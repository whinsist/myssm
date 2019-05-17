/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

/**
 * The type Period.
 * <p>
 * Created on 2017/9/22
 *
 * @author ChaoHong.Mao
 */
public class Period {

    private LocalDate periodStart;
    private LocalDate periodEnd;
    private PeriodType periodType;

    /**
     * Instantiates a new Period.
     *
     * @param periodStart the period start
     * @param periodEnd   the period end
     */
    public Period(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = LocalDate.of(periodStart.getYear(), periodStart.getMonth(), periodStart.getDayOfMonth());
        this.periodEnd = LocalDate.of(periodEnd.getYear(), periodEnd.getMonth(), periodEnd.getDayOfMonth());
    }

    /**
     * Instantiates a new Period.
     *
     * @param localDate the local date
     * @param type      the type
     */
    public Period(LocalDate localDate, PeriodType type) {
        this.periodType = type;
        switch (type) {
            case Daily:
                this.periodStart = localDate;
                this.periodEnd = localDate;
                break;
            case Weekly:
                this.periodStart = localDate.with(DayOfWeek.MONDAY);
                this.periodEnd = localDate.with(DayOfWeek.SUNDAY);
                break;
            case Monthly:
                this.periodStart = localDate.with(TemporalAdjusters.firstDayOfMonth());
                this.periodEnd = localDate.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case Yearly:
                this.periodStart = localDate.with(TemporalAdjusters.firstDayOfYear());
                this.periodEnd = localDate.with(TemporalAdjusters.lastDayOfYear());
            default:
                break;

        }
    }

    /**
     * Is in period boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public boolean isInPeriod(Date date) {
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate.compareTo(periodStart) >= 0 && localDate.compareTo(periodEnd) <= 0;
    }

    /**
     * Gets period name.
     *
     * @return the period name
     */
    public String getPeriodName() {
        String name = "";
        switch (periodType) {
            case Daily:
                DateTimeFormatter dailyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                name = periodStart.format(dailyFormatter);
                break;
            case Weekly:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
                name = periodStart.format(formatter) + " ~ " + periodEnd.format(formatter);
                break;
            case Monthly:
                name = periodStart.getMonth().getDisplayName(TextStyle.NARROW, Locale.CHINA) + "月";
                break;
            case Yearly:
                name = periodStart.getYear() + "年";
            default:
                break;

        }
        return name;
    }

    /**
     * Gets period start.
     *
     * @return the period start
     */
    public LocalDate getPeriodStart() {
        return periodStart;
    }

    /**
     * Sets period start.
     *
     * @param periodStart the period start
     */
    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    /**
     * Gets period end.
     *
     * @return the period end
     */
    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    /**
     * Sets period end.
     *
     * @param periodEnd the period end
     */
    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    /**
     * Gets period type.
     *
     * @return the period type
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets period type.
     *
     * @param periodType the period type
     */
    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    @Override
    public String toString() {
        return "Period{" + "periodStart=" + periodStart + ", periodEnd=" + periodEnd + ", periodType=" + periodType +
                '}';
    }

    /**
     * The enum Period type.
     */
    public enum PeriodType {
        /**
         * daily period type.
         */
        Daily("daily"), /**
         * Weekly period type.
         */
        Weekly("weekly"), /**
         * Monthly period type.
         */
        Monthly("monthly"), /**
         * Yearly period type.
         */
        Yearly("yearly");

        /**
         * The Value.
         */
        String value;

        PeriodType(String value) {
            this.value = value;
        }

        /**
         * Is boolean.
         *
         * @param value the value
         * @return the boolean
         */
        public boolean is(String value) {
            return this.value.equalsIgnoreCase(value);
        }

    }

}