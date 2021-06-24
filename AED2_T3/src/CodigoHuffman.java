import EstruturaDeDados.filaPriorizada;
import EstruturaDeDados.myHashMap;

public class CodigoHuffman
{
    static myHashMap<Byte, String> lookupTable = new myHashMap<>(10);
    public No raiz;
    public HuffmanResultado compress(byte[] data)
    {
        myHashMap<Byte, Integer> freq = gerarFreqTable(data);
        No raiz = gerarHuffmanArvore(freq);
        myHashMap<Byte, String> lookupTable = gerarLookupTable(raiz);
        return new HuffmanResultado(gerarDataCodificada(data, lookupTable), raiz);
    }
    //Retorna a string do código binário
    private static String gerarDataCodificada(byte[] data, myHashMap<Byte, String> lookupTable) 
    {
        StringBuilder builder = new StringBuilder();
        for(byte bytes : data)
        {
            builder.append(lookupTable.pegarValor(bytes));
        }
        return builder.toString();   
    }
    
    //Gera a LookUpTable
    static public myHashMap<Byte, String> gerarLookupTable(No root)
    {             
        gerarLookupTableImpl(root, "", lookupTable);

        return lookupTable;
    }
    //Implementa a LookUpTable , contem um byte como chave e seu código binário como valor
    public static void gerarLookupTableImpl(No no, String string, myHashMap<Byte, String> lookupTable) 
    {
        if(!no.verificaFolha())
        {
            gerarLookupTableImpl(no.filhoEsquerda, string + '0', lookupTable);
            gerarLookupTableImpl(no.filhoDireita, string + '1', lookupTable);
        }
        else
        {
            lookupTable.atribuiChaveValor(no.data, string);
        }
    }

    //Gera a árvore Huffman é necessário usar uma fila com prioridade para o geramento correto do código binário 
    public static No gerarHuffmanArvore(myHashMap<Byte, Integer> freq)
    {
        
        filaPriorizada<No> pq = new filaPriorizada<>();
        int posicao = 0;
        for(int i=0;i < myHashMap.tamanho(); i++)
        {
            if((Integer)freq.vetorListaLigada[posicao].pega(1) > 0)
            {
                pq.enfileira((new No((byte)freq.vetorListaLigada[posicao].pega(0), (int)freq.vetorListaLigada[posicao].pega(1), null, null)));
                posicao++;
            }
        }
        if(pq.tamanho() == 1)
        {
            pq.enfileira(new No((byte)'\0', 1, null, null));
        }
        while(pq.tamanho() > 1)
        {
            No esquerda = pq.desenfileira();
            No direita = pq.desenfileira();
            No pai = new No((byte)'\0', esquerda.frequencia + direita.frequencia, esquerda , direita); 
            pq.enfileira(pai);
        }
        return pq.desenfileira();

    }  
    
    //Recebe os bytes e retorna um vetor de lista ligada que o byte como chave e seu valor como sua frequência
    public static myHashMap<Byte,Integer> gerarFreqTable(byte[] data)
    {
        
        myHashMap<Byte,Integer> freqMap = new myHashMap<>(10);
        
        for(byte bytes : data)
        {
            Integer count = (Integer)freqMap.pegarValor(bytes);
            if(count!= null)
            {
                freqMap.modificarValor(bytes, count+1);
            }
            else
            {
                freqMap.atribuiChaveValor(bytes, 1);
            }
            
        }
        return freqMap;
        
    }       
    public static myHashMap<Byte, String> getLookupTable()
    {
        return lookupTable;
    }
 
    
    //Recebe o binário resultado do algoritmo huffman e retorna uma string contendo o conteúdo descompactado
    public String decompress(HuffmanResultado resultado)
    {
        StringBuilder resultadoBuilder = new StringBuilder();

        No atual = resultado.getRaiz();
        int i = 0;
        while(i < resultado.getEncodedData().length())
        {
            while(!atual.verificaFolha())
            {
                char bit = resultado.getEncodedData().charAt(i);
                if(bit == '1')
                {
                    atual = atual.filhoDireita;
                }
                else if(bit == '0')
                {
                    atual = atual.filhoEsquerda;
                }
                else
                {
                    throw new IllegalArgumentException("Bit Invalido na mensagem" + bit);
                }
                i++;               
            }
            resultadoBuilder.append(atual.data + " ");
            atual = resultado.getRaiz();
        }
        return resultadoBuilder.toString();
    } 

    public static class HuffmanResultado
    {
        No raiz;
        String encodedData; 
        HuffmanResultado(String encodedData,  No raiz)
        {
            this.encodedData = encodedData;
            this.raiz = raiz;
        }
        public No getRaiz()
        {
            return this.raiz;
        }
        public String getEncodedData()
        {
            return this.encodedData;
        }
    }
    
    public static void main(String[] args) throws Exception 
    {
       
        byte[] bytesArquivo = leitorArquivo.lerArquivo();       
        CodigoHuffman encoder = new CodigoHuffman();
        HuffmanResultado resultado = encoder.compress(bytesArquivo);
       
        leitorArquivo.gerarArquivoCodificado(resultado.encodedData);
        byte[] bitsRecuperados = recuperarByts(encoder, resultado);
        leitorArquivo.gerarArquivoDescodificado(bitsRecuperados);
     

    }   
    //Recebe a string da descompactação do binário e retorna seus bytes
    public static byte[] recuperarByts(CodigoHuffman encoder, HuffmanResultado resultado)
    {
        String originalString = encoder.decompress(resultado);
        String[] bytesString = originalString.split(" ");
        byte[] bytes  = new byte[bytesString.length];
        for(int i = 0 ; i < bytes.length ; ++i)
        {
            bytes[i] = Byte.parseByte(bytesString[i]);
        }
        System.out.println(bytes);
        return bytes;
    }

}


