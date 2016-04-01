package com.mmt.shubh.database.repository.category;

import com.mmt.shubh.database.BaseRepository;
import com.mmt.shubh.database.entity.CategoryEntity;
import com.mmt.shubh.database.entity.CategoryEntity_;
import com.mmt.shubh.database.entity.MemberEntity_;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "categorySQLRepository")
public class CategorySQLRepository extends BaseRepository<CategoryEntity> implements ICategoryRepository {

    public CategorySQLRepository() {
        setClazz(CategoryEntity.class);
    }

    @Override
    public long createCategory(CategoryEntity category) {
        create(category);
        return category.getId();
    }

    @Override
    public void updateCategory(CategoryEntity category) {
        update(category);
    }

    @Override
    public CategoryEntity getCategory(long serverId) {
        return findOne(serverId);
    }

    @Override
    public void deleteCategory(long serverId) {
        deleteById(serverId);
    }

    @Override
    public void deleteCategory(CategoryEntity categoryEntity) {
        delete(categoryEntity);
    }


    @Override
    public List<CategoryEntity> getCategoriesByMember(long memberServerId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Metamodel metamodel = entityManager.getMetamodel();
        Root<CategoryEntity> root = query.from(metamodel.entity(CategoryEntity.class));
        query.select(root);
        query.where(builder.equal(root.get(CategoryEntity_.owner).get(MemberEntity_.id), memberServerId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteAllCategoryForMember(long memberServerId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Metamodel metamodel = entityManager.getMetamodel();
        Root<CategoryEntity> root = query.from(metamodel.entity(CategoryEntity.class));
        query.select(root);
        //TODO: Delete all category for a member
    }
}
