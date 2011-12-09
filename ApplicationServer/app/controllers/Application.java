package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import play.Logger;
import play.Play;
import play.mvc.Controller;
import rest.conf.ClientFactory;
import rest.dto.Data;
import rest.interfaces.UserAPI;

public class Application extends Controller {

	public static void test() {
		String url = Play.configuration.getProperty("server.url") + "rest";
		// private static final String url = "localhost:3010/rest";
		ClientFactory clientFactory = new ClientFactory(url);
		UserAPI userAPI = clientFactory.createProxy(UserAPI.class);

		List<Data> dataList = userAPI.getData();
		Logger.debug("Pages no: %s", dataList.size());
		index();
	}
	
	public static void blogger() {
		String url = Play.configuration.getProperty("server.url") + "rest";
		// private static final String url = "localhost:3010/rest";
		ClientFactory clientFactory = new ClientFactory(url);
		UserAPI userAPI = clientFactory.createProxy(UserAPI.class);

		List<Data> dataList = userAPI.getData();
		Logger.debug("Pages no: %s", dataList.size());
		index();
	}

	public static void index() {
		String dateS = "12-08-2001";
		// DateFormat dF = DateFormat.getDateInstance(DateFormat.MEDIUM);
		DateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
		Date dateO;
		try {
			dateO = dF.parse(dateS);
			System.out.println(dateO);
			System.out.println(dF.format(dateO));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render();
	}

}