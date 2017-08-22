/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring.monitoring;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import monitoring.pojo.BasicObject;
import monitoring.pojo.ReportObject;

/**
 *
 * @author purushah
 */
public class Util {

	public static List<String> getFileList(String name) throws IOException {
		List<String> result = Files.readAllLines(new File(getPath() + "/" + name + ".txt").toPath(),
				Charset.defaultCharset());
		Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
		return result;
	}
	
	
	public static void updateFileList(String name, List<String> lines) throws IOException {
		List<String> result = Files.readAllLines(new File(getPath() + "/" + name + ".txt").toPath(),
				Charset.defaultCharset());
		result.addAll(lines);
		Files.write(new File(getPath() + "/" + name + ".txt").toPath(), result, Charset.defaultCharset());
	}


	public static List<String> getOptionList(String option) throws IOException {
		List<String> results = Files.readAllLines(
				new File(getPath() + "/" + option + ".txt").toPath(), Charset.defaultCharset());
		List<String> newResults = new ArrayList<String>();
		for (String result : results) {
			newResults.add(result.split(ProgressConstant.seperator)[0]);
		}
		return newResults;
	}

	public static String getOptionType(String option, String type) throws IOException {
		List<String> results = Files.readAllLines(new File(getPath() + "/" + type + ".txt").toPath(),
				Charset.defaultCharset());
		for (String result : results) {
			if (result.split(ProgressConstant.seperator)[0].equals(option)) {
				return result.split(ProgressConstant.seperator)[1].trim();
			}

		}

		return null;

	}

	
	public static Map<String, String> getOptionMap(String option) throws IOException {
		List<String> results = Files.readAllLines(
				new File(getPath() + "/" + option + ".txt").toPath(), Charset.defaultCharset());
		Map<String, String> newResults = new HashMap<String, String>();
		for (String result : results) {
			newResults.put(result.split(ProgressConstant.seperator)[0], result.split(ProgressConstant.seperator)[1]);
		}
		return newResults;

	}
	
	public static Map<String, String> getOptionMapForValue(String name, String type) throws IOException {
		List<String> results = Files.readAllLines(
				new File(getPath() + "/" + getOptionType(name.trim(), type) + ".txt").toPath(), Charset.defaultCharset());
		Map<String, String> newResults = new HashMap<String, String>();
		for (String result : results) {
			newResults.put(result.split(ProgressConstant.seperator)[0], result.split(ProgressConstant.seperator)[1]);
		}
		return newResults;

	}


	public static Map<String, String> getColorMap() throws IOException {
		List<String> results = Files.readAllLines(
				new File(getPath() + "/" + ProgressReport.FOOD_COLOR_LIST + ".txt").toPath(), Charset.defaultCharset());
		Map<String, String> newResults = new HashMap<String, String>();
		String color = null;

		for (String result : results) {
			if (StringUtils.isEmpty(result)) {
				continue;
			}
			if (result.startsWith(ProgressConstant.section_start)) {
				color = StringUtils.substring(result, ProgressConstant.section_start.length());
			} else {
				newResults.put(result, color);
			}
		}
		return newResults;

	}

	public static Map<String, List<String>> getCalanderMap() {
		List<String> results = null;
		try {
			results = Files.readAllLines(
					new File(getPath() + "/" + ProgressConstant.CALENDAR + ".txt").toPath(), Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, List<String>> newResults = new HashMap<String, List<String>>();

		List<String> events = new ArrayList<String>();

		String date = null;

		for (String result : results) {
			if (StringUtils.isEmpty(result)) {
				continue;
			}
			if (result.startsWith(ProgressConstant.section_start)) {
				if(date!=null){
					newResults.put(date, events);
				}
				date = StringUtils.substring(result, ProgressConstant.section_start.length());
			} else {
				events.add(result);
			}
		}
		return newResults;

	}

	public static void writeFileList(String name, List<String> contents) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(Util.getPath() + "/" + name + ".txt"));
		for (String content : contents) {
			writer.println(content);

		}
		writer.close();

	}

	public static String getPath() {
		return "/Users/purushah/Documents/mydoc/doc/neo report/progress/items";
	}
	
	public static String getPathDir(String date) {
		return getPath() + "/" + date;
	}

	public static void writeObject(PrintWriter bw, List<String> objects) {
		for (String o : objects) {
			writeObject(bw, o);
		}

	}

	public static void writeObject(PrintWriter bw, String object) {
		bw.println(object);

	}

	public static String getDayDate(int i) {
		return new SimpleDateFormat("MM-dd-yyyy")
				.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 * i));
	}

	public static Date getDate(String date) throws ParseException {
		return new SimpleDateFormat("MM-dd-yyyy").parse(date);
	}
	
	public static String getDate(Date date) throws ParseException {
		return new SimpleDateFormat("MM-dd-yyyy").format(date);
	}


	public static String getLatestReportDateAVailabe() throws IOException {
		File f = new File(getPath());
		File[] files = getFiles(f);
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File o1, File o2) {
				try {
					return getDate(getFileNameWithoutExtension(o1)).compareTo(getDate(getFileNameWithoutExtension(o2)));
				} catch (ParseException e) {
					return 0;

				}
			}
		});
		return getFileNameWithoutExtension(files[0]);
	}

	public static File[] getLatestReportDateAVailabe(int count) throws IOException {
		File f = new File(getPath());
		File[] files = getFiles(f);
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File o1, File o2) {
				try {
					return getDate(getFileNameWithoutExtension(o2)).compareTo(getDate(getFileNameWithoutExtension(o1)));
				} catch (ParseException e) {
					return 0;

				}
			}
		});
		if (count > 0) {
			return Arrays.copyOfRange(files, 0, count);
		}
		return files;
	}
	
	private static File[] getFiles(File f){
		return f.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				try {
					getDate(getFileNameWithoutExtension(pathname));
				} catch (ParseException e) {
					return false;
				}
				return pathname.isFile() && true ;
			}
		});
	}

	public static String getFileNameWithoutExtension(File file) {
		String fileName = file.getName();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0) {
			fileName = fileName.substring(0, pos);
		}
		return fileName;
	}

	public static Map<String, BasicObject> getReport(String date) {

		BufferedReader reader = null;
		Map<String, BasicObject> objectMap = new HashMap<String, BasicObject>();

		try {
			reader = new BufferedReader(new FileReader(Util.getPath() + "/" + date + ".txt"));

			String sCurrentLine;
			BasicObject currentObject = null;
			while ((sCurrentLine = reader.readLine()) != null) {
				if (sCurrentLine.startsWith(ProgressConstant.section_start)) {
					if (objectMap.containsKey(sCurrentLine)) {
						currentObject = objectMap.get(sCurrentLine);
					} else {
						String name = StringUtils.substring(sCurrentLine, ProgressConstant.section_start.length());
						currentObject = ObjectFactory.getObject(name);
						objectMap.put(name, currentObject);
					}
				} else {
					if (!StringUtils.isEmpty(sCurrentLine)) {
						currentObject.readObject(sCurrentLine);
					}
				}

			}
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return objectMap;
	}

	public static Map<String, ReportObject> getReport(int date) throws IOException {

		Map<String, ReportObject> reportMap = new HashMap<String, ReportObject>();

		File[] files = getLatestReportDateAVailabe(date);
		BufferedReader reader = null;

		for (File file : files) {
			ReportObject report = new ReportObject();

			try {
				reader = new BufferedReader(new FileReader(file));

				String sCurrentLine;
				BasicObject currentObject = null;
				while ((sCurrentLine = reader.readLine()) != null) {
					if (sCurrentLine.startsWith(ProgressConstant.section_start)) {
						if (report.containsKey(sCurrentLine)) {
							currentObject = report.getObject(sCurrentLine);
						} else {
							String name = StringUtils.substring(sCurrentLine, ProgressConstant.section_start.length());
							currentObject = ObjectFactory.getObject(name);
							report.putObject(name, currentObject);
						}
					} else {
						if (!StringUtils.isEmpty(sCurrentLine)) {
							if (currentObject != null) {
								currentObject.readObject(sCurrentLine);
							}
						}
					}

				}
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			reportMap.put(getFileNameWithoutExtension(file), report);
		}
		return reportMap;
	}

	public static Map<String, ReportObject> getReport(String date, int offset) throws IOException, ParseException {

		Map<String, ReportObject> reportMap = new HashMap<String, ReportObject>();
		final Date endDate = getDate(date);
		final Date startDate = adjustOffset(getDate(date), offset);

		File f = new File(getPath());
		File[] files = f.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				try {
					Date date = getDate(getFileNameWithoutExtension(pathname));
					return pathname.isFile() && date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
				} catch (ParseException e) {
					return false;
				}
			}
		});

		Arrays.sort(files, new Comparator<File>() {
			public int compare(File o1, File o2) {
				try {
					return getDate(getFileNameWithoutExtension(o2)).compareTo(getDate(getFileNameWithoutExtension(o1)));
				} catch (ParseException e) {
					return 0;

				}
			}
		});

		BufferedReader reader = null;

		for (File file : files) {
			ReportObject report = new ReportObject();

			try {
				reader = new BufferedReader(new FileReader(file));

				String sCurrentLine;
				BasicObject currentObject = null;
				while ((sCurrentLine = reader.readLine()) != null) {
					if (sCurrentLine.startsWith(ProgressConstant.section_start)) {
						if (report.containsKey(sCurrentLine)) {
							currentObject = report.getObject(sCurrentLine);
						} else {
							String name = StringUtils.substring(sCurrentLine, ProgressConstant.section_start.length());
							currentObject = ObjectFactory.getObject(name);
							report.putObject(name, currentObject);
						}
					} else {
						if (!StringUtils.isEmpty(sCurrentLine)) {
							if (currentObject != null) {
								currentObject.readObject(sCurrentLine);
							}
						}
					}

				}
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			reportMap.put(getFileNameWithoutExtension(file), report);
		}
		return reportMap;
	}

	public static Set<String> getCurrentReport(String object) throws IOException {
		Set<String> current = new HashSet<String>();
		Map<String, ReportObject> reportMap = getReport(1);
		for (ReportObject report : reportMap.values()) {
			if (report.getObject(object) != null) {
				current.addAll(report.getObject(object).getAllItemName());
			}
		}

		return current;
	}

	public static ImageIcon getInfoIcon(JLabel label) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/Users/purushah/temp/icon.png"));
			return new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Date adjustOffset(Date date, int offset) throws IOException {
		return DateUtils.addDays(date, offset);
	}
}
