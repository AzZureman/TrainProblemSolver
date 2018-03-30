package solvers;

import train.ICar;
import javafx.util.Pair;
import java.util.List;

//тоже бы выделил в отдельный пакет, не мешал бы с кастомными солверами
//комментами можно пояснить, когда и зачем вызывать эти методы - например, step()
//если получится очень сложно, возможно, есть смысл пересмотреть апи
public interface ISolver
{
    void setTrain(ICar train);
    int step();
    List<Pair<String, String>> getState();
}
