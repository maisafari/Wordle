
/**
 * Represents color images.
 * Image data is represented as a matrix:
 * - the number of lines corresponds to the image height (data.length)
 * - the length of the lines corresponds to the image width (data[*].length)
 * - pixel color is encoded as integers (ARGB)
 */

class ColorImage {


	// data é uma matriz xy

	private int[][] data; // @colorimage


	// Construtors

	// cria objeto data a partir de x, y

	ColorImage(int width, int height) {
		data = new int[height][width];
	}

	// cria data a partir de uma imagem usando classe ImageUtil
	ColorImage(String file) {
		this.data = ImageUtil.readColorImage(file);
	}

	// cria data a partir de uma matriz xy
	ColorImage(int[][] data) {
		this.data = data;
	}

	//cria data a partir de coordenadas x y e pinta os pixles usando o procedimento setImage
	ColorImage(int width, int height, Color color) {
		data = new int[height][width];

		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				this.setColor(x, y, color);
			}
		}
	}

	// Metods

	// retorna x de data
	int getWidth() {
		return data[0].length;
	}
	// retorna comprimento de tada y
	int getHeight() {
		return data.length;
	}

	// utiliza a classe imageUtils para pintar  o pixel xy de data
	void setColor(int x, int y, Color c) {
		data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
	}

	// utiliza a clase ImageUtils para retornar a cor em xy
	// utiliza a classe Color para  criar e devolver a cor retornada pela classe ImageUtils
	Color getColor(int x, int y) {
		int[] rgb = ImageUtil.decodeRgb(data[y][x]);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

	// Text functions

	void drawText(int textX, int textY, String text, int textSize, Color textColor) {
		drawText(textX, textY, text, textSize, textColor, false);
	}


	void drawCenteredText(int textX, int textY, String text, int textSize, Color textColor) {
		drawText(textX, textY, text, textSize, textColor, true);
	}
	void drawCenteredImg(int textX, int textY, Color textColor) {
		drawImg(textX, textY, textColor, true);
	}

	ColorImage copy () {

		ColorImage img = new ColorImage (getWidth(), getHeight());

		for (int x = 0; x < this.getWidth(); x++)
			for (int y = 0; y < this.getHeight(); y++)
				img.setColor(x, y, getColor(x,y));

		return img;

	}

	void copyIcon(ColorImage img, ColorImage icon, int x, int y, int maxX, int maxY){
		for (int w = 0;  w < icon.getWidth() && x+w < maxX; w++){
			for (int h = 0; h < icon.getHeight() && y+h < maxY; h++){
				img.setColor(x+w, y+h, icon.getColor(w,h));
			}
		}
	}

	ColorImage copyIconto(ColorImage KeyBoard, ColorImage img) {
		int offset = Constantes.ICON_SIZE * Constantes.MAX_LINES + Constantes.ICON_SPACING * (Constantes.MAX_LINES - 1);
		int	offsetX = (img.getWidth() - offset)/2;
		int	offsetY = (img.getHeight()/2 - offset)/2;

		for (int x = offsetX; x< img.getWidth()-offsetX; x= x + Constantes.ICON_SIZE + Constantes.ICON_SPACING){
			for (int y = offsetY; y< img.getHeight()/2-offsetY; y= y + Constantes.ICON_SIZE + Constantes.ICON_SPACING){
				copyIcon(img, KeyBoard, x, y, img.getWidth(), img.getHeight());		

			}

		}
		return img;

	}

	ColorImage copyto(ColorImage icon, ColorImage img, int x, int y) {


		copyIcon(img, icon, x, y, img.getWidth(), img.getHeight());

		return img;
	}

	ColorImage copyKeyBoardto(ColorImage icon, ColorImage img) {
		int	offsetX = (img.getWidth() - icon.getWidth())/2;
		int	offsetY = (img.getHeight()/2 )+(img.getHeight()/2 - icon.getHeight())/2;

		copyIcon(img, icon, offsetX, offsetY, img.getWidth(), img.getHeight());

		return img;

	}
	
	ColorImage copyBoardto(ColorImage icon, ColorImage img) {
		int	offsetX = (img.getWidth()- icon.getWidth())/2;
		int	offsetY = (img.getHeight()/2 - icon.getHeight())/2;

		copyIcon(img, icon, offsetX, offsetY, img.getWidth(), img.getHeight());

		return img;

	}

	private void drawText(int textX, int textY, String text, int textSize, Color textColor, boolean isCentered) {
		int r = 255 - textColor.getR();
		int g = 255 - textColor.getG();
		int b = 255 - textColor.getB();

		Color maskColor = new Color(r, g, b);

		int encodedMaskRGB = ImageUtil.encodeRgb(r, g, b);

		int[][] aux = ImageUtil.createColorImageWithText(getWidth(), getHeight(), maskColor, textX, textY, text, textSize, textColor, isCentered);

		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[i].length; j++) {
				int value = aux[i][j];
				if(value != encodedMaskRGB) {
					data[i][j] = aux[i][j];
				}
			}
		}
	}


	private void drawImg(int textX, int textY, Color textColor, boolean isCentered) {
		int r = 255 - textColor.getR();
		int g = 255 - textColor.getG();
		int b = 255 - textColor.getB();

		Color maskColor = new Color(r, g, b);

		int encodedMaskRGB = ImageUtil.encodeRgb(r, g, b);

		int[][] aux = ImageUtil.createColorImage(getWidth(), getHeight(), maskColor, textX, textY, isCentered);

		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[i].length; j++) {
				int value = aux[i][j];
				if(value != encodedMaskRGB) {
					data[i][j] = aux[i][j];
				}
			}
		}
	}


	Color getValidColor(String chr, int[] gameKeyInt){

		Color color = Constantes.DEFAULT_BG; 
		chr.toUpperCase();
		int car = (int) chr.charAt(0);
		car = ( car - 65);


		int value = car;
		if(value >= 0 && value < gameKeyInt.length){

			value = gameKeyInt[value] ;
		}
		if(value== Constantes.CORRECT_POSITION){

			color = Constantes.CORRECT_BG ;
		}else if( value == Constantes.EXISTS){

			color = Constantes.EXISTS_BG ; 
		}else if(value == Constantes.NOT_EXISTS){
			color = Constantes.NOT_IN_WORD_BG ;
		}else{
			color = Constantes.DEFAULT_BG;  
		}

		return color;
	}


	Color getValidColorBoard(char str, String puzzle, int index){
		Color color = Constantes.DEFAULT_BG; 
		int exists = puzzle.indexOf(str);
		if(str == ' '){

			color = Constantes.EMPTY_BG ;
		}else{
			if(exists != -1){

				if(puzzle.charAt(index) == str){
					color = Constantes.CORRECT_BG ;
				}else{
					color = Constantes.EXISTS_BG ;

				}
			}else{
				color = Constantes.NOT_IN_WORD_BG;
			}
		}

		return color;
	}


	//create icon
	ColorImage icon(String chr, Color color){
		ColorImage icon = new ColorImage(Constantes.ICON_SIZE, Constantes.ICON_SIZE, color);
		icon.drawCenteredText(Constantes.ICON_SIZE/2, Constantes.ICON_SIZE/2, chr, 12, Constantes.CORRECT_BG);
		return icon;
	}

}
