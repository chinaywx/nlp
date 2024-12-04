import java.util.HashMap;
import java.util.HashSet;

public class Symbol{
	private static char[] SBCs={'！','＂','＃','＄','％','＆','＇','（','）','＊','＋','，','－','．','／','：','；','＜','＝','＞','？','＠','［','＼','］','＾','＿','｀','｛','｜','｝','～','￡','￥','￠'};
	private static char[] DBCs={'!','"','#','$','%','&','\'','(',')','*','+',',','-','.','/',':',';','<','=','>','?','@','[','\\',']','^','_','`','{','|','}','~','£','¥','¢'};
	private static HashMap<Character, Character> toSBC=new HashMap<Character, Character>();
	private static HashMap<Character, Character> toDBC=new HashMap<Character, Character>();
	static{
		for(int i=0; i<SBCs.length; i++){
			toSBC.put(DBCs[i], SBCs[i]);
			toDBC.put(SBCs[i], DBCs[i]);
		}
	}
	
	//标点符号半角(DBC)转全角(SBC)
	public static char toSBC(char chaDBC){
		if(toSBC.containsKey(chaDBC)) chaDBC=toSBC.get(chaDBC);
		return chaDBC;
	}
	
	//标点符号串半角(DBC)转全角(SBC)
	public static String toSBC(String strDBC){
		String strSBC="";
		for(char chaDBC:strDBC.toCharArray()) strSBC=strSBC+toSBC(chaDBC);
		return strSBC;
	}
	
	//标点符号全角(SBC)转半角(DBC)
	public static char toDBC(char chaSBC){
		if(toDBC.containsKey(chaSBC)) chaSBC=toDBC.get(chaSBC);
		return chaSBC;
	}
	
	//标点符号串全角(SBC)转半角(DBC)
	public static String toDBC(String strSBC){
		String strDBC="";
		for(char chaSBC:strSBC.toCharArray()) strDBC=strDBC+toDBC(chaSBC);
		return strDBC;
	}

	private static char[] lefts={'（','(','［','[','｛','{','【','〖','〔','≪','《','«','〈','<','＜','『','｢','「','“','‘','`'};
	private static char[] rights={'）',')','］',']','｝','}','】','〗','〕','≫','》','»','〉','>','＞','』','｣','」','”','’','´'};
	
	private static char[] singles={'。','｡','、','•','・','･','.','．','·','∶','‚','¨','＂','"','〝','″','„','〃','｀','＇','′','\'',
			                       '+','＋','±','*','×','÷','≠','≤','≥',
			                       '–','—','－','-','_','＿','┄','┈','…','〜','~','～','=','＝',
			                       '/','／','│','｜','⁄','\\','‰','¼','½','¾',
			                       '◯','〇','°','®','©','℃','㎡','㎝','㎞','㎏',' '};
	public static HashSet<Character> symbol=new HashSet<Character>();
	static{
		for(int i=0; i<SBCs.length; i++){
			symbol.add(SBCs[i]);
			symbol.add(DBCs[i]);
		}
		for(int i=0; i<lefts.length; i++){
			symbol.add(lefts[i]);
			symbol.add(rights[i]);
		}
		for(int i=0; i<singles.length; i++){
			symbol.add(singles[i]);
		}
	}

	public static boolean isSymbolCha(char cha){
		boolean isSymbolCha=false;
		if(symbol.contains(cha)) isSymbolCha=true;
		return isSymbolCha;
	}
	
	public static boolean isSymbolStr(String str){
		boolean isSymbolStr=true;
		for(char cha:str.toCharArray()){
			if(!isSymbolCha(cha)){
				isSymbolStr=false;
				break;
			}
		}
		return isSymbolStr;
	}

	public static boolean containsSymbolCha(String str){
		boolean containsSymbolCha=false;
		for(char cha:str.toCharArray()){
			if(isSymbolCha(cha)){
				containsSymbolCha=true;
				break;
			}
		}
		return containsSymbolCha;
	}
	
	private static final char[] Spaces={'\r','\n','\f','\t','\b','　',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','​','‌','‍','‎','‏',' ',' ','‪','‫','‬','‭','‮',' ','⁡',' ','⁢','⁣','⁤','⁪','⁫','⁬','⁭','⁮','⁯'};	
	
	public static String formatReplaceSpace(String str){
		for(char cha:Spaces) str=str.replace(cha, ' ');
		return str;
	}
	
	public static String removeTwoSpace(String str){
		while(str.contains("  ")) str=str.replace("  ", " ");
		return str;
	}

	public static String removeStartSpace(String str){
		while(str.startsWith(" ")) str=str.replaceFirst(" ", "");
		return str;
	}
	
	public static String removeEndSpace(String str){
		return str.trim();
	}
	
	public static String formatSpace(String str){
		str=formatReplaceSpace(str);
		str=removeTwoSpace(str);
		str=removeStartSpace(str);
		str=removeEndSpace(str);
		return str;
	}
	
	public static String formatDot(String str){
		while(str.contains("...")) str=str.replace("...", "…");
		while(str.contains("..")) str=str.replace("..", ".");
		return str;
	}
}