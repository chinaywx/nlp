import java.util.HashMap;

public class Numeral{
	private static char[] SBCs={'０','１','２','３','４','５','６','７','８','９'};
	private static char[] DBCs={'0','1','2','3','4','5','6','7','8','9'};
	private static HashMap<Character, Character> toSBC=new HashMap<Character, Character>();
	private static HashMap<Character, Character> toDBC=new HashMap<Character, Character>();
	static{
		for(int i=0; i<SBCs.length; i++){
			toSBC.put(DBCs[i], SBCs[i]);
			toDBC.put(SBCs[i], DBCs[i]);
		}
	}

	//阿拉伯数字字符半角(DBC)转全角(SBC)
	public static char toSBC(char chaDBC){
		if(toSBC.containsKey(chaDBC)) chaDBC=toSBC.get(chaDBC);
		return chaDBC;
	}
	
	//阿拉伯数字字符串半角(DBC)转全角(SBC)
	public static String toSBC(String strDBC){
		String strSBC="";
		for(char chaDBC:strDBC.toCharArray()) strSBC=strSBC+toSBC(chaDBC);
		return strSBC;
	}
	
	//阿拉伯数字字符全角(SBC)转半角(DBC)
	public static char toDBC(char chaSBC){
		if(toDBC.containsKey(chaSBC)) chaSBC=toDBC.get(chaSBC);
		return chaSBC;
	}
	
	//阿拉伯数字字符串全角(SBC)转半角(DBC)
	public static String toDBC(String strSBC){
		String strDBC="";
		for(char chaSBC:strSBC.toCharArray()) strDBC=strDBC+toDBC(chaSBC);
		return strDBC;
	}
	
	public static boolean isNumeralCha(char cha){
		boolean isNumeralCha=false;
		if(toDBC.containsKey(cha)||toSBC.containsKey(cha)) isNumeralCha=true;
		return isNumeralCha;
	}
	
	public static boolean isNumeralStr(String str){
		boolean isNumeralStr=true;
		for(char cha:str.toCharArray()){
			if(!isNumeralCha(cha)&&cha!='.'){
				isNumeralStr=false;
				break;
			}
		}
		return isNumeralStr;
	}
	
	public static boolean containsNumeralCha(String str){
		boolean containsNumeralCha=false;
		for(char cha:str.toCharArray()){
			if(isNumeralCha(cha)){
				containsNumeralCha=true;
				break;
			}
		}
		return containsNumeralCha;
	}
}