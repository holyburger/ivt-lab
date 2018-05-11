package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockFirstTorpedo;
  private TorpedoStore mockSecondTorpedo;

  private boolean mockWasPrimaryFiredLast;

  @Before
  public void init(){
    mockFirstTorpedo=mock(TorpedoStore.class);
    mockSecondTorpedo=mock(TorpedoStore.class);

    this.ship = new GT4500(mockFirstTorpedo,mockSecondTorpedo);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    when(mockFirstTorpedo.isEmpty()).thenReturn(false);
    when(mockSecondTorpedo.isEmpty()).thenReturn(false);

    when(mockFirstTorpedo.fire(1)).thenReturn(true);
    when(mockSecondTorpedo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedo, times(1)).isEmpty();
    verify(mockSecondTorpedo, times(0)).isEmpty();
    verify(mockFirstTorpedo, times(1)).fire(1);
    verify(mockSecondTorpedo, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_primaryEmpty(){
    // Arrange

    when(mockFirstTorpedo.isEmpty()).thenReturn(true);
    when(mockSecondTorpedo.isEmpty()).thenReturn(false);

    when(mockFirstTorpedo.fire(1)).thenReturn(false);
    when(mockSecondTorpedo.fire(1)).thenReturn(true);


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedo, times(1)).isEmpty();
    verify(mockSecondTorpedo, times(1)).isEmpty();
    verify(mockFirstTorpedo, times(0)).fire(1);
    verify(mockSecondTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_wasPrimaryFiredLast(){
    // Arrange
    when(mockFirstTorpedo.isEmpty()).thenReturn(false);
    when(mockSecondTorpedo.isEmpty()).thenReturn(true);

    when(mockFirstTorpedo.fire(1)).thenReturn(true);
    when(mockSecondTorpedo.fire(1)).thenReturn(false);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedo, times(2)).isEmpty();
    verify(mockSecondTorpedo, times(1)).isEmpty();
    verify(mockFirstTorpedo, times(2)).fire(1);
    verify(mockSecondTorpedo, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_wasPrimaryFiredLast_firstEmpty(){
    // Arrange
    when(mockFirstTorpedo.isEmpty()).thenReturn(false);
    when(mockSecondTorpedo.isEmpty()).thenReturn(false);

    when(mockFirstTorpedo.fire(1)).thenReturn(true);
    when(mockSecondTorpedo.fire(1)).thenReturn(true);


    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedo, times(1)).isEmpty();
    verify(mockSecondTorpedo, times(1)).isEmpty();
    verify(mockFirstTorpedo, times(1)).fire(1);
    verify(mockSecondTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    when(mockFirstTorpedo.fire(1)).thenReturn(true);
    when(mockSecondTorpedo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedo, times(1)).isEmpty();
    verify(mockSecondTorpedo, times(1)).isEmpty();
  }

}
