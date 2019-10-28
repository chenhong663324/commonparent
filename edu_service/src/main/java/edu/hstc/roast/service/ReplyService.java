package edu.hstc.roast.service;

import edu.hstc.roast.module.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyService {
    int add(Reply reply);
    List<Reply> queryAll();
    Reply queryReplyByID(Reply reply);
    List<Reply> queryRepliesOfUserByUserID(int id);
    List<Reply> queryAllRepliesOfPostingByPostingID(String posting_id);
    List<Reply> queryRepliesForPostingByPostingID(String posting_id);
    List<Reply> queryReplyForReplyByPostingID(String posting_id);
    List<Reply> queryChildRepliesByID(Reply reply);
    List<Reply> queryChildRepliesByIDUseMap(Map map);
    int updateReplyByID(Reply reply);
    int deleteReplyByID(String id);
}
