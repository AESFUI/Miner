package miner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static miner.Field.*;

/**
 * Created by AESFUI on 05.08.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Boolean gameOver = false;

        Field field = createField(10, 10, 40);
        //outField(field);
        System.out.println(field);

        int x, y;

        do {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            x = Integer.parseInt(reader.readLine());
            y = Integer.parseInt(reader.readLine());

            if (isInField(field, x, y)) {
                if (field.getCells()[x][y] == 9) {
                    gameOver = true;
                } else if (field.getCells()[x][y] > 0) {
                    field.getCells()[x][y]+=10;
                } else {
                    field.getCells()[x][y]+=10;
                    openNullableCells(field, x, y);
                }
            }
        }
        while (!gameOver);
    }
}
