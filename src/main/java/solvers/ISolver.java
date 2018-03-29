package solvers;

import train.ICar;
import javafx.util.Pair;
import java.util.List;

public interface ISolver
{
    void setTrain(ICar train);
    int step();
    List<Pair<String, String>> getState();
}
