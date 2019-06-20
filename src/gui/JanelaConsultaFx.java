package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Dia_semana;

import modelo.Gerenciador;
import modelo.Tipo_acidente;

public class JanelaConsultaFx extends Application {

    Gerenciador ger = Gerenciador.getInstance();

    final SwingNode mapkit = new SwingNode();

    private GerenciadorMapa gerenciador;

    private EventosMouse mouse;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ger.readFile("acidentes_trab.csv");

        setup();

        GeoPosition poa = new GeoPosition(-30.025, -51.18);

        // Cria um mapa com inicio voltado para o centro geo de POA
        gerenciador = new GerenciadorMapa(poa, GerenciadorMapa.FonteImagens.VirtualEarth);

        mouse = new EventosMouse();
        gerenciador.getMapKit().getMainMap().addMouseListener(mouse);
        gerenciador.getMapKit().getMainMap().addMouseMotionListener(mouse);
        gerenciador.getMapKit().setZoom(6);

        createSwingContent(mapkit);

        BorderPane pane = new BorderPane();
        GridPane leftPane = new GridPane();

        leftPane.setAlignment(Pos.CENTER);
        leftPane.setHgap(10);
        leftPane.setVgap(10);
        leftPane.setPadding(new Insets(10, 10, 10, 10));

        Button btnConsulta1 = new Button("Consulta 1");

        Button btnConsulta2 = new Button("Consulta 2");
        Button btnConsulta3 = new Button("Consulta 3");
        Button btnConsulta4 = new Button("Consulta 4");
        Button btnConsultasRestantes = new Button("Consultas Restantantes");

        leftPane.add(btnConsulta1, 0, 0);
        leftPane.add(btnConsulta2, 1, 0);
        leftPane.add(btnConsulta3, 2, 0);
        leftPane.add(btnConsulta4, 3, 0);
        leftPane.add(btnConsultasRestantes, 4, 0);

        btnConsulta1.setOnAction(e -> {
            consulta1();
        });

        btnConsulta2.setOnAction(e -> {
            consulta2();
        });

        btnConsulta3.setOnAction(e -> {
            consulta3();
        });

        btnConsulta4.setOnAction(e -> {
            consulta4();
        });

        btnConsultasRestantes.setOnAction(e -> {
            consultasRestantes();
        });

        pane.setCenter(mapkit);
        pane.setTop(leftPane);

        Scene scene = new Scene(pane, 1700, 820);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mapas com JavaFX");
        primaryStage.show();

    }

    // Inicializando os dados aqui...
    private void setup() {
        //gerenciador = Gerenciador.getInstance();

    }

    private void consulta1() {
        List<GeoPosition> geos = ger.colecaoAcidentesPorHora(ger.horaDoDiaComMaisAcidentes());
        List<MyWaypoint> lstPoints = new ArrayList<>();

        System.out.println("Consulta 1 - Acidentes encontrados: " + geos.size());

        gerenciador.clear();
        geos.forEach((geo) -> {
            lstPoints.add(new MyWaypoint(Color.RED, "", geo, 5));
        });
        gerenciador.setPontos(lstPoints);
        gerenciador.getMapKit().repaint();
    }

    private void consulta2() {
        List<GeoPosition> geos = ger.colecaoAcidentesPorLogDiaSem("SERTORIO", Dia_semana.SEXTA);
        List<MyWaypoint> lstPoints = new ArrayList<>();

        System.out.println("Consulta 2 - Acidentes encontrados: " + geos.size());

        gerenciador.clear();
        geos.forEach((geo) -> {
            lstPoints.add(new MyWaypoint(Color.BLACK, "", geo, 5));
        });
        gerenciador.setPontos(lstPoints);
        gerenciador.getMapKit().repaint();
    }

    private void consulta3() {
        LocalDateTime dataInicial = LocalDateTime.of(2010, Month.MARCH, 5, 0, 0);
        LocalDateTime dataFinal = LocalDateTime.of(2010, Month.MARCH, 20, 0, 0);
        List<Tipo_acidente> tipos = Arrays.asList(Tipo_acidente.ABALROAMENTO, Tipo_acidente.CAPOTAGEM, Tipo_acidente.COLISAO);
        List<GeoPosition> geos = ger.colecaoAcidentesPorDataTipoAcidente(dataInicial, dataFinal, tipos);
        List<MyWaypoint> lstPoints = new ArrayList<>();

        System.out.println("Consulta 3 - Acidentes encontrados: " + geos.size());

        gerenciador.clear();
        geos.forEach((geo) -> {
            lstPoints.add(new MyWaypoint(Color.GREEN, "", geo, 5));
        });
        gerenciador.setPontos(lstPoints);
        gerenciador.getMapKit().repaint();
    }

    private void consulta4() {
    }

    public void consultasRestantes() {
        
    }

    private class EventosMouse extends MouseAdapter {

        private int lastButton = -1;

        @Override
        public void mousePressed(MouseEvent e) {
            JXMapViewer mapa = gerenciador.getMapKit().getMainMap();
            GeoPosition loc = mapa.convertPointToGeoPosition(e.getPoint());
            System.out.println(loc.getLatitude() + ", " + loc.getLongitude());
            lastButton = e.getButton();
            // Botão 3: seleciona localização
            if (lastButton == MouseEvent.BUTTON3) {
                gerenciador.setPosicao(loc);
                gerenciador.getMapKit().repaint();
            }
        }
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            swingNode.setContent(gerenciador.getMapKit());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
