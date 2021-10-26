package org.perfumepedia.DataBase.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HtmlTagGenerator {

    @Value("${vole-stars-full}")
    private String voleStarsFull;
    @Value("${vote-stars-half}")
    private String voteStarsHalf;
    @Value("${vote-stars-empty}")
    private String voteStarsEmpty;


    public String getScoreStar(Double score) {
        String star = "";
        if (score == 0.0) {
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }

        if (score >= 0.0 && score < 0.5) {
            star += voteStarsHalf + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 0.5 && score < 1) {
            star += voleStarsFull + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 1.0 && score < 1.5) {
            star += voleStarsFull + " ";
            star += voteStarsHalf + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 1.5 && score < 2.0) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 2.0 && score < 2.5) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voteStarsHalf + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 2.5 && score < 3.0) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voteStarsEmpty + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 3.0 && score < 3.5) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voteStarsHalf + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 3.5 && score < 4.0) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voteStarsEmpty + " ";
            return star;
        }
        else if (score >= 4.0 && score < 4.5) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voteStarsHalf + " ";
            return star;
        }
        else if (score >= 4.5 && score == 5.0) {
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            star += voleStarsFull + " ";
            return star;
        }
        return star;
    }
}
