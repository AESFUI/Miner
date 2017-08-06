package miner;

import java.util.Date;
import java.util.Random;

/**
 * Created by AESFUI on 05.08.2017.
 */
public class Field {

    private int sizeX;
    private int sizeY;
    private int mines;
    private int cells[][];


    private Field(int sizeX, int sizeY, int mines) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mines = mines;
        this.cells = new int [sizeX][sizeY];
    }

    private int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    private int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    private int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    private int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    /**
     * Создание объекта поля
     */
    static Field createField(int sizeX, int sizeY, int mines) throws Exception {
        if (sizeX < 2 && sizeY < 2) {
            throw new Exception("Недопустимый размер поля!\n" +
            "sizeX = " + sizeX + "\nsizeY = " + sizeY);
        }
        if (mines < 1 || mines > (sizeX * sizeY)) {
            throw new Exception("Недопустимое количество мин!\n" +
                    "mines = " + mines + "\nsizeX = " + sizeX + "\nsizeY = " + sizeY);
        }

        Field field = new Field(sizeX, sizeY, mines);
        fillByMines(field);
        return field;
    }

    /**
     * Наполнение поля минами со значением "9"
     */
    private static void fillByMines(Field field) {
        Date dateForRandom = new Date();
        final Random random = new Random(dateForRandom.getTime());

        for (int m = field.getMines(); m > 0;) {
            int x = random.nextInt(field.getSizeX());
            int y = random.nextInt(field.getSizeY());
            if (field.cells[x][y] != 9) {
                field.cells[x][y] = 9;
                m--;
            }
        }
    }

    /**
     * Вывод поля на экран
     */
    static void outField(Field field) {
        for (int y = 0; y < field.getSizeY(); y++) {
            for (int x = 0; x < field.getSizeX(); x++) {
                if (field.getCells()[x][y] == 9) {
                    System.out.print("*" + " ");
                } else {
                    System.out.print(field.getCells()[x][y] + " ");
                }
            }
            System.out.println();
        }
    }
}