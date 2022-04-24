package NadavOren_YanivBenDavid.Model;

public interface Synchronizable {

	void sync(int wh, boolean fm);
	
	int getStartHour();
	boolean getFromHome();
}
