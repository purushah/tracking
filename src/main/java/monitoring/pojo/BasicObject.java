package monitoring.pojo;

import java.io.PrintWriter;
import java.util.Set;

import monitoring.monitoring.ProgressConstant;

public abstract class BasicObject {

	public abstract String getName();

	public final void write(PrintWriter bw) throws Exception {
		bw.println(ProgressConstant.section_start + getName());
		writeObject(bw);
		bw.println();
	}

	public abstract void add(Object t);
	
	
	public abstract void writeObject(PrintWriter bw) throws Exception;
	
	public abstract void readObject(String bw);
	
	public abstract boolean contains(String object);
	
	public abstract Set<String> getAllItemName();



}
