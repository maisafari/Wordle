
public class test {


	static void  run(){
		Dictionary dicio = new Dictionary("pt_br.txt");
		Game game = new Game(dicio);
		return;
	}
	static void  run2(){
		//Dictionary dicio = new Dictionary("pt_br.txt");
		Game game = new Game("coffee.jpg");
		return;
	}
}
