package NadavOren_YanivBenDavid.Model;

@SuppressWarnings("serial")
public class BaseEmployee extends Employee {

	protected final int SUM_OF_HOURS = 160;

	public BaseEmployee() {
		this("", "", null, null);
	}

	public BaseEmployee(String n, Preference p) {
		this(n, "", p, null);
	}

	public BaseEmployee(String n, String id, Preference p, Role r) {
		super(n, id, p, r);
	}

	public String toString() {
		StringBuffer str = new StringBuffer(super.toString());
		if (this instanceof SellingEmployee)
			str.append("Base selling employee");
		else
			str.append("Base employee");
		str.append("\nSum of hours: 160");
		return str.toString();
	}
}
