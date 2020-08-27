package co.grandcircus.Lab24;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface PartyRepo extends JpaRepository<Party, Long> {
  @Query("from Party where name like %:prefix% ")
  List<Party> findPartyByName(@Param("prefix") String name);
	
}
