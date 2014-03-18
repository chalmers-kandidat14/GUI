
import java.awt.List;
import java.io.*;
import java.util.ArrayList;

public class ExAlg {
	String line = "";
	String temp = "";
	ArrayList<String> algChain = new ArrayList<String>();
	
	ExAlg(){
        	
	}
	public ArrayList<String> getAlgRes(){
		return algChain;
	}
	
	public void runAlg(){
		try {
            Process p = Runtime.getRuntime().exec("runhaskell  Main.hs HHHPHPPHHPH 100");

            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            
            while ((line = in.readLine()) != null) {
            	algChain.add(line);
            	
            	
                System.out.println(line);
            }
            //System.out.println(out);
            //System.out.println("rumppa");
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
		
	

	}
