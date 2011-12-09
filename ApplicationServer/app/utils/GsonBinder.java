package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import play.data.binding.Global;
import play.data.binding.TypeBinder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Global
public class GsonBinder implements TypeBinder<JsonObject> {

	@Override
	public Object bind(String name, Annotation[] antns, String value, Class classType, Type type)
			throws Exception {
		return new JsonParser().parse(value);
	}

}