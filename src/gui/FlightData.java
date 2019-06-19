package gui;
import java.util.List;
import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class FlightData{
    private static final FlightData fd = new FlightData();
    
    private final List<String> cidadesAtendidas;
    private final List<Integer> qtdadeAssentos;
    
    private List<String> loadCidadesAtendidas(){
        List<String> lst = new LinkedList<>();
        lst.add("SEGUNDA-FEIRA");
        lst.add("TERCA-FEIRA");
        lst.add("QUARTA-FEIRA");
        lst.add("QUINTA-FEIRA");
        lst.add("SABADO");
        lst.add("DOMINGO");
        return lst;
    }
    
    private List<Integer> loadQtdadeAssentos(){
        List<Integer> lst = new LinkedList<>();
        for(int i=1;i<10;i++){
            lst.add(i);
        }
        return lst;
    }
    
    private FlightData(){
        cidadesAtendidas = loadCidadesAtendidas();
        qtdadeAssentos = loadQtdadeAssentos();
    }
    
    public static FlightData getInstance(){
        return(fd);
    }
    
    public ObservableList getCidadesAtendidas(){
        return FXCollections.observableList(cidadesAtendidas);
    }

    public ObservableList getQtdadeAssentos(){
        return FXCollections.observableList(qtdadeAssentos);
    }
    
}
