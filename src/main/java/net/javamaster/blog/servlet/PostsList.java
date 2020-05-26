package net.javamaster.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Post;
import net.javamaster.blog.model.Tag;

@WebServlet({ "/posts" })
public class PostsList extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Post> allPosts = SQLClass.getAllPosts();
		List<List<Tag>> tags = new ArrayList<>();
		for (int i = 0; i < allPosts.size(); i += 1) {
			Post post = allPosts.get(i);
			List<Tag> subTags = new ArrayList<>();
			subTags = SQLClass.getTagsByPost(post.getSlug());
			tags.add(subTags);
		}
		request.setAttribute("posts", allPosts);
		request.setAttribute("tags", tags);

		request.setAttribute("currentPage", "page/posts_list.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}
}
