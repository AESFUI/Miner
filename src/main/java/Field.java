import java.util.Date;
import java.util.Random;

/**
 * Created by AESFUI on 05.08.2017.
 */
public class Field {

    private int sizeX;
    private int sizeY;
    private int mines;
    private int field[][];


    private Field(int sizeX, int sizeY, int mines) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mines = mines;
        this.field = new int [sizeX][sizeY];
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    /**
     * Создание объекта поля
     */
    public Field createField(int sizeX, int sizeY, int mines) throws Exception {
        if (sizeX < 2 && sizeY < 2) {
            throw new Exception("Недопустимый размер поля!\n" +
            "sizeX = " + sizeX + "\nsizeY = " + sizeY);
        }

        Field field = new Field(sizeX, sizeY, mines);
        fillByMines(field);
        return field;
    }

    /**
     * Наполнение поля минами со значением "9"
     */
    private void fillByMines(Field field) {
        Date dateForRandom = new Date();
        final Random random = new Random(dateForRandom.getTime());
    }

    public void outField(Field field) {
        for (int y = 0; y < field.getSizeY(); y++)
            for (int x = 0; x < field.getSizeX(); x++) {
                System.out.print(field.getField()[y][x] + " ");
            }
        System.out.println();
    }
}
