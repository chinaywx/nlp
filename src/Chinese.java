import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import com.github.nobodxbodon.zhconverter.简繁转换类;

public class Chinese{
	//全角-->半角
	private static char[] SBCstoDBC={'＃','＄','％','＆','（','）','＊','＋','－','．','／','＜','＝','＞','＠','［','＼','］','＾','＿','｀','｛','｜','｝','～','￡','￥','￠'};
	private static char[] DBCstoDBC={'#','$','%','&','(',')','*','+','-','.','/','<','=','>','@','[','\\',']','^','_','`','{','|','}','~','£','¥','¢'};
	private static HashMap<Character, Character> toDBC=new HashMap<Character, Character>();
	
	//半角-->全角
	private static char[] SBCstoSBC={'！','＂','＇','，','：','；','？','“','”','“','”','‘','’'};
	private static char[] DBCstoSBC={'!','"','\'',',',':',';','?','「','」','｢','｣','『','』'};
	private static HashMap<Character, Character> toSBC=new HashMap<Character, Character>();

	static{
		for(int i=0; i<SBCstoDBC.length; i++){
			toDBC.put(SBCstoDBC[i], DBCstoDBC[i]);
		}
		for(int i=0; i<SBCstoSBC.length; i++){
			toSBC.put(DBCstoSBC[i], SBCstoSBC[i]);
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
		if(strDBC.endsWith(".")&&strDBC.length()>1){
			if(isZhoCha(strDBC.charAt(strSBC.length()-2))) strSBC=strSBC.substring(0, strSBC.length()-1)+"。";
		}
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
	
	public static 简繁转换类 t2sconverter=简繁转换类.取实例(简繁转换类.目标.简体);
	public static 简繁转换类 s2tconverter=简繁转换类.取实例(简繁转换类.目标.繁体);
	
	public static String toTraditional(String strSimplified){
		String traditional=s2tconverter.转换(strSimplified);
		return traditional;
	}
	
	public static String toSimplified(String strTraditional){
		String simplified=t2sconverter.转换(strTraditional);
		return simplified;
	}
	
	public static String cha2HexUnicode(char cha){
		String hexUnicode=Integer.toHexString(cha);
		return hexUnicode;
	}
	
	public static int cha2Unicode(char cha){
		int unicode=(int) cha;
		return unicode;
	}
	
	public static char unicode2Cha(int unicode){
		char cha=(char) unicode;
		return cha;
	}
	
	public static boolean isZhoCha(char cha){
		boolean isZhoCha=false;
		int code=cha2Unicode(cha);
		//CJK统一汉字20924个(0x4E00到0x9FBB)
		//CJK统一汉字扩充A6582个(0x3400到0x4DB5)
		if(((code>=0x4E00)&&(code<=0x9FBB))||((code>=0x3400)&&(code<=0x4DB5))){
			isZhoCha=true;
		}
		return isZhoCha;
	}
	
	public static boolean isZhoStr(String str){
		boolean isZhoStr=true;
		for(char c:str.toCharArray()){
			if(!isZhoCha(c)){
			//if(!isZhoCha(c)&&c!='；'&&c!='》'&&c!='《'){
				isZhoStr=false;
				break;
			}
		}
		return isZhoStr;
	}
	
	public static boolean containsZhoCha(String str){
		boolean containsZhoCha=false;
		for(char cha:str.toCharArray()){
			if(isZhoCha(cha)){
				containsZhoCha=true;
				break;
			}
		}
		return containsZhoCha;
	}
	
	public static String formatZhoLine(String line){
		line=Symbol.formatDot(line);
		line=Symbol.formatSpace(line);
		line=toDBC(line);//符号转半角
		line=toSBC(line);//标点转全角
		line=Numeral.toDBC(line);
		line=Latin.toDBC(line);
		line=toSimplified(line);
		return line;
	}
	
	//汉语句终标点
	private static final char[] senPuncs={'。','！','？','；'};
	private static HashSet<Character> senPunc=new HashSet<Character>();
	static{
		for(char cha:senPuncs) senPunc.add(cha);
	}
	
	public static boolean isSenPunc(char cha){
		boolean isSenPunc=false;
		if(senPunc.contains(cha)) isSenPunc=true;
		return isSenPunc;
	}
	
	//汉语段落分句
	public static ArrayList<String> split(String line){
		line=formatZhoLine(line);
		ArrayList<String> sens=new ArrayList<String>();
		String sen="";
		for(char cha:line.toCharArray()){
			sen=sen+cha;
			if(isSenPunc(cha)){
				sen=Symbol.removeStartSpace(sen);
				sens.add(sen);
				sen="";
			}
		}
		if(!sen.equals("")) sens.add(Symbol.removeStartSpace(sen));
		return sens;
	}
	
	//汉语分字
	public static String segmentCha(String line){
		line=line.replaceAll(" ", "");
		String zhs="";
		for(char c:line.toCharArray()){
			zhs=zhs+" "+c;
		}
		line=zhs.replaceFirst(" ", "");
		line=formatZhoLine(line);
		return line;
	}
	
	public static void main(String[] args)throws Exception{

		System.out.println(toTraditional("刘"));
        System.out.println(toSimplified("劉"));
		System.out.println(isZhoCha('록'));
		System.out.println(isZhoCha('漢'));
		System.out.println(formatZhoLine(" 劉漢. "));

	}
}