

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class MoviesByActor
 */
@WebServlet("/MoviesByActor")
public class MoviesByActor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesByActor() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doGet2(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	
		JSONObject myResponse = new JSONObject();
	 	JSONArray Lugares = new JSONArray();
	 	
	 	String myContinente = request.getParameter("continente");
	 	String myClima = request.getParameter("clima");
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://44.201.21.29:7687", "neo4j", "spears-compresses-whirl" ) )
	        {
			 	LinkedList<String> paisesContinente = greeter.getLugarByContinente(myContinente);
			 	LinkedList<String> paisesClima = greeter.getLugarByClima(myClima);
			 	
			 	for (int i = 0; i < paisesContinente.size(); i++) {
			 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
			 		Lugares.add(paisesContinente.get(i));
			 	}
				 for (int i = 0; i < paisesClima.size(); i++) {
					//out.println( "<p>" + myactors.get(i) + "</p>" );
				   Lugares.add(paisesClima.get(i));
			   }
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 	myResponse.put("conteo", Lugares.size()); //Guardo la cantidad de actores
	 	myResponse.put("lugares", Lugares);
	 	out.println(myResponse);
	 	out.flush();  
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
