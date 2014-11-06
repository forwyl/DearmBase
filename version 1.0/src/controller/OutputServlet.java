package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OutputServlet
 */
@WebServlet(name="OutputServlet", description="output handler", urlPatterns = { "/OutputServlet" })
public class OutputServlet extends HttpServlet {
	
    private static final String SESSION_ATTR_NAME = "xmlName";
    
	private static final long serialVersionUID = 1L;
	
    public OutputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/xml; charset=UTF-8");
		String fileName = "intoDream"+ UUID.randomUUID().toString() +".xml";
		res.setHeader("Content-disposition","attachment; filename="+fileName);
		HttpSession session = req.getSession();
		String xmlFileName = (String)session.getAttribute(SESSION_ATTR_NAME);
		
		createNewFile(getServletContext().getRealPath("/") + "/" + xmlFileName, res);			

	}

    private void createNewFile(String fileName, HttpServletResponse res) throws IOException{
    	
    	File file = new File(fileName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
		
		byte[] buffer = new byte[1024];
		int length = 0;
		
		while((length = bis.read(buffer)) != -1){
			bos.write(buffer, 0, length);
		}
		
		if (bis != null) bis.close();
		if (bos != null) bos.close();

    }
}
