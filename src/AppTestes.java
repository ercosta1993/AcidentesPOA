import java.time.LocalDateTime;
import java.time.Month;
import modelo.Dia_semana;
import modelo.Gerenciador;
import modelo.Tipo_acidente;

public class AppTestes {

    public static void main(String[] args){
        Gerenciador ger = Gerenciador.getInstance();
        
        ger.readFile("acidentes_trab.csv");

        //System.out.println("Numero de linhas cadastradas: " + ger.size());
        
        /*
         * 1. Verificar a hora do dia quando ocorrem mais acidentes (dados
         * intervalos de horas cheias: 13:00 até 13:59, 14:00h até 14:59h, etc).
         * Mostre no mapa os locais dos acidentes que ocorrem neste horário;
         */
        //System.out.println("1. Ocorrencias de acidentes por hora:\n" + ger.mapaOcorrenciasPorHora());
        //System.out.println("Hora do dia com mais acidentes: " + ger.horaDoDiaComMaisAcidentes() + "hs");
        //System.out.println("Acidentes:\n" + ger.colecaoAcidentesPorHora(ger.horaDoDiaComMaisAcidentes()));
        
        /*
         * 2. Informar um nome de rua/avenida e um dia da semana e visualizar
         * todos os acidentes que ocorreram na rua e no dia indicados;
         */
        //System.out.println("2.");
        //String log = "ICARAI";
        //Dia_semana dia = Dia_semana.SABADO;
        //System.out.println("Teste:\nLog: " + log + "\nDia da Semana: " + dia + "\n" + ger.colacaoAcidentesPorLogDiaSem(log, dia));
        
        /*
         * 3. Identificar para um determinado período (dia inicial e dia final,
         * informado/selecionado pelo usuário), quais avenidas ou ruas que
         * exibiram acidentes de um ou mais tipos, escolhidos pelo usuário (ex:
         * colisão, atropelamento, queda...). Mostrar no mapa os locais;
         */
        System.out.println(ger.listaAcidentesPorRuaTipo(LocalDateTime.of(2010, Month.MARCH, 5, 0, 0), LocalDateTime.of(2010, Month.MARCH, 9, 0, 0), Tipo_acidente.COLISAO));

        /*
         * 4. A partir de uma determinada localização mostrar todos os acidentes
         * que ocorreram a até uma determinada distância deste local (valor
         * informado pelo usuário);
         */
        //System.out.println();

        /*
         * 5. Identificar a data específica em que houve mais acidentes;
         */
        //System.out.println();
        
        /*
         * 6. Verificar o dia da semana em que houve mais acidentes;
         */
        //System.out.println("6. Dia da semana com mais acidentes: " + ger.diaSemanaComMaisAcidentes());
        
        /*
         * 7. Verificar e informar se ocorrem mais acidentes de dia ou à noite;
         */
        //System.out.println("7. Maior ocorrencia entre Dia ou Noite: " + ger.maiorOcorrenciadiaNoite());
        
        /*
         * 8. Verificar se ocorrem mais acidentes com tempo chuvoso, nublado ou bom. 
         */
        //System.out.println("8. Maior ocorrencia entre tempo BOM, NUBLADO ou CHUVOSO: " + ger.maiorOcorrenciaTempo());
    }

}
