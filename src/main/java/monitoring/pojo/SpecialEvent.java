package monitoring.pojo;


import java.util.List;

import monitoring.monitoring.ProgressReport;

public class SpecialEvent extends SingleListObject {
	@Override
	public String getName() {
		return ProgressReport.SPECAIL_EVENT;
	}
	
	public List<String> getSpecialEvents(){
		return objects;
	}

}
