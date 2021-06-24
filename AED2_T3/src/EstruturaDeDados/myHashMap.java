package EstruturaDeDados;

//Implementa um vetor de lista ligada que funciona como um hash map, em sua primeira posição contém uma chave, em sua segunda posição contém o valor.
public class myHashMap<K, T> 
{
  
    public listaLigada[] vetorListaLigada;
    private int capacidadeAtual = 0;
    private int posicao;
    static private int quantidadeElementos;
    public myHashMap(){}
    public myHashMap(int capacidade)
    {
        vetorListaLigada = new listaLigada[capacidade];
    }
    //Inicializa o vetor de lista ligada
    public void InicializaVetor()
    {
        for(int i = 0; i < 10; i++)
        {
            vetorListaLigada[i] = new listaLigada();
            capacidadeAtual++;
        }
    }
    //Adiciona na lista ligada em duas posições,a primeira posição que funciona como uma chave e sua segunda posição como seu valor.
    public void atribuiChaveValor(Object K, Object T)
    {
        if(posicao == capacidadeAtual && posicao != 0)
        {
            aumentarCapacidade();
        }
        if(vetorListaLigada[0] == null)
        {
            InicializaVetor();
        }
        for(int i = 0; i < capacidadeAtual; i++)
        {
            if(vetorListaLigada[i].contem(K))
            {
                System.out.println("Já contém essa chave");
                return;               
            }
        }                     
        for(int y = 0; y < 2; y++)
        {
            if(y == 0)
            {
                vetorListaLigada[posicao].adiciona(y, K);
            }
            else if(y == 1)
            {
                vetorListaLigada[posicao].adiciona(y, T);
            }     
        } 
        quantidadeElementos++;
        posicao++;
    }
    //Retorna o valor de uma chave que é passada como parâmetro, funciona como o método get implementado em collections hashmap
    public Object pegarValor(Object chave)
    {
        try
        {
            if(vetorListaLigada[0] == null)
            {
                InicializaVetor();
            }
            else
            {
                for(int i = 0; i < capacidadeAtual; i++)
                {
                    if(vetorListaLigada[i].pega(0).equals(chave))
                    {
                        return vetorListaLigada[i].pega(1);
                    }
                }
            }       
            return null;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    //Recebe uma chave e modifica seu valor
    public void modificarValor(byte chave, int count)
    {
        for(int i = 0; i < myHashMap.quantidadeElementos; i++)
        {
            if(vetorListaLigada[i].contem(chave))
            {
                vetorListaLigada[i].remove(1);
                vetorListaLigada[i].adiciona(1, count);
            }           
        }
           
       
    }
    public static int tamanho()
    {
        return quantidadeElementos;
    }
    private void aumentarCapacidade() 
    {
        int novaCapacidade = 0;
        listaLigada[] novaListaLigada = new listaLigada[capacidadeAtual *2];
        for(int i = 0; i < capacidadeAtual*2; i++)
        {
            novaListaLigada[i] = new listaLigada();
            novaCapacidade = i;             
        }
        for(int y = 0; y < capacidadeAtual; y++)
        {
            novaListaLigada[y] = vetorListaLigada[y];                
        }    
        capacidadeAtual = novaCapacidade + 1;
        vetorListaLigada = novaListaLigada;
    }
}