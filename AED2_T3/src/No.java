import EstruturaDeDados.IComparador;

public class No implements IComparador<No>
{
    public byte data;
    public int frequencia;
    public No filhoEsquerda;
    public No filhoDireita;
    public int pai;
    public int index;

    public No() 
    {

    }
    public No(Byte data, int frequencia) 
    {
        this.data = data;
        this.frequencia = frequencia;
    }
    public No(byte data, int frequencia, No filhoEsquerda, No filhoDireita)
    {
        this.data = data;
        this.frequencia = frequencia;
        this.filhoEsquerda = filhoEsquerda;
        this.filhoDireita = filhoDireita;
    }
    boolean verificaFolha()
    {
        return this.filhoDireita == null && this.filhoEsquerda == null;
    }
    //Compara as frequências
    public int compareTo(No that)
    {
        int comparacaoFrequencia = Integer.compare(this.frequencia, that.frequencia);
        if(comparacaoFrequencia != 0)
        {
            return comparacaoFrequencia;
        }
        return Integer.compare(this.data, that.data);
    }
}
