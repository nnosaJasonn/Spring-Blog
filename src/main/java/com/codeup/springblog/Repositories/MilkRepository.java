package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Pump;
import org.springframework.data.repository.CrudRepository;

public interface MilkRepository extends CrudRepository<Pump, Long> {

    Iterable<Pump> findAllByDateOrderByTimeAsc(String today);

}
