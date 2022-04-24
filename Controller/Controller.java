package NadavOren_YanivBenDavid.Controller;

import java.io.IOException;
import java.io.Serializable;
import NadavOren_YanivBenDavid.Model.Company;
import NadavOren_YanivBenDavid.Model.Model;
import NadavOren_YanivBenDavid.Model.Role;
import NadavOren_YanivBenDavid.Model.Role.eSaleryType;
import NadavOren_YanivBenDavid.View.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

@SuppressWarnings("serial")
public class Controller implements Serializable {

	protected static final Company Elections = new Company();
	private Model theModel;
	private View theView;
	private EventHandler<ActionEvent> checkIfPracticeCanBeFixed, checkInfoDepartmentsComboBox,
			checkForAvaliableRolesInDepartment, checkInfoDepartmentsComboBoxForRoleChange, chosenDepartmentToShow,
			chosenDepartment, checkAllInfoComboBoxesEmployee, checkInfoRolesComboBoxForRoleChange;

	public Controller(Model p_model, View p_view) {

		theModel = p_model;
		theView = p_view;

		if (theModel.checkingExistenseOfOldData()) {
			theView.readFile();
		} else {
			theModel.setCompanyHardCodedDatas();
			theView.start();
		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Changing the choice
		EventHandler<ActionEvent> chMenu = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String kind = theView.getKind(arg0.getSource());
				if (kind == "Add Department") {
					theView.update(kind, null);
				} else if (kind == "Add Role") {
					if (theModel.CheckForDepartmentsAvailablty()) {
						theView.update(kind, theModel.getAllDepartments());
						changeEventHandlerForCmbDepartments(1);
					} else {
						theView.showMessage(
								"There are no avaliable departments \nin the entire company for a new role");
					}
				} else if (kind == "Add Employee") {
					if (theModel.CheckForRolesAvailablty()) {
						theView.update(kind, theModel.getAllDepartments());
						changeEventHandlerForCmbDepartments(2);
						changeEventHandlerForCmbRoels(1);
					} else
						theView.showMessage("There are no avaliable roles \nin the entire company for a new employee");
				} else if (kind == "Showing all departmnets") {
					if (theModel.CheckForDepartmentsAvailablty()) {
						theView.update(kind, theModel.getAllDepartments());
						changeEventHandlerForCmbDepartments(6);
					} else
						theView.showMessage("There are no avaliable departments \nto show");
				} else if (kind == "Showing all roles") {
					if (theModel.CheckForRolesExistence()) {
						theView.update(kind, theModel.getAllDepartments());
						changeEventHandlerForCmbDepartments(4);
					} else
						theView.showMessage("There are no avaliable roles \nto show");
//					changeEventHandlerForCmbRoels(1);
				} else if (kind == "Showing all employees") {
					if (theModel.CheckForEmployeesAvailablty()) {
						theView.update(kind, theModel.getAllEmployees());
					} else
						theView.showMessage("There are no avaliable employees \nto show");
				} else if (kind == "Changing department practice") {
					if (theModel.checkForDepartmentToChange()) {
						theView.update(kind, theModel.getAllDepartments());
						changeEventHandlerForCmbDepartments(3);
					} else
						theView.showMessage("There are no departments that \ncan be change due to \nno syncronized departments \nor unchangeable departments ");
				} else if (kind == "Changing role practice") {
					if (theModel.checkForRoleToChange()) {
						theView.update(kind, theModel.getAllDepartments());
						changeEventHandlerForCmbDepartments(5);
						changeEventHandlerForCmbRoels(2);
					} else
						theView.showMessage("There are no roles \nthat can be change");
				} else if (kind == "Show profit") {
					if (theModel.CheckForEmployeesAvailablty()) {
						theView.update(kind, theModel.showCompanyProfit());
					} else
						theView.showMessage("There are no employees \nto calculate the company profit");
				} else if (kind == "Exit") {
					theView.update(kind, null);
				}
			}
		};

		theView.addEventToBtCreateNewDepartment(chMenu);
		theView.addEventToBtCreateNewRole(chMenu);
		theView.addEventToBtCreateNewEmployee(chMenu);
		theView.addEventToBtShowDepartments(chMenu);
		theView.addEventToBtShowRoles(chMenu);
		theView.addEventToBtShowEmployees(chMenu);
		theView.addEventToBtChangePracticeDepartment(chMenu);
		theView.addEventToBtShowEfficiency(chMenu);
		theView.addEventToBtChangePracticeRole(chMenu);
		theView.addEventToBtExit(chMenu);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Back Button
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		EventHandler<ActionEvent> goBack = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theView.start();
			}
		};
		theView.addEventGoBack(goBack);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Loading old data from binary file section
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Loading old data
		EventHandler<ActionEvent> loadingOldData = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				try {
					theModel.readFile();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				theView.showMessage("You decided to load the old data that exist");
				theView.start();
			}

		};
		theView.addEventToLoadOldData(loadingOldData);

		// Not loading old data
		EventHandler<ActionEvent> notLoadingOldData = new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				theModel.setCompanyHardCodedDatas();
				theView.showMessage("You decided to not load the old data that exist");
				theView.start();
			}

		};
		theView.addEventToNotLoadOldData(notLoadingOldData);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Creating Department
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		EventHandler<ActionEvent> doneDepartment = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!theView.checkingStringContent(theView.getTfNameValue(), "Department"))
					System.out.println("Error with the department name input");
				else if (theModel.checkForDuplicateName(theView.getTfNameValue(), 1)) {
					theView.showMessage("This department's name already exists, try again");
				} else {
					int startWork = -1;
					if (theView.getCbSyncDepartment())
						startWork = theView.cmbStartWorkDepartment();
					theModel.addDepartment(theView.getTfNameValue(), startWork, theView.getCbCanFromHomeDepartment(),
							theView.getCbCanBeChangedDepartment(), theView.getCbSyncDepartment());
					theView.showMessage("Added a new department successfully!");
					theView.start();
				}

			}
		};
		theView.addEventToDoneDepartment(doneDepartment);

		EventHandler<ActionEvent> checkedSyncedDepartment = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theView.setHbStartWorkVisible(theView.getCbSyncDepartment());

			}

		};
		theView.addEventToCheckBoxSync(checkedSyncedDepartment);
		// check if all info has entered
		ChangeListener<String> checkAllInfoDepartment = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				theView.setBtDoneDepartmentAvaliable(!theView.checkAllInfoHasEntered());
			}
		};
		theView.addEventListinerToTextFieldNameDepartment(checkAllInfoDepartment);

		EventHandler<ActionEvent> checkAllInfoComboBoxDepartment = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theView.setBtDoneDepartmentAvaliable(!theView.checkAllInfoHasEntered());
			}
		};
		theView.addEventToCmbStartWorkDepartment(checkAllInfoComboBoxDepartment);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Creating Role
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		EventHandler<ActionEvent> doneRole = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!theView.checkingStringContent(theView.getTfNameRole(), "role"))
					System.out.println("Error with the role name input");
				else if (!theView.checkingNumAsStringContent(theView.getTfSaleryAmount(), "salery amount", 2))
					System.out.println("Error with the salery amount input");
				else {
					int startWork;
					boolean canBeChanged;
					boolean canFromHome;
					if (theView.getIfCbCanBeChangedIsDisabled()
							|| (theView.getIfCbCanBeChangedIsDisabled() && theView.getIfCbStartWorkRoleIsDisabled()))
						canBeChanged = false;
					else
						canBeChanged = theView.getCbCanBeChanged();
					if (theView.getIfCbStartWorkRoleIsDisabled())
						startWork = theModel.getStartWorkDepartment(theView.getCmbDepartments());
					else
						startWork = theView.getCmbStartWorkRole();
					if (theView.getIfCbCanFromHomeRoleIsDisabled())
						canFromHome = theModel.getCanFromHomeFromDepartment(theView.getCmbDepartments());
					else
						canFromHome = theView.getcbCanFromHome();

					theModel.addRole(theView.getTfNameRole(), startWork, canFromHome, canBeChanged,
							Double.valueOf(theView.getTfSaleryAmount()), theModel.getChosenDepartment(),
							theView.getCmbSaleryType());
					theView.showMessage("Added a new role successfully!");
					theView.start();

				}

			}
		};
		theView.addEventToBtDoneRole(doneRole);

		// check if all info has entered
		ChangeListener<String> checkAllInfoTextFieldRole = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				theView.setBtDoneRoleAvaliable(theView.checkAllInfoHasEnteredForRole());
			}
		};
		theView.addEventListinerToTextFieldNameRole(checkAllInfoTextFieldRole);
		theView.addEventListinerToTextFieldSaleryAmount(checkAllInfoTextFieldRole);

		EventHandler<ActionEvent> checkAllInfoComboBoxRole = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theView.setBtDoneRoleAvaliable(theView.checkAllInfoHasEnteredForRole());
			}
		};
		theView.addEventToCmbSaleryType(checkAllInfoComboBoxRole);
		theView.addEventToCmbStartWorkRole(checkAllInfoComboBoxRole);

		checkIfPracticeCanBeFixed = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theModel.setChosenDepartmentName(theView.getCmbDepartments());
				theModel./* printDepartmentInfo */setChosenDepartment();
				theView.setLblChooseFirstNotVisible(true);
				theView.setCmbStartWorkDisabled(theModel.checkSyncedDepartment(theModel.getChosenDepartment()));
				theView.setCbCanFromHomeRoleDisabled(theModel.checkSyncedDepartment(theModel.getChosenDepartment()));
				if (theModel.checkSyncedDepartment(theModel.getChosenDepartment()))
					theView.setCbCanBeChangedDisabled(theModel.checkSyncedDepartment(theModel.getChosenDepartment()));
				else
					theView.setCbCanBeChangedDisabled(
							theModel.checkCanBeChangedDepartment(theModel.getChosenDepartment()));

			}
		};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Creating Employee
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		EventHandler<ActionEvent> doneEmployee = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if (!theView.checkingStringContent(theView.getTfNameEmployee(), "employee"))
					System.out.println("Error with the employee name input");
				else if (theView.getTfId().length() != 9)
					theView.showMessage("ID needs to be 9 digits long");
				else if (!theView.checkingNumAsStringContent(theView.getTfId(), "ID number", 1))
					System.out.println("Error with the ID number input");
				else if (theView.checkiHbfHoursWorkedIsVisible() // CHANGED - 29.7
						&& !theView.checkingNumAsStringContent(theView.getTfHoursWorked(), "hours worked", 1))
					System.out.println("Error with the hours worked input");
				else if (theView.checkiHbfHoursWorkedIsVisible() // CHANGED - 29.7
						&& (Integer.valueOf(theView.getTfHoursWorked()) <= 8 || Integer.valueOf(theView.getTfHoursWorked()) > 180))
					theView.showMessage("Worker need to work between 8 to 180 hours a month");
				else {
					int startWork = -1;
					int hoursAmount = 0; // ADDED - 29.7
					if (!theView.getTfHoursWorked().equals("")) {
						hoursAmount = Integer.valueOf(theView.getTfHoursWorked());
					}
					if (!theView.chechIfCbWorksFromHome())
						startWork = theView.getCmbStartWorkEmployee();
					if (theView.checkiHbfHoursWorkedIsVisible()) { // CHANGED - 29.7
						theModel.addEmployee(theView.getTfNameEmployee(), theView.getTfId(), startWork,
								theView.chechIfCbWorksFromHome(),
								theModel.getChosenRoleFromDepartment(theView.getCmbRoleIndex()), hoursAmount); // CHANGED
																												// -
																												// 31.7
					} else
						theModel.addEmployee(theView.getTfNameEmployee(), theView.getTfId(), startWork,
								theView.chechIfCbWorksFromHome(),
								theModel.getChosenRoleFromDepartment(theView.getCmbRoleIndex()), 0); // CHANGED - 31.7
					theView.showMessage("Added a new employee successfully!");
					theView.start();

				}

			}
		};
		theView.addEventToBtDoneEmployee(doneEmployee);
		ChangeListener<String> checkAllInfoTextFieldEmployee = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				theView.setBtDoneEmployeeAvaliable(theView.checkAllInfoHasEnteredForEmployee());
			}
		};
		theView.addEventListinerToTextFieldNameEmployee(checkAllInfoTextFieldEmployee);
		theView.addEventListinerToTextFieldId(checkAllInfoTextFieldEmployee);
		theView.addEventListinerToTextFieldHoursWorked(checkAllInfoTextFieldEmployee);

		checkAllInfoComboBoxesEmployee = new EventHandler<ActionEvent>() { // CHANGED - 31.7

			@Override
			public void handle(ActionEvent arg0) {
				if(theView.checkSourceActionFrom(arg0.getSource()))
					theView.setcmbStartWorkEmployeeDisable(theView.chechIfCbWorksFromHome());
				theView.setBtDoneEmployeeAvaliable(theView.checkAllInfoHasEnteredForEmployee());
				if (theView.getCmdRoles() != null) {
					theModel.createArrayListForRolesInDepartment(theModel.getChosenDepartment()); // CHANGED - 31.7
					theView.setHbHoursWorkedVisiable(theModel.getChosenRoleFromDepartment(theView.getCmbRoleIndex())
							.getSaleryType() == eSaleryType.hourlySalery);
				}
			}
		};

		theView.addEventToCmbStartWorkEmployee(checkAllInfoComboBoxesEmployee);
		theView.addEventToCbWorksFromHome(checkAllInfoComboBoxesEmployee);

		checkForAvaliableRolesInDepartment = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theModel.setChosenDepartmentName(theView.getCmbDepartments());
				theModel./* printDepartmentInfo */setChosenDepartment();
				theView.addRolesFromChosenDepartment(theModel.getChosenDepartment());
			}
		};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Show department section
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		EventHandler<ActionEvent> showDepartment = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theView.setChosenDepartmentInforamtion(theModel.getChosenDepartmentInformation());
				theView.printDepartmentInfo();
//				}
			}

		};
		theView.addEventToShowDepartmentButton(showDepartment);

		chosenDepartment = new EventHandler<ActionEvent>() { // ADDED - 31.7

			@Override
			public void handle(ActionEvent arg0) {
				String departmentName = theView.getCmbDepartments();
				theModel.setChosenDepartmentName(departmentName);
				theModel./* printDepartmentInfo */setChosenDepartment();
				theView.setBtShowDepartmentVisible(true);
			}

		};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Show role section
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		EventHandler<ActionEvent> showRole = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Role roleName = theView.getCmdRoles(); // CHANGED - 30.7
				if (roleName == null) {
					theView.showMessage("You need to choose one role");
				} else {
					theView.setChosenRoleInforamtion(
							theModel.getChosenRoleFromDepartment(theView.getCmbRoleIndex()).info());
					theView.printRoleInfo();
				}
			}

		};
		theView.addEventToShowRoleButton(showRole);

		chosenDepartmentToShow = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) { // CHANGED - 31.7
				String departmentName = theView.getCmbDepartments();
				if (departmentName != null) {
					theModel.setChosenDepartmentName(departmentName);
					theModel./* printDepartmentInfo */setChosenDepartment();
					theModel.createArrayListForRolesInDepartmentToShow(theModel.getChosenDepartment());
					theView.getAllDepartmentRoles(theModel.roles());
					theView.setBtChangingRolePracticeAvaliable(true);
				}
			}
		};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Show employee section
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		EventHandler<ActionEvent> showEmployee = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				String employeeName = theView.getChosenEmployee();
				if (employeeName == null) {
					theView.showMessage("You need to choose one of the options");
				} else {
					theModel.setChosenEmployeeName(employeeName);
					theModel./* printEmployeeInfo */setChosenEmployee();
					theView.setChosenEmployeeInforamtion(theModel.getChosenEmployeeInformation());
					theView.printEmployeeInfo();
				}
			}

		};
		theView.addEventToShowEmployeeButton(showEmployee);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Change department practice
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		EventHandler<ActionEvent> changeDepartmentPractice = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theModel.setChosenDepartmentName(theView.getCmbDepartments());
				theModel.changingDepartmentPractice(theView.getCmbChangeStartWork(), theView.getcbCanFromHome()); // CHANGED
																													// -
																													// 31.7
				theView.showMessage("You changed successfully");
				theView.start();
			}

		};
		theView.addEventToDoneChangingDepartmentPracticeButton(changeDepartmentPractice);

		checkInfoDepartmentsComboBox = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theView.setBtChangingDepartmentPracticeAvaliable(theView.checkingCmbDepartment());
			}
		};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		// Change role practice
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		EventHandler<ActionEvent> changeRolePractice = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theModel.changingRolePractice(theView.getCmbRoleIndex(), theView.getCmbChangeStartWork(),
						theView.getcbCanFromHome()); // CHANGED - 31.7
				theView.showMessage("You changed successfully");
				theView.start();
			}

		};
		theView.addEventToDoneChangingRolePracticeButton(changeRolePractice);

		checkInfoDepartmentsComboBoxForRoleChange = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				theModel.setChosenDepartmentName(theView.getCmbDepartments());
				theModel./* printDepartmentInfo */setChosenDepartment();
				theModel.createArrayListForRolesInDepartment(theModel.getChosenDepartment());
				theView.getAllDepartmenChangeableRoles(theModel.roles());
				theView.setBtChangingRolePracticeAvaliable(false);
				theView.setChangeCanFromHomeRoleAvaliable(theModel.getChosenDepartment().getFromHome());
				theView.setLblChooseFirstNotVisible(false);
			}
		};

		checkInfoRolesComboBoxForRoleChange = new EventHandler<ActionEvent>() { // CHANGED - 31.7
//

			@Override
			public void handle(ActionEvent arg0) {
				theView.setBtChangingRolePracticeAvaliable(theView.checkingCmbDepartment());
			}
		};

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				

		// Save data to binary file section
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Button for saving data
		EventHandler<ActionEvent> saveData = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					theModel.ending();
				} catch (IOException e) {
					e.printStackTrace();
				}
				theView.showMessage("You decided to save the new data");
				theView.exitJavafxWindow();
			}

		};
		theView.addEventToSaveData(saveData);

		// Button for not saving data
		EventHandler<ActionEvent> doNotSaveData = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				theView.showMessage("You decided to not save the new data");
				theView.exitJavafxWindow();
			}

		};
		theView.addEventToNotSaveData(doNotSaveData);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

	public void changeEventHandlerForCmbDepartments(int type) {
		if (type == 1)
			theView.addEventToCmbDepartments(checkIfPracticeCanBeFixed);
		else if (type == 2)
			theView.addEventToCmbDepartments(checkForAvaliableRolesInDepartment);
		else if (type == 3)
			theView.addEventToCmbDepartments(checkInfoDepartmentsComboBox);
		else if (type == 4)
			theView.addEventToCmbDepartments(chosenDepartmentToShow);
		else if (type == 5)
			theView.addEventToCmbDepartments(checkInfoDepartmentsComboBoxForRoleChange);
		else if (type == 6)
			theView.addEventToCmbDepartments(chosenDepartment);

	}

	public void changeEventHandlerForCmbRoels(int type) {// ADDED - 31.7
		if (type == 1) {
			theView.addEventToCmbRoles(checkAllInfoComboBoxesEmployee);
		} else if (type == 2) {
			theView.addEventToCmbRoles(checkInfoRolesComboBoxForRoleChange);
		}
	}
}
