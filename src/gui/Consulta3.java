package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Tipo_acidente;

public class Consulta3 extends Application implements EventHandler<ActionEvent> {

    private DatePicker dpIda, dpVolta;

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

        grid.add(new Label("Data inicial:"), 0, 2);
        dpIda = new DatePicker();
        grid.add(dpIda, 1, 2);
        grid.add(new Label("Data final:"), 0, 3);
        dpVolta = new DatePicker();
        grid.add(dpVolta, 1, 3);
        
        // botoes
        Button btSearch = new Button();
        btSearch.setText("Pesquisar");
        btSearch.setOnAction(this);
        Button btCancelar = new Button();
        btCancelar.setText("Cancelar");
        btCancelar.setOnAction(this);

        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BASELINE_RIGHT);
        hbBtn.getChildren().add(btSearch);
        hbBtn.getChildren().add(btCancelar);
        grid.add(hbBtn, 0, 5);

        grid.add(mainGrid, 0, 2);

        
        CheckBox cBoxAbalroamento = new CheckBox();
        cBoxAbalroamento.setText("Abalroamento");
        cBoxAbalroamento.setOnAction((event) -> {
            boolean selected = cBoxAbalroamento.isSelected();
        });
        grid.add(cBoxAbalroamento, 2, 2);
        
        CheckBox cBoxAtropelamento = new CheckBox();
        cBoxAtropelamento.setText("Atropelamento");
        cBoxAtropelamento.setOnAction((event) -> {
            boolean selected = cBoxAtropelamento.isSelected();
        });
        grid.add(cBoxAtropelamento, 2, 3);
        
        CheckBox cBoxCapotagem = new CheckBox();
        cBoxCapotagem.setText("Capotagem");
        cBoxCapotagem.setOnAction((event) -> {
            boolean selected = cBoxCapotagem.isSelected();
        });
        grid.add(cBoxCapotagem, 2, 4);
        
        
        CheckBox cBoxChoque = new CheckBox();
        cBoxChoque.setText("Choque");
        cBoxChoque.setOnAction((event) -> {
            boolean selected = cBoxChoque.isSelected();
        });
        grid.add(cBoxChoque, 2, 5);
        
        CheckBox cBoxColisao = new CheckBox();
        cBoxColisao.setText("Colisão");
        cBoxColisao.setOnAction((event) -> {
            boolean selected = cBoxColisao.isSelected();
        });
        grid.add(cBoxColisao, 2, 6);
        
        CheckBox cBoxEventual = new CheckBox();
        cBoxEventual.setText("Eventual");
        cBoxEventual.setOnAction((event) -> {
            boolean selected = cBoxEventual.isSelected();
        });
        grid.add(cBoxEventual, 3, 2);
        
        CheckBox cBoxIncendio = new CheckBox();
        cBoxIncendio.setText("Incendio");
        cBoxIncendio.setOnAction((event) -> {
            boolean selected = cBoxIncendio.isSelected();
        });
        grid.add(cBoxIncendio, 3, 3);
        
        CheckBox cBoxNaoCadastrado = new CheckBox();
        cBoxNaoCadastrado.setText("Não Cadastrado");
        cBoxNaoCadastrado.setOnAction((event) -> {
            boolean selected = cBoxNaoCadastrado.isSelected();
        });
        grid.add(cBoxNaoCadastrado, 3, 4);
        
        CheckBox cBoxQueda = new CheckBox();
        cBoxQueda.setText("Queda");
        cBoxQueda.setOnAction((event) -> {
            boolean selected = cBoxQueda.isSelected();
        });
        grid.add(cBoxQueda, 3, 5);
        
        CheckBox cBoxTombamento = new CheckBox();
        cBoxTombamento.setText("Tombamento");
        cBoxTombamento.setOnAction((event) -> {
            boolean selected = cBoxTombamento.isSelected();
        });
        grid.add(cBoxTombamento, 3, 6);
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 650, 300);
        primaryStage.setTitle("Consulta3");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.BASELINE_LEFT);
        grid.setHgap(4);
        grid.setVgap(6);

        grid.add(new Label("Data inicial:"), 0, 2);
        dpIda = new DatePicker();
        grid.add(dpIda, 1, 2);
        grid.add(new Label("Data final:"), 0, 3);
        dpVolta = new DatePicker();
        grid.add(dpVolta, 1, 3);

        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.BASELINE_LEFT);
        mainGrid.setHgap(4);
        mainGrid.setVgap(6);
        
        grid.add(mainGrid, 0, 2);
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 530, 200);
        primaryStage.setTitle("Consulta2");
        primaryStage.setScene(scene);
        primaryStage.show();
         */
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Evento disparado !!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
