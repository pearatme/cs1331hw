package hw4;

import java.util.Random;

public class RandomAI extends AI {

    Random random;

    public RandomAI(Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        random = new Random();
    }

    @Override
    public boolean shouldSwapCannonTarget() {
        return random.nextBoolean();
    }

    @Override
    public boolean shouldSelfDestruct() {
        return random.nextBoolean();
    }

}
