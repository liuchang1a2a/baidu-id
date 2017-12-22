package scan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLEncoder;

public class Idcard {

	/**
     * ��Ҫ��ʾ���������蹤����
     * FileUtil,Base64Util,HttpUtil,GsonUtils���
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * ����
     */
    public static void main(String[] args) {
        // ���֤ʶ��url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        // ����ͼƬ·��
        String filePath = "D://pic.txt";
        try {

 //       	System.out.println(Base64Util.getImageStr(filePath));
        	BufferedReader br = new BufferedReader(new FileReader(filePath));
        	String line=br.readLine();
        	String picStr="";
        	while(line!=null){
        		picStr +=line;
        		line=br.readLine();
        	}
        	br.close();
        	
        	picStr=picStr.replaceAll("%2F", "/");
        	picStr=picStr.replaceAll("%3D", "=");
        	picStr = picStr.replace("%2B", "+");
        	System.out.println(picStr);        	
        	
        	
//          Base64Util.generateImage(imgStr, filePath);
 //       	byte[] imgData = FileUtil.readFileByBytes(filePath);
 //           String imgStr = Base64Util.encode(imgData);
        	// ʶ�����֤����id_card_side=front;ʶ�����֤����id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(picStr, "UTF-8");
            /**
             * ���ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
             */
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
	
    
    
    

}
