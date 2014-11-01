package com.pixceed.util;

import java.lang.reflect.Field;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class PixceedObjectsNamingStrategy extends PropertyNamingStrategy
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4081647196085085628L;

	private Class<?> clazz;

	public PixceedObjectsNamingStrategy(Class<?> clazz)
	{
		this.clazz = clazz;
	}

	@Override
	public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
	{
		return translate(defaultName);
	}

	@Override
	public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
	{
		return translate(defaultName);
	}

	@Override
	public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName)
	{
		return translate(defaultName);
	}

	/**
	 * Gets the name of the field in Java class and returns the String which occurs in the JSON object.
	 * 
	 * @param defaultName
	 * @return
	 */
	private String translate(String defaultName)
	{
		// if tag name is some of Java's language reserved words, it will be escaped by an underline (e.g. applies to the word "public")
		if (defaultName.startsWith("_"))
			defaultName = defaultName.substring(1);
		char[] nameChars = defaultName.toCharArray();
		StringBuilder nameTranslated =
				new StringBuilder(nameChars.length * 2);
		for (char c : nameChars)
		{
			if (Character.isUpperCase(c))
				nameTranslated.append("_");
			c = Character.toUpperCase(c);
			nameTranslated.append(c);
		}
		nameTranslated.append("_TAG");
		try
		{
			Field field = findFieldInInnerClasses(clazz, nameTranslated.toString());
			if (field == null)
				clazz.getField(nameTranslated.toString()); // force NoSuchFieldException
			String string = (String) field.get(null);
			return string;
		}
		catch (IllegalAccessException | IllegalArgumentException e)
		{
			Log.e("NAMING_STRATEGY", "Error when trying to resolve field: " + defaultName, e);
			return defaultName;
		}
		catch (NoSuchFieldException e)
		{
			return defaultName;
		}
	}

	public static Field findFieldInInnerClasses(Class<?> clazz, String nameTranslated)
	{
		try
		{
			return clazz.getField(nameTranslated);
		}
		catch (NoSuchFieldException e)
		{
			for (Class<?> innerClazz : clazz.getClasses())
			{
				Field findFieldInInnerClasses = findFieldInInnerClasses(innerClazz, nameTranslated);
				if (findFieldInInnerClasses != null)
					return findFieldInInnerClasses;
			}
			return null;
		}
	}

	public static ObjectMapper getMapper(Class<?> clazz)
	{
		return new ObjectMapper().setPropertyNamingStrategy(new PixceedObjectsNamingStrategy(clazz));
	}

}
