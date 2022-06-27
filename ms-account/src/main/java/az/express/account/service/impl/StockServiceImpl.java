package az.express.account.service.impl;

import az.express.account.repository.StockRepository;
import az.express.account.model.entity.Stock;
import az.express.account.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> getStocks() {
        return stockRepository.getStocks();
    }
}
