package monitoring.monitoring;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import monitoring.graph.DrawGraphProgress;
import monitoring.graph.DrawGraphSupplements;
import monitoring.graph.Graph;
import monitoring.graph.GraphDethoxBath;
import monitoring.graph.GraphHomeopathy;
import monitoring.graph.GraphTherapy;
import monitoring.graph.GraphVegitable;
import monitoring.pojo.BasicObject;
import monitoring.pojo.Behaviour;
import monitoring.pojo.DetoxBath;
import monitoring.pojo.EssentialOil;
import monitoring.pojo.Fruits;
import monitoring.pojo.IssuesObject;
import monitoring.pojo.Juicing;
import monitoring.pojo.Note;
import monitoring.pojo.Progress;
import monitoring.pojo.SpecialEvent;
import monitoring.pojo.Supplements;
import monitoring.pojo.Therapy;
import monitoring.pojo.Vegitables;

public class ObjectFactory {

	public static BasicObject getObject(String name) {
		if (name.equals(ProgressReport.VEGI)) {
			return new Vegitables();
		}
		if (name.equals(ProgressReport.JUICING)) {
			return new Juicing();
		}
		if (name.equals(ProgressReport.CHIROPRACTIC)) {
			return new Chiropractic();
		}

		if (name.equals(ProgressReport.DETOX_BATH)) {
			return new DetoxBath();
		}

		if (name.equals(ProgressReport.ESSENTIAL_OIL)) {
			return new EssentialOil();
		}

		// if (name.equals(ProgressReport.FERMENTATION)) {
		// return new FermentedFood();
		// }

		if (name.equals(ProgressReport.FRUITS)) {
			return new Fruits();
		}

		if (name.equals(ProgressReport.HOMOEPATHY)) {
			return new Homeopathy();
		}

		if (name.equals(ProgressReport.PROGRESS)) {
			return new Progress();
		}

		if (name.equals(ProgressReport.SPECAIL_EVENT)) {
			return new SpecialEvent();
		}

		if (name.equals(ProgressReport.SUPPLEMENTS)) {
			return new Supplements();
		}

		if (name.equals(ProgressReport.THERAPY)) {
			return new Therapy();
		}

		if (name.equals(ProgressReport.NOTE)) {
			return new Note();
		}
		if (name.equals(ProgressReport.BEHAVIOUR)) {
			return new Behaviour();
		}
		if (name.equals(ProgressReport.ISSUES)) {
			return new IssuesObject();
		}


		return null;
	}

	public static Graph getObjectForGraph(String name) throws IOException {

		if (name.equals(ProgressReport.PROGRESS)) {
			return new DrawGraphProgress(name);
		}

		if (name.equals(ProgressReport.SUPPLEMENTS)) {
			return new DrawGraphSupplements(name);
		}
		if (name.equals(ProgressReport.VEGI)) {
			return new GraphVegitable(name);
		}

		if (name.equals(ProgressReport.THERAPY)) {
			return new GraphTherapy(name);
		}

		if (name.equals(ProgressReport.DETOX_BATH)) {
			return new GraphDethoxBath(name);
		}

		if (name.equals(ProgressReport.HOMOEPATHY)) {
			return new GraphHomeopathy(name);
		}

		return null;
	}

	public static Graph getObjectForGraph(String name, List<String> graphList, String date, String offset) {
		if (name.equals(ProgressReport.PROGRESS)) {
			return new DrawGraphProgress(name, graphList, date, offset);
		}

		if (name.equals(ProgressReport.SUPPLEMENTS)) {
			return new DrawGraphSupplements(name, graphList, date, offset);
		}
		if (name.equals(ProgressReport.VEGI)) {
			return new GraphVegitable(name, graphList, date, offset);
		}

		if (name.equals(ProgressReport.THERAPY)) {
			return new GraphTherapy(name, graphList, date, offset);
		}

		if (name.equals(ProgressReport.DETOX_BATH)) {
			return new GraphDethoxBath(name, graphList, date, offset);
		}
		if (name.equals(ProgressReport.HOMOEPATHY)) {
			return new GraphHomeopathy(name, graphList, date, offset);
		}
		return null;
	}

	public static Color getColor(String name) {
		if(name==null){
			return null;
		}
		if (name.equalsIgnoreCase("Red")) {
			return Color.RED;
		}

		if (name.equals("Orange")) {
			return Color.ORANGE;
		}
		if (name.equals("Green")) {
			return Color.GREEN;
		}
		if (name.equals("Blue")) {
			return Color.blue;
		}
		if (name.equals("White")) {
			return Color.WHITE;
		}

		return null;
	}
}
