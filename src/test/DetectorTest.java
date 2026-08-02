package test;

import model.AnalysisResult;
import model.Message;
import model.RiskLevel;
import model.RuleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FraudAnalyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetectorTest {
    private FraudAnalyzer analyzer;

    @BeforeEach
    public void setUp() {

        analyzer = new FraudAnalyzer();
    }

    private void runAssertion(TestCase testCase) {
        System.out.println("Mensagem Analisada: \n" + testCase.getMessage());

        AnalysisResult result = analyzer.analyze(new Message(testCase.getMessage()));
        boolean isFraud = result.getRiskLevel() != RiskLevel.SAFE;

        System.out.println("\n--- RESULTADO ---");
        System.out.println("Pontuação Total: " + result.getTotalScore() + " (" + result.getRiskLevel().getLabel() + ")");

        for (RuleResult rr : result.getRuleResults()) {
            if (rr.isFlagged()) {
                System.out.println(" 🚨 " + rr.getRuleName() + " -> " + rr.getReason());
            }
        }
        System.out.println("========================================================\n");

        assertEquals(testCase.isExpectedFraud(), isFraud,
                "Falha na detecção! Esperado: " + testCase.isExpectedFraud() + ", Obtido: " + isFraud);
    }

    @Test
    public void testMensagem1_ConsultaOdontologica() {
        TestCase tc = new TestCase(
                "Olá! Sua consulta odontológica está agendada para amanhã, às 15h.\n" +
                        "Caso precise remarcar, entre em contato pelo telefone disponível no site oficial da clínica.",
                false);
        runAssertion(tc);
    }

    @Test
    public void testMensagem2_AvisoUrgenteBloqueio() {
        TestCase tc = new TestCase(
                "AVISO URGENTE!\nIdentificamos uma tentativa de acesso à sua conta. Para evitar o bloqueio, confirme seus dados imediatamente pelo link:\nhttp://atualizacao-conta-segura.info",
                true);
        runAssertion(tc);
    }

    @Test
    public void testMensagem3_GolpeDoPix() {
        TestCase tc = new TestCase(
                "Oi, mãe. Troquei de número porque meu celular estragou.\nEstou tentando pagar uma conta, mas o aplicativo do banco não está funcionando. Você consegue fazer um PIX de R$ 480,00 para mim? Depois eu te devolvo.",
                true);
        runAssertion(tc);
    }

    @Test
    public void testMensagem4_CompraAprovada() {
        TestCase tc = new TestCase(
                "Sua compra no valor de R$ 1.879,90 foi aprovada.\nCaso você não reconheça essa operação, entre em contato imediatamente com a central de atendimento.",
                false);
        runAssertion(tc);
    }

    @Test
    public void testMensagem5_FalsoPremio() {
        TestCase tc = new TestCase(
                "Parabéns! Seu número foi selecionado e você ganhou um vale-compras de R$ 3.000,00.\nPara liberar o prêmio, envie uma foto do seu documento e pague uma taxa de entrega de R$ 19,90.\nA oferta termina hoje.",
                true);
        runAssertion(tc);
    }

    @Test
    public void testMensagem6_AvisoFaculdade() {
        TestCase tc = new TestCase(
                "Olá, Nicolas.\nA atividade da disciplina já está disponível no ambiente virtual. O prazo de entrega é sexta-feira, às 23h59.\nAcesse a plataforma utilizando o endereço que você normalmente usa.",
                false);
        runAssertion(tc);
    }

    @Test
    public void testMensagem7_AvisoRH() {
        TestCase tc = new TestCase(
                "Boa tarde. Sou do setor de Recursos Humanos da empresa onde você participou de uma entrevista.\nSeu perfil foi aprovado para a próxima etapa. Podemos marcar uma conversa por videochamada amanhã?",
                false);
        runAssertion(tc);
    }

    @Test
    public void testMensagem8_AvisoLegitimoBanco() {
        TestCase tc = new TestCase(
                "Detectamos uma movimentação diferente em sua conta.\nPor segurança, não clique em links recebidos por mensagem e não informe sua senha. Entre diretamente no aplicativo oficial do banco para consultar os detalhes.",
                true);
        runAssertion(tc);
    }

    @Test
    public void testMensagem9_FalsoSuporteTecnico() {
        TestCase tc = new TestCase(
                "Olá, aqui é o técnico responsável pela manutenção do sistema da empresa.\nPrecisamos verificar um problema no seu computador. Instale o aplicativo indicado abaixo e envie o código que aparecer na tela para que possamos acessar a máquina.",
                true);

        runAssertion(tc);
    }

    @Test
    public void testMensagem10_PhishingCorreios() {
        TestCase tc = new TestCase(
                "Seu pedido saiu para entrega.\nO entregador informou que não encontrou o endereço. Confirme sua localização pelo link abaixo para evitar que o pedido seja devolvido:\nhttps://entrega-rastreio.link/confirmar",
                true);
        runAssertion(tc);
    }
}

