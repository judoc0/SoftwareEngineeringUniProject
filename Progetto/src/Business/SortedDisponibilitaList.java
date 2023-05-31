package Business;

import Model.Disponibilita;
import Model.FeedBack;

import java.util.List;
//STRATEGY PATTERN
//Context
public class SortedDisponibilitaList {

    private List<Disponibilita> disponibilitaList;
    private IDisponibilitaSortStrategy sortStrategy;

    public SortedDisponibilitaList(List<Disponibilita> disponibilitaList) {
        this.disponibilitaList = disponibilitaList;
    }

    public void setSortStrategy(IDisponibilitaSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort() {
        sortStrategy.sort(disponibilitaList);
    }
}
