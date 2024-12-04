public class ZhoSen{
	//汉语句子良好化：句首为汉字，zhomin个汉字(缺省10)<=句长<=zhomax个汉字(缺省200)
	public static String toBetterZhoSen(String sen, int zhomin, int zhomax){
		sen=Chinese.formatZhoLine(sen);
		int len=sen.length();
		if(len>0){
			char head=sen.charAt(0);
			//if(!Chinese.isZhoCha(head)&&!Numeral.isNumeralCha(head)&&head!='('&&head!='（'){
			if(!Chinese.isZhoCha(head)){
				sen=null;
			}else if(len<zhomin||len>zhomax){
				sen=null;
			}else{
				for(char cha:sen.toCharArray()){
					if(cha==' '){
						sen=null;
						break;
					}else if(!Chinese.isZhoCha(cha)&&!Numeral.isNumeralCha(cha)&&!Symbol.isSymbolCha(cha)){
						sen=null;
						break;
					}
				}
			}
			if(sen!=null){
				//sen=_toStop(sen);
			}
		}else{
			sen=null;
		}
		return sen;
	}
	
	//汉语句子最优化：句首为汉字，尾字。！？，zhomin个汉字(缺省10)<=句长<=zhomax个汉字(缺省200)
	public static String toBestZhoSen(String sen, int zhomin, int zhomax){
		String toBestZhoSen=null;
		sen=toBetterZhoSen(sen, zhomin, zhomax);
		if(sen!=null){
			int len=sen.length();
			char tail=sen.charAt(len-1);
			//if(tail=='。'||tail=='！'||tail=='？'||tail=='；'||tail=='：'){
			if(tail=='。'||tail=='！'||tail=='？'){
				toBestZhoSen=sen;
			}
		}
		return toBestZhoSen;
	}
	
}