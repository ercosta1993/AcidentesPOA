package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;
import org.jxmapviewer.viewer.GeoPosition;

public class Gerenciador {

    ArrayList<Acidente> lista;
    ArrayList<List<String>> csvData = new ArrayList<>();

    private static Gerenciador cad = null;

    public static Gerenciador getInstance() {
        if (cad == null) {
            cad = new Gerenciador();
        }
        return cad;
    }

    private Gerenciador() {
        lista = new ArrayList<>();
    }

    public int size() {
        return lista.size();
    }

    public void readFile(String nomeArq) {
        LocalDateTime data;
        try {
            Path path1 = Paths.get(nomeArq);

            BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"));

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");

                List<String> dataLine = new ArrayList<>(dados.length);

                int id = Integer.parseInt(dados[0]);

                int fx_hora = Integer.parseInt(dados[31]);

                String tipoConvertido = dados[5].replaceAll("NAO CADASTRADO", "CHOQUE");
                Tipo_acidente tipo = Enum.valueOf(Tipo_acidente.class, tipoConvertido);

                String diaConvertido = dados[8].replaceAll("-FEIRA", "");
                Dia_semana dia_sem = Enum.valueOf(Dia_semana.class, diaConvertido);

                try { // gera exceção se o formato da data estiver invalido
                    data = LocalDateTime.parse(dados[7], DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"));
                } catch (Exception e) {
                    continue;
                }

                Dia_noite dia_noite = Enum.valueOf(Dia_noite.class, dados[24]);

                // tratamento da linha do .csv com problema
                String tempoConvertido = dados[23].replaceAll("NAO CADAST", "BOM");
                Tempo tempo = Enum.valueOf(Tempo.class, tempoConvertido);

                double lat = Double.parseDouble(dados[35].replaceAll(",", "."));
                double lon = Double.parseDouble(dados[36].replaceAll(",", "."));

                Acidente acidente = new Acidente(id, data, dados[1], dados[2], tipo, dia_sem, dia_noite, tempo, fx_hora, new GeoPosition(lat, lon));

                //System.out.println(acidente);
                lista.add(acidente);
                dataLine.addAll(Arrays.asList(dados));
                csvData.add(dataLine);
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    // Questao 01a ok
    public int horaDoDiaComMaisAcidentes() {
        int maior = 0;
        int hora = 0;
        int horaMaior = -1;

        while (hora < 24) {
            int cont = 0;
            for (Acidente a : lista) {
                if (a.getFx_hora() == hora) {
                    cont++;
                }
            }
            if (cont > maior) {
                maior = cont;
                horaMaior = hora;
            }
            hora++;
        }
        return horaMaior;
    }

    // Questao 01b ok
    public ArrayList<GeoPosition> colecaoAcidentesPorHora(int hora) {
        ArrayList<GeoPosition> acidentes = new ArrayList<>();
        lista.stream().filter((acidente) -> (acidente.getFx_hora() == hora)).forEachOrdered((acidente) -> {
            acidentes.add(acidente.getGeo());
        });
        return acidentes;
    }

    // Questao 01 - Complemento ok
    public Map<Integer, Integer> mapaOcorrenciasPorHora() {
        Map<Integer, Integer> mapa = new HashMap<>();
        int hora = 0;

        while (hora < 24) {
            int cont = 0;
            for (Acidente a : lista) {
                if (a.getFx_hora() == hora) {
                    cont++;
                }
            }
            mapa.put(hora, cont);
            hora++;
        }
        return mapa;
    }

    // Questao 02 ok
    public ArrayList<GeoPosition> colecaoAcidentesPorLogDiaSem(String log, Dia_semana dia) {
        ArrayList<GeoPosition> acidentes = new ArrayList<>();
        lista.stream().filter((acidente) -> ((acidente.getLog1().contains(log)) && (acidente.getDia_sem() == dia))).forEachOrdered((acidente) -> {
            acidentes.add(acidente.getGeo());
        });
        return acidentes;
    }

    // Questao 03 ok
    public ArrayList<GeoPosition> colecaoAcidentesPorDataTipoAcidente(LocalDateTime dataInicial, LocalDateTime dataFinal, List<Tipo_acidente> tipos) {
        ArrayList<GeoPosition> acidentes = new ArrayList<>();
        LocalDate inicio = dataInicial.toLocalDate();
        LocalDate fim = dataFinal.toLocalDate();
        for (LocalDate id = inicio; !id.isAfter(fim); id = id.plusDays(1)) {
            for (Acidente acidente : lista) {
                LocalDate data = acidente.getData().toLocalDate();
                if ((id.equals(data)) && tipos.contains(acidente.getTipo())) {
                    acidentes.add(acidente.getGeo());
                }
            }
        }
        return acidentes;
    }

    // Questao 04
    public ArrayList<Acidente> acidentesPorLocalizacao(GeoPosition geo, long distancia) {
        ArrayList<Acidente> acidentes = new ArrayList<>();

        return acidentes;
    }

    // Questao 05
    public LocalDateTime dataComMaisAcidentes() {
        LocalDateTime dataMaior = null;

        return dataMaior;
    }

    // Questao 06 ok
    public Enum diaSemanaComMaisAcidentes() {
        Map<Enum, Long> map = new TreeMap<>();
        long maior = Long.MIN_VALUE;
        Enum maiorEnum = null;

        // contagem de cada dia da semana utilizando stream
        long contSegunda = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.SEGUNDA).count();
        long contTerca = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.TERCA).count();
        long contQuarta = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.QUARTA).count();
        long contQuinta = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.QUINTA).count();
        long contSexta = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.SEXTA).count();
        long contSabado = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.SABADO).count();
        long contDomingo = lista.stream().filter(a -> a.getDia_sem() == Dia_semana.DOMINGO).count();

        List<Long> contadores = Arrays.asList(contSegunda, contTerca, contQuarta, contQuinta, contSexta, contSabado, contDomingo);
        List<Enum> dias = Arrays.asList(Dia_semana.values());

        for (int i = 0; i < Dia_semana.values().length; i++) {
            map.put(dias.get(i), contadores.get(i));
        }

        for (Enum e : map.keySet()) {
            if (map.get(e) > maior) {
                maior = map.get(e);
                maiorEnum = e;
            }
        }
        return maiorEnum;
    }

    // Questao 07 ok
    public Enum maiorOcorrenciadiaNoite() {
        Map<Enum, Long> map = new TreeMap<>();
        long maior = Long.MIN_VALUE;
        Enum maiorEnum = null;

        // contagem de cada dia ou noite utilizando stream
        long contDia = lista.stream().filter(a -> a.getDia_noite() == Dia_noite.DIA).count();
        long contNoite = lista.stream().filter(a -> a.getDia_noite() == Dia_noite.NOITE).count();

        List<Long> contadores = Arrays.asList(contDia, contNoite);
        List<Enum> diaNoite = Arrays.asList(Dia_noite.values());

        for (int i = 0; i < Dia_noite.values().length; i++) {
            map.put(diaNoite.get(i), contadores.get(i));
        }

        for (Enum e : map.keySet()) {
            if (map.get(e) > maior) {
                maior = map.get(e);
                maiorEnum = e;
            }
        }
        return maiorEnum;
    }

    // Questao 08 ok
    public Enum maiorOcorrenciaTempo() {
        Map<Enum, Long> map = new TreeMap<>();
        long maior = Long.MIN_VALUE;
        Enum maiorEnum = null;

        // contagem de cada tempo utilizando stream
        long contBom = lista.stream().filter(a -> a.getTempo() == Tempo.BOM).count();
        long contNublado = lista.stream().filter(a -> a.getTempo() == Tempo.NUBLADO).count();
        long contChuvoso = lista.stream().filter(a -> a.getTempo() == Tempo.CHUVOSO).count();

        List<Long> contadores = Arrays.asList(contBom, contNublado, contChuvoso);
        List<Enum> tempo = Arrays.asList(Tempo.values());

        for (int i = 0; i < Tempo.values().length; i++) {
            map.put(tempo.get(i), contadores.get(i));
        }

        for (Enum e : map.keySet()) {
            if (map.get(e) > maior) {
                maior = map.get(e);
                maiorEnum = e;
            }
        }
        return maiorEnum;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
