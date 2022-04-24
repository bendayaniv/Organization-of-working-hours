package NadavOren_YanivBenDavid.Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Company implements Serializable, ProfitMultiplable {

	private String name;
	private ArrayList<Department> departments;
	private ArrayList<Employee> employees;
	private double dailyProfit;

	public Company() {
		this("", 0, null, null);
	}

	public Company(String n, ArrayList<Department> d, ArrayList<Employee> e) {
		this(n, 0, d, e);
	}

	public Company(String n, double dayp, ArrayList<Department> d, ArrayList<Employee> e) {
		name = n;
		setDailyProfit(dayp);
		setAllDepartments(d);
		setAllEmployees(e);
	}

	public void setDailyProfit(double dayp) {
		dailyProfit = dayp;
	}

	public ArrayList<Employee> getAllEmployees() {
		return employees;
	}

	public void setAllEmployees(ArrayList<Employee> e) {
		if(e != null)
			employees = e;
		else 
			employees = new ArrayList<Employee>();
	}

	public ArrayList<Department> getAllDepartments() {
		return departments;
	}

	public void setAllDepartments(ArrayList<Department> d) {
		if(d != null)
			departments = d;
		else
			departments = new ArrayList<Department>();
	}

	public Department getSelectedDepartment(Department dep) {
		for (int i = 0; i < departments.size(); i++) {
			if (departments.get(i).equals(dep)) {
				dep = departments.get(i);
				break;
			}
		}
		return dep;
	}

	public double getDailyProfit() {
		return dailyProfit;
	}

	@Override
	public double getMultiProfit() {
		double multiProfit = 0;
		int size = departments.size();
		for (int i = 0; i < size; i++)
			multiProfit += departments.get(i).getMultiProfit();
		return multiProfit;
	}

	public String toString() {
		double multiProfit = getMultiProfit();
		StringBuffer str = new StringBuffer("Company's name: " + name);
		str.append("\nOriginal daily profit per employee for 1 hour: " + dailyProfit);
		str.append("\nMulti Profit: " + multiProfit);
		str.append("\nCalculated daily profit: " + (dailyProfit * multiProfit));
		return str.toString();
	}

	public void addEmployee(Employee e) {
		employees.add(e);
	}

	public void addDepartment(Department d) {
		departments.add(d);
	}
}
