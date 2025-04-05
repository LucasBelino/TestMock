package br.horario;

import br.pratico.Horario;
import br.pratico.ServicoRemoto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
