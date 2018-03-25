package Train;

import java.util.concurrent.ThreadLocalRandom;

public class Train implements ICar {

    protected boolean[] Cars;
    protected int CurrentCarNumber;
    protected int CarsCount;

    private int MIN = 10;
    private int MAX = 50;

    public Train() {

        CarsCount = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);

        Cars = new boolean[CarsCount];

        for (int i = 0; i < CarsCount; i++) Cars[i] = ThreadLocalRandom.current().nextBoolean();

        CurrentCarNumber = 0;
    }

    public boolean getCarValue(int Index) {
        return Cars[Index];
    }

    public void setCarValue(int Index, boolean value) {
        Cars[Index] = value;
    }

    public boolean getCurrentCarState()
    {
        return Cars[CurrentCarNumber];
    }

    public void switchCurrentCarState()
    {
        Cars[CurrentCarNumber] = !Cars[CurrentCarNumber];
    }

    public void next() {
        CurrentCarNumber = (CurrentCarNumber < CarsCount - 1) ? ++CurrentCarNumber : 0;
    }

    public void previous() {
        CurrentCarNumber = (CurrentCarNumber > 0) ? --CurrentCarNumber : CarsCount - 1;
    }

}