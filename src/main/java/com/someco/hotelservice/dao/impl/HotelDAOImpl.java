package com.someco.hotelservice.dao.impl;

import com.someco.hotelservice.api.request.FindHotelRequest;
import com.someco.hotelservice.model.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelDAOImpl implements HotelFacade {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> findHotelsByCriteria(FindHotelRequest findHotelRequest, String orderProperty, String orderType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> root = criteriaQuery.from(Hotel.class);

        List<Predicate> predicateList = getPredicateList(findHotelRequest, criteriaBuilder, root);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));

        if (StringUtils.hasText(orderProperty)) {
            if ("desc".equalsIgnoreCase(orderType)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderProperty)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderProperty)));
            }
        }
        TypedQuery<Hotel> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((findHotelRequest.getOffset() - 1) * findHotelRequest.getLimit());
        typedQuery.setMaxResults(findHotelRequest.getLimit());
        return typedQuery.getResultList();
    }

    private List<Predicate> getPredicateList(FindHotelRequest findHotelRequest, CriteriaBuilder criteriaBuilder, Root<Hotel> root) {
        List<Predicate> predicateList = new ArrayList<>();
        if (findHotelRequest.getName() != null) {
            predicateList.add(criteriaBuilder.like(root.get("hotelName"), findHotelRequest.getName()));
        }
        if (findHotelRequest.getAddress() != null) {
            predicateList.add(criteriaBuilder.like(root.get("hotelAddress"), findHotelRequest.getAddress()));
        }
        return predicateList;
    }


    @Override
    public Long getTotalSize(FindHotelRequest findHotelRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<Hotel> root = criteriaQuery.from(Hotel.class);
        criteriaQuery.select(criteriaBuilder.count(root));

        List<Predicate> predicateList = getPredicateList(findHotelRequest, criteriaBuilder, root);
        criteriaQuery.where(predicateList.toArray(new Predicate[0]));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }
}
