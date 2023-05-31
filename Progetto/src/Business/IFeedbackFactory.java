package Business;

import Model.FeedBack;
import Model.IFeedback;
//FACTHORY METHOD PATTERN
//Concrete Creator
public class IFeedbackFactory {

    public IFeedback getIFeedback(String Type){

        if (Type.equalsIgnoreCase("FEEDBACK"))
            return new FeedBack();

        return null;
    }
}
