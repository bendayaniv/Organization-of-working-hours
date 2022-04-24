package NadavOren_YanivBenDavid.Model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Department implements Serializable, Synchronizable, ProfitMultiplable, Nameable, ChangablePractice {

	private String name;
	private ArrayList<Role> roles;
	private int startWork;
	private boolean canFromHome;
	private boolean changablePractice;
	private boolean synced;

	public Department() {
		this("", null, 8, false, false, false);
	}

	public Department(String n, int sw, boolean cfm, boolean cp, boolean s) {
		this(n, null, sw, cfm, cp, s);
	}

	public Department(String n, ArrayList<Role> r, int sw, boolean cfm, boolean cp, boolean s) {
		name = n;
		synced = s;
		setRoles(r);
		setStartWork(sw);
		setPracticeCanBeChanged(cp);
		setCanFromHome(cfm);

	}

	public void setStartWork(int sw) {
		if (synced)
			startWork = sw;
		else
			startWork = -1; // indicates that every role has his own start work hour

	}

	@Override
	public void sync(int wh, boolean fm) {
		int size = roles.size();
		for (int i = 0; i < size; i++) {
			roles.get(i).sync(wh, fm);
		}
	}

	public void setRoles(ArrayList<Role> r) {
		if (r == null)
			roles = new ArrayList<Role>();
		else
			roles = r;
	}

	public void setCanFromHome(boolean t) {
		canFromHome = t;
	}

	public ArrayList<Role> getAllRoles() {
		return roles;
	}

	public boolean getSynced() {
		return synced;
	}

	@Override
	public double getMultiProfit() {

		double sumOfMultiPro = 0;
		int size = roles.size();
		for (int i = 0; i < size; i++)
			if (roles.get(i).getEmployee() != null) {
				sumOfMultiPro += roles.get(i).getEmployee().getMultiProfit();
			}
		return sumOfMultiPro;
	}

	public void addRole(Role r) {
		if (r != null) {
			roles.add(r);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getStartHour() {
		return startWork;
	}

	@Override
	public void setPracticeCanBeChanged(boolean cp) {
		changablePractice = cp;
	}

	@Override
	public boolean getPracticeCanBeChanged() {
		return changablePractice;
	}

	@Override
	public boolean getFromHome() {
		return canFromHome;
	}

	public String toString() {
		StringBuffer str = new StringBuffer("Department's name: " + name);
		str.append("\nSynced work: " + (synced ? "Yes" : "No") + "\n");
		if (startWork != -1) {
			if (startWork + 9 > 23) {
				str.append("Work Hours: " + (startWork + 9 - 24) + ":00 - " + startWork + ":00");
			} else {
				str.append("Work Hours: " + (startWork + 9) + ":00 - " + startWork + ":00");
			}
		} else {
			str.append("Every role has his own start work hour");
		}
		str.append("\nRoles: \n");
		int size = roles.size();
		for (int i = 0; i < size; i++) {
			str.append((i + 1) + ". " + roles.get(i).getName());
			if (roles.get(i).getEmployee() != null) {
				str.append("\nEmployee: " + roles.get(i).getEmployee().getName() + "\n");
			} else {
				str.append("\nNo employee yet\n");
			}
		}
		str.append("\nMulti Profit: " + getMultiProfit());
		str.append("\nCan work from home: " + (canFromHome ? "Yes" : "No"));
		str.append("\nCan change practice: " + (changablePractice ? "Yes" : "No"));
		return str.toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Department)) {
			return false;
		}
		Department d = (Department) other;

		if (roles.size() != 0 && d.getAllRoles().size() != 0) { // ADDED - 29.7
			for (int i = 0; i < roles.size(); i++) {
				if (!(roles.get(i).equals(d.getAllRoles().get(i)))) {
					return false;
				}
			}
		}

		return d.getName() == this.name && d.getStartHour() == startWork && d.getFromHome() == canFromHome
				&& d.getPracticeCanBeChanged() == changablePractice && d.getSynced() == synced;
	}

}
