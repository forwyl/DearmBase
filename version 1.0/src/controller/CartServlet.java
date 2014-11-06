package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONArray;
import org.json.JSONException;

import beans.Dream;
import beans.Wishes;


@WebServlet(name="CartServlet", description="cart handler", urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final String INPUT_XML_LOC="dream.xml";
    private static final String OUTPUT_XML_PREFIX="intoDream";
    private static final String REF_XSLT_LOC = "intoDream.xsl";
//    private static final String NEXT_PAGE = "/intoDream.jsp";
    private static final String SESSION_ATTR_NAME = "xmlName";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		File xmlFile = null;
		req.setCharacterEncoding("UTF-8");

		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		
		String json = readJSONString(req);
		System.out.println("收到的JSON字串："+ json);
		
        try {
        	JSONArray ary = new JSONArray(json);
        	List<Integer> list = new ArrayList<Integer>();
        	
        	for(int i=0; i < ary.length(); i++){
            	list.add(Integer.valueOf(ary.getString(i)));
        	}
        	
        	xmlFile = generateXML(list);
        	
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        
        System.out.println("XML檔案名稱：" + xmlFile.getName());
        session.setAttribute(SESSION_ATTR_NAME, xmlFile.getName());
//			res.sendRedirect(NEXT_PAGE+"?xmlName=" + xmlFile.getName());
//			RequestDispatcher rd = req.getRequestDispatcher(NEXT_PAGE);
//			rd.forward(req, res);
        
	}
	
	private File generateXML(List<Integer> idList){
		
		File file = new File(getServletContext().getRealPath("/") + OUTPUT_XML_PREFIX+UUID.randomUUID().toString()+".xml");
		List<Dream> wishList= findXMLBy(idList);
		for(Dream d: wishList){
			System.out.println(d.getNumber()+"," + d.getImgUrl());
		}
		Wishes wishes = new Wishes();
		wishes.setAllDreams(wishList);
			    
	    try {
		    JAXBContext jaxbContext = JAXBContext.newInstance(Wishes.class);			 
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type='text/xsl' href='"+ REF_XSLT_LOC + "' ?>");
		    jaxbMarshaller.marshal(wishes, file);
		    jaxbMarshaller.marshal(wishes, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	    
	    return file;
	    
	}
	
	 private List<Dream> findXMLBy(List<Integer> idList) {
		 
		 List<Dream> wishList = new ArrayList<Dream>();
		 	 
		 try {
			      InputStream ins = getServletContext().getResourceAsStream(INPUT_XML_LOC);
			      JAXBContext jaxbContext = JAXBContext.newInstance(Wishes.class);
			 
			      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			      Wishes wishes = (Wishes) jaxbUnmarshaller.unmarshal(ins);
			      
			      List<Dream> list = wishes.getAllDreams();
			      Map<Integer, Dream> map = new HashMap<Integer, Dream>();
			      for(Dream dream: list){
			    	  map.put(dream.getNumber(), dream);
			      }
			      
			      for(int i: idList){
			    	  wishList.add(map.get(i));
			      }
			      
		    }catch (JAXBException e) {
		    	e.printStackTrace();		    
		    }
		 
		 	return wishList;
		 
	}
	
    private String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }


    
}
