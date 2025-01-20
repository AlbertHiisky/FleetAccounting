package FleetAccounting;

import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.characters.FleetTotalItem;
import com.fs.starfarer.api.characters.FleetTotalSource;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;


public class FleetAccountingSkillPhase {

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, FleetTotalSource {

        public FleetTotalItem getFleetTotalItem() {
            return getPhaseOPTotal();
        }

        public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {}

        public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {}

        public ScopeDescription getScopeDescription() {
            return ScopeDescription.FLEET;
        }
    }
}
