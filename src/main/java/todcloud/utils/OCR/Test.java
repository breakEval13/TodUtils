package todcloud.utils.OCR;

/**
 * Created by zhangjianxin on 2017/6/9.
 */
import java.io.File;

public class Test
{
    public static void main(String[] args)
    {
        try
        {

            File testDataDir = new File("conf/DATA");
            System.out.println(testDataDir.listFiles().length);
            int i = 0 ;
            for(File file :testDataDir.listFiles())
            {
                i++ ;
                String recognizeText = new OCRHelper().recognizeText(file);
                System.out.print(recognizeText+"\t");

                if( i % 5  == 0 )
                {
                    System.out.println();
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}