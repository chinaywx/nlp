import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Latin{
	private static char[] UpperSBCs={'Ａ','Ｂ','Ｃ','Ｄ','Ｅ','Ｆ','Ｇ','Ｈ','Ｉ','Ｊ','Ｋ','Ｌ','Ｍ','Ｎ','Ｏ','Ｐ','Ｑ','Ｒ','Ｓ','Ｔ','Ｕ','Ｖ','Ｗ','Ｘ','Ｙ','Ｚ'};
	private static char[] UpperDBCs={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private static char[] LowerSBCs={'ａ','ｂ','ｃ','ｄ','ｅ','ｆ','ｇ','ｈ','ｉ','ｊ','ｋ','ｌ','ｍ','ｎ','ｏ','ｐ','ｑ','ｒ','ｓ','ｔ','ｕ','ｖ','ｗ','ｘ','ｙ','ｚ'};
	private static char[] LowerDBCs={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static HashMap<Character, Character> toDBC=new HashMap<Character, Character>();
	private static HashMap<Character, Character> toSBC=new HashMap<Character, Character>();
	private static HashSet<Character> upper=new HashSet<Character>();
	private static HashSet<Character> lower=new HashSet<Character>();
	static{
		for(int i=0; i<UpperSBCs.length; i++){
			toDBC.put(UpperSBCs[i], UpperDBCs[i]);
			toDBC.put(LowerSBCs[i], LowerDBCs[i]);
			toSBC.put(UpperDBCs[i], UpperSBCs[i]);
			toSBC.put(LowerDBCs[i], LowerSBCs[i]);
			upper.add(UpperSBCs[i]);
			upper.add(UpperDBCs[i]);
			lower.add(LowerSBCs[i]);
			lower.add(LowerDBCs[i]);
		}
	}

	//拉丁字符全角(SBC)转半角(DBC)
	public static char toDBC(char chaSBC){
		if(toDBC.containsKey(chaSBC)) chaSBC=toDBC.get(chaSBC);
		return chaSBC;
	}
	
	//拉丁字符串全角(SBC)转半角(DBC)
	public static String toDBC(String strSBC){
		String strDBC="";
		for(char chaSBC:strSBC.toCharArray()) strDBC=strDBC+toDBC(chaSBC);
		return strDBC;
	}
	
	//拉丁字符半角(DBC)转全角(SBC)
	public static char toSBC(char chaDBC){
		if(toSBC.containsKey(chaDBC)) chaDBC=toSBC.get(chaDBC);
		return chaDBC;
	}
	
	//拉丁字符串半角(DBC)转全角(SBC)
	public static String toSBC(String strDBC){
		String strSBC="";
		for(char chaDBC:strDBC.toCharArray()) strSBC=strSBC+toSBC(chaDBC);
		return strSBC;
	}

	//是大写拉丁字符
	public static boolean isUpperLatCha(char cha){
		boolean isUpperLatCha=false;
		if(upper.contains(cha)) isUpperLatCha=true;
		return isUpperLatCha;
	}
	
	//是大写拉丁字符串
	public static boolean isUpperLatStr(String str){
		boolean isUpperLatStr=true;
		for(char cha:str.toCharArray()){
			if(!isUpperLatCha(cha)){
				isUpperLatStr=false;
				break;
			}
		}
		return isUpperLatStr;
	}
	
	//是小写拉丁字符
	public static boolean isLowerLatCha(char cha){
		boolean isLowerLatCha=false;
		if(lower.contains(cha)) isLowerLatCha=true;
		return isLowerLatCha;
	}
	
	//是小写拉丁字符串
	public static boolean isLowerLatStr(String str){
		boolean isLowerLatStr=true;
		for(char cha:str.toCharArray()){
			if(!isLowerLatCha(cha)){
				isLowerLatStr=false;
				break;
			}
		}
		return isLowerLatStr;
	}
	
	//是拉丁字符
	public static boolean isLatCha(char cha){
		boolean isLatCha=false;
		if(isUpperLatCha(cha)||isLowerLatCha(cha)) isLatCha=true;
		return isLatCha;
	}
	
	//是拉丁字符串：判断由26个字母大小写组成的单词
	public static boolean isFullLatStr(String str){
		boolean isLatStr=true;
		for(char cha:str.toCharArray()){
			if(!isLatCha(cha)){
				isLatStr=false;
				break;
			}
		}
		return isLatStr;
	}
	
	//是拉丁字符串：判断由26个字母大小写和(-)组成的单词
	public static boolean isLatStr(String str){
		boolean isLatStr=true;
		for(char cha:str.toCharArray()){
			if(!isLatCha(cha)&&cha!='-'){
				isLatStr=false;
				break;
			}
		}
		return isLatStr;
	}
	
	//是拉丁字符串：判断由26个字母大小写和(-)和(空格)组成的单词
	public static boolean isSpaceLatStr(String str){
		boolean isLatStr=true;
		for(char cha:str.toCharArray()){
			if(!isLatCha(cha)&&cha!='-'&&cha!=' '){
				isLatStr=false;
				break;
			}
		}
		return isLatStr;
	}
	
	//头大写拉丁字符
	public static boolean startsWithUpperLatCha(String str){
		boolean startsWithUpperLatCha=false;
		if(isUpperLatCha(str.charAt(0))) startsWithUpperLatCha=true;
		return startsWithUpperLatCha;
	}

	//头大写拉丁字符串
	public static boolean startsWithUpperLatChaStr(String str){
		boolean startsWithUpperLatChaStr=false;
		if(isLatStr(str)&&isUpperLatCha(str.charAt(0))) startsWithUpperLatChaStr=true;
		return startsWithUpperLatChaStr;
	}
	
	//头小写拉丁字符
	public static boolean startsWithLowerLatCha(String str){
		boolean startsWithLowerLatCha=false;
		if(isLowerLatCha(str.charAt(0))) startsWithLowerLatCha=true;
		return startsWithLowerLatCha;
	}

	//头小写拉丁字符串
	public static boolean startsWithLowerLatChaStr(String str){
		boolean startsWithLowerLatChaStr=false;
		if(isLatStr(str)&&isLowerLatCha(str.charAt(0))) startsWithLowerLatChaStr=true;
		return startsWithLowerLatChaStr;
	}
	
	public static boolean startsWithLatCha(String str){
		boolean startsWithLatCha=false;
		if(isLatCha(str.charAt(0))) startsWithLatCha=true;
		return startsWithLatCha;
	}
	
	//含大写拉丁字符
	public static boolean containsUpperLatCha(String str){
		boolean containsUpperLatCha=false;
		for(char cha:str.toCharArray()){
			if(isUpperLatCha(cha)){
				containsUpperLatCha=true;
				break;
			}
		}
		return containsUpperLatCha;
	}
	
	//含小写拉丁字符
	public static boolean containsLowerLatCha(String str){
		boolean containsLowerLatCha=false;
		for(char cha:str.toCharArray()){
			if(isLowerLatCha(cha)){
				containsLowerLatCha=true;
				break;
			}
		}
		return containsLowerLatCha;
	}
	
	//含拉丁字符
	public static boolean containsLatCha(String str){
		boolean containsLatCha=false;
		for(char cha:str.toCharArray()){
			if(isLatCha(cha)){
				containsLatCha=true;
				break;
			}
		}
		return containsLatCha;
	}
	
	//拉丁语句终标点
	private static final char[] senPuncs={'!','?'};
	private static HashSet<Character> senPunc=new HashSet<Character>();
	static{
		for(char cha:senPuncs) senPunc.add(cha);
	}

	public static boolean isSenPunc(char cha){
		boolean isSenPunc=false;
		if(senPunc.contains(cha)) isSenPunc=true;
		return isSenPunc;
	}
	
	//处理句号(.)
	private static boolean isSenPunc(char post1, char post2){
		boolean isSenPunc=false;
		if(post1==Character.UNASSIGNED){
			isSenPunc=true;
		}else if(post2==Character.UNASSIGNED){
			isSenPunc=true;
		}else if((post1==' ')&&(isUpperLatCha(post2))){
			isSenPunc=true;
		}
		return isSenPunc;
	}
	
	public static boolean isSenPunc(char current, char post1, char post2){
		boolean isSenPunc=false;
		if(isSenPunc(current)) isSenPunc=true;
		else if(current=='.') isSenPunc=isSenPunc(post1, post2);
		return isSenPunc;
	}
	
	//拉丁语段落分句：不含回车(\n)
	public static ArrayList<String> split(String line){
		line=formatLatLine(line);
		char current;
		char post1;
		char post2;
		ArrayList<String> sens=new ArrayList<String>();
		String sen="";
		int len=line.length();
		for(int i=0; i<len; i++){
			current=line.charAt(i);
			int ip1=i+1;
			int ip2=i+2;
			if(ip1<len) post1=line.charAt(ip1); else post1=Character.UNASSIGNED;
			if(ip2<len) post2=line.charAt(ip2); else post2=Character.UNASSIGNED;

			sen=sen+current;
			if(isSenPunc(current, post1, post2)){
				sen=Symbol.removeStartSpace(sen);
				sens.add(sen);
				sen="";
			}
		}
		if(!sen.equals("")) sens.add(sen);
		return sens;
	}

	public static String formatLatLine(String line){
		line=Symbol.formatDot(line);
		line=Symbol.formatSpace(line);
		line=Symbol.toDBC(line);
		line=Numeral.toDBC(line);
		line=toDBC(line);
		return line;
	}
	
	public static boolean totalWordsInSet(String line, HashSet<String> words){
		boolean totalWordsInSet=true;
		for(String word:segmentWord(line)){
			if(!words.contains(word.toLowerCase())){
				totalWordsInSet=false;
				break;
			}
		}
		return totalWordsInSet;
	}

	//拉丁语分词
	public static ArrayList<String> segmentWord(String line){
		ArrayList<String> segment=new ArrayList<String>();
		line=formatLatLine(line);
		String words="";
		String temp="";
		for(char c:line.toCharArray()){
			if((Symbol.isSymbolCha(c)&&c!='-'&&c!='•'&&c!='‧')||c==' '){
				if(temp.length()>0){
					words=words+" "+temp+" "+c;
					temp="";
				}else{
					words=words+" "+c;
				}
			}else{
				temp=temp+c;
			}
		}
		if(temp.length()>0) words=words+" "+temp;
		line=formatLatLine(words);
		for(String word:line.split(" ")){
			segment.add(word);
		}
		return segment;
	}
	
	//拉丁语分词(下一次仿照阿拉伯语和汉语进行阿拉伯数字单个切分)
	public static String segmentWordStr(String line){
		line=formatLatLine(line);
		String words="";
		String temp="";
		for(char c:line.toCharArray()){
			if(Symbol.isSymbolCha(c)||c==' '){
				if(temp.length()>0){
					words=words+" "+temp+" "+c;
					temp="";
				}else{
					words=words+" "+c;
				}
			}else{
				temp=temp+c;
			}
		}
		if(temp.length()>0) words=words+" "+temp;
		line=formatLatLine(words);
		return line;
	}
	
	//拉丁语分词(仿照阿拉伯语和汉语进行阿拉伯数字单个切分)
	public static String segmentWordNumeral(String line){
		line=formatLatLine(line);
		String words="";
		String temp="";
		for(char c:line.toCharArray()){
			if(Symbol.isSymbolCha(c)||Numeral.isNumeralCha(c)||c==' '){
				if(temp.length()>0){
					words=words+" "+temp+" "+c;
					temp="";
				}else{
					words=words+" "+c;
				}
			}else{
				temp=temp+c;
			}
		}
		if(temp.length()>0) words=words+" "+temp;
		line=formatLatLine(words);
		return line;
	}
	
}