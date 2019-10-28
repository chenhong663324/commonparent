package edu.hstc.roast.service;

import edu.hstc.roast.dao.PostingMapper;
import edu.hstc.roast.module.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostingServiceImpl implements PostingService {
    @Autowired
    PostingMapper postingMapper;

    @Override
    public int add(Posting posting) {
        return postingMapper.add(posting);
    }

    @Override
    public List<Posting> queryAll() {
        return postingMapper.queryAll();
    }

    @Override
    public Posting queryPostingByID(String id) {
        return postingMapper.queryPostingByID(id);
    }

    @Override
    public List<Posting> queryPostingsByNameOrContext(Posting posting) {
        return postingMapper.queryPostingsByNameOrContext(posting);
    }

    @Override
    public List<Posting> queryPostingByUserID(int id) {
        return postingMapper.queryPostingByUserID(id);
    }

    @Override
    public int updatePostingByID(Posting posting) {
        return postingMapper.updatePostingByID(posting);
    }

    @Override
    public int deletePostingByID(String id) {
        return postingMapper.deletePostingByID(id);
    }
}
