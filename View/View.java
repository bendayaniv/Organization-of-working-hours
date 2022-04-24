package NadavOren_YanivBenDavid.View;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import NadavOren_YanivBenDavid.Exceptions.CheckingNumAsStringContent;
import NadavOren_YanivBenDavid.Exceptions.CheckingStringContent;
import NadavOren_YanivBenDavid.Exceptions.OurException;
import NadavOren_YanivBenDavid.Model.Department;
import NadavOren_YanivBenDavid.Model.Employee;
import NadavOren_YanivBenDavid.Model.Role;
import NadavOren_YanivBenDavid.Model.Role.eSaleryType;
import NadavOren_YanivBenDavid.Program.ManageUI;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("serial")
public class View implements ManageUI, Serializable {

	private Scene scene;
	private BorderPane bp;
	private Stage globalStage;
	private ComboBox<String> cmbDepartments;
	private ComboBox<Role> cmbRoles;
	private ComboBox<eSaleryType> cmbSaleryType;
	private ComboBox<Integer> cmbStartWorkDepartment, cmbStartWorkRole, cmbStartWorkEmployee;
	private ComboBox<String> allEmployees;
	private Text txt, txtDepartment;
	private HBox hbOldData, hbSaveData, hbStartWork, hbHoursWorked, hbRolesForChange, hbChangeCanFromHomeRole,
			hbCanBeChanged, hbDoneBack;
	private VBox vbCenter;
	private Button btLoadData, btSaveData, btDoNotSaveData, btDoNotLoadData, btShowDepartment, btShowRole,
			btShowEmployee, btCreateNewDepartment, btCreateNewRole, btCreateNewEmployee, btShowDepartments, btShowRoles,
			btShowEmployees, btChangePracticeForRole, btChangePracticeForDepartment, btShowEfficiency, btBack,
			btDoneDepartment, btDoneRole, btDoneEmployee, btDoneChangingDepartmentPractice, btDoneChangingRolePractice,
			btExit;
	private Label lblLoadingOldData, lblSavingData, lblMainMenu, lblCantChangePractice, lblCantChangeStartWork,
			lblCantChangePracticeDueToSync, lblSelectDepartmentNeeded, lblCantChangeCanFromHome, lblChooseFirst,
			lblRoles,lblPrefereToWorkAtHome, lblHourlyEmployee, lblEmpty;
	private TextField tfNameDepartment, tfNameRole, tfNameEmployee, tfSaleryAmount, tfId, tfHoursWorked;
	private CheckBox cbCanBeChanged, cbSynced, cbWorksFromHome, cbCanFromHomeDepartment;
	private String chosenDepartmentInformation, chosenRoleInformation, chosenEmployeeInformation, companyInformation;

	public View(Stage stage) {

		globalStage = stage;
		globalStage.setTitle("Company");

		bp = new BorderPane();

		vbCenter = new VBox();
		vbCenter.setAlignment(Pos.CENTER);
		vbCenter.setSpacing(10);
		vbCenter.setPadding(new Insets(80));

		bp.setCenter(vbCenter);

		// Load old data from binary file
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		lblLoadingOldData = new Label();
		lblLoadingOldData.setText("Do you want to load the old data?");

		btLoadData = new Button();
		btLoadData.setText("Yes");

		btDoNotLoadData = new Button();
		btDoNotLoadData.setText("No");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Save data to binary file
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		lblSavingData = new Label();
		lblSavingData.setText("Do you want to save the data?");

		btSaveData = new Button();
		btSaveData.setText("Yes");

		btDoNotSaveData = new Button();
		btDoNotSaveData.setText("No");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Button that shows 1 employee according to choosing from ComboBox
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		btShowDepartment = new Button();
		btShowDepartment.setText("Show Department");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Button that shows 1 role according to choosing from ComboBox
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		btShowRole = new Button();
		btShowRole.setText("Show Role");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Button that shows 1 employee according to choosing from ComboBox
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		allEmployees = new ComboBox<>();

		btShowEmployee = new Button();
		btShowEmployee.setText("Show Employee");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Creating toggle group for the menu
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		lblMainMenu = new Label("Main Menu");
		lblMainMenu.setFont(Font.font("Castellar", 30));

		btCreateNewDepartment = new Button("Add Department");

		btCreateNewRole = new Button("Add Role");

		btCreateNewEmployee = new Button("Add Employee");

		btShowDepartments = new Button("Showing all departmnets");

		btShowRoles = new Button("Showing all roles");

		btShowEmployees = new Button("Showing all employees");

		btChangePracticeForDepartment = new Button("Changing department practice");

		btChangePracticeForRole = new Button("Changing role practice");

		btShowEfficiency = new Button("Show profit");

		btExit = new Button("Exit");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		lblCantChangePractice = new Label("Can't change this practice due to \ndepartment work practice");
		lblCantChangePractice.setTextFill(Color.RED);

		lblCantChangeStartWork = new Label("Can't change the start hour due to \ndepartment syncronized work");
		lblCantChangeStartWork.setTextFill(Color.RED);

		lblCantChangeCanFromHome = new Label("Can't change the work from home \ndue to department syncronized work");
		lblCantChangeCanFromHome.setTextFill(Color.RED);

		lblCantChangePracticeDueToSync = new Label("Can't change this practice due to \ndepartment synced practice");
		lblCantChangePracticeDueToSync.setTextFill(Color.RED);

		lblSelectDepartmentNeeded = new Label("Need to select the department first \nto choose the role within it");
		lblSelectDepartmentNeeded.setTextFill(Color.RED);
		
		lblPrefereToWorkAtHome = new Label("The employee wants to work at home \nregardless of the hour of work");

		lblChooseFirst = new Label("Please enter the department first \nto fill the other infromation");

		lblRoles = new Label("Choose the assigned role: ");

		lblHourlyEmployee = new Label("Enter the amount of hours he is \nworking this month (180 maximum):");

		lblEmpty = new Label();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Buttons
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		btDoneDepartment = new Button("Done");
		btDoneRole = new Button("Done");
		btDoneEmployee = new Button("Done");
		btDoneChangingDepartmentPractice = new Button("Done");
		btDoneChangingRolePractice = new Button("Done");
		btBack = new Button("Go Back");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// HBoxes
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		hbStartWork = new HBox();

		hbHoursWorked = new HBox();

		hbCanBeChanged = new HBox();

		hbDoneBack = new HBox();
		hbDoneBack.getChildren().addAll(btBack);
		hbDoneBack.setSpacing(250);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Text Fields
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		tfNameDepartment = new TextField();
		tfNameRole = new TextField();
		tfNameEmployee = new TextField();
		tfSaleryAmount = new TextField();
		tfId = new TextField();
		tfHoursWorked = new TextField();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// CheckBoxes
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		cbSynced = new CheckBox();
		cbCanBeChanged = new CheckBox();
		cbWorksFromHome = new CheckBox();
		cbCanFromHomeDepartment = new CheckBox();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// ComboBoxes
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		cmbDepartments = new ComboBox<String>();

		cmbRoles = new ComboBox<Role>();

		cmbSaleryType = new ComboBox<eSaleryType>();
		cmbSaleryType.getItems().addAll(eSaleryType.values());

		cmbStartWorkDepartment = new ComboBox<Integer>();
		cmbStartWorkDepartment.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23);
		cmbStartWorkRole = new ComboBox<Integer>();
		cmbStartWorkRole.getItems().addAll(cmbStartWorkDepartment.getItems());

		cmbStartWorkEmployee = new ComboBox<Integer>();
		cmbStartWorkEmployee.getItems().addAll(cmbStartWorkDepartment.getItems());

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		scene = new Scene(bp, 800, 600);
		scene.setFill(Color.BLUEVIOLET);
		globalStage.setScene(scene);
		globalStage.show();

	}

	public void addEventGoBack(EventHandler<ActionEvent> event) {
		btBack.setOnAction(event);
	}

	public void start() {

		vbCenter.getChildren().clear();
		VBox allChoices = new VBox();
		allChoices.getChildren().addAll(btCreateNewDepartment, btCreateNewRole, btCreateNewEmployee, btShowDepartments,
				btShowRoles, btShowEmployees, btChangePracticeForDepartment, btChangePracticeForRole, btShowEfficiency,
				btExit);
		VBox allTogether = new VBox();
		allTogether.getChildren().addAll(lblMainMenu, allChoices);
		allTogether.setSpacing(50);

		vbCenter.getChildren().addAll(allTogether);
		bp.setCenter(vbCenter);
		if (hbDoneBack.getChildren().size() > 1)
			hbDoneBack.getChildren().remove(1);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public void update(String kind, Object obj1) {
		if (kind == "Add Department") {
			addDepartment();
		} else if (kind == "Add Role") {
			addRole((ArrayList<Department>) obj1); // CHANGED - 31.7
		} else if (kind == "Add Employee") {
			addEmployee((ArrayList<Department>) obj1); // CHANGED - 31.7
		} else if (kind == "Showing all departmnets") {
			createShowDepartmentWindow((ArrayList<Department>) obj1);
		} else if (kind == "Showing all roles") {
			createShowRoleWindow((ArrayList<Department>) obj1);
		} else if (kind == "Showing all employees") {
			createShowEmployeeWindow((ArrayList<Employee>) obj1);
		} else if (kind == "Changing department practice") {
			changingDepartmentPractice((ArrayList<Department>) obj1); // CHANGED - 31.7
		} else if (kind == "Changing role practice") {
			changingRolePractice((ArrayList<Department>) obj1); // CHANGED - 31.7
		} else if (kind == "Show profit") {
			setCompanyInfo((String) obj1);
			showCompanyProfit();
		} else if (kind == "Exit") {
			ending();
		}
	}

	public String getKind(Object obj) {

		if (btCreateNewDepartment.equals(obj))
			return btCreateNewDepartment.getText();
		else if (btCreateNewRole.equals(obj))
			return btCreateNewRole.getText();
		else if (btCreateNewEmployee.equals(obj))
			return btCreateNewEmployee.getText();
		else if (btShowDepartments.equals(obj))
			return btShowDepartments.getText();
		else if (btShowDepartments.equals(obj))
			return btShowDepartments.getText();
		else if (btShowRoles.equals(obj))
			return btShowRoles.getText();
		else if (btShowEmployees.equals(obj))
			return btShowEmployees.getText();
		else if (btChangePracticeForDepartment.equals(obj))
			return btChangePracticeForDepartment.getText();
		else if (btChangePracticeForRole.equals(obj))
			return btChangePracticeForRole.getText();
		else if (btShowEfficiency.equals(obj))
			return btShowEfficiency.getText();
		else
			return btExit.getText();
	}

	public void addEventToBtCreateNewDepartment(EventHandler<ActionEvent> event) {
		btCreateNewDepartment.setOnAction(event);
	}

	public void addEventToBtCreateNewRole(EventHandler<ActionEvent> event) {
		btCreateNewRole.setOnAction(event);
	}

	public void addEventToBtCreateNewEmployee(EventHandler<ActionEvent> event) {
		btCreateNewEmployee.setOnAction(event);
	}

	public void addEventToBtShowDepartments(EventHandler<ActionEvent> event) {
		btShowDepartments.setOnAction(event);
	}

	public void addEventToBtShowRoles(EventHandler<ActionEvent> event) {
		btShowRoles.setOnAction(event);
	}

	public void addEventToBtShowEmployees(EventHandler<ActionEvent> event) {
		btShowEmployees.setOnAction(event);
	}

	public void addEventToBtChangePracticeDepartment(EventHandler<ActionEvent> event) {
		btChangePracticeForDepartment.setOnAction(event);
	}

	public void addEventToBtChangePracticeRole(EventHandler<ActionEvent> event) {
		btChangePracticeForRole.setOnAction(event);
	}

	public void addEventToBtShowEfficiency(EventHandler<ActionEvent> event) {
		btShowEfficiency.setOnAction(event);
	}

	public void addEventToBtExit(EventHandler<ActionEvent> event) {
		btExit.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// read file
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void readFile() {
		vbCenter.getChildren().clear();
		hbOldData = new HBox();
		hbOldData.getChildren().addAll(btLoadData, btDoNotLoadData);
		hbOldData.setAlignment(Pos.CENTER);
		vbCenter.getChildren().addAll(lblLoadingOldData, hbOldData);
		vbCenter.setAlignment(Pos.CENTER);
	}

	public void addEventToLoadOldData(EventHandler<ActionEvent> event) {
		btLoadData.setOnAction(event);
	}

	public void addEventToNotLoadOldData(EventHandler<ActionEvent> event) {
		btDoNotLoadData.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// add department
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addDepartment() { // CHANGED - 31.7
		vbCenter.getChildren().clear();

		Label lblCreatingDepartment = new Label("Creating new Department");
		lblCreatingDepartment.setFont(Font.font("Castellar", 20));
		VBox allTogether = new VBox();

		Label lblDepartmentName = new Label("Enter the new department's name:");

		tfNameDepartment.clear();

		HBox hbName = new HBox();
		hbName.getChildren().addAll(lblDepartmentName, tfNameDepartment);
		hbName.setSpacing(10);

		Label lblCanBeChanged = new Label("Check if the practice for roles\n can be changed:");

		cbCanBeChanged.setSelected(false);

		HBox hbCanBeChanged = new HBox();
		hbCanBeChanged.getChildren().addAll(lblCanBeChanged, cbCanBeChanged);
		hbCanBeChanged.setSpacing(38);

		Label lblCanFromHome = new Label("Check if the roles have \nthe option to work from home:");

		cbCanFromHomeDepartment.setSelected(false);

		HBox hbCanFromHome = new HBox();
		hbCanFromHome.getChildren().addAll(lblCanFromHome, cbCanFromHomeDepartment);
		hbCanFromHome.setSpacing(28);

		Label lblSynced = new Label("Check if the department is synced:");

		cbSynced.setSelected(false);

		HBox hbSynced = new HBox();
		hbSynced.getChildren().addAll(lblSynced, cbSynced);
		hbSynced.setSpacing(10);

		Label lblStartWork = new Label("Choose the start hour:\n(0-23)");

		cmbStartWorkDepartment.getSelectionModel().clearSelection();
		hbStartWork.getChildren().clear();
		hbStartWork.getChildren().addAll(lblStartWork, cmbStartWorkDepartment);
		hbStartWork.setVisible(false);
		hbStartWork.setSpacing(75);

		btDoneDepartment.setDisable(true);
		hbDoneBack.getChildren().add(btDoneDepartment);

		allTogether.getChildren().addAll(lblCreatingDepartment, hbName, hbCanBeChanged, hbCanFromHome, hbSynced,
				hbStartWork, hbDoneBack);
		allTogether.setSpacing(20);

		vbCenter.getChildren().addAll(allTogether);
		vbCenter.setAlignment(Pos.CENTER);
	}

	public String getTfNameValue() {
		return tfNameDepartment.getText();
	}

	public boolean getCbCanBeChangedDepartment() {
		return cbCanBeChanged.isSelected();
	}

	public boolean getCbCanFromHomeDepartment() {
		return cbCanFromHomeDepartment.isSelected();
	}

	public boolean getCbSyncDepartment() {
		return cbSynced.isSelected();
	}

	public int cmbStartWorkDepartment() {
		return cmbStartWorkDepartment.getValue();
	}

	public boolean checkingStringContent(String txt, String type) {
		try {
			@SuppressWarnings("unused")
			CheckingStringContent checkingStringContent = new CheckingStringContent(txt, type);
			return true;
		} catch (OurException oe) {
			showMessage(oe.getMessage());
			return false;
		} catch (Exception ex) {
			showMessage("There was an error - " + ex.getMessage());
			return false;
		}
	}

	public boolean checkingNumAsStringContent(String txt, String type, int numType) {
		try {
			@SuppressWarnings("unused")
			CheckingNumAsStringContent checkingNumAsStringContent = new CheckingNumAsStringContent(txt, type, numType);
			return true;
		} catch (OurException oe) {
			showMessage(oe.getMessage());
			return false;
		} catch (Exception ex) {
			showMessage("There was an error - " + ex.getMessage());
			return false;
		}
	}

	public void addEventToDoneDepartment(EventHandler<ActionEvent> event) {
		btDoneDepartment.setOnAction(event);
	}

	public void addEventToCheckBoxSync(EventHandler<ActionEvent> event) {
		cbSynced.setOnAction(event);
	}

	public void addEventToCmbStartWorkDepartment(EventHandler<ActionEvent> event) {
		cmbStartWorkDepartment.setOnAction(event);
	}

	public void addEventListinerToTextFieldNameDepartment(ChangeListener<String> event) {
		tfNameDepartment.textProperty().addListener(event);
	}

	public boolean checkAllInfoHasEntered() {
		return (tfNameDepartment.getText().isEmpty()
				|| (cmbStartWorkDepartment.getSelectionModel().isEmpty() && cbSynced.isSelected()));
	}

	public void setBtDoneDepartmentAvaliable(boolean t) {
		btDoneDepartment.setDisable(!t);
	}

	public void setHbStartWorkVisible(boolean t) {
		hbStartWork.setVisible(t);
		cmbStartWorkDepartment.getSelectionModel().clearSelection();
		if (!tfNameDepartment.getText().isEmpty())
			btDoneDepartment.setDisable(t);

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// add role
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addRole(ArrayList<Department> departments) { // CHANGED - 31.7
		vbCenter.getChildren().clear();

		Label lblCreateRole = new Label("Creating New Role");
		lblCreateRole.setFont(Font.font("Castellar", 20));
		VBox allTogether = new VBox();

		Label lblRoleName = new Label("Enter the new role's name:");

		tfNameRole.clear();

		HBox hbName = new HBox();
		hbName.getChildren().addAll(lblRoleName, tfNameRole);
		hbName.setSpacing(37);

		Label lblDepartment = new Label("Enter the assigned department:");

		cmbDepartments.getItems().clear();
		int size = departments.size();
		for (int i = 0; i < size; i++)
			cmbDepartments.getItems().add(departments.get(i).getName());
		lblChooseFirst.setVisible(true);

		HBox hbDepartments = new HBox();
		hbDepartments.getChildren().addAll(lblDepartment, cmbDepartments, lblChooseFirst);
		hbDepartments.setSpacing(11);

		Label lblCanBeChanged = new Label("Check if the practice can \nbe changed:");

		cbCanBeChanged.setDisable(true);
		cbCanBeChanged.setSelected(false);

		lblCantChangePractice.setVisible(false);
		lblCantChangePracticeDueToSync.setVisible(false);

		hbCanBeChanged.getChildren().clear();
		hbCanBeChanged.getChildren().addAll(lblCanBeChanged, cbCanBeChanged);
		hbCanBeChanged.setSpacing(43);

		Label lblCanFromHome = new Label("Check if employee can work\nfrom home:");

		cbWorksFromHome.setSelected(false);
		cbWorksFromHome.setDisable(false);
		lblCantChangeCanFromHome.setVisible(false);

		HBox hbCanFromHome = new HBox();
		hbCanFromHome.getChildren().addAll(lblCanFromHome, cbWorksFromHome, lblCantChangeCanFromHome);
		hbCanFromHome.setSpacing(27);

		Label lblStartWork = new Label("Choose the start hour:");

		lblCantChangeStartWork.setVisible(false);
		cmbStartWorkRole.setDisable(true);
		cmbStartWorkRole.getSelectionModel().clearSelection();

		HBox hbStartWork = new HBox();
		hbStartWork.getChildren().addAll(lblStartWork, cmbStartWorkRole, lblCantChangeStartWork);
		hbStartWork.setSpacing(58);

		Label lblSaleryType = new Label("Choose the salery type: ");

		cmbSaleryType.getSelectionModel().clearSelection();

		HBox hbSaleryType = new HBox();
		hbSaleryType.getChildren().addAll(lblSaleryType, cmbSaleryType);
		hbSaleryType.setSpacing(50.5);

		Label lblSaleryAmount = new Label("Write the salery amount:\n(can be with a float point)");

		tfSaleryAmount.clear();

		HBox hbSaleryAmount = new HBox();
		hbSaleryAmount.getChildren().addAll(lblSaleryAmount, tfSaleryAmount);
		hbSaleryAmount.setSpacing(40.5);

		btDoneRole.setDisable(true);
		hbDoneBack.getChildren().add(btDoneRole);

		allTogether.getChildren().addAll(lblCreateRole, hbName, hbSaleryType, hbSaleryAmount, hbDepartments,
				hbCanFromHome, hbCanBeChanged, hbStartWork, hbDoneBack);
		allTogether.setSpacing(15);

		vbCenter.getChildren().addAll(allTogether);
		vbCenter.setAlignment(Pos.CENTER);

	}

	public String getTfNameRole() {
		return tfNameRole.getText();
	}

	public eSaleryType getCmbSaleryType() {
		return cmbSaleryType.getValue();
	}

	public String getCmbDepartments() {
		return cmbDepartments.getValue();
	}

	public String getTfSaleryAmount() {
		return tfSaleryAmount.getText();
	}

	public boolean getcbCanFromHome() {
		return cbWorksFromHome.isSelected();
	}

	public boolean getCbCanBeChanged() {
		return cbCanBeChanged.isSelected();
	}

	public int getCmbStartWorkRole() {
		return cmbStartWorkRole.getValue();
	}

	public boolean getIfCbCanBeChangedIsDisabled() {
		return cbCanBeChanged.isDisabled();
	}

	public boolean getIfCbStartWorkRoleIsDisabled() {
		return cmbStartWorkRole.isDisabled();
	}

	public boolean getIfCbCanFromHomeRoleIsDisabled() {
		return cbWorksFromHome.isDisabled();
	}

	public void addEventToCmbSaleryType(EventHandler<ActionEvent> event) {
		cmbSaleryType.setOnAction(event);
	}

	public void addEventToCmbStartWorkRole(EventHandler<ActionEvent> event) {
		cmbStartWorkRole.setOnAction(event);
	}

	public void addEventListinerToTextFieldNameRole(ChangeListener<String> event) {
		tfNameRole.textProperty().addListener(event);
	}

	public void addEventListinerToTextFieldSaleryAmount(ChangeListener<String> event) {
		tfSaleryAmount.textProperty().addListener(event);
	}

	public void addEventToBtDoneRole(EventHandler<ActionEvent> event) {
		btDoneRole.setOnAction(event);
	}

	public boolean checkAllInfoHasEnteredForRole() {
		if (tfNameRole.getText().isBlank() || cmbDepartments.getSelectionModel().isEmpty()
				|| cmbSaleryType.getSelectionModel().isEmpty() || tfSaleryAmount.getText().isEmpty()
				|| (cmbStartWorkRole.getSelectionModel().isEmpty() && !cmbStartWorkRole.isDisabled()))
			return false;
		else
			return true;
	}

	public void setLblChooseFirstNotVisible(boolean t) {
		lblChooseFirst.setVisible(!t);
	}

	public void setBtDoneRoleAvaliable(boolean t) {
		btDoneRole.setDisable(!t);
	}

	public void setCbCanBeChangedDisabled(boolean t) {
		cbCanBeChanged.setDisable(t);
		if (t) {
			if (hbCanBeChanged.getChildren().size() > 2)
				hbCanBeChanged.getChildren().remove(2);
			if (cbWorksFromHome.isDisabled()) {
				hbCanBeChanged.getChildren().add(lblCantChangePracticeDueToSync);
				lblCantChangePracticeDueToSync.setVisible(t);
			} else {
				hbCanBeChanged.getChildren().add(lblCantChangePractice);
				lblCantChangePractice.setVisible(t);
			}
		} else {
			lblCantChangePracticeDueToSync.setVisible(t);
			lblCantChangePractice.setVisible(t);
		}
	}

	public void setCbCanFromHomeRoleDisabled(boolean t) {
		cbWorksFromHome.setDisable(t);
		lblCantChangeCanFromHome.setVisible(t);
	}

	public void setCmbStartWorkDisabled(boolean t) {
		if (checkAllInfoHasEnteredForRole())
			btDoneRole.setDisable(!t);
		cmbStartWorkRole.setDisable(t);
		cmbStartWorkRole.getSelectionModel().clearSelection();
		lblCantChangeStartWork.setVisible(t);

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// add employee
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addEmployee(ArrayList<Department> departments) { // CHANGED - 31.7
		vbCenter.getChildren().clear();

		Label CreatingEmployee = new Label("Creating New Employee");
		CreatingEmployee.setFont(Font.font("Castellar", 20));
		VBox allTogether = new VBox();

		Label lblEmployeeName = new Label("Enter the new employee's name:");

		tfNameEmployee.clear();

		HBox hbName = new HBox();
		hbName.getChildren().addAll(lblEmployeeName, tfNameEmployee);
		hbName.setSpacing(17);

		Label lblId = new Label("Enter employee's ID: \n(9 digits long)");

		tfId.clear();

		HBox hbId = new HBox();
		hbId.getChildren().addAll(lblId, tfId);
		hbId.setSpacing(77);

		Label lblDepartment = new Label("Choose the assigned department:");

		cmbDepartments.getItems().clear();
		int size = departments.size();
		for (int i = 0; i < size; i++) {
			if (departments.get(i).getAllRoles().size() > 0) {
				int sizeRoles = departments.get(i).getAllRoles().size();
				ArrayList<Role> roles = departments.get(i).getAllRoles();
				for (int j = 0; j < sizeRoles; j++)
					if (roles.get(j).getEmployee() == null) {
						cmbDepartments.getItems().add(departments.get(i).getName());
						break;
					}
			}
		}
		HBox hbDepartments = new HBox();
		hbDepartments.getChildren().addAll(lblDepartment, cmbDepartments);
		hbDepartments.setSpacing(11);

		cmbRoles.getItems().clear();

		lblRoles.setDisable(true);
		cmbRoles.setDisable(true);
		lblSelectDepartmentNeeded.setVisible(true);

		HBox hbRoles = new HBox();
		hbRoles.getChildren().addAll(lblRoles, cmbRoles, lblSelectDepartmentNeeded);
		hbRoles.setSpacing(49.5);

		tfHoursWorked.clear();

		hbHoursWorked.getChildren().clear();
		hbHoursWorked.getChildren().addAll(lblHourlyEmployee, tfHoursWorked);
		hbHoursWorked.setVisible(false);
		hbHoursWorked.setSpacing(20);

		Label lblPreferenceType = new Label("Check if the Employee\n wants to work from home:");

		cbWorksFromHome.setSelected(false);
		cbWorksFromHome.setDisable(false);

		HBox hbPreferenceType = new HBox();
		hbPreferenceType.getChildren().addAll(lblPreferenceType, cbWorksFromHome, hbHoursWorked);
		hbPreferenceType.setSpacing(48);

		Label lblPreferenceStartWork = new Label("Choose the preferred \nstart work time:");

		cmbStartWorkEmployee.setDisable(false);
		cmbStartWorkEmployee.getSelectionModel().clearSelection();
		lblPrefereToWorkAtHome.setVisible(false);

		HBox hbPreferenceStartWork = new HBox();
		hbPreferenceStartWork.getChildren().addAll(lblPreferenceStartWork, cmbStartWorkEmployee,lblPrefereToWorkAtHome);
		hbPreferenceStartWork.setSpacing(74);

		btDoneEmployee.setDisable(true);
		hbDoneBack.getChildren().add(btDoneEmployee);

		allTogether.getChildren().addAll(CreatingEmployee, hbName, hbId, hbDepartments, hbRoles, hbPreferenceType,
				hbPreferenceStartWork, hbDoneBack);
		allTogether.setSpacing(10);

		vbCenter.getChildren().addAll(allTogether);
		vbCenter.setAlignment(Pos.CENTER);

	}

	public void addRolesFromChosenDepartment(Department d) {
		cmbRoles.getItems().clear();
		int size = d.getAllRoles().size();
		for (int i = 0; i < size; i++) {
			if (d.getAllRoles().get(i).getEmployee() == null) {
				cmbRoles.getItems().add(d.getAllRoles().get(i));
			}
		}
		cmbRoles.setDisable(false);
		lblSelectDepartmentNeeded.setVisible(false);
		lblRoles.setDisable(false);

	}

	public String getTfNameEmployee() {
		return tfNameEmployee.getText();
	}

	public String getTfId() {
		return tfId.getText();
	}

	public String getTfHoursWorked() {
		return tfHoursWorked.getText();
	}

	public Role getCmdRoles() {
		return cmbRoles.getValue();
	}

	public int getCmbRoleIndex() {
		return cmbRoles.getSelectionModel().getSelectedIndex();
	}

	public int getCmbStartWorkEmployee() {
		return cmbStartWorkEmployee.getValue();
	}

	public boolean checkiHbfHoursWorkedIsVisible() { // CHANGED - 29.7
		return hbHoursWorked.isVisible();
	}

	public boolean chechIfCbWorksFromHome() {
		return cbWorksFromHome.isSelected();
	}
	public boolean checkSourceActionFrom(Object obj1) {
		return cbWorksFromHome.equals(obj1);
	}
	public void addEventListinerToTextFieldNameEmployee(ChangeListener<String> event) {
		tfNameEmployee.textProperty().addListener(event);
	}

	public void addEventListinerToTextFieldId(ChangeListener<String> event) {
		tfId.textProperty().addListener(event);
	}

	public void addEventListinerToTextFieldHoursWorked(ChangeListener<String> event) {
		tfHoursWorked.textProperty().addListener(event);
	}

	public void addEventToCmbRoles(EventHandler<ActionEvent> event) {
		cmbRoles.setOnAction(event);
	}

	public void addEventToCmbDepartments(EventHandler<ActionEvent> event) {
		cmbDepartments.setOnAction(event);
	}

	public void addEventToCbWorksFromHome(EventHandler<ActionEvent> event) {
		cbWorksFromHome.setOnAction(event);
	}

	public void addEventToCmbStartWorkEmployee(EventHandler<ActionEvent> event) {
		cmbStartWorkEmployee.setOnAction(event);
	}

	public void addEventToBtDoneEmployee(EventHandler<ActionEvent> event) {
		btDoneEmployee.setOnAction(event);
	}
	public void setcmbStartWorkEmployeeDisable(boolean t) {
		cmbStartWorkEmployee.setDisable(t);
		lblPrefereToWorkAtHome.setVisible(t);
	}

	public void setHbHoursWorkedVisiable(boolean t) {
		hbHoursWorked.setVisible(t);
	}

	public void setBtDoneEmployeeAvaliable(boolean t) {
		btDoneEmployee.setDisable(t);
	}

	public boolean checkAllInfoHasEnteredForEmployee() {
		if (tfNameEmployee.getText().isEmpty() || tfId.getText().isEmpty() || cmbRoles.getSelectionModel().isEmpty()
				|| (cmbStartWorkEmployee.getSelectionModel().isEmpty() && !cmbStartWorkEmployee.isDisabled())
				|| (tfHoursWorked.getText().isEmpty() && hbHoursWorked.isVisible()))
			return true;
		else
			return false;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Printing department info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void printDepartmentInfo() {
		if (vbCenter.getChildren().size() == 5) {
			vbCenter.getChildren().remove(4);
		}

		txt = new Text(chosenDepartmentInformation);
		vbCenter.getChildren().add(txt);
	}

	public void createShowDepartmentWindow(ArrayList<Department> departments) {
		vbCenter.getChildren().clear();

		BorderPane bp1 = new BorderPane();
		Label lblShowDepartment = new Label("Showing Department Info");
		lblShowDepartment.setFont(Font.font("Castellar", 20));
		HBox hbDepartments = new HBox();

		cmbDepartments.getItems().clear();
		for (int i = 0; i < departments.size(); i++) {
			cmbDepartments.getItems().add(String.valueOf(departments.get(i).getName()));
		}
		cmbDepartments.setVisible(true);
		txt = new Text("Departments list: ");

		hbDepartments.getChildren().addAll(txt, cmbDepartments);
		hbDepartments.setAlignment(Pos.TOP_CENTER);

		bp1.setTop(hbDepartments);
		hbDoneBack.getChildren().add(lblEmpty);

		btShowDepartment.setVisible(false);
		vbCenter.getChildren().addAll(lblShowDepartment, hbDoneBack, hbDepartments, btShowDepartment);
		vbCenter.setAlignment(Pos.TOP_CENTER);
	}

	public void setChosenDepartmentInforamtion(String information) {
		chosenDepartmentInformation = information.toString();
	}

	public void setBtShowDepartmentVisible(boolean t) {
		btShowDepartment.setVisible(t);
	}

	public void addEventToShowDepartmentButton(EventHandler<ActionEvent> event) {
		btShowDepartment.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Printing role info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void printRoleInfo() {
		if (vbCenter.getChildren().size() == 6) {
			vbCenter.getChildren().remove(5);
		}

		txt = new Text(chosenRoleInformation);
		vbCenter.getChildren().add(txt);
	}

	public void createShowRoleWindow(ArrayList<Department> departments) {
		vbCenter.getChildren().clear();

		HBox hbDepartments = new HBox();
		Label lblShowRole = new Label("Showing Role Info");
		lblShowRole.setFont(Font.font("Castellar", 20));

		cmbDepartments.getItems().clear();
		for (int i = 0; i < departments.size(); i++) {
			cmbDepartments.getItems().add(String.valueOf(departments.get(i).getName()));
		}
		cmbDepartments.setVisible(true);
		txtDepartment = new Text("Departments list: ");

		hbDoneBack.getChildren().add(lblEmpty);

		hbDepartments.getChildren().addAll(txtDepartment, cmbDepartments);
		hbDepartments.setAlignment(Pos.TOP_CENTER);
		vbCenter.getChildren().addAll(lblShowRole, hbDoneBack, hbDepartments);
		vbCenter.setAlignment(Pos.TOP_CENTER);
	}

	public void setChosenRoleInforamtion(String information) {
		chosenRoleInformation = information.toString();
	}

	public void getAllDepartmentRoles(ArrayList<Role> roles) {
		HBox hbRoles = new HBox();

		cmbRoles.getItems().clear();
		if (roles.size() == 0) {
			txt = new Text("There is no roles to show");
			hbRoles.getChildren().addAll(txt);
			btShowRole.setVisible(false);
		} else {
			for (int i = 0; i < roles.size(); i++) { // CHANGED - 31.7
				if (i != 0) {
					int count = 0;
					for (int j = 0; j < i; j++) {
						if (!roles.get(j).equals(roles.get(i))) { // CHANGED - 1.8
							count += 1;
						} else {
							break;
						}
					}
					if (count == i) {
						cmbRoles.getItems().add((roles.get(i)));
					}
				} else {
					cmbRoles.getItems().add((roles.get(i)));
				}
			}
			cmbRoles.setVisible(true);
			cmbRoles.setDisable(false);
			txt = new Text("Roles list: ");

			hbRoles.getChildren().addAll(txt, cmbRoles);
			btShowRole.setVisible(true);
		}

		hbRoles.setAlignment(Pos.TOP_CENTER);

		if (vbCenter.getChildren().size() > 3) {
			vbCenter.getChildren().set(3, hbRoles);
			vbCenter.getChildren().set(4, btShowRole);
		} else {
			vbCenter.getChildren().addAll(hbRoles, btShowRole);
		}
	}

	public void addEventToShowRoleButton(EventHandler<ActionEvent> event) {
		btShowRole.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Printing employee info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void printEmployeeInfo() {
		if (vbCenter.getChildren().size() == 5) {
			vbCenter.getChildren().remove(4);
		}

		txt = new Text(chosenEmployeeInformation);
		vbCenter.getChildren().add(txt);
	}

	public void createShowEmployeeWindow(ArrayList<Employee> employees) {
		vbCenter.getChildren().clear();

		BorderPane bp1 = new BorderPane();
		HBox hbEmployees = new HBox();

		Label lblShowEmployee = new Label("Showing Employee Info");
		lblShowEmployee.setFont(Font.font("Castellar", 20));

		allEmployees.getItems().clear();
		for (int i = 0; i < employees.size(); i++) {
			allEmployees.getItems().add(String.valueOf(employees.get(i).getName()));
		}
		allEmployees.setVisible(true);
		txt = new Text("Employees list: ");

		hbEmployees.getChildren().addAll(txt, allEmployees);
		hbEmployees.setAlignment(Pos.TOP_CENTER);

		bp1.setTop(hbEmployees);
		hbDoneBack.getChildren().add(lblEmpty);

		vbCenter.getChildren().addAll(lblShowEmployee, hbDoneBack, hbEmployees, btShowEmployee);
		vbCenter.setAlignment(Pos.TOP_CENTER);
	}

	public String getChosenEmployee() {
		return allEmployees.getValue();
	}

	public void setChosenEmployeeInforamtion(String information) {
		chosenEmployeeInformation = information.toString();
	}

	public void addEventToShowEmployeeButton(EventHandler<ActionEvent> event) {
		btShowEmployee.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Changing department practice
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void changingDepartmentPractice(ArrayList<Department> departments) { // CHANGED - 31.7
		vbCenter.getChildren().clear();

		Label changingDepartmentPractive = new Label("Changing Department Practice");
		changingDepartmentPractive.setFont(Font.font("Castellar", 20));

		VBox allTogether = new VBox();

		Label lblDepartment = new Label("Enter the assigned department:");

		cmbDepartments.getItems().clear();
		int size = departments.size();
		for (int i = 0; i < size; i++)
			if (departments.get(i).getPracticeCanBeChanged() == true && departments.get(i).getSynced() == true)
				cmbDepartments.getItems().add(departments.get(i).getName());

		HBox hbDepartments = new HBox();
		hbDepartments.getChildren().addAll(lblDepartment, cmbDepartments);
		hbDepartments.setSpacing(11);

		Label lblStartWork = new Label("Choose the start hour:");

		cmbStartWorkDepartment.setValue(8);
		hbStartWork.getChildren().clear();
		hbStartWork.getChildren().addAll(lblStartWork, cmbStartWorkDepartment);
		hbStartWork.setVisible(true);
		hbStartWork.setSpacing(60);

		Label lblCanFromHome = new Label("Check if employee can work\nfrom home:");

		cbWorksFromHome.setSelected(false);

		HBox hbCanFromHome = new HBox();
		hbCanFromHome.getChildren().addAll(lblCanFromHome, cbWorksFromHome);
		hbCanFromHome.setSpacing(30);

		btDoneChangingDepartmentPractice.setDisable(true);
		hbDoneBack.getChildren().add(btDoneChangingDepartmentPractice);

		allTogether.getChildren().addAll(changingDepartmentPractive, hbDepartments, hbStartWork, hbCanFromHome,
				hbDoneBack);
		allTogether.setSpacing(20);

		vbCenter.getChildren().addAll(allTogether);
		vbCenter.setAlignment(Pos.CENTER);
	}

	public boolean checkingCmbDepartment() {
		if (cmbDepartments.getSelectionModel().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public int getCmbChangeStartWork() {
		return cmbStartWorkDepartment.getValue();
	}

	public void setBtChangingDepartmentPracticeAvaliable(boolean t) {
		btDoneChangingDepartmentPractice.setDisable(!t);
	}

	public void addEventToDoneChangingDepartmentPracticeButton(EventHandler<ActionEvent> event) {
		btDoneChangingDepartmentPractice.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Changing role practice
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void changingRolePractice(ArrayList<Department> departments) { // CHANGED - 31.7
		vbCenter.getChildren().clear();

		Label changingRolePractive = new Label("Changing Role Practice");
		changingRolePractive.setFont(Font.font("Castellar", 20));

		VBox allTogether = new VBox();

		Label lblDepartment = new Label("Enter the assigned department:");

		cmbDepartments.getItems().clear();
		int size = departments.size();
		for (int i = 0; i < size; i++)
			if (departments.get(i).getPracticeCanBeChanged() == true && departments.get(i).getSynced() == false)
				cmbDepartments.getItems().add(departments.get(i).getName());

		HBox hbDepartments = new HBox();
		hbDepartments.getChildren().addAll(lblDepartment, cmbDepartments);
		hbDepartments.setSpacing(11);

		lblSelectDepartmentNeeded.setVisible(true);

		hbRolesForChange = new HBox();
		txt = new Text("Roles list: ");
		cmbRoles.getItems().clear(); // CHANGED - 31.7
		cmbRoles.setDisable(true); // CHANGED - 31.7
		hbRolesForChange.getChildren().addAll(txt, cmbRoles, lblSelectDepartmentNeeded); // CHANGED - 31.7

		hbRolesForChange.setSpacing(125);

		Label lblStartWork = new Label("Choose the start hour:");

		cmbStartWorkDepartment.setValue(8);
		hbStartWork.getChildren().clear();
		hbStartWork.getChildren().addAll(lblStartWork, cmbStartWorkDepartment);
		hbStartWork.setVisible(true);
		hbStartWork.setSpacing(60);

		Label lblCanFromHome = new Label("Check if employee can work\nfrom home:");

		cbWorksFromHome.setSelected(false);
		lblCantChangePractice.setVisible(false);

		hbChangeCanFromHomeRole = new HBox();
		hbChangeCanFromHomeRole.getChildren().addAll(lblCanFromHome, cbWorksFromHome, lblCantChangePractice);
		hbChangeCanFromHomeRole.setSpacing(30);
		hbChangeCanFromHomeRole.setDisable(true);

		btDoneChangingRolePractice.setDisable(true);
		hbDoneBack.getChildren().add(btDoneChangingRolePractice);

		allTogether.getChildren().addAll(changingRolePractive, hbDepartments, hbRolesForChange, hbStartWork,
				hbChangeCanFromHomeRole, hbDoneBack);
		allTogether.setSpacing(20);

		vbCenter.getChildren().addAll(allTogether);
		vbCenter.setAlignment(Pos.CENTER);
	}

	public void getAllDepartmenChangeableRoles(ArrayList<Role> roles) {
		cmbRoles.setDisable(false);
		cmbRoles.getItems().clear();
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).getPracticeCanBeChanged() == true) {
				cmbRoles.getItems().add((roles.get(i)));
			}
		}

		lblSelectDepartmentNeeded.setVisible(false);
	}

	public void setChangeCanFromHomeRoleAvaliable(boolean t) {
		hbChangeCanFromHomeRole.setDisable(!t);
		lblCantChangePractice.setVisible(!t);
	}

	public void setBtChangingRolePracticeAvaliable(boolean t) {
		btDoneChangingRolePractice.setDisable(!t);
	}

	public void addEventToDoneChangingRolePracticeButton(EventHandler<ActionEvent> event) {
		btDoneChangingRolePractice.setOnAction(event);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Show company info
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public String showCompanyProfit() {
		Label changingDepartmentPractive = new Label("Company Info");
		changingDepartmentPractive.setFont(Font.font("Castellar", 20));
		vbCenter.getChildren().clear();
		txt = new Text(companyInformation);
		vbCenter.getChildren().addAll(changingDepartmentPractive, txt, hbDoneBack);
		return null;
	}

	public void setCompanyInfo(String information) {
		companyInformation = information;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void ending() {
		vbCenter.getChildren().clear();
		hbSaveData = new HBox();
		hbSaveData.getChildren().addAll(btSaveData, btDoNotSaveData);
		hbSaveData.setAlignment(Pos.CENTER);
		vbCenter.getChildren().addAll(lblSavingData, hbSaveData);
	}

	public void addEventToSaveData(EventHandler<ActionEvent> event) {
		btSaveData.setOnAction(event);
	}

	public void addEventToNotSaveData(EventHandler<ActionEvent> event) {
		btDoNotSaveData.setOnAction(event);
	}

	public void exitJavafxWindow() {
		showMessage("ByeBye");
		globalStage.close();
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
