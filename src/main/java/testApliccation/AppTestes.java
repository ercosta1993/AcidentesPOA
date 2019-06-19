package testApliccation;

import model.Gerenciador;

public class AppTestes {

    public static void main(String[] args) {
        Gerenciador ger = Gerenciador.getInstance();
        
        // Leitura do arquivo csv
        ger.readFile("acidentes_trab.csv");
        
        //Numero de linhas cadastradas: 69522
        //System.out.println("Numero de linhas cadastradas: " + ger.size());
        
        // Questao 01
        System.out.println("O horario do dia em que ocorrem mais acidentes é " + ger.horaDoDiaComMaisAcidentes() + "hs");
        System.out.println(ger.ocorrenciasPorHora());
        
        // Questao 02
        System.out.println("\nLog: SERTORIO\nDia semana: SABADO\n" + ger.acidentesPorLogeDiaSem("SERTORIO", "SABADO"));
        
        // Questao 03
        
        
        // Questao 04
        
        
        // Questao 05
        
        
        // Questao 06
        
        
        // Questao 07
        
        
        // Questao 08
        
    }

}
