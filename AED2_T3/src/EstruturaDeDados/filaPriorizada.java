package EstruturaDeDados; 


public class filaPriorizada<T> extends Fila<T>
{
	//Enfileira a fila em ordem
    @SuppressWarnings("unchecked")
	public void enfileira(T elemento){
		
		IComparador<T> chave = (IComparador<T>) elemento;
		
		
		int i;
		for (i=0; i<this.tamanho; i++){
			if (chave.compareTo(this.elementos[i]) < 0 ){
				break;
			}
		}
		this.adiciona(i, elemento);
		
	}		

}