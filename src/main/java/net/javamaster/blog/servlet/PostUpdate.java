package net.javamaster.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Post;
import net.javamaster.blog.model.Tag;

@WebServlet({ "/post/update" })
public class PostUpdate extends HttpServlet {

	public Map<String, String> makeTagMap(List<Tag> tags) {
		Map<String, String> mapTags = new HashMap<>();
		for (int i = 0; i < tags.size(); i += 1) {
			mapTags.put(tags.get(i).getSlug(), tags.get(i).getTitle());
		}
		return mapTags;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String slug = request.getParameter("post");
		Post post = SQLClass.getPost(slug);

		List<Tag> allTags = SQLClass.getAllTags();
		Map<String, String> mapAllTags = makeTagMap(allTags);

		List<Tag> laterSelectTags = SQLClass.getTagsByPost(slug);
		Map<String, String> mapLaterSelectTags = makeTagMap(laterSelectTags);

		request.setAttribute("title", post.getTitle());
		request.setAttribute("slug", post.getSlug());
		request.setAttribute("body", post.getBody());
		request.setAttribute("mapAllTags", mapAllTags);
		request.setAttribute("mapLaterSelectTags", mapLaterSelectTags);
		request.setAttribute("currentPage", "page/post_update.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}

	public void updatePost(String oldSlug, String title, String newSlug, String body, String[] tags) {
		ArrayList<String> cols = new ArrayList<>();
		cols.add("title");
		cols.add("slug");
		cols.add("body");

		ArrayList<String> vals = new ArrayList<>();
		vals.add(title);
		vals.add(newSlug);
		vals.add(body);
		SQLClass.deleteBoundsPostWithTags(oldSlug);
		SQLClass.updatePost(cols, vals, oldSlug);
		if (tags != null) {
			for (int i = 0; i < tags.length; i += 1) {
				String tag = tags[i];
				SQLClass.makeBoundPostTag(newSlug, tag);
			}
		}
	}

	public Boolean postExist(String oldSlug, String newSlug) {
		Post oldPost = SQLClass.getPost(oldSlug);
		Post newPost = SQLClass.getPost(newSlug);
		Boolean postExist = false;
		if (newPost == null) {
			return postExist;
		}

		if (oldPost.getId() != newPost.getId()) {
			postExist = true;
		}
		return postExist;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String slug = request.getParameter("post");
		String title = request.getParameter("title");
		String newSlug = request.getParameter("slug");
		String body = request.getParameter("body");
		Boolean postExist = postExist(slug, newSlug);
		String[] selectTags = request.getParameterValues("tags");

		if (Post.validate(title, newSlug, body, postExist)) {
			updatePost(slug, title, newSlug, body, selectTags);
			response.sendRedirect("/posts");
		} else {
			Map<String, ArrayList<String>> errors = Post.getErrors(title, newSlug, body, postExist);

			List<Tag> allTags = SQLClass.getAllTags();
			Map<String, String> mapAllTags = makeTagMap(allTags);
			List<Tag> laterSelectTags = SQLClass.getTagsByPost(slug);
			Map<String, String> mapLaterSelectTags = makeTagMap(laterSelectTags);

			request.setAttribute("mapAllTags", mapAllTags);
			request.setAttribute("mapLaterSelectTags", mapLaterSelectTags);
			request.setAttribute("errors", errors);
			request.setAttribute("title", title);
			request.setAttribute("slug", newSlug);
			request.setAttribute("body", body);
			request.setAttribute("currentPage", "page/post_update.jsp");
			request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
		}
	}
}
