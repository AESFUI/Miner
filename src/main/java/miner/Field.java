package miner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        this.cells = new int[sizeX][sizeY];
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

    protected int[][] getCells() {
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
                    "sizeX = " + (sizeX - 1) + "\nsizeY = " + (sizeY - 1));
        }
        if (mines < 1 || mines > (sizeX * sizeY)) {
            throw new Exception("Недопустимое количество мин!\n" +
                    "mines = " + mines + "\nsizeX = " + (sizeX - 1) + "\nsizeY = " + (sizeY - 1));
        }

        //поле для удобства манипуляций будет с невидимой границей шириной 1 клетка
        Field field = new Field(sizeX + 2, sizeY + 2, mines);
        fillByMines(field);
        return field;
    }

    /**
     * Наполнение поля минами со значением "9"
     */
    private static void fillByMines(Field field) {
        Date dateForRandom = new Date();
        final Random random = new Random(dateForRandom.getTime());

        for (int m = field.getMines(); m > 0; ) {
            int x = random.nextInt(field.getSizeX() - 1);
            int y = random.nextInt(field.getSizeY() - 1);
            if (x > 0 && y > 0 && field.cells[x][y] != 9) {
                field.cells[x][y] = 9;
                m--;
            }
        }

        //outField(field);

        calculatingMines(field);
    }

    /**
     * Расчёт количества мин для каждой клетки
     **/
    private static void calculatingMines(Field field) {
        for (int y = 1; y < field.getSizeY() - 1; y++) {
            for (int x = 1; x < field.getSizeX() - 1; x++) {
                if (field.getCells()[x][y] != 9) {
                    int minesAround = 0;
                    for (int yShift = -1; yShift < 2; yShift++) {
                        for (int xShift = -1; xShift < 2; xShift++) {
                            if (field.getCells()[x + xShift][y + yShift] == 9) {
                                minesAround++;
                            }
                        }
                    }
                    field.getCells()[x][y] = minesAround;
                }
            }
        }
    }

    /**
     * Вывод поля на экран
     */
    static void outField(Field field) {
        for (int y = 1; y < field.getSizeY() - 1; y++) {
            for (int x = 1; x < field.getSizeX() - 1; x++) {
                int cell = field.getCells()[x][y];
                if (cell == 9) {
                    System.out.print("\033[30m" + "¤" + " ");
                } else if (cell == 0) {
                    System.out.print(" " + " ");
                } else if (cell == 1) {
                    System.out.print("\033[34m" + cell + " ");
                } else if (cell == 2) {
                    System.out.print("\033[32m" + cell + " ");
                } else if (cell == 3) {
                    System.out.print("\033[31m" + cell + "\033[22m ");
                } else if (cell == 4) {
                    System.out.print("\033[35m" + cell + " ");
                } else if (cell == 5) {
                    System.out.print("\033[36m" + cell + " ");
                } else if (cell == 6) {
                    System.out.print("\033[33;1m" + cell + " ");
                } else if (cell == 7) {
                    System.out.print("\033[30m" + cell + " ");
                } else if (cell == 8) {
                    System.out.print("\033[31;1m" + cell + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Метод проверяет принадлежность клетки полю
     *
     * @param field поле
     * @param x     координата по горизонтали
     * @param y     координата по вертикали
     * @return
     */
    public static Boolean isInField(Field field, int x, int y) {
        if (x > 0 & x < field.sizeX - 1 & y > 0 & y < field.sizeY - 1)
            return true;
        else
            return false;
    }

    /**
     * Открывает целиком часть поля с "0"
     * @param field
     * @param x
     * @param y
     */
    public static void openNullableCells(Field field, int x, int y) {
        List<Integer> list = new ArrayList<>();

        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                if (isInField(field, i, j)) {
                    if (field.getCells()[x + j][y + i] == 0) {
                        field.getCells()[x + j][y + i] = 10;
                        //list.add();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String info = "Field{" +
                "sizeX = " + (sizeX - 2) +
                ", sizeY = " + (sizeY - 2) +
                ", mines = " + mines + '}';

        String lineVertical = "\u2502";

        StringBuilder field = new StringBuilder();
        String out = new String();

        for (int y = 1; y < sizeY - 1; y++) {
            for (int x = 1; x < sizeX - 1; x++) {
                int cell = this.getCells()[x][y];

                if (cell == 9) {
                    out = "\033[30m" + "¤" + "\033[0m";
                } else if (cell == 0) {
                    out = "\033[37m" + cell + "\033[0m";
                } else if (cell == 1) {
                    out = "\033[34m" + cell + "\033[0m";
                } else if (cell == 2) {
                    out = "\033[32m" + cell + "\033[0m";
                } else if (cell == 3) {
                    out = "\033[31m" + cell + "\033[0m";
                } else if (cell == 4) {
                    out = "\033[35m" + cell + "\033[0m";
                } else if (cell == 5) {
                    out = "\033[36m" + cell + "\033[0m";
                } else if (cell == 6) {
                    out = "\033[33m" + cell + "\033[0m";
                } else if (cell == 7) {
                    out = "\033[30m" + cell + "\033[0m";
                } else if (cell == 8) {
                    out = "\033[31m" + cell + "\033[0m";
                }

                field.append(lineVertical + out);
            }
            field.append(lineVertical + "\n");
        }

        return info + "\n\n" + (field == null ? "" : field);
    }
}