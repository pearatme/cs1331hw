package hw4;

public class DoctorCS {

    final int jlaid;
    final AI ai;
    final String secretIdentity;
    private boolean safe;

    public DoctorCS(AI ai, String secretIdentity, int jlaid) {
        this.ai = ai;
        this.secretIdentity = secretIdentity;
        this.jlaid = jlaid;
        this.safe = false;
    }

    public AI getAI() {
        return ai;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public int getJlaid() {
        return jlaid;
    }

    void saveTheDay() {
        if (ai instanceof RogueAI rogueAI)
            while (rogueAI.getFirewallProtection() > 0)
                rogueAI.lowerFirewall();
        safe = ai.swapCannonTarget(ai.getSecretHQ());
    }

    String getStatus() {
        if (safe) return "Doctor CS has saved the day!";
        if (ai.getDestructed()) return "Dr. Chipotle has succeeded in his plan...";
        return "Georgia Tech is still in danger!";
    }

    public String toString() {
        return String.format("%s aka Doctor CS with JLAID: %d", secretIdentity, jlaid);
    }

}
