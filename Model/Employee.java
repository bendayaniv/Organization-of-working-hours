package NadavOren_YanivBenDavid.Model;

import java.io.Serializable;
import NadavOren_YanivBenDavid.Model.Preference.ePracticeType;

@SuppressWarnings("serial")
public abstract class Employee implements Serializable, ProfitMultiplable, Nameable {

	protected String name;
	protected String id;
	protected Preference preference;
	protected Role role;
	protected Department department;

	public Employee() {
		this("", "", null, null);
	}

	public Employee(String n, Preference p) {
		this(n, "", p, null);
	}

	public Employee(String n, String id, Preference p, Role r) {
		name = n;
		this.id = id;
		setPreference(p);
		setRole(r);
		setDepartment();
	}

	public void setPreference(Preference p) {
		preference = p;
	}

	public void setDepartment() {
		if (role != null) {
			department = role.getDepartment();
		}
	}

	public void setRole(Role r) {
		role = r;
		if (role != null) {
			role.setWorker(this);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getMultiProfit() {
		if (preference.getType() == ePracticeType.Default && role.getStartHour() == 8)
			return 0.0;
		else {
			double multiProfit = 0.0;
			if (preference.getType() == ePracticeType.Home && role.getFromHome())
				multiProfit = 1.1 * 8;
			else if (preference.getType() == ePracticeType.Home && !role.getFromHome())
				multiProfit = -1.1 * 8;
			else {
				int amountOfHours = getAmountOfHours();
				multiProfit = 0.2 * amountOfHours;
			}
			return multiProfit;
		}
	}

	public int getAmountOfHours() {
		int amountOfHours = 0;
		int preHour = preference.getstartWork();
		if (preHour == role.getStartHour())
			amountOfHours = Math.abs(preHour - 8);
		else
			amountOfHours = -1 * Math.abs(preHour - role.getStartHour());
		return amountOfHours;
	}

	public String toString() {
		StringBuffer str = new StringBuffer("Employee name: " + name + "\n");
		str.append("Id: " + id + "\n");
		str.append("Department: " + department.getName() + "\n");
		str.append("Role: " + role.getName() + "\n");
		int startWork = role.getStartHour();
		if (startWork + 9 > 23) {
			str.append("\nWork Hours: " + (startWork + 9 - 24) + ":00 - " + startWork + ":00");
		} else {
			str.append("\nWork Hours: " + (startWork + 9) + ":00 - " + startWork + ":00");
		}
		str.append("\nWorks from home: " + (role.getFromHome() ? "Yes" : "No") + "\n");
		str.append("Preference:\n"+preference.toString() + "\n");
		str.append("Profit multiplier: " + getMultiProfit() + "\n");
		str.append("Type of employee: ");

		return str.toString();
	}

	public String getId() {
		return id;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Employee)) {
			return false;
		}
		Employee e = (Employee) other;

		return e.getName() == name && e.getId().equals(id);
	}

}
