package hw4;

public abstract class AI {

    Coordinates cannonTarget, secretHQ;
    boolean destructed;

    AI(Coordinates cannonTarget, Coordinates secretHQ) {
        this.cannonTarget = cannonTarget;
        this.secretHQ = secretHQ;
        destructed = false;
    }

    public Coordinates getCannonTarget() {
        return cannonTarget;
    }

    public Coordinates getSecretHQ() {
        return secretHQ;
    }

    public boolean getDestructed() {
        return destructed;
    }

    public boolean swapCannonTarget (Coordinates newCoords) {
        if (destructed || newCoords == cannonTarget)
            return false;

        if (shouldSwapCannonTarget()) {
            cannonTarget = newCoords;
            return true;
        } else {
            if (shouldSelfDestruct())
                selfDestruct();
            return false;
        }
    }

    public void selfDestruct() {
        destructed = true;
    }

    @Override
    public String toString() {
        return String.format("Dr. Chipotle’s guacamole cannon is currently pointed at %s.", cannonTarget);
    }

    public abstract boolean shouldSwapCannonTarget();
    public abstract boolean shouldSelfDestruct();


}
