package edu.hstc.roast.service;

import edu.hstc.roast.dao.ReplyMapper;
import edu.hstc.roast.module.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;


    @Override
    public int add(Reply reply) {
        return replyMapper.add(reply);
    }

    @Override
    public List<Reply> queryAll() {
        List<Reply> replies=replyMapper.queryAll();

        return replies;
    }

    @Override
    public Reply queryReplyByID(Reply reply) {
        return replyMapper.queryReplyByID(reply);
    }

    @Override
    public List<Reply> queryRepliesOfUserByUserID(int id) {
        return replyMapper.queryRepliesOfUserByUserID(id);
    }

    @Override
    public List<Reply> queryAllRepliesOfPostingByPostingID(String posting_id) {
        return replyMapper.queryAllRepliesOfPostingByPostingID(posting_id);
    }

    @Override
    public List<Reply> queryRepliesForPostingByPostingID(String posting_id) {
        return replyMapper.queryRepliesForPostingByPostingID(posting_id);
    }

    @Override
    public List<Reply> queryReplyForReplyByPostingID(String posting_id) {
        return replyMapper.queryReplyForReplyByPostingID(posting_id);
    }

    @Override
    public List<Reply> queryChildRepliesByID(Reply reply) {
        return replyMapper.queryChildRepliesByID(reply);
    }

    @Override
    public List<Reply> queryChildRepliesByIDUseMap(Map map) {
        return replyMapper.queryChildRepliesByIDUseMap(map);
    }


    @Override
    public int updateReplyByID(Reply reply) {
        return replyMapper.updateReplyByID(reply);
    }

    @Override
    public int deleteReplyByID(String id) {

        return replyMapper.deleteReplyByID(id);
    }


}
