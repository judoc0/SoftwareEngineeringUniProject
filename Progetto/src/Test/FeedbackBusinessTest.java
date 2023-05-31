package Test;

import Business.*;
import Model.FeedBack;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackBusinessTest { //Feedback nel DAO te lo fai da solo :(

    List<FeedBack> feedBackList;

    @Before
    public void setUp() throws Exception {

        feedBackList = new ArrayList<FeedBack>();

        FeedBack f1 = new FeedBack();
        f1.setId(1);
        f1.setPunteggio(5);
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-20");
        f1.setDate(date1);

        FeedBack f2 = new FeedBack();
        f2.setId(2);
        f2.setPunteggio(3);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-27");
        f2.setDate(date2);

        FeedBack f3 = new FeedBack();
        f3.setId(3);
        f3.setPunteggio(1);
        Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-22"); //Libreria mock Mockito
        f3.setDate(date3);

        feedBackList.add(f1);
        feedBackList.add(f2);
        feedBackList.add(f3);
    }

    @After
    public void tearDown() throws Exception {
        feedBackList = null;
    }

    @Test
    public void bestFirstSortTest() {

        SortedFeedbackList sortedFeedbackList = new SortedFeedbackList(feedBackList);

        IFeedbackSortStrategy strategy = new BestFirstFeedbackSort();
        sortedFeedbackList.setSortStrategy(strategy);

        sortedFeedbackList.sort();

        int[] expected = new int[] {1,2,3};

        int[] actual = new int[3];

        for (int i = 0; i < feedBackList.size(); actual[i] = feedBackList.get(i++).getId());

        Assert.assertArrayEquals(expected, actual);

    }

    @Test
    public void urgentFirstSortTest() {

        SortedFeedbackList sortedFeedbackList = new SortedFeedbackList(feedBackList);

        IFeedbackSortStrategy strategy = new UrgentFirstFeedbackSort();
        sortedFeedbackList.setSortStrategy(strategy);

        sortedFeedbackList.sort();

        int[] expected = new int[] {3,2,1};

        int[] actual = new int[3];

        for (int i = 0; i < feedBackList.size(); actual[i] = feedBackList.get(i++).getId());

        Assert.assertArrayEquals(expected, actual);

    }

    @Test
    public void recentFirstSortTest() {

        SortedFeedbackList sortedFeedbackList = new SortedFeedbackList(feedBackList);

        IFeedbackSortStrategy strategy = new RecentFirstFeedbackSort();
        sortedFeedbackList.setSortStrategy(strategy);

        sortedFeedbackList.sort();

        int[] expected = new int[] {1,3,2};

        int[] actual = new int[3];

        for (int i = 0; i < feedBackList.size(); actual[i] = feedBackList.get(i++).getId());

        Assert.assertArrayEquals(expected, actual);

    }

}
