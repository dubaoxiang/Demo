package zhenlin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

  private static final String INPUTPATH = "in.txt";
  private static final String OUTPATH = "out.txt";

  
  /**
   * 获取文件内容
   * 
   *
   * @author dubx
   * @return
   */
  public List<String>  getCount() {
    FileInputStream fis = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    List<String> intputList = new ArrayList<String>();
    try {
      fis = new FileInputStream(INPUTPATH);
      isr = new InputStreamReader(fis, "UTF-8");
      br = new BufferedReader(isr);
      String currentLine = "";
      while (null != (currentLine = br.readLine())) {
        intputList.add(currentLine);
      }
    } catch (Exception e) {

    } finally {
      if (null != fis) {
        try {
          fis.close();
        } catch (IOException ex) {

        }
      }

      if (null != isr) {
        try {
          isr.close();
        } catch (IOException ex) {

        }
      }

      if (null != br) {
        try {
          br.close();
        } catch (IOException ex) {

        }
      }
    }

    return intputList;
  }

  /**
   * 把内容写到文件中
   * 
   *
   * @author dubx
   * @param result
   */
  public void writeFile(List<String> result) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPATH))) {
      for (int i = 0; i < result.size(); i++) {
        pw.println(result.get(i));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  public static void main(String[] args) {
    FileUtil fileUtil = new FileUtil();
 
    fileUtil.writeFile(fileUtil.getCount());
  }
}
