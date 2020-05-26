package net.javamaster.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.javamaster.blog.model.Post;
import net.javamaster.blog.model.Tag;

public class SQLClass {
	private static final String url = "jdbc:mysql://localhost:3306/blog?useSSL=false";
    private static final String user = "root";
    private static final String password = "";

    static Connection con = null;
    static ResultSet rs = null;
    static PreparedStatement prepStmt = null;
    static Statement stmt = null;

	public static void Conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		} catch (ClassNotFoundException e) { //нет класса
			e.printStackTrace();
		}
	}

	public static void close(Connection con, Statement stmt) {
		try {
			con.close();
			stmt.close();
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
	}

	public static ResultSet querySelect(ArrayList<String> param, String query) {
		try {
			if (param.size() == 0) {
				rs = stmt.executeQuery(query);
			} else {
				int count = 1;
				PreparedStatement prepStmt = con.prepareStatement(query);
				for (int i = 0; i < param.size(); i+=1) {
					prepStmt.setString(count, param.get(i));
					count += 1;
				}		
				rs = prepStmt.executeQuery();
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		return rs;
	}

	public static List<Post> getAllPosts() {
		Conn();
		List<Post> list = new ArrayList<>();
		String queryString = "SELECT * FROM post ORDER BY datePub DESC;";
		ArrayList<String> queryParam = new ArrayList<>();
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			while (returnQuery.next()) {
				Post tempPost = Post.convertRowToPost(returnQuery);
				list.add(tempPost);
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return list;
	}

	public static List<Tag> getAllTags() {
		Conn();
		List<Tag> list = new ArrayList<>();
		String queryString = "SELECT * FROM tag;";
		ArrayList<String> queryParam = new ArrayList<>();
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			while (returnQuery.next()) {
				Tag tempTag = Tag.convertRowToTag(returnQuery);
				list.add(tempTag);
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return list;
	}

	public static Post getPost(String slug) {
		Conn();
		Post post = null;
		String queryString = "SELECT * FROM post WHERE slug = ?";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slug);
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			while (returnQuery.next()) {
				post = Post.convertRowToPost(rs);
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return post;
	}

	public static Tag getTag(String slug) {
		Conn();
		Tag tag = null;
		String queryString = "SELECT * FROM tag WHERE slug = ?";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slug);
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			while (returnQuery.next()) {
				tag = Tag.convertRowToTag(rs);
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return tag;
	}

	public static Integer queryChange(ArrayList<String> param, String query) {
		Integer rows = null;
		try {
			int count = 1;
			prepStmt = con.prepareStatement(query);
			for (int i = 0; i < param.size(); i+=1) {
				prepStmt.setString(count, param.get(i));
				count += 1;
			}		
			rows = prepStmt.executeUpdate();
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		return rows;
	}

	public static Integer insertPost(Post obj) {
		Conn();
		String title = obj.getTitle();
		String slug = obj.getSlug();
		String body = obj.getBody();
		String datePub = obj.getDatePub();

		ArrayList<String> list = new ArrayList<>();
		list.add(title);
		list.add(slug);
		list.add(body);
		list.add(datePub);

		String queryString = "INSERT INTO post (title, slug, body, datePub) VALUES (?, ?, ?, ?)";
		int rows = queryChange(list, queryString);
		close(con, stmt);
		return rows;
	}

	public static Integer insertTag(Tag obj) {
		Conn();
		String title = obj.getTitle();
		String slug = obj.getSlug();

		ArrayList<String> list = new ArrayList<>();
		list.add(title);
		list.add(slug);

		String queryString = "INSERT INTO tag (title, slug) VALUES (?, ?)";
		int rows = queryChange(list, queryString);
		close(con, stmt);
		return rows;
	}

	public static Integer updateOneCol(String column, String newValue, String tableName, String slug) {
		Conn();
		ArrayList<String> list = new ArrayList<>();
		String queryString = null;
		list.add(newValue);
		list.add(slug);
		if (tableName == "post") {
			queryString = "UPDATE post SET "+ column +" = ? WHERE slug = ?";
		} else if (tableName == "tag") {
			queryString = "UPDATE tag SET "+ column +" = ? WHERE slug = ?";
		}		
		int row = queryChange(list, queryString);
		close(con, stmt);
		return row;
	}

	public static Integer updatePost(ArrayList<String> columns, ArrayList<String> newValues, String slug) {
		Integer rowsUpdate = 0;
		String table = "post";
		for (int i = 0; i < columns.size(); i+=1) {
			Integer row = updateOneCol(columns.get(i), newValues.get(i), table, slug);
			rowsUpdate += 1;
		}
		return rowsUpdate;
	}

	public static Integer updateTag(ArrayList<String> columns, ArrayList<String> newValues, String slug) {
		Integer rowsUpdate = 0;
		String table = "tag";
		for (int i = 0; i < columns.size(); i+=1) {
			Integer row = updateOneCol(columns.get(i), newValues.get(i), table, slug);
			rowsUpdate += 1;
		}
		return rowsUpdate;
	}

	public static Integer deletePost(String slug) {
		Conn();
		ArrayList<String> list = new ArrayList<>();
		list.add(slug);

		String queryString = "DELETE FROM post WHERE slug = ?";
		int rows = queryChange(list, queryString);
		close(con, stmt);
		return rows;
	}

	public static Integer deleteTag(String slug) {
		Conn();
		ArrayList<String> list = new ArrayList<>();
		list.add(slug);

		String queryString = "DELETE FROM tag WHERE slug = ?";
		int rows = queryChange(list, queryString);
		close(con, stmt);
		return rows;
	}

	public static Integer makeBoundPostTag(String slugPost, String slugTag) {
		Conn();

		ArrayList<String> list = new ArrayList<>();
		list.add(slugPost);
		list.add(slugTag);

		String queryString = "INSERT INTO postTag (slugPost, slugTag) VALUES (?, ?)";
		int row = queryChange(list, queryString);

		close(con, stmt);
		return row;
	}

	public static List<Post> getPostsByTag(String slugTag) {
		Conn();
		List<Post> list = new ArrayList<>();
		String queryString = "SELECT post.id, post.title, post.slug, post.body, post.datePub FROM post JOIN posttag ON post.slug = posttag.slugPost WHERE posttag.slugTag = ? ORDER BY post.datePub DESC";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slugTag);
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			while (returnQuery.next()) {
				Post tempPost = Post.convertRowToPost(returnQuery);
				list.add(tempPost);
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return list;
	}

	public static List<Tag> getTagsByPost(String slugPost) {
		Conn();
		List<Tag> list = new ArrayList<>();
		String queryString = "SELECT tag.id, tag.title, tag.slug FROM tag JOIN posttag ON tag.slug = posttag.slugTag WHERE posttag.slugPost = ?";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slugPost);
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			while (returnQuery.next()) {
				Tag tempPost = Tag.convertRowToTag(returnQuery);
				list.add(tempPost);
			}
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return list;
	}

	public static Integer deleteBoundsPostWithTags(String slugPost) {
		Conn();
		String queryString = "DELETE FROM posttag WHERE slugPost=?";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slugPost);

		int rows = queryChange(queryParam, queryString);
		close(con, stmt);
		return rows;
	}

	public static Integer deleteBoundsTagsWithPosts(String slugTag) {
		Conn();
		String queryString = "DELETE FROM posttag WHERE slugTag=?";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slugTag);

		int rows = queryChange(queryParam, queryString);
		close(con, stmt);
		return rows;
	}

	public static Boolean boundExist(String slugPost, String slugTag) {
		Conn();
		Boolean boundExist = false;
		String queryString = "SELECT COUNT(*) as rw FROM posttag WHERE slugPost=? AND slugTag=?";
		ArrayList<String> queryParam = new ArrayList<>();
		queryParam.add(slugPost);
		queryParam.add(slugTag);
		ResultSet returnQuery = querySelect(queryParam, queryString);
		try {
			returnQuery.next();
			int rows = returnQuery.getInt("rw");
			if (rows > 0) {
				boundExist = true;
			} 
		} catch (java.sql.SQLException e) {
		  	e.printStackTrace();
		}
		close(con, stmt);
		return boundExist;
	}

}
