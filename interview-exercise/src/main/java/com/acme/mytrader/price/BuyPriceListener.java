package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class BuyPriceListener implements PriceListener {

  private  String security;
  private  double triggerLevel;
  private  int quantityToPurchase;
  private  ExecutionService executionService;

  private boolean tradeExecuted;

  public BuyPriceListener(String security2, double priceThreshold, int volume, ExecutionService executionServiceImpl,
		boolean b) {
	this.security =  security2;
	this.executionService = executionServiceImpl;
	this.quantityToPurchase =  volume;
	this.triggerLevel =  priceThreshold;
	this.tradeExecuted = b;
	
}

@Override
  public void priceUpdate(String security, double price) {
    if (canBuy(security, price)) {
      executionService.buy(security, price, quantityToPurchase);
      tradeExecuted = true;
    }
  }

  private boolean canBuy(String security, double price) {
    return (!tradeExecuted) && this.security.equals(security) && (price < this.triggerLevel);
  }

/**
 * @return the security
 */
public String getSecurity() {
	return this.security;
}

/**
 * @param security the security to set
 */
public void setSecurity(String security) {
	this.security = security;
}

/**
 * @return the triggerLevel
 */
public double getTriggerLevel() {
	return this.triggerLevel;
}

/**
 * @param triggerLevel the triggerLevel to set
 */
public void setTriggerLevel(double triggerLevel) {
	this.triggerLevel = triggerLevel;
}

/**
 * @return the quantityToPurchase
 */
public int getQuantityToPurchase() {
	return this.quantityToPurchase;
}

/**
 * @param quantityToPurchase the quantityToPurchase to set
 */
public void setQuantityToPurchase(int quantityToPurchase) {
	this.quantityToPurchase = quantityToPurchase;
}

/**
 * @return the executionService
 */
public ExecutionService getExecutionService() {
	return this.executionService;
}

/**
 * @param executionService the executionService to set
 */
public void setExecutionService(ExecutionService executionService) {
	this.executionService = executionService;
}

/**
 * @return the tradeExecuted
 */
public boolean isTradeExecuted() {
	return this.tradeExecuted;
}

/**
 * @param tradeExecuted the tradeExecuted to set
 */
public void setTradeExecuted(boolean tradeExecuted) {
	this.tradeExecuted = tradeExecuted;
}
}
