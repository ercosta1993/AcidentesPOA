package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Consulta2 extends Application implements EventHandler<ActionEvent> {

    private TextField tfName;
    private ComboBox cbTipo_acidente;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Define o grid basico
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Define o título do form
        Text tfTitulo = new Text("Selecione sua busca");
        tfTitulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(tfTitulo, 0, 0, 2, 1);

        // campos
        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.BASELINE_LEFT);
        mainGrid.setHgap(4);
        mainGrid.setVgap(6);
        Label log = new Label("logradouro");
        mainGrid.add(new Label("Logradouro:"), 0, 1);
        tfName = new TextField();
        tfName.setPrefWidth(210);
        mainGrid.add(tfName, 1, 1);

        // comboBox
        mainGrid.add(new Label("Dia da Semana:"), 0, 2);
        cbTipo_acidente = new ComboBox(FlightData.getInstance().getCidadesAtendidas());
        mainGrid.add(cbTipo_acidente, 1, 2);
        
        // botoes
        Button btSearch = new Button();
        btSearch.setText("Pesquisar");
        btSearch.setOnAction(this);
        Button btCancelar = new Button();
        btCancelar.setText("Cancelar");
        btCancelar.setOnAction(this);
        
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BASELINE_CENTER);
        hbBtn.getChildren().add(btSearch);
        hbBtn.getChildren().add(btCancelar);
        grid.add(hbBtn, 1, 4);
        
        grid.add(mainGrid, 0, 2);

        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 530, 200);
        primaryStage.setTitle("Consulta2");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Evento disparado !!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
