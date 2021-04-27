package edu.bit.hcm.user.registration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import edu.bit.hcm.UserDTO;
import edu.bit.hcm.framework.service.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserManagementController implements Controller, Initializable {

	private FXMLLoader loader;

	@FXML
	private TableView<UserAccount> userTable;

	@FXML
	private TableColumn<UserAccount, String> clmUserMngUserID;

	@FXML
	private TableColumn<UserAccount, String> clmUserMngUsername;

	@FXML
	private TableColumn<UserAccount, String> clmUserMngRole;

	@FXML
	private TableColumn<UserAccount, String> clmUserMngStatus;
	@FXML
	private TableColumn<UserAccount, String> clmCreatedDate;

	@FXML
	private RadioButton accountActiveRadioButton;

	@FXML
	private RadioButton accountDisableRadioButton;

	@FXML
	private ComboBox<UserRole> cmb_userRole;

	@FXML
	private TextField txt_user_id;

	@FXML
	private TextField txt_user_name;

	@FXML
	private TextField txt_password;

	private ToggleGroup accountStatusToggleGroup;

	final ObservableList<UserAccount> tableData = FXCollections.observableArrayList();

	private UserDTO selectedUserDTO = null;

	public UserManagementController() {
		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/user_management/user_management.fxml"));

		accountStatusToggleGroup = new ToggleGroup();
	}

	@Override
	public Scene getScene() throws IOException {

		AnchorPane anchorPane = loader.<AnchorPane>load();
		return new Scene(anchorPane);
	}

	@FXML
	public void addRow(ActionEvent event) {
		// tableData.add(new UserAccount("1", "Harsha 2", "Admin 2", "True"));
	}

	private class UserRole {
		private int userRoleId;
		private String userRole;

		public UserRole(int userRoleId, String userRole) {
			super();
			this.userRoleId = userRoleId;
			this.userRole = userRole;
		}

		public int getUserRoleId() {
			return userRoleId;
		}

		public void setUserRoleId(int userRoleId) {
			this.userRoleId = userRoleId;
		}

		public String getUserRole() {
			return userRole;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}

		@Override
		public String toString() {
			return this.userRole;
		}

	}

	@FXML
	public void deleteUser() {
		UserConnector connector = new UserConnector();
		if (null != selectedUserDTO) {

			try {
				connector.deleteUser(selectedUserDTO);
				clearForm();
				loadUserAccounts(userTable);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void createNewUser() {
		UserConnector connector = new UserConnector();
		UserDTO userDTO = new UserDTO();

		if (null != selectedUserDTO) {
			userDTO.setUserId(selectedUserDTO.getUserId());
		}

		if (!txt_password.getText().isEmpty()) {
			userDTO.setPassword(txt_password.getText());
		}
		
		userDTO.setUsername(txt_user_name.getText());

		userDTO.setTimeStamp(new Date().getTime());
		if (accountActiveRadioButton.isSelected()) {
			userDTO.setActive(true);
		} else {
			userDTO.setActive(false);
		}

		userDTO.setUserRoleId(cmb_userRole.getSelectionModel().getSelectedItem().userRoleId);

		try {
			connector.createNewUser(userDTO);

			clearForm();

			loadUserAccounts(userTable);

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clearUserForm() {
		clearForm();
	}
	
	private void clearForm() {
		txt_user_name.clear();
		txt_password.clear();
		accountActiveRadioButton.setSelected(true);
		cmb_userRole.getSelectionModel().select(0);
		selectedUserDTO = null;
	}

	@FXML
	public void onUserAccountTableClicked(MouseEvent event) {
		if (event.getClickCount() == 2 && userTable.getSelectionModel().getSelectedItem() != null) {
			UserAccount selectedUserAccount = userTable.getSelectionModel().getSelectedItem();
			selectedUserDTO = selectedUserAccount.getUserDTO();
			txt_user_name.setText(selectedUserAccount.getUserDTO().getUsername());
			cmb_userRole.getSelectionModel().select(selectedUserAccount.getUserDTO().getUserRoleId() - 1);
			if (selectedUserAccount.getUserDTO().isActive()) {
				accountActiveRadioButton.setSelected(true);
			} else {
				accountDisableRadioButton.setSelected(true);
			}
		}
	}

	private class UserAccount {
		private StringProperty clmUserMngUserID, clmUserMngUsername, clmUserMngRole, clmUserMngStatus, createdDate;
		private UserDTO userDTO;

		@SuppressWarnings("unused")
		public UserAccount(String clmUserMngUserID, String clmUserMngUsername, String clmUserMngRole,
				String clmUserMngStatus) {
			super();
			this.clmUserMngUserID = new SimpleStringProperty(clmUserMngUserID);
			this.clmUserMngUsername = new SimpleStringProperty(clmUserMngUsername);
			this.clmUserMngRole = new SimpleStringProperty(clmUserMngRole);
			this.clmUserMngStatus = new SimpleStringProperty(clmUserMngStatus);
			this.createdDate = new SimpleStringProperty("");
		}

		@SuppressWarnings("unused")
		public UserAccount(StringProperty clmUserMngUserID, StringProperty clmUserMngUsername,
				StringProperty clmUserMngRole, StringProperty clmUserMngStatus, StringProperty createdDate,
				UserDTO userDTO) {
			super();
			this.clmUserMngUserID = clmUserMngUserID;
			this.clmUserMngUsername = clmUserMngUsername;
			this.clmUserMngRole = clmUserMngRole;
			this.clmUserMngStatus = clmUserMngStatus;
			this.createdDate = createdDate;
			this.userDTO = userDTO;
		}

		public StringProperty getClmUserMngUserID() {
			return clmUserMngUserID;
		}

		public void setClmUserMngUserID(String clmUserMngUserID) {
			this.clmUserMngUserID.set(clmUserMngUserID);
		}

		public StringProperty getClmUserMngUsername() {
			return clmUserMngUsername;
		}

		public void setClmUserMngUsername(String clmUserMngUsername) {
			this.clmUserMngUsername.set(clmUserMngUsername);
		}

		public StringProperty getClmUserMngRole() {
			return clmUserMngRole;
		}

		public void setClmUserMngRole(String clmUserMngRole) {
			this.clmUserMngRole.set(clmUserMngRole);
		}

		public StringProperty getClmUserMngStatus() {
			return clmUserMngStatus;
		}

		public void setClmUserMngStatus(String clmUserMngStatus) {
			this.clmUserMngStatus.set(clmUserMngStatus);
		}

		public void setUserDTO(UserDTO userDTO) {
			this.userDTO = userDTO;
		}

		public UserDTO getUserDTO() {
			return userDTO;
		}

		public StringProperty getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate.set(new SimpleDateFormat("yyyy/MM/dd").format(createdDate));
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		accountActiveRadioButton.setToggleGroup(accountStatusToggleGroup);
		accountDisableRadioButton.setToggleGroup(accountStatusToggleGroup);
		accountActiveRadioButton.setSelected(true);

		// userTable.setItems(tableData);
		loadUserAccounts(userTable);
		clmUserMngUserID.setCellValueFactory(cellData -> cellData.getValue().getClmUserMngUserID());
		clmUserMngUsername.setCellValueFactory(cellData -> cellData.getValue().getClmUserMngUsername());
		clmUserMngRole.setCellValueFactory(cellData -> cellData.getValue().getClmUserMngRole());
		clmUserMngStatus.setCellValueFactory(cellData -> cellData.getValue().getClmUserMngStatus());
		clmCreatedDate.setCellValueFactory(cellData -> cellData.getValue().getCreatedDate());

		//
		cmb_userRole.getItems().add(new UserRole(1, "Admin"));
		cmb_userRole.getItems().add(new UserRole(2, "Doctor"));
		cmb_userRole.getItems().add(new UserRole(3, "Pharmesist"));
		cmb_userRole.getItems().add(new UserRole(4, "Lab Operator"));
		cmb_userRole.getSelectionModel().select(0);

	}

	private void loadUserAccounts(TableView<UserAccount> table) {
		tableData.clear();
		UserConnector userConnector = new UserConnector();
		try {
			List<UserDTO> userList = userConnector.getAllUsers().getUsers();

			for (UserDTO dto : userList) {
				UserAccount ua = new UserAccount(dto.getUserId().toString(), dto.getUsername(),
						userRoleConverter(dto.getUserRoleId()), dto.isActive() ? "Active" : "Deactive");
				ua.setCreatedDate(new Date(dto.getTimeStamp()));
				ua.setUserDTO(dto);
				tableData.add(ua);
			}

			table.setItems(tableData);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String userRoleConverter(int userRoleId) {
		switch (userRoleId) {
		case 1:
			return "Admin";
		case 2:
			return "Doctor";
		case 3:
			return "Pharmesist";
		case 4:
			return "Lab Operator";

		default:
			return null;
		}
	}

}
