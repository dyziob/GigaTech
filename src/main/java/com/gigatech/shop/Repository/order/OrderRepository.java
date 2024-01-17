package com.gigatech.shop.Repository.order;
// Repozytorium OrderRepository odpowiedzialne za operacje na encji Order.
import com.gigatech.shop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Brak dodatkowych metod specyficznych dla repozytorium OrderRepository.
    // Korzysta z gotowych operacji CRUD dla encji Order.
}
