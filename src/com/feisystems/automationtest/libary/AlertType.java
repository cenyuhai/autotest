package com.feisystems.automationtest.libary;

public enum AlertType {
	
	 RecordApproved("Record has been approved"),
	
	 RecordActivated("Record has been activated"),
	 
	 RecordArchived("Record has been archived"),
	 
	 RecordCloned ( "Record has been cloned"),
	 
	 RecordCreatedSuccessfully ( "Record has been created"),
	 
	 RecordDeactivated ( "Record has been deactivated"),
	 
	 RecordDeletedSuccessfully ( "Record has been deleted"),
	 
	 RecordDiscarded ( "The record has been discarded"),
	 
	 RecordHasBeenAcknowledged ( "Record has been acknowledged"),
	 
	 RecordHasBeenSubmitted ( "Record has been submitted"),
	 
	 RecordOnHold ( "Record has been put on hold"),
	 
	 RecordPublished ( "Record has been published"),
	 
	 RecordResumed ( "Record has been resumed"),
	 
	 RecordRevisedSuccessfully ( "Record has been revised"),
	 
	 RecordRequestedClarification("Record has been requested clarification"),
	 
	 RecordSavedSuccessfully ( "Record has been saved"),
	 
	 RecordUnlockedSuccessfully ( "Record has been unlocked");
	
	
	 private String message;
	 public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private AlertType(String message) {
		 this.message = message;
	 }

}
