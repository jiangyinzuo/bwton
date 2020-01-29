package pers.jiangyinzuo.carbon.dao.cache;

import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public interface LeaderBoardCache {
    List<Object> getLeaderBoard(Set<Long> usersId);
}
