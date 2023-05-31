package Business;

import Model.FeedBack;

import java.util.List;
//STRATEGY PATTERN
//Context
public class SortedFeedbackList {

    private List<FeedBack> feedBackList;
    private IFeedbackSortStrategy sortStrategy;

    public SortedFeedbackList(List<FeedBack> feedBackList) {
        this.feedBackList = feedBackList;
    }

    public void setSortStrategy(IFeedbackSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort() {
        sortStrategy.sort(feedBackList);
    }
}
