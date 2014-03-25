

/**
 * Class that fixes the input to the algorithm and checks for errors.
 * @author kandidatgruppen
 *
 */
public class ReadAlgInput {
	String arg ="";
	String check [];
	boolean notValid = true;
	/**
	 * Construcotr takes a String as parameter and runs method splitter.
	 * @param arg
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	ReadAlgInput(String arg) throws NumberFormatException, Exception{
		this.arg = arg;
		splitter(arg);
		//checker(arg);
		
	}
	/**
	 * Splitter splits the argument and checks that the input is valid.
	 * @param arg
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void splitter (String arg) throws NumberFormatException, Exception{
		check = arg.split(" ");
		System.out.println(check[0] + " " + check[1]);
		if(checkerOne(check[0]) & checkerTwo(check[1])){
			ExAlg exAlg = new ExAlg(check[0], Integer.parseInt(check[1]));
			new readChain(exAlg.getAlgRes());
		}
		else System.out.println("error");
	}
	private boolean checkerOne(String arg){
		for(Character c : arg.toCharArray()){
			if(!(c.equals('H')) && !(c.equals('P'))){
				System.out.println(c);
				return notValid = false;
			}
		}
		return notValid;
	}
	
	private boolean checkerTwo(String arg) throws Exception{
		int number = Integer.parseInt(arg);
		if(number > 0){
			return true;
		}
		else{
			throw new Exception("To low number of iterations.");
			
		}
	}
	
	
	

}
