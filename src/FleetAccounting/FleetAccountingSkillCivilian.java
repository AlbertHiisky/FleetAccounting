package FleetAccounting;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.skills.BaseSkillEffectDescription;
import com.fs.starfarer.api.characters.FleetTotalItem;
import com.fs.starfarer.api.characters.FleetTotalSource;
import com.fs.starfarer.api.characters.ShipSkillEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import java.util.ArrayList;
import java.util.List;


public class FleetAccountingSkillCivilian {

    public static class Level1 extends BaseSkillEffectDescription implements ShipSkillEffect, FleetTotalSource {

        public FleetTotalItem getFleetTotalItem() {
            return getCivilianOPTotal();
        }

        public void apply(MutableShipStatsAPI stats, HullSize hullSize, String id, float level) {}

        public void unapply(MutableShipStatsAPI stats, HullSize hullSize, String id) {}

        public ScopeDescription getScopeDescription() {
            return ScopeDescription.FLEET;
        }

        public FleetTotalItem getCivilianOPTotal() {
            final CampaignFleetAPI fleet = Global.getSector().getPlayerFleet();
            final MutableCharacterStatsAPI stats = Global.getSector().getPlayerStats();
            FleetTotalItem item = new FleetTotalItem();
            item.label = "Civilian ship ordnance points";
            if (USE_RECOVERY_COST) {
                item.label = "Civilian ships";
            }
            item.value = "" + (int) getTotalCivilianOP(fleet.getFleetData(), stats);
            item.sortOrder = 100;

            item.tooltipCreator = getTooltipCreator(new BaseSkillEffectDescription.TooltipCreatorSkillEffectPlugin() {
                public void addDescription(TooltipMakerAPI tooltip, boolean expanded, Object tooltipParam) {
                    float opad = 10f;
                    tooltip.addPara("The total deployment points of all the civilian ships in your fleet.", 0f);
                }
                public List<BaseSkillEffectDescription.FleetMemberPointContrib> getContributors() {
                    return getTotalCivilianOPDetail(fleet.getFleetData(), stats);
                }
            });

            return item;
        }

        public static float getTotalCivilianOP(FleetDataAPI data, MutableCharacterStatsAPI stats) {
            float op = 0;
            for (FleetMemberAPI curr : data.getMembersListCopy()) {
                if (curr.isMothballed()) continue;
                if (!isCivilian(curr)) continue;
                op += getPoints(curr, stats);
            }
            return Math.round(op);
        }

        public static List<FleetMemberPointContrib> getTotalCivilianOPDetail(FleetDataAPI data, MutableCharacterStatsAPI stats) {
            List<FleetMemberPointContrib> result = new ArrayList<FleetMemberPointContrib>();
            for (FleetMemberAPI curr : data.getMembersListCopy()) {
                if (curr.isMothballed()) continue;
                if (!isCivilian(curr)) continue;
                int pts = (int) Math.round(getPoints(curr, stats));
                result.add(new FleetMemberPointContrib(curr, pts));
            }
            return result;
        }
    }


}
