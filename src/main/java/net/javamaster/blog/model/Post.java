package net.javamaster.blog.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Post {
	private int id;
	private String title;
	private String slug;
	private String body;
	private String datePub;

	public Post(int id, String title, String slug, String body, String datePub) {
		this.id = id;
		this.title = title;
		this.slug = slug;
		this.body = body;
		this.datePub = datePub;
	}

	public Post(String title, String slug, String body, String datePub) {
		this.title = title;
		this.slug = slug;
		this.body = body;
		this.datePub = datePub;
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

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDatePub() {
		return this.datePub;
	}

	public void setDatePub(String datePub) {
		this.datePub = datePub;
	}

	public static Post convertRowToPost(ResultSet myRs) throws SQLException {
		int id = myRs.getInt("id");
		String title = myRs.getString("title");
		String slug = myRs.getString("slug");
		String body = myRs.getString("body");
		String datePub = myRs.getString("datePub");
		Post tempPost = new Post(id, title, slug, body, datePub);
		return tempPost;
	}

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
	
	public static void main(String[] args) {
		String title = "1";
		String slug = "kirill";
		String body = null;
		Boolean postExists = true;
		Boolean validate = validate(title, slug, body, postExists);
		System.out.println(validate);
		Map<String, ArrayList<String>> errors = getErrors(title, slug, body, postExists);
		System.out.println(errors.get("slug"));
	}

}
