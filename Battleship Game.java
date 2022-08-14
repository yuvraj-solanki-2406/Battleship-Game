package com.company;
import java.util.Scanner;
public class Main {

   public static int rows = 10;
   public static int cols = 10;
   public static int playersship ;
   public static int computersship ;
   public static String[][] oceanArea = new String[rows][cols];

    public static void main(String[] args) {

      creatingOcean();
      placingShipsInOcean();
      do {
          fightingBattle();
      }while (Main.playersship != 0 && Main.computersship != 0);
      gameOver();
    }

    private static void creatingOcean() {

        System.out.print("  ");
        for(int i = 0; i < rows; i++)
            System.out.print(i);
        System.out.println();

        for(int i = 0; i < oceanArea.length; i++) {
            for (int j = 0; j < oceanArea[i].length; j++) {
                oceanArea[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + oceanArea[i][j]);
                else if (j == oceanArea[i].length - 1)
                    System.out.print(oceanArea[i][j] + "|" + i);
                else
                    System.out.print(oceanArea[i][j]);
            }
            System.out.println();
        }

        System.out.print(" " + " ");
        for(int i = 0; i < rows; i++)
            System.out.print(i);

       // printTheOcean();
    }

    private static void placingShipsInOcean() {
        Scanner input = new Scanner(System.in);

//for player
        System.out.println("\n Place your ship in the Ocean");
        Main.playersship = 5;
        for ( int i = 1; i <= 5;){
            System.out.print("Enter X coordinate of your" + " " + i + " " + "ship:");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate of your" + " " + i + " " + "ship:");
            int y = input.nextInt();

            if ( (x>0 && x<rows) && (y>0 && y<cols) && (oceanArea[x][y] == " ")){
                oceanArea[x][y] = "@";
                i++;
            }else if ( (x>0 && x<rows) && (y>0 && y<cols) && (oceanArea[x][y] == "@")){
                System.out.println("Place the ship at other location");
            }else if ( (x<0 || x > rows) || (y<0 || y>cols) ){
                System.out.println("Put the ship in the Ocean area only");
            }
        }

//for computer
        System.out.println("\n Computer placing ship in the Ocean");
        Main.computersship = 5;
        for (int i = 1 ; i <= Main.computersship;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);

            if ((x > 0 && x < rows) && (y > 0 && y < cols) && (oceanArea[x][y] == " ")) {
                oceanArea[x][y] = "x";
                System.out.println(i + ".ship placed");
                i ++ ;
            }
        } printTheOcean();
    }

    private static void fightingBattle() {
        System.out.println("Let the Battle Begins");
         playerChance();
         computerChance();
         printTheOcean();
        System.out.println();
        System.out.println("Your ships" + " " + Main.playersship + "| Computer ships" + " " + Main.computersship);
    }

    private static void computerChance() {
        System.out.println("Computers Chance");
int x = -2 ; int y = -1 ;
do {
    Scanner input = new Scanner(System.in);
    x = (int) (Math.random()*10);
    y = (int) (Math.random()*10);
    if ( (x>0 && x<= rows) && (y>0 && y<= cols)) {
        if ((oceanArea[x][y] == "@")) {
            System.out.println("Computer hits your ship");
            oceanArea[x][y] = "!";
            --Main.playersship;
        } else if ((oceanArea[x][y] == "x")) {
            System.out.println("Yeah! Computer hits its own ship");
            oceanArea[x][y] = "!";
            --Main.computersship;
        } else if ((oceanArea[x][y] == " ")) {
            System.out.println("Computer misses target");
            oceanArea[x][y] = "";
        }

    }else if ((x < 0 || x >= rows) || (y < 0 || y >= cols)) {
        System.out.println("You can't place ships outside the " + rows + " by " + cols + " oceanArea");
    }
    }while ((x < 0 || x >= rows) || (y < 0 || y >= cols)) ;
    }

    private static void playerChance() {
int x = -1 ; int y = -1 ;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your x coordinate");
             x = input.nextInt();
            System.out.println("Enter your y coordinate");
             y = input.nextInt();

            if ((x > 0 && x <= rows) && (y > 0 && y <= cols) ) {

               if ((oceanArea[x][y] == "x")){
                   System.out.println("Boom! You sank the ship");
                   oceanArea[x][y] = "!";
                   --Main.computersship;
               }
                else if ((oceanArea[x][y] == "@")) {
                    System.out.println("Ooh No! You sank your own ship");
                    oceanArea[x][y] = "!";
                    --Main.playersship;
                } else if ((oceanArea[x][y] == " ")) {
                   System.out.println("Sorry , missed the target");
                   oceanArea[x][y] = "-";
               }
                }
                  else if ((x < 0 || x >= rows) || (y < 0 || y >= cols)) {
                    System.out.println("The ships are placed within (" + rows + " by " + cols + ") oceanArea \n So enter a valid coordinate");
                }
        }   while ((x < 0 || x >= rows) || (y < 0 || y >= cols)) ;

            }

    private static void printTheOcean() {
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < cols; i++)
            System.out.print(i);
        System.out.println();

        for(int x = 0; x < oceanArea.length; x++) {
            System.out.print(x + "|");
            for (int y = 0; y < oceanArea[x].length; y++){
                System.out.print(oceanArea[x][y]);
            }
            System.out.println("|" + x);
        }

        System.out.print("  ");
        for(int i = 0; i < cols; i++)
            System.out.print(i);
        System.out.println();
    }

    private static void gameOver() {
if ( Main.playersship <= 0  && Main.computersship > 0  ){
    System.out.println("You Lose" + "\n Better Luck Next Time");
} else{
    System.out.println("Congratulations , You won the Battle");
}
    }

}
