package edu.hstc.roast.dao;

import edu.hstc.roast.module.Posting;

import java.util.List;

public interface PostingMapper {
    int add(Posting posting);
    List<Posting> queryAll();
    Posting queryPostingByID(String id);
    List<Posting> queryPostingsByNameOrContext(Posting posting);
    List<Posting> queryPostingByUserID(int id);
    int updatePostingByID(Posting posting);
    int deletePostingByID(String id);

}
