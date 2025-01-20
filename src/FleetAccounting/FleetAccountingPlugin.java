package FleetAccounting;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import lunalib.lunaSettings.LunaSettings;

public class FleetAccountingPlugin extends BaseModPlugin {
    @Override
    public void onGameLoad(boolean newGame) {

        boolean count_all = true;
        boolean count_combat = true;
        boolean count_civilian = true;
        boolean count_automated = true;
        boolean count_militarized = true;
        boolean count_phase = true;


        if (Global.getSettings().getModManager().isModEnabled("second_in_command"))
        {
            count_automated = false;
        }

        if (Global.getSettings().getModManager().isModEnabled("lunalib"))
        {
            count_all = Boolean.TRUE.equals(LunaSettings.getBoolean("fleetaccounting", "count_all"));
            count_combat = Boolean.TRUE.equals(LunaSettings.getBoolean("fleetaccounting", "count_combat"));
            count_civilian = Boolean.TRUE.equals(LunaSettings.getBoolean("fleetaccounting", "count_civilian"));
            count_automated = Boolean.TRUE.equals(LunaSettings.getBoolean("fleetaccounting", "count_automated"));
            count_militarized = Boolean.TRUE.equals(LunaSettings.getBoolean("fleetaccounting", "count_militarized"));
            count_phase = Boolean.TRUE.equals(LunaSettings.getBoolean("fleetaccounting", "count_phase"));
        }


        if (count_all) {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_all", 1);
        } else {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_all", 0);
        }

        if (count_combat) {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_combat", 1);
        } else {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_combat", 0);
        }

        if (count_civilian) {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_civilian", 1);
        } else {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_civilian", 0);
        }

        if (count_automated) {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_automated", 1);
        } else {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_automated", 0);
        }

        if (count_militarized) {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_militarized", 1);
        } else {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_militarized", 0);
        }

        if (count_phase) {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_phase", 1);
        } else {
            Global.getSector().getPlayerPerson().getStats().setSkillLevel("fleet_accounting_phase", 0);
        }
    }
}
