package com.acme.mytrader.execution;

import lombok.*;


@Getter
public class ExecutionServiceImpl implements ExecutionService {

	  private  int id ;

	
	public ExecutionServiceImpl(int i) {
		this.id = i;
	}

	@Override
	  public void buy(String security, double price, int volume) {
	    System.out.printf("\n BUY Trade executed for %s @ £ %.2f for %d number of securities", security,
	        price, volume);
	  }

	  @Override
	  public void sell(String security, double price, int volume) {
		  System.out.printf("\n Sell Trade executed for %s @ £ %.2f for %d number of securities", security,
			        price, volume);
	  }

}
