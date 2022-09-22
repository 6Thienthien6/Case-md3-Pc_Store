package com.casemd3pcstore.dao;

import com.casemd3pcstore.model.Category;

import java.util.List;

public interface ICategoryDAO {
    List<Category> selectAllCategories();

    Category findById(int categoryId);
}
