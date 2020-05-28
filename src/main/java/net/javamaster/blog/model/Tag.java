package net.javamaster.blog.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill
 * Класс представляет сущность тег
 */
public class Tag {
	/**
	 * Поле id
	 */
	private int id;
	/**
	 * Поле загловок
	 */
	private String title;
	/**
	 * Поле слаг
	 */
	private String slug;

	/**
	 * Конструктор - создание нового тега
	 * @param id - id
	 * @param title - загловок
	 * @param slug - слаг
	 */
	public Tag(int id, String title, String slug) {
		this.id = id;
		this.title = title;
		this.slug = slug;
	}

	/**
	 * Конструктор - создание нового тега без id
	 * @param title - загловок
	 * @param slug - слаг
	 */
	public Tag(String title, String slug) {
		this.title = title;
		this.slug = slug;
	}

	/**
	 * Метод возвращает id
	 * @return - id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Метод устанавливает id
	 * @param id - id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Метод вовращает заголовок
	 * @return - заголовок
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Метод устанавливает заголовок
	 * @param title - заголовок
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Метод возвращает слаг
	 * @return - слаг
	 */
	public String getSlug() {
		return this.slug;
	}

	/**
	 * Метод устанавливает слаг
	 * @param slug - слаг
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * Метод создает объект тега, из запроса
	 * @param myRs - переменная типа ResultQuery
	 * @return - тег
	 * @throws SQLException
	 */
	public static Tag convertRowToTag(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String title = myRs.getString("title");
		String slug = myRs.getString("slug");
		Tag tempTag = new Tag(id, title, slug);
		return tempTag;
	}

	/**
	 * Метод проводит проверку тега
	 * @param title - заголовок
	 * @param slug - слаг
	 * @param postExists - наличие тега в базе данных
	 * @return - правду, если проверка прошла успешно, ложь если проверка не прошла
	 */
	public static Boolean validate(String title, String slug, Boolean tagExists) {
		Boolean validate = true;

		if (title == null || title == "" || title.length() > 150) {
			validate = false;
		}

		if (slug == null || slug == "" || slug.length() > 150 || tagExists) {
			validate = false;
		}

		return validate;
	}

	/**
	 * Метод возращает ошибки, которые возникли во время проверки
	 * @param title - заголовок
	 * @param slug - слаг
	 * @param postExists - наличие тега в базе данных
	 * @return - ассоциативный массив, ключ - название поля, значение - список ошибок
	 */
	public static Map<String, ArrayList<String>> getErrors(String title, String slug, Boolean tagExists) {
		String errorNull = "Поле не должно быть null";
		String errorEmpty = "Поле не должно быть пустым";
		String errorMaxLength = "Максимальная длина поля 150 символов";
		String errorTagExist = "Тег с таким слагом уже существует";
		Map<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();

		ArrayList<String> errorsTitle = new ArrayList<>();
		if (title == null) {
			errorsTitle.add(errorNull);
		}
		if (title == "") {
			errorsTitle.add(errorEmpty);
		}

		if (title.length() > 150) {
			errorsTitle.add(errorMaxLength);
		}

		ArrayList<String> errorsSlug = new ArrayList<>();
		if (slug == null) {
			errorsSlug.add(errorNull);
		}
		if (slug == "") {
			errorsSlug.add(errorEmpty);
		}

		if (slug.length() > 150) {
			errorsSlug.add(errorMaxLength);
		}

		if (tagExists) {
			errorsSlug.add(errorTagExist);
		}

		hashMap.put("title", errorsTitle);
		hashMap.put("slug", errorsSlug);

		return hashMap;
	}
}