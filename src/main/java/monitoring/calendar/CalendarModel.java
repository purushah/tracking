package monitoring.calendar;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.time.DateUtils;

import monitoring.monitoring.Util;

public class CalendarModel extends DefaultTableModel {
	static String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	public static int DaysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	static String[][] calendar = new String[5][7];

	public static final int FEBRUARY = 1;
	Map<String, List<String>> event = null;

	public CalendarModel() {
		super(calendar, days);
		event = Util.getCalanderMap();

		for (int i = 0; i < 5; ++i)
			for (int j = 0; j < 7; ++j)
				calendar[i][j] = " ";
	}

	public int getRowCount() {
		return 5;
	}

	public int getColumnCount() {
		return 7;
	}

	public Object getValueAt(int row, int column) {
		return calendar[row][column];
	}

	public void setValueAt(Object value, int row, int column) {
		calendar[row][column] = (String) value;
	}

	@SuppressWarnings("deprecation")
	public void setMonth(Calendar cal) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 7; ++j) {
				calendar[i][j] = " ";
			}
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int start = CalcFirstOfMonth(cal.getTime().getYear(), cal.getTime().getMonth());

		int num = daysInMonth(cal.getTime().getYear(), cal.getTime().getMonth());
		int count = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = start; j < 7; j++) {
				calendar[i][j] = Integer.toString(count) + "\n" + getEvent(cal, count);
				count++;
				if (count == num) {
					return;
				}
			}
			start = 0;
		}
	}

	public boolean isLeapYear(int year) {
		if (year % 4 == 0)
			return true;
		return false;
	}

	public int daysInMonth(int year, int month) {
		int days = DaysInMonth[month];
		if (month == 1 && isLeapYear(year))
			++days;
		return days;
	}

	public String getColumnName(int column) {
		return days[column];
	}

	public static int CalcFirstOfMonth(int year, int month) {

		int firstDay;

		// if the month is not 1 thru 12
		if ((month < 0) || (month > 11)) {
			return (-1);
		}

		firstDay = CalcJanuaryFirst(year);

		// loop to find first day of month
		for (int i = 0; i < month; i++) {
			firstDay += DaysInMonth[i];
		}

		// go to next day if it's a leap year and month is after February
		if ((month > FEBRUARY) && IsLeapYear(year)) {
			firstDay++;
		}

		return (firstDay % 7);
	}

	/**
	 * Determines if the specified year is a Leap Year
	 * 
	 * @param year
	 *            the year
	 * @return true if the year is a Leap Year, false otherwise
	 **/
	public static boolean IsLeapYear(int year) {
		// if the year is a multiple of 100, the year is a leap year if its also
		// a multiple of 400
		if ((year % 100) == 0) {

			if ((year % 400) == 0) {
				return true;
			} else {
				return false;
			}
		}

		// year is also leap year if multiple of 4
		if ((year % 4) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines the day of the week January 1st of the given year occurs on
	 * 
	 * @param year
	 *            the year
	 * @return number of the day of the week (1 is Monday, etc...)
	 * 
	 **/
	public static int CalcJanuaryFirst(int year) {

		int numLeapYears, numYearsSince1582;

		// modern calendar started in 1582
		if (year < 1582) {
			return (-1);
		}

		// 1-1-1582 was a friday, so 5 days + a day for every year since 1582.
		// add an extra day leap years because January 1st moved two days those
		// years
		numLeapYears = CalcLeapYears(year);
		numYearsSince1582 = year - 1582;
		return ((5 + numYearsSince1582 + numLeapYears) % 7);
	}

	public static int NumRowsNeeded(int year, int month) {
		int firstDay; // day of week of the first of the month
		int numBlocks; // number of "blocks" the month needs

		// modern calendar started in 1582
		if (year < 1582) {
			return (-1);
		}

		// if the month is not 1 thru 12
		if ((month < 0) || (month > 11)) {
			return (-1);
		}

		firstDay = CalcFirstOfMonth(year, month);

		// a February which isn't a leap year with Sunday as first day needs 4
		// rows
		if ((month == FEBRUARY) && (firstDay == 0) && !IsLeapYear(year)) {
			return (4);
		}

		numBlocks = firstDay + DaysInMonth[month];

		// we need an extra block for the 29th in a leap year February
		if ((month == FEBRUARY) && (IsLeapYear(year))) {
			numBlocks++;
		}
		// all other cases will need 5 or 6 rows
		if (numBlocks <= 35) {
			return 5;
		} else {
			return 6;
		}
	}

	public static int CalcLeapYears(int year) {
		int numLeapYears, multipleHundred, multipleFourHundred;

		// modern calendar started in 1582
		if (year < 1582) {
			return (-1);
		}

		// # of years that are multiple of 4
		numLeapYears = (year - 1581) / 4;

		// # years that are multiple of a hundred
		multipleHundred = (year - 1501) / 100;

		// those years aren't leap years
		numLeapYears = numLeapYears - multipleHundred;

		// # years that are multiple of four hundred
		multipleFourHundred = (year - 1201) / 400;

		// those years are leap years
		numLeapYears = numLeapYears + multipleFourHundred;

		return numLeapYears;
	}

	public String getEvent(Calendar startMonth, int day) {

		Calendar cal = (Calendar) startMonth.clone();
		try {
			List<String> todayEvents = event.get(Util.getDate(DateUtils.addDays(cal.getTime(), day + 1)));
			if (todayEvents == null) {
				return "";
			} else {
				String events = "";
				for (String todayEvent : todayEvents) {
					events = events + todayEvent + "\n";
				}
				return events;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}