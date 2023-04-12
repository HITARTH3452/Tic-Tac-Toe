import java.util.*;
public class Game {
    Board board;
    Player p1; 
    Player[] players;
    int turn;
    int noofMoves;
    boolean gameOver;
    String zeroPattern;
    String crossPattern;

    public static Scanner sc = new Scanner(System.in);

    Game(Board board, Player[] players){
          this.board = board;
          this.players= players;
          this.turn = 0;
          this.noofMoves = 0;
          this.gameOver = false;
          zeroPattern = "";
          crossPattern = "";

          for(int i=0; i<board.size; i++){
            zeroPattern += 'O';
            crossPattern += 'X';
          }
    }
    public void play(){
        System.out.print(board);
        while(true){
            this.noofMoves++;
            if(this.noofMoves > board.size*board.size){
                System.out.println("Match draw");
            }
            int[] index = getIndex();
            int rn = index[0], cn = index[1];
            board.board[rn][cn] = players[turn].symbol;
            if(this.noofMoves >= 2*board.size-1 && checkifGameisended()){
                System.out.print(board);
                this.gameOver = true;
                System.out.print(this.players[turn].name+" has won !!");
                return;
            }
            turn = (turn+1)%2;
            System.out.print(board);
        }
    }
    public int[] getIndex(){
        while(true){
        System.out.println(players[turn].name+"'s turn give index");
        int index = sc.nextInt()-1;
        int rn = index/board.size, cn = index%board.size;
        if(rn<0 || cn<0 || rn >= board.size || cn >= board.size){
            System.out.println("Out of bound indrx");
            continue;
        }
        if(board.board[rn][cn] != '-'){
            System.out.println("Position already filled, try again");
            continue;
        }
        return new int[]{rn,cn};
    }
}
    public boolean checkifGameisended(){
    //rows
    StringBuilder sb;
    for(int i=0; i<board.size; i++){
        sb = new StringBuilder();
        for(int j=0; j<board.size; j++){
            sb.append(board.board[i][j]);
            if(getResult(sb)) return true;
        }
    }
    //column
    for(int i=0; i<board.size; i++){
        sb = new StringBuilder();
        for(int j=0; j<board.size; j++){
            sb.append(board.board[j][i]);
            if(getResult(sb)) return true;
        }
    }
    //major diagonal
    int i=0, j=0;
    sb = new StringBuilder();
    while(i<board.size){
        sb.append(board.board[i++][j++]);
    }
    if(getResult(sb))return true;

    //minor diagonal
    i =0 ; j = board.size-1;
    sb = new StringBuilder();
    while(i<board.size){
        sb.append(board.board[i++][j--]);
    }
    if(getResult(sb)) return true;
    return false;
    }

    public boolean getResult(StringBuilder sb){
     if(sb.toString().equals(zeroPattern) || sb.toString().equals(crossPattern)) return true;
     return false;
    }
}

