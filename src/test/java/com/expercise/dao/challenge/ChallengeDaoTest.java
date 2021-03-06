package com.expercise.dao.challenge;

import com.expercise.AbstractDaoTest;
import com.expercise.domain.challenge.Challenge;
import com.expercise.domain.level.Level;
import com.expercise.domain.user.User;
import com.expercise.testutils.builder.ChallengeBuilder;
import com.expercise.testutils.builder.LevelBuilder;
import com.expercise.testutils.builder.ThemeBuilder;
import com.expercise.testutils.builder.UserBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.expercise.testutils.asserts.Asserts.assertExpectedItems;
import static com.expercise.testutils.asserts.Asserts.assertNotExpectedItems;

public class ChallengeDaoTest extends AbstractDaoTest {

    @Autowired
    private ChallengeDao dao;

    @Test
    public void shouldFindAllApproved() {
        User user = new UserBuilder().persist(getCurrentSession());
        Challenge challenge1 = new ChallengeBuilder().user(user).approved(true).persist(getCurrentSession());
        Challenge challenge2 = new ChallengeBuilder().user(user).approved(false).persist(getCurrentSession());
        Challenge challenge3 = new ChallengeBuilder().user(user).approved(true).persist(getCurrentSession());

        flushAndClear();

        List<Challenge> resultList = dao.findAllApproved();

        assertExpectedItems(resultList, challenge1, challenge3);
        assertNotExpectedItems(resultList, challenge2);
    }

    @Test
    public void shouldFindAllByUser() {
        User user1 = new UserBuilder().persist(getCurrentSession());
        User user2 = new UserBuilder().persist(getCurrentSession());
        Challenge challenge1 = new ChallengeBuilder().user(user2).persist(getCurrentSession());
        Challenge challenge2 = new ChallengeBuilder().user(user1).persist(getCurrentSession());
        Challenge challenge3 = new ChallengeBuilder().user(user1).persist(getCurrentSession());

        flushAndClear();

        List<Challenge> resultList = dao.findAllByUser(user1);

        assertExpectedItems(resultList, challenge2, challenge3);
        assertNotExpectedItems(resultList, challenge1);
    }

    @Test
    public void shouldFindNotThemedApprovedChallenges() {
        User user = new UserBuilder().persist(getCurrentSession());

        Level themedLevel = new LevelBuilder().persist(getCurrentSession());
        new ThemeBuilder().levels(themedLevel).persist(getCurrentSession());

        Level notThemedLevel = new LevelBuilder().persist(getCurrentSession());

        Challenge challenge1 = new ChallengeBuilder().user(user).approved(false).persist(getCurrentSession());
        Challenge challenge2 = new ChallengeBuilder().user(user).level(themedLevel).approved(true).persist(getCurrentSession());
        Challenge challenge3 = new ChallengeBuilder().user(user).approved(true).persist(getCurrentSession());
        Challenge challenge4 = new ChallengeBuilder().user(user).level(notThemedLevel).approved(true).persist(getCurrentSession());

        flushAndClear();

        List<Challenge> resultList = dao.findNotThemedApprovedChallenges();

        assertExpectedItems(resultList, challenge3, challenge4);
        assertNotExpectedItems(resultList, challenge1, challenge2);
    }

}
