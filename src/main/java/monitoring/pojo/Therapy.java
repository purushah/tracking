package monitoring.pojo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import monitoring.monitoring.ProgressConstant;
import monitoring.monitoring.ProgressReport;

public class Therapy extends Progress {


	@Override
	public String getName() {
		return ProgressReport.THERAPY;
	}

}
