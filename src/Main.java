
import model.AnalysisResult;
import model.Message;
import model.RuleResult;
import service.FraudAnalyzer;
import service.RulesConfig;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Mensagens de teste");

        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a mensagem que será verificada:");

        String textoDigitado = "";

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();

            if (linha.isEmpty()) {
                break;
            }

            textoDigitado += linha + "\n";
        }

        Message message = new Message(textoDigitado);
        AnalysisResult result = fraudAnalyzer.analyze(message);

        System.out.println("\nResultado da Análise");
        System.out.println("Risco: " + result.getRiskLevel());
        System.out.println("Pontuação Total: " + result.getTotalScore());

        for (RuleResult rule : result.getRuleResults()) {
            if (rule.isFlagged()) {
                System.out.println("Motivo: " + rule.getReason());
            }
        }

    }
}