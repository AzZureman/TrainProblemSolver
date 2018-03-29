package train;

import java.util.concurrent.ThreadLocalRandom;

public class Train implements ICar {

    private Car headCar;
    private Car currentCar;

    private int carsCount;

    private int MIN = 10;
    private int MAX = 20;

    public Train() {

        headCar = new Car(randomLightState());
        Car previousCar = headCar;
        carsCount = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);

        int counter = carsCount - 1;
        while (counter > 0) {
            previousCar.setNextCar(new Car(randomLightState()));
            previousCar.getNextCar().setPreviosCar(previousCar);
            previousCar = previousCar.getNextCar();
            counter--;
        }
        previousCar.setNextCar(headCar);
        headCar.setPreviosCar(previousCar);

        currentCar = headCar;

    }

    protected Car getHeadCar() {
        return headCar;
    }

    protected Car getCurrentCar() {
        return currentCar;
    }

    protected int getCarsCount() {
        return carsCount;
    }

    private LightState randomLightState(){
        return ThreadLocalRandom.current().nextBoolean() ? LightState.ON : LightState.OFF;
    }

    @Override
    public boolean getCurrentCarLightState() {
        return currentCar.getCarBooleanState();
    }

    @Override
    public void currentCarLightOn() {
        currentCar.setCarLightState(LightState.ON);
    }

    @Override
    public void currentCarLightOff() {
        currentCar.setCarLightState(LightState.OFF);
    }

    @Override
    public void next() {
        currentCar = currentCar.getNextCar();
    }

    @Override
    public void previous() {
        currentCar = currentCar.getPreviosCar();
    }

    private enum LightState { ON, OFF }

    protected class Car {
        private LightState carLightState;
        private Car nextCar;
        private Car previousCar;

        private Car(LightState lightState) {
            carLightState = lightState;
        }

        private LightState getCarLightState() {
            return carLightState;
        }

        protected boolean getCarBooleanState() {
            return lightStateToBoolean(carLightState);
        }

        private boolean lightStateToBoolean(LightState lightState) {
            boolean value = false;
            switch (lightState) {
                case ON:
                    value = true;
                    break;
                case OFF:
                    value = false;
                    break;
            }
            return value;
        }

        private void setCarLightState(LightState carLightState) {
            this.carLightState = carLightState;
        }

        protected Car getNextCar() {
            return nextCar;
        }

        private void setNextCar(Car nextCar) {
            this.nextCar = nextCar;
        }

        protected Car getPreviosCar() {
            return previousCar;
        }

        private void setPreviosCar(Car previosCar) {
            this.previousCar = previosCar;
        }
    }


}