package calendarWithEvents;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import monitoring.monitoring.Util;

/**
 * Created by MindFusion.
 */
public class MainWindow extends JFrame {

//	AwtCalendar calendar;
//	Recurrence recurrence;
//
//	Map<String, List<String>> plan = new HashMap<String, List<String>>();
//
//	protected MainWindow() throws IOException, ParseException {
//		plan = Util.getCalanderMap();
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		setTitle("MindFusion.Scheduling Sample: Calendar With Events");
//		setMinimumSize(new Dimension(400, 600));
//
//		BorderLayout layout = new BorderLayout();
//		getContentPane().setLayout(layout);
//
//		JLabel label = new JLabel("This sample demonstrates the TimeTable view and recurrent events. "
//				+ "Click on a cell and a new event will be created that will occur each week on the selected day.");
//		label.setBorder(new EmptyBorder(10, 10, 10, 10));
//		getContentPane().add(label, BorderLayout.NORTH);
//
//		// Calendar initialization start
//		calendar = new AwtCalendar();
//		calendar.beginInit();
//		// set the current time
//		calendar.setCurrentTime(DateTime.now());
//		DateTime today = DateTime.today();
//		// set the current date
//		calendar.setDate(today);
//		// Select the current date
//		calendar.getSelection().set(DateTime.today());
//		calendar.getFirstVisibleDate();
//
//		calendar.setCurrentView(CalendarView.SingleMonth);
//		calendar.setCustomDraw(CustomDrawElements.CalendarItem);
//		calendar.getMonthSettings().getDaySettings().setHeaderSize(20);
//		calendar.getItemSettings().setSize(32);
//		addMonth();
//		calendar.endInit();
//
//		// add a listener for custom draw
//		calendar.addCalendarListener(new CalendarAdapter() {
//			@Override()
//			public void draw(DrawEvent e) {
//				onDraw(e);
//			}
//		});
//
//		// add a listener for selecting events
//		calendar.addCalendarListener(new CalendarAdapter() {
//			public void dateClick(ResourceDateEvent e) {
//				onDateClicked(e);
//			}
//
//		});
//		
//		// add a listener for selecting events
//				calendar.addCalendarListener(new CalendarAdapter() {
//					
//					@Override
//					public void itemCreated(ItemEvent e){
//						System.out.println("ppppp");
//					}
//					
//					public void itemModified(ItemModifiedEvent itemmodifiedevent)
//				    {
//						System.out.println("ppppp");
//
//				    }
//
//
//				});
//
//
//		// arrange the calendar
//		getContentPane().add(calendar, BorderLayout.CENTER);
//
//		pack();
//		setVisible(true);
//	}
//
//	/* called when a date is clicked. Creates a recurring appointment. */
//	protected void onDateClicked(ResourceDateEvent e) {
//
//		int dayIndex = e.getDate().getDayOfWeek();
//
//		Appointment item = new Appointment();
//		item.setStartTime(e.getDate());
//		item.setEndTime(e.getDate());
//		item.setHeaderText(events[dayIndex]);
//		item.getStyle().setBrush(brushes[dayIndex]);
//
//		// Create the recurrence pattern.
//		recurrence = new Recurrence();
//		recurrence.setPattern(RecurrencePattern.Weekly);
//		recurrence.setDaysOfWeek(getDayOfWeek(dayIndex));
//		recurrence.setStartDate(e.getDate());
//		recurrence.setRecurrenceEnd(RecurrenceEnd.Never);
//		item.setRecurrence(recurrence);
//
//		
//		calendar.getSchedule().getItems().add(item);
//		calendar.getSchedule().getAllItems(e.getDate(), e.getDate()).get(0).getHeaderText();
//	}
//
//	
//	
//	protected void addDay(DateTime date, Date date1) throws ParseException {
//
//		List<String> dayPlans = plan.get(Util.getDate(date1));
//		if(dayPlans==null){
//			return;
//		}
//		for (String dayPlan : dayPlans) {
//			Appointment item = new Appointment();
//			item.setStartTime(date);
//			item.setEndTime(date);
//			item.setHeaderText(dayPlan);
//			item.getStyle().setBrush(brushes[date.getDayOfWeek()]);
//			// Create the recurrence pattern.
//			recurrence = new Recurrence();
//			recurrence.setPattern(RecurrencePattern.Weekly);
//			recurrence.setStartDate(date);
//			recurrence.setRecurrenceEnd(RecurrenceEnd.Never);
//			calendar.getSchedule().getItems().add(item);
//		}
//	}
//
//	protected void addMonth() throws ParseException {
//		java.util.Calendar c = java.util.Calendar.getInstance();
//		c.set(java.util.Calendar.DAY_OF_MONTH, 1);
//		int maxDay = c.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
//		
//		DateTime date = calendar.getFirstDate();
//		for (int co = 0; co < maxDay; co++) {
//			date.addDays(co);
//			c.add(java.util.Calendar.DATE, 1);
//			addDay(date, c.getTime());
//		}
//	}
//
//	/*
//	 * gets the day of the week as a value of the DaysOfWeek enumeration. The
//	 * arguments identifies the day of the week as an integer.
//	 */
//	private int getDayOfWeek(int i) {
//
//		switch (i) {
//		case 1:
//			return DaysOfWeek.Monday;
//		case 2:
//			return DaysOfWeek.Tuesday;
//		case 3:
//			return DaysOfWeek.Wednesday;
//		case 4:
//			return DaysOfWeek.Thursday;
//		case 5:
//			return DaysOfWeek.Friday;
//		case 6:
//			return DaysOfWeek.Saturday;
//		}
//
//		return DaysOfWeek.Sunday;
//
//	}
//
//	/* custom drawing is performed here/ */
//	private void onDraw(DrawEvent e) {
//		if (recurrence == null)
//			return;
//		if (e.getElement() == CustomDrawElements.CalendarItem) {
//			if (e.getDate().getDay() == 6) {
//				java.awt.Image img = null;
//
//				try {
//					// Read the image file from an input stream
//					InputStream is = new BufferedInputStream(new FileInputStream("../cake.png"));
//					img = ImageIO.read(is);
//
//				} catch (IOException ioe) {
//				}
//
//				// gets the bounds of the drawing area
//				Rectangle r = e.getBounds();
//				AwtImage awtImage = new AwtImage(img);
//				// draw the image
//				e.getGraphics().drawImage(awtImage, e.getBounds().getLeft(), e.getBounds().getTop(), 32, 32);
//
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				MainWindow window = null;
//				try {
//					window = new MainWindow();
//					window.setVisible(true);
//				} catch (Exception exp) {
//					exp.printStackTrace();
//				}
//			}
//		});
//	}
//	
//	
//
//	Brush[] brushes = { Brushes.AliceBlue, Brushes.Beige, Brushes.LightBlue, Brushes.LightGreen, Brushes.LightGray,
//			Brushes.LightPink, Brushes.LemonChiffon };
//
//	String[] events = { "swimming", "yoga", "piano", "shopping", "cooking", "french", "party night" };

}
