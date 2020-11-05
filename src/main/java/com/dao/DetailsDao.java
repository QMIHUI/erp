package com.dao;

import com.bean.Details;

public interface DetailsDao {
    int deleteByPrimaryKey(Integer detailsId);

    int insert(Details record);

    int insertSelective(Details record);

    Details selectByPrimaryKey(Integer detailsId);

    int updateByPrimaryKeySelective(Details record);

    int updateByPrimaryKey(Details record);
}