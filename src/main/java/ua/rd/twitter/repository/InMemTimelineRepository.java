package ua.rd.twitter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
@Repository("timelineRepository")
public class InMemTimelineRepository implements TimelineRepository {
    private List<Timeline> timelines = new ArrayList<>();

    @Autowired
    private ArrayList<Timeline> testTimelines;

    @PostConstruct
    public void init(){
        timelines.addAll(testTimelines);
    }

    @Override
    public Timeline find(User user) {
        return timelines.stream()
                .filter(t -> t.getUser().equals(user))
                .findFirst()
                .get();
    }

    @Override
    public void save(Timeline timeline) {
        timelines.add(timeline);
    }

    @Override
    public Iterable<Timeline> findAll() {
        return new ArrayList<>(timelines);
    }
}
