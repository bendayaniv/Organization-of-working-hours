package NadavOren_YanivBenDavid.Model;

@SuppressWarnings("serial")
public class SellingEmployee extends BaseEmployee {

	double salesPercent;

	public SellingEmployee() {
		this("", "", null, null, 0.0);
	}

	public SellingEmployee(String n, Preference p, double sp) {
		this(n, "", p, null, sp);
	}

	public SellingEmployee(String n, String id, Preference p) {
		this(n, id, p, null, 0.0);
	}

	public SellingEmployee(String n, String id, Preference p, Role r, double sp) {
		super(n, id, p, r);
		salesPercent = sp;
	}

	public String toString() {
		StringBuffer str = new StringBuffer(super.toString() + "\nSales Percent: " + salesPercent);
		return str.toString();
	}

}
