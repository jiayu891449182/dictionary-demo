package dic;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NLPDictionary {

    static {
        updateDic();
    }

    public static String getToken(String str){
        Result rs = DicAnalysis.parse(str);
        StringBuffer sb = new StringBuffer();
        for (Term t:rs) {
            sb.append(t.getName()).append(":").append(t.getNatureStr()).append(" ");
        }
        if (sb == null || sb.length() < 1) {
            return "";
        } else {
            return sb.substring(0,sb.length() - 1);
        }
    }

    private static void updateDic(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(NLPDictionary.class.getResourceAsStream("/dictionary.tsv")));
            String s = null;
            while((s = br.readLine())!=null){
                String[] ss = s.split("\t");
                if (ss.length==3) {
                    UserDefineLibrary.insertWord(ss[0],ss[1],Integer.valueOf(ss[2]));
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        String s1 = NLPDictionary.getToken("关中刀客之黑脊背1");
        String s2 = NLPDictionary.getToken("关中刀客之黑脊背2");
        String s3 = NLPDictionary.getToken("关中刀客之黑脊背3");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

}

