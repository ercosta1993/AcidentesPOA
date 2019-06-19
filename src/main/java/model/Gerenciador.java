package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jxmapviewer.viewer.GeoPosition;

public class Gerenciador {

    List<Acidente> lista;
    List<List<String>> csvData = new ArrayList<>();
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
        try {
            Path path1 = Paths.get(nomeArq);

            BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"));

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] dados = line.split(";");

                List<String> dataLine = new ArrayList<>(dados.length);

                int id = Integer.parseInt(dados[0]);
                int fx_hora = Integer.parseInt(dados[31]);
                double lat = Double.parseDouble(dados[35].replaceAll(",", "."));
                double lon = Double.parseDouble(dados[36].replaceAll(",", "."));
                Dia_semana dia = Enum.valueOf(Dia_semana.class, dados[8]);
                Acidente acidente = new Acidente(id, dados[1], dados[2], fx_hora, new GeoPosition(lat, lon), dia);
                lista.add(acidente);
                dataLine.addAll(Arrays.asList(dados));
                csvData.add(dataLine);
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    // Questao 01 - a
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

    // Questao 01 - b
    public Map<Integer, Integer> ocorrenciasPorHora() {
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

    // Questao 02 - Falta filtra por dia_sem (enum)
    public ArrayList<Acidente> acidentesPorLogeDiaSem(String log, String dia_sem) {
        int cont = 0;
        ArrayList<Acidente> acidentes = new ArrayList<>();
        String diaConvertido = dia_sem.replaceAll("-FEIRA", "");
        Dia_semana dia = Enum.valueOf(Dia_semana.class, diaConvertido);

        System.out.println("dia: " + dia);

        for (Acidente a : lista) {
            System.out.println("aqui: " + a.getDia_sem());
            if ((a.getLog1().contains(log)) && (a.getDia_sem() == dia)) {

                acidentes.add(a);
            }
        }
        return acidentes;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
