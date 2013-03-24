package core;

public class FormatXml {
	public String change(String s,int i){
		StringBuilder sbtemp = new StringBuilder();
		sbtemp.append("\n");
		for(int a = 0; a < i-1; a++){
			sbtemp.append("\t");
		}
		String endTag = sbtemp.toString();
		sbtemp.append("\t");
		String startTag = sbtemp.toString();//换行和制表符个数
		int test = s.indexOf('<');
		if(test > 0){//去掉非法字符
			s = s.substring(test,s.length());
		}
		if(test < 0){
			return s;
		}
		int test1 = s.lastIndexOf('>');
		if(test1 != s.length()){//去掉非法字符
			s = s.substring(0, test1+1);
		}
		int j = s.indexOf('>');
		String temp = s.substring(0,j+1);//such as <a> or <a id=''>
		if(temp.contains("/>")){// such as <a/>
			String s2 = s.substring(temp.length(), s.length());
			if(s2.length() > 0){
				return temp+change(s2,i);
			}else{
				return temp;
			}
		}
		
		String newtemp = ""; // such as <a> only
		String tempBack = "";//such as </a>
		if(temp.contains(" ")){
			String[] str = temp.split(" ");
			newtemp = str[0]+">";
			tempBack = "</"+newtemp.substring(1, newtemp.length());
		}else{
			tempBack = "</"+temp.substring(1, temp.length());
		}
		if(s.contains(tempBack)){
			if(s.indexOf(tempBack) == s.length()-tempBack.length()){//最外层标签
				s = s.substring(j+1,s.length()-tempBack.length());
				if(s.length() > 0 && s.contains("<")&& s.contains(">") && s.contains("</")){
					return temp + startTag+change(s,++i)+endTag+tempBack;
				}else{
					return temp+s+tempBack;
				}
			}else{//
				int k = s.indexOf(tempBack);
				String st = null;
				String s1 = s.substring(0,k)+tempBack;
				s1 = s1.substring(j+1,s1.length()-tempBack.length());
				if(s1.length() > 0 && s1.contains("<")&& s1.contains(">") && s1.contains("</")){
					st = temp + startTag+change(s1,++i)+endTag+tempBack;
					--i;
				}else{
					st = temp+s1+tempBack;
				}
				String s2 = s.substring(k+tempBack.length(),s.length());
				return st+endTag+change(s2,i);
			}
		}
		return s;
	}

	public String goodXml(String source){

		String title = "";
		if(source.contains("<?xml") && source.contains("?>")){
			String[] str =  source.split("\\?>");
			title = str[0]+"?>";
			source = str[1];
		}
		String sss = change(source,1);System.out.println(sss);
		return title+"\n"+sss;
	}
	
	public String trime(String source){
		return source.trim();
	}
}
