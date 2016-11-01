package ru.matevosyan.test;

public class Control{

	public String subString(String s, String sub){

		char[] masS = s.toCharArray();
		char[] masSub = sub.toCharArray();
		int counter = 0;
		String scomp = "";
			
		for(int i = 0; i < masS.length; i++){

			if (masS[i] == masSub[counter]) {
				
				scomp += masS[i];
				
				if (scomp.equals(sub)) {
				
					System.out.println("Substring" + sub + "is in the string" + s);
					break;
				
				}
				
					counter++;
								
			} else {

				counter = 0;
					
			} 
				

		}

	return scomp;

	}

}