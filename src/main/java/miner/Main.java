package miner;

import static miner.Field.createField;
import static miner.Field.outField;

/**
 * Created by AESFUI on 05.08.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Field field = createField(5, 6, 7);
        outField(field);
    }
}
