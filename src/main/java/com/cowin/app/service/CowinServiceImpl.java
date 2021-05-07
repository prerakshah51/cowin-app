package com.cowin.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.cowin.app.db.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

@Singleton
public class CowinServiceImpl implements CowinService {

	@Inject
	public Provider<EntityManager> emp;

	public final int HTTP_OK = 200;
	public final String COWIN_API_URL_BASE = "https://cdn-api.co-vin.in/api/v2";
	public final String CURRENT_DATE = DateTimeFormatter.ofPattern("dd-MM-YYYY").format(LocalDate.now());

	@Override
	@Transactional
	public String saveUser(User user) throws Exception {

		EntityManager em = emp.get();
		em.persist(user);

		return getCalendarByPincode(user.getPincode().toString());
	}

	@Override
	public String getCalendarByPincode(String pincode) throws Exception {

		URIBuilder uriBuilder = new URIBuilder(COWIN_API_URL_BASE + "/appointment/sessions/public/calendarByPin");
		uriBuilder.addParameter("pincode", pincode);
		uriBuilder.addParameter("date", CURRENT_DATE);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		CloseableHttpResponse response = httpClient.execute(httpGet);

		if (response.getStatusLine().getStatusCode() != HTTP_OK) {
			throw new Exception(String.valueOf(response.getStatusLine()));
		}

		return EntityUtils.toString(response.getEntity());
	}

	@Override
	@SuppressWarnings("unchecked")
	public void sendNotificationToAll() {

		List<User> userList = emp.get().createNamedQuery("findAllUser").getResultList();

		if (userList != null && !userList.isEmpty()) {

			for (User user : userList) {

				try {
					System.err.println(getCalendarByPincode(user.getPincode().toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
