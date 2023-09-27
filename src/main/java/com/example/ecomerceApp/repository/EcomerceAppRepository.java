package com.example.ecomerceApp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ecomerceApp.model.Price;

@Repository
public interface EcomerceAppRepository extends JpaRepository<Price, Long> {

	@Query(value = "SELECT p FROM Price p WHERE p.productId = :idProduct and p.brandId = :brandId and :appDate BETWEEN p.startDate and p.endDate ")
	public Optional<List<Price>> findBFilter(@Param("idProduct") Integer idProduct, @Param("appDate") LocalDateTime appDate,
			@Param("brandId") Integer brandId);

}
