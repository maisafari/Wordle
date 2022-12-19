 

public class Constantes {

	
	// Color pallete
	
		static final Color CORRECT_BG = new Color (59, 163, 148);
		static final Color EXISTS_BG = new Color (212, 173, 106);
		static final Color NOT_IN_WORD_BG = new Color (48, 42, 44);
		static final Color EMPTY_BG = new Color (96, 84, 88);
		static final Color DEFAULT_BG = new Color (111, 92, 98);
		static final Color ERROR_BG = new Color (0, 154, 254);


	// Size defaults
	
		static final int ICON_SIZE = 40;
		static final int ICON_SPACING = 4;

	
	// Coding
	
		static final int CORRECT_POSITION = 3;
		static final int EXISTS = 2;
		static final int NOT_EXISTS = 1;
		static final int UNTESTED = 0;


	// Game setup
		static final int MAX_GAMES = 5;
		static final int MAX_ATEMPTS = 5;
		static final int MAX_LINES = 5;
		static final int DEFAULT_WIDTH = 700;
		static final int DEFAULT_HEIGHT = 600;

		static final char [][] QWERTY = {
			{'Q','W','E','R','T','Y','U','I','O','P'},
			{'A','S','D','F','G','H','J','K','L'}, 
			{'Z','X','C','V','B','N','M'}
			};

		static final char [][] ACCENTS = {
				{'A', 'Á','À', 'Ã', 'Â','Ä'},
				{'a', 'á','à','ã','â','ä'},
				{'E', 'É','È','Ê','Ë'},
				{'e', 'é','è','ê','ë'},
				{'I', 'Í','Ì','Î','Ï'},
				{'i', 'í','ì','î','ï'},
				{'O', 'Ó','Ò','Ô','Õ','Ö'},
				{'o', 'ó','ò','ô','õ','ö'},
				{'U', 'Ú','Ù','Û','Ü'},
				{'u', 'ú','ù','û','ü'},
				{'C', 'Ç'},
				{'c', 'ç'},
				{'N', 'Ñ'},
				{'n', 'ñ'}
		};
		static final int WIN = 1;
		static final int LOSS= -1;
		static final int CONTINUE= 0;
		
}