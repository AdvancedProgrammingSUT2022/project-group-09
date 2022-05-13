package com.civilization.Controller.GameControllerPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.civilization.Model.Civilization;
import com.civilization.Model.Units.MilitaryUnit;
import com.civilization.Model.Units.Settler;
import com.civilization.Model.Units.Unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void reset() {
        GameDataBase.setSelected(null);
        GameDataBase.setCurrentCivilization(null);
    }

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
    }

    @Test
    public void sleepSuccessfull() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(unit);
        GameDataBase.setCurrentCivilization(civilization1);
        when(unit.getCivilization()).thenReturn(civilization1);
        assertEquals("Unit slept successfully!", unitController.sleep());
    }

    private void selectSetup() {
        GameDataBase.setSelected(unit);
        GameDataBase.setCurrentCivilization(civilization2);
    }

    private void notMilitarySetup() {
        GameDataBase.setSelected(settler);
        GameDataBase.setCurrentCivilization(civilization1);
        when(settler.getCivilization()).thenReturn(civilization1);
    }

    private void successfullSetUp() {
        GameDataBase.setSelected(militaryUnit);
        GameDataBase.setCurrentCivilization(civilization1);
        when(militaryUnit.getCivilization()).thenReturn(civilization1);
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
        selectSetup();
        assertEquals("selectedo bayad har turn new mikardim", unitController.sleep());
    }

    @Test
    public void alertNotMilitary() {
        UnitController unitController = new UnitController();
        notMilitarySetup();
        assertEquals("This is not a military unit!", unitController.alert());
    }

    @Test
    public void alertSuccessfull() {
        UnitController unitController = new UnitController();
        successfullSetUp();
        assertEquals("Unit is in alert!", unitController.alert());
    }

    @Test
    public void fortifyNoUnit() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(null);
        Assertions.assertEquals("No unit selected!", unitController.fortify());
    }

    @Test
    public void fortifySelect() {
        UnitController unitController = new UnitController();
        selectSetup();
        assertEquals("selectedo bayad har turn new mikardim", unitController.fortify());
    }

    @Test
    public void fortifyNotMilitary() {
        UnitController unitController = new UnitController();
        notMilitarySetup();
        assertEquals("This is not a military unit!", unitController.fortify());
    }

    @Test
    public void fortifySuccessfull() {
        UnitController unitController = new UnitController();
        successfullSetUp();
        assertEquals("Unit is fortify!", unitController.fortify());
    }

    @Test
    public void foritfyHealNoUnit() {
        UnitController unitController = new UnitController();
        GameDataBase.setSelected(null);
        Assertions.assertEquals("No unit selected!", unitController.fortifyHeal());
    }

    @Test
    public void fortifyHealSelect() {
        UnitController unitController = new UnitController();
        selectSetup();
        assertEquals("selectedo bayad har turn new mikardim", unitController.fortifyHeal());
    }

    @Test
    public void fortifyHealNotMilitary() {
        UnitController unitController = new UnitController();
        notMilitarySetup();
        assertEquals("This is not a military unit!", unitController.fortifyHeal());
    }

    @Test
    public void fortifyHealSuccessfull() {
        UnitController unitController = new UnitController();
        successfullSetUp();
        assertEquals("Unit is fortify until heal!", unitController.fortifyHeal());
    }
}
