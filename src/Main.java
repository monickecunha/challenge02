
import model.AnalysisResult;
import model.Message;
import model.RuleResult;
import service.FraudAnalyzer;

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
// AVISO URGENTE: Sua conta foi bloqueada por motivos de segurança. Para recuperar seu acesso e evitar multas, clique no link e confirme sua Senha e seu Pix imediatamente: http://bit.ly/recuperacao-conta
// PARABÉNS! Você foi o grande vencedor do nosso sorteio de fim de ano e acaba de ganhar um smartphone de última geração! Para liberar o envio, pague apenas a Taxa de entrega informando o CVV do seu cartão no site. Acesse: tinyurl.com/premio-liberado
// Trabalho remoto urgente! Ganhe até 5 mil por semana trabalhando de casa. Sem experiência necessária. Faça seu cadastro e pague a taxa de adesão via Pix agora mesmo: http://encurta.net/vaga-garantida

// Oi, tudo bem? Tô passando no mercado depois da aula, quer que eu compre alguma coisa pro jantar de hoje? Me avisa se precisar de algo.
// Olá! O fechamento da sua fatura do cartão de crédito ocorreu hoje. O valor já está disponível para consulta no seu aplicativo oficial do banco. Tenha um ótimo dia!
// Pessoal, a nossa reunião de alinhamento do projeto vai começar daqui a pouco, às 14h. Segue o link da sala para todo mundo entrar: meet.google.com/sala-de-reuniao