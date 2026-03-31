package com.ebv14.backend.repository;

import com.ebv14.backend.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByUsuarioIdOrderByFechaTransaccionDesc(Long usuarioId);

    List<Transaccion> findByUsuarioIdAndTipo(Long usuarioId, Transaccion.TipoTransaccion tipo);

    @Query("SELECT COALESCE(SUM(t.monto), 0) FROM Transaccion t WHERE t.usuario.id = :usuarioId AND t.tipo = 'INGRESO'")
    BigDecimal sumIngresosByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT COALESCE(SUM(t.monto), 0) FROM Transaccion t WHERE t.usuario.id = :usuarioId AND t.tipo = 'GASTO'")
    BigDecimal sumGastosByUsuarioId(@Param("usuarioId") Long usuarioId);
}
