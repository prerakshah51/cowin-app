package com.cowin.app.service;

import com.cowin.app.db.User;

public interface CowinService {

	public String getCalendarByPincode(String pincode) throws Exception;

	public String saveUser(User user) throws Exception;

	public void sendNotificationToAll();
}
