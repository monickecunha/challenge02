package model;

public enum RiskLevel {
    SAFE(0, 10, "Seguro"),
    LOW(11, 30, "Risco Baixo"),
    MEDIUM(31, 60, "Risco Médio"),
    HIGH(61, 90, "Risco Alto"),
    CRITICAL(91, Integer.MAX_VALUE, "Risco Crítico");

    private final int min;
    private final int max;
    private final String label;


    RiskLevel(int min, int max, String label) {
        this.min = min;
        this.max = max;
        this.label = label;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getLabel() {
        return label;
    }

    public static RiskLevel fromScore(int score) {
        for (RiskLevel level : RiskLevel.values())  {
            if(score >= level.min && score <= level.max)    {
                return level;

            }
        }
        return CRITICAL;
    }
}
