package Day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Response {
	
	public void response(PrintWriter out, Request r) throws IOException{
		if(r.getMethod().equals("GET") && r.getResource().equals("/index.html")){
			get(out);
		}else if(r.getMethod().equals("POST")){
			post(r);
		}else if(r.getMethod().equals("PUT") && r.getResource().equals("/index.html")){
			put(r);
		}else if(r.getMethod().equals("DELETE")){
			delete(r);
		}else{
			printError(out);
		}
	}
	
	public void get(PrintWriter out) throws FileNotFoundException{
		String path = "C:\\Users\\elsyser\\Desktop\\Programing\\TUES_Programing\\2014-2015\\Internet_Programing\\Internet_Programing\\src\\Day_9\\index.html";	
		Scanner sc = new Scanner(new File(path));
		String str = null;
		while(sc.hasNextLine()){
		    str = sc.nextLine(); 
		    out.print(str);
		}
		sc.close();
	}
	
	public void post(Request r) throws IOException{
		File fnew=new File("C:\\Users\\elsyser\\Desktop\\Programing\\TUES_Programing\\2014-2015\\Internet_Programing\\Internet_Programing\\src\\Day_9\\"+r.getResource().toString());
		System.out.println(fnew.toString());
		fnew.createNewFile();
	}
	
	public void put(Request r) throws IOException{
		File file = new File("C:\\Users\\elsyser\\Desktop\\Programing\\TUES_Programing\\2014-2015\\Internet_Programing\\Internet_Programing\\src\\Day_9\\index.html");
		FileWriter fileWriter = new FileWriter(file,false);
		System.out.println(r.getBody().toString());
		fileWriter.write(r.getBody().toString());
	
		fileWriter.close();
	}
	
	public void delete(Request r){
		File fnew=new File("C:\\Users\\elsyser\\Desktop\\Programing\\TUES_Programing\\2014-2015\\Internet_Programing\\Internet_Programing\\src\\Day_9\\"+r.getResource().toString());
		fnew.delete();
	}
	
	public void printError(PrintWriter out){
		out.println("<html><head>" + "<title>404</title>" + "</head>"
				+ "<body>" + "<h1>404</h1>" +

				"</body></html>");
	}
}
