
public class ReadAlgInput {
	String arg ="";
	String check [];
	boolean notValid = true;
	ReadAlgInput(String arg){
		this.arg = arg;
		splitter(arg);
		//checker(arg);
		
	}
	
	private void splitter (String arg){
		check = arg.split(" ");
		System.out.println(check[0] + " " + check[1]);
		if(checker(check[0])){
			ExAlg exAlg = new ExAlg(check[0], Integer.parseInt(check[1]));
			new readChain(exAlg.getAlgRes());
		}
		else System.out.println("error");
	}
	private boolean checker(String arg){
		for(Character c : arg.toCharArray()){
			if(!(c.equals('H')) || !(c.equals('P'))){
				System.out.println(c);
				//return notValid = false;
			}
		}
		return notValid;
	}
	
	
	

}
