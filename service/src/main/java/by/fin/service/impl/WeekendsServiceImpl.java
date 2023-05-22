package by.fin.service.impl;

import by.fin.module.entity.Weekend;
import by.fin.module.repository.WeekendsRepository;
import by.fin.service.WeekendService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WeekendsServiceImpl implements WeekendService {

  private final WeekendsRepository weekendsRepository;

  @Override
  public List<Weekend> findAll() {
    return weekendsRepository.findAll();
  }

}
