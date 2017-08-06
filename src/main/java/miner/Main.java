package miner;

import static miner.Field.createField;
import static miner.Field.outField;

/**
 * Created by AESFUI on 05.08.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Field field = createField(10, 10, 10);
        outField(field);
    }

    /**
     * Вывод статистики минирования
     **/
    public static void outStat() {

    }
}
