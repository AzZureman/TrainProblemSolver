package solvers;

import javafx.util.Pair;
import train.ICar;

import java.util.ArrayList;
import java.util.List;

public class UnSolver implements ISolver {

    private ICar train;
    private int counter;

    public UnSolver() {
        counter = 0;
    }

    @Override
    public void setTrain(ICar Train) {
        train = Train;

    }

    @Override
    public int step() {
        counter++;
        train.next();
        return -1;
    }

    @Override
    public List<Pair<String, String>> getState() {
        List params = new ArrayList();
        params.add(new Pair<>("counter", String.valueOf(counter)));
        return params;
    }
}

