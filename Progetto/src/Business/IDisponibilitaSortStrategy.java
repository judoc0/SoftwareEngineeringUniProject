package Business;

import Model.Disponibilita;

import java.util.List;
//STRATEGY PATTERN
//Strategy
public interface IDisponibilitaSortStrategy {

    void sort(List<Disponibilita> Disponibilitalist);
}
