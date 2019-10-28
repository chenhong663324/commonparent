import edu.hstc.roast.module.Posting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyPageService {
    public List<Posting> queryNextPagePostings(){
        List<Posting> postings=new ArrayList<Posting>();
        Posting posting=new Posting();
        posting.setName("You Have Only One Life");
        posting.setIntroduce("May you have enough happiness to make you sweet,enough trials to make you strong,enough sorrow to keep you human,enough hope to make you happy? Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too.");
        postings.add(posting);
        return postings;
    }

}
