package Business;
import Model.FeedBack;

import java.util.Comparator;
import java.util.List;
//STRATEGY PATTERN
//Concrete Strategy
public class UrgentFirstFeedbackSort implements IFeedbackSortStrategy{

    @Override
    public void sort(List<FeedBack> feedBacksList) {

        feedBacksList.sort(Comparator.comparingInt(FeedBack::getPunteggio));

    }
}
