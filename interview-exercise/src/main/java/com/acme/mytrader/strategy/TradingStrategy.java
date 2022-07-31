package com.acme.mytrader.strategy;

import static java.util.Arrays.asList;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.BuyPriceListener;
import com.acme.mytrader.price.PriceSourceImpl;
import com.acme.mytrader.price.PriceSourceRunnable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@AllArgsConstructor
@Getter
public class TradingStrategy {

	private ExecutionService executionServiceImpl;
	private PriceSourceRunnable priceSource;

	public TradingStrategy(ExecutionServiceImpl executionServiceImpl2, PriceSourceImpl priceSourceImpl) {
		this.executionServiceImpl = executionServiceImpl2;
		this.priceSource = priceSourceImpl;
	}

	public void autoBuy(List<SecurityDTO> request) throws InterruptedException {

		request.stream().map(r -> new BuyPriceListener(r.getSecurity(), r.getPriceThreshold(), r.getVolume(),
				executionServiceImpl, false)).forEach(priceSource::addPriceListener);

	}

}

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
class SecurityDTO {

  private String security;
  private double priceThreshold;
  private  int volume;
  
		public String getSecurity() {
		return security;
			
		}
		public int getVolume() {
		
			return volume;
		}
		public double getPriceThreshold() {
		
			return priceThreshold;
		}
		public void setSecurity(String security) {
			this.security = security;
		}
		public void setPriceThreshold(double priceThreshold) {
			this.priceThreshold = priceThreshold;
		}
		public void setVolume(int volume) {
			this.volume = volume;
		}
}
