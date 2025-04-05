package br.pratico;

import org.json.JSONObject;

public class Horario {

    private final ServicoRemoto servico;

    public Horario(ServicoRemoto servico) {
        this.servico = servico;
    }

    public String processarHorario() {
        String json = servico.obterHorarioJSON();

        JSONObject obj = new JSONObject(json);
        String nome = obj.getString("nomeDoProfessor");
        String horario = obj.getString("horarioDeAtendimento");
        String periodo = obj.getString("periodo");
        int sala = Integer.parseInt(obj.getString("sala"));

        int predio = (sala - 1) / 5 + 1;

        return String.format("Prof: %s - Horário: %s - Período: %s - Sala: %d - Prédio: %d",
                nome, horario, periodo, sala, predio);
    }


}
