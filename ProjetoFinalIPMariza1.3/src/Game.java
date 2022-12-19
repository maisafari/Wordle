
class Game {

	char[][] gameMattrix = new char[Constantes.MAX_LINES][Constantes.MAX_LINES];
	char[][] gameKeyBoard = Constantes.QWERTY;
	int [] gameKeyInt = new int[26];;
	Stats estatistica;
	String gameWord;
	Dictionary dicionario;
	ColorImage jogo;
	private int times;
	private String test;
	private String path = null;


	public Game(String imgPath) {
		// TODO Auto-generated constructor stub
		this.path = imgPath;
		this.dicionario  = new Dictionary("pt_br.txt");
		createJogo();
		initStat();
	}

	public Game(Dictionary dicionario) {
		// TODO Auto-generated constructor stub
		this.dicionario = dicionario;
		createJogo();
		initStat();

	}


	private void  createJogo(){
		this.times = 0;
		this.test = null;
		getRandomWord();
		intGameMatrix();
		initGameKeyInt();
		if(this.path != null){
			this.jogo = new ColorImage(this.path);
			if(this.jogo.getWidth() < Constantes.DEFAULT_WIDTH  || this.jogo.getHeight() < Constantes.DEFAULT_HEIGHT )
				throw new IllegalArgumentException("Image size not valid");

		}else{
			this.jogo = new ColorImage(Constantes.DEFAULT_WIDTH , Constantes.DEFAULT_HEIGHT, Constantes.ERROR_BG );
		}

		this.jogo.copyKeyBoardto(keyBoard(), this.jogo);
		this.jogo.copyBoardto(gameBoard(), this.jogo);

	}

	private void initStat(){
		this.estatistica= new Stats();
	}


	private void intGameMatrix(){

		for(int i = 0; i < this.gameMattrix.length; i++){
			for(int j = 0; j< this.gameMattrix[i].length; j++){
				this.gameMattrix[i][j] = ' ';
			}
		}
		return;
	}

	private void initGameKeyInt(){
		for(int i = 0; i < this.gameKeyInt.length; i++){
			this.gameKeyInt[i] = Constantes.UNTESTED;
		}
		return;
	}

	private void getRandomWord(){
		this.gameWord = this.dicionario.generateSecretWord(Constantes.MAX_LINES).toUpperCase();

	}

	// strings and chars
	private String toString(char chr){
		String s = "" + chr;
		return s.toUpperCase();

	}

	private	int getCharValue(char chr){
		int car = (int) chr;
		if(car < 65 || car > 90)
			throw new IllegalArgumentException("Argument has invalid characteers!");
		return (car - 65);
	}

	private int getCharState(char chr){
		int index =  getCharValue(chr);
		return this.gameKeyInt[index];
	}

	private	String toValidString(String str){
		char[][] accentsMap = Constantes.ACCENTS;
		if(str.length()!= Constantes.MAX_LINES)
			throw new IllegalArgumentException("Argument size is Invalid");
		int val;
		str = str.toUpperCase();
		String replace = null;
		String with = null;

		for(int i = 0; i < accentsMap.length; i++){
			for(int j =0; j < accentsMap[i].length; j++){
				val = str.indexOf(accentsMap[i][j]);
				if (val != -1){
					replace = toString(accentsMap[i][j]);
					with = toString(accentsMap[i][0]);
					str = str.replace(replace,with);

				}
			}
		}

		return str;
	}

	// Boards

	private ColorImage keyBoard(){
		int w = (Constantes.QWERTY[0].length  * Constantes.ICON_SIZE ) + ((Constantes.QWERTY[0].length -1) * Constantes.ICON_SPACING);
		int h = ((3 * Constantes.ICON_SIZE )+ (2 * Constantes.ICON_SPACING));
		int x = 0;
		int y = 0;
		ColorImage keyBoard = new ColorImage(w, h,Constantes.ERROR_BG );
		Color color = Constantes.EMPTY_BG;

		for(int i = 0 ; i < Constantes.QWERTY.length; i++){
			for(int j = 0; j < Constantes.QWERTY[i].length; j++){
				color = this.jogo.getValidColor(toString(Constantes.QWERTY[i][j]), this.gameKeyInt);
				if(x > keyBoard.getWidth() - Constantes.ICON_SIZE + Constantes.ICON_SPACING || j == 0){
					x = 0;	
				}
				if(y <= keyBoard.getHeight() - Constantes.ICON_SIZE + Constantes.ICON_SPACING){
					if(i == 1 && j == 0){
						x = x +  Constantes.ICON_SIZE/2;
					}
					if(i == 2 && j == 0){
						x = x + 3 * Constantes.ICON_SIZE/2;
					}	
					keyBoard = keyBoard.copyto(this.jogo.icon(toString(Constantes.QWERTY[i][j]), color),keyBoard, x, y);	
				}
				x =  x + Constantes.ICON_SIZE + Constantes.ICON_SPACING;
			}			
			y = y +  Constantes.ICON_SIZE + Constantes.ICON_SPACING;
		}
		return keyBoard;
	}



	private ColorImage gameBoard(){

		int offset = Constantes.ICON_SIZE * Constantes.MAX_LINES + Constantes.ICON_SPACING * (Constantes.MAX_LINES - 1);
		int	w = offset;
		int h = offset;

		ColorImage gameBoard = new ColorImage(w, h,Constantes.ERROR_BG );
		Color color = Constantes.EMPTY_BG;
		int x=0;
		int y=0;
		for(int i =0; i < this.gameMattrix.length; i++){

			for(int j =0; j <  this.gameMattrix[i].length; j++){
				color =  this.jogo.getValidColorBoard(this.gameMattrix[i][j], this.gameWord, j); 

				if(x > gameBoard.getWidth()-Constantes.ICON_SIZE + Constantes.ICON_SPACING || j==0)
					x=0;
				if(y <= gameBoard.getHeight()-Constantes.ICON_SIZE + Constantes.ICON_SPACING){
					gameBoard = gameBoard.copyto(this.jogo.icon(toString(this.gameMattrix[i][j]), color),gameBoard, x, y);
				}else{
					y = 0;
					gameBoard = gameBoard.copyto(this.jogo.icon(toString(this.gameMattrix[i][j]), color),gameBoard, x, y);
				}
				x =  x + Constantes.ICON_SIZE + Constantes.ICON_SPACING;
			}
			if(y <= gameBoard.getHeight()-Constantes.ICON_SIZE + Constantes.ICON_SPACING){
				y = y +  Constantes.ICON_SIZE + Constantes.ICON_SPACING;
			}else
			{
				y = 0;
				x = 0;
			}
		}
		return gameBoard;
	}


	//update
	private void updateCharState(char chr, int state){
		int index =  getCharValue(chr);
		if(index >= 0){
			this.gameKeyInt[index] = state;
		}
	}

	private void charCompare(String str){
		str = toValidString(str);
		String puzzle = toValidString(this.gameWord);
		int exists = -1;
		for(int i =0; i< str.length(); i++){

			exists = puzzle.indexOf(str.charAt(i));
			if(exists != -1){
				if(puzzle.charAt(i)== str.charAt(i)){
					updateCharState(str.charAt(i), Constantes.CORRECT_POSITION);
				}else{
					if(getCharState(str.charAt(i))!= Constantes.CORRECT_POSITION){
						updateCharState(str.charAt(i), Constantes.EXISTS);
					}

				}
			}else{
				if(getCharState(str.charAt(i))!= Constantes.CORRECT_POSITION){
					updateCharState(str.charAt(i), Constantes.NOT_EXISTS);
				}
			}
		} 

		return;
	}

	void play(String test){
		if(test == null)
			throw new NullPointerException("Argument can not be null !");

		charCompare(test);
		test = toValidString(test);
		if(this.times >= Constantes.MAX_ATEMPTS  || estatistica.getNumGames() == -1){
			return;
		}
		this.test = test;
		this.test = toValidString(test);
		charCompare(this.test);
		for( int i = 0; i < this.gameMattrix[this.times].length && i < this.test.length(); i++){
			if(this.gameMattrix[this.times][i]== ' '){
				this.gameMattrix[this.times][i]= test.charAt(i);
			}
		}
		this.jogo.copyBoardto(gameBoard(), this.jogo);
		this.jogo.copyKeyBoardto(keyBoard(), this.jogo);
		if(lossWin()!= Constantes.CONTINUE){
			this.estatistica.setVitorias(lossWin());
			this.estatistica.setTryPerGame(this.times);

			if(!estatistica.maxGames()){
				createJogo();
				this.estatistica.setGames();
				return;
			}else
				return;

		}
		this.times++;

		return;
	}

	private	boolean chekIfMatrixFull(){
		int	count = 0;

		for(int i = 0; i < this.gameMattrix.length ; i++){
			for(int j = 0; j < this.gameMattrix[i].length ; j++){
				if(this.gameMattrix[i][j] != ' '){
					count++;
				}
			}
		}
		if(count == (Constantes.MAX_LINES * Constantes.MAX_LINES))
			return true;
		return false;
	}

	private int lossWin(){
		//if matrix is full/ not full

		if(!chekIfMatrixFull()){
			if(this.test.equals(this.gameWord)){				
				return Constantes.WIN;
			}else				
				return Constantes.CONTINUE;		
		}else{
			if(this.test.equals(this.gameWord)){			
				return Constantes.WIN;
			}else{	
				return Constantes.LOSS;
			}	
		}
	}


	void showStatis(){

		this.estatistica.getStat();
		return ;
	}


}