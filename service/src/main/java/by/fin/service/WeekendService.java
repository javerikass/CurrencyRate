package by.fin.service;

import by.fin.module.entity.Weekend;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface WeekendService {

  List<Weekend> findAll();

}
