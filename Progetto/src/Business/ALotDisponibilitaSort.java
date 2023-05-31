package Business;

import Model.Disponibilita;

import java.util.Comparator;
import java.util.List;
//STRATEGY PATTERN
//Concrete Strategy
public class ALotDisponibilitaSort implements IDisponibilitaSortStrategy{

    @Override
    public void sort(List<Disponibilita> disponibilitaList) {

        disponibilitaList.sort(Comparator.comparing(Disponibilita::getQuantita).reversed());

    }

}
