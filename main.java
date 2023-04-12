public class main {
    public static void main(String[] args){
    Player p1 = new Player("Hitarth");
    System.out.println(p1);
    Player p2 = new Player("hada");
    System.out.println(p2);

    Board board = new Board(3);
    Game game = new onePlayergame(board, new Player[]{p1, p2});
    game.play();
    }
}
