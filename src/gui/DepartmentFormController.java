package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constrains;
import gui.util.utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	private Department entity;
	
	private DepartmentService service;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		try {
			if (entity == null) {
				throw new IllegalStateException("entity null");
			}
			if (service == null) {
				throw new IllegalStateException("service null");
			}
			entity = getFormData();
			service.saveOrUpdate(entity);
			
			utils.currentStage(event).close();
		
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving objext", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Department getFormData() {
		Department obj = new Department();
		obj.setId(utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		utils.currentStage(event).close();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rs) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		Constrains.setTextFieldMaxLenght(txtName, 30);
	}
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null!");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		
	}
	
	

}
