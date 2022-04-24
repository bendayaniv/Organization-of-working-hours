package NadavOren_YanivBenDavid.Model;

@SuppressWarnings("serial")
public class HourlyEmployee extends Employee {

	int sumOfHours;

	public HourlyEmployee() {
		this("", "", null, null, 160);
	}

	public HourlyEmployee(String n, Preference p, int sh) {
		this(n, "", p, null, sh);
	}

	public HourlyEmployee(String n, Preference p) {
		this(n, "", p, null, 160);
	}

	public HourlyEmployee(String n, String id, Preference p, Role r, int sh) {
		super(n, id, p, r);
		setSumOfHours(sh);
	}

	public void setSumOfHours(int sh) {
		sumOfHours = sh;
	}

	public String toString() {
		StringBuffer str = new StringBuffer(super.toString() + "Hourly Worker\n" + "Sum of hours: " + sumOfHours);
		return str.toString();
	}
}
