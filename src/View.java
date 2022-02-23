/**
 * Author: Jishan Sharif
 * Revised: 12/04/2021
 *
 * Description: Game simulation here
 */

package src;

import java.util.Scanner;
/**
 * @brief This is where we communicate with the user! This file takes the role
 * of a controller as well as a View module.
 */

public class View{
        String greetings = "Welcome to 2048!\n";
        String start = "Here's the starting board, you will need to get to 2048!\n Good luck!\n";
        String instructions = "Use the commands mentioned below to move the tiles. Tiles with the same " +
                "number merge into one when they touch. Add them up to reach 2048!. 'r' means to go right, 'l' means" +
                "to go right, 'u' means to go up, 'p'  to view your points and 'e' to end the game! That simple!";

        private static boolean checkWinner(int score){
                if (score < 2048){
                        return false;
                }
                return true;
        }
        public static void main(String[] args) {
                View view = new View();
                System.out.println(view.greetings);
                System.out.println(view.instructions);
                System.out.println(view.start);
                BoardT trial = new BoardT();
                TileT[][] board = trial.getBoard();
                for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                                System.out.print(board[i][j] + " ");
                        }

                        System.out.println();
                }


                System.out.println("Start the game by typing in 'r', 'l', 'u' or 'd' or 'p' to check points or 'e' to end");
                Scanner userInputs = new Scanner(System.in);
                String move = userInputs.nextLine();
                while (true){
                        if (move.equals("r")){
                                trial.moveRight();
                                TileT[][] board1 = trial.getBoard();
                                for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                                System.out.print(board1[i][j] + " ");
                                        }

                                        System.out.println();
                                }
                                if (!trial.noMoreMoves(board)){
                                        move = userInputs.nextLine();
                                } else {
                                        break;
                                }


                        } else if (move.equals("l")){
                                trial.moveLeft();
                                TileT[][] board1 = trial.getBoard();
                                for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                                System.out.print(board1[i][j] + " ");
                                        }

                                        System.out.println();
                                }
                                if (!trial.noMoreMoves(board)){
                                        move = userInputs.nextLine();
                                } else {
                                        break;
                                }
                        } else if (move.equals("p")){
                                int score = trial.getScore();
                                System.out.println(score);
                                move = userInputs.nextLine();
                        } else if (move.equals("e")){
                                System.out.println("Thanks for playing! Your final score is " + trial.getScore());
                                if (checkWinner(trial.getScore())){
                                        System.out.println("Congratulations, you won!");
                                } else {
                                        System.out.println("Better luck next time!");
                                }

                                break;
                        } else if (move.equals("u")){
                                trial.moveUp();
                                TileT[][] board1 = trial.getBoard();
                                for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                                System.out.print(board1[i][j] + " ");
                                        }

                                        System.out.println();
                                }
                                if (!trial.noMoreMoves(board)){
                                        move = userInputs.nextLine();
                                } else {
                                        System.out.println("You have no more possible moves!\n");
                                        System.out.println("Thanks for playing! Your final score is " + trial.getScore());
                                        if (checkWinner(trial.getScore())){
                                                System.out.println("Congratulations, you won!");
                                        } else {
                                                System.out.println("Better luck next time!");
                                        }
                                        break;
                                }
                        } else if (move.equals("d")){
                                trial.moveDown();
                                TileT[][] board1 = trial.getBoard();
                                for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                                System.out.print(board1[i][j] + " ");
                                        }

                                        System.out.println();
                                }
                                if (!trial.noMoreMoves(board)){
                                        move = userInputs.nextLine();
                                } else {
                                        break;
                                }
                        } else {
                                System.out.println("It seems that you have entered an invalid move!\n");
                                move = userInputs.nextLine();

                        }

                }
        }
}
