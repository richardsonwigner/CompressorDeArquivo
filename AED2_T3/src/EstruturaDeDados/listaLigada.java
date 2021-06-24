package EstruturaDeDados;
public class listaLigada
{
	private Celula primeira = null;	
	private int totalDeElementos = 0;

	//Adiciona um elemento no começo da lista
	public void adicionaNoComeco(Object elemento) 
	{
		//Para adicionar no começo faz a nova célula apontar para a primeira.
        Celula nova = new Celula(elemento, primeira);
        this.primeira = nova;  
        this.totalDeElementos++;
    }
	//Adiciona um elemento em qualquer posição da lista, caso o total de elementos for zero chama o metódo adicionaNoComeco	
	public void adiciona(int posicao, Object elemento) 
	{
		if(posicao == 0) 
		{
			adicionaNoComeco(elemento);
		} 
		else 
		{
			//Pega a posição anterior da posição que foi passada, faz o próximo da nova célula ser o próximo do anterior e o próximo do anterior ser a nova célula.
			Celula anterior = this.pegaCelula(posicao - 1);
			Celula nova = new Celula(elemento, anterior.getProximo());
			anterior.setProximo(nova);
			this.totalDeElementos++;
		}
	}	
	//Remove um elemento que está no inicio da lista, manda uma exceção caso a lista for vazia.
	public void removeDoComeco() 
	{
		if (this.totalDeElementos == 0) 
		{
			throw new IllegalArgumentException("Lista vazia");
		}
		//Faz a primeira celula apontar para o próximo, assim tirando o primeiro elemento da lista
		this.primeira = primeira.getProximo();
		totalDeElementos--;		
	}
	//Remove um elemento em qualquer posição da lista, caso a posição passada seja 0, chama o metódo removeDoComeco.
	public void remove(int posicao) 
	{
		if (posicao == 0) 
		{
			removeDoComeco();
		} 		
		else 
		{		            
			//Pega a posição anterior da posição que foi passada, faz com que a célula atual seja a próximo do anterior, e a proxima celula ser a proxima do atual.             
			Celula anterior = this.pegaCelula(posicao - 1);
			Celula atual = anterior.getProximo();
			Celula proximo = atual.getProximo();				
			anterior.setProximo(proximo);			

			this.totalDeElementos--;
		}
	}

	//Verifica se uma posição esteja ocupada
	public boolean posicaoOcupada(int posicao)
	{
		return posicao >= 0 && posicao < this.totalDeElementos;
	}

	//Verifica se na lista existe o elemento
	public boolean contem(Object elemento) 
	{
		Celula atual = this.primeira;
		while (atual != null) 
		{
			//Percorre a lista até achar o elemento que foi passado pelo parâmetro , caso não ache faz com que a célula atual aponte para o próximo da atual.
			//Retorna true se o elemento existe, caso não retorna false.
			if (atual.getElemento().equals(elemento)) 
			{
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}

	//retorna um elemento da lista.
	public Object pega(int posicao) 
	{
		return pegaCelula(posicao).getElemento();
	}
	
	//Metódo para auxilar nos metódos de adicionar
	private Celula pegaCelula(int posicao) 
	{
		if (!posicaoOcupada(posicao)) 
		{
			throw new IllegalArgumentException("Posicao inválida");
		}
		Celula atual = primeira;
		//Percorre a lista fazendo a célula atual apontar para o proximo da célula atual.
		for (int i = 0; i < posicao; i++)
		{
			atual = atual.getProximo();
		}
		return atual;
	}

	//Retorna o total de elementos
	public int tamanho() 
	{
		return this.totalDeElementos;
	}
	//Metódo toString 
	public String toString () 
	{
		if(this.totalDeElementos == 0) 
		{
			return " ";
		}	
		Celula atual = primeira;
	
		StringBuilder builder = new StringBuilder("");
	
		for(int i = 0; i < totalDeElementos; i++) 
		{
			builder.append(atual.getElemento());
			builder.append(" ");
	
			atual = atual.getProximo();
		}
	
		builder.append(" ");
	
		return builder.toString();
	}

}
