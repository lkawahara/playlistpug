package edu.neumont.java.playlistpug;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dependencyInjector.DependencyInjector;
import interfaces.IDALpug;

/**
 * Servlet implementation class PugServlet
 */
@WebServlet("/pugs/*")
public class PugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String[] dummy = {"One","Two","Three","Four","Five"};
    private String rowOne = "";
    private String rowTwo = "";
    private String rowThree = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PugServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDALpug dallPug = (IDALpug) DependencyInjector.getInstance().get("IDALpug");
		
		String destination = request.getPathInfo();
		String[] path = null;
		String error = "You done goofed.";
		request.setAttribute("error", error);
		if(request.getPathInfo() == null || request.getPathInfo().equals("")){
			destination = "/WEB-INF/index.jsp";
		}else{
			path = request.getPathInfo().substring(1).split("/");
			
			if(destination.equals("/") || path[0].equals("index") || path[0].equals("main") || path[0].equals("home")){
				destination = "/WEB-INF/index.jsp";
			}else if(path[0].equals("upload")){
				destination = "/WEB-INF/upload.jsp";
			}else if(path[0].equals("song")){
				//set attributes name as the name of the song and
				//song as whatever is needed to play the song
				request.setAttribute("name", "song name");
				request.setAttribute("song", "song data");
				request.setAttribute("sid", 1);
				destination = "/WEB-INF/song.jsp";
			}else if(path[0].equals("search")){
				//depends on how we decide to do the searching
				//may set attribute to search terms or list of search results
				getResults();
				request.setAttribute("rowOne", rowOne);
				request.setAttribute("rowTwo", rowTwo);
				request.setAttribute("rowThree", rowThree);
				destination = "/WEB-INF/search.jsp";
			}else{
				destination = "/WEB-INF/error.jsp";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = "";
		String p = request.getPathInfo().substring(1);
		String[] path = p.split("/");	

	}
	private void getResults(){
		int rounds = dummy.length / 3;
		if(dummy.length % 3 == 0){
			for(int i = 0; i < rounds; i++){
				rowOne += "<a href='/pugs/home'>" + dummy[i] + "</a>";
			}
			for(int i = 0; i < rounds; i++){
				rowTwo += "<a href='/pugs/home'>" + dummy[rounds + i] + "</a>";
			}
			for(int i = 0; i < rounds; i++){
				rowThree += "<a href='/pugs/home'>" + dummy[(rounds * 2) + i] + "</a>";
			}
		}else if(dummy.length % 3 == 1){
			for(int i = 0; i < rounds; i++){
				rowOne += "<a href='/pugs/home'>" + dummy[i] + "</a>";
			}
			for(int i = 0; i < rounds; i++){
				rowTwo += "<a href='/pugs/home'>" + dummy[rounds + i] + "</a>";
			}
			for(int i = 0; i < rounds; i++){
				rowThree += "<a href='/pugs/home'>" + dummy[(rounds * 2) + i] + "</a>";
			}
			rowOne += "<a href='/pugs/home'>" + dummy[dummy.length - 1] + "</a>";
		}else{
			for(int i = 0; i < rounds; i++){
				rowOne += "<a href='/pugs/home'>" + dummy[i] + "</a>";
			}
			for(int i = 0; i < rounds; i++){
				rowTwo += "<a href='/pugs/home'>" + dummy[rounds + i] + "</a>";
			}
			for(int i = 0; i < rounds; i++){
				rowThree += "<a href='/pugs/home'>" + dummy[(rounds * 2) + i] + "</a>";
			}
			rowOne += "<a href='/pugs/home'>" + dummy[dummy.length - 1] + "</a>";
			rowTwo += "<a href='/pugs/home'>" + dummy[dummy.length - 2] + "</a>";
		}
		
	}
}
