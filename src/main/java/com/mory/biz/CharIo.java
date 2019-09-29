import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CharIo {
    public statis final String apple = "x";
    public static void main(String[] args) {
        int i = 0;       
        // 第一种方式读文件,因为方法throws了异常，所以在这要捕获异常
        try {
            CharIo.readFromFileByByte();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件啊");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读不成功啊！");
        }
        int peany = 0;        
        System.out.println("===========================");
        
        // 第二种方式读文件
        try {
            CharIo.readFromFileByteTwo();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("还是读不成功啊！");
        }
        
    }
    
    /**
     * 第一种方法读文件
     * 通过字符流读取文件中的数据
     * @throws IOException 
     */
    public static void readFromFileByByte() throws IOException{
        File file = new File("abc.txt");
        // 如果文件不存在则创建文件
        if (!file.exists()) {
            file.createNewFile();
        }
        InputStream inputStream = new FileInputStream(file);
        // 这里定义了数组的长度是1024个字节，如果文件超出这字节，就会溢出，结果就是读不到1024字节以后的东西
        byte[] bs = new byte[1024];
        // 这里len获得的是文件中内容的长度
        int len = inputStream.read(bs);
        inputStream.close();
        System.out.println(new String(bs));
    }
    
    /**
     * 第二种方法读文件
     * 通过字符流读取文件中的数据
     * @throws IOException 
     */
    public static void readFromFileByteTwo() throws IOException{
        // 注意这里的不同，File.separator是分隔符，这里指明绝对路径，即D盘根目录下的abc.txt文件
        File file = new File("d:" + File.separator+"abc.txt");
        // 如果文件不存在则创建文件
        if (!file.exists()) {
            file.createNewFile();
        }
        InputStream inputStream = new FileInputStream(file);
        // 这里也有不同，可以根据文件的大小来声明byte数组的大小，确保能把文件读完
        byte[] bs = new byte[(int)file.length()];
        // read()方法每次只能读一个byte的内容
        inputStream.read(bs);
        inputStream.close();
        System.out.println(new String(bs));
    }
    
}
