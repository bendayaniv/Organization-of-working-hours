package NadavOren_YanivBenDavid.Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Preference implements Serializable {

	public enum ePracticeType {
		Default, Early, Late, Home
	}

	private ePracticeType type;
	private int startWork;
	private boolean fromHome;

	public Preference() {
		this(8, false);
	}

	public Preference(int sw) {
		this(sw, false);
	}

	public Preference(boolean fh) {
		this(8, fh);
	}

	public Preference(int sw, boolean fh) {
		setFromHome(fh);
		setStartWork(sw);
	}

	public void setStartWork(int sw) {
		startWork = sw;
		if (fromHome == true)
			return;
		else {
			if (sw > 8)
				setType(ePracticeType.Late);
			else if (sw < 8)
				setType(ePracticeType.Early);
			else
				setType(ePracticeType.Default);
		}
	}

	public void setFromHome(boolean fh) {
		fromHome = fh;
		if (fh == true)
			setType(ePracticeType.Home);
	}

	public void setType(ePracticeType t) {
		type = t;
	}

	public ePracticeType getType() {
		return type;
	}

	public int getstartWork() {
		return startWork;
	}

	public boolean getFromHome() {
		return fromHome;
	}

	public String toString() {
		StringBuffer str = new StringBuffer("Preference type: " + (fromHome ? "From home" : type) + "\n");
		if (fromHome == false) {
			if (startWork + 9 > 23) { // ADDED - 29.7
				str.append("Prefered Work Hours: " + (startWork + 9 - 24) + ":00 - " + startWork + ":00\n");
			} else {
				str.append("Prefered Work Hours: " + (startWork + 9) + ":00 - " + startWork + ":00\n");
			}
		}
		return str.toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Preference)) {
			return false;
		}
		Preference p = (Preference) other;

		return p.getType().equals(type) && p.getstartWork() == startWork && p.getFromHome() == fromHome;
	}

}
