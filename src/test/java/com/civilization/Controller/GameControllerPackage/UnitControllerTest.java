package com.civilization.Controller.GameControllerPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.civilization.Model.Civilization;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Settler;
import com.civilization.Model.Units.Unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UnitControllerTest{

    @Mock
    Unit unit;

    @Mock
    MilitaryUnit militaryUnit;

    @Mock
    Settler settler;

    @Mock
    Civilization civilization1;

    @Mock
    Civilization civilization2;

    @Test
    public void sleepNoUnit() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(null);
        Assertions.assertEquals("No unit selected!", unitController.sleep());
    }

    @Test
    public void sleepSelect() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(unit);
        GameDataBase.setCurrentCivilization(civilization2);
        assertEquals("selectedo bayad har turn new mikardim", unitController.sleep());
        GameDataBase.setSelected(null);
        GameDataBase.setCurrentCivilization(null);
    }

    @Test
    public void sleepSuccessfull() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(unit);
        GameDataBase.setCurrentCivilization(civilization1);
        when(unit.getCivilization()).thenReturn(civilization1);
        assertEquals("Unit slept successfully!", unitController.sleep());
        GameDataBase.setSelected(null);
        GameDataBase.setCurrentCivilization(null);
    }

    @Test
    public void alertNoUnit() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(null);
        Assertions.assertEquals("No unit selected!", unitController.alert());
    }

    @Test
    public void alertSelect() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(unit);
        GameDataBase.setCurrentCivilization(civilization2);
        assertEquals("selectedo bayad har turn new mikardim", unitController.sleep());
        GameDataBase.setSelected(null);
        GameDataBase.setCurrentCivilization(null);
    }

    @Test
    public void alertNotMilitary() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(settler);
        GameDataBase.setCurrentCivilization(civilization1);
        when(settler.getCivilization()).thenReturn(civilization1);
        assertEquals("This is not a military unit!", unitController.alert());
        GameDataBase.setSelected(null);
        GameDataBase.setCurrentCivilization(null);
    }

    @Test
    public void alertSuccessfull() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(militaryUnit);
        GameDataBase.setCurrentCivilization(civilization1);
        when(militaryUnit.getCivilization()).thenReturn(civilization1);
        assertEquals("Unit is in alert!", unitController.alert());
        GameDataBase.setSelected(null);
        GameDataBase.setCurrentCivilization(null);
    }

}
