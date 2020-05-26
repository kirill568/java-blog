package net.javamaster.blog.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tag {
	private int id;
	private String title;
	private String slug;

	public Tag(int id, String title, String slug) {
		this.id = id;
		this.title = title;
		this.slug = slug;
	}

	public Tag(String title, String slug) {
		this.title = title;
		this.slug = slug;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public static Tag convertRowToTag(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String title = myRs.getString("title");
		String slug = myRs.getString("slug");
		Tag tempTag = new Tag(id, title, slug);
		return tempTag;
	}

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