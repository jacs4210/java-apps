package co.edu.usbcali.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.bank.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
