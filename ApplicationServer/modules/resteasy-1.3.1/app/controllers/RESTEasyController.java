package controllers;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import play.Logger;
import play.Play;
import play.libs.IO;
import play.modules.resteasy.RESTEasyPlugin;
import play.modules.resteasy.RESTEasyRequestWrapper;
import play.modules.resteasy.RESTEasyResponseWrapper;
import play.mvc.Before;
import play.mvc.Controller;
import utils.StringUtil;

public class RESTEasyController extends Controller {

	@Before
	public static void checkAuthentification() {		
//		MediaType mediaT1 = MediaType.valueOf(request.current().contentType);
//		MediaType mediaT2 = MediaType.valueOf(request.current().headers.get("content-type").value());
//		Logger.debug("Media Type 1: %s", mediaT1.getParameters().get("boundary"));
//		Logger.debug("Media Type 2: %s", mediaT2.getParameters().get("boundary"));
//		Logger.debug("Content-Type: %s", request.current().contentType);
//		Logger.debug("Content-Type: %s", request.current().headers.get("content-type").value());
//		Logger.debug("Body:\n %s", IO.readContentAsString(request.current().body));
		if (!isAuthorized()) {
			// error msg
			unauthorized();
			// renderText("Application is not authorized.");
		}
	}

	/**
	 * @param authHeader
	 * @return
	 */
	private static boolean isAuthorized() {
		if (request.current().headers.get("authorization") == null
			|| request.current().headers.get("authorization").value() == null)
			return false;
		String authHeader = request.current().headers.get("authorization").value();
		if (StringUtil.isEmptyOrWhitespace(authHeader))
			return false;
		else {
			if (authHeader.equals("DopJ7gQ9NqGek7sQRcRwmBlH4DwGem")) {
				return true;
			}
		}
		return false;
	}

	public static void serve() {
		Logger.info("RESTEasy controller invoked: %s %s", request.method, request.url);
		RESTEasyPlugin plugin = Play.plugin(RESTEasyPlugin.class);
		Dispatcher dispatcher = plugin.deployment.getDispatcher();
		ResteasyProviderFactory factory = plugin.deployment.getProviderFactory();
		HttpRequest restReq = new RESTEasyRequestWrapper(request, plugin.path);
		HttpResponse restRep = new RESTEasyResponseWrapper(request, response, factory);
		dispatcher.invoke(restReq, restRep);
	}
}
