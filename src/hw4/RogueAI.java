package hw4;

public class RogueAI extends AI {

    final int maxAlert;
    int firewallProtection, alertLevel;

    public RogueAI(
            int firewallProtection,
            int alertLevel,
            int maxAlert,
            Coordinates cannonTarget,
            Coordinates secretHQ
    ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = alertLevel;
        this.maxAlert = maxAlert;
    }

    public RogueAI(int firewallProtection, int maxAlert, Coordinates cannonTarget, Coordinates secretHQ) {
        this(firewallProtection, 0, maxAlert, cannonTarget, secretHQ);
    }

    public RogueAI(int firewallProtection, Coordinates cannonTarget, Coordinates secretHQ) {
        this(firewallProtection, 0, 10, cannonTarget, secretHQ);
    }

    public int getFirewallProtection() {
        return firewallProtection;
    }

    public int getAlertLevel() {
        return alertLevel;
    }

    public int getMaxAlert() {
        return maxAlert;
    }

    public void lowerFirewall() {
        firewallProtection -= 2;
        alertLevel++;
    }

    @Override
    public boolean shouldSwapCannonTarget() {
        return firewallProtection <= 0;
    }

    @Override
    public boolean shouldSelfDestruct() {
        return alertLevel >= maxAlert;
    }

    public String toString() {
        String superString = super.toString();
        return String.format("%s, and is at alert level %d with firewall protection %d",
                superString.substring(0, superString.length() - 1), alertLevel, firewallProtection);
    }
}
