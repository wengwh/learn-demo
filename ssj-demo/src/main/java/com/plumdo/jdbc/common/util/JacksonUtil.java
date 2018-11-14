package com.plumdo.jdbc.common.util;

import com.plumdo.jdbc.common.exception.ConvertException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wengwenhui
 *
 */
public class JacksonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	private JacksonUtil() {
	}

	/**
	 * 将对象转成Json
	 * 
	 * @param object
	 *            对象
	 * @return String
	 */
	public static String object2Json(Object object) {
		try {
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new ConvertException("将对象转成Json异常", e);
		}
	}

	/**
	 * 将Json转成map
	 * 
	 * @param json
	 *            String
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String json) {
		try {
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			return mapper.readValue(json, Map.class);
		} catch (Exception e) {
			throw new ConvertException("将Json转成map异常", e);
		}
	}

	/**
	 * 将Json转成map
	 * 
	 * @param json
	 *            String
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> json2List(String json) {
		try {
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			return mapper.readValue(json, List.class);
		} catch (Exception e) {
			throw new ConvertException("将Json转成list异常", e);
		}
	}

	/**
	 * 将对象转成Json
	 * 
	 * @param object
	 *            对象
	 * @return String
	 */
	public static String object2JsonGBK(Object object) {
		try {
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new ConvertException("将对象转成Json异常", e);
		}
	}
}
