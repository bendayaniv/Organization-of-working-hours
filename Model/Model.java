package NadavOren_YanivBenDavid.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import NadavOren_YanivBenDavid.Model.Role.eSaleryType;

@SuppressWarnings("serial")
public class Model implements Serializable {

	private Company theBoringCompany;
	private ArrayList<Department> departments;
	private ArrayList<Role> roles, rolesFromDepartment;
	private ArrayList<Employee> employees;
	private String departmentName, employeeName;
	private Employee chosenEmployee;
	private Department chosenDepartment;

	public Model() throws FileNotFoundException, ClassNotFoundException, IOException {
		departments = hardCodedDepartments();
		roles = hardCodedRoles(departments);
		employees = hardCodedEmployees(roles);
		theBoringCompany = new Company();
		rolesFromDepartment = new ArrayList<Role>();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// HARD-CODED

	private ArrayList<Department> hardCodedDepartments() {
		ArrayList<Department> departments = new ArrayList<>();
		departments.add(new Department("HR", null, -1, false, true, false));
		departments.add(new Department("IT", null, 9, false, true, true));
		departments.add(new Department("R&D", null, -1, true, true, false));
		departments.add(new Department("baba", null, -1, false, true, false));
		departments.add(new Department("nyny", null, 10, true, false, true));
		return departments;
	}

	private ArrayList<Role> hardCodedRoles(ArrayList<Department> department) {
		ArrayList<Role> roles = new ArrayList<>();
		roles.add(new Role("a", null, 10, false, true, 35, department.get(0), eSaleryType.baseSalery));
		roles.add(new Role("a", null, 10, false, true, 35, department.get(0), eSaleryType.baseSalery));
		roles.add(new Role("c", null, 8, false, true, 35, department.get(0), eSaleryType.baseSalery));
		roles.add(new Role("d", null, 8, false, true, 35, department.get(1), eSaleryType.baseSalery));
		roles.add(new Role("e", null, 8, false, true, 35, department.get(1), eSaleryType.hourlySalery));
		roles.add(new Role("f", null, 8, false, true, 35, department.get(1), eSaleryType.hourlySalery));
		roles.add(new Role("g", null, 8, false, true, 35, department.get(2), eSaleryType.baseSalery));
		roles.add(new Role("h", null, 8, false, true, 35, department.get(2), eSaleryType.baseSalery));
		roles.add(new Role("i", null, 8, false, true, 35, department.get(2), eSaleryType.baseBonusSalery));
		roles.add(new Role("j", null, 8, false, true, 35, department.get(3), eSaleryType.baseSalery));
		roles.add(new Role("k", null, 8, false, true, 35, department.get(3), eSaleryType.baseBonusSalery));
		roles.add(new Role("l", null, 8, false, true, 35, department.get(4), eSaleryType.hourlySalery));
		roles.add(new Role("l", null, 8, false, true, 35, department.get(4), eSaleryType.hourlySalery));
		roles.add(new Role("m", null, 6, false, false, 35, department.get(4), eSaleryType.baseBonusSalery));
		roles.add(new Role("n", null, 10, false, false, 35, department.get(4), eSaleryType.baseBonusSalery));
		departments.get(0).addRole(roles.get(0)); // CHANGED - 28-7
		departments.get(0).addRole(roles.get(1));
		departments.get(0).addRole(roles.get(2));
		departments.get(1).addRole(roles.get(3));
		departments.get(1).addRole(roles.get(4));
		departments.get(1).addRole(roles.get(5));
		departments.get(2).addRole(roles.get(6));
		departments.get(2).addRole(roles.get(7));
		departments.get(2).addRole(roles.get(8));
		departments.get(3).addRole(roles.get(9));
		departments.get(3).addRole(roles.get(10));
		departments.get(4).addRole(roles.get(11));
		departments.get(4).addRole(roles.get(12));
		departments.get(4).addRole(roles.get(13));
		departments.get(4).addRole(roles.get(14));

		return roles;
	}

	private ArrayList<Employee> hardCodedEmployees(ArrayList<Role> roles) {
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new BaseEmployee("Jennet Cooper", "303201455", new Preference(), roles.get(2)));
		employees.add(new BaseEmployee("Shelly Ben El", "398464511", new Preference(), roles.get(3)));
		employees.add(new BaseEmployee("Noa Rabinovich", "331445672", new Preference(), roles.get(4)));
		employees.add(new BaseEmployee("Bar David", "312478340", new Preference(), roles.get(5)));
		employees.add(new SellingEmployee("Omer Gershon", "316465533", new Preference(), roles.get(6), 0.5));
		employees.add(new SellingEmployee("Or Zamir", "308455326", new Preference(), roles.get(7), 0.4));
		employees.add(new SellingEmployee("Itamar Zvikler", "355775334", new Preference(), roles.get(8), 0.4));
		employees.add(new HourlyEmployee("Shalom Hanoh", "303648761", new Preference(), roles.get(9), 50));
		employees.add(new HourlyEmployee("Rin Shemer", "300763321", new Preference(), roles.get(10), 70));
		return employees;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("company.dat"));
		theBoringCompany = (Company) inFile.readObject();
		inFile.close();
	}

	public void setCompanyHardCodedDatas() {
//		theBoringCompany = new Company("Boring Company", 10, departments, employees); \\ for hard coded
		theBoringCompany = new Company("Boring Company", 10, null, null);
	}

	// Checking existence of file with old data
	public boolean checkingExistenseOfOldData() {
		File file = new File("company.dat");
		if (file.exists() && !file.isDirectory()) {
			return true;
		}
		return false;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<Department> getAllDepartments() {
		return theBoringCompany.getAllDepartments();
	}

	public ArrayList<Employee> getAllEmployees() {
		return theBoringCompany.getAllEmployees();
	}

	public ArrayList<Role> getRolesInDepartment(int index) {
		return theBoringCompany.getAllDepartments().get(index).getAllRoles();
	}

	public boolean CheckForRolesAvailablty() {
		int size = getAllDepartments().size();
		for (int i = 0; i < size; i++) {
			if (getAllDepartments().get(i).getAllRoles().size() > 0) {
				int sizeRoles = getAllDepartments().get(i).getAllRoles().size();
				ArrayList<Role> roles = getRolesInDepartment(i);
				for (int j = 0; j < sizeRoles; j++)
					if (roles.get(j).getEmployee() == null)
						return true;
			}
		}
		return false;
	}

	// NEW - 1.8
	public boolean CheckForRolesExistence() {
		if (getAllDepartments().size() == 0) {
			return false;
		} else {
			for (int i = 0; i < getAllDepartments().size(); i++) {
				if (getAllDepartments().get(i).getAllRoles().size() != 0) {
					return true;
				}
			}
		}
		return false;
	}

	// NEW - 1.8
	public boolean CheckForDepartmentsAvailablty() {
		if (getAllDepartments().size() == 0) {
			return false;
		} else
			return true;
	}

	// NEW - 1.8
	public boolean CheckForEmployeesAvailablty() {
		if (getAllEmployees().size() == 0) {
			return false;
		} else
			return true;
	}

	// NEW - 1.8
	public boolean checkForDepartmentToChange() {
		int size = getAllDepartments().size();
		for (int i = 0; i < size; i++) {
			if (getAllDepartments().get(i).getPracticeCanBeChanged() == true
					&& getAllDepartments().get(i).getSynced() == true) {
				return true;
			}
		}
		return false;
	}

	// NEW - 1.8
	public boolean checkForRoleToChange() {
		int departmentsSize = getAllDepartments().size();
		for (int i = 0; i < departmentsSize; i++) {
			if (getAllDepartments().get(i).getPracticeCanBeChanged() == true
					&& getAllDepartments().get(i).getSynced() == false) {
				int rolesSize = getAllDepartments().get(i).getAllRoles().size();
				ArrayList<Role> roles = getRolesInDepartment(i);
				for (int j = 0; j < rolesSize; j++) {
					if (roles.get(i).getPracticeCanBeChanged() == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void createArrayListForRolesInDepartment(Department d) {
		rolesFromDepartment.clear();
		int size = d.getAllRoles().size();
		for (int i = 0; i < size; i++)
			rolesFromDepartment.add(d.getAllRoles().get(i));
	}

	public void createArrayListForRolesInDepartmentToShow(Department d) {
		rolesFromDepartment.clear();
		int size = d.getAllRoles().size();
		for (int i = 0; i < size; i++)
			if (i != 0) {
				int count = 0;
				for (int j = 0; j < i; j++) {
//					if (!d.getAllRoles().get(j).checkOtherParameters(d.getAllRoles().get(i))) {
					if (!d.getAllRoles().get(j).equals(d.getAllRoles().get(i))) { // CHANGED - 1.8
						count += 1;
					} else {
						break;
					}
				}
				if (count == i) {
					rolesFromDepartment.add(d.getAllRoles().get(i));
				}
			} else {
				rolesFromDepartment.add(d.getAllRoles().get(i));
			}
	}

	public Role getChosenRoleFromDepartment(int index) {
		return rolesFromDepartment.get(index);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Adding department
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addDepartment(String name, int sw, boolean cfm, boolean cp, boolean s) {
		Department d = new Department(name, sw, cfm, cp, s);
		theBoringCompany.addDepartment(d);
	}

	public boolean checkForDuplicateName(String name, int type) {
		int size = theBoringCompany.getAllDepartments().size();
		if (type == 1) {
			for (int i = 0; i < size; i++)
				if (theBoringCompany.getAllDepartments().get(i).getName().equals(name))
					return true;
		}
		return false;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Adding role
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addRole(String name, int startwork, boolean cfh, boolean cp, double s, Department d,
			eSaleryType salerytype) {
		theBoringCompany.getSelectedDepartment(d).addRole(new Role(name, startwork, cfh, cp, s, d, salerytype));
	}

	public int getStartWorkDepartment(String d) {
		setChosenDepartmentName(d);
		setChosenDepartment();
		return chosenDepartment.getStartHour();
	}

	public boolean getCanFromHomeFromDepartment(String d) {
		setChosenDepartmentName(d);
		setChosenDepartment();
		return chosenDepartment.getFromHome();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Adding employee
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addEmployee(String name, String id, int startWork, boolean fromHome, Role r, int amountOfHours) { // CHANGED
																													// -
																													// 31.7
		if (amountOfHours == 0) {
			if (r.getSaleryType() == eSaleryType.baseSalery)
				theBoringCompany.addEmployee(new BaseEmployee(name, id, new Preference(startWork, fromHome), r));
			else
				theBoringCompany
						.addEmployee(new SellingEmployee(name, id, new Preference(startWork, fromHome), r, 0.1));
		} else
			theBoringCompany
					.addEmployee(new HourlyEmployee(name, id, new Preference(startWork, fromHome), r, amountOfHours));
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Printing department info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setChosenDepartment() {
		int size = theBoringCompany.getAllDepartments().size();
		for (int i = 0; i < size; i++) {
			if (theBoringCompany.getAllDepartments().get(i).getName().equals(departmentName)) {
				chosenDepartment = theBoringCompany.getAllDepartments().get(i);
				break;
			}
		}
	}

	public void setChosenDepartmentName(String ChosenDepartmentName) {
		departmentName = ChosenDepartmentName;
	}

	public String getChosenDepartmentInformation() {
		return chosenDepartment.toString();
	}

	public Department getChosenDepartment() {
		return chosenDepartment;
	}

	public boolean checkCanBeChangedDepartment(Department d) {
		return !d.getPracticeCanBeChanged();
	}

	public boolean checkSyncedDepartment(Department d) {
		return d.getSynced();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Printing role info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ArrayList<Role> roles() {
		return rolesFromDepartment;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Printing employee info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setChosenEmployee() {
		for (int i = 0; i < theBoringCompany.getAllEmployees().size(); i++) {
			if (theBoringCompany.getAllEmployees().get(i).getName().equals(employeeName)) {
				chosenEmployee = theBoringCompany.getAllEmployees().get(i);
			}
		}
	}

	public void setChosenEmployeeName(String chosenEmployeeName) {
		employeeName = chosenEmployeeName;
	}

	public String getChosenEmployeeInformation() {
		return chosenEmployee.toString();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Changing department practice
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void changingDepartmentPractice(int startWork, boolean cfh) { // CHANGED - 31.7
		setChosenDepartment();

		chosenDepartment.setStartWork(startWork);
		chosenDepartment.setCanFromHome(cfh);
		chosenDepartment.sync(startWork, cfh);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Changing role practice
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void changingRolePractice(int index, int startWork, boolean cfh) { // CHANGED - 31.7

		Role chosenRole = rolesFromDepartment.get(index);

		if (chosenRole != null) {
			chosenRole.setWorkHours(startWork);
			chosenRole.setCanFromHome(cfh);
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Show company info(the profit)
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String showCompanyProfit() {
		return theBoringCompany.toString();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void ending() throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("company.dat"));
		outFile.writeObject(theBoringCompany);
		outFile.close();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
