
package com.xsg.common.printer.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName:MapUtils Function:
 * 
 * @author Rick
 */
public class MapUtil {

	/**
	 * 转换对象为map
	 * 
	 * @param object
	 * @param ignore
	 * @return
	 */
	public static Map<String, String> objectToMap(Object object, String... ignore) {
		Map<String, String> tempMap = new HashMap<>();
		if (object == null) {
			return tempMap;
		}
		if (object instanceof Map) {
			Map<String, String> finalTempMap = tempMap;
			((Map) object).forEach((k, v) -> {
				if (k != null && v != null) {
					finalTempMap.put(k.toString(), v.toString());
				}
			});
			return finalTempMap;
		}
		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();
				if (fields != null && fields.length > 0) {
					if (tempMap == null)
						tempMap = new LinkedHashMap<String, String>(fields.length);
					for (Field field : fields) {
						field.setAccessible(true);

						// 排除类变量
						if (Modifier.isStatic(field.getModifiers())) {
							continue;
						}

						boolean ig = false;
						if (ignore != null && ignore.length > 0) {
							for (String i : ignore) {
								if (i.equals(field.getName())) {
									ig = true;
									break;
								}
							}
						}

						if (ig) {
							continue;
						} else {
							Object o = null;
							try {
								o = field.get(object);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							if (o != null) {
								tempMap.put(field.getName(), o.toString());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return tempMap;
	}


	/**
	 * 转换对象为map,为null的字段不加入map
	 * value为空字段不加入到map中
	 * @param object
	 * @param ignore
	 * @return
	 */
	public static Map<String, String> objectToMapNoNullValue(Object object, String... ignore) {
		Map<String, String> tempMap = new HashMap<>();
		if (object == null) {
			return tempMap;
		}
		if (object instanceof Map) {
			Map<String, String> finalTempMap = tempMap;
			((Map) object).forEach((k, v) -> {
				if (k != null && v != null) {
					finalTempMap.put(k.toString(), v.toString());
				}
			});
			return finalTempMap;
		}
		for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();
				if (fields != null && fields.length > 0) {
					if (tempMap == null)
						tempMap = new LinkedHashMap<String, String>(fields.length);
					for (Field field : fields) {
						field.setAccessible(true);

						// 排除类变量
						if (Modifier.isStatic(field.getModifiers())) {
							continue;
						}

						boolean ig = false;
						if (ignore != null && ignore.length > 0) {
							for (String i : ignore) {
								if (i.equals(field.getName())) {
									ig = true;
									break;
								}
							}
						}

						if (ig) {
							continue;
						} else {
							Object o = null;
							try {
								o = field.get(object);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							if (o != null)
							tempMap.put(field.getName(), o.toString());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return tempMap;
	}


	/**
	 * 转换对象为map
	 * 
	 * @param object
	 * @param ignore
	 * @return HashMap<String, Object>
	 */
	public static HashMap<String, Object> objectToObjectMap(Object object, String... ignore) {
		
		if (object == null) {
			return null;
		}

		HashMap<String, Object> tempMap = new LinkedHashMap<String, Object>();
		for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
			try {
				Field[] fields = clazz.getDeclaredFields();
				if (fields != null && fields.length > 0) {
					for (Field field : fields) {
						field.setAccessible(true);

						// 排除类变量
						if (Modifier.isStatic(field.getModifiers())) {
							continue;
						}

						boolean ig = false;
						if (ignore != null && ignore.length > 0) {
							for (String i : ignore) {
								if (i.equals(field.getName())) {
									ig = true;
									break;
								}
							}
						}

						if (ig) {
							continue;
						}
						Object o = null;
						try {
							o = field.get(object);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(o != null) {
							tempMap.put(field.getName(), o);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return tempMap;
	}
}
