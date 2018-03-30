package solvers;

import train.ICar;

import javafx.util.Pair;
import java.util.List;

public class AzZuSolver implements ISolver {

    private ICar train;
    private int counter;

    private int solved;
    private int steps;
    private int cwSteps;
    private int ccwSteps;
    private boolean returning;
    enum Direction { CLOCKWISE, COUNTERCLOCKWISE }
    private Direction direction;

    public AzZuSolver() {
        counter = 0;
        solved = -1;
        steps = 0;
        cwSteps = 0;
        ccwSteps = 0;
        returning = false;
        direction = Direction.CLOCKWISE;
    }

    @Override
    public void setTrain(ICar Train) {
        train = Train;

    }

    @Override
    public int step() {
        if (solved < 0){  //тройной вложенный if - возможно, стоит попробовать разбить на подметоды (которые включают if'ы) - сложно читать
            if (!returning) {
                if (steps < getDirectionSteps()) { //(!getDirectionChecked())
                    directionStep();
                }
                else {
                    if (!checkDirection() && steps != 0) {
                        solved = cwSteps + ccwSteps;
                    } else {
                        directionStep();
                        setCarState();
                        incDirectionSteps();
                        returning = true;
                    }
                }
                counter++;
            } else {
                returnStep();
            }
        }
        return solved;
    }

    private int getDirectionSteps() {
        int directionSteps = 0;
        switch (direction) {
            case CLOCKWISE:
                directionSteps = cwSteps;
                break;
            case COUNTERCLOCKWISE:
                directionSteps = ccwSteps;
                break;
        }
        return directionSteps;
    }

    private void incDirectionSteps() {
        switch (direction) {
            case CLOCKWISE:
                cwSteps++;
                break;
            case COUNTERCLOCKWISE:
                ccwSteps++;
                break;
        }
    }

    private boolean checkDirection() {
        boolean directionCheck = false;
        switch (direction) {
            case CLOCKWISE:
                directionCheck = train.getCurrentCarLightState();
                break;
            case COUNTERCLOCKWISE:
                directionCheck = !train.getCurrentCarLightState();
                break;
        }
        return directionCheck;
    }

    private void directionStep() {
        switch (direction) {
            case CLOCKWISE:
                train.next();
                steps++;
                break;
            case COUNTERCLOCKWISE:
                train.previous();
                steps++;
                break;
        }
        if (steps == 0) returning = false;
    }

    private void returnStep() {
        switch (direction) {
            case CLOCKWISE:
                train.previous();
                steps--;
                break;
            case COUNTERCLOCKWISE:
                train.next();
                steps--;
                break;
        }
        if (steps == 0) {
            returning = false;
            changeDirection();
        }
    }

    private void changeDirection() {
        switch (direction) {
            case CLOCKWISE:
                direction = Direction.COUNTERCLOCKWISE;
                break;
            case COUNTERCLOCKWISE:
                direction = Direction.CLOCKWISE;
                break;
        }

    }

    private void setCarState() {
        switch (direction) {
            case CLOCKWISE:
                train.currentCarLightOn();
                break;
            case COUNTERCLOCKWISE:
                train.currentCarLightOff();
                break;
        }
    }

    @Override
    public List<Pair<String, String>> getState() {
        return List.of(
                new Pair<>("counter", String.valueOf(counter)),
                new Pair<>("steps", String.valueOf(steps)),
                new Pair<>("cwSteps", String.valueOf(cwSteps)),
                new Pair<>("ccwSteps", String.valueOf(ccwSteps)),
                new Pair<>("returning", String.valueOf(returning)),
                new Pair<>("direction", String.valueOf(direction)),
                new Pair<>("solved", String.valueOf(solved))
        );
    }

}
