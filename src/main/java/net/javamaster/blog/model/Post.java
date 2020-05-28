package net.javamaster.blog.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill
 * Класс представляет сущность пост
 */
public class Post {
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
	 * Поле тело поста
	 */
	private String body;
	/**
	 * поле дата публикации
	 */
	private String datePub;

	/**
	 * Конструктор - создание нового поста
	 * @param id - id
	 * @param title - загловок
	 * @param slug - слаг
	 * @param body - тело поста
	 * @param datePub - дата публикации
	 */
	public Post(int id, String title, String slug, String body, String datePub) {
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.body = body;
		this.datePub = datePub;
	}

	/**
	 * Конструктор - создание нового поста без id
	 * @param title - загловок
	 * @param slug - слаг
	 * @param body - тело поста
	 * @param datePub - дата публикации
	 */
	public Post(String title, String slug, String body, String datePub) {
		this.title = title;
		this.slug = slug;
		this.body = body;
		this.datePub = datePub;
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
	 * Метод возвращает тело поста
	 * @return - тело поста
	 */
	public String getBody() {
		return this.body;
	}

	/**
	 * Метод устанавливает тело поста
	 * @param body - тело поста
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Метод возвращает дату публикации
	 * @return - дата публикации
	 */
	public String getDatePub() {
		return this.datePub;
	}

	/**
	 * Метод устанавливает дату публикации
	 * @param datePub - дата публикации
	 */
	public void setDatePub(String datePub) {
		this.datePub = datePub;
	}

	/**
	 * Метод создает объект поста, из запроса
	 * @param myRs - переменная типа ResultQuery
	 * @return - пост
	 * @throws SQLException
	 */
	public static Post convertRowToPost(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String title = myRs.getString("title");
		String slug = myRs.getString("slug");
		String body = myRs.getString("body");
		String datePub = myRs.getString("datePub");
		Post tempPost = new Post(id, title, slug, body, datePub);
		return tempPost;
	}

	/**
	 * Метод проводит проверку поста
	 * @param title - заголовок
	 * @param slug - слаг
	 * @param body - тело поста
	 * @param postExists - наличие поста в базе данных
	 * @return - правду, если проверка прошла успешно, ложь если проверка не прошла
	 */
	public static Boolean validate(String title, String slug, String body, Boolean postExists) {
		Boolean validate = true;

		if (title == null || title == "" || title.length() > 150) {
			validate = false;
		}

		if (slug == null || slug == "" || slug.length() > 150 || postExists) {
			validate = false;
		}

		if (body == null || body == "") {
			validate = false;
		}

		return validate;
	}

	/**
	 * Метод возращает ошибки, которые возникли во время проверки
	 * @param title - заголовок
	 * @param slug - слаг
	 * @param body - тело поста
	 * @param postExists - наличие поста в базе данных
	 * @return - ассоциативный массив, ключ - название поля, значение - список ошибок
	 */
	public static Map<String, ArrayList<String>> getErrors(String title, String slug, String body, Boolean postExists) {
		String errorNull = "Поле не должно быть null";
		String errorEmpty = "Поле не должно быть пустым";
		String errorMaxLength = "Максимальная длина поля 150 символов";
		String errorPostExist = "Пост с таким слагом уже существует";
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

		if (postExists) {
			errorsSlug.add(errorPostExist);
		}

		ArrayList<String> errorsBody = new ArrayList<>();
		if (body == null) {
			errorsBody.add(errorNull);
		}
		if (body == "") {
			errorsBody.add(errorEmpty);
		}

		hashMap.put("title", errorsTitle);
		hashMap.put("slug", errorsSlug);
		hashMap.put("body", errorsBody);

		return hashMap;
	}
	
}
