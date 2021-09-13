package cryptography;
import java.io.*;

public class Decryptor 
{
    KeyGenerator kGen;
    
    public Decryptor(String key) 
    {
        //get the KeyGenerator
        kGen = new KeyGenerator(key);
    }
    
    public void decrypt(String src, String trgt)throws Exception //throws statement is used to explicitly propogate a Checked Exception
        {
        //open the source file for reading
        //file must exist, otherwise FileNotFoundException be raised
        FileInputStream fin = new FileInputStream(src);
        //RandomAccessFile fin = new RandomAccessFile(src, "r");
        //fin = open(src, "rb")
        
        //open the target file for writing
        //file will be created or overwritten
        //Invalid path or permission issue may cause FileNotFoundException 
        FileOutputStream fout = new FileOutputStream(trgt);
        //RandomAccessFile fout =new RandomAccessFile(trgt, "rw");
        
        //fout = open(trgt, "wb")
        
        String nK = kGen.getNumericKey();
        //for level 1 of encryption
        ArmstrongManager aManager = new ArmstrongManager(nK);
        //for level 2 of encryption
        ColorManager cManager = new ColorManager(nK);
        
        //byte buffer[] = new byte[2048];
        int n, i;
        int temp;
        
        while((n = fin.read()) != -1)
        {
            //process the buffer
            //for (i =0 ; i < n; i++)
            //{
                temp = cManager.decrypt(n);
                temp = aManager.decrypt(temp);
                //buffer[i] = (byte)temp;
                fout.write(temp);
            //}
            //write into the result
            //fout.write(buffer, 0, n);
        }
        
        //close the files
        fin.close();
        fout.close();
        
    }
}
