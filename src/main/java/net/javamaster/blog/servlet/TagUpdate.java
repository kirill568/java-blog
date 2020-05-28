package net.javamaster.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * @author Kirill
 * Класс отвечает за изменение тега по пути /tag/update
 */
@WebServlet({ "/tag/update" })
public class TagUpdate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String slug = request.getParameter("tag");
		Tag tag = SQLClass.getTag(slug);

		request.setAttribute("title", tag.getTitle());
		request.setAttribute("slug", tag.getSlug());
		request.setAttribute("currentPage", "page/tag_update.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}

	public Boolean tagExist(String oldSlug, String newSlug) {
		Tag oldTag = SQLClass.getTag(oldSlug);
		Tag newTag = SQLClass.getTag(newSlug);
		Boolean tagExist = false;
		if (newTag == null) {
			return tagExist;
		}

		if (oldTag.getId() != newTag.getId()) {
			tagExist = true;
		}
		return tagExist;
	}

	public void updateTag(String oldSlug, String title, String newSlug) {
		List<Post> posts = SQLClass.getPostsByTag(oldSlug);
		ArrayList<String> cols = new ArrayList<>();
		cols.add("title");
		cols.add("slug");

		ArrayList<String> vals = new ArrayList<>();
		vals.add(title);
		vals.add(newSlug);

		SQLClass.deleteBoundsTagsWithPosts(oldSlug);
		SQLClass.updateTag(cols, vals, oldSlug);
		if (posts != null) {
			for (int i = 0; i < posts.size(); i += 1) {
				Post post = posts.get(i);
				SQLClass.makeBoundPostTag(post.getSlug(), newSlug);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String slug = request.getParameter("tag");
		String title = request.getParameter("title");
		String newSlug = request.getParameter("slug");
		Boolean tagExist = tagExist(slug, newSlug);

		if (Tag.validate(title, newSlug, tagExist)) {
			updateTag(slug, title, newSlug);
			response.sendRedirect("/tags");
		} else {
			Map<String, ArrayList<String>> errors = Tag.getErrors(title, newSlug, tagExist);

			request.setAttribute("errors", errors);
			request.setAttribute("title", title);
			request.setAttribute("slug", newSlug);
			request.setAttribute("currentPage", "page/tag_update.jsp");
			request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
		}
	}
}
