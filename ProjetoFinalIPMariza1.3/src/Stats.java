

public class Stats {

	private int[] vitorias = new int[Constantes.MAX_GAMES];
	private int[] tryPerGame = new int[Constantes.MAX_GAMES];
	private int numGames = 1;
	private int numConsecutiveVictories = 0;
	private int maxSequenceOfVisctories = 0;
	ColorImage estatistica;


	public Stats() {
	}
	
	private void ptintTryPerGame(){
		int[] trys = getTryPerGame();
		int size = trys.length;

		for(int i = 0; i < size; i++){

			this.estatistica.drawCenteredText(Constantes.DEFAULT_WIDTH/2, Constantes.DEFAULT_WIDTH /2 - Constantes.ICON_SIZE *(2-i), "GAME: "+ i + ", Tentativas: " + trys[i], 24, Constantes.DEFAULT_BG);
		}
	}

	public void getStat(){
		this.estatistica = new ColorImage(Constantes.DEFAULT_WIDTH , Constantes.DEFAULT_HEIGHT, Constantes.ERROR_BG );
		this.estatistica.drawCenteredText(Constantes.DEFAULT_WIDTH/2, Constantes.DEFAULT_WIDTH /2 - Constantes.ICON_SIZE * 5, "Jogados: " + getNumGames(), 24, Constantes.DEFAULT_BG);
		this.estatistica.drawCenteredText(Constantes.DEFAULT_WIDTH/2, Constantes.DEFAULT_WIDTH/2 - Constantes.ICON_SIZE * 4, " % vitorias: " + ((getVitories()/Constantes.MAX_GAMES )*100), 24, Constantes.DEFAULT_BG);
		this.estatistica.drawCenteredText(Constantes.DEFAULT_WIDTH/2, Constantes.DEFAULT_WIDTH /2 - Constantes.ICON_SIZE * 3, "Maior sequencia de vitorias: " + getmaxSequenceVict(), 24, Constantes.DEFAULT_BG);
		ptintTryPerGame();

		return ;
	}
	

	int [] getTryPerGame(){
		return this.tryPerGame;
	}

	void setVitorias(int vitoria){
		System.out.println("jogo: " + this.numGames +", vitorias: "+ vitoria );
		this.vitorias[this.numGames - 1]=vitoria;
	}

	public void setTryPerGame(int times) {
		this.tryPerGame[this.numGames-1]= times + 1 ;
	}


	void setGames(){
		this.numGames++;
	}

	int getNumGames(){
		return this.numGames;
	}
	
	boolean maxGames(){
		return(this.numGames == Constantes.MAX_GAMES);
	}
	
	int getmaxSequenceVict(){
		return this.maxSequenceOfVisctories;
	}

	double getVitories(){
		int count = 0;
		int consecutive = 0;
		int aux = 0;

		for(int i = 0; i < this.vitorias.length; i++){
			if(this.vitorias[i] == Constantes.WIN){
				count++;
				consecutive++;

			}else{
				if(consecutive >= aux){
					aux = consecutive;

				}consecutive = 0;
			}
		}
		this.maxSequenceOfVisctories = consecutive;
		System.out.println(" vitorias end game: "+count );
		return count;
	}






}
