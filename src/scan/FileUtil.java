package scan;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 鏂囦欢璇诲彇宸ュ叿绫�?*/
public class FileUtil {

    /**
     * 璇诲彇鏂囦欢鍐呭锛屼綔涓哄瓧绗︿覆杩斿�?     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } 

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        } 

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 鍒涘缓�?楄妭杈撳叆娴�?
        FileInputStream fis = new FileInputStream(filePath);  
        // 鍒涘缓涓�釜�?��害涓�?240鐨凚uffer
        byte[] bbuf = new byte[10240];  
        // 鐢ㄤ簬淇濆瓨瀹為檯璇诲彇鐨勫瓧鑺傛暟  
        int hasRead = 0;  
        while ( (hasRead = fis.read(bbuf)) > 0 ) {  
            sb.append(new String(bbuf, 0, hasRead));  
        }  
        fis.close();  
        return sb.toString();
    }

    /**
     * 鏍规嵁鏂囦欢璺緞璇诲彇byte[] 鏁扮�?     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }
}
