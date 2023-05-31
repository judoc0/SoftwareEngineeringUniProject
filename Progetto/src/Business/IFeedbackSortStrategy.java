package Business;

import Model.FeedBack;

import java.util.List;
//STRATEGY PATTERN
//Strategy
public interface IFeedbackSortStrategy {

    void sort(List<FeedBack> feedBacksList);
}
