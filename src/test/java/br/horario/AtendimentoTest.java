package br.horario;

import br.pratico.Horario;
import br.pratico.ServicoRemoto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AtendimentoTest {

    private ServicoRemoto servicoMock;
    private Horario horarioAtendimento;

    @BeforeEach
    void setUp() {
        servicoMock = mock(ServicoRemoto.class);
        horarioAtendimento = new Horario(servicoMock);
    }

    //TESTES CENÁRIO DE SUCESSO

    @Test
    @DisplayName("Teste sala 1 deve retornar prédio 1")
    void testSala1Predio1() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Maria\", \"horarioDeAtendimento\":\"13:00\", \"periodo\":\"integral\", \"sala\":\"1\", \"predio\":[\"1\",\"2\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 1"));
    }

    @Test
    @DisplayName("Teste sala 5 deve retornar prédio 1")
    void testSala5Predio1() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"João\", \"horarioDeAtendimento\":\"14:00\", \"periodo\":\"noturno\", \"sala\":\"5\", \"predio\":[\"1\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 1"));
    }

    @Test
    @DisplayName("Teste sala 6 deve retornar prédio 2")
    void testSala6Predio2() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Carlos\", \"horarioDeAtendimento\":\"15:00\", \"periodo\":\"integral\", \"sala\":\"6\", \"predio\":[\"2\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 2"));
    }

    @Test
    @DisplayName("Teste sala 10 deve retornar prédio 2")
    void testSala10Predio2() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Ana\", \"horarioDeAtendimento\":\"16:00\", \"periodo\":\"noturno\", \"sala\":\"10\", \"predio\":[\"2\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 2"));
    }

    @Test
    @DisplayName("Teste sala 11 deve retornar prédio 3")
    void testSala11Predio3() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Bruno\", \"horarioDeAtendimento\":\"10:00\", \"periodo\":\"integral\", \"sala\":\"11\", \"predio\":[\"3\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 3"));
    }

    @Test
    @DisplayName("Teste nome, período e horário válidos")
    void testCamposValidos() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Daniela\", \"horarioDeAtendimento\":\"17:00\", \"periodo\":\"noturno\", \"sala\":\"7\", \"predio\":[\"2\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prof: Daniela"));
        assertTrue(resultado.contains("Horário: 17:00"));
        assertTrue(resultado.contains("Período: noturno"));
    }

    @Test
    void testSalaComLimiteSuperiorPredio5() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Paulo\", \"horarioDeAtendimento\":\"18:00\", \"periodo\":\"integral\", \"sala\":\"25\", \"predio\":[\"5\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 5"));
    }

    @Test
    void testSalaComLimiteInferiorPredio4() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Larissa\", \"horarioDeAtendimento\":\"19:00\", \"periodo\":\"noturno\", \"sala\":\"16\", \"predio\":[\"4\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 4"));
    }

    @Test
    void testSalaMedianaPredio3() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Thiago\", \"horarioDeAtendimento\":\"08:00\", \"periodo\":\"integral\", \"sala\":\"13\", \"predio\":[\"3\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 3"));
    }

    @Test
    void testSala20Predio4() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Fernanda\", \"horarioDeAtendimento\":\"09:00\", \"periodo\":\"noturno\", \"sala\":\"20\", \"predio\":[\"4\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 4"));
    }

    //TESTES CENÁRIOS DE FALHA

    @Test
    void testCampoSalaAusente() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Carlos\", \"horarioDeAtendimento\":\"10:00\", \"periodo\":\"integral\", \"predio\":[\"1\"] }");

        assertThrows(Exception.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testSalaNaoNumerica() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"João\", \"horarioDeAtendimento\":\"10:00\", \"periodo\":\"integral\", \"sala\":\"x\", \"predio\":[\"1\"] }");

        assertThrows(NumberFormatException.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testJSONVazio() {
        when(servicoMock.obterHorarioJSON()).thenReturn("{}");
        assertThrows(Exception.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testJSONMalFormado() {
        when(servicoMock.obterHorarioJSON()).thenReturn("{ nomeDoProfessor: 'Erro' ");
        assertThrows(Exception.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testCampoPeriodoAusente() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Pedro\", \"horarioDeAtendimento\":\"12:00\", \"sala\":\"3\", \"predio\":[\"1\"] }");

        assertThrows(Exception.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testCampoHorarioAusente() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Lucas\", \"periodo\":\"noturno\", \"sala\":\"2\", \"predio\":[\"1\"] }");

        assertThrows(Exception.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testCampoNomeAusente() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"horarioDeAtendimento\":\"11:00\", \"periodo\":\"integral\", \"sala\":\"4\", \"predio\":[\"1\"] }");

        assertThrows(Exception.class, () -> horarioAtendimento.processarHorario());
    }

    @Test
    void testSalaNegativa() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Nina\", \"horarioDeAtendimento\":\"07:00\", \"periodo\":\"noturno\", \"sala\":\"-1\", \"predio\":[\"1\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 0"));
    }

    @Test
    void testSalaZero() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Nico\", \"horarioDeAtendimento\":\"07:00\", \"periodo\":\"noturno\", \"sala\":\"0\", \"predio\":[\"1\"] }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 0"));
    }

    @Test
    void testPredioArrayAusente() {
        when(servicoMock.obterHorarioJSON()).thenReturn(
                "{ \"nomeDoProfessor\":\"Silvia\", \"horarioDeAtendimento\":\"12:00\", \"periodo\":\"integral\", \"sala\":\"1\" }");

        String resultado = horarioAtendimento.processarHorario();
        assertTrue(resultado.contains("Prédio: 1"));
    }
}
