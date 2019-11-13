package br.uefs.ecomp.sensores.termometro;

public class TermometroController {
    private String ip;
    private String porta;
    private String topico;

    void setConfiguracoes(String ip, String porta, String topico) {
        this.ip = ip;
        this.porta = porta;
        this.topico = topico;
    }
}
