//хранит количество кораблей игрока и компьютера
public class Ships {
    private int playerShips;
    private int computerShips;

    public Ships(int pShips, int cShips) {
        this.playerShips = pShips;
        this.computerShips = cShips;
    }

    public int getNumberOfPlayerShips() {
        return playerShips;
    }

    public int getNumberOfComputerShips() {
        return computerShips;
    }

    public void setNumberOfPlayerShips(int numb) {
        this.playerShips = numb;
    }

    public void setNumberOfComputerShips(int numb) {
        this.computerShips = numb;
    }
}

