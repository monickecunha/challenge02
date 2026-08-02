import model.AnalysisResult;
import model.Message;
import model.RiskLevel;
import model.RuleResult;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- TESTANDO AS CLASSES DO MODELO ---");

        Message mensagem = new Message("URGENTE: Sua CÔNTA será blôqueada amanhã!");
        System.out.println("Texto Original: " + mensagem.getOriginalText());
        System.out.println("Texto Normalizado: " + mensagem.getNormalizedText());
        System.out.println("Contém 'conta'? " + mensagem.contains("conta")); // Deve retornar true

        System.out.println("\n--- SIMULANDO REGRAS ---");

        RuleResult regraUrgencia = new RuleResult("Regra de Urgência", 40, true, "Palavra URGENTE encontrada");
        RuleResult regraBloqueio = new RuleResult("Regra de Bloqueio", 35, true, "Ameaça de bloqueio detectada");
        RuleResult regraLinkSeguro = RuleResult.notFlagged("Regra de Link"); // Regra que não achou nada

        List<RuleResult> resultados = new ArrayList<>();
        resultados.add(regraUrgencia);
        resultados.add(regraBloqueio);
        resultados.add(regraLinkSeguro);

        AnalysisResult analiseFinal = new AnalysisResult(resultados);

        System.out.println("\n--- RESULTADO FINAL ---");
        System.out.println("Pontuação Total: " + analiseFinal.getTotalScore());

        RiskLevel nivelDeRisco = analiseFinal.getRiskLevel();
        System.out.println("Nível de Risco: " + nivelDeRisco.getLabel() + " (Esperado Alto ou Crítico)");
    }
}