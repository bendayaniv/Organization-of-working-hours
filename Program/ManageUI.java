package NadavOren_YanivBenDavid.Program;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import NadavOren_YanivBenDavid.Model.Department;

public interface ManageUI extends Serializable {

	void readFile() throws FileNotFoundException, IOException, ClassNotFoundException;

	void addDepartment(); // CHANGED - 31.7

	void addRole(ArrayList<Department> departments); // CHANGED - 31.7

	void addEmployee(ArrayList<Department> departments); // CHANGED - 31.7

	void printDepartmentInfo();

	void printRoleInfo();

	void printEmployeeInfo();

	void changingDepartmentPractice(ArrayList<Department> departments); // CHANGED - 31.7

	void changingRolePractice(ArrayList<Department> departments); // CHANGED - 31.7

	String showCompanyProfit();

	void ending() throws FileNotFoundException, IOException;

}
