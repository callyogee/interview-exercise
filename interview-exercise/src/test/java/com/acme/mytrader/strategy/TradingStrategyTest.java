package com.acme.mytrader.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSourceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class TradingStrategyTest {

	
	  @SneakyThrows
	  @Test
	  public void testAutoBuyForSuccessfulBuy() throws InterruptedException {
		  ExecutionServiceImpl tradeExecutionService = Mockito.mock(ExecutionServiceImpl.class);
	    PriceSourceImpl priceSource = new MockPriceSource("IBM", 25.00);
	    TradingStrategy tradingStrategy = new TradingStrategy(tradeExecutionService, priceSource);
	    SecurityDTO ibm = new SecurityDTO();
	    ibm.setPriceThreshold(50.00);
	    ibm.setSecurity("IBM");
	    ibm.setVolume(10);
	    List<SecurityDTO> input = Arrays.asList(ibm);
	    tradingStrategy.autoBuy(input);
	    verify(tradeExecutionService, times(1));
	   
	  }

  @SneakyThrows
  @Test
  public void testAutoBuyForNotSuccessfulBuy() throws Exception {
	ExecutionServiceImpl tradeExecutionService = Mockito.mock(ExecutionServiceImpl.class);
    PriceSourceImpl priceSource = new MockPriceSource("IBM", 25.00);

    TradingStrategy tradingStrategy = new TradingStrategy(tradeExecutionService, priceSource);
    SecurityDTO ibm = new SecurityDTO();
    ibm.setPriceThreshold(50.00);
    ibm.setSecurity("ABC");
    ibm.setVolume(10);
    List<SecurityDTO> input = Arrays.asList(ibm);    
    tradingStrategy.autoBuy(input);
    verifyZeroInteractions(tradeExecutionService);
  }

  private class MockPriceSource extends PriceSourceImpl {

    String security;
    double price;

    MockPriceSource(String security, double price) {
      this.security = security;
      this.price = price;
    }

    private final List<PriceListener> priceListeners = new CopyOnWriteArrayList<>();

    @Override
    public void addPriceListener(PriceListener listener) {
      priceListeners.add(listener);
    }

    @Override
    public void removePriceListener(PriceListener listener) {
      priceListeners.remove(listener);
    }

    @Override
    public void run() {
      priceListeners.forEach(priceListener -> priceListener.priceUpdate(security, price));
    }
  }
}
