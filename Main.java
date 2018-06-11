import java.util.*;
import java.lang.Math;

public class Main {
    public static void greetings(){
        System.out.println("**** Добро пожаловать в игру морской бой! ****");
        System.out.println("Сейчас карта пустая");
        System.out.println("После установки кораблей,ваши корабли будут отображаться на карте как \"@\"");
        System.out.println("Ваши потопленные корабли будут отображаться как \"x\"");
        System.out.println("Потопленные корабли компьютера будут отображаться как \"!\"");
        System.out.println("Клетки с промахом будут отображаться как \"-\"");
    }
    //Создаёт карту
    public static String[][] createOceanMap() {
        String map[][] = new String[10][10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = " ";
                System.out.println(map[i][j]);
            }
        }
        return map;
    }

    //Отображает карту
    public static void showOceanMap(String map[][]) {
        System.out.println();
        System.out.println();
        System.out.println("   0123456789   ");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].equals("1")) {
                    System.out.print("@");
                } else if (map[i][j].equals("2")) {
                    System.out.print(" ");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.print("| " + i + "\n");
        }
        System.out.println("   0123456789   ");
        System.out.println();
        System.out.println();
    }

    //Устанавливает корабли игрока
    public static String[][] choosePlayerShips(String map[][], Scanner input) {
        int x;
        int y;
        String playerShipsMap[][] = map;
        System.out.println("Установите ваши корабли:");
        for (int i = 1; i <= 5; i++) {
            while (true) {
                System.out.print("Введите координаты Х для вашего " + i + ". корабля: ");
                x = input.nextInt();
                System.out.print("Введите координаты Y для вашего " + i + ". корабля: ");
                y = input.nextInt();
                if (x < 0 || x > 9) {
                    System.out.println("Вы ввели неверные координаты X. Введите 0-9.");
                } else if (y < 0 || y > 9) {
                    System.out.println("Вы ввели неверные координаты Y. Введите 0-9.");
                } else if (!playerShipsMap[x][y].equals(" ")) {
                    System.out.println("Вы не можете установить ваш корабль здесь,введите другие координаты.");
                } else {
                    playerShipsMap[x][y] = "1";
                    break;
                }
            }
        }
        showOceanMap(playerShipsMap);
        return playerShipsMap;
    }

    //Устанавливает корабли компьютера
    public static String[][] chooseComputerShips(String playerMap[][]) {
        int x;
        int y;
        String computerShipsMap[][] = playerMap;
        System.out.println("Компьютер устанавливает корабли");
        for (int i = 1; i <= 5; i++) {
            label:
            while (true) {
                x = (int) (Math.random() * 10);
                y = (int) (Math.random() * 10);
                if (x < 0 || x > 9) {
                    continue label;
                } else if (y < 0 || y > 9) {
                    continue label;
                } else if (!computerShipsMap[x][y].equals(" ")) {
                    continue label;
                } else {
                    computerShipsMap[x][y] = "2";
                    System.out.println(i + ". корабль УСТАНОВЛЕН");
                    break;
                }
            }
        }
        System.out.println("---------------------------");
        System.out.println();
        return computerShipsMap;
    }

    //очередь игрока для выстрела
    public static String[][] playersTurn(String gameMap[][], Scanner input, Ships obj) {
        String mapWithTurn[][] = gameMap;
        int x;
        int y;
        System.out.println("ВАША ОЧЕРЕДЬ");
        while (true) {
            System.out.print("Введите координаты Х: ");
            x = input.nextInt();
            System.out.print("Введите координаты Y: ");
            y = input.nextInt();
            if (x < 0 || x > 9) {
                System.out.println("Вы ввели неверные координаты Х. Введите 0-9.");
            } else if (y < 0 || y > 9) {
                System.out.println("Вы ввели неверные координаты Y. Введите 0-9.");
            } else if (mapWithTurn[x][y].equals("2")) {
                mapWithTurn[x][y] = "!";
                System.out.println("Бум! Вы потопили корабль противника!");
                obj.setNumberOfComputerShips(obj.getNumberOfComputerShips() - 1);
                break;
            } else if (mapWithTurn[x][y].equals("1")) {
                mapWithTurn[x][y] = "x";
                System.out.println("О нет,вы потопили свой корабль :(");
                obj.setNumberOfPlayerShips(obj.getNumberOfPlayerShips() - 1);
                break;
            } else if (mapWithTurn[x][y].equals(" ")) {
                mapWithTurn[x][y] = "-";
                System.out.println("Sorry, you missed");
                break;
            }
        }
        showOceanMap(mapWithTurn);
        return mapWithTurn;
    }

    //очередь компьютера для выстрела
    public static String[][] computerTurn(String gameMap[][], Ships obj) {
        String mapWithTurn[][] = gameMap;
        int x;
        int y;
        System.out.println("ОЧЕРЕДЬ КОМПЬЮТЕРА");
        label:
        while (true) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            if (x < 0 || x > 9) {
                continue label;
            } else if (y < 0 || y > 9) {
                continue label;
            } else if (mapWithTurn[x][y].equals("2")) {
                mapWithTurn[x][y] = "!";
                System.out.println("Компьютер потопил один из своих кораблей!");
                obj.setNumberOfComputerShips(obj.getNumberOfComputerShips() - 1);
                break;
            } else if (mapWithTurn[x][y].equals("1")) {
                mapWithTurn[x][y] = "x";
                System.out.println("Компьютер потопил один из ваших кораблей!");
                obj.setNumberOfPlayerShips(obj.getNumberOfPlayerShips() - 1);
                break;
            } else if (mapWithTurn[x][y].equals(" ")) {
                mapWithTurn[x][y] = "-";
                System.out.println("Компьютер промахнулся");
                break;
            }
        }
        showOceanMap(mapWithTurn);
        return mapWithTurn;

    }

    public static void main(String[] args) {
        String map[][] = createOceanMap();
        greetings();
        showOceanMap(map);
        Scanner input = new Scanner(System.in);
        String playersShips[][] = choosePlayerShips(map, input);
        String fullMap[][] = chooseComputerShips(playersShips);
        Ships ships = new Ships(5, 5);
        while (true) {
            playersTurn(fullMap, input, ships);
            System.out.println("Ваши корабли: " + ships.getNumberOfPlayerShips() + " | Корабли компьютера: "
                    + ships.getNumberOfComputerShips());
            System.out.println("---------------------------------");
            System.out.println();

            computerTurn(fullMap, ships);
            System.out.println("Ваши корабли: " + ships.getNumberOfPlayerShips() + " | Корабли компьютера: "
                    + ships.getNumberOfComputerShips());
            System.out.println("---------------------------------");
            System.out.println();
            if (ships.getNumberOfPlayerShips() == 0) {
                System.out.println("КОМПЬЮТЕР ВЫИГРАЛ");
                break;
            } else if (ships.getNumberOfComputerShips() == 0) {
                System.out.println("ВЫ ВЫИГРАЛИ");
                break;
            }
        }
    }

}
