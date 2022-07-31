package com.acme.mytrader.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSourceImpl;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class PriceSourceImplTest {


  @Test
  public void addPriceListener_shouldAddToAList() {
    PriceListener priceListener = Mockito.mock(PriceListener.class);
    PriceSourceImpl priceSource = new PriceSourceImpl();
    priceSource.addPriceListener(priceListener);
    assertThat(priceSource.getPriceListeners()).hasSize(1);
  }

  @Test
  public void addPriceListenerOfTwoListeners_shouldAddToAList() {
    PriceListener priceListener1 = Mockito.mock(PriceListener.class);
    PriceListener priceListener2 = Mockito.mock(PriceListener.class);
    PriceSourceImpl priceSource = new PriceSourceImpl();
    priceSource.addPriceListener(priceListener1);
    priceSource.addPriceListener(priceListener2);

    assertThat(priceSource.getPriceListeners()).hasSize(2);
  }

  @Test
  public void removePriceListenerOfOneListeners_shouldRemoveListener() {
    PriceListener priceListener1 = Mockito.mock(PriceListener.class);
    PriceListener priceListener2 = Mockito.mock(PriceListener.class);
    PriceSourceImpl priceSource = new PriceSourceImpl();
    priceSource.addPriceListener(priceListener1);
    priceSource.addPriceListener(priceListener2);
    priceSource.removePriceListener(priceListener2);
    assertThat(priceSource.getPriceListeners()).hasSize(1);
  }


}
