package com.ssafy.nyamnyamcoach.util;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class JsonUtil {
	static Gson gson = new Gson();
	
	// serialize any object/list to a JSON string
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	// deserialize a JSON array string into a List<T>
	// type example: new TypeToken<List<Post>>(){}.getType()
	public static <T> List<T> fromJsonList(String json, Type type) {
		Type postListType = new TypeToken<List<T>>() {}.getType();
		List<T> li = gson.fromJson(json, postListType);
		return li;
	}
}