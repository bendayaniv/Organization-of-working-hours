package NadavOren_YanivBenDavid.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Role implements Serializable, Synchronizable, Nameable, ChangablePractice {

	public enum eSaleryType {
		baseSalery("Base"), hourlySalery("Hourly"), baseBonusSalery("Base With Bonus Sales");

		private final String name;

		private eSaleryType(String n) {
			name = n;
		}

		public String toString() {
			return name;
		}
	}

	private String name;
	private Employee employee;
	private int startWork;
	private boolean canFromHome;
	private double salery;
	private Department department;
	private boolean changePractice;
	private eSaleryType saleryType;

	public Role() {
		this("", null, 8, false, false, 35.0, null, null);
	}

	public Role(String n, int workHours, boolean cfh, boolean cp, double s, Department d, eSaleryType salerytype) {
		this(n, null, workHours, cfh, cp, s, d, salerytype);
	}

	public Role(String n, Employee w, int workHours, boolean cfh, boolean cp, double s, Department d,
			eSaleryType type) {
		name = n;
		setDepartment(d);
		setWorker(w);
		setWorkHours(workHours);
		setCanFromHome(cfh);
		setPracticeCanBeChanged(cp);
		setSalery(s);
		saleryType = type;
	}

	public void setWorker(Employee w) {
		if (w != null) {
			employee = w;
		}
	}

	public void setWorkHours(int wh) {
		if (department.getStartHour() == -1) {
			startWork = wh;
		} else {
			startWork = department.getStartHour();
		}
	}

	public void setSalery(double s) {
		salery = s;
	}

	public void setDepartment(Department d) {
		if (d != null) {
			department = d;
		}
	}

	public void setPracticeCanBeChanged(boolean cp) {
		if (department.getPracticeCanBeChanged() == false) {
			changePractice = false;
		} else {
			if (department.getSynced() == true) {
				changePractice = false;
			} else {
				changePractice = cp;
			}
		}
	}

	public void setCanFromHome(boolean cfm) { // CHANGED - 1.8
		if (department.getFromHome() == false) {
			canFromHome = false;
		} else {
			if (department.getSynced() == true) {
				canFromHome = department.getFromHome();
			} else {
				canFromHome = cfm;
			}
		}
	}

	@Override
	public void sync(int wh, boolean fm) {
		startWork = wh;
		canFromHome = fm;
	}

	@Override
	public boolean getFromHome() {
		return canFromHome;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getStartHour() {
		return startWork;
	}

	public double getSalery() {
		return salery;
	}

	public eSaleryType getSaleryType() {
		return saleryType;
	}

	public Department getDepartment() {
		return department;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public boolean getPracticeCanBeChanged() {
		return changePractice;
	}

	public String toString() {
		return name;
	}

	public String info() {
		StringBuffer str = new StringBuffer("Role's name: " + name);
		str.append("\nDepartment: " + department.getName());
		if (startWork + 9 > 23) {
			str.append("\nWork Hours: " + (startWork + 9 - 24) + ":00 - " + startWork + ":00");
		} else {
			str.append("\nWork Hours: " + (startWork + 9) + ":00 - " + startWork + ":00");
		}
		str.append("\nSalery: " + salery);
		str.append("\nSalery type: " + saleryType);
		str.append("\nCan change practice: " + (changePractice ? "Yes" : "No"));
		str.append("\nCan from home: " + (canFromHome ? "Yes" : "No"));
		return str.toString();
	}

	public boolean equals(Object other) { // CHANGED - 1.8
		if (!(other instanceof Role)) {
			return false;
		}
		Role r = (Role) other;
		return r.getName() == name && r.getStartHour() == startWork && r.getFromHome() == canFromHome
				&& r.getSalery() == salery && r.getDepartment() == department
				&& r.getPracticeCanBeChanged() == changePractice && r.getSaleryType() == saleryType;
	}
}
