package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.Dia_semana;

import modelo.Gerenciador;

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

        Scene scene = new Scene(pane, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mapas com JavaFX");
        primaryStage.show();

    }

    // Inicializando os dados aqui...
    private void setup() {
        //gerenciador = Gerenciador.getInstance();

    }

    private void consulta1() {

        // Lista para armazenar o resultado da consulta
        /*
        List<MyWaypoint> lstPoints = new ArrayList<>();

        GeoPosition pucrs = new GeoPosition(-30.058642, -51.182809);
        GeoPosition brio = new GeoPosition(-30.065616, -51.233587);
        GeoPosition mquin = new GeoPosition(-30.031408, -51.234098);
        GeoPosition arena = new GeoPosition(-29.975119, -51.195651);

        GeoPosition p1630 = new GeoPosition(-30.0375, -51.2192);

        gerenciador.clear();
        Tracado tr = new Tracado();
        tr.setLabel("PUC-BRIO");
        tr.setWidth(7);
        tr.setCor(Color.RED);
        tr.addPonto(pucrs);
        tr.addPonto(brio);

        gerenciador.addTracado(tr);

        Tracado tr2 = new Tracado();
        tr2.setWidth(3);
        tr2.setCor(Color.BLUE);
        tr2.addPonto(mquin);
        tr2.addPonto(arena);
        gerenciador.addTracado(tr2);

        // Adiciona os locais (sem repetir) na lista de waypoints
        lstPoints.add(new MyWaypoint(Color.RED, "PUCRS", pucrs, 10));
        lstPoints.add(new MyWaypoint(Color.RED, "Beira-Rio", brio, 10));
        lstPoints.add(new MyWaypoint(Color.BLUE, "CC MQuintana", mquin, 5));
        lstPoints.add(new MyWaypoint(Color.BLUE, "Arena", arena, 5));
        lstPoints.add(new MyWaypoint(Color.BLACK, "P1630", p1630, 25));

        // Para obter um ponto clicado no mapa, usar como segue:
        // GeoPosition pos = gerenciador.getPosicao();
        // Informa o resultado para o gerenciador
        gerenciador.setPontos(lstPoints);

        // Quando for o caso de limpar os traçados...
        // gerenciador.clear();
        gerenciador.getMapKit().repaint();
         */
        List<GeoPosition> geos = ger.colecaoAcidentesPorHora(ger.horaDoDiaComMaisAcidentes());
        List<MyWaypoint> lstPoints = new ArrayList<>();

        System.out.println("Consulta 1 - Acidentes encontrados: " + geos.size());

        gerenciador.clear();
        for (GeoPosition geo : geos) {
            lstPoints.add(new MyWaypoint(Color.RED, "", geo, 5));
        }
        gerenciador.setPontos(lstPoints);
        gerenciador.getMapKit().repaint();
    }

    private void consulta2() {
        List<GeoPosition> geos = ger.colacaoAcidentesPorLogDiaSem("SERTORIO", Dia_semana.SEXTA);
        List<MyWaypoint> lstPoints = new ArrayList<>();

        System.out.println("Consulta 2 - Acidentes encontrados: " + geos.size());

        gerenciador.clear();
        for (GeoPosition geo : geos) {
            lstPoints.add(new MyWaypoint(Color.BLACK, "", geo, 5));
        }
        gerenciador.setPontos(lstPoints);
        gerenciador.getMapKit().repaint();
    }

    private void consulta3() {
    }

    private void consulta4() {
    }

    public void consultasRestantes() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CONSULTAS RESTANTES");
        //alert.setHeaderText("CONSULTAS RESTANTES");
        alert.setContentText("Dia da semana com mais acidentes: " + ger.diaSemanaComMaisAcidentes() + "\n\n" + "Momento do dia que houveram mais acidentes: " + ger.maiorOcorrenciadiaNoite() + "\n\n" + "Tempo que apresenteu mais acidentes: " + ger.maiorOcorrenciaTempo());

        alert.showAndWait();
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
