import java.io.*;

 
public class leitorArquivo 
{
 
    //Retorna os bytes do arquivo
    public static byte[] lerArquivo() throws IOException 
    {
        FileInputStream in = null;

		
			in = new FileInputStream("teste.txt");
			ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();

			int bytesread = 0;
			byte[] tbuff = new byte[1024];
			while ((bytesread = in.read(tbuff)) != -1) 
            {
				byteArrayStream.write(tbuff, 0, bytesread);
			}
            in.close();
			return byteArrayStream.toByteArray();
    }
       
    //Recebe a string binaria gerada pelo algoritmo huffman e gera o arquivo compressado
    public static void gerarArquivoCodificado(String encodeData) throws IOException 
    {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
        
		String codebuf = null;
        fos = new FileOutputStream("arquivoCodificado.tst");
		oos = new ObjectOutputStream(fos);
        int tempchar;


        codebuf = "";
        codebuf = encodeData;

        while (codebuf.length() >= 8) 
        {
            tempchar = 0;
            for (int i = 0; i < 8; i++) 
            {
                    tempchar <<= 1;
 
                if (codebuf.charAt(i) == '1')
                        tempchar |= 1;//bitwise or
            }
                oos.writeByte((byte) tempchar);
                codebuf = codebuf.substring(8);
        }       
				if (codebuf.length() > 0) 
                {
					tempchar = 0;
					for (int i = 0; i < codebuf.length(); ++i) 
                    {
                        tempchar <<= 1;
                        if (codebuf.charAt(i) == '1')
                            tempchar |= 1;//bitwise and
					}
                    tempchar <<= (8 - codebuf.length());
                    oos.writeByte((byte) tempchar);
				}
     
    
        oos.close();
    }
    
    //Recebe os bytes do arquivo e escreve no arquivo
    public static void gerarArquivoDescodificado(byte[] bytesRecuperados) throws IOException
    {
        String mensagemOriginal = new String(bytesRecuperados, "UTF-8");
        System.out.println("Mensagem original: " + mensagemOriginal);
        File file = new File ("descodificado.tst");

        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            out.write(mensagemOriginal);
            out.newLine();
            out.close();
        } 
        catch (IOException e)
        {
            System.out.println("Erro ao escrever arquivo");
        }
     }
      
          
}       

